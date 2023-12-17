package code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

    public static void main(String[] args) {

        System.out.println(findWaysToBeat());
        System.out.println(findWaysToBeat2());
    }

    private static int findWaysToBeat() {

        List<Integer> timeList = new ArrayList<>(Arrays.asList(48,93,84,66));
        List<Integer> distanceList = new ArrayList<>(Arrays.asList(261,1192,1019,1063));

        int c=0;
        int count = 1;
        for(int i=0;i<timeList.size();i++){
            int maxTime = timeList.get(i);
            for (int j = 1; j < maxTime; j++) {

                int rem = maxTime - j;
                int dist = rem * j;
                if(dist > distanceList.get(i))
                    c++;

            }
            count *= c;
            c=0;
        }
        return count;

    }

    private static long findWaysToBeat2(){
        long c=0;
        int maxTime = 48938466;
        long maxDist = 261119210191063L;
        for (int j = 1; j < maxTime; j++) {
            int rem = maxTime - j;
            long dist = (long) rem * j;
            if(dist > maxDist) {
                c++;
            }
        }
        return c;
    }

}
