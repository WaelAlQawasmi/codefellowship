package com.example.CodeFellowship;

import com.example.CodeFellowship.Rebository.ApplicationUserRebositry;
import com.example.CodeFellowship.table.ApplicationUser;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;

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

    @GetMapping("/dash")
    public String dash(Model model){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("First", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getFirstName());
        model.addAttribute("Last", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getLastName());
        model.addAttribute("bio", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getBio());
        model.addAttribute("date", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getDateOfBirth());

        return "dash";
    }


    @GetMapping("/signup")
    public String signupfoem(){
        return "signup";
    }

    @GetMapping("/")
    public String index(Model model){
       try {
           UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
           model.addAttribute("username", userDetails.getUsername());
           return "index";   }
       catch (Exception E){
           return "index";
       }

       //

    }

    @PostMapping ("/signup")

    public RedirectView signup(String username, String password, String firstName, String lastName, String dateOfBirth, String bio){
        ApplicationUser newUser= new ApplicationUser(username,encoder.encode(password),firstName,lastName,dateOfBirth,bio);
        ApplicationUserRebositry.save(newUser);



        Authentication authentication = new UsernamePasswordAuthenticationToken(newUser, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

            return new RedirectView("/dash");
    }


}
