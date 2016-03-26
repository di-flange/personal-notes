# Docker

## Work with

List images:

    docker images

List containers: 

    docker ps

### Run

Run with bash

    docker run -t -i <image> /bin/bash

Run deamon

    docker run -d <image>

Attach to exist

    docker attach <image>

### Build

Build current directory

    docker build -t <image> .

### Repository

Free repository available on `http://hub.docker.com`. For work with it name of images should be `<username>/<repository>`.

Download image 

    docker pull <image>

Upload image 

    docker push <image>

### Clean up

Remove all stoped containers 

    docker rm $(docker ps -a -q)

Remove all untaged images

    docker rmi $(docker images | grep "^<none>" | awk "{print $3}") > /dev/null
    
## Aliases

    alias d-ps="docker ps -all"
    alias d-img="docker images"
    alias d-img-rm="docker rmi"
    alias d-pull="docker pull"
    alias d-push="docker push"
    alias d-build="docker build -t"
