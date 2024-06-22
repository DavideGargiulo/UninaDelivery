# Use an OpenJDK 11 base image with OpenJFX 11
FROM openjdk:11-jre-slim

# Set the working directory in the container to /app
WORKDIR /app

# Copy the current directory contents into the container at /app
ADD . /app

# Compile the JavaFX application
RUN javac --module-path /usr/lib/jvm/default-jvm/jmods --add-modules javafx.controls,javafx.fxml -d out src/*.java

# Set the startup command to run your JavaFX application
CMD ["java", "--module-path", "/usr/lib/jvm/default-jvm/jmods", "--add-modules", "javafx.controls,javafx.fxml", "-cp", "out", "App"]