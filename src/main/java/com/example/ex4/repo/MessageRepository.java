package com.example.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * default class of this Bean is singleton, add here the queries you may need
 */
public interface MessageRepository extends JpaRepository<Message, Long> {
    /**
     * This function returns the last five messages by ID
     * @return List Message
     */
    List<Message> findFirst5ByOrderByIdDesc();

    /**
     * Query that retrieves all messages that belong to the same username entered by name search
     * @param userName the username that entered by name search
     * @return List Message
     */
    List<Message> findAllByUserName(String userName);

    /**
     * A query that returns the list of messages that match what the user entered in the search
     * @param msg messages that match what the user entered in the search
     * @return List Message
     */
    List<Message> findByMsgContaining(String msg);
}
