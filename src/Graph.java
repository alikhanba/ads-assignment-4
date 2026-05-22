import java.util.*;
public class Graph {
    private Map<Integer, Vertex> vertices;
    private Map<Integer, List<Integer>> adjacencyList;
    public Graph() {
        vertices = new LinkedHashMap<>();
        adjacencyList = new LinkedHashMap<>();
    }
    public void addVertex(Vertex v) {
        if (!vertices.containsKey(v.getId())) {
            vertices.put(v.getId(), v);
            adjacencyList.put(v.getId(), new ArrayList<>());
        }
    }
    public void addEdge(int from, int to) {
        if (!vertices.containsKey(from) || !vertices.containsKey(to)) {
            System.out.println("Warning: one or both vertices not found (" + from + ", " + to + ")");
            return;
        }
        adjacencyList.get(from).add(to);
    }
    public void printGraph() {
        System.out.println("Graph Adjacency List:");
        for (int id : adjacencyList.keySet()) {
            System.out.print("  " + vertices.get(id) + " -> [");
            List<Integer> neighbors = adjacencyList.get(id);
            for (int i = 0; i < neighbors.size(); i++) {
                System.out.print(vertices.get(neighbors.get(i)));
                if (i < neighbors.size() - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
    }
    public void bfs(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("BFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new LinkedHashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        visited.add(start);

        System.out.print("BFS from " + vertices.get(start) + ": ");

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(vertices.get(current) + " ");
            for (int neighbor : adjacencyList.get(current)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                }
            }
        }
        System.out.println();
    }

    public void dfs(int start) {
        if (!vertices.containsKey(start)) {
            System.out.println("DFS: start vertex " + start + " not found.");
            return;
        }

        Set<Integer> visited = new LinkedHashSet<>();
        Deque<Integer> stack = new ArrayDeque<>();

        stack.push(start);

        System.out.print("DFS from " + vertices.get(start) + ": ");

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                System.out.print(vertices.get(current) + " ");

                List<Integer> neighbors = adjacencyList.get(current);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    if (!visited.contains(neighbors.get(i))) {
                        stack.push(neighbors.get(i));
                    }
                }
            }
        }
        System.out.println();
    }
    public int getVertexCount() {
        return vertices.size();
    }
    public int getEdgeCount() {
        int count = 0;
        for (List<Integer> neighbors : adjacencyList.values()) {
            count += neighbors.size();
        }
        return count;
    }
}
