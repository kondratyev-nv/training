#!/bin/sh

mkdir -p build && \
cd build && \
cmake .. && \
cmake --build . && \
./training_test

