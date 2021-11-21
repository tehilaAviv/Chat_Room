package com.example.ex4.controller;

import com.example.ex4.bean.Login;
import com.example.ex4.repo.Message;
import com.example.ex4.repo.MessageRepository;
import com.example.ex4.repo.Users;
import com.example.ex4.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * This department is responsible for managing the database, retrieving data from the database and handling queries
 */
@Controller
public class DbController {

    @Autowired
    private MessageRepository repository;

    /**
     * inject via its type the Message repo bean - a singleton
     * @return repository
     */
    private MessageRepository getRepo() {
        return repository;
    }


    @Autowired
    private UsersRepository repository_;

    /**
     * inject via its type the User repo bean - a singleton
     * @return repository_
     */
    private UsersRepository getRepo_() {
        return repository_;
    }

    @Resource(name ="loggedIn")
    private Login login;

    /**
     * When sending a message come to this router and he is responsible for handling it
     * @param msg is the name of the msg entered
     * @param model for displaying message in the screen
     * @return page login or chatRoom
     */
    @PostMapping("/addMessage")
    public String addMsg(@RequestParam(name = "message", required = false, defaultValue = "nothing") String msg, Model model) {
        if(login.getLogin()) {
            String m = msg.trim();
            //If the message is invalid, we will display an error message to the user
            if (m.equals("nothing") || m.length() == 0) {
                model.addAttribute("error", "message is missing");
                model.addAttribute("username", login.getName());
                return "chatRoom";
            }
            // otherwise we will enter the message in the database (message table)
            model.addAttribute("username", login.getName());
            Message message = new Message(login.getName(), m);
            getRepo().save(message);
            return "chatRoom";
        }
        return "login";
    }

    /**
     *This GET is intended for the user to route the pages himself by writing manually
     * @param model for displaying username in the screen
     * @return page login or chatRoom
     */
    @GetMapping("/addMessage")
    public String addMessage__(Model model) {
        if(login.getLogin()){
            model.addAttribute("username",  login.getName());
            return "chatRoom";
        }
        return "login";
    }
    /**
     *This router is responsible for displaying the last 5 messages received and will be updated every 10 seconds
     * @return List Message of messages
     */
    @GetMapping("/getMessages")
    @ResponseBody
    public synchronized List<Message> getMessage()
    {
        return getRepo().findFirst5ByOrderByIdDesc();//A query that retrieves from the database the last 5 messages received
    }

    /**
     *This router searches the message in the database for the message content entered by the user
     * @param content  the message content entered by the user
     * @return List Message of messages
     */
    @GetMapping("/searchContentJson/{content}")
    @ResponseBody
    public synchronized List<Message> searchContentJson(@PathVariable("content") String content) {
        String content_ = content.trim();
        List<Message> list= getRepo().findByMsgContaining(content_);//A query that returns the list of messages that match what the user entered in the search
        if (list.size()==0)
            return null;

        return getRepo().findByMsgContaining(content_);
    }

    /**
     *This router searches for the message in the database by the user name entered by the user
     * @param name_ the name entered in the search by the user
     * @return List Message of messages
     */
    @GetMapping("/searchUsersJson/{userName}")
    @ResponseBody
    public  synchronized List<Message> searchUserJson(@PathVariable("userName") String name_ ) {

        String name = name_.trim();
        List<Message> list= getRepo().findAllByUserName(name_);//Query that retrieves from the message table all messages that belong to the same username entered by name search
        if (list.size()==0)
            return null;
        return getRepo().findAllByUserName(name_);
    }

    /**
     * This router is responsible for handling the display of all connected users
     * @return List Users Connect Users
     */
    @GetMapping("/getUsersConnect")
    @ResponseBody
    public synchronized List<Users> getUsersConnect()
    {
        return getRepo_().findByConnected(true);//A query that returns all connected users from the user base
    }

}
