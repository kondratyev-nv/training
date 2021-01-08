project(googletest C CXX)

find_package(Threads REQUIRED)

include(ExternalProject)
ExternalProject_Add(googletest
        GIT_REPOSITORY  https://github.com/google/googletest.git
        GIT_TAG         release-1.10.0
        PREFIX          "${CMAKE_CURRENT_BINARY_DIR}"
        INSTALL_COMMAND "")
ExternalProject_Get_Property(googletest source_dir binary_dir)

set(GTEST_INCLUDE_DIRS ${source_dir}/googletest/include ${source_dir}/googlemock/include PARENT_SCOPE)
set(GTEST_LIBS_DIR ${binary_dir}/lib PARENT_SCOPE)
