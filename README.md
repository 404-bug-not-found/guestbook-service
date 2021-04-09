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
5. download the postgres image: `docker pull postgres`
6. start the postgres container: `docker run --name postgres -e POSTGRES_PASSWORD=open -p 5432:5432 -d postgres`
7. Create the docker network: `docker network create --driver bridge guest-network`
8. Start Database Server on Network: `docker run --name postgres-db-cotainer --network guest-network -e POSTGRES_PASSWORD=open -e POSTGRES_DB=postgres -d postgres`
9. Build local docker image of project: `docker build -t guestbook-image .`
10. Start Docker Container based on Image: `docker run --name guestbook_postgres_container --network guest-network -e PORT=8080 -e SPRING_PROFILES_ACTIVE=postgres -p 9000:8080 -d guestbook-image:latest`
11. Run the GuestBook service with postgres DB : `http://localhost:9000/guestbook`
12. Refer the REST Documentation for end point details
    

**Heroku App Url:** `https://guestbook-service-01.herokuapp.com/guestbook`

# **End Point Details (REST Documentation):**

Rest documentation of end point details can be found at this url : https://guestbook-service-01.herokuapp.com/docs/index.html

