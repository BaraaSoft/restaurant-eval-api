FROM openjdk:8-jdk-alpine as builder
WORKDIR /app
COPY . .
#RUN ./mvnw package
#ARG JAR_FILE=target/restaurantsAppApi-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#&& java -jar target/restaurantsAppApi-0.0.1-SNAPSHOT.jar

COPY pom.xml .
# To resolve dependencies in a safe way (no re-download when the source code changes)
RUN mvn clean package -Dmaven.main.skip -Dmaven.test.skip && rm -r target

# To package the application
COPY src ./src
RUN mvn clean package -Dmaven.test.skip

FROM openjdk:8-jdk-alpine
WORKDIR /root/
# Copy the binary from the builder stage and set it as the default command.
COPY --from=builder /app/bin/app.jar /usr/local/bin/
ENTRYPOINT ["java","-jar","app.jar"]
#CMD ["hello"]

#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]