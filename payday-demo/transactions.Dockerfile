FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY transactions-service/pom.xml /build/payday-demo/transactions-service
COPY . /build/payday-demo/
WORKDIR /build/payday-demo/transactions-service
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/payday-demo/transactions-service/target/transactions-service-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "transactions-service-0.0.1-SNAPSHOT.jar"]