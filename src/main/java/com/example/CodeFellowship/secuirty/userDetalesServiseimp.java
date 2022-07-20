package com.example.CodeFellowship.secuirty;

import com.example.CodeFellowship.Rebository.ApplicationUserRebositry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service // to create service
public class userDetalesServiseimp implements UserDetailsService { // this interface use to know the login page whatch reo use
    @Autowired // we use  @Autowired use to access to the privet methods and properties of the instanse
    ApplicationUserRebositry AppUserRebo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {// you must added
        return AppUserRebo.findByusername(username);
    }
}
