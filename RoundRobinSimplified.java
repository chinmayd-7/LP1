import java.util.*;

class Process {
    int pid, arrival, burst, remaining, completion, waiting, turnaround;

    Process(int pid, int arrival, int burst) {
        this.pid = pid;
        this.arrival = arrival;
        this.burst = burst;
        this.remaining = burst;
    }
}

public class RoundRobinSimplified {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();

        List<Process> processes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            System.out.printf("\nProcess %d\n", i + 1);
            System.out.print("Arrival Time: ");
            int at = sc.nextInt();
            System.out.print("Burst Time: ");
            int bt = sc.nextInt();
            processes.add(new Process(i + 1, at, bt));
        }

        System.out.print("\nEnter Time Quantum: ");
        int tq = sc.nextInt();

        // Sort processes by arrival time for consistency
        processes.sort(Comparator.comparingInt(p -> p.arrival));

        Queue<Process> queue = new LinkedList<>();
        int time = 0, completed = 0;
        boolean[] inQueue = new boolean[n];

        while (completed < n) {
            // Add all arrived processes to the queue
            for (int i = 0; i < n; i++) {
                Process p = processes.get(i);
                if (!inQueue[i] && p.arrival <= time) {
                    queue.add(p);
                    inQueue[i] = true;
                }
            }

            if (queue.isEmpty()) { time++; continue; }

            Process current = queue.poll();
            int exec = Math.min(tq, current.remaining);
            current.remaining -= exec;
            time += exec;

            // Add new processes that arrived during this time slice
            for (int i = 0; i < n; i++) {
                Process p = processes.get(i);
                if (!inQueue[i] && p.arrival <= time) {
                    queue.add(p);
                    inQueue[i] = true;
                }
            }

            if (current.remaining > 0)
                queue.add(current);
            else {
                current.completion = time;
                current.turnaround = current.completion - current.arrival;
                current.waiting = current.turnaround - current.burst;
                completed++;
            }
        }

        printResults(processes, tq);
        sc.close();
    }

    static void printResults(List<Process> list, int tq) {
        System.out.println("\n===== Round Robin Scheduling (TQ = " + tq + ") =====");
        System.out.printf("%-5s %-10s %-10s %-15s %-15s %-15s%n",
                "PID", "Arrival", "Burst", "Completion", "Waiting", "Turnaround");

        double totalWT = 0, totalTAT = 0;
        for (Process p : list) {
            System.out.printf("%-5d %-10d %-10d %-15d %-15d %-15d%n",
                    p.pid, p.arrival, p.burst, p.completion, p.waiting, p.turnaround);
            totalWT += p.waiting;
            totalTAT += p.turnaround;
        }

        System.out.printf("\nAverage Waiting Time: %.2f", totalWT / list.size());
        System.out.printf("\nAverage Turnaround Time: %.2f\n", totalTAT / list.size());
    }
}
