package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.entity.Employee;

import java.util.ArrayList;
import java.util.Optional;

public interface EmployeeService {

    public String addEmployeeService(Employee employee) throws Exception;
    public String updateEmployeeService(Employee employee, long employeeId) throws Exception;
    public ArrayList<Employee> getAllEmployeeService();
    public Optional<Employee> getEmployeeByEmployeeIdService(long employeeId);



}
