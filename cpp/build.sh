#!/bin/sh

mkdir -p build && \
  cd build && \
    cmake .. && \
      make && \
        ./training_test
