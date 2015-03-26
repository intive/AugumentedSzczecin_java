#!/bin/bash
readonly mongod='./mongodb-linux-x86_64-3.0.0/bin/mongod'
readonly dbpath='dbfiles'
readonly port='9090'
mkdir -p $dbpath > /dev/null
$mongod --dbpath $dbpath --port $port