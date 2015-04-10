# Augmented Szczecin Java back-end

Prerequisites:
- install: JDK 1.8 [Oracle jdk download link]
- install: Maven 3.x [Maven download link]
- install: GPG [GPG download link], configuration: [GPG on windows configuration help]
- install and setup database: [mongo] TODO or any hibernate RDBMS

## Install dropwizard-guice-hubspot to local maven repository (once)

        git clone https://github.com/mlotysz/dropwizard-guice-hubspot.git
        cd dropwizard-guice-hubspot
        mvn -T4 clean install

## Building backend with maven

        mvn -T4 clean package

## Running backend

* mongodb as backend db

        (cd api && java -DDBTYPE=mongodb -jar target/api-1.0.0-SNAPSHOT.jar server augmented.yml)

* To setup H2 (and hibernate JPA implementation)

        (cd api && java -DDBTYPE=rdbms -jar target/api-1.0.0-SNAPSHOT.jar db migrate augmented.yml)
        
* Run application with H2 in memory db - but note, needs some work
        
## Testing (currently available with mongodb or h2)

* Create new user

        curl -v http://localhost:8000/users -H "Content-Type: application/json" -d '{"email":"asd", "password":"zxc"}'

* Fetch all data

        curl -v http://localhost:8000/pois -u asd:zxc

* Fetch point of interest by id

        curl -v http://localhost:8000/pois/{id} -u asd:zxc

* Add new POI

        curl -v http://localhost:8000/pois \
            -u asd:zxc \
            -H "Content-Type: application/json" \
            -d '{"name":"thug_621",\
                "tag":"GYM",\
                "location":{"latitude":966021550188765432,\
                          "longitude":392425644375222609}\
                }'

* Fetch all users

        curl -v http://localhost:8000/users -u asd:zxc
		
* Add new place

		curl -v http://localhost:8000/places -H "Content-Type: application/json" -d '{"name":"thug_765", "location":{"longitude":56.56, "latitude":67.89}}' -u asd:zxc
		
* Fetch all places 

		curl -v http://localhost:8000/places
	
* Fetch one place 	
		
		curl -v http://localhost:8000/places/{id} -u asd:zxc
		
* Update place

		curl -XPUT http://localhost:8000/places/{id} -u asd:zxc
		
* Delete place

		curl -XDELETE http://localhost:8000/places/{id} -u asd:zxc
		
* Find other places by specified radius and location of interested place 

		curl -v http://localhost:8000/places/q/q?"lg=57.45&lt=87.9887&radius=8900000"

[Oracle jdk download link]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Maven download link]: http://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache
[GPG download link]: https://www.gnupg.org/download/
[GPG on windows configuration help]: https://virgo47.wordpress.com/2014/08/09/releasing-to-maven-central-with-git-on-windows/
[mongo]: http://docs.mongodb.org/manual/installation/
