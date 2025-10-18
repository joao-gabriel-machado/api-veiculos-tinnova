FROM openjdk:21-jdk

WORKDIR /app

COPY build/api-veiculos-dev.jar /app/app.jar
COPY src/main/resources/privatekey.pem /app/src/main/resources/privatekey.pem
COPY src/main/resources/publickey.pem /app/src/main/resources/publickey.pem

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]