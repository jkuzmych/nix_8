package ua.com.alevel.graphs;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Graphs {

    private final int INFINITY = Integer.MAX_VALUE;
    private int max;
    private Vertex[] verticesList;
    private int[][] relationMatrix;
    private int countOfVertices;
    private int countOfVertexInGraph;
    private List<Edge> shortestPaths;
    private int currentVertex;
    private int startToCurrent;

    public void ioFile() {
        String PATH_INPUT = "files/input3.txt";
        String PATH_OUTPUT = "files/output3.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_INPUT));
             BufferedWriter writer = new BufferedWriter(new FileWriter(PATH_OUTPUT))) {
            while (reader.ready()) {
                max = Integer.parseInt(reader.readLine());
                newMatrix();
                for (int i = 0; i < max; i++) {
                    String str = reader.readLine();
                    verticesList[countOfVertices++] = new Vertex(str, i);
                    int count = Integer.parseInt(reader.readLine());
                    for (int j = 0; j < count; j++) {
                        str = reader.readLine();
                        String[] substrings = str.split(" ");
                        if (substrings.length != 2) {
                            throw new RuntimeException("invalid input");
                        }
                        relationMatrix[i][Integer.parseInt(substrings[0]) - 1] = Integer.parseInt(substrings[1]);
                    }
                }
                int count = Integer.parseInt(reader.readLine());
                for (int j = 0; j < count; j++) {
                    String str = reader.readLine();
                    String[] substrings = str.split(" ");
                    if (substrings.length != 2) {
                        throw new RuntimeException("invalid input");
                    }
                    Vertex current = null;
                    for (Vertex vertex : verticesList) {
                        if (vertex.getName().equals(substrings[0])) current = vertex;
                    }
                    Vertex last = null;
                    for (Vertex vertex : verticesList) {
                        if (vertex.getName().equals(substrings[1])) last = vertex;
                    }
                    if (current == null || last == null) throw new RuntimeException("invalid input");
                    int finalIndex = findShortestPath(current.getIndex(), last.getIndex());
                    outputFile(finalIndex, writer);
                    clean();
                }
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("invalid input");
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private void newMatrix() {
        verticesList = new Vertex[max];
        relationMatrix = new int[max][max];
        countOfVertices = 0;
        countOfVertexInGraph = 0;
        for (int i = 0; i < max; i++) {
            for (int k = 0; k < max; k++) {
                relationMatrix[i][k] = INFINITY;
                shortestPaths = new ArrayList<>();
            }
        }
    }

    private int findShortestPath(int startGraph, int finalIndex) {
        verticesList[startGraph].setInGraph(true);
        countOfVertexInGraph = 1;
        for (int i = 0; i < countOfVertices; i++) {
            int tempDist = relationMatrix[startGraph][i];
            Edge edge = new Edge(tempDist);
            edge.getParentVertices().add(startGraph);
            shortestPaths.add(edge);
        }
        while (countOfVertexInGraph < countOfVertices) {
            int indexMin = minDistance();
            if (shortestPaths.get(indexMin).getDistance() == INFINITY) break;
            else {
                currentVertex = indexMin;
                startToCurrent = shortestPaths.get(indexMin).getDistance();
            }
            verticesList[currentVertex].setInGraph(true);
            countOfVertexInGraph++;
            updateShortestPaths();
        }
        System.out.print("check output3.txt file");
        return finalIndex;
    }

    private int minDistance() {
        int minDistance = INFINITY;
        int min = 0;
        for (int i = 1; i < countOfVertices; i++) {
            if (!verticesList[i].inGraph() && shortestPaths.get(i).getDistance() < minDistance) {
                minDistance = shortestPaths.get(i).getDistance();
                min = i;
            }
        }
        return min;
    }

    private void updateShortestPaths() {
        int vertexIndex = 1;
        while (vertexIndex < countOfVertices) {
            if (verticesList[vertexIndex].inGraph()) {
                vertexIndex++;
                continue;
            }
            int currentToFringe = relationMatrix[currentVertex][vertexIndex];
            int startToFringe = startToCurrent + currentToFringe;
            int shortPathDistance = shortestPaths.get(vertexIndex).getDistance();
            if (startToFringe < shortPathDistance) {
                List<Integer> newParents = new ArrayList<>(shortestPaths.get(currentVertex).getParentVertices());
                newParents.add(currentVertex);
                shortestPaths.get(vertexIndex).setParentVertices(newParents);
                shortestPaths.get(vertexIndex).setDistance(startToFringe);
            }
            vertexIndex++;
        }
    }

    private void outputFile(int finalIndex, BufferedWriter writer) {
        String shortestWay = "";
        if (shortestPaths.get(finalIndex).getDistance() == INFINITY) {
            shortestWay = shortestWay + "0";
        } else {
            shortestWay = shortestWay + shortestPaths.get(finalIndex).getDistance();
        }
        try {
            writer.write(shortestWay);
            writer.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void clean() {
        countOfVertexInGraph = 0;
        for (int i = 0; i < countOfVertices; i++) {
            verticesList[i].setInGraph(false);
        }
        countOfVertexInGraph = 0;
        for (int i = 0; i < max; i++) {
            for (int j = 0; j < max; j++) {
                shortestPaths = new ArrayList<>();
            }
        }
    }
}