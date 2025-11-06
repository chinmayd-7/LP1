import java.util.*;

public class FIFOPageReplacement {
    public static void main(String[] args) {
        int pages[] = {1, 3, 0, 3, 5, 6, 3};
        int frameCount = 3;
        Queue<Integer> frames = new LinkedList<>();
        int pageFaults = 0;

        System.out.println("Page Reference String: " + Arrays.toString(pages));
        System.out.println("Using FIFO Page Replacement:\n");

        for (int page : pages) {
            if (!frames.contains(page)) {
                if (frames.size() == frameCount) {
                    frames.poll(); // remove oldest page
                }
                frames.add(page);
                pageFaults++;
                System.out.println("Page " + page + " -> Page Fault | Frames: " + frames);
            } else {
                System.out.println("Page " + page + " -> No Fault   | Frames: " + frames);
            }
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
    }
}
