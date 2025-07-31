@echo off
REM Build the project
call mvn clean install

REM Start the backend in the background
start "Backend" /B java -jar sbecomms\target\sbecomms-1.0-SNAPSHOT.war

REM Start the frontend
cd ecomms
call mvn javafx:run
