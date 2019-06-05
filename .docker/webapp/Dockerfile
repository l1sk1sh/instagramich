# Run with `docker build -t webapp:latest -f .docker/webapp/Dockerfile . && docker run -p 4000:8080 --name webapp webapp:latest `
# Base Alpine Linux based image with OpenJDK JRE only
FROM openjdk:8-jre-alpine

# Open port for the world
EXPOSE 8080 3306

# Copy application Jar with libs (you can COPY change to ADD)
COPY app.jar /app.jar

# Specify default command (you can substitude CMD with ENTRYPOINT)
CMD ["/usr/bin/java", "-jar", "/app.jar"]