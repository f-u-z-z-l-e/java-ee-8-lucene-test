FROM jboss/wildfly:18.0.1.Final

ADD scripts /opt/jboss/wildfly/customization/
ADD modules /opt/jboss/wildfly/modules/

USER root
# create mount point for shared index directory
RUN mkdir -p /mnt/master-index
RUN chown jboss:jboss /mnt/master-index

# create directory for local index
RUN mkdir -p /local-index
RUN chown jboss:jboss /local-index

# switch back to jboss user
USER jboss

RUN /opt/jboss/wildfly/customization/execute.sh

