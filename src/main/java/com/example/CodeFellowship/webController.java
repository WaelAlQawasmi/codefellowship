package com.example.CodeFellowship;

import com.example.CodeFellowship.Rebository.ApplicationUserRebositry;
import com.example.CodeFellowship.table.ApplicationUser;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class webController {

    @Autowired
    PasswordEncoder encoder;

@Autowired
    ApplicationUserRebositry ApplicationUserRebositry;


        @GetMapping("/login")
        public String getLoginPage(){
            return "login";
        }

    @GetMapping("/")
    public String index(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("First", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getFirstName());
        model.addAttribute("Last", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getLastName());
        model.addAttribute("bio", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getBio());
        model.addAttribute("date", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getDateOfBirth());

        return "index";
    }


    @GetMapping("/signup")
    public String signupfoem(){
        return "signup";
    }


    @PostMapping ("/signup")

    public String signup(String username, String password,String firstName,String lastName,String dateOfBirth,String bio){
        ApplicationUser newUser= new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
        ApplicationUserRebositry.save(newUser);
            return "login";
    }


}
