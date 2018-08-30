"""
Consider the forest as an N x M grid.
Each cell is either empty (represented by .) or blocked by a tree (represented by X).
You can move LEFT, RIGHT, UP, and DOWN through empty cells,
but you cannot travel through a tree cell. Starting cell is marked
with the character M, and the target cell is marked with a *.
The upper-left corner is indexed as (0, 0).
Find a number of times you are able to move in more than one direction.
"""

from src.graph import UndirectedGraph
from src.shortest_path import dijkstra_path


def count_number_of_road_forks(field):
    """
    Returns number of times you are able to move in more
    than one direction by a given forest

    :param field: Array of strings that represent forest
    :return: number of road forks
    """
    graph = UndirectedGraph()
    start = None
    end = None
    height = len(field)
    width = len(field[0])
    for i, row in enumerate(field):
        for j, symbol in enumerate(row):
            if symbol == 'X':
                continue

            index = __linear_index(i, j, width)
            if symbol == 'M':
                start = index
            if symbol == '*':
                end = index

            if __can_go_to(i - 1, j, height, width):
                if field[i - 1][j] != 'X':
                    neighbor_index = __linear_index(i - 1, j, width)
                    graph.add_edge(neighbor_index, index, 1)

            if __can_go_to(i, j - 1, height, width):
                if field[i][j - 1] != 'X':
                    neighbor_index = __linear_index(i, j - 1, width)
                    graph.add_edge(neighbor_index, index, 1)

    path = dijkstra_path(graph, start, end)
    fork_count = 1 if len(graph.get_edges(start)) > 1 else 0
    for vertex in path[1:-1]:
        if len(graph.get_edges(vertex)) - 1 > 1:
            fork_count += 1
    return fork_count


def __linear_index(i, j, width):
    return i * width + j


def __can_go_to(i, j, width, height):
    return 0 <= i < width and 0 <= j < height
