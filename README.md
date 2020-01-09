# Demo Hibernate Search with JGroups

This demo shall demonstrate hibernate search configured with jgroups (master/slave).

## Steps to Run the demo in docker

First build the master and slave artefacts
* ```mvn clean install -P master```
* ```mvn clean install -P slave```

Then create the custom wildfly docker image (jdbc driver & datasource configuration)
* ```cd docker/wildfly-base-image```
* ```docker build --rm --tag wildfly-config-cli .```

Create the application docker images
* ```cd ../app-image```
* ```docker build --rm --build-arg ARTIFACT_FILE=app-master.war --tag app-master .```
* ```docker build --rm --build-arg ARTIFACT_FILE=app-slave.war --tag app-slave .```

Use docker-compose to start the db & the two app instances
* ```cd ../../docker-compose```
* ```docker-compose up -d ```

Check if all services started successfully
* ```docker-compose logs -f```

Use the provided postman collection and environment to test the application
* create person
* find person by firstname
* get person

Remove all docker related stuff from your machine
* ```docker-compose down --rmi all -v```
