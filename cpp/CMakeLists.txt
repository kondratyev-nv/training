project(training)

cmake_minimum_required(VERSION 3.2)

find_package(Threads REQUIRED)
set(CMAKE_CXX_FLAGS "-std=c++14 -Wall -pedantic -fno-inline -pthread ${CMAKE_CXX_FLAGS}")
set(CMAKE_MODULE_PATH ${PROJECT_SOURCE_DIR}/cmake/modules/)

#-------------------
# set common include folder for module
#-------------------
set(COMMON_INCLUDES ${PROJECT_SOURCE_DIR}/include)
set(LIB_PROJECTS_DIR ${PROJECT_SOURCE_DIR}/lib)

#-------------------
# Module source
#-------------------
file(GLOB SRC_FILES ${PROJECT_SOURCE_DIR}/src/*.cpp)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

#-------------------
# Test
#-------------------
enable_testing()
add_subdirectory(${LIB_PROJECTS_DIR}/gtest)
set(PROJECT_TEST_NAME ${PROJECT_NAME}_test)
include_directories(${GTEST_INCLUDE_DIRS} ${COMMON_INCLUDES})

include(CodeCoverage)
APPEND_COVERAGE_COMPILER_FLAGS()
file(GLOB TEST_SRC_FILES ${PROJECT_SOURCE_DIR}/test/*.cpp)
add_executable(${PROJECT_TEST_NAME} ${TEST_SRC_FILES} ${SRC_FILES})
add_dependencies(${PROJECT_TEST_NAME} googletest)

if (NOT MINGW)
    target_link_libraries(${PROJECT_TEST_NAME}
            ${GTEST_LIBS_DIR}/libgtest.a
            ${GTEST_LIBS_DIR}/libgtest_main.a
            ${CMAKE_THREAD_LIBS_INIT}
    )
else ()
    target_link_libraries(${PROJECT_TEST_NAME}
            debug ${GTEST_LIBS_DIR}/DebugLibs/${CMAKE_FIND_LIBRARY_PREFIXES}gtest${CMAKE_FIND_LIBRARY_SUFFIXES}
            optimized ${GTEST_LIBS_DIR}/ReleaseLibs/${CMAKE_FIND_LIBRARY_PREFIXES}gtest${CMAKE_FIND_LIBRARY_SUFFIXES}
    )
    target_link_libraries(${PROJECT_TEST_NAME}
            debug ${GTEST_LIBS_DIR}/DebugLibs/${CMAKE_FIND_LIBRARY_PREFIXES}gtest_main${CMAKE_FIND_LIBRARY_SUFFIXES}
            optimized ${GTEST_LIBS_DIR}/ReleaseLibs/${CMAKE_FIND_LIBRARY_PREFIXES}gtest_main${CMAKE_FIND_LIBRARY_SUFFIXES}
    )
endif ()

add_test(test ${PROJECT_TEST_NAME})
