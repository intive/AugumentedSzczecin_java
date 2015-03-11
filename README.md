Patronage2015
=============

## Simple Dropwizard - guice - mongodb integration api
requires java 8

## Install dropwizard-guice-hubspot to local maven repository

```
> git clone https://github.com/mlotysz/dropwizard-guice-hubspot.git
> cd dropwizard-guice-hubspot
> mvn -T4 clean install
```

if you run across any problems with gpg, replace configuration of maven-gpg-plugin with "skip":
```
   <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-gpg-plugin</artifactId>
                <version>1.4</version>
                <configuration>
                    <skip>true</skip>
                </configuration>
                <executions>
                    <execution>
                        <id>sign-artifacts</id>
                        <phase>verify</phase>
                        <goals>
                            <goal>sign</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
```


### MongoDB = 2.6.8
```
wget https://fastdl.mongodb.org/linux/mongodb-linux-x86_64-2.6.8.tgz
```

#### How to run mongo:
- create some db folder for example - mkdir db
- run mongod with dppath to folder You just created and port
```
*pathToMongo*/mongod --dbpath *pathToFolder*/db --port 9090
```

### How to run api:
- package (maven also with java8)
```
mvn clean package
```
-- run (java8!)
```
java -jar target/augmented.jar server config.yml
```
