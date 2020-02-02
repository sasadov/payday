FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY customers-service/pom.xml /build/payday-demo/customers-service
COPY . /build/payday-demo/
WORKDIR /build/payday-demo/customers-service
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/payday-demo/customers-service/target/customers-service-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "customers-service-0.0.1-SNAPSHOT.jar"]