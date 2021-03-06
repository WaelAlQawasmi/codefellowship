package com.example.CodeFellowship;

import com.example.CodeFellowship.Rebository.ApplicationUserRebositry;
import com.example.CodeFellowship.Rebository.postRebository;
import com.example.CodeFellowship.table.ApplicationUser;
import com.example.CodeFellowship.table.post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class webController implements ErrorController {// non-whitelabel error handling page


    @RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }
    @Autowired
    PasswordEncoder encoder;




    public webController(com.example.CodeFellowship.Rebository.postRebository postRebository, com.example.CodeFellowship.Rebository.ApplicationUserRebositry applicationUserRebositry) {
        this.postRebository = postRebository;
        ApplicationUserRebositry = applicationUserRebositry;
    }
@Autowired
  postRebository postRebository;

 ApplicationUserRebositry ApplicationUserRebositry;

    @GetMapping("/login")
        public String getLoginPage(){

            return "login";
        }

    @GetMapping("/myprofile")
    public String dash(Model model){
        model.addAttribute("IsSame", true);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("username", userDetails.getUsername());
        model.addAttribute("First", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getFirstName());
        model.addAttribute("Last", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getLastName());
        model.addAttribute("bio", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getBio());
        model.addAttribute("date", ApplicationUserRebositry.findByusername(userDetails.getUsername()).getDateOfBirth());
      Long id =ApplicationUserRebositry.findByusername(userDetails.getUsername()).getId();
List<post> posta=  postRebository.findByapplicationUserId(id);
  model.addAttribute("allpost", posta );



        return "dash";
    }



    @GetMapping("/users/{id}")
    public String users(Model model , @PathVariable Long id){
            System.out.println(id);
        UserDetails CurrentUserDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
String CurrentUserName=CurrentUserDetails.getUsername();
        ApplicationUser currentUser= ApplicationUserRebositry.findByusername(CurrentUserName);

        ApplicationUser user = ApplicationUserRebositry.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));



        model.addAttribute("username", user.getUsername());
        model.addAttribute("First", user.getFirstName());
        model.addAttribute("Last", user.getLastName());
        model.addAttribute("bio", user.getBio());
        model.addAttribute("date", user.getDateOfBirth());
        System.out.println(user.getUsername()+".................3");
if(currentUser.getId()==id){
    model.addAttribute("IsSame", true);
}
else {
    model.addAttribute("IsSame", false);
}
if(currentUser.getFollowing().contains(user)){
    model.addAttribute("isFollowed","true");
    System.out.println(true);
}
else


      {
            model.addAttribute("isFollowed",false);
            System.out.println(false);

        }

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

            return new RedirectView("/myprofile");
    }




    @PostMapping ("/addpost/{username}")

    public RedirectView addpost(String text,@PathVariable String username){

        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        System.out.println(timeStamp);
      ApplicationUser user=  ApplicationUserRebositry.findByusername(username);
        post newPost= new post(text,timeStamp);
        newPost.setApplicationUser(user);
        postRebository.save(newPost);


        return new RedirectView("/myprofile");
    }

    @GetMapping("/allusers")

    public String allusers(Model users){


        List<ApplicationUser> user=  ApplicationUserRebositry.findAll();

   users.addAttribute("users",user);


        return ("users");
    }


    @PostMapping("users/follow/{id}")

    public RedirectView following(Model folloeingUser,@PathVariable("id") Long id){
       // System.out.println("id-----------"+id);
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      String CurrentUserName=userDetails.getUsername();
        ApplicationUser currentUser= ApplicationUserRebositry.findByusername(CurrentUserName);
        ApplicationUser usertofollow = ApplicationUserRebositry.findById(id).orElseThrow();


        currentUser.getFollowing().add(usertofollow);

        usertofollow.getFollowers().add(currentUser);
        ApplicationUserRebositry.save(usertofollow);
        ApplicationUserRebositry.save(currentUser);
        folloeingUser.addAttribute("users",currentUser.getFollowing());
        return new RedirectView ("/allusers");
    }




    @GetMapping("/feed")
    public String feed(Model model){

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        Long id =ApplicationUserRebositry.findByusername(userDetails.getUsername()).getId();
        List<post> posta=  postRebository.findByapplicationUserId(id);
      List<ApplicationUser> following=  ApplicationUserRebositry.findByusername(userDetails.getUsername()).getFollowing();
      if(following.size()>0) {
        for (ApplicationUser followingUser :following ) {
posta.addAll( postRebository.findByapplicationUserId(followingUser.getId()))
       ;

        }

      }
        model.addAttribute("allpost", posta );



        return "feed";
    }

}
