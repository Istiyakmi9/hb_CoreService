package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.UserInterests;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInterestsRepository extends JpaRepository<UserInterests, Long> {

}
