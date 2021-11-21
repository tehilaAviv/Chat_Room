package com.example.ex4.filters;

import com.example.ex4.bean.Login;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This department is responsible for redirecting the user to a login page in case he is not logged in and trying to access any other page
 */
public class Filter implements HandlerInterceptor {
        private Login login;

    /**
     * ctor of the Login Session
     * @param login the updated login, A variable that indicates whether a session exists or no
     */
    public Filter(Login login){
                this.login = login;
        }
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
                throws Exception {
               if(!(login.getLogin())){ //If the user is not logged in and trying to access another page we will redirect him to the login page
                 response.sendRedirect("/login");
                 return false;
               }
                return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                               Object handler, ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                    Object handler, Exception ex) throws Exception {
              }


}
