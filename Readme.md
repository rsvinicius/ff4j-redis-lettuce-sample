# FF4J - REDIS - LETTUCE - SAMPLE

FF4j is an implementation of the Feature Toggle pattern written in Java. Feature Toggle (or Feature Flag) is the capacity for a program or an application to change features (business logic) behavior at runtime.

## Description

FF4J usage sample using redis (lettuce) store. The toggle can be operated through web console or via api.

## Requirements

- Java 17+
- IntelliJ IDEA / Netbeans / Eclipse
- Docker (and Docker Compose for easy setup)

## Usage

1) Run docker-compose up in CLI to start a local redis database

    The docker compose file should look like this (file included in project root directory):
    ```
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

2) Start application in IDE or via command line:

    ```
    ./gradlew bootRun
    ```  

3) You can toggle features through [web-console](localhost:9080/ff4j-redis/ff4j-web-console/) or via api.
   * Web console credentials are defined in application.yml file.

## Contributing

Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.  Please make sure to update tests as appropriate.

## License

Usage is provided under the [MIT License](https://mit-license.org/). See LICENSE for full details.
