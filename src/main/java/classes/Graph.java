package main.java.classes;

public class Graph {
    private int matrix[][];

    public Graph(int numVertices) {
        matrix = new int[numVertices][numVertices];
    }

    /**
     * A getter method to get the constructed matrix
     * @return int[][] matrix
     */
    public int[][] getMatrix() {
        return matrix;
    }

    /**
     * add an edge to the adjacency matrix
     * @param i
     * @param j
     * @param weight
     */
    public void addEdge(int i, int j, int weight) {
        matrix[i-1][j] = weight;
        matrix[j][i-1] = weight;
    }
}