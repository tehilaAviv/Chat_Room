package com.example.ex4.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

/**
 * This class defines and manages the message table in the database
 */
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    private String userName;

    @NotEmpty(message = "Message is mandatory")
    private String msg;

    public Message(){

    }

    /**
     * A c-tor who gets the username and message
     * @param name conntent the userName
     * @param msg conntent the message
     */
    public Message(String name,String msg){
        this.userName = name;
        this.msg = msg;
    }

    /**
     * A function that returns the message
     * @return String msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * A function that returns the userName
     * @return String userName
     */
    public String getUserName() {
        return userName;
    }


}