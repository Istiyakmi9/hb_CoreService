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
}
