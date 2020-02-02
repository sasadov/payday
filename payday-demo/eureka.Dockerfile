FROM maven:3.5.2-jdk-8-alpine AS MAVEN_BUILD
COPY eureka/pom.xml /build/payday-demo/eureka
COPY . /build/payday-demo/
WORKDIR /build/payday-demo/eureka
RUN mvn package
FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=MAVEN_BUILD /build/payday-demo/eureka/target/eureka-0.0.1-SNAPSHOT.jar /app/
ENTRYPOINT ["java", "-jar", "eureka-0.0.1-SNAPSHOT.jar"]