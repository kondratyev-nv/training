#!/bin/sh

BUILD_TYPE=${BUILD_TYPE:-"Debug"}

mkdir -p build && \
  cd build && \
    cmake -DCMAKE_BUILD_TYPE=$BUILD_TYPE .. && \
      cmake --build . --config $BUILD_TYPE && \
        ctest -C $BUILD_TYPE --parallel 4 --timeout 10 --output-on-failure
