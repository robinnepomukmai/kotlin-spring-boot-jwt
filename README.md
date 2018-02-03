kotlin springboot-jwt
An Example Kotlin Spring Boot Application for Securing a REST API with JSON Web Token (JWT)

This application can be used as a seed to quick start your spring boot REST API project with a fully functional security module.
Main building blocks

    Spring Boot 1.5.3.RELEASE go to http://docs.spring.io/spring-boot/docs/1.5.3.RELEASE/reference/htmlsingle/ to learn more about spring boot
    JSON Web Token go to https://jwt.io/ to decode your generated token and learn more
    H2 Database Engine - used for rapid prototyping and development, but not suitable for production at least in most cases. Go to www.h2database.com to learn more

To run the application

Use one of the several ways of running a Spring Boot application. Below are just two options:

    Build using maven goal: mvn clean package and execute the resulting artifact as follows java -jar springboot-jwt-0.0.1-SNAPSHOT.jar or
    On Unix/Linux based systems: run mvn clean package then run the resulting jar as any other executable ./springboot-jwt-0.0.1-SNAPSHOT.jar

To test the application
First you will need the following basic pieces of information:

    client: accountjwtclient
    secret: XY7kmzoNzl100
    Non-admin username and password: john.doe and jwtpass
    Admin user: james.dean@nepomuk.io and test123
    Example of resource accessible to only an admin user: http://localhost:8080/api/user/{user}

    Generate an access token

Use the following generic command to generate an access token: $ curl client:secret@localhost:8080/oauth/token -d grant_type=password -d username=user -d password=pwd

For this specific application, to generate an access token for the non-admin user john.doe, run: $ curl testjwtclientid:XY7kmzoNzl100@localhost:8080/oauth/token -d grant_type=password -d username=james.dean@nepomuk.io -d password=test123# kotlin-spring-boot-jwt
