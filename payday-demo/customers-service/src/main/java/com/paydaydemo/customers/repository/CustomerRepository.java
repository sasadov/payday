package com.paydaydemo.customers.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.paydaydemo.customers.model.Customer;


@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("select c from Customer c where c.userId = :userId")
    Optional<Customer> findByUserId(@Param("userId") Long userId);
}
