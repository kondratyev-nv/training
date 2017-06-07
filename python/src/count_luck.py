class GraphEdge:
    start = None
    end = None
    length = 0

    def __init__(self, s, e, l):
        self.length = l
        self.start = s
        self.end = e


class BidirectionalGraph:
    graph = {}

    def add_edge(self, s, e, l):
        if s not in self.graph:
            self.graph[s] = []
        if e not in self.graph:
            self.graph[e] = []
        self.graph[s].append(GraphEdge(s, e, l))
        self.graph[e].append(GraphEdge(e, s, l))

    def dijkstra_path(self, start, end):
        distances = {}
        previous = {}
        visited = set()

        distances[start] = 0

        while True:
            u = self.__next_vertex(distances, visited)
            if u is None or u == end:
                break
            visited.add(u)
            for edge in self.graph[u]:
                v = edge.end
                d = distances[u] + edge.length
                if v not in distances or d < distances[v]:
                    distances[v] = d
                    previous[v] = u

        return self.__build_path(start, end, previous)

    def __next_vertex(self, distances, visited):
        min_distance_vertex = None
        for vertex in filter(lambda v: v not in visited, distances.keys()):
            if min_distance_vertex is None:
                min_distance_vertex = vertex
            if vertex in distances and distances[vertex] < distances[min_distance_vertex]:
                min_distance_vertex = vertex
        return min_distance_vertex

    def __build_path(self, start, end, previous):
        path = []
        current = end
        while current in previous:
            path = [current] + path
            current = previous[current]
        return [start] + path
