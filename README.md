# Bugtracker-Projects
Bugtracker Sprig boot Projects

# Bugtracker Web Application using Spring Boot and H2 In memory database

Run org.springframework.boot.SpringApplication.BugTrackerApplication as a Java Application.

Runs on default port of Spring Boot - 8080 

## Generate a WAR

`mvn clean install` generate a war which can deployed to web server.

We will deploy to Docker as a WAR

## Web Application

- http://localhost:8080/login with dummy/dummy as credentials
- You can add, delete and update your Jiras
- Spring Security is used to secure the application
- `com.nilx.springboot.web.bugtracker.SecurityConfiguration` contains the in memory security credential configuration.

## H2 Console

- http://localhost:8080/h2-console
- Use `jdbc:h2:mem:testdb` as JDBC URL 
