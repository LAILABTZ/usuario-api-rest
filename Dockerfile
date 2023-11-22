# Etapa de construcción
FROM maven:3.8.3-openjdk-17 AS build
WORKDIR /usuario
COPY src ./src
COPY pom.xml ./pom.xml
RUN mvn clean package

# Etapa de ejecución
FROM openjdk:17.0.2-jdk
VOLUME /tmp
COPY --from=build /usuario/target/usuario-0.0.1-SNAPSHOT.jar usuario.jar
EXPOSE 8098
ENTRYPOINT ["java","-jar","usuario.jar"]