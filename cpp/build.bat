(if not exist build mkdir build) &&^
cd build &&^
cmake -DCMAKE_BUILD_TYPE=Debug -G "Unix Makefiles" .. &&^
cmake --build . &&^
ctest -VV && cd ..
