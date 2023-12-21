package com.hiringbell.hbserver.serviceImpl;

import com.hiringbell.hbserver.Repository.EmployeeRepository;
import com.hiringbell.hbserver.entity.Employee;
import com.hiringbell.hbserver.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;

    public String addEmployeeService(Employee employee) {


        return null;
    }
}
