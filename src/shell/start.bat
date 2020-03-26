@ECHO OFF
REM Starting the application

SET USER=user
SET PASSWORD=pass

REM SET JAVA_HOME=...
REM SET PATH=%JAVA_HOME%\bin;%PATH%

ECHO Starting application
java -Dspring.security.user.name=%USER% -Dspring.security.user.password=%PASSWORD% -jar webservicemockup.jar
