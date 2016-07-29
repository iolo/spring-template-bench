#!/bin/sh

RESULT_FILE=$1.txt

mvn spring-boot:run -Drun.profiles=$1 &

sleep 15

echo $1 > $RESULT_FILE
echo '----------' >> $RESULT_FILE

ab -n 100 -c 4 http://localhost:8080/ >> $RESULT_FILE
ab -n 100 -c 4 http://localhost:8080/react/nashorn >> $RESULT_FILE
ab -n 100 -c 4 http://localhost:8080/react/v8 >> $RESULT_FILE
ab -n 100 -c 4 http://localhost:8080/react/v8ejs >> $RESULT_FILE
ab -n 100 -c 4 http://localhost:8080/react.html >> $RESULT_FILE

curl -X POST localhost:8080/shutdown

