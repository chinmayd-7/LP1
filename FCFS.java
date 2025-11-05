import java.util.*;
import java.io.*;

public class demofcfs {
    public static void main(String []args) {
        int n; 
        float tot_tt = 0, tot_wt = 0;
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the number of processes: ");
        n = sc.nextInt();
        
        int at[] = new int[n];
        int cpu[] = new int[n];
        int finish[] = new int[n];
        int tat[] = new int[n];
        int wt[] = new int[n];
        int pro[] = new int[n];
        
        for(int i = 0; i < n; i++) {
            pro[i] = i + 1;
            System.out.println("Enter the arrival time of process " + pro[i] + ": ");
            at[i] = sc.nextInt();
            System.out.println("Enter the CPU time of process " + pro[i] + ": ");
            cpu[i] = sc.nextInt();
        }

        // FCFS calculation
        for (int i = 0; i < n; i++) {
            if (i == 0)
                finish[i] = at[i] + cpu[i];
            else {
                if (at[i] > finish[i - 1])
                    finish[i] = at[i] + cpu[i]; // CPU idle
                else
                    finish[i] = finish[i - 1] + cpu[i];
            }

            tat[i] = finish[i] - at[i];
            wt[i] = tat[i] - cpu[i];
            tot_tt += tat[i];
            tot_wt += wt[i];
        }

        System.out.println("\nProcess\tAT\tCPU_T\tCT\tTAT\tWT");
        for(int i = 0; i < n; i++) {
            System.out.println("P" + pro[i] + "\t" + at[i] + "\t" + cpu[i] + "\t" + finish[i] + "\t" + tat[i] + "\t" + wt[i]);
        }

        System.out.println("\nAverage Turn Around Time : " + (tot_tt / n));
        System.out.println("Average Waiting Time : " + (tot_wt / n));
    }
}
