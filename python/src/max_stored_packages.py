"""
You are in charge of storing some of the inventory in a company. 
There are different types of packages. The total number of packages and 
the number of packages of each type is known to you. Also, each package is 
cube-shaped, with edge length given.

Packages can only be stored in cylindrical containers which have a circular 
opening of given radius. Each container can hold a particular number of packages. 
We may assume that each container is tall enough that it can accommodate 
the total height of all the packages.

As part of company policy, you must ensure the following holds:

- Each package can only be kept on top of another package.
- The package must not touch the sides of the cylinder, even at a single point. The packages may touch boundaries with each other or with the base of the cylinder.
- You have to choose which packages go into which containers. Discard packages that can't be stored.

What is the maximum number of packages that can be put in the containers?
"""
from collections import namedtuple
from math import sqrt


class Package:
    def __init__(self, side, count):
        self.side = side
        self.count = count


class Container:
    def __init__(self, radius, count):
        self.radius = radius
        self.count = count


def max_stored_packages(packages, containers):
    packages = sorted(map(lambda p: Package(p.side, p.count),
                          packages),
                      key=lambda p: p.side, reverse=True)
    containers = sorted(map(lambda c: Container(c.radius, c.count),
                            containers),
                        key=lambda c: c.radius, reverse=True)
    total_count = 0
    package_index = 0
    container_index = 0
    while True:
        if package_index >= len(packages):
            break
        if container_index >= len(containers):
            break
        p = packages[package_index]
        c = containers[container_index]
        if p.side * sqrt(2) >= 2 * c.radius:
            package_index += 1
            continue
        if p.count <= 0:
            package_index += 1
            continue
        if c.count <= 0:
            container_index += 1
            continue
        count = min(p.count, c.count)
        total_count += count
        p.count -= count
        c.count -= count

    return total_count
