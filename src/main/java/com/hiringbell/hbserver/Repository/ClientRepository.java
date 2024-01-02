package com.hiringbell.hbserver.Repository;

import com.hiringbell.hbserver.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
    @Query(nativeQuery = true, value = "select u.* from client u order by u.Id desc limit 1")
    Client getLastClient();
}
