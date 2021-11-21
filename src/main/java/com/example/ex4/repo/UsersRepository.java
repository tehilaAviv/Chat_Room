package com.example.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * default class of this Bean is singleton, add here the queries you may need
 */
public interface UsersRepository extends JpaRepository<Users, Long> {
    /**
     *A query that returns the UserName
     * @param name containing the UserName
     * @return Users
     */
    Users findByUserName(String name);

    /**
     *A query that retrieves from the database the names of the connected users with the same name of the current user
     * @param name containing the UserName
     * @param isConnected containing if Connected or not
     * @return Users
     */
     Users findByUserNameAndConnected(String name,Boolean isConnected);

    /**
     * A query that returns all connected users from the user base
     * @param isConnected containing if Connected or not
     * @return  List Users
     */
    List<Users> findByConnected(Boolean isConnected);

    /**
     * This query returns all users in the table
     * @return List Users
     */
    @Override
    List<Users> findAll();


}

