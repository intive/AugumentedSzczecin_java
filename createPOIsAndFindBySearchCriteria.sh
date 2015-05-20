#!/bin/bash

# Creating new user
curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"asd@com", "password":"zxc"}'
# Creating another user
curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"qwe@com", "password":"asd"}'

# Creating events
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_777", "isPublic":false, "location":{"longitude":87.4644, "latitude":92.4454}, "owner":{"email":"asd@com", "password":"zxc"}, "tags":["concert", "movie", "game"]}' -u asd@com:zxc
## Public Event (without owner)
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_111", "isPublic":true, "location":{"longitude":52.3453, "latitude":75.4242}, "tags":["concert"]}' -u qwe@com:asd
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_555", "isPublic":false, "location":{"longitude":34.4535, "latitude":86.4643}, "owner":{"email":"qwe@com", "password":"asd"}, "tags":["movie", "game"]}' -u qwe@com:asd	
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug_7876", "isPublic":false, "location":{"longitude":111.1313, "latitude":168.242}, "owner":{"email":"asd@com", "password":"zxc"}, "tags":["game"]}' -u asd@com:zxc	

# Creating people
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_234", "isPublic":false, "location":{"longitude":12.4564, "latitude":4.535}, "owner":{"email":"qwe@com", "password":"asd"}, "tags":["friend", "grandmother", "teacher"]}' -u qwe@com:asd
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_9876", "isPublic":false, "location":{"longitude":46.6768, "latitude":84.3454}, "owner":{"email":"asd@com", "password":"zxc"}, "tags":["friend"]}' -u asd@com:zxc
## Public Person (without owner)
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_1", "isPublic":true, "location":{"longitude":124.242, "latitude":145.3535}, "tags":["friend", "teacher"]}' -u asd@com:zxc	
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug_290", "isPublic":false, "location":{"longitude":45.4543, "latitude":143.4342}, "owner":{"email":"qwe@com", "password":"asd"}, "tags":["grandmother"]}' -u qwe@com:asd

# Creating places
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "isPublic":false, "location":{"longitude":56.56, "latitude":67.89}, "owner":{"email":"asd@com", "password":"zxc"}, "tags":["restaurant", "cinema", "stadium"]}' -u asd@com:zxc
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_123", "isPublic":false, "location":{"longitude":133.4354, "latitude":93.5643}, "owner":{"email":"qwe@com", "password":"asd"}, "tags":["restaurant", "cinema"]}' -u qwe@com:asd
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "isPublic":false, "location":{"longitude":55.4, "latitude":6.4242}, "owner":{"email":"asd@com", "password":"zxc"}, "tags":["stadium"]}' -u asd@com:zxc	
## Public Place (without owner)
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "isPublic":true, "location":{"longitude":34.5654, "latitude":44.435}, "tags":["cinema"]}' -u qwe@com:asd

# Searching with authorization
curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=89" -u asd@com:zxc

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=89&cat=EVENT&cat=PLACE&cat=PERSON" -u qwe@com:zxc

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=29&cat=PERSON&cat=EVENT&cat=PLACE&tag=stadium&tag=concert&tag=grandmother&tag=movie" -u asd@com:zxc

# Searching without authorization
curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=89" 

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=89&cat=EVENT&cat=PLACE&cat=PERSON"

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=29&cat=PERSON&cat=EVENT&cat=PLACE&tag=stadium&tag=concert&tag=grandmother&tag=movie"