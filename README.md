# Health check service

#### Instructions for running locally:
Pre-requisites: Java 11, Docker, Maven

`cd local-development`

`docker-compose up`

`cd ..`

`mvn clean flyway:migrate install`

`java -jar target/health-check-service-0.0.1.jar`

Visit localhost:8888 in a browser