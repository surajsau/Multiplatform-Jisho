#!/bin/bash

# Usage: ./build_kmm_framework.sh BUILD(debug/release) CONFIGURATION(dev/prod)

SCRIPT_PATH=${0}
BUILD=${1}

#cd "$(dirname $SCRIPT_PATH)/../../"
./gradlew :shared:assembleXCFramework

mkdir -p "iosApp/build"
cp -r "shared/build/XCFrameworks/$BUILD/shared.xcframework" "iosApp/build"