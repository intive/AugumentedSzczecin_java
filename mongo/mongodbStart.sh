#!/bin/bash

readonly mongod='C:\Program Files\MongoDB\Server\3.0\bin\mongod'
readonly dbpath='C:\data'
readonly port='9090'

mkdir -p $dbpath > /dev/null
$mongod --dbpath $dbpath --port $port
