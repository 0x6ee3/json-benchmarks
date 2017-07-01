#!/bin/bash

java -jar target/springhttpbenchmark-0.0.1-SNAPSHOT.jar --server.port=8000 > /dev/null &
echo "$!" > PID

echo "waiting for spring..."
sleep 20

echo -n "warmup"
wrk -d 10s http://localhost:8000/ > /dev/null
echo -n "."
wrk -d 10s http://localhost:8000/json > /dev/null
echo -n ". "
