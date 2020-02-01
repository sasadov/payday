package com.paydaydemo.transactions.repository;

import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paydaydemo.transactions.model.Transaction;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.customerId = :customerId")
    Stream<Transaction> findByCustomerIdReturnStream(@Param("customerId") Long customerId);
}
