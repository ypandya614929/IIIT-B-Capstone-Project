# **JWT Token Security**
Default users are created during application startup

Normal User (ROLE_USER)  
username: normal-user@abc.com  
password: Test@123

Admin User (ROLE_ADMIN)  
username: admin-user@abc.com  
password: Admin@123

Prerequisites/Assumption: Connecting to aws rds mysql db.

URLs  
1. Generate Token:  
   url - http://localhost:8088/foodDelivery/security/generate-token  
Ex: For normal user  
Request: {  
"username":"normal-user@abc.com",  
"password":"Test@123"  
}
Response: {  
eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEZW1vIEFwcCIsInN1YiI6Im5vcm1hbC11c2VyQGFiYy5jb20iLCJpYXQiOjE2MTY5MjM3OTYsImV4cCI6MTYxNzMzMzg2MSwiUm9sZXMiOlsiUk9MRV9VU0VSIl19.8UEE_r2VYIw-SutacJmSx5ndDbiFSqgyoApVc5BnVKPGerB0QB8fNXV702TJoqFATUaUU5n9WazeqopNzOHKkg  
}  
   
2.  Get All users  
url - http://localhost:8088/foodDelivery/security/user/all  
    Header - Authorization: eyJhbGciOiJIUzUxMiJ9.eyJpc3MiOiJEZW1vIEFwcCIsInN1YiI6Im5vcm1hbC11c2VyQGFiYy5jb20iLCJpYXQiOjE2MTY5MjM3OTYsImV4cCI6MTYxNzMzMzg2MSwiUm9sZXMiOlsiUk9MRV9VU0VSIl19.8UEE_r2VYIw-SutacJmSx5ndDbiFSqgyoApVc5BnVKPGerB0QB8fNXV702TJoqFATUaUU5n9WazeqopNzOHKkg  
    Response: {  
    Access is Denied  
    }  
    Note: Since the token was created with normal user, the access is denied. To access this url, you need to generate token with admin user and use it's token as Authorization header.
    
