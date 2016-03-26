# Docker

## Work with

List images:

    docker images

List containers: 

    docker ps

## Clean up

Remove all stoped containers 

    docker rm $(docker ps -a -q)

Remove all untaged images

    docker rmi $(docker images | grep "^<none>" | awk "{print $3}") > /dev/null
