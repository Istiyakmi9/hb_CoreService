package com.hiringbell.hbserver.controller;

import com.hiringbell.hbserver.entity.Employee;
import com.hiringbell.hbserver.jwtconfig.JwtGateway;
import com.hiringbell.hbserver.model.ApiResponse;
import com.hiringbell.hbserver.model.JwtTokenModel;
import com.hiringbell.hbserver.serviceImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/addEmployee")
    public ResponseEntity<ApiResponse> addEmployee(@RequestBody Employee employee) throws Exception {
        var result = this.employeeServiceImpl.addEmployeeService(employee);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<ApiResponse> updateEmployee(@RequestBody Employee employee, @PathVariable("employeeId") long employeeId ) throws Exception {
        var result = this.employeeServiceImpl.updateEmployeeService(employee, employeeId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PostMapping("/testGenerateJwtToken")
    public ResponseEntity<ApiResponse> testGenerateJwtToken(@RequestBody JwtTokenModel jwtTokenModel) throws IOException {
        JwtGateway jwtGateway = JwtGateway.getJwtGateway();
        String result = jwtGateway.generateJwtToken(jwtTokenModel);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<ApiResponse> getAllEmployee(){
        var result = this.employeeServiceImpl.getAllEmployeeService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getEmployeeByEmployeeId/{employeeId}")
    public ResponseEntity<ApiResponse> getEmployeeByEmployeeId( @PathVariable("employeeId") long employeeId){
        var result = this.employeeServiceImpl.getEmployeeByEmployeeIdService(employeeId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

}
