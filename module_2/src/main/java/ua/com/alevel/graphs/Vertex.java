package ua.com.alevel.graphs;

public class Vertex {
    private final String name;
    private final int index;
    private boolean inGraph;

    public Vertex(String name, int index) {
        this.name = name;
        this.inGraph = false;
        this.index = index;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public boolean inGraph() {
        return inGraph;
    }

    public void setInGraph(boolean inTree) {
        inGraph = inTree;
    }
}