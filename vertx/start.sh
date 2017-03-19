#!/bin/bash

java -jar target/vertx-json-benchmark-1.0-SNAPSHOT-fat.jar -conf '{"http.port":8000}' > /dev/null &
echo "$!" > PID

sleep 1

echo -n "warmup"
wrk -d 10s http://localhost:8000/ > /dev/null
echo -n "."
wrk -d 10s http://localhost:8000/json > /dev/null
echo -n ". "
