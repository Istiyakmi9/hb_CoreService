package com.hiringbell.hbserver.Repository;

import com.hiringbell.hbserver.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {

//    @Query(value = "select ed.* from employee_detail ed order by ed.EmployeeId desc limit 1", nativeQuery = true)
//    EmployeeDetail getLastEmployeeDetailRecord();
    @Query(nativeQuery = true, value = "select ed.* from employee_detail ed where ed.EmployeeId = :employeeId")
    EmployeeDetail getEmployeeDetailByEmployeeId(@Param("employeeId") long employeeId);

}
