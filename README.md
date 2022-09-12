# quarkus-mongodb-dns-issue-reproducer-27627

Steps to reproduce:
- Make sure Docker is up and running
- Using quarkus-cli, run 'quarkus dev' on project directory
- From the web browser access 'localhost:8080'
- observe the error

Apply the workaround
- From the org.acme.TenantConfigResolver.java, uncomment line#27
- Restart the application and access localhost:8080
* Make sure to set correct mongodb+srv connection string in the application.properties