FROM openjdk:8-jdk-alpine

ADD build/resources/main/certs/keycloak_client.jks oauth_client.jks
ADD build/libs/api_oidc-1.0.jar app.jar

ENV JAVA_OPTS="-Xss2048k  -Xms1024m -Xmx1024m -XX:ReservedCodeCacheSize=240m -Djavax.net.ssl.trustStore=/oauth_client.jks -Djavax.net.ssl.trustStoreType=jks -Djavax.net.ssl.trustStorePassword=<password>"

ENTRYPOINT exec java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar
