FROM openjdk:21-oracle
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} twitter_app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/twitter_app.jar"]