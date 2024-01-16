package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    @Query(nativeQuery = true, value = "select ed.* from user_detail ed where ed.userId = :userId")
    UserDetail getUserDetailByUserId(@Param("userId") long userId);
}
