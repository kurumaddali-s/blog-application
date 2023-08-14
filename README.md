# blog-application
Spring boot backend application for a blogging app
# CS 5610 Semester Project on SpringBoot 

This repo represents the coursework for CS 5610, the Fall 2022 Edition!

**Names:** Sri Sai Sushmitha Kurumaddali 
                   
**Email:**   kurumaddali.s@northeastern.edu

**Preferred Name:** Sushmitha


### About/Overview
Developed a SpringBoot backend application for a Blogging site, used MySQL as database. There were total 4 entities in the project - User, Category, Comment, Post.

### List of Features
- New users prompted to signup, entered username, password if valid, user account is created
- Users can be of type admin or just normal user
- Users can create new posts, modify existing ones.
- One post can exist in more than one category, one category can contain one or more posts
- Comments can be added to posts
- Used JWT to authenticate users and secure the APIs
- Did role based access to APIs, admins have exclusive privileges to delete update, add new categories
- The APIs take 300 - 400 milli seconds to respond
  
### How to Run
Download the jar file by running this command "java -jar blogging-app-apis-0.0.1-SNAPSHOT.jar" by navigating to the target directory of the github repo, the application will start running in port number 9090

### PostMan Collection
The Postman collections consists of sample API responses and calls made in this blogging application
