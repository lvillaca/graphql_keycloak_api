**A secure GraphQL API for data consumption**

This API presents several features:
* graphql (allows a call with a subset of possible attributes customized by the requester, which may optimize data traffic;
* horizontal scalability - by increasing # of stateless microservices, with built-in application server (tomcat);
* distributed cache, via infinispan - all microservices have access to this cache (enables access, with local proximity, to recent data);
* loadbalancer in accessing the data source with hikari pools (2 pools);
* integration with the Keycloak for security (access to API is available after pulling an access token, by calling the IDP).


Follow the guidelines below:

1. Make changes to the infinispan script - edit docker_run_infinispan.sh, include a username and password
2. Run docker_run_infinispan.sh script, check the logs. If OK, exit with Ctrl-P-Q - DO NOT execute CTRL-C to exit the console, this will abort the process;
3. Now update the src/main/resources/application.yml file with the infinispan password and database connection credentials (2 users);
4. Run the build.sh script, analyze the results of the build, test and deployment. Two containers are created.
5. This application relates to a realm. Client (API consumers) need to be added to the same realm
6. Those clients can be configured with openid-connect/confidential settings, where "Service Accounts" and "Authorization Options" should be enabled


