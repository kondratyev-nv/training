project(training)

cmake_minimum_required(VERSION 3.9)

find_package(Threads REQUIRED)
set(CMAKE_CXX_STANDARD 14)
add_definitions(-ansi -pedantic -Wall -Wno-deprecated -fno-inline -pthread)
set(CMAKE_MODULE_PATH ${PROJECT_SOURCE_DIR}/cmake/modules/)

#-------------------
# set common include folder for module
#-------------------
set(COMMON_INCLUDES ${PROJECT_SOURCE_DIR}/include)
set(LIB_PROJECTS_DIR ${PROJECT_SOURCE_DIR}/thirdparty)

#-------------------
# Module source
#-------------------
file(GLOB SRC_FILES ${PROJECT_SOURCE_DIR}/src/*.cpp)

set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

#-------------------
# Test
#-------------------
enable_testing()
set(PROJECT_TEST_NAME ${PROJECT_NAME}_test)

add_subdirectory(${LIB_PROJECTS_DIR}/googletest)
include_directories(${GTEST_INCLUDE_DIRS} ${COMMON_INCLUDES})

file(GLOB TEST_SRC_FILES ${PROJECT_SOURCE_DIR}/test/*.cpp)
add_executable(${PROJECT_TEST_NAME} ${TEST_SRC_FILES} ${SRC_FILES})

add_dependencies(${PROJECT_TEST_NAME} googletest)
target_link_libraries(${PROJECT_TEST_NAME}
    ${GTEST_LIBS_DIR}/${CMAKE_FIND_LIBRARY_PREFIXES}gtest.a
    ${GTEST_LIBS_DIR}/${CMAKE_FIND_LIBRARY_PREFIXES}gtest_main.a
    pthread)
if(CMAKE_CXX_COMPILER_ID STREQUAL "GNU" AND NOT MINGW)
    target_link_libraries(${PROJECT_TEST_NAME} stdc++fs)
endif()

add_custom_target(copy_test_resources ALL
    COMMAND ${CMAKE_COMMAND} -E copy_directory
    ${PROJECT_SOURCE_DIR}/test/test_resources
    ${CMAKE_BINARY_DIR}/test_resources)

add_dependencies(${PROJECT_TEST_NAME} copy_test_resources)

include(GoogleTest)
gtest_add_tests(TARGET ${PROJECT_TEST_NAME})

include(CodeCoverage)
append_coverage_compiler_flags()
setup_target_for_coverage_gcovr_xml(
    NAME ${PROJECT_TEST_NAME}_coverage
    EXECUTABLE ${PROJECT_TEST_NAME}
    BASE_DIRECTORY "${PROJECT_SOURCE_DIR}/src"
    EXCLUDE "${PROJECT_SOURCE_DIR}/build" "${PROJECT_SOURCE_DIR}/test")
