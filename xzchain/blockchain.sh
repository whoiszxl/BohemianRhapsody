#!/bin/bash

set -e

# Check if the jar has been built.
if [ ! -e target/xzchain-1.0-SNAPSHOT.jar ]; then
  echo "Compiling blockchain project to a JAR"
  mvn package -DskipTests
fi

java -jar target/xzchain-1.0-SNAPSHOT.jar "$@"