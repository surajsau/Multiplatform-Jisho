#!/bin/bash

# Usage: ./build_kmm_framework.sh BUILD(debug/release) CONFIGURATION(dev/prod)

SCRIPT_PATH=${0}
MODULE=${1}
BUILD=${2}

#cd "$(dirname $SCRIPT_PATH)/../../"
./gradlew ":$MODULE:assembleXCFramework"

mkdir -p "iosApp/build"
cp -r "$MODULE/build/XCFrameworks/$BUILD/$MODULE.xcframework" "iosApp/build"
