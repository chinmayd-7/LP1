import java.util.*;

class ProcessSJF {
    int pid, arrival, burst, remaining, completion, waiting, turnaround;
    ProcessSJF(int pid, int arrival, int burst) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.remaining = burst;
    }
}

public class SJF_Preemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<ProcessSJF> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.printf("\nProcess %d\n", i + 1);
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();
            list.add(new ProcessSJF(i + 1, at, bt));
        }

        int completed = 0, time = 0;
        while (completed < n) {
            ProcessSJF shortest = null;
            for (ProcessSJF p : list) {
                if (p.arrival <= time && p.remaining > 0) {
                    if (shortest == null || p.remaining < shortest.remaining)
                        shortest = p;
                }
            }
            if (shortest == null) { time++; continue; }

            shortest.remaining--;
            time++;

            if (shortest.remaining == 0) {
                shortest.completion = time;
                shortest.turnaround = shortest.completion - shortest.arrival;
                shortest.waiting = shortest.turnaround - shortest.burst;
                completed++;
            }
        }

        printResults(list, "Shortest Job First (Preemptive)");
        sc.close();
    }

    static void printResults(List<ProcessSJF> list, String title) {
        System.out.println("\n===== " + title + " =====");
        System.out.printf("%-5s %-10s %-10s %-15s %-15s %-15s%n",
                "PID", "Arrival", "Burst", "Completion", "Waiting", "Turnaround");

        double avgWT = 0, avgTAT = 0;
        for (ProcessSJF p : list) {
            System.out.printf("%-5d %-10d %-10d %-15d %-15d %-15d%n",
                    p.pid, p.arrival, p.burst, p.completion, p.waiting, p.turnaround);
            avgWT += p.waiting;
            avgTAT += p.turnaround;
        }

        System.out.printf("\nAverage Waiting Time: %.2f", avgWT / list.size());
        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTAT / list.size());
    }
}
