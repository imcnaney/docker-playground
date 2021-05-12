# syntax=docker/dockerfile:1

FROM maven:3.8.1-openjdk-11
WORKDIR /user/src
# maybe not necessary
COPY . .
EXPOSE 8081
CMD ["mvn", "compile", "quarkus:dev"]
