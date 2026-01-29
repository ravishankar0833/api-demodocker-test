#start with the official debain image
FROM debian:bullseye-slim

# install open jdk and maven
RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven \
    && rm -rf /var/ib/apt/lists/* # clean upt he apt cache to reduce image size

# ===== Environment Variables =====
ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
ENV MAVEN_HOME=/usr/share/maven
ENV PATH=$JAVA_HOME/bin:$MAVEN_HOME/bin:$PATH

# Working directory
WORKDIR /app

#Declare the volume point insisde the container
VOLUME ["/app/target"]

#Copy the project files into the container
COPY . .

# Run TestNG tests
#CMD ["mvn", "clean", "test"]
CMD ["mvn", "test", "-Dtestng.suiteXmlFile=testng.xml"]