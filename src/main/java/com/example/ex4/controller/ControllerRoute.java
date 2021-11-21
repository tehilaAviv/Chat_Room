package com.example.ex4.controller;

import com.example.ex4.bean.Login;
import com.example.ex4.repo.Users;
import com.example.ex4.repo.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * This department is responsible for dealing with the user and handling login and logout.
 */
@Controller
public class ControllerRoute {

    @Resource(name ="loggedIn")
    private Login login;

    @Autowired
    private UsersRepository repository_;

    /**
     * inject via its type the User repo bean - a singleton
     * @return repository_
     */
    @Autowired
    private UsersRepository getRepo_() {
        return repository_;
    }

    /**
     *When you press the ENTER CHAT ROOM  button you come to this router and it is responsible for handling in front of the user
     * @param username is the name of the user entered
     * @param model for displaying message in the screen
     * @return page login or chatRoom
     * */
    @PostMapping("/login")
    public String loginHandle(@RequestParam(name = "yourname", required = false, defaultValue = "nothing") String username, Model model) {
        String name = username.trim();
        //Check if the user does not have a session
        if(!(login.getLogin())) {
            //If the username is entered correctly, we will move the user to the message page, otherwise it will remain on the current page and an error message will be displayed.
            if (name.equals("nothing") || name.length() == 0) {
                model.addAttribute("error", "user name is missing");
                return "login";
            }
            login.setName(name);
            //A query that retrieves from the database the names of the connected users with the same name of the current user
            Users u1 =  getRepo_().findByUserNameAndConnected(login.getName(),true);
            //If there are users with the same name logged in, we will display an error message
            if(!(u1==null)) {
                model.addAttribute("error", "already exist user in this name");
                return "login";
            }
            login.setLogin(true);
            Users user_ = new Users(name,true);
            getRepo_().save(user_);//If all goes well we will keep the new user in the database
            model.addAttribute("username", name);
            return "chatRoom";
        }
        return "chatRoom";
    }

    /**
     *This GET is intended for the user to route the pages himself by writing manually
     * @param username is the name of the user entered
     * @param model for displaying username in the screen
     * @return page login or chatRoom
     */
    @GetMapping("/login")
    public String loginGet(@RequestParam(name = "yourname", required = false, defaultValue = "nothing") String username, Model model){
        if(login.getLogin()){
            model.addAttribute("username",  login.getName());
            return "chatRoom";
        }
        return "login";
    }

    /**
     * Here the treatment is done in case of pressing a logout button
     * @param request is HttpServletRequest
     * @return page login
     */
    @GetMapping("/logout")
    public String logoutHandle(HttpServletRequest request){

        if(login.getLogin()){ //If the user is logged in we will update the false session
            login.setLogin(false);
            //Update the Connected field of the current user in the database to be FALSE
            Users u = getRepo_().findByUserNameAndConnected(login.getName(),true);
            u.setConnected(false);
            getRepo_().save(u);
            //delete the session of the current user
            HttpSession session = request.getSession(false);
            if (session != null)
                session.invalidate();
        }
        return "login";
    }

    /**
     *Handling the user login for the first time
     * @param model for displaying username in the screen
     * @return page login or chatRoom
     */
    @GetMapping("/")
    public String landingPage(Model model) {
        if(login.getLogin()) {
            model.addAttribute("username",  login.getName());
            return "chatRoom";
        }
        return "login";
    }

}
