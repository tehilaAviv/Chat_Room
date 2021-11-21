package com.example.ex4.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;

/**
 * this is a  bean class instantiated in session
 */
@Component
public class Login implements Serializable {
    private boolean is_login;
    private String name;

    /**
     * constructor to update the is_login to be false
     */
    public Login(){
        this.is_login = false;
    }

    /**
     * function that sets is_login
     * @param login The updated login
     */
    public void setLogin(boolean login){
        is_login = login;
    }

    /**
     *This function is responsible for returning whether the user is logged in or not
     * @return boolean is_login
     */
    public boolean getLogin(){
        return is_login;
    }

    /**
     *  function that sets the name of the user
     * @param name_ updated the user name
     */
    public void setName(String name_){
        name = name_;
    }

    /**
     * This function is responsible for returning the user name
     * @return user name
     */
    public String getName(){
        return name;
    }

    /**
     *BEAN using ctor - session scope
     * @return Login
     */
    @Bean
    @SessionScope

   public Login loggedIn(){
        return new Login();
    }
}
