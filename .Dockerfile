FROM openjdk:22-jdk-slim
WORKDIR /app
COPY target/myapp.jar /app/myapp.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/myapp.jar"]
