
public class Experiment {

    private long[][] results;

    private String[] graphLabels;

    private int testCount;


    public Experiment(int testCount) {
        this.testCount = testCount;
        this.results = new long[testCount][2];
        this.graphLabels = new String[testCount];
    }

    public void runTraversals(Graph g, int startId, int index, String label, boolean printOrder) {
        graphLabels[index] = label;

        System.out.println("\n--- " + label + " ---");
        System.out.println("Vertices: " + g.getVertexCount() + " | Edges: " + g.getEdgeCount());

        long bfsStart = System.nanoTime();
        if (printOrder) {
            g.bfs(startId);
        } else {
            silentBfs(g, startId);
        }
        long bfsEnd = System.nanoTime();
        results[index][0] = bfsEnd - bfsStart;

        long dfsStart = System.nanoTime();
        if (printOrder) {
            g.dfs(startId);
        } else {
            silentDfs(g, startId);
        }
        long dfsEnd = System.nanoTime();
        results[index][1] = dfsEnd - dfsStart;

        System.out.printf("  BFS time: %,d ns%n", results[index][0]);
        System.out.printf("  DFS time: %,d ns%n", results[index][1]);
    }

    public void runMultipleTests(Graph[] graphs, String[] labels) {
        for (int i = 0; i < graphs.length; i++) {
            boolean printOrder = (i == 0);
            runTraversals(graphs[i], 0, i, labels[i], printOrder);
        }
    }

    public void printResults() {
        System.out.println("\nresults");
        System.out.printf("%-25s %15s %15s%n", "Graph", "BFS (ns)", "DFS (ns)");
        System.out.println("-".repeat(57));
        for (int i = 0; i < testCount; i++) {
            if (graphLabels[i] != null) {
                System.out.printf("%-25s %,15d %,15d%n",
                        graphLabels[i], results[i][0], results[i][1]);
            }
        }
    }


    private void silentBfs(Graph g, int start) {
        java.io.PrintStream original = System.out;
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            public void write(int b) {}
        }));
        g.bfs(start);
        System.setOut(original);
    }


    private void silentDfs(Graph g, int start) {
        java.io.PrintStream original = System.out;
        System.setOut(new java.io.PrintStream(new java.io.OutputStream() {
            public void write(int b) {}
        }));
        g.dfs(start);
        System.setOut(original);
    }
}
