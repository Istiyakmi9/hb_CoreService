package com.bot.coreservice.service;

import com.bot.coreservice.entity.Employee;
import com.bot.coreservice.model.EmployeeMaster;

import java.util.ArrayList;

public interface EmployeeService {

    public String addEmployeeService(EmployeeMaster employeeMaster) throws Exception;
    public String updateEmployeeService(EmployeeMaster employeeMaster, long employeeId) throws Exception;
    public ArrayList<Employee> getAllEmployeeService();
    public EmployeeMaster getEmployeeByEmployeeIdService(long employeeId);

}
