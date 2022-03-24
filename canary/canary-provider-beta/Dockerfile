FROM openjdk:8u181-jdk-alpine

WORKDIR /home/apps/
ADD target/canary-provider-beta-1.0-SNAPSHOT.jar .
ADD start.sh .

ENTRYPOINT ["sh", "/home/apps/start.sh"]