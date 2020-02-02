FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY accounts-service/pom.xml /build/payday-demo/accounts-service
COPY . /build/payday-demo/
WORKDIR /build/payday-demo/accounts-service
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/payday-demo/accounts-service/target/accounts-service-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "accounts-service-0.0.1-SNAPSHOT.jar"]