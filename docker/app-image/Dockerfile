FROM wildfly-config-cli:latest

ARG ARTIFACT_FILE
COPY "${ARTIFACT_FILE}" /opt/jboss/wildfly/standalone/deployments/app.war

CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-c", "standalone-ha.xml", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]