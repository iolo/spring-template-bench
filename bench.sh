#!/bin/sh

mvn spring-boot:run -Drun.profiles=thymeleaf &

sleep 15

ab -n 10 -c 2 http://localhost:8080/
ab -n 10 -c 2 http://localhost:8080/react/nashorn
ab -n 10 -c 2 http://localhost:8080/react/v8
ab -n 10 -c 2 http://localhost:8080/react.html

curl -X POST localhost:8080/shutdown
