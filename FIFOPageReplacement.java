import java.util.*;
public class FIFO {
    public static void main(String args[]){
        ArrayList<Integer> frames=new ArrayList<>();
        int pages[]={1, 3, 0, 3, 5, 6, 3};
        int frameCount=3;
        int pageFaults=0;
        int indexToReplace=0;
        for(int page: pages){ 
                if(!frames.contains(page)){
                    if(frames.size()==frameCount){
                        frames.set(indexToReplace,page); 
                        indexToReplace=(indexToReplace+1)%frameCount;
                    }else{
                        frames.add(page);
                    }
                    pageFaults++; 
                    
                }
                System.out.println(frames.toString());
        }
        System.out.println(pageFaults);
    }
}
