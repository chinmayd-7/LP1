import java.util.*;

public class MemoryAllocation {
    static void firstFit(int[] blockSize, int[] processSize) {
        int m = blockSize.length;
        int n = processSize.length;
        int[] allocation = new int[n];

        Arrays.fill(allocation, -1);
        int[] blocks = Arrays.copyOf(blockSize, m);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (blocks[j] >= processSize[i]) {
                    allocation[i] = j;
                    blocks[j] -= processSize[i];
                    break;
                }
            }
        }

        printResult("First Fit", allocation, processSize);
    }

    static void bestFit(int[] blockSize, int[] processSize) {
        int m = blockSize.length;
        int n = processSize.length;
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);
        int[] blocks = Arrays.copyOf(blockSize, m);

        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blocks[j] >= processSize[i]) {
                    if (bestIdx == -1 || blocks[j] < blocks[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }
            if (bestIdx != -1) {
                allocation[i] = bestIdx;
                blocks[bestIdx] -= processSize[i];
            }
        }

        printResult("Best Fit", allocation, processSize);
    }

    static void nextFit(int[] blockSize, int[] processSize) {
        int m = blockSize.length;
        int n = processSize.length;
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);
        int[] blocks = Arrays.copyOf(blockSize, m);
        int lastAllocated = 0;

        for (int i = 0; i < n; i++) {
            int j = lastAllocated;
            do {
                if (blocks[j] >= processSize[i]) {
                    allocation[i] = j;
                    blocks[j] -= processSize[i];
                    lastAllocated = j;
                    break;
                }
                j = (j + 1) % m;
            } while (j != lastAllocated);
        }

        printResult("Next Fit", allocation, processSize);
    }

    static void worstFit(int[] blockSize, int[] processSize) {
        int m = blockSize.length;
        int n = processSize.length;
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);
        int[] blocks = Arrays.copyOf(blockSize, m);

        for (int i = 0; i < n; i++) {
            int worstIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blocks[j] >= processSize[i]) {
                    if (worstIdx == -1 || blocks[j] > blocks[worstIdx]) {
                        worstIdx = j;
                    }
                }
            }
            if (worstIdx != -1) {
                allocation[i] = worstIdx;
                blocks[worstIdx] -= processSize[i];
            }
        }

        printResult("Worst Fit", allocation, processSize);
    }

    static void printResult(String algoName, int[] allocation, int[] processSize) {
        System.out.println("\n" + algoName + " Allocation:");
        System.out.println("Process No.\tProcess Size\tBlock No.");
        for (int i = 0; i < processSize.length; i++) {
            System.out.print("   " + (i + 1) + "\t\t" + processSize[i] + "\t\t");
            if (allocation[i] != -1)
                System.out.println(allocation[i] + 1);
            else
                System.out.println("Not Allocated");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter sizes of blocks:");
        for (int i = 0; i < m; i++)
            blockSize[i] = sc.nextInt();

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < n; i++)
            processSize[i] = sc.nextInt();

        firstFit(blockSize, processSize);
        bestFit(blockSize, processSize);
        nextFit(blockSize, processSize);
        worstFit(blockSize, processSize);
    }
}
