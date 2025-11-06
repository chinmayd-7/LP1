import java.util.*;

public class OptimalPageReplacement {
    public static void main(String[] args) {
        int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2};
        int frameCount = 4;
        ArrayList<Integer> frames = new ArrayList<>();
        int pageFaults = 0;

        System.out.println("Page Reference String: " + Arrays.toString(pages));
        System.out.println("Using Optimal Page Replacement:\n");

        for (int i = 0; i < pages.length; i++) {
            int page = pages[i];

            if (!frames.contains(page)) {
                if (frames.size() == frameCount) {
                    int farthest = -1, indexToReplace = -1;

                    for (int j = 0; j < frameCount; j++) {
                        int currentPage = frames.get(j);
                        int nextUse = Integer.MAX_VALUE;

                        for (int k = i + 1; k < pages.length; k++) {
                            if (pages[k] == currentPage) {
                                nextUse = k;
                                break;
                            }
                        }

                        if (nextUse > farthest) {
                            farthest = nextUse;
                            indexToReplace = j;
                        }
                    }

                    frames.set(indexToReplace, page);
                } else {
                    frames.add(page);
                }

                pageFaults++;
                System.out.println("Page " + page + " -> Page Fault | Frames: " + frames);
            } else {
                System.out.println("Page " + page + " -> No Fault   | Frames: " + frames);
            }
        }

        System.out.println("\nTotal Page Faults = " + pageFaults);
    }
}
