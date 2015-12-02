#!/bin/bash

./gradlew --settings-file settings-travis.gradle :library:test --info
