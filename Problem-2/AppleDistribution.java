import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class AppleDistribution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> appleWeights = new ArrayList<>();

        System.out.println("Enter apple weights in grams (enter -1 to stop): ");
        
        // Read weights from the console until -1 is entered
        while (true) {
            int weight = sc.nextInt();
            if (weight == -1) break; // Stop when -1 is entered
            appleWeights.add(weight); // Add weight to the list
        }

        distributeApples(appleWeights);
        sc.close(); // Close the scanner
    }

    private static void distributeApples(List<Integer> appleWeights) {
        // Sort weights in descending order
        Collections.sort(appleWeights, Collections.reverseOrder());

        // Initialize lists for Ram, Sham, and Rahim
        List<Integer> ram = new ArrayList<>();
        List<Integer> sham = new ArrayList<>();
        List<Integer> rahim = new ArrayList<>();

        // Define the share of each person
        int ramShare = 50;   // 50% of total weight
        int shamShare = 30;  // 30% of total weight
        int rahimShare = 20; // 20% of total weight

        // Calculate total weight
        int totalWeight = appleWeights.stream().mapToInt(Integer::intValue).sum();

        // Calculate individual weight limits based on total weight
        int ramLimit = (ramShare * totalWeight) / 100;
        int shamLimit = (shamShare * totalWeight) / 100;
        int rahimLimit = (rahimShare * totalWeight) / 100;

        // Initialize weight trackers
        int ramWeight = 0;
        int shamWeight = 0;
        int rahimWeight = 0;

        // Distribute apples according to share limits
        for (int weight : appleWeights) {
            if (ramWeight < ramLimit) {
                ram.add(weight);
                ramWeight += weight; // Add weight to Ram's total
            } else if (shamWeight < shamLimit) {
                sham.add(weight);
                shamWeight += weight; // Add weight to Sham's total
            } else if (rahimWeight < rahimLimit) {
                rahim.add(weight);
                rahimWeight += weight; // Add weight to Rahim's total
            } else {
                // If all limits are reached, break out of the loop
                break;
            }
        }

        // Output the distributed apples
        System.out.println("Ram: " + ram + " | Total Weight: " + ramWeight + "g");
        System.out.println("Sham: " + sham + " | Total Weight: " + shamWeight + "g");
        System.out.println("Rahim: " + rahim + " | Total Weight: " + rahimWeight + "g");
    }
}
