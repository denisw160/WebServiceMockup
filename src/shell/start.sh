#!/bin/bash
#
# Starting the application
#

USER=user
PASSWORD=pass

echo Starting application
java -Dspring.security.user.name=$USER -Dspring.security.user.password=$PASSWORD -jar webservicemockup.jar
