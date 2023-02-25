FROM openjdk:19-jdk-alpine
COPY target/ussd.jar ussd.jar
ENTRYPOINT ["java","-jar","ussd.jar"]
