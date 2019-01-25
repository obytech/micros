FROM java

EXPOSE 1111

RUN mkdir -p /var/opt/eureka

WORKDIR /var/opt/eureka

COPY ./micros-eureka-0.0.1-SNAPSHOT.jar .

CMD ["java", "-jar", "/var/opt/eureka/micros-eureka-0.0.1-SNAPSHOT.jar", "--eureka.instance.hostname=eureka", "--eureka.instance.host=eureka", "--spring.cloud.config=http://config:8888"]
