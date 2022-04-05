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
> > or from all users button in profile

you can basic user info by this rout

## Following
> you can access  without dash at rout
> /users/{user_id}
> or from all userits button in profile

you can follow the user that you haven't followed yet anfer follow the user , you can see all posts in feed

## feed
> you can access  without dash at rout
> /feed
> or from feed button in profile

you can see your posts and the users that followed posts


# Set up this app
- create DB in postgres called "userdata"
- chang the application.properties with your username and password in postgres
- start postgres by this command
> pg_ctl -D /home/linuxbrew/.linuxbrew/var/postgres start