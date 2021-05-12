#!/usr/bin/env bash

# starts a container called backend based on the maven:3.8.1-openjdk-11 base image
# exposes and maps port 8081 to the host's port 8081
# mounts the current working directory as /user/src
# mounts the maven artifact cache in the appropriate place for the container root user
# sets working directory to /user/src
# compiles and runs
# cleans up after itself (--rm)
# runs interactively (--it)
docker run --rm -it --name backend \
	-p 8081:8081 \
	-v "$(pwd)":/user/src/ \
	-v "$HOME/.m2":/root/.m2 \
	-w /user/src \
	maven:3.8.1-openjdk-11 \
	mvn compile quarkus:dev
#"mvn compile quarkus:dev"

