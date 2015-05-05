#!/bin/bash

curl -v http://localhost:8000/places/monuments -H "Content-Type: application/json" -d '{"name":"thug_345", "location":{"longitude":53.4302, "latitude":14.5555}}'
curl -v http://localhost:8000/places/monuments -H "Content-Type: application/json" -d '{"name":"thug_999", "location":{"longitude":53.4294, "latitude":14.5562}}'
curl -v http://localhost:8000/places/monuments -H "Content-Type: application/json" -d '{"name":"thug_996", "location":{"longitude":53.4296, "latitude":14.5560}}'

curl -v "http://localhost:8000/q?lg=53.43&lt=14.55&radius=1000&cat=PLACE_MONUMENT"