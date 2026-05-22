
public class Main {

    public static void main(String[] args) {

        Graph smallGraph  = buildGraph(10,  20,  true);   // 10  vertices
        Graph mediumGraph = buildGraph(30,  80,  false);  // 30  vertices
        Graph largeGraph  = buildGraph(100, 350, false);  // 100 vertices


        System.out.println("======== SMALL GRAPH STRUCTURE ========");
        smallGraph.printGraph();

        Graph[]  graphs = { smallGraph, mediumGraph, largeGraph };
        String[] labels = {
                "Small (10 vertices)",
                "Medium (30 vertices)",
                "Large (100 vertices)"
        };

        Experiment experiment = new Experiment(graphs.length);
        experiment.runMultipleTests(graphs, labels);
        experiment.printResults();
    }


    private static Graph buildGraph(int n, int edgeCount, boolean undirected) {
        Graph g = new Graph();

        for (int i = 0; i < n; i++) {
            g.addVertex(new Vertex(i));
        }

        int added = 0;
        int step   = 1;

        while (added < edgeCount && step < n) {
            for (int i = 0; i < n && added < edgeCount; i++) {
                int from = i;
                int to   = (i + step) % n;

                if (from != to) {
                    g.addEdge(from, to);
                    added++;

                    if (undirected) {
                        g.addEdge(to, from);
                        added++;
                    }
                }
            }
            step++;
        }

        return g;
    }
}