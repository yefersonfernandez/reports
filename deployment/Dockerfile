FROM eclipse-temurin:21-jdk-alpine
WORKDIR /app
COPY ./applications/app-service/build/libs/reports.jar .

RUN mkdir -p /keys
VOLUME /keys

EXPOSE 8082
ENTRYPOINT ["java","-jar","reports.jar"]
