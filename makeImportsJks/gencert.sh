#echo -n | openssl s_client -connect nexus.blah.com.br:443 \
#    | sed -ne '/-BEGIN CERTIFICATE-/,/-END CERTIFICATE-/p' > nexus.cert


#gera keycloak jks
#keytool -import -trustcacerts -file nexus.cert -alias nexus -keystore <JKS FILE> -storepass <JKS FILE PASS> -noprompt
keytool -import -trustcacerts -file repo_maven_apache_org.crt -alias maven -keystore <JKS FILE> -storepass <JKS FILE PASS> -noprompt


