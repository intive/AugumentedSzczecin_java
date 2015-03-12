# Augmented Szczecin Java back-end

Prerequisites:
- install: JDK 1.8 [Oracle jdk download link]
- install: Maven 3.x [Maven download link]
- install: GPG [GPG download link], configuration: [GPG on windows configuration help]
- install and setup database: [mongo] TODO or any hibernate RDBMS

## Install dropwizard-guice-hubspot to local maven repository (once)

```
git clone https://github.com/mlotysz/dropwizard-guice-hubspot.git
cd dropwizard-guice-hubspot
mvn -T4 clean install
```

## Building backend with maven
```
mvn -T4 clean package
```

## Running backend

- with mongo (version 2.6.8 works ok):
```
java -jar target/augmented.jar server config.yml
```

- TODO with hibernate on H2:
```
java -jar target/augmented.jar server config-rdbms-h2.yml
```
- TODO with hibernate on Postgresql:
```
java -jar target/augmented.jar server config-rdbms-pg.yml
```

[Oracle jdk download link]:http://www.oracle.com/technetwork/java/javase/downloads/index.html
[Maven download link]: http://maven.apache.org/download.cgi?Preferred=ftp://mirror.reverse.net/pub/apache
[GPG download link]: https://www.gnupg.org/download/
[GPG on windows configuration help]: https://virgo47.wordpress.com/2014/08/09/releasing-to-maven-central-with-git-on-windows/
[mongo]: http://docs.mongodb.org/manual/installation/
