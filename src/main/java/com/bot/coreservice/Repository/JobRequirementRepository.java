package com.bot.coreservice.Repository;

import com.bot.coreservice.entity.JobRequirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobRequirementRepository extends JpaRepository<JobRequirement, Long> {

    @Query(value = "select jr.* from job_requirement jr order by jr.JobRequirementId desc limit 1", nativeQuery = true)
    JobRequirement getLastJobRequirementRecord();

}
