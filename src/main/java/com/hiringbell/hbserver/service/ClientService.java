package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.model.Client;

import java.util.List;

public interface ClientService {
    List<Client> getAllClientService();
    String mangeClientService(Client client) throws Exception;
    Client getClientService(int clientId);
}
