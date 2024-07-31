import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// The java.awt.geom.Line2D class is part of the Java 2D API in the java.awt.geom package,
// which provides the necessary tools to create and manipulate two-dimensional geometric shapes.

public class P1NoAccidentPlease {
    public static void main(String[] args) {
        // Creating the Scanner object to get the data dynamically from the console
        Scanner sc = new Scanner(System.in);
        
        // Read the number of flights
        System.out.print("Enter the number of flights: ");
        int numFlights = sc.nextInt();
        
        // List to store all flights' coordinates
        List<int[][]> flights = new ArrayList<>();
        
        // Read coordinates for each flight
        // This loop runs for the total number of flights times
        for (int i = 0; i < numFlights; i++) {
            // Take dynamic coordinates if needed for future changes; for this question, it is 3
            System.out.print("Enter the number of coordinates for flight " + (i + 1) + ": ");
            int numCoordinates = sc.nextInt();

            // Here 2 represents two axes: x and y
            int[][] flight = new int[numCoordinates][2];
            
            // This loop runs for the number of coordinates
            for (int j = 0; j < numCoordinates; j++) {
                System.out.print("Enter coordinate " + (j + 1) + " (format: x y): ");
                flight[j][0] = sc.nextInt(); // Taking the x coordinate
                flight[j][1] = sc.nextInt(); // Taking the y coordinates
            }
            // Adding each flight to the flights ArrayList
            flights.add(flight);
        }
        
        // Plot the paths and check for intersections
        plotFlightPaths(flights);
    }

    private static void plotFlightPaths(List<int[][]> flights) {
        // List to store all line segments representing flight paths
        // Line2D allows you to define a line segment using two endpoints: (x1, y1) and (x2, y2).
        List<Line2D> lines = new ArrayList<>();
        List<int[]> flightIndices = new ArrayList<>(); // To store which flight each line belongs to
        
        // Convert each flight's coordinates into line segments
        for (int flightIndex = 0; flightIndex < flights.size(); flightIndex++) {
            int[][] flight = flights.get(flightIndex);
            for (int i = 0; i < flight.length - 1; i++) {
                Line2D line = new Line2D.Double(flight[i][0], flight[i][1], flight[i + 1][0], flight[i + 1][1]);
                lines.add(line);
                flightIndices.add(new int[]{flightIndex, i});
            }
        }
        
        // Check for intersections between any two flight paths
        boolean[] intersectedFlights = new boolean[flights.size()]; // Array to keep track of intersecting flights
        for (int i = 0; i < lines.size(); i++) {
            for (int j = i + 1; j < lines.size(); j++) {
            // intersectsLine(Line2D line) is a method of the Line2D class, which checks if the two line segments intersect.
               if (lines.get(i).intersectsLine(lines.get(j))) {
                    intersectedFlights[flightIndices.get(i)[0]] = true;
                    intersectedFlights[flightIndices.get(j)[0]] = true;
                }
            }
        }
        
        // Print the non-intersecting plot paths
        printNonIntersectingPlotPaths(flights, intersectedFlights);
    }

    private static void printNonIntersectingPlotPaths(List<int[][]> flights, boolean[] intersectedFlights) {
        System.out.println("Non-intersecting flight paths:");
        System.out.println(flights.size());
        for (int i = 0; i < flights.size(); i++) {
            if (!intersectedFlights[i]) {
                System.out.print("Flight " + (i + 1) + " path: ");
                for (int j = 0; j < flights.get(i).length; j++) {
                    System.out.print("(" + flights.get(i)[j][0] + ", " + flights.get(i)[j][1] + ")");
                    if (j < flights.get(i).length - 1) {
                        System.out.print(" -> ");
                    }
                }
                System.out.println();
            }
        }
    }
}



