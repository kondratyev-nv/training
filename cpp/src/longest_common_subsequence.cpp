/**
 * Compute the longest common subsequence of two or three sequences.
 */

#include "longest_common_subsequence.hpp"

#include <unordered_map>

using namespace std;

int longest_common_subsequence(vector<int> const& x,
                               vector<int> const& y,
                               int xi,
                               int yi,
                               unordered_map<int, unordered_map<int, int>>& cache) {
    if (xi < 0 || yi < 0) {
        return 0;
    }
    if (cache.find(xi) != cache.end() && cache[xi].find(yi) != cache[xi].end()) {
        return cache[xi][yi];
    }
    if (x[xi] == y[yi]) {
        cache[xi][yi] = 1 + longest_common_subsequence(x, y, xi - 1, yi - 1, cache);
    } else {
        cache[xi][yi] = max(longest_common_subsequence(x, y, xi - 1, yi, cache),
                            longest_common_subsequence(x, y, xi, yi - 1, cache));
    }
    return cache[xi][yi];
}

int longest_common_subsequence(vector<int> const& x,
                               vector<int> const& y,
                               vector<int> const& z,
                               int xi,
                               int yi,
                               int zi,
                               unordered_map<int, unordered_map<int, unordered_map<int, int>>>& cache) {
    if (xi < 0 || yi < 0 || zi < 0) {
        return 0;
    }
    if (cache.find(xi) != cache.end() && cache[xi].find(yi) != cache[xi].end() &&
        cache[xi][yi].find(zi) != cache[xi][yi].end()) {
        return cache[xi][yi][zi];
    }
    if (x[xi] == y[yi] && y[yi] == z[zi]) {
        cache[xi][yi][zi] = 1 + longest_common_subsequence(x, y, z, xi - 1, yi - 1, zi - 1, cache);
    } else {
        cache[xi][yi][zi] = max(max(longest_common_subsequence(x, y, z, xi - 1, yi, zi, cache),
                                    longest_common_subsequence(x, y, z, xi, yi - 1, zi, cache)),
                                longest_common_subsequence(x, y, z, xi, yi, zi - 1, cache));
    }
    return cache[xi][yi][zi];
}

vector<int> longest_common_subsequence(vector<int> const& x, vector<int> const& y) {
    unordered_map<int, unordered_map<int, int>> cache;
    int max_length = longest_common_subsequence(x, y, x.size() - 1, y.size() - 1, cache);

    vector<int> lcs(max_length);
    int xi = x.size() - 1, yi = y.size() - 1;
    int lcsi = cache[xi][yi];
    while (xi >= 0 && yi >= 0) {
        if (x[xi] == y[yi]) {
            lcs[lcsi - 1] = x[xi];
            xi--;
            yi--;
            lcsi--;
        } else if (cache[xi - 1][yi] > cache[xi][yi - 1]) {
            xi--;
        } else {
            yi--;
        }
    }
    return lcs;
}

vector<int> longest_common_subsequence(std::vector<int> const& x,
                                       std::vector<int> const& y,
                                       std::vector<int> const& z) {
    unordered_map<int, unordered_map<int, unordered_map<int, int>>> cache;
    int max_length = longest_common_subsequence(x, y, z, x.size() - 1, y.size() - 1, z.size() - 1, cache);

    vector<int> lcs(max_length);
    int xi = x.size() - 1, yi = y.size() - 1, zi = z.size() - 1;
    int lcsi = cache[xi][yi][zi];
    while (xi >= 0 && yi >= 0 && zi >= 0) {
        if (x[xi] == y[yi] && y[yi] == z[zi]) {
            lcs[lcsi - 1] = x[xi];
            xi--;
            yi--;
            zi--;
            lcsi--;
        } else if (cache[xi - 1][yi][zi] >= cache[xi][yi - 1][zi] && cache[xi - 1][yi][zi] >= cache[xi][yi][zi - 1]) {
            xi--;
        } else if (cache[xi][yi - 1][zi] >= cache[xi - 1][yi][zi] && cache[xi][yi - 1][zi] >= cache[xi][yi][zi - 1]) {
            yi--;
        } else {
            zi--;
        }
    }
    return lcs;
}
