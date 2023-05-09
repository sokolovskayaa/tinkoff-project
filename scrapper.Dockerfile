FROM openjdk:17

COPY scrapper/target/scrapper-1.0-SNAPSHOT.jar /scrapper.jar

ENTRYPOINT ["java", "-jar", "scrapper.jar"]