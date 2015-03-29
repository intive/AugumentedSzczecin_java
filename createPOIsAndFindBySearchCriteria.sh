#!/bin/bash

curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"asd", "password":"zxc"}'

curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_777", "location":{"longitude":87.4644, "latitude":92.4454}}' -u asd:zxc
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_111", "location":{"longitude":52.3453, "latitude":75.4242}}' -u asd:zxc
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_555", "location":{"longitude":34.4535, "latitude":786.4643}}' -u asd:zxc	
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_7876", "location":{"longitude":111.1313, "latitude":6768.242}}' -u asd:zxc	

curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_234", "location":{"longitude":12.4564, "latitude":4.535}}' -u asd:zxc
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_9876", "location":{"longitude":46.6768, "latitude":984.3454}}' -u asd:zxc
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_1", "location":{"longitude":342324.242, "latitude":4545.3535}}' -u asd:zxc	
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_290", "location":{"longitude":3545.4543, "latitude":4343.4342}' -u asd:zxc

curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "location":{"longitude":56.56, "latitude":67.89}}' -u asd:zxc
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_123", "location":{"longitude":1033.4354, "latitude":93.5643}}' -u asd:zxc
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "location":{"longitude":550.4, "latitude":6.4242}}' -u asd:zxc	
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "location":{"longitude":34.5654, "latitude":44.435}}' -u asd:zxc

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=8900000&cat=EVENT&cat=PLACE&cat=PERSON" -u asd:zxc