FROM amazoncorretto:17.0.9-alpine3.18

WORKDIR /app

COPY ./target/prueba_tecnica-0.0.1-SNAPSHOT.jar /app

EXPOSE 8086

ENTRYPOINT ["java", "-jar", "prueba_tecnica-0.0.1-SNAPSHOT.jar"]