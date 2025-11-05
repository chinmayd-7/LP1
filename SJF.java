import java.util.*;
import java.io.*;

public class sjf {
    public static void main(String args[]) {
        int n, sum = 0;
        float total_tt = 0, total_waiting = 0;

        Scanner s = new Scanner(System.in);
        System.out.println("Enter Number Of Processes:");
        n = s.nextInt();

        int arrival[] = new int[n];
        int cpu[] = new int[n];
        int remaining[] = new int[n];
        int finish[] = new int[n];
        int turntt[] = new int[n];
        int wait[] = new int[n];
        int process[] = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Enter arrival time of " + (i + 1) + " process: ");
            arrival[i] = s.nextInt();
            System.out.println("Enter CPU time of " + (i + 1) + " process: ");
            cpu[i] = s.nextInt();
            remaining[i] = cpu[i];
            process[i] = i + 1;
        }

        int completed = 0, time = 0, min_index = -1;
        int min_rem = Integer.MAX_VALUE;

        while (completed != n) {
            min_index = -1;
            min_rem = Integer.MAX_VALUE;

            // find process with smallest remaining time among arrived ones
            for (int i = 0; i < n; i++) {
                if (arrival[i] <= time && remaining[i] > 0 && remaining[i] < min_rem) {
                    min_rem = remaining[i];
                    min_index = i;
                }
            }

            if (min_index == -1) {
                time++; // no process has arrived yet
                continue;
            }

            remaining[min_index]--; // execute for 1 time unit
            time++;

            if (remaining[min_index] == 0) {
                completed++;
                finish[min_index] = time;
                turntt[min_index] = finish[min_index] - arrival[min_index];
                wait[min_index] = turntt[min_index] - cpu[min_index];
                total_tt += turntt[min_index];
                total_waiting += wait[min_index];
            }
        }

        System.out.println("\nProcess\tAT\tCPU_T\tFT\tTAT\tWT");
        for (int i = 0; i < n; i++) {
            System.out.println(process[i] + "\t" + arrival[i] + "\t" + cpu[i] + "\t" + finish[i] + "\t" + turntt[i] + "\t" + wait[i]);
        }

        System.out.println("\nAverage Turnaround Time: " + (total_tt / n));
        System.out.println("Average Waiting Time: " + (total_waiting / n));
    }
}     