package com.bot.coreservice.controller;

import com.bot.coreservice.model.ApiResponse;
import com.bot.coreservice.model.EmployeeMaster;
import com.bot.coreservice.services.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/hb/api/core/employee")
public class EmployeeController {

    @Autowired
    EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/addEmployee")
    public ResponseEntity<ApiResponse> addEmployee(@RequestBody EmployeeMaster employeeMaster) throws Exception {
        var result = this.employeeServiceImpl.addEmployeeService(employeeMaster);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @PutMapping("/updateEmployee/{employeeId}")
    public ResponseEntity<ApiResponse> updateEmployee(@RequestBody EmployeeMaster employeeMaster, @PathVariable("employeeId") long employeeId ) throws Exception {
        var result = this.employeeServiceImpl.updateEmployeeService(employeeMaster, employeeId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getAllEmployee")
    public ResponseEntity<ApiResponse> getAllEmployee(){
        var result = this.employeeServiceImpl.getAllEmployeeService();
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @GetMapping("/getEmployeeByEmployeeId/{employeeId}")
    public ResponseEntity<ApiResponse> getEmployeeByEmployeeId( @PathVariable("employeeId") long employeeId) throws Exception {
        var result = this.employeeServiceImpl.getEmployeeByEmployeeIdService(employeeId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

    @DeleteMapping("/deleteEmployeeByEmployeeId/{employeeId}")
    public ResponseEntity<ApiResponse> deleteEmployeeByEmployeeId( @PathVariable("employeeId") long employeeId) throws Exception {
        var result = this.employeeServiceImpl.deleteEmployeeByEmployeeIdService(employeeId);
        return ResponseEntity.ok(ApiResponse.Ok(result));
    }

}
