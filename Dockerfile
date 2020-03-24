FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY ./target/fourmeal-1.1.0.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 5000