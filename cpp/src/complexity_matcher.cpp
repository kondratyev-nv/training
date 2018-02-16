#include "complexity_matcher.hpp"

#include <algorithm>
#include <chrono>
#include <iostream>
#include <vector>

using namespace std;
using timer = chrono::high_resolution_clock;

double sum(vector<double> const& x) {
    double sum = 0;
    for (size_t index = 0; index < x.size(); ++index) {
        sum += x[index];
    }
    return sum;
}

double sqsum(vector<double> const& x) {
    double sum = 0;
    for (size_t index = 0; index < x.size(); ++index) {
        sum += x[index] * x[index];
    }
    return sum;
}

double dot_product(vector<double> const& x, vector<double> const& y) {
    double p = 0;
    for (size_t index = 0; index < x.size(); ++index) {
        p += x[index] * y[index];
    }
    return p;
}

double norm(vector<double> const& x, vector<double> const& y) {
    double p = 0;
    for (size_t index = 0; index < x.size(); ++index) {
        p += (x[index] - y[index]) * (x[index] - y[index]);
    }
    return sqrt(p);
}

double norm(vector<double> const& x) {
    double p = 0;
    for (size_t index = 0; index < x.size(); ++index) {
        p += x[index] * x[index];
    }
    return sqrt(p);
}

namespace complexity_matcher {
double pearson_correlation_coefficient(vector<double> const& x, vector<double> const& y) {
    double xs = sum(x);
    double ys = sum(y);
    double ms = dot_product(x, y);
    double xss = sqsum(x);
    double yss = sqsum(y);
    double n = x.size();
    return (n * ms - xs * ys) / (sqrt((n * xss - xs * xs)) * sqrt((n * yss - ys * ys)));
}

double correlation_coefficient(vector<double> const& x, vector<double> const& y) {
    double m = dot_product(x, y);
    double xss = sqsum(x), yss = sqsum(y);
    return m / (sqrt(xss) * sqrt(yss));
}

double derivative_strategy(vector<double> const& x, vector<double> const& y, double h) {
    vector<double> dxdy(x.size() - 1);
    for (size_t index = 0; index < x.size() - 1; ++index) {
        double dx = (x[index + 1] - x[index]) / h;
        double dy = (y[index + 1] - y[index]) / h;
        dxdy[index] = dx / dy;
    }
    double average = sum(dxdy) / dxdy.size();
    vector<double> avgdxdy(dxdy.size(), average);
    return norm(dxdy, avgdxdy) / norm(avgdxdy);
}

builder_t builder(std::function<void(size_t)> const& action, std::function<double(size_t)> const& complexity) {
    return builder_t(action, complexity);
}

double match_o(function<double(size_t)> const& ef, function<double(size_t)> const& tf) {
    const size_t n = 100000;
    const size_t from = 1000;
    const size_t to = 10000000;
    const size_t h = (to - from) / n;
    vector<double> e(n), t(n);
    for (size_t i = 1; i <= n; ++i) {
        e[i - 1] = ef(i * h);
        t[i - 1] = tf(i * h);
    }
    return abs(1. - pearson_correlation_coefficient(e, t)) / 2.;
}

match_result match(std::function<void(size_t)> const& action, std::function<double(size_t)> const& complexity) {
    return builder(action, complexity).match();
}

match_result builder_t::match() {
    const size_t h = (max_ - min_) / measurements_;
    vector<double> measured(measurements_), expected(measurements_);
    for (size_t attempt = 0; attempt < retries_; ++attempt) {
        for (size_t i = 1; i <= measurements_; ++i) {
            timer::time_point start = timer::now();
            action_(i * h);
            timer::time_point end = timer::now();
            measured[i - 1] += (end - start).count();
        }
    }
    for (size_t i = 1; i <= measurements_; ++i) {
        expected[i - 1] = complexity_(i * h);
        measured[i - 1] = measured[i - 1] / 10.;
        // cout << i * h << ";" << measured[i - 1] << ";" << expected[i - 1] << endl;
    }
    double match = abs(1. - pearson_correlation_coefficient(measured, expected)) / 2.;
    return {match, match < tolerance_};
}

}  // namespace complexity_matcher
