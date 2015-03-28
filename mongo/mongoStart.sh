#!/bin/bash

readonly mongod="/c/Program Files/MongoDB/Server/3.0/bin/mongod.exe"
readonly dbpath='c/data/db'
readonly port='9090'

mkdir -p $dbpath > /dev/null
"$mongod" --dbpath "$dbpath" --port $port
