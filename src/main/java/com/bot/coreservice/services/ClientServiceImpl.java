package com.bot.coreservice.services;

import com.bot.coreservice.entity.Login;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bot.coreservice.Repository.BankAccountRepository;
import com.bot.coreservice.Repository.ClientRepository;
import com.bot.coreservice.Repository.ClientTypeRepository;
import com.bot.coreservice.Repository.CountryRepository;
import com.bot.coreservice.db.LowLevelExecution;
import com.bot.coreservice.model.BankAccount;
import com.bot.coreservice.model.Client;
import com.bot.coreservice.model.DbParameters;
import com.bot.coreservice.model.FilterModel;
import com.bot.coreservice.contracts.ClientService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ServerWebExchange;

import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    @Autowired
    CountryRepository countryRepository;
    @Autowired
    ClientTypeRepository clientTypeRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    UserContextDetail userContextDetail;
    public List<Client> getAllClientService(FilterModel filter) {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_searchString", filter.getSearchString(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_sortBy", filter.getSortBy(), Types.VARCHAR));
        dbParameters.add(new DbParameters("_pageIndex", filter.getPageIndex(), Types.INTEGER));
        dbParameters.add(new DbParameters("_pageSize", filter.getPageSize(), Types.INTEGER));
        var dataSet = lowLevelExecution.executeProcedure("sp_client_getby_filter", dbParameters);
        return objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<Client>>() {});
    }

    @Transactional(rollbackFor = Exception.class)
    public String mangeClientService(Client client, ServerWebExchange exchange) throws Exception {
        try {
            var currentUser = userContextDetail.getCurrentUserDetail(exchange);
            validateClientDetail(client);
            var bankAccountId = manageBankDetail(client, currentUser);
            client.setBankDetailId(bankAccountId);
            if (client.getId() == 0) {
                insertClientDetail(client, currentUser);
            } else  {
                updateClientDetail(client, currentUser);
            }

            return "client detail insert/updated successfully";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void validateClientDetail(Client client) throws Exception {
        if (client.getCompanyName() == null)
            throw new Exception("Company name is invalid");

        if (client.getClientTypeId() == 0)
            throw new Exception("Invalid client type selected");

        if (client.getCountryCode() == null)
            throw new Exception("Invalid country selected");

        if (client.getMobile() == null)
            throw new Exception("Mobile number is invalid");

        if (client.getEmail() == null)
            throw new Exception("Email is invalid");

        if (client.getLegalEntity() == null)
            throw new Exception("Legal company name is invalid");

        if (client.getAccountNo() == null)
            throw new Exception("Account number is invalid");

        if (client.getIFSC() == null)
            throw new Exception("IFSC Code is invalid");
    }

    private int manageBankDetail(Client client, Login currentUser) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        BankAccount bankAccount = new BankAccount();
        if (client.getBankDetailId() == 0) {
            var lastBankDetail = bankAccountRepository.getLastBankAccount();
            if (lastBankDetail == null)
                bankAccount.setBankAccountId(1);
            else
                bankAccount.setBankAccountId(lastBankDetail.getBankAccountId() + 1);
        } else {
            bankAccount = bankAccountRepository.findById(client.getBankDetailId()).orElse(null);
            if (bankAccount == null)
                throw new Exception("Bank detail not found");
        }
        bankAccount.setBankName(client.getBankName());
        bankAccount.setAccountNo(client.getAccountNo());
        bankAccount.setIfsc(client.getIFSC());
        bankAccount.setBranch(client.getBranch());
        bankAccount.setCreatedBy(currentUser.getUserId());
        bankAccount.setUpdatedBy(currentUser.getUserId());
        bankAccount.setCreatedOn(date);
        bankAccount.setUpdatedOn(date);

        bankAccountRepository.save(bankAccount);
        return  bankAccount.getBankAccountId();
    }

    private void insertClientDetail(Client client, Login currentUser) {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        var lastClient = clientRepository.getLastClient();
        if (lastClient == null)
            client.setId(1);
        else
            client.setId(lastClient.getId() + 1);

        client.setCreatedBy(currentUser.getUserId());
        client.setUpdatedBy(currentUser.getUserId());
        client.setCreatedOn(date);
        client.setUpdatedOn(date);
        clientRepository.save(client);
    }

    private void updateClientDetail(Client client, Login currentUser) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());

        var existingClientData = clientRepository.findById(client.getId());
        if (!existingClientData.isPresent())
            throw new Exception("Client detail not found");

        var clientDetail = existingClientData.get();
        clientDetail.setRepresenterId(client.getRepresenterId());
        clientDetail.setCompanyName(client.getCompanyName());
        clientDetail.setDescription(client.getDescription());
        clientDetail.setClientTypeId(client.getClientTypeId());
        clientDetail.setAddress(client.getAddress());
        clientDetail.setEmail(client.getEmail());
        clientDetail.setAlternateEmail1(client.getAlternateEmail1());
        clientDetail.setAlternateEmail2(client.getAlternateEmail2());
        clientDetail.setMobile(client.getMobile());
        clientDetail.setAlternateMobile1(client.getAlternateMobile1());
        clientDetail.setAlternateMobile2(client.getAlternateMobile2());
        clientDetail.setGstin(client.getGstin());
        clientDetail.setLicenseNo(client.getLicenseNo());
        clientDetail.setPanNumb(client.getPanNumb());
        clientDetail.setLegalEntity(client.getLegalEntity());
        clientDetail.setWebsite(client.getWebsite());
        clientDetail.setBankDetailId(client.getBankDetailId());
        clientDetail.setCountryCode(client.getCountryCode());
        clientDetail.setCity(client.getCity());
        clientDetail.setPincode(client.getPincode());
        clientDetail.setState(client.getState());
        clientDetail.setUpdatedBy(currentUser.getUserId());
        clientDetail.setUpdatedOn(date);
        clientRepository.save(clientDetail);
    }


    @Override
    public Map<String, Object> getClientService(int clientId) throws Exception {
        Client client = null;
        if (clientId > 0) {
            client = clientRepository.findById(clientId).get();
            BankAccount bankAccount= bankAccountRepository.findById(client.getBankDetailId()).get();
            if (bankAccount == null)
                throw new Exception("Bank detail not found");

            client.setBankName(bankAccount.getBankName());
            client.setAccountNo(bankAccount.getAccountNo());
            client.setIFSC(bankAccount.getIfsc());
            client.setBranch(bankAccount.getBranch());
        }

        var country = countryRepository.findAll();
        var clientType = clientTypeRepository.findAll();
        Map<String, Object> result = new HashMap<>();
        result.put("Client", client);
        result.put("ClientType", clientType);
        result.put("Country", country);

        return result;
    }
}
