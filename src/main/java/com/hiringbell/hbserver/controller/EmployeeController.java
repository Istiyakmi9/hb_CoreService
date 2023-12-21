package com.hiringbell.hbserver.controller;

import com.hiringbell.hbserver.entity.Employee;
import com.hiringbell.hbserver.jwtconfig.JwtGateway;
import com.hiringbell.hbserver.model.ApiResponse;
import com.hiringbell.hbserver.model.JwtTokenModel;
import com.hiringbell.hbserver.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;


    public ResponseEntity<ApiResponse> addEmployee(@RequestBody Employee employee){
        this.employeeServiceImpl.addEmployeeService(employee);
        return ResponseEntity.ok(ApiResponse.Ok(null));
    }

    @PostMapping("/testGenerateJwtToken")
    public ResponseEntity<ApiResponse> testGenerateJwtToken(@RequestBody JwtTokenModel jwtTokenModel) throws IOException {
        JwtGateway jwtGateway = JwtGateway.getJwtGateway();
        String result = jwtGateway.generateJwtToken(jwtTokenModel);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }


}
