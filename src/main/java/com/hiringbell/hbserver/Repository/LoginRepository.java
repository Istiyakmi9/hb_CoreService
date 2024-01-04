package com.hiringbell.hbserver.Repository;

import com.hiringbell.hbserver.entity.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository extends JpaRepository<Login, Long> {
    @Query(nativeQuery = true, value = " select l.* from login l where l.Email = :email or l.Mobile = :mobile ")
    Login getLoginByEmailOrMobile(@Param("mobile") String mobile, @Param("email") String email );
}
