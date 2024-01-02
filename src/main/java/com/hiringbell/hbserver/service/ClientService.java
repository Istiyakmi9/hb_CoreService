package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.model.Client;
import com.hiringbell.hbserver.model.FilterModel;

import java.util.List;
import java.util.Map;

public interface ClientService {
    List<Client> getAllClientService(FilterModel filterModel);
    String mangeClientService(Client client) throws Exception;
    Map<String, Object> getClientService(int clientId) throws Exception;
}
