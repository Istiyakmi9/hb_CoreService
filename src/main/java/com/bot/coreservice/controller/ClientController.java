package com.bot.coreservice.controller;

import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.model.Client;
import com.bot.coreservice.model.FilterModel;
import com.bot.coreservice.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hb/api/client/")
public class ClientController {
    @Autowired
    ClientService clientService;
    @PostMapping("getAllClient")
    public ResponseEntity<ApiResponse> getAllClient(@RequestBody FilterModel filter) throws Exception {
        var result = clientService.getAllClientService(filter);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("manageClient")
    public ResponseEntity<ApiResponse> manageClient(@RequestBody Client client) throws Exception {
        var result = clientService.mangeClientService(client);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("getClientById/{clientId}")
    public ResponseEntity<ApiResponse> getClientById(@PathVariable("clientId") int clientId) throws Exception {
        var result = clientService.getClientService(clientId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("test")
    public String test(){
        return  "Successfully";
    }
}
