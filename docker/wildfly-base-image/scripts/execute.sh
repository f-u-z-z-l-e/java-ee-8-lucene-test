#!/bin/bash

JBOSS_HOME=/opt/jboss/wildfly
JBOSS_CLI=$JBOSS_HOME/bin/jboss-cli.sh

function wait_for_server() {
  until `$JBOSS_CLI -c "ls /deployment" &> /dev/null`; do
    sleep 1
  done
}

echo "=> Starting WildFly server"
$JBOSS_HOME/bin/standalone.sh -c standalone-ha.xml > /dev/null &

echo "=> Waiting for the server to boot"
wait_for_server

echo "=> Add admin user"
$JBOSS_HOME/bin/add-user.sh admin admin77

echo "=> Executing the commands"
$JBOSS_CLI -c --file=`dirname "$0"`/commands.cli

echo "=> Shutting down WildFly"
  $JBOSS_CLI -c ":shutdown"