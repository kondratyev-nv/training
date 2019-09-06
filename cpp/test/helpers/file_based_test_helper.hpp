
#include <experimental/filesystem>
#include <fstream>
#include <functional>

namespace file_based_test_helper {

using namespace std;
namespace fs = experimental::filesystem;

template <typename T>
T read_test_resource(fs::path const& test_path, function<T(ifstream&)> const& reader) {
    auto path = fs::current_path() / "test_resources" / test_path;
    ifstream input(path);
    if (!input) {
        throw std::invalid_argument("can not open file " + path.string());
    }

    return reader(input);
}

}  // namespace file_based_test_helper
