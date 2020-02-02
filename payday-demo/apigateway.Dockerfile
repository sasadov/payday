FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY apigateway/pom.xml /build/payday-demo/apigateway
COPY . /build/payday-demo/
WORKDIR /build/payday-demo/apigateway
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/payday-demo/apigateway/target/apigateway-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "apigateway-0.0.1-SNAPSHOT.jar"]