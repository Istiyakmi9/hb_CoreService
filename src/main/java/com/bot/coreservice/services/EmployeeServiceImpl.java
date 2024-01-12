package com.bot.coreservice.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.bot.coreservice.Repository.EmployeeDetailRepository;
import com.bot.coreservice.Repository.EmployeeMedicalDetailRepository;
import com.bot.coreservice.Repository.EmployeeRepository;
import com.bot.coreservice.Repository.LoginRepository;
import com.bot.coreservice.db.LowLevelExecution;
import com.bot.coreservice.entity.Employee;
import com.bot.coreservice.entity.EmployeeDetail;
import com.bot.coreservice.entity.EmployeeMedicalDetail;
import com.bot.coreservice.entity.Login;
import com.bot.coreservice.model.DbParameters;
import com.bot.coreservice.model.EmployeeMaster;
import com.bot.coreservice.contracts.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.sql.Types;
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
    @Autowired
    LowLevelExecution lowLevelExecution;
    @Autowired
    ObjectMapper objectMapper;

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
        employee.setPinCode(employeeMaster.getPinCode());
        employee.setReporteeId(employeeMaster.getReporteeId());
        employee.setActive(true);
        employee.setCreatedOn(currentDate);
        this.employeeRepository.save(employee);

        Login loginDetail = new Login();
        var lastLoginRecord = this.loginRepository.getLastLoginRecord();
        if (lastLoginRecord == null){
            loginDetail.setLoginId(1L);
        }else {
            loginDetail.setLoginId(lastLoginRecord.getLoginId()+1);
        }
        loginDetail.setEmployeeId(employee.getEmployeeId());
        loginDetail.setEmail(employeeMaster.getEmail());
        loginDetail.setMobile(employeeMaster.getMobile());
        loginDetail.setPassword("emp123");
        loginDetail.setRoleId(employeeMaster.getRoleId());
        loginDetail.setActive(true);
        loginDetail.setCreatedBy(1L);
        loginDetail.setCreatedOn(currentDate);
        this.loginRepository.save(loginDetail);

        EmployeeDetail employeeDetail = new EmployeeDetail();
        employeeDetail.setEmployeeId(employee.getEmployeeId());
        employeeDetail.setPan(employeeMaster.getPan());
        employeeDetail.setAadhar(employeeMaster.getAadhar());
        employeeDetail.setPassportNumber(employeeMaster.getPassportNumber());
        employeeDetail.setBankName(employeeMaster.getBankName());
        employeeDetail.setAccountNo(employeeMaster.getAccountNo());
        employeeDetail.setBranch(employeeMaster.getBranch());
        employeeDetail.setIfscCode(employeeMaster.getIfscCode());
        employeeDetail.setJobTypeId(employeeMaster.getJobTypeId());
        employeeDetail.setExperienceInMonths(employeeMaster.getExperienceInMonths());
        employeeDetail.setLastCompanyName(employeeMaster.getLastCompanyName());
        employeeDetail.setLastWorkingDate(utilDate);
        employeeDetail.setDesignation(employeeMaster.getDesignation());
        employeeDetail.setSalary(employeeMaster.getSalary());
        employeeDetail.setExpectedSalary(employeeMaster.getExpectedSalary());
        employeeDetail.setCreatedBy(1L);
        employeeDetail.setCreatedOn(currentDate);
         this.employeeDetailRepository.save(employeeDetail);

        EmployeeMedicalDetail employeeMedicalDetail = new EmployeeMedicalDetail();
        var lastEmployeeMedicalDetailRecord = this.employeeMedicalDetailRepository.getLastEmployeeMedicalDetailRecord();
        if (lastEmployeeMedicalDetailRecord == null){
            employeeMedicalDetail.setEmployeeMedicalDetailId(1L);
        }else {
            employeeMedicalDetail.setEmployeeMedicalDetailId(lastEmployeeMedicalDetailRecord.getEmployeeMedicalDetailId()+1);
        }
        employeeMedicalDetail.setEmployeeId(employee.getEmployeeId());
        employeeMedicalDetail.setMedicalConsultancyId(employeeMaster.getMedicalConsultancyId());
        employeeMedicalDetail.setConsultedBy(employeeMaster.getConsultedBy());
        employeeMedicalDetail.setConsultedOn(utilDate);
        employeeMedicalDetail.setReferenceId(employeeMaster.getReferenceId());
        employeeMedicalDetail.setReportId(employeeMaster.getReportId());
        employeeMedicalDetail.setReportPath(employeeMaster.getReportPath());
        employeeMedicalDetail.setCreatedBy(1L);
        employeeMedicalDetail.setCreatedOn(currentDate);
        this.employeeMedicalDetailRepository.save(employeeMedicalDetail);

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
        existingEmployee.setPinCode(employeeMaster.getPinCode());
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
        Optional<EmployeeDetail> employeeDetailResult = Optional.ofNullable(this.employeeDetailRepository.getEmployeeDetailByEmployeeId(employeeId));
        if (employeeDetailResult.isEmpty())
            throw new Exception("EmployeeDetail not found");
        employeeDetail = employeeDetailResult.get();
        employeeDetail.setPan(employeeMaster.getPan());
        employeeDetail.setAadhar(employeeMaster.getAadhar());
        employeeDetail.setPassportNumber(employeeMaster.getPassportNumber());
        employeeDetail.setBankName(employeeMaster.getBankName());
        employeeDetail.setAccountNo(employeeMaster.getAccountNo());
        employeeDetail.setBranch(employeeMaster.getBranch());
        employeeDetail.setBranch(employeeMaster.getIfscCode());
        employeeDetail.setJobTypeId(employeeMaster.getJobTypeId());
        employeeDetail.setExperienceInMonths(employeeMaster.getExperienceInMonths());
        employeeDetail.setLastCompanyName(employeeMaster.getLastCompanyName());
        employeeDetail.setLastWorkingDate(utilDate);
        employeeDetail.setDesignation(employeeMaster.getDesignation());
        employeeDetail.setSalary(employeeMaster.getSalary());
        employeeDetail.setExpectedSalary(employeeMaster.getExpectedSalary());
        employeeDetail.setExpectedDesignation(employeeMaster.getExpectedDesignation());
        employeeDetail.setUpdatedBy(1L);
        employeeDetail.setUpdatedOn(currentDate);
        EmployeeDetail employeeDetailData = this.employeeDetailRepository.save(employeeDetail);
        if (employeeDetail == null){
            throw new Exception("fail to update EmployeeDetail");
        }
        EmployeeMedicalDetail employeeMedicalDetail;
        EmployeeMedicalDetail employeeMedicalDetailResult = this.employeeMedicalDetailRepository.getEmployeeMedicalDetailByEmployeeId(employeeId);
        if (employeeMedicalDetailResult == null){
            throw new Exception("EmployeeMedicalDetail not found");
        }
        employeeMedicalDetailResult.setMedicalConsultancyId(employeeMaster.getMedicalConsultancyId());
        employeeMedicalDetailResult.setConsultedBy(employeeMaster.getConsultedBy());
        employeeMedicalDetailResult.setConsultedOn(utilDate);
        employeeMedicalDetailResult.setReferenceId(employeeMaster.getReferenceId());
        employeeMedicalDetailResult.setReportId(employeeMaster.getReportId());
        employeeMedicalDetailResult.setReportPath(employeeMaster.getReportPath());
        employeeMedicalDetailResult.setUpdatedBy(1L);
        employeeMedicalDetailResult.setUpdatedOn(currentDate);
        EmployeeMedicalDetail employeeMedicalDetailData = this.employeeMedicalDetailRepository.save(employeeMedicalDetailResult);
        return "Employee has been updated";
    }


    public ArrayList<Employee> getAllEmployeeService() {
        List<Employee> result = this.employeeRepository.findAll();
        return (ArrayList<Employee>) result;
    }

    public EmployeeMaster getEmployeeByEmployeeIdService(long employeeId) {
        List<DbParameters> dbParameters = new ArrayList<>();
        dbParameters.add(new DbParameters("_EmployeeId", employeeId, Types.BIGINT));
        var dataSet = lowLevelExecution.executeProcedure("sp_Employee_getByEmployeeId", dbParameters);
        var data =  objectMapper.convertValue(dataSet.get("#result-set-1"), new TypeReference<List<EmployeeMaster>>() {
        });
        if (data != null)
            return data.get(0);
        else
            return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public String deleteEmployeeByEmployeeIdService(long employeeId) throws Exception {
        Optional<Employee> result = this.employeeRepository.findById(employeeId);
        if (result.isEmpty()) {
            throw new Exception("employee record not found");
        }
        Employee existingEmployee = result.get();
        existingEmployee.setActive(false);
        this.employeeRepository.save(existingEmployee);

        Login loginResult = this.loginRepository.getLoginByEmployeeId(employeeId);
        if (loginResult == null){
            throw new Exception("login record not found");
        }
        loginResult.setActive (false);
        this.loginRepository.save(loginResult);
        return "Employee data is De-active";
    }
}
