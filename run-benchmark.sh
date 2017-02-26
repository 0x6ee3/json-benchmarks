#!/bin/bash

TEST_THREADS="4"
TEST_DURATION="60s"
TEST_CONCURRENCY=(8 16 32 64 128 256)

mkdir -p reports/

echo "*** Preparation! ***"

for d in */ ; do
    d="${d%/}" # strip trailing slash 
    cd $d

    if [ -f prepare.sh ]; then
        echo "Preparing $d.."
        ./prepare.sh
    fi

    cd ..
done

echo "*** Benchmarking! ***"

for d in */ ; do
    d="${d%/}" # strip trailing slash 
    cd $d

    if [ -f start.sh ]; then
        echo -n "$d: start"
        ./start.sh
        echo -n ". "

        for c in ${TEST_CONCURRENCY[@]}; do
            echo -n "$c"
            wrk -t $TEST_THREADS -d $TEST_DURATION -c $c http://localhost:8080/ > ../reports/$d-hello.txt
            echo -n "."
            wrk -t $TEST_THREADS -d $TEST_DURATION -c $c http://localhost:8080/json > ../reports/$d-json.txt
            echo -n ". "
        done
        
        echo -n "stop"
        ./stop.sh
        echo "."
    fi

    cd ..
done

echo "*** Done! ***"
