FROM java

EXPOSE 8888


RUN mkdir -p /var/opt/config

VOLUME ["/config"]

WORKDIR /var/opt/config

COPY ./micros-config-0.0.1-SNAPSHOT.jar .
CMD ls

CMD ["java", "-jar", "./micros-config-0.0.1-SNAPSHOT.jar", "--spring.cloud.config.server.git.uri=/config"]
