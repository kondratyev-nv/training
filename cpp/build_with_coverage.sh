#!/bin/sh

./build.sh && \
  lcov --capture --directory ./ --output-file ./code_coverage.info -rc lcov_branch_coverage=1 && \
    lcov --remove code_coverage.info '/usr/*' 'build/*' 'test/*' --output-file code_coverage.info && \
      lcov --list code_coverage.info
