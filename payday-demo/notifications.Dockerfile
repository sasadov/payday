FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY notifications-service/pom.xml /build/payday-demo/notifications-service
COPY . /build/payday-demo/
WORKDIR /build/payday-demo/notifications-service
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/payday-demo/notifications-service/target/notifications-service-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "notifications-service-0.0.1-SNAPSHOT.jar"]