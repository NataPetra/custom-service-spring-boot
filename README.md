# Test project for BootCamp - custom-service-spring-boot

## Technical task

- Multi-module maven project
- Version control system - Git
  Technologies:
  Java 11, Log4j2, Spring Boot 2, Hibernate 5, MySQL 8.
- Using Docker and Docker Compose
- Unit and Integration tests for the application
- Using Liquibase
- Format: JSON
- Architectural style: REST
- Endpoint for adding a user, main fields:
  Surname (40 characters, latin letters only)
  Name (20 characters, latin letters only)
  Middle name(40 characters, latin letters only)
  Email(50 characters, standard pattern)
  Role(Administrator, Sale User, Customer User, Secure API User)
- Endpoint to get all users, main fields (records must be sorted by email in alphabetical order):
  Full name
  Email
  Role
- Error processing
- Paginate output of records by 10 to get the user

## DevOps part

To pull all docker images:

```text
docker compose pull
```

To run mvn:

```text
docker compose up -d maven-build
```

To build local docker image:

```text
docker compose build
```

To run database:

```text
docker compose up -d db-mysql
```

To run web app:
```text
docker compose up -d bootcamp-app
```

## To use the application

To get all users (sorted by email, default 10 records and first page):

Go to [http://localhost:8080/app_users](http://localhost:8080/app_users)

To get all users with a specific page and number of users (example of the first page with the output of two users):

Go to [http://localhost:8080/app_users/?page=0&size=2](http://localhost:8080/app_users/?page=0&size=2)
example of the first page with the output of two users

For adding a user on http://localhost:8080/app_users send JSON (example):
```text
{
   "lastName": "Jason",
   "firstName": "Fila",
   "surname": "Ivanovich",
   "email": "jasonhila@lkcv.ru",
   "roleName": "ADMINISTRATOR"
}
```
Like a bonus get one user (for example fist user):

Go to [http://localhost:8080/app_users/1](http://localhost:8080/app_users/1)

If something is not clear, write to the creator of the application, will be grateful for feedback.

