package com.ndexsystems.phoenix.repositories;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.ndexsystems.phoenix.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, BigDecimal> {

    @Procedure(procedureName = "sps_User_Table_LoginId")
    User userTableLoginId(String login);
}