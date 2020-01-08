# Wildfly Setup
## Wildfly Installation
Download the latest version of Wildfly from [official Wildfly Website](https://wildfly.org/downloads/) and unpack it.
## Wildfly DataSource Configuration
### PostgreSQL JDBC Driver Installation & DataSource Configuration
Download the [PostgreSQL JDBC Driver](https://repo1.maven.org/maven2/org/postgresql/postgresql/42.2.9/postgresql-42.2.9-dist.tar.gz)
to your Downloads directory.

start wildfly locally with

* **$WILDFLY_HOME/bin/standalone.sh**
* **$WILDFLY_HOME/bin/jboss-cli.sh --connect controller=127.0.0.1**
* ```module add --name=org.postgresql --resources=~/Downloads/postgresql-42.2.9.jar --dependencies=javax.api,javax.transaction.api```
* ```/subsystem=datasources/jdbc-driver=postgres:add(driver-name="postgres",driver-module-name="org.postgresql",driver-class-name=org.postgresql.Driver)```
* ```data-source add --jndi-name=java:jboss/datasources/postgresDS --name=postgresDS --connection-url=jdbc:postgresql://172.24.0.10:5432/testcasedb --driver-name=postgres --user-name=testcasedbuser --password=testcasedbpassword```