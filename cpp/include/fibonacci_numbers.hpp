#ifndef FIBONACCI_NUMBERS_HPP
#define FIBONACCI_NUMBERS_HPP

long long fibonacci_number(long long n);

long long fibonacci_number_modulo(long long n, long long m);

long long fibonacci_numbers_sum_modulo(long long n, long long m);

long long fibonacci_numbers_partial_sum_modulo(long long from,
                                               long long to,
                                               long long m);

#endif  // FIBONACCI_NUMBERS_HPP
