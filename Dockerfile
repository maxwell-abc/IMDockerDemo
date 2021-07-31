FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/fims-crm-mid.jar spring-docker.jar
EXPOSE 18603
ENTRYPOINT ["java","-jar","/spring-docker.jar"]


