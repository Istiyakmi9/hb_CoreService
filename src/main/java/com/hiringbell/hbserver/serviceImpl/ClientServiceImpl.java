package com.hiringbell.hbserver.serviceImpl;

import com.hiringbell.hbserver.Repository.ClientRepository;
import com.hiringbell.hbserver.model.Client;
import com.hiringbell.hbserver.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    ClientRepository clientRepository;
    public List<Client> getAllClientService() {
       return clientRepository.findAll();
    }

    @Override
    public String mangeClientService(Client client) throws Exception {
        try {
            if (client.getId() == 0) {
                insertClientDetail(client);
            } else  {
                updateClientDetail(client);
            }

            return "client destail insert/updated successfully";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private void insertClientDetail(Client client) {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());
        var lastClient = clientRepository.getLastClient();
        if (lastClient == null)
            client.setId(1);
        else
            client.setId(lastClient.getId() + 1);

        client.setCreatedBy(1L);
        client.setUpdatedBy(1L);
        client.setCreatedOn(date);
        client.setUpdatedOn(date);
        clientRepository.save(client);
    }

    private void updateClientDetail(Client client) throws Exception {
        java.util.Date utilDate = new java.util.Date();
        var date = new java.sql.Timestamp(utilDate.getTime());

        var existingClientData = clientRepository.findById(client.getId());
        if (!existingClientData.isPresent())
            throw new Exception("Client detail not found");

        var clientDetail = existingClientData.get();
        clientDetail.setRepresenterId(client.getRepresenterId());
        clientDetail.setCompanyName(client.getCompanyName());
        clientDetail.setDescription(client.getDescription());
        clientDetail.setClientType(client.getClientType());
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
        clientDetail.setUpdatedBy(1L);
        clientDetail.setUpdatedOn(date);
        clientRepository.save(clientDetail);
    }


    @Override
    public Client getClientService(int clientId) {
        var client = new Client();
        if (clientId > 0)
            client = clientRepository.findById(clientId).get();

        return client;
    }
}
