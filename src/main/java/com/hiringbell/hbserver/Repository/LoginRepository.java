package com.hiringbell.hbserver.Repository;

import com.hiringbell.hbserver.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query(value = "select l from Login l where l.mobile = :mobile")
    Login getLoginByMobile(@Param("mobile") String mobile);
    @Query(value = "select l from Login l where l.email = :email")
    Login getLoginByEmail(@Param("email") String email);

    @Query(value = "select l.* from login l order by l.EmployeeId desc limit 1", nativeQuery = true)
    Login getLastLoginRecord();

//    @Query(value = "select l from Login l where l.employeeId = :employeeId")
//    Login getLoginByEmployeeId(@Param("employeeId") Long employeeId);
//    @Query(nativeQuery = true, value = " select l.* from login l where l.Email = :email or l.Mobile = :mobile ")
//    Login getLoginByEmailOrMobile(@Param("mobile") String mobile, @Param("email") String email );

    @Query(nativeQuery = true, value = "select l.* from login l where l.EmployeeId = :employeeId")
    Login getLoginByEmployeeId(@Param("employeeId") long employeeId);
}
