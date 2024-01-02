package com.hiringbell.hbserver.Repository;

import com.hiringbell.hbserver.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {
}
