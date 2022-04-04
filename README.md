# codefellowship
code fellowship to hold my work in Spring Security 
Using the Spring Initializer to set up an app with
dependencies on Web, Thymeleaf, JPA, Postgres,
and Security and optionally DevTools for auto refresh of app on building 

## About the app

The site have:
- ### index page
> you can access it without login at rout 
> /
>
This page has links to login and signup if the user is NOT logged in.
This page has a link to logout if the user IS logged in.

## signup page
> you can access it without signup at rout
> /signup

in this page  you can register in site by enter your information
, username  and password , the password encoded by 
passwordEncoder.encode(password) before saving the password into the new user.
 and after signup you automatic login

## login page
> you can access it without login at rout
> /login

in this page you can log in by  correctly configure Spring Security
and you can't pass to any page not authorize without login

## dash page
> you can access it without dash at rout
> /myprofile

The site   display your information in this page after login.


## add post
> you can access it without dash at rout
> /myprofile

you can add posts and you can see your posts

## search in user
> you can access it without dash at rout
> /users/{user_id}

you can basic user info by this rout
