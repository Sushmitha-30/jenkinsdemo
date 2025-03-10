FROM openjdk:17

LABEL maintainer="Sushmitha@gmail.com"

WORKDIR /app

COPY target/springboot-mysql-docker.jar /app/springboot-mysql.jar

# Set the entry point for running the application
ENTRYPOINT ["java", "-jar", "/app/springboot-mysql.jar"]
