package pickyourtrail;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * MudWalls class
 * 
 * @author Muthukumar PL
 *
 */
public class MudWalls {
	
	private static final Logger log = Logger.getLogger(MudWalls.class.getName());
	
	public static void main(String[] args) {
	Scanner scanner = new Scanner(System.in);
	int numberOfSticks = scanner.nextInt();
	int[] stickPositions = new int[numberOfSticks];
	for(int i=0; i < numberOfSticks; i++) {
		stickPositions[i] = scanner.nextInt();
	}
	
	int numberOfStickHeights = scanner.nextInt();
	int[] stickHeights = new int[numberOfStickHeights];
	for(int i=0; i < numberOfStickHeights; i++) {
		stickHeights[i] = scanner.nextInt();
	}
	
	int maxHeight = getMaxHeight(stickPositions, stickHeights);
	System.out.println(maxHeight);
	
	}

	/**
	 * getMaxHeight is used to get the maxHeight of the sticks.
	 * 
	 * @param stickPositions
	 * @param stickHeights
	 * @return maxHeight
	 */
	private static int getMaxHeight(int[] stickPositions, int[] stickHeights) {
		log.info("getMaxHeight method starts");
		int numberOfPositions = stickPositions.length;
        int max = 0;
 
        log.info("Iterating through the Sticks");
        for (int i=0; i<numberOfPositions-1; i++) {
            if (stickPositions[i]<stickPositions[i+1]-1) {
                // When gap is present between adjacent sticks.
                int heightDiff =  Math.abs(stickHeights[i+1] - stickHeights[i]);
                int gapLen = stickPositions[i+1] - stickPositions[i] - 1;
                int localMax = 0;
                if (gapLen > heightDiff) {
                    int low = Math.max(stickHeights[i+1], stickHeights[i]) + 1;
                    int remainingGap = gapLen - heightDiff - 1;
                    localMax = low + remainingGap/2;
 
                } else {
                    localMax = Math.min(stickHeights[i+1], stickHeights[i]) + gapLen;
                }
                max = Math.max(max, localMax);
            }
        }
        log.info("getMaxHeight method ends");
        return max;
    }

}
