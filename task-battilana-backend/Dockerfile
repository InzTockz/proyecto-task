#Usa la imagen de base de Eclipse Temurin con java 21
FROM eclipse-temurin:21-jdk

#Establece el directorio de trabajo del contenedor.
WORKDIR /app

#Copia tu archivo .jar al contenedor
COPY target/task-battilana-0.0.1-SNAPSHOT.jar app.jar

#Expone el puerto donde corre tu aplicacion
EXPOSE 8080

#Comando para ejecutar tu aplicacion
ENTRYPOINT ["java", "-jar", "app.jar"]