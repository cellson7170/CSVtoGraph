////////////////////////////////////////////////////////////////////////////////////////////////////////
//Program Name : CSVtoGraph
//Author : Chris Ellson
//Date : 3/21/2020
//Class : CS250 - Discrete Structures
//Description : This program reads and parses a CSV file, with information distances between cities, 
//into a weighted adjacency matrix. It then uses Dijkstra's Algorithm to find the shortest paths
//between the cities. It allows the user to choose a city as the starting point via the console, and
//then prints out a list of distances and paths from that city to all other cities in the matrix.
//TODO: Allow for the ability to choose a start and destination, and print the path between the two.
////////////////////////////////////////////////////////////////////////////////////////////////////////
package main.java;

import main.java.classes.Graph;
import main.java.classes.ReadCSV;
import java.util.Scanner;
import main.java.classes.DijkstrasAlgorithm;

class Main {
 
    public static void main(String[] args){
        //TODO: Clean up the main class
        ReadCSV reader = new ReadCSV();
        Graph graph = new Graph(28);
        DijkstrasAlgorithm path = new DijkstrasAlgorithm();

        // read CSV into a 2d array
        reader.makeCsvArray();
        
        // Convert valueArray into into a graph.
        // TODO: turn this into a method in another class.
        int[][] matrix = reader.getValueArray();
        for (int i = 1; i < matrix.length; i++) { 
            for (int j = 0; j < matrix.length; j++) { 
                graph.addEdge(i, j, matrix[i][j]);
            }
        }

        // Get user input
        reader.printLabels();
        Scanner scan = new Scanner(System.in);
        System.out.print("\nEnter the name of the starting city to see the shortest path to every other city: ");
        // Get desired city name
        String cityName = scan.nextLine();
        String[] labels = reader.getLabelArray();
        // Get index of desired city in labelArray
        int index = reader.getLabelIndex(cityName);

        // Construct and print distance and path information from
        // a given city to all other cities in the graph.
        path.dijkstra(graph.getMatrix(), index, labels);
        // Close scanner
        scan.close();
	}

}

/**
 * OUTPUT:
 * 
 * PS C:\Users\chris\Desktop\CSVtoGraph> java -cp bin main.java.Main
java.lang.ArrayIndexOutOfBoundsException: Index 28 out of bounds for length 28

Below are all the cities in the graph:
-------------------------------------------------------------------
Birmingham        Boston            Buffalo           Chicago
Cleveland         Dallas            Denver            Detroit
El Paso           Houston           Indianapolis      Kansas City       
Los Angeles       Louisville        Memphis           Miami
Minneapolis       New Orleans       New York          Omaha
Philadelphia      Phoenix           Pittsburgh        St. Louis
Salt Lake City    San Francisco     Seattle           Washington DC     

Enter the name of the starting city to see the shortest path to every other city: New York

Vertex                             Distance            Path
--------------------------------------------------------------------------------
New York -> Birmingham             980 miles           New York -> Washington DC -> Birmingham
New York -> Boston                 213 miles           New York -> Boston
New York -> Buffalo                436 miles           New York -> Buffalo
New York -> Chicago                840 miles           New York -> Chicago
New York -> Cleveland              514 miles           New York -> Cleveland
New York -> Dallas                 1601 miles          New York -> Washington DC -> Dallas
New York -> Denver                 1780 miles          New York -> Denver
New York -> Detroit                671 miles           New York -> Detroit
New York -> El Paso                2147 miles          New York -> El Paso
New York -> Houston                1672 miles          New York -> Washington DC -> Houston
New York -> Indianapolis           729 miles           New York -> Indianapolis
New York -> Kansas City            1216 miles          New York -> Kansas City
New York -> Los Angeles            2825 miles          New York -> Los Angeles
New York -> Louisville             785 miles           New York -> Louisville
New York -> Memphis                1131 miles          New York -> Washington DC -> Memphis
New York -> Miami                  1328 miles          New York -> Miami
New York -> Minneapolis            1251 miles          New York -> Chicago -> Minneapolis
New York -> New Orleans            1327 miles          New York -> Washington DC -> New Orleans
New York -> Omaha                  1315 miles          New York -> Omaha
New York -> Philadelphia           93 miles            New York -> Philadelphia
New York -> Phoenix                2442 miles          New York -> Phoenix
New York -> Pittsburgh             386 miles           New York -> Pittsburgh
New York -> St. Louis              968 miles           New York -> St. Louis
New York -> Salt Lake City         2274 miles          New York -> Indianapolis -> Salt Lake City
New York -> San Francisco          3028 miles          New York -> Indianapolis -> San Francisco
New York -> Seattle                2892 miles          New York -> Chicago -> Seattle
New York -> Washington DC          229 miles           New York -> Washington DC
 */