"""
The member states of the UN are planning to send two people to the Moon. 
But there is a problem. In line with their principles of global unity, 
they want to pair astronauts of two different countries.
There are N trained astronauts numbered from 0 to N - 1. 
But those in charge of the mission did not receive information 
about the citizenship of each astronaut. The only information they have is 
that some particular pairs of astronauts belong to the same country.
Your task is to compute in how many ways they can pick a pair of astronauts 
belonging to different countries. Assume that you are provided enough pairs 
to let you identify the groups of astronauts even though you might not know their country directly. 
For instance, if 1, 2, 3 are astronauts from the same country; it is sufficient to mention 
that (1, 2) and (2, 3) are pairs of astronauts from the same country without 
providing information about a third pair (1, 3).
"""

from src.graph import UndirectedGraph
from src.vertices_to_components import vertices_to_components


def count_possible_astronaut_pairs(total_astronauts, astronauts_connections):
    """
    Get number of possible astronaut pairs where both of them are 
    from different countries
    """
    graph = UndirectedGraph()
    for astronaut in range(0, total_astronauts):
        graph.add_vertex(astronaut)
    for connection in astronauts_connections:
        graph.add_edge(connection[0], connection[1], 0)
    country_by_astronaut = vertices_to_components(graph)
    count_by_country = {}
    for astronaut, country in country_by_astronaut.items():
        count_by_country[country] = count_by_country.get(country, 0) + 1
    total = 0
    pairs = 0
    for country, count in count_by_country.items():
        pairs += total * count
        total += count
    return pairs
