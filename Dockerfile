#FROM openjdk:8-jdk-alpine as builder
FROM maven:3.8.4-openjdk-8 as builder
WORKDIR /app
COPY . .
#RUN ./mvnw package
#ARG JAR_FILE=target/restaurantsAppApi-0.0.1-SNAPSHOT.jar
#COPY ${JAR_FILE} app.jar
#&& java -jar target/restaurantsAppApi-0.0.1-SNAPSHOT.jar
#
#COPY pom.xml .
## To resolve dependencies in a safe way (no re-download when the source code changes)
#RUN mvn install
#
## To package the application
#COPY src ./src
#RUN mvn clean package -Dmaven.test.skip
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#
#FROM openjdk:8-jdk-alpine
#WORKDIR /root/
## Copy the binary from the builder stage and set it as the default command.
#COPY --from=builder /app/bin/app.jar /usr/local/bin/
#ENTRYPOINT ["java","-jar","app.jar"]
#CMD ["hello"]

#FROM openjdk:8-jdk-alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

# syntax=docker/dockerfile:experimental
########build stage########
########build stage########
#----
# Build stage
#----
FROM maven:3.5-jdk-8 as buildstage
# Copy only pom.xml of your projects and download dependencies
COPY pom.xml .
RUN mvn -B -f pom.xml dependency:go-offline
# Copy all other project files and build project
COPY . .
RUN mvn -B install

#----
# Final stage
#----
FROM java:8
COPY --from=buildstage ./target/*.jar ./
ENV JAVA_OPTS ""
CMD [ "bash", "-c", "java ${JAVA_OPTS} -jar *.jar -v"]
