package main.java.classes;

import java.util.Stack;

public class DijkstrasAlgorithm {
  
    private final int NO_PARENT = -1; 

    /**
     * This is a method that takes an weighted adjacency matrix and
     * finds and prints to console the shortest path from the start 
     * vertex to all other vertices using Dijkstra's Algorithm.
     * @param adjacencyMatrix
     * @param startVertex
     * @param labelArray
     */
    public void dijkstra(int[][] adjacencyMatrix, int startVertex, String[] labelArray) { 
        // nVertices holds the number of vertices in the graph
        int nVertices = adjacencyMatrix[0].length; 
  
        // shortestDistances[i] will hold the shortest distances from the source vertex to i
        int[] shortestDistances = new int[nVertices]; 
  
        // added[i] will true if vertex i is included in shortest path tree 
        // or shortest distance from src to i is finalized 
        boolean[] added = new boolean[nVertices]; 
  
        // Initialize all distances as INFINITE and added[] as false 
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
            shortestDistances[vertexIndex] = Integer.MAX_VALUE; 
            added[vertexIndex] = false; 
        } 
          
        // Set the distance of the startVertex to 0
        shortestDistances[startVertex] = 0; 
  
        // parents[] will store the shortest path tree 
        int[] parents = new int[nVertices]; 
  
        // The starting vertex does not have a parent 
        parents[startVertex] = NO_PARENT; 
  
        // Find shortest path for all vertices 
        for (int i = 1; i < nVertices; i++) 
        { 
  
            // Pick the minimum distance vertex from the set of vertices not yet 
            // processed. nearestVertex is always equal to startNode in first iteration. 
            int nearestVertex = -1; 
            int shortestDistance = Integer.MAX_VALUE; 
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
                // If the current vertexIndex is not in added[] and the it's value in 
                // shortestDistances[] is less than INFINITY,
                if (!added[vertexIndex] && shortestDistances[vertexIndex] < shortestDistance) { 
                    // set nearestVertex equal to the current vertexIndex
                    nearestVertex = vertexIndex; 
                    // and set shortestDistance equal to the current vertexIndex's
                    // value in shortestDistances[]
                    shortestDistance = shortestDistances[vertexIndex]; 
                } 
            } 
  
            // Mark the current vertex as processed 
                added[nearestVertex] = true; 
  
            // Update shortestDistances[] value of the adjacent vertices of the current vertex. 
            for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
                // Set edgeDistance equal to the value of the vertexIndex and its nearestVertex.
                int edgeDistance = adjacencyMatrix[nearestVertex][vertexIndex]; 
                // If edgeDistance is GREATER THAN zero AND the shortestDistance plus the edgeDistance
                // is LESS THAN the value of the current vertexIndex in shortestDistances[],
                if (edgeDistance > 0 && ((shortestDistance + edgeDistance) < shortestDistances[vertexIndex])) { 
                    // set the vertexIndex's value in parents[] equal to nearestVertex
                    parents[vertexIndex] = nearestVertex; 
                    // and set the vertexIndex's value in shortestDistances equal to shortestDistance plus edgeDistance.
                    shortestDistances[vertexIndex] = shortestDistance + edgeDistance; 
                } 
            } 
        } 
        // Print the path for each destination vertex.
        printSolution(startVertex, shortestDistances, parents, labelArray); 
    } 
  
    /**
     * A utility function to print the constructed distances array and shortest paths 
     * @param startVertex
     * @param distances
     * @param parents
     * @param labelArray
     */
    private void printSolution(int startVertex, int[] distances, int[] parents, String[] labelArray) { 
        // Print the header.
        String line = "-";
        int nVertices = distances.length; 
        System.out.println();
        System.out.format("%-35s", "Vertex");
        System.out.format("%-20s", "Distance"); 
        System.out.format("%-35s", "Path");
        System.out.println("\n" + line.repeat(80));
        // Print the distance and shortest path for each  
        for (int vertexIndex = 0; vertexIndex < nVertices; vertexIndex++) { 
            if (vertexIndex != startVertex) {
                System.out.format("%-35s", labelArray[startVertex] + " -> " + (labelArray[vertexIndex])); 
                System.out.format("%-20s", distances[vertexIndex] + " miles"); 
                printPath(vertexIndex, parents, labelArray);  
                System.out.println();
            } 
        } 
    } 
  
    /**
     * Function to print shortest path from source to currentVertex using parents array 
     * @param currentVertex
     * @param parents
     * @param labelArray
     */
    private void printPath(int currentVertex, int[] parents, String[] labelArray) { 
        // Create a stack to hold the city names along the path.
        Stack<String> labels = new Stack<String>();
        // Push each city onto the stack.
        while (currentVertex > NO_PARENT) {
            labels.push(labelArray[currentVertex]);
            currentVertex = parents[currentVertex];
        }
        // Pop and print the city names.
        while (labels.isEmpty() == false) {
            System.out.print(labels.pop());
            // Add " -> " between each city name.
            if (labels.isEmpty() == false){
                System.out.print(" -> ");
            }
        }
    }
}
