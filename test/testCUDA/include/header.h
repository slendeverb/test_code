#pragma once

#include "my_utils.h"

#include <iostream>
#include <unordered_set>
#include <execution>
#include <random>

std::random_device rd{};
std::mt19937_64 gen{rd()};

#include <thrust/universal_vector.h>
#include <thrust/device_vector.h>
#include <thrust/host_vector.h>

#include <opencv2/opencv.hpp>

#include <tbb/tick_count.h>
#define TICK(x) auto bench_##x = tbb::tick_count::now();
#define TOCK(x) \
std::cout << #x ": " << (tbb::tick_count::now() - bench_##x).seconds() << "s" << std::endl;

#include "backward.hpp"
namespace backward {
    backward::SignalHandling sh;
}