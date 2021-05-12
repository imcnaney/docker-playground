# docker-playground project

## Summary

This project launches a really simple Quarkus API, in dev mode, on http://localhost:8081.  The actual API is at
http://localhost:8081/hello.  The entire project directory is mounted inside of the docker container, so any file changes
you make should be automatically detected and the project recompiled the next time you hit http://localhost:8081.

## How To Launch It

### Linux

Simply run `./go.sh`.  It will mount your local maven cache from `~/.m2` into the container as well, so you won't have to
download artifacts every time you rebuild the container.  `Ctrl-c` will stop the server and destroy the container,
so you start fresh every time.

To connect to docker-compose's mariadb database run: `docker run -it --network docker-playground_default --rm mariadb mysql -hdb -uroot -p` and provide the password `example` (configured in docker-compose.yml)

### Windows

All commands assume you're running them in powershell from the root of the project (the docker-playground directory).

**Create a folder called `.m2` in your home directory if you don't already have one: `mkdir ~/.m2`**

To create the `backend` container the first time run (on windows): `./go.cmd`.  This will create a container that you'll
start and stop with docker commands.  Java dependencies are downloaded within the container's file system, so to avoid
having to download them every time the script doesn't automatically delete the container every time you shut it down.

To detach from the container, which on windows leaves it running but on linux kills it, press `ctrl-c`.

To destroy the container so you can start fresh with `go.cmd` run `docker kill backend`.  This happens automatically
every time you `ctrl-c` on linux.

It's not clear how stable dev mode is so if you start seeing strange behavior, especially after doing a `git pull` or
leaving it running for days, then kill the backend container and start a new one from scratch.  Some changes, like
changing the port in `application.properties` won't be picked up until you do this.

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
