package com.bot.coreservice.Repository;

import com.bot.coreservice.model.ClientType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientTypeRepository extends JpaRepository<ClientType, Integer> {
}
