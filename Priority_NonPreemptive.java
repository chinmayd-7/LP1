import java.util.*;

class ProcessPriority {
    int pid, arrival, burst, priority, completion, waiting, turnaround;
    int remaining;
    ProcessPriority(int pid, int arrival, int burst, int priority) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.priority = priority;
        this.remaining = burst;
    }
}

public class Priority_NonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<ProcessPriority> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.printf("\nProcess %d\n", i + 1);
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();
            System.out.print("Priority (smaller = higher): ");
            int pr = sc.nextInt();
            list.add(new ProcessPriority(i + 1, at, bt, pr));
        }

        int completed = 0, time = 0;
        while (completed < n) {
            ProcessPriority highest = null;
            for (ProcessPriority p : list) {
                if (p.arrival <= time && p.remaining > 0) {
                    if (highest == null || p.priority < highest.priority)
                        highest = p;
                }
            }

            if (highest == null) { time++; continue; }

            time += highest.burst;
            highest.remaining = 0;
            highest.completion = time;
            highest.turnaround = highest.completion - highest.arrival;
            highest.waiting = highest.turnaround - highest.burst;
            completed++;
        }

        printResults(list, "Priority Scheduling (Non-Preemptive)");
        sc.close();
    }

    static void printResults(List<ProcessPriority> list, String title) {
        System.out.println("\n===== " + title + " =====");
        System.out.printf("%-5s %-10s %-10s %-10s %-15s %-15s %-15s%n",
                "PID", "Arrival", "Burst", "Priority", "Completion", "Waiting", "Turnaround");

        double avgWT = 0, avgTAT = 0;
        for (ProcessPriority p : list) {
            System.out.printf("%-5d %-10d %-10d %-10d %-15d %-15d %-15d%n",
                    p.pid, p.arrival, p.burst, p.priority, p.completion, p.waiting, p.turnaround);
            avgWT += p.waiting;
            avgTAT += p.turnaround;
        }

        System.out.printf("\nAverage Waiting Time: %.2f", avgWT / list.size());
        System.out.printf("\nAverage Turnaround Time: %.2f\n", avgTAT / list.size());
    }
}
