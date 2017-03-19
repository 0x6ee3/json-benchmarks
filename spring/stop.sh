#!/bin/bash

PID=`cat PID`
kill $PID
rm -f PID
