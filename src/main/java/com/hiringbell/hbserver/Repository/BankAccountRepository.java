package com.hiringbell.hbserver.Repository;

import com.hiringbell.hbserver.model.BankAccount;
import com.hiringbell.hbserver.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
    @Query(nativeQuery = true, value = "select u.* from bank_accounts u order by u.BankAccountId desc limit 1")
    BankAccount getLastBankAccount();
}
