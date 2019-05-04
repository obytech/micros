# Roadmap - microservices

Introduction into microservices architecture.

## Functionality

2 micro-services one acts as a pinger, the other acts as "ponger" - reply with pong.
Actually this is a single microservice acts in 2 separate profiles specifying ping/pong.

## Building and executing
Run: `gradlew bootJar copyArtifacts`

then, in order to boot the application, first goto to dockers dir, then boot with docker-compose:
```
cd dockers
docker-compose build && docker-compose up
```

Let the services sync and the whole app to get stable, then a periodic msg will be printed.

## Update Pong message
Update file `dockers/config-service/config.prop/pong-service.yml` and commit it.
Then, send POST request to `http://<PONG-service>:8080/actuator/refresh`, and the update should be reflected.
