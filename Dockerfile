# ===== Etapa 1: build =====
FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /app

# Cache de dependencias: copiar wrapper + pom primero
COPY .mvn/ .mvn/
COPY mvnw pom.xml ./
RUN chmod +x mvnw && ./mvnw dependency:go-offline -B

# Compilar el artefacto
COPY src ./src
RUN ./mvnw clean package -DskipTests -B

# ===== Etapa 2: runtime =====
FROM eclipse-temurin:21-jre-alpine AS runtime
WORKDIR /app

# Usuario no-root por seguridad
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
ENV SPRING_PROFILES_ACTIVE=prod \
    JAVA_OPTS="-XX:MaxRAMPercentage=75.0"

# Healthcheck contra el endpoint de Actuator (usa PORT si Railway lo inyecta)
HEALTHCHECK --interval=30s --timeout=3s --start-period=40s --retries=3 \
    CMD wget -q --spider http://localhost:${PORT:-8080}/actuator/health || exit 1

ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
