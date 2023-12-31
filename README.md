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
User Entity
|HTTP METHOD|PATH|PURPOSE|REQUEST BODY|
|----|----|----|----|
|POST|/api/users/|Add a new user|{<br>"name":"sanjana",<br>"email":"sanj@gmail.com",<br>"password":"s123907",<br>"about":"I am a coder"<br>}|
|GET|/api/users/|Returns all the users in database|None|
|GET|/api/users/{userid}|Return details of the user with that userid|None|
|PUT|/api/users/{userid}|Updates the details of the user with that userid|{</br>"name":"sanjana</br>"email":"sanj@gmail.com"</br>"password":"s123907",</br>"about":"I am a debugger"</br>}|
|DELETE|/api/users/{userid}|Deletes details of the user with that userid|None|

Categories Entity
|HTTP METHOD|PATH|PURPOSE|REQUEST BODY|
|----|----|----|----|
|POST|/api/categories/|Add a new category|{<br>"categoryTitle":"Political News",<br>"categoryDescription": "This category contains all the details about indian politics"<br>}|
|GET|/api/categories/|Returns all the categories in database|None|
|GET|/api/categories/{categoryid}|Return details of the category with that categoryid|None|
|PUT|/api/categories/{categoryid}|Updates the details of the category with that categoryid|{<br>"categoryTitle":"Sports News",<br>"categoryDescription": "This category contains all the details about indian sports"},<br>|
|DELETE|/api/categories/{categoryid}|Deletes details of the category with that categoryid|None|

Post Entity
|HTTP METHOD|PATH|PURPOSE|REQUEST BODY|
|----|----|----|----|
|POST|api/user/{userid}/category/{categoryid}/posts|Add a new post in the category<br> with categoryid by user with userid|{<br>"title" : "Movies",<br>"content" : "Oppenheimer Barbie are releasing on the same day"<br>}|
|GET|api/category/{categoryid}/posts|Returns all the posts in the category|None|
|GET|/api/users/{userid}/posts|Return all the posts done by the user|None|
|GET|/api/posts|Return all the posts in the database|None|
|GET|/api/posts/{postid}|Return details of the post with id as postid|None|
|GET|/api/posts/search/{titlekey}|Return all the posts with titles containing titlekey|None|
|PUT|/api/posts/{postid}|Updates the details of the post with that postid|{<br>"title" : "Movies",<br>"content" : "Oppenheimer is directed by Christopher Nolan"<br>}|
|DELETE|/api/posts/{postid}|Deletes details of the post with that postid|None|

Comment Entity
|HTTP METHOD|PATH|PURPOSE|REQUEST BODY|
|----|----|----|----|
|POST|/api/post/{postid}/comments|Add a comment to the post with postid|{<br>"content":"Oppenheimer was a great movie",<br>"Date":"07-12-2023"<br>}|
|GET|/api/comments/|Returns all the comments in database|None|
|GET|/api/comments/{commentid}|Return details of the comment with that commentid|None|
|PUT|/api/comments/{commentid}|Updates the details of the comment with that comment|{<br>"content":"Oppenheimer and Barbie were great movies",<br>"Date":"07-12-2023"<br>}|
|DELETE|/api/comments/{commentid}|Deletes details of the comment with that commentid|None|
