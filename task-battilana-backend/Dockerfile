# ============================================================
# ETAPA 1: "La cocina" — aquí se compila el proyecto
# ============================================================
FROM maven:3.9-eclipse-temurin-21 AS build

WORKDIR /app

# Primero solo la lista de ingredientes (dependencias)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# 3) Ahora sí copiamos el código fuente
COPY src ./src
RUN mvn package -DskipTests -B

# ============================================================
# ETAPA 2: "La vitrina" — imagen final, liviana, solo para ejecutar
# ============================================================
FROM eclipse-temurin:21-jre

WORKDIR /app

# Traemos SOLO el .jar desde la etapa "build"
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

#Comando para ejecutar tu aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]