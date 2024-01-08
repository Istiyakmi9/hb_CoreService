package com.hiringbell.hbserver.serviceImpl;

import com.hiringbell.hbserver.Repository.EmployeeDetailRepository;
import com.hiringbell.hbserver.Repository.EmployeeMedicalDetailRepository;
import com.hiringbell.hbserver.Repository.EmployeeRepository;
import com.hiringbell.hbserver.Repository.LoginRepository;
import com.hiringbell.hbserver.entity.Employee;
import com.hiringbell.hbserver.entity.EmployeeDetail;
import com.hiringbell.hbserver.entity.EmployeeMedicalDetail;
import com.hiringbell.hbserver.entity.Login;
import com.hiringbell.hbserver.model.EmployeeMaster;
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

    @Autowired
    EmployeeDetailRepository employeeDetailRepository;

    @Autowired
    EmployeeMedicalDetailRepository employeeMedicalDetailRepository;

    @Transactional(rollbackFor = Exception.class)
    public String addEmployeeService(EmployeeMaster employeeMaster) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Employee employee = new Employee();
        var lastEmployeeId = this.employeeRepository.getLastEmployeeId();
        if (lastEmployeeId == null){
            employee.setEmployeeId(1L);
        }else {
            employee.setEmployeeId(lastEmployeeId.getEmployeeId()+1);
        }
        employee.setFirstName(employeeMaster.getFirstName());
        employee.setLastName(employeeMaster.getLastName());
        employee.setFatherName(employeeMaster.getFatherName());
        employee.setMotherName(employeeMaster.getMotherName());
        employee.setEmail(employeeMaster.getEmail());
        employee.setMobile(employeeMaster.getMobile());
        employee.setAlternateNumber(employeeMaster.getAlternateNumber());
        employee.setAddress(employeeMaster.getAddress());
        employee.setCity(employeeMaster.getCity());
        employee.setState(employeeMaster.getState());
        employee.setCountry(employeeMaster.getCountry());
        employee.setRoleId(employeeMaster.getRoleId());
        employee.setDesignationId(employeeMaster.getDesignationId());
        employee.setReporteeId(employeeMaster.getReporteeId());
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
        loginDetail.setEmployeeId(employeeMaster.getEmployeeId());
        loginDetail.setEmail(employeeMaster.getEmail());
        loginDetail.setMobile(employeeMaster.getMobile());
        loginDetail.setPassword("emp123");
        loginDetail.setRoleId(employeeMaster.getRoleId());
        loginDetail.setCreatedBy(1L);
        loginDetail.setCreatedOn(currentDate);
        loginDetail = this.loginRepository.save(loginDetail);
        if (loginDetail == null)
                throw new Exception("Fail to create login detail");


        EmployeeDetail employeeDetail = new EmployeeDetail();
        employeeDetail.setEmployeeId(employeeMaster.getEmployeeId());
        employeeDetail.setPan(employeeMaster.getPan());
        employeeDetail.setAadhar(employeeMaster.getAadhar());
        employeeDetail.setPassportNumber(employeeMaster.getPassportNumber());
        employeeDetail.setBankName(employeeMaster.getBankName());
        employeeDetail.setBranch(employeeMaster.getBranch());
        employeeDetail.setIfscCode(employeeMaster.getIfscCode());
        employeeDetail.setJobTypeId(employeeMaster.getJobTypeId());
        employeeDetail.setExperienceInMonths(employeeMaster.getExperienceInMonths());
        employeeDetail.setLastCompanyName(employeeMaster.getLastCompanyName());
        employeeDetail.setLastWorkingDate(employeeMaster.getLastWorkingDate());
        employeeDetail.setDesignation(employeeMaster.getDesignation());
        employeeDetail.setSalary(employeeMaster.getSalary());
        employeeDetail.setExpectedSalary(employeeMaster.getExpectedSalary());
        employeeDetail.setCreatedBy(1L);
        employeeDetail.setCreatedOn(currentDate);
        employeeDetail = this.employeeDetailRepository.save(employeeDetail);
        if (employeeDetail == null)
            throw new Exception("Failed to create employee_detail");

//        Login loginDetail;
//        loginDetail = new Login();
//        Optional<Login> lastLoginRecord = Optional.ofNullable(this.loginRepository.getLastLoginRecord());
//        if (lastLoginRecord.isEmpty()){
//            loginDetail.setLoginId(1L);
//        }else {
//            loginDetail.setLoginId(lastLoginRecord.get().getLoginId()+1);
//        }
//        loginDetail.setEmployeeId(employeeMaster.getEmployeeId());

        EmployeeMedicalDetail employeeMedicalDetail = new EmployeeMedicalDetail();
        var lastEmployeeMedicalDetailRecord = this.employeeMedicalDetailRepository.getLastEmployeeMedicalDetailRecord();
        if (lastEmployeeMedicalDetailRecord == null){
            employeeMedicalDetail.setEmployeeMedicalDetailId(1L);
        }else {
            employeeMedicalDetail.setEmployeeMedicalDetailId(lastEmployeeMedicalDetailRecord.getEmployeeMedicalDetailId()+1);
        }
        employeeMedicalDetail.setEmployeeId(employeeMaster.getEmployeeId());
        employeeMedicalDetail.setMedicalConsultancyId(employeeMaster.getMedicalConsultancyId());
        employeeMedicalDetail.setConsultedBy(employeeMaster.getConsultedBy());
        employeeMedicalDetail.setConsultedOn(employeeMaster.getConsultedOn());
        employeeMedicalDetail.setReferenceId(employeeMaster.getReferenceId());
        employeeMedicalDetail.setReportId(employeeMaster.getReportId());
        employeeMedicalDetail.setReportPath(employeeMaster.getReportPath());

        return "New Employee has been added in Emploee and Login table";
    }


    @Transactional(rollbackFor = Exception.class)
    public String updateEmployeeService(EmployeeMaster employeeMaster, long employeeId) throws Exception {
        Date utilDate = new Date();
        var currentDate = new Timestamp(utilDate.getTime());
        Optional<Employee> result = this.employeeRepository.findById(employeeId);
        if (result.isEmpty())
            throw new Exception("employee record not found");
        Employee existingEmployee = result.get();
        existingEmployee.setFirstName(employeeMaster.getFirstName());
        existingEmployee.setLastName(employeeMaster.getLastName());
        existingEmployee.setFatherName(employeeMaster.getFatherName());
        existingEmployee.setMotherName(employeeMaster.getMotherName());
        existingEmployee.setEmail(employeeMaster.getEmail());
        existingEmployee.setMobile(employeeMaster.getMobile());
        existingEmployee.setAlternateNumber(employeeMaster.getAlternateNumber());
        existingEmployee.setAddress(employeeMaster.getAddress());
        existingEmployee.setCity(employeeMaster.getCity());
        existingEmployee.setState(employeeMaster.getState());
        existingEmployee.setCountry(employeeMaster.getCountry());
        existingEmployee.setRoleId(employeeMaster.getRoleId());
        existingEmployee.setDesignationId(employeeMaster.getDesignationId());
        existingEmployee.setReporteeId(employeeMaster.getReporteeId());
        existingEmployee.setUpdatedBy(1L);
        existingEmployee.setUpdatedOn(currentDate);

        employeeRepository.save(existingEmployee);

        Login login;
        Optional<Login> loginResult = Optional.ofNullable(this.loginRepository.getLoginByEmployeeId(employeeId));
        if (loginResult.isEmpty()){
            throw new Exception("Fail to get login, please contact to admin");
        }
        login = loginResult.get();
        login.setEmail(employeeMaster.getEmail());
        login.setMobile(employeeMaster.getMobile());
        login.setUpdatedBy(1L);
        login.setUpdatedOn(currentDate);
        Login loginData = this.loginRepository.save(login);
        if (loginData == null){
            throw new Exception("fail to update login. please contact to admin");
        }

        EmployeeDetail employeeDetail;
        Optional<EmployeeDetail> employeeDetailResult = this.employeeDetailRepository.findById(employeeId);
        if (employeeDetailResult.isEmpty())
            throw new Exception("EmployeeDetail not found");
        EmployeeDetail existingEmployeeDetail = employeeDetailResult.get();
        existingEmployeeDetail.setPan(employeeMaster.getPan());
        existingEmployeeDetail.setAadhar(employeeMaster.getAadhar());
        existingEmployeeDetail.setPassportNumber(employeeMaster.getPassportNumber());
        existingEmployeeDetail.setBankName(employeeMaster.getBankName());
        existingEmployeeDetail.setBranch(employeeMaster.getBranch());
        existingEmployeeDetail.setBranch(employeeMaster.getIfscCode());
        existingEmployeeDetail.setJobTypeId(employeeMaster.getJobTypeId());
        existingEmployeeDetail.setExperienceInMonths(employeeMaster.getExperienceInMonths());
        existingEmployeeDetail.setLastCompanyName(employeeMaster.getLastCompanyName());
        existingEmployeeDetail.setLastWorkingDate(employeeMaster.getLastWorkingDate());
        existingEmployeeDetail.setDesignation(employeeMaster.getDesignation());
        existingEmployeeDetail.setSalary(employeeMaster.getSalary());
        existingEmployeeDetail.setExpectedSalary(employeeMaster.getExpectedSalary());
        existingEmployeeDetail.setExpectedDesignation(employeeMaster.getExpectedDesignation());
        existingEmployeeDetail.setUpdatedBy(1L);
        existingEmployeeDetail.setUpdatedOn(currentDate);
        EmployeeDetail employeeDetailData = this.employeeDetailRepository.save(existingEmployeeDetail);
        if (employeeDetailData == null){
            throw new Exception("fail to update EmployeeDetail");
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
