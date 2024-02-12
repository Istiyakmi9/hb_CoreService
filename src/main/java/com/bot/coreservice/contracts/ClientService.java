package com.bot.coreservice.contracts;

import com.bot.coreservice.model.Client;
import com.bot.coreservice.model.FilterModel;

import java.util.List;
import java.util.Map;

public interface ClientService {
    List<Client> getAllClientService(FilterModel filterModel);
    String mangeClientService(Client client) throws Exception;
    Map<String, Object> getClientService(int clientId) throws Exception;
}
