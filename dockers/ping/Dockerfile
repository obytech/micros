FROM java

EXPOSE 8080

ENV mode ping

RUN mkdir -p /var/opt/$mode

WORKDIR /var/opt/$mode

COPY ./services-0.0.1-SNAPSHOT.jar .

CMD [\
    "java", "-jar", "services-0.0.1-SNAPSHOT.jar", \
        "--spring.profiles.active=ping", \
        "--eureka.server.host=eureka", \
        "--server.port=8080", \
        "--spring.cloud.config.uri=http://config:8888" \
    ]