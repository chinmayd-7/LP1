import java.util.*;

public class LRUPageReplacement {
    public static void main(String[] args) {
        int pages[] = {1, 3, 0, 3, 5, 6, 3};
        int frameCount = 3;
        ArrayList<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("Page Reference String: " + Arrays.toString(pages));
        System.out.println("Using LRU Page Replacement:\n");

        for (int page : pages) {
            if (!frames.contains(page)) {
                if (frames.size() == frameCount) {
                    frames.remove(0); // remove least recently used page
                }
                frames.add(page);
                pageFaults++;
                System.out.println("Page " + page + " -> Page Fault | Frames: " + frames);
            } else {
                // Move page to the most recent position
                frames.remove((Integer) page);
                frames.add(page);
                System.out.println("Page " + page + " -> No Fault   | Frames: " + frames);
            }
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
    }
}
