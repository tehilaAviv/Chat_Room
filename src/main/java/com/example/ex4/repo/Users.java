package com.example.ex4.repo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

@Entity
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    private String userName;

    private Boolean connected;

    /**
     * This function updates the connection field
     * @param connected A field that indicates whether the user is logged in or not
     */
    public void setConnected(Boolean connected) {
        this.connected = connected;
    }

    /**
     * return the connection field
     * @return Boolean connected
     */
    public Boolean getConnected() {
        return connected;
    }

    public Users(){

    }

    /**
     * return the userName
     * @return String userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * A c-tor that gets the username and field that says whether it is missing or not
     * @param name Updates username
     * @param connected_ Updates connected
     */
    public Users(String name, Boolean connected_){
        this.userName = name;
        this.connected = connected_;
    }
}
