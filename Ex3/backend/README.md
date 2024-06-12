# WebNote - A simple web-based note-taking application

Use http://localhost:8080/swagger-ui.html to access the API documentation

![img_2.png](img_2.png)

All routes of client-controller are unprotected to allow the user to create an account and login. The routes of 
note-controller are protected and require the user to be authenticated using auth-controller.

The user authentication will generate a simple token that needs to be used in the header Authentication of the requests 
to all routes of note-controller

1 - Token generation
![img_3.png](img_3.png)

2 - Token usage
![img_4.png](img_4.png)

3 - Note creation
![img_5.png](img_5.png)

4 - Note update
![img_6.png](img_6.png)

5 - Note deletion
![img_7.png](img_7.png)

6 - Note listing
![img_8.png](img_8.png)

Data Bank Modeling

![img.png](img.png)


Use http://localhost:8080/h2-console to access the database

![img_1.png](img_1.png)