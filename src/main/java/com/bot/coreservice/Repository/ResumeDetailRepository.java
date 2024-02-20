package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.ResumeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResumeDetailRepository extends JpaRepository<ResumeDetail, Long> {



}
