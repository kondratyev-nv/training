(if not exist build mkdir build) &&^
cd build &&^
cmake -DCMAKE_BUILD_TYPE=Debug -G "Unix Makefiles" .. &&^
cmake --build . &&^
ctest --parallel 4 --timeout 10 --output-on-failure &&^
cd ..
