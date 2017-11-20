#include "surface_area.hpp"
#include <cstdlib>

using namespace std;

/**
 * Madison, is a little girl who is fond of toys. 
 * Her friend Mason works in a toy manufacturing factory. 
 * Mason has a 2D board A of size H x W with H rows and W columns. 
 * The board is divided into cells of size 1 x 1 with each cell indicated 
 * by it's coordinate (i, j). The cell (i, j) has an integer A_ij written on it. 
 * To create the toy Mason stacks A_ij number of cubes of size 1 x 1 x 1 on the cell (i, j).
 * Given the description of the board showing the values of A_ij and 
 * that the price of the toy is equal to the 3D surface area find the price of the toy.
 */
long long surface_area(const std::vector<std::vector<int>>& figure) {
  if (figure.empty() || figure[0].empty()) {
    return 0;
  }
  size_t n = figure.size(), m = figure[0].size();
  long long area = 2 * n * m;
  for (size_t i = 0; i < n; ++i) {
    area += figure[i][0];
  }
  for (size_t i = 0; i < n; ++i) {
    area += figure[i][m - 1];
  }
  for (size_t j = 0; j < m; ++j) {
    area += figure[0][j];
  }
  for (size_t j = 0; j < m; ++j) {
    area += figure[n - 1][j];
  }
  for (size_t i = 0; i < n; ++i) {
    for (size_t j = 1; j < m; ++j) {
      area += abs(figure[i][j] - figure[i][j - 1]);
    }
  }
  for (size_t j = 0; j < m; ++j) {
    for (size_t i = 1; i < n; ++i) {
      area += abs(figure[i][j] - figure[i - 1][j]);
    }
  }
  return area;
}
