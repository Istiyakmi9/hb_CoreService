package com.hiringbell.hbserver.Repository;
import com.hiringbell.hbserver.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {
}
