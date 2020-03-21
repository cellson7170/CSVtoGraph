package main.java.classes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

public class ReadCSV {

    private int[][] valueArray;
    private String[] labelArray;
    private File file = new File(ClassLoader.getSystemResource("cities.csv").getFile());

    /**
     * A getter that returns the value array of the
     * adjacency matrix.
     * @return int[][] valueArray
     */
    public int[][] getValueArray() {
        return valueArray;
    }

    /**
     * A getter than returns the label array
     * for the graph.
     * @return String[] labelArray
     */
    public String[] getLabelArray() {
        return labelArray;
    }

    public int getLabelIndex(String cityName) {
        String[] labels = labelArray;
        for (int i = 0; i < labels.length; i++) {
            if (cityName.equals(labels[i])) {
                return i;
            }
        }
        return -1;
    }

    public void printLabels() {
        System.out.println("\nBelow are all the cities in the graph:");
        String line = "-";
        System.out.println(line.repeat(67));
        int spacingSeparation = 5;
        int longest = "Washington DC".length();
        int spacing = longest + spacingSeparation;
        for (int i = 0; i < labelArray.length; i += 4) {
            System.out.print(String.format(
                    "%-" + spacing + "s%-" + spacing + "s%-" + spacing + "s%-" + spacing + "s\n",
                    labelArray[i], labelArray[i + 1], labelArray[i + 2], labelArray[i + 3]));
        }
    }

    /**
     * Takes a CSV file and parses it into a 2D array of
     * values and an array of column names. At present, it
     * is designed to work exclusively with "cities.csv".
     * When I have more time, I plan on modifying it to
     * work dynamically with any CSV file.
     * 
     * @return int[][] valueArray
     */
    public int[][] makeCsvArray() {

        valueArray = new int[28][28];
        labelArray = new String[28];

        Scanner scan = null;
        int row = 0;
        String inputLine = "";


        try{
            //setup a scanner
            scan = new Scanner(new BufferedReader(new FileReader(file)));
           
            //while the file has a next line
            while (scan.hasNextLine()){
                //read line in from the file
                inputLine = scan.nextLine();
                //split the inputLine into an array at the commas (except commas in quotes)
                String[] inArray = inputLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                //copy the content of inArray to myArray, remove quotes,
                //and create an array of city names called labelArray
                for (int i = 1; i < inArray.length; i++){
                    if (row == 0){
                        labelArray[i-1] = inArray[i];
                    }else{
                        valueArray[row][i-1] = Integer.parseInt(inArray[i].replace("\"","").replace(",",""));
                    }
                }
                row++;
            }
        }catch (Exception exception){
            System.out.println(exception);
        }
        return valueArray;
    }
}