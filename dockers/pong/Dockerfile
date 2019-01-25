FROM java

EXPOSE 8080

ENV mode pong

RUN mkdir -p /var/opt/$mode

WORKDIR /var/opt/$mode

COPY ./services-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "services-0.0.1-SNAPSHOT.jar", "--spring.profiles.active=pong", "--eureka.server.host=eureka", "--server.port=8080", "--spring.cloud.config=http://config:8888"]