import java.util.*;

public class LruAlgo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int ref_len, frames, hit = 0, fault = 0;
        int i, j, k, l, flag1, flag2, index = 0;

        System.out.print("Enter the length of the Reference string: ");
        ref_len = sc.nextInt();

        int reference[] = new int[ref_len];
        System.out.println("Enter elements of the Reference string:");
        for (i = 0; i < ref_len; i++)
            reference[i] = sc.nextInt();

        System.out.print("Enter number of Frames: ");
        frames = sc.nextInt();

        int buffer[] = new int[frames];
        int recent[] = new int[frames];

        // Initialize frames as empty
        for (i = 0; i < frames; i++)
            buffer[i] = -1;

        // Start processing pages
        for (j = 0; j < ref_len; j++) {
            flag1 = 0;
            flag2 = 0;

            // Check if page already exists (Hit)
            for (i = 0; i < frames; i++) {
                if (buffer[i] == reference[j]) {
                    flag1 = 1;
                    flag2 = 1;
                    hit++;
                    break;
                }
            }

            // If empty frame found
            if (flag1 == 0) {
                for (i = 0; i < frames; i++) {
                    if (buffer[i] == -1) {
                        buffer[i] = reference[j];
                        flag2 = 1;
                        fault++;
                        break;
                    }
                }
            }

            // If no empty frame → Replace least recently used page
            if (flag2 == 0) {
                for (i = 0; i < frames; i++)
                    recent[i] = 0;

                for (k = j - 1, l = 1; l <= frames - 1 && k >= 0; l++, k--) {
                    for (i = 0; i < frames; i++) {
                        if (buffer[i] == reference[k])
                            recent[i] = 1;
                    }
                }

                for (i = 0; i < frames; i++) {
                    if (recent[i] == 0) {
                        index = i;
                        break;
                    }
                }

                buffer[index] = reference[j];
                fault++;
            }

            // Display current state
            System.out.print("Page: " + reference[j] + "  ");
            for (i = 0; i < frames; i++) {
                if (buffer[i] == -1)
                    System.out.print("[ ] ");
                else
                    System.out.print("[" + buffer[i] + "] ");
            }
            System.out.println();
        }

        System.out.println("\nTotal Page Hits: " + hit);
        System.out.println("Total Page Faults: " + fault);
        System.out.println("Hit Ratio: " + (float) hit / ref_len);
    }
}
