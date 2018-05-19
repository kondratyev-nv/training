#!/bin/sh

./build.sh && \
  lcov --capture --rc lcov_branch_coverage=1 --directory ./ --output-file ./build/code_coverage.info && \
    lcov --remove ./build/code_coverage.info "/usr/*" "$(pwd)/build/*" "$(pwd)/test/*" --rc lcov_branch_coverage=1 --output-file ./build/code_coverage.info && \
      genhtml -o ./build/coverage_html_report --rc genhtml_branch_coverage=1 ./build/code_coverage.info && \
        lcov --list ./build/code_coverage.info --rc lcov_branch_coverage=1
