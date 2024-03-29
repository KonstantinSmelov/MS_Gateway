FROM openjdk:11

WORKDIR /app

COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline
EXPOSE 8090

COPY src ./src

CMD ["./mvnw", "spring-boot:run"]