# Estágio 1: Build
FROM eclipse-temurin:17-jdk-jammy as build-image
WORKDIR /to-build-app
COPY . .
RUN ./mvnw dependency:go-offline
RUN ./mvnw clean package -DskipTests

# Verifica se o arquivo JAR foi gerado corretamente
RUN ls -la /to-build-app/target

# Estágio 2: Runtime
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=build-image /to-build-app/target/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=8080"]