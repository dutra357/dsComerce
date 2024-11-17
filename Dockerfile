FROM eclipse-temurin:17-jre-jammy
#RUN apt-get update && apt-get upgrade
#COPY target/modulo1_proj.avaliativo-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
