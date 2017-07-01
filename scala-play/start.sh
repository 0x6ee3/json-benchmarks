target/universal/stage/bin/play -Dplay.crypto.secret=abc -Dhttp.port=8000 > /dev/null &
echo "$!" > PID

echo -n "warmup"
wrk -d 30s http://localhost:8000/ > /dev/null
echo -n "."
wrk -d 30s http://localhost:8000/json > /dev/null
echo -n ". "
