FROM openjdk:11-buster

RUN mkdir /home/app
COPY target/simple-crud-0.0.1-SNAPSHOT.jar /home/app/.

CMD ["java","-jar","/home/app/simple-crud-0.0.1-SNAPSHOT.jar"]
