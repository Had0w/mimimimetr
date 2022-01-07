FROM adoptopenjdk/openjdk11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
COPY src/main/resources/db/migration/*.sql sql/
#COPY ${flyway.confdir}/flyway.conf conf/
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]