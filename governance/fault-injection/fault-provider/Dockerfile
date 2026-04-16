FROM openjdk:17

WORKDIR /home/apps/
ADD target/fault-provider-1.0-SNAPSHOT.jar .
ADD start.sh .

ENTRYPOINT ["sh", "/home/apps/start.sh"]