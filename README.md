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
        
        (cd api && java -DDBTYPE=rdbms -jar target/api-1.0.0-SNAPSHOT.jar server augmented.yml)

* TODO with hibernate on Postgresql:

        (cd api && java -DDBTYPE=rdbms -jar target/api-1.0.0-SNAPSHOT.jar server augmented-pg.yml)

## Testing (currently available with mongodb or h2)

* Generate some example data

        curl -v http://localhost:8000/poi/generate

* Fetch all data

        curl -v http://localhost:8000/poi

* Fetch entity by id

        curl -v http://localhost:8000/poi/{id}

* Add new POI

        curl -v http://localhost:8000/poi/add \
            -H "Content-Type: application/json" \
            -d '{"name":"thug_621",\
                "tag":"GYM",\
                "location":{"latitude":966021550188765432,\
                          "longitude":392425644375222609}\
                }'

* Create new user

        curl -v http://localhost:8000/user/add -H "Content-Type: application/json" -d '{"email":"asd", "password":"zxc"}'

* Get protected resource

        curl -v http://localhost:8000/auth -u asd:zxc

* Fetch all users [Auth required]

        curl -v http://localhost:8000/user/list

* Clear user list [Auth required]

        curl -v http://localhost:8000/user/clear

* There is some proof of concept for http://open-data.org.pl backend client:

        curl -v http://localhost:8000/poi/open

[Oracle jdk download link]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Maven download link]: http://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache
[GPG download link]: https://www.gnupg.org/download/
[GPG on windows configuration help]: https://virgo47.wordpress.com/2014/08/09/releasing-to-maven-central-with-git-on-windows/
[mongo]: http://docs.mongodb.org/manual/installation/
