cd api && java -DDBTYPE=rdbms -jar target/api-1.0.0-SNAPSHOT.jar db migrate augmented.yml
pause
java -DDBTYPE=rdbms -jar target/api-1.0.0-SNAPSHOT.jar server augmented.yml