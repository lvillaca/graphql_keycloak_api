
######################################################################################################


#sudo  docker pull jboss/infinispan-server:10.1

sudo docker run  -v $(pwd)/infinispan:/user-config -e CONFIG_PATH="/user-config/tcpping.yml"  -e USER=<infinispanuser> -e PASS=<infinispanpass> --name infini001 --restart unless-stopped  --network keycloak -it jboss/infinispan-server:10.1
