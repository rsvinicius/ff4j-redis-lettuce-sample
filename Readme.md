# FF4J - SAMPLE

FF4J is a Kotlin implementation of the Feature Toggle pattern, allowing dynamic modification of program or application features (business logic) at runtime. This sample demonstrates the use of FF4J with a Redis (Lettuce) store, providing the flexibility to manage feature toggles programmatically via API.

## Description

This sample showcases the integration of FF4J with a Redis store using Lettuce, focusing solely on the configuration of FF4J and the API. The Spring Security and web console configurations have been removed to streamline the project. Check out the [FF4J Web Console](https://github.com/rsvinicius/spring-ff4j-webconsole-demo) Demo for an example of using FF4J with a web console.

## Requirements

- Java 17+
- IntelliJ IDEA / Netbeans / Eclipse
- Docker (and Docker Compose for convenient setup)

## Usage

1) Launch a local Redis database using Docker Compose by running the following command in the CLI:
   ```bash
    docker-compose up
    ```  

   Ensure your docker-compose.yml file, included in the project root directory, resembles the provided example:
   ```yaml
    version: '3.3'
    services:
      redis:
        image: redis
        container_name: redis
        expose:
          - 6379
        ports:
        - "6379:6379" 
    ``` 

2) Start the application either in your IDE or via the command line:

    ```
    ./gradlew bootRun
    ```  

3) Toggle features programmatically through the API.

## Swagger

Explore the API using Swagger at:
```
http://localhost:9080/ff4j-redis/swagger-ui/index.html
```