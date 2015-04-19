#!/bin/bash

#A script for testing REST API with libcurl
#Set your hostname and path to text file containing URLs you want to test below

host="http://localhost:8000/" #remember the ending slash
urls="resourceurl.txt"

for url in $(cat $urls)
do 

status=$(curl --max-time 30 -sL -w "%{http_code} %{url_effective}\\n" $host$url -o /dev/null);
word=($status)

if [[ ${word[0]} -eq 200 ]]; then
echo “OK $status”
else
echo “FAILED $status”

fi
done
