@rem starts a container called backend based on the maven:3.8.1-openjdk-11 base image
@rem exposes and maps port 8081 to the host's port 8081
@rem mounts the current working directory as /user/src
@rem mounts the maven artifact cache in the appropriate place for the container root user
@rem sets working directory to /usr/src
@rem compiles and runs
@rem runs interactively (--it)

docker run -it --name backend ^
	-p 8081:8081 ^
	-v "%cd%":/user/src/ ^
	-w /user/src ^
	maven:3.8.1-openjdk-11 ^
	mvn compile quarkus:dev
