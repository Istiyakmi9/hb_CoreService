package com.hiringbell.hbserver.serviceImpl;

import com.hiringbell.hbserver.Repository.EmployeeRepository;
import com.hiringbell.hbserver.Repository.LoginRepository;
import com.hiringbell.hbserver.entity.Employee;
import com.hiringbell.hbserver.entity.Login;
import com.hiringbell.hbserver.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    LoginRepository loginRepository;

    @Transactional(rollbackFor = Exception.class)
    public String addEmployeeService(Employee employee) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        var lastEmployeeId = this.employeeRepository.getLastEmployeeId();
        if (lastEmployeeId == null){
            employee.setEmployeeId(1L);
        }else {
            employee.setEmployeeId(lastEmployeeId.getEmployeeId()+1);
        }
        employee.setCreatedOn(currentDate);
        this.employeeRepository.save(employee);

        Login loginDetail;
        loginDetail = new Login();
        Optional<Login> lastLoginRecord = Optional.ofNullable(this.loginRepository.getLastLoginRecord());
        if (lastLoginRecord.isEmpty()){
            loginDetail.setLoginId(1L);
        }else {
            loginDetail.setLoginId(lastLoginRecord.get().getLoginId()+1);
        }
        loginDetail.setEmployeeId(employee.getEmployeeId());
        loginDetail.setEmail(employee.getEmail());
        loginDetail.setMobile(employee.getMobile());
        loginDetail.setPassword("emp123");
        loginDetail.setRoleId(employee.getRoleId());
        loginDetail.setCreatedBy(1L);
        loginDetail.setCreatedOn(currentDate);
        loginDetail = this.loginRepository.save(loginDetail);
        if (loginDetail == null)
                throw new Exception("Fail to create login detail");

        return "New Employee has been added in Emploee and Login table";
    }


    @Transactional(rollbackFor = Exception.class)
    public String updateEmployeeService(Employee employee, long employeeId) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Optional<Employee> result = this.employeeRepository.findById(employeeId);
        if (result.isEmpty())
            throw new Exception("employee detail not found");
        Employee existingEmployee = result.get();
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setFatherName(employee.getFatherName());
        existingEmployee.setMotherName(employee.getMotherName());
        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setMobile(employee.getMobile());
        existingEmployee.setAlternetNumber(employee.getAlternetNumber());
        existingEmployee.setAddress(employee.getAddress());
        existingEmployee.setCity(employee.getCity());
        existingEmployee.setState(employee.getState());
        existingEmployee.setCountry(employee.getCountry());
        existingEmployee.setRoleId(employee.getRoleId());
        existingEmployee.setDesignationId(employee.getDesignationId());
        existingEmployee.setReporteeId(employee.getReporteeId());
        existingEmployee.setUpdatedBy(1L);
        existingEmployee.setUpdatedOn(currentDate);

        employeeRepository.save(existingEmployee);

        Login login;
        Optional<Login> loginResult = Optional.ofNullable(this.loginRepository.getLoginByEmployeeId(employeeId));
        if (loginResult.isEmpty()){
            throw new Exception("Fail to get login, please contact to admin");
        }
        login = loginResult.get();
        login.setEmail(employee.getEmail());
        login.setMobile(employee.getMobile());
        login.setUpdatedBy(1L);
        login.setUpdatedOn(currentDate);
        Login loginData = this.loginRepository.save(login);
        if (loginData == null){
            throw new Exception("fail to update login. please contact to admin");
        }
        return "Employee has been updated";
    }


    public ArrayList<Employee> getAllEmployeeService() {
        List<Employee> result = this.employeeRepository.findAll();
        return (ArrayList<Employee>) result;
    }

    public Optional<Employee> getEmployeeByEmployeeIdService(long employeeId) {
        Optional<Employee> result = this.employeeRepository.findById(employeeId);
        return result;
    }
}
