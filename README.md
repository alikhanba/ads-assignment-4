# Assignment 4: Graph Traversal and Representation System

## Project Overview

This project implements a graph in Java using an adjacency list. A graph is made of vertices and edges. Each `Vertex` has a unique integer id, and each `Edge` connects one vertex to another.

The program runs two graph traversal algorithms:

Breadth-First Search (BFS)
 Depth-First Search (DFS)

BFS explores vertices level by level. DFS explores one path as far as possible before backtracking.

## Repository Structure

```text
assignment4/
├── src/
│   ├── Vertex.java
│   ├── Edge.java
│   ├── Graph.java
│   ├── Experiment.java
│   └── Main.java
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```
## Class Descriptions

### Vertex

Vertex represents one node in the graph.

- Field: id
- Methods: constructor, getId(), toString()

### Edge

Edge represents a connection between two vertices.

- Fields: source, destination
- Methods: constructor, getters, toString()

### Graph

Graph stores vertices and edges using an adjacency list.

- addVertex(Vertex v) adds a vertex.
- addEdge(int from, int to) adds an edge between two existing vertices.
- printGraph() prints each vertex and its neighbors.
- bfs(int start) prints BFS traversal from a starting vertex.
- dfs(int start) prints DFS traversal from a starting vertex.

The graph is undirected by default, so adding edge `0-1` also allows traversal from `1` back to `0`.

### Experiment

Experiment creates graphs with 10, 30, and 100 vertices. It runs BFS and DFS on each graph and measures time with `System.nanoTime()`.

## Algorithm Descriptions

### BFS

Steps:

1. Add the start vertex to a queue.
2. Mark it as visited.
3. Remove the next vertex from the queue.
4. Add all unvisited neighbors to the queue.
5. Repeat until the queue is empty.

Use cases:

- Finding the shortest path in an unweighted graph
- Exploring a graph by levels
- Checking connected components

Time complexity: O(V + E), where V is vertices and E is edges.

### DFS

Steps:

1. Start at the selected vertex.
2. Mark it as visited.
3. Visit the first unvisited neighbor.
4. Continue deeper until no unvisited neighbor remains.
5. Backtrack and continue with other neighbors.

Use cases:

- Searching paths
- Detecting cycles
- Topological sorting in directed graphs

Time complexity: `O(V + E)`.

## Experimental Results

| Vertices | Edges | BFS Time (ns) | DFS Time (ns) |
|---------:|------:|--------------:|--------------:|
| 10       | 17    | 153303        | 30521         |
| 30       | 57    | 195977        | 71728         |
| 100      | 197   | 271126        | 128090        |

## Analysis Questions

### How does graph size affect BFS and DFS performance?

As the graph gets larger, both traversals usually take more time because they must visit more vertices and check more edges.

### Which traversal is faster in your experiments?

DFS was faster in this run for all three graph sizes. The graphs are small, so exact nanosecond values can change between runs, but both algorithms stayed very fast.

### Do results match the expected complexity O(V + E)?

Yes. The running time increases as the number of vertices and edges increases. BFS and DFS both visit each reachable vertex once and check each edge from the adjacency list.

### How does graph structure affect traversal order?

Graph structure affects which neighbors are available from each vertex. The adjacency list order also matters because BFS and DFS visit neighbors in the order they were added.

### When is BFS preferred over DFS?

BFS is preferred when the shortest path is needed in an unweighted graph, or when the graph should be explored level by level.

### What are the limitations of DFS?

DFS does not guarantee the shortest path. Recursive DFS can also use a lot of stack memory on very deep graphs. DFS must track visited vertices to avoid infinite loops in graphs with cycles.

## Screenshots

### Graph Structure Output

![Graph structure output](docs/screenshots/graph-output.png)

### BFS Traversal Output

![BFS traversal output](docs/screenshots/bfs-output.png)

### DFS Traversal Output

![DFS traversal output](docs/screenshots/dfs-output.png)

### Performance Results

![Performance output](docs/screenshots/performance-output.png)

## Reflection

This assignment helped me understand how graphs can be represented with an adjacency list. The adjacency list is simple and efficient because each vertex stores only its direct neighbors.

BFS and DFS both visit vertices in a graph, but they behave differently. BFS uses a queue and explores by distance from the start vertex. DFS uses recursion and follows one path deeply before backtracking. The main challenge was keeping the traversal order clear and making the experiment output easy to compare.
