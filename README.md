# **Guest Book Service**

Guestbook Service is a service that allows guests to leave some comments.

# **Prerequisites**

JDK 11,
Gradle 6.8.3,
IntelliJ IDEA

Plugins: Lombok

# **How to setup and run guestbook service in local:**

1. Clone the project repository from github : https://github.com/404-bug-not-found/guestbook-service
2. Setup project in IntelliJ IDEA
3. Fetch and pull latest code from branch main.
4. Run the integration test - GuestBookServiceIT
5. Create docker repository and build & push the image. 
6. heroku login
7. heroku git:remote -a guestbook-service-01
8. heroku container:login
9. heroku container:push web
10. heroku container:release web
11. Open the app url in browser: https://guestbook-service-01.herokuapp.com/guestbook

# **End Point Details (REST Documentation):**

Rest documentation of end point details can be found at this url : https://guestbook-service-01.herokuapp.com/docs/index.html

