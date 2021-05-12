# docker-playground project

## Summary

This project launches a really simple Quarkus API, in dev mode, on http://localhost:8081.  The actual API is at
http://localhost:8081/hello.  The entire project directory is mounted inside of the docker container, so any file changes
you make should be automatically detected and the project recompiled the next time you hit http://localhost:8081.

## How To Launch It

### Linux

Simply run `docker compose up` or, if that's not a thing, run `docker-compose up`.  Note the hyphen.

### Windows

I don't care.  You're using a defective OS so you can figure it out for yourself.  You'll have to install docker, set the memory limit for containers, and run commands in powershell.

## Docker Cheat Sheet

To see what containers are running run `docker container ps`.

To stop the running container (only really relevant for windows) run `docker stop backend`.

To start the container again (only really relevant for windows) run `docker start backend`.

To reattach to the running container (only really relevant for windows) run `docker attach backend`.

# The following instructions aren't really relevant if you're running inside of docker.

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

## Creating a native executable

You can create a native executable using:
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using:
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/getting-started-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.html.

## Provided examples

### RESTEasy JAX-RS example

REST is easy peasy with this Hello World RESTEasy resource.

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
