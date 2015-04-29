#!/bin/bash

curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"asd", "password":"zxc"}'

curl -v http://localhost:8000/places/monuments -H "Content-Type: application/json" -d '{"name":"thug_345", "location":{"longitude":53.4302, "latitude":14.5555}}' -u asd:zxc
curl -v http://localhost:8000/places/monuments -H "Content-Type: application/json" -d '{"name":"thug_999", "location":{"longitude":53.4294, "latitude":14.5562}}' -u asd:zxc

curl -v "http://localhost:8000/q?lg=53.4295&lt=14.5556&radius=100&cat=PLACE_MONUMENT"
