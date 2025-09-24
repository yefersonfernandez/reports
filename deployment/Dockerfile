FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY ./applications/app-service/build/libs/reports.jar .

EXPOSE 8082
ENTRYPOINT ["java","-jar","reports.jar"]

