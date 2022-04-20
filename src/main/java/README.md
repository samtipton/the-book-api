#The Book API

##Getting local environment started:

##Spring Application
To start application run `$ ./run.sh`, grant execution privilege if necessary.

This script will build a new image for this spring boot application and then call `docker-compose up -d`.

##Database
The mariadb database for scripture is a docker image built from the repository found [here]( 
https://github.com/samtipton/bible_databases) and hosted on docker hub [here](https://hub.docker.com/r/samtipton/bible-mysql/tags)

Then Navigate to localhost:8080/graphiql


