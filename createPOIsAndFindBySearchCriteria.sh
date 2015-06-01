#!/bin/bash

# Creating new user
curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"asd@com", "password":"zxc"}'
# Creating another user
curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"qwe@com", "password":"asd"}'

read -p "Press [Enter] key to continue..."

# Creating events
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug777", "description":"BLStream Szczecin", "location":{"longitude":87.4644, "latitude":82.4454}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560bab5a1664e30172260d0", "email":"asd@com", "password":"zxc"}, "tags":["concert", "movie", "game"]}' -u asd@com:zxc
## Public Event (without owner)
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug111", "description":"BLStream Szczecin", "location":{"longitude":52.3453, "latitude":75.4242}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "tags":["concert"]}' -u qwe@com:asd
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug555", "description":"BLStream Szczecin", "location":{"longitude":34.4535, "latitude":86.4643}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560babfa1664e30172260d1", "email":"qwe@com", "password":"asd"}, "tags":["movie", "game"]}' -u qwe@com:asd	
curl -v http://localhost:8000/events -H "Content-Type: application/json" -d '{"name":"thug7876", "description":"BLStream Szczecin", "location":{"longitude":111.1313, "latitude":68.242}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560bab5a1664e30172260d0", "email":"asd@com", "password":"zxc"}, "tags":["game", "play", "enjoy"]}' -u asd@com:zxc	

read -p "Press [Enter] key to continue..."

# Creating people
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug234", "description":"BLStream Szczecin", "location":{"longitude":12.4564, "latitude":4.535}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560babfa1664e30172260d1", "email":"qwe@com", "password":"asd"}, "tags":["friend", "grandmother"]}' -u qwe@com:asd
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug9876", "description":"BLStream Szczecin", "location":{"longitude":46.6768, "latitude":84.3454}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560bab5a1664e30172260d0", "email":"asd@com", "password":"zxc"}, "tags":["friend", "teacher", "mother"]}' -u asd@com:zxc
## Public Person (without owner)
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug1", "description":"BLStream Szczecin", "location":{"longitude":124.242, "latitude":45.3535}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "tags":["friend"]}' -u asd@com:zxc	
curl -v http://localhost:8000/people -H "Content-Type: application/json" -d '{"name":"thug290", "description":"BLStream Szczecin", "location":{"longitude":45.4543, "latitude":43.4342}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560babfa1664e30172260d1", "email":"qwe@com", "password":"asd"}, "tags":["grandmother", "teacher"]}' -u qwe@com:asd

read -p "Press [Enter] key to continue..."

# Creating places
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug765", "description":"BLStream Szczecin", "location":{"longitude":56.56, "latitude":67.89}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560bab5a1664e30172260d0", "email":"asd@com", "password":"zxc"}, "tags":["restaurant", "cinema", "stadium"], "subcategory":"COMMERCIAL"}' -u asd@com:zxc
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug123", "description":"BLStream Szczecin", "location":{"longitude":133.4354, "latitude":43.5643}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560babfa1664e30172260d1", "email":"qwe@com", "password":"asd"}, "tags":["restaurant", "cinema"], "subcategory":"HOSPITAL"}' -u qwe@com:asd
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug765", "description":"BLStream Szczecin", "location":{"longitude":55.4, "latitude":6.4242}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "owner":{"id": "5560bab5a1664e30172260d0", "email":"asd@com", "password":"zxc"}, "tags":["stadium", "restaurant", "hotel"], "subcategory":"OFFICE"}' -u asd@com:zxc	
## Public Place (without owner)
curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug765", "description":"BLStream Szczecin", "location":{"longitude":34.5654, "latitude":44.435}, "address":{"city":"Szczecin", "street":"Plac Holdu Pruskiego", "zipcode":"70-550", "streetNumber":"9"}, "tags":["cinema"], "subcategory":"TRAIN_STATION"}' -u qwe@com:asd

read -p "Press [Enter] key to continue..."

# Searching with authorization
curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=89" -u asd@com:zxc

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=890000000&cat=EVENT&cat=PLACE&cat=PERSON" -u qwe@com:zxc

curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=290000000&cat=PERSON&cat=EVENT&cat=PLACE&tag=stadium&tag=concert&tag=grandmother&tag=movie&page=0" -u asd@com:zxc
