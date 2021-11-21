package com.example.ex4;

import com.example.ex4.bean.Login;
import com.example.ex4.filters.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/*
  this is a class for configuring SringMVC
  here we register our interceptor class and the session listener
  WebMvcConfigurer allows configuring all of the MVC:
 */
@Configuration
public class MyConfig implements WebMvcConfigurer {
    @Resource(name ="loggedIn")
    private Login login;
    /**
     * move the user to a login page in case the user wants to move to another page that is not a login page and he has not logged in before.
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry){
        List<String> Urls =new ArrayList<String>();
        Urls.add("/login");
        registry.addInterceptor(new Filter(this.login)).excludePathPatterns(Urls);
    }
}