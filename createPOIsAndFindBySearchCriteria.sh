#!/bin/bash
userpass="asd@zxc.pl:zxc"

curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"asd@zxc.pl", "password":"zxc"}'

curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_777", "location":{"longitude":87.4644, "latitude":88.4454}}' -u $userpass
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_111", "location":{"longitude":52.3453, "latitude":75.4242}}' -u $userpass
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_555", "location":{"longitude":34.4535, "latitude":86.4643}}' -u $userpass	
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_7876", "location":{"longitude":111.1313, "latitude":68.242}}' -u $userpass	

curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_234", "location":{"longitude":12.4564, "latitude":4.535}}' -u $userpass
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_9876", "location":{"longitude":46.6768, "latitude":84.3454}}' -u $userpass
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_1", "location":{"longitude":24.242, "latitude":45.3535}}' -u $userpass
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_290", "location":{"longitude":35.4543, "latitude":43.4342}' -u $userpass

curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "location":{"longitude":56.56, "latitude":67.89}}' -u $userpass
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_123", "location":{"longitude":33.4354, "latitude":9.5643}}' -u $userpass
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "location":{"longitude":50.4, "latitude":6.4242}}' -u $userpass
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "location":{"longitude":34.5654, "latitude":44.435}}' -u $userpass

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=3600000&cat=EVENT&cat=PLACE&cat=PERSON" -u $userpass
