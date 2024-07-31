import java.util.*;

public class SpecialCastle {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of soldiers: ");
        int numSoldiers = sc.nextInt();
        int[][] soldiers = new int[numSoldiers][2];
        
        for (int i = 0; i < numSoldiers; i++) {
            System.out.print("Enter coordinates for soldier " + (i + 1) + ": ");
            soldiers[i][0] = sc.nextInt();
            soldiers[i][1] = sc.nextInt();
        }
        
        System.out.print("Enter the coordinates for your special castle: ");
        int castleX = sc.nextInt();
        int castleY = sc.nextInt();
        
        findPaths(soldiers, castleX, castleY);
    }
    
    private static void findPaths(int[][] soldiers, int castleX, int castleY) {
        // Sort soldiers based on their positions for simplicity
        Arrays.sort(soldiers, Comparator.comparingInt(a -> a[0] * 10 + a[1]));
        
        List<List<int[]>> paths = new ArrayList<>();
        findPathsRecursive(soldiers, castleX, castleY, new ArrayList<>(), paths);
        
        System.out.println("Thanks. There are " + paths.size() + " unique paths for your 'special_castle'");
        for (int i = 0; i < paths.size(); i++) {
            System.out.println("Path " + (i + 1) + ":");
            for (int[] step : paths.get(i)) {
                System.out.println("Kill at (" + step[0] + "," + step[1] + "). Turn Left");
            }
            System.out.println("Arrive (" + castleX + "," + castleY + ")");
        }
    }
    
    private static void findPathsRecursive(int[][] soldiers, int x, int y, List<int[]> currentPath, List<List<int[]>> paths) {
        // Base case: all soldiers are killed
        if (soldiers.length == 0) {
            paths.add(new ArrayList<>(currentPath));
            return;
        }
        
        for (int i = 0; i < soldiers.length; i++) {
            int[] soldier = soldiers[i];
            int[][] remainingSoldiers = new int[soldiers.length - 1][2];
            for (int j = 0, k = 0; j < soldiers.length; j++) {
                if (j != i) {
                    remainingSoldiers[k++] = soldiers[j];
                }
            }
            
            currentPath.add(soldier);
            findPathsRecursive(remainingSoldiers, soldier[0], soldier[1], currentPath, paths);
            currentPath.remove(currentPath.size() - 1);
        }
    }
}
