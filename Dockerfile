FROM maven:3.3-jdk-8-onbuild as builder

FROM java:8

COPY --from=builder /usr/src/app/target/dotwebstack-theatre*.jar /opt/dotwebstack-theatre.jar

WORKDIR /opt

CMD ["java","-jar","/opt/dotwebstack-theatre.jar"]

EXPOSE 8080
