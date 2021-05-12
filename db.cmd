#!/bin/bash

docker run -it --network docker-playground_default --rm mariadb mysql -hdb -uroot -p

