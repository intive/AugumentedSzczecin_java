cd api && java -DDBTYPE=rdbms -jar target/augumented-api.jar db migrate augmented.yml
pause
java -DDBTYPE=rdbms -jar target/augumented-api.jar server augmented.yml
