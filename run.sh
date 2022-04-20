#!/usr/bin/env bash

./gradlew bootBuildImage --imageName=samtipton/the-book-api
docker-compose up -d
