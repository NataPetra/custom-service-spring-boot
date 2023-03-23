# custom-service-spring-boot

To pull all docker images:

```text
docker compose pull
```

To build local docker image:

```text
docker compose build
```

To run database:

```text
docker compose up -d db-mysql
```

To run mvn:

```text
docker compose up -d maven-build
```

To run web app:
```text
docker compose up -d bootcamp-app
```

Go to [http://localhost:8080/app_users](http://localhost:8080/app_users)