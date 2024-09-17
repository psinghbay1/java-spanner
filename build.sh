#!/usr/bin/env bash

git remote add upstream https://github.com/googleapis/java-spanner.git
git pull upstream main --no-rebase

mvn com.coveo:fmt-maven-plugin:format
mvn clean verify
