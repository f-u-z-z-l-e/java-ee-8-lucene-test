FROM jboss/wildfly:18.0.1.Final

ADD scripts /opt/jboss/wildfly/customization/
ADD modules /opt/jboss/wildfly/modules/

#ADD lucenetest.war /opt/jboss/wildfly/standalone/deployments/

RUN /opt/jboss/wildfly/customization/execute.sh

