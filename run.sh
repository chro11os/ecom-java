#!/bin/bash
mvn clean install
java -jar sbecomms/target/sbecomms-1.0-SNAPSHOT.war &
cd ecomms && mvn javafx:run
