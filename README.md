# Roadmap - microservices

Introduction into microservices architecture.

## Functionality

2 micro-services one acts as a pinger, the other acts as "ponger" - reply with pong.
Actualy this is a single microservice acts in 2 seperate profiles specifying ping/pong.

## Building and executing
Run: `gradlew bootJar copyArtifacts`

then, in order to boot the application, first goto to dockers dir, then boot with docker-compose:
```
cd dockers
docker-compose up
```

Let the services sync and the whole app to get stable, then a periodic msg will be printed.
