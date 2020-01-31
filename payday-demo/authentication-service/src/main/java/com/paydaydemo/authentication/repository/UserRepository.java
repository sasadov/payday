package com.paydaydemo.authentication.repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.paydaydemo.authentication.model.User;


@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    List<User> findByEmail(String email);

    List<User> findByDate(Date date);

	// custom query example and return a stream
    @Query("select u from User u where u.email = :email")
    Stream<User> findByEmailReturnStream(@Param("email") String email);
    
    Optional<User> findUserByUserName(String userName);
}
