FROM azul/zulu-openjdk-alpine:17-jre
# COPY target/dependency-jars /run/dependency-jars
ADD target/keypr-server-fat-0.9-SNAPSHOT.jar /run/keypr-server-fat-0.9-SNAPSHOT.jar

ENTRYPOINT java -jar /run/keypr-server-fat-0.9-SNAPSHOT.jar