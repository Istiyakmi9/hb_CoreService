package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select e.* from user e order by e.UserId desc limit 1", nativeQuery = true)
    User getLastUserId();
}
