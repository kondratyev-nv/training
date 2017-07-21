"""
Consider the forest as an N x M grid.
Each cell is either empty (represented by .) or blocked by a tree (represented by X).
You can move LEFT, RIGHT, UP, and DOWN through empty cells,
but you cannot travel through a tree cell. Starting cell is marked
with the character M, and the target cell is marked with a *.
The upper-left corner is indexed as (0, 0).
Find a number of times you are able to move in more than one direction.
"""


def count_number_of_road_forks(field):
    """
    Returns number of times you are able to move in more
    than one direction by a given forest

    :param field: Array of strings that represent forest
    :return: number of road forks
    """
    graph = {}
    start = None
    end = None
    height = len(field)
    width = len(field[0])
    for i, row in enumerate(field):
        for j, symbol in enumerate(row):
            if symbol == 'X':
                continue

            index = __linear_index(i, j, height, width)
            if symbol == 'M':
                start = index
            if symbol == '*':
                end = index

            graph[index] = []
            if __can_go_to(i - 1, j, height, width):
                if field[i - 1][j] != 'X':
                    neighbor_index = __linear_index(i - 1, j, height, width)
                    graph[neighbor_index].append(index)
                    graph[index].append(neighbor_index)

            if __can_go_to(i, j - 1, height, width):
                if field[i][j - 1] != 'X':
                    neighbor_index = __linear_index(i, j - 1, height, width)
                    graph[neighbor_index].append(index)
                    graph[index].append(neighbor_index)

    path = __dijkstra_path(graph, start, end)
    fork_count = 1 if len(graph[start]) > 1 else 0
    for vertex in path[1:-1]:
        if len(graph[vertex]) - 1 > 1:
            fork_count += 1
    return fork_count


def __dijkstra_path(graph, start, end):
    distances = {}
    previous = {}
    visited = set()

    distances[start] = 0

    while True:
        u = __next_vertex(distances, visited)
        if u is None or u == end:
            break
        visited.add(u)
        for v in graph[u]:
            d = distances[u] + 1
            if v not in distances or d < distances[v]:
                distances[v] = d
                previous[v] = u

    return __build_path(start, end, previous)


def __next_vertex(distances, visited):
    min_distance_vertex = None
    for vertex in filter(lambda v: v not in visited, distances.keys()):
        if min_distance_vertex is None:
            min_distance_vertex = vertex
        if vertex in distances and distances[vertex] < distances[min_distance_vertex]:
            min_distance_vertex = vertex
    return min_distance_vertex


def __build_path(start, end, previous):
    path = []
    current = end
    while current in previous:
        path = [current] + path
        current = previous[current]
    return [start] + path


def __linear_index(i, j, width, height):
    return i * height + j


def __can_go_to(i, j, width, height):
    return 0 <= i < width and 0 <= j < height
