#!/bin/bash
docker stop corp-api
docker rm corp-api
docker stop corp-api2
docker rm corp-api2
docker rmi -f luis/api_oidc:1.0
export group=luis
export name=api_oidc
export calculatedVersion=1.0
export SONAR_URL=1
export USER=2
export PASSWD_INTEGRACAO=3
export ref=4
export NEXUS_URL=https://nexus.luis.com.br
export nexusPath=6

./gradlew clean build
#buildDocker
#-Djavax.net.ssl.trustStore=<JKS FILE> -Djavax.net.ssl.trustStorePassword=<JKS FILE PASS>
#sudo docker system prune
docker run --name corp-api --restart=unless-stopped -d -p 8974:8999  --net=keycloak  -e "TZ=America/Sao_Paulo"  luis/api_oidc:1.0
docker run --name corp-api2 --restart=unless-stopped -d -p 8975:8999  --net=keycloak  -e "TZ=America/Sao_Paulo"  luis/api_oidc:1.0
docker logs -f corp-api

