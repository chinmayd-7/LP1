import java.util.Scanner;

public class OptimalReplacement {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of Frames: ");
        int frames = sc.nextInt();

        System.out.print("Enter length of Reference String: ");
        int ref_len = sc.nextInt();

        int reference[] = new int[ref_len];
        int buffer[] = new int[frames];
        int mem_layout[][] = new int[ref_len][frames];

        // Initialize frames with -1
        for (int i = 0; i < frames; i++)
            buffer[i] = -1;

        System.out.println("Enter Reference String:");
        for (int i = 0; i < ref_len; i++)
            reference[i] = sc.nextInt();

        int hit = 0, fault = 0;

        for (int i = 0; i < ref_len; i++) {

            int page = reference[i];
            int search = -1;

            // Check if page is already present → HIT
            for (int j = 0; j < frames; j++) {
                if (buffer[j] == page) {
                    search = j;
                    hit++;
                    break;
                }
            }

            // Page fault (not found)
            if (search == -1) {
                fault++;

                int pos = -1, farthest = -1;

                // Find optimal page to replace
                for (int j = 0; j < frames; j++) {

                    int k;
                    for (k = i + 1; k < ref_len; k++) {
                        if (buffer[j] == reference[k]) {
                            if (k > farthest) {
                                farthest = k;
                                pos = j;
                            }
                            break;
                        }
                    }

                    // If page not found in future, replace this frame immediately
                    if (k == ref_len) {
                        pos = j;
                        break;
                    }
                }

                // If all frames empty, fill first empty
                if (pos == -1)
                    pos = 0;

                // Replace page
                buffer[pos] = page;
            }

            // Save memory layout
            for (int j = 0; j < frames; j++)
                mem_layout[i][j] = buffer[j];
        }

        // Print memory layout
        System.out.println("\nMemory Layout:");
        for (int i = 0; i < frames; i++) {
            for (int j = 0; j < ref_len; j++)
                System.out.printf("%3d ", mem_layout[j][i]);
            System.out.println();
        }

        System.out.println("\nTotal Hits: " + hit);
        System.out.println("Hit Ratio: " + (float) hit / ref_len);
        System.out.println("Total Faults: " + fault);

        sc.close();
    }
}
