package com.hiringbell.hbserver.service;

import com.hiringbell.hbserver.entity.Employee;
import com.hiringbell.hbserver.model.EmployeeMaster;

import java.util.ArrayList;
import java.util.Optional;

public interface EmployeeService {

    public String addEmployeeService(EmployeeMaster employeeMaster) throws Exception;
    public String updateEmployeeService(EmployeeMaster employeeMaster, long employeeId) throws Exception;
    public ArrayList<Employee> getAllEmployeeService();
    public EmployeeMaster getEmployeeByEmployeeIdService(long employeeId);

}
