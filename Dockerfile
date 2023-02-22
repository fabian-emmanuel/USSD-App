FROM openjdk:19-jdk-alpine
COPY target/ussd.jar ussd.jar
EXPOSE 80
ENTRYPOINT ["java","-jar","ussd.jar"]