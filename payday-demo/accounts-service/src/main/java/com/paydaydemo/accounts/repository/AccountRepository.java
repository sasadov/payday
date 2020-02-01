package com.paydaydemo.accounts.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paydaydemo.accounts.model.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    @Query("select a from Account a where a.CustomerId = :customerId")
    Stream<Account> findByCustomerIdReturnStream(@Param("customerId") Long customerId);
}
