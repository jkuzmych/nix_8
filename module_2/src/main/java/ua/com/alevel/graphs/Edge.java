package ua.com.alevel.graphs;

import java.util.ArrayList;
import java.util.List;

public class Edge {
    private int weight;
    private List<Integer> baseVertices;

    public Edge(int weight) {
        this.weight = weight;
        this.baseVertices = new ArrayList<>();
    }

    public int getDistance() {
        return weight;
    }

    public void setDistance(int weight) {
        this.weight = weight;
    }

    public List<Integer> getParentVertices() {
        return baseVertices;
    }

    public void setParentVertices(List<Integer> parentVertices) {
        this.baseVertices = parentVertices;
    }
}