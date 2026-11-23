FROM maven:3.9-eclipse-temurin-11 AS build
WORKDIR /app
COPY pom.xml .
RUN mvn -B dependency:go-offline
COPY src ./src
RUN mvn -B clean package -DskipTests

FROM eclipse-temurin:11-jre-jammy AS runtime
WORKDIR /app
COPY --from=build --chown=10001:10001 \
    /app/target/comunicacao_api-0.0.1-SNAPSHOT.jar \
    app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
