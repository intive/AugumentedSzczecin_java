# Augmented Szczecin Java back-end

Prerequisites:
- install: JDK 1.8 [Oracle jdk download link]
- install: Maven 3.x [Maven download link]
- install: GPG [GPG download link], configuration: [GPG on windows configuration help]
- install and setup database: [mongo] TODO or any hibernate RDBMS

## Building backend with maven

        mvn -T4 clean package

## Running backend

* mongodb as backend db

		(cd api && java -DDBTYPE=mongodb -jar target/augumented-api.jar server augmented.yml)

## Testing (currently available with mongodb)

* Create new user

		curl -v http://localhost:8000/users \
			-H "Content-Type: application/json" \
			-d '{"email":"asd@com", "password":"zxc"}'

* Confirm user's credentials

        curl -v http://localhost:8000/users/whoami -u asd@com:zxc

* Add new place

		curl -v localhost:8000/places -H "Content-type: application/json" -d '{"name":"BLStream","description":"BLStream Szczecin","location":{"latitude":53.428624,"longitude":14.556482},"address":{"city":"Szczecin","street":"Plac Hołdu Pruskiego","streetNumber":"9","zipcode":"70-550"},"tags":["it","thug"],"www":"http://blstream.com/en/","phone":"914325680","fanpage":"https://www.facebook.com/blstream","media":["http://www.netcamp.pl/wp-content/uploads/2013/02/blstream-logo.png","https://pbs.twimg.com/profile_images/378800000665275518/550d61b582adb462f664c8f33d9cde82_400x400.png"],"opening":[{"day":"MONDAY","open":"08:00","close":"00:00"}],"subcategory":"COMMERCIAL"}' -u asd@com:zxc

* Fetch all places 

		curl -v http://localhost:8000/places

* Fetch one place 	

		curl -v http://localhost:8000/places/{id} -u asd@com:zxc

* Update place

		curl -XPUT http://localhost:8000/places/{id} -u asd@com:zxc

* Delete place

		curl -XDELETE http://localhost:8000/places/{id} -u asd@com:zxc

* Find all known POI types matching search criteria: location and radius

		curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=150" -u asd@com:zxc
		
* Find all known POI types matching search criteria: location and radius with results paging (return 7 nearest POIs)

		curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=150&page=0&pageSize=7" -u asd@com:zxc

* Find all known POI types matching search criteria: location, radius and categories
		
		curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=150&cat=PLACE&cat=EVENT" -u asd@com:zxc
		
* Find all known POI types matching search criteria: location, radius, categories and tags

		curl -v "http://localhost:8000/q?lg=57.45&lt=87.9887&radius=150&cat=PLACE&cat=EVENT&tag=restaurant&tag=stadium" -u asd@com:zxc
		
## Password changing
* Request change password token

		curl -XPOST http://localhost:8000/users/{id}/resetpassword
	Receive email with token, then:

* Change password

		curl -XPUT http://localhost:8000/users/{id}/changepassword/{token} \
			-d "newpassword"

[Oracle jdk download link]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Maven download link]: http://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache
[GPG download link]: https://www.gnupg.org/download/
[GPG on windows configuration help]: https://virgo47.wordpress.com/2014/08/09/releasing-to-maven-central-with-git-on-windows/
[mongo]: http://docs.mongodb.org/manual/installation/
