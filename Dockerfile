# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine
# Open port for the world
EXPOSE 8080
# Copy application Jar with libs (you can COPY change to ADD)
COPY build/libs/com.multiheaded.webapp*.jar /app.jar
# Specify default command (you can substitude CMD with ENTRYPOINT)
CMD ["/usr/bin/java", "-jar", "/app.jar"]