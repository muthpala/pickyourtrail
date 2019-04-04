package pickyourtrail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * OfficeParty class
 * 
 * @author Muthukumar PL
 *
 */
public class OfficeParty {
	
	private static final Logger log = Logger.getLogger(OfficeParty.class.getName());
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("The number of cakes: ");
		int numberOfCakes = scanner.nextInt();
		System.out.println("The radius of the cakes:");
		int[] radii = new int[numberOfCakes];
		for(int i=0;i<numberOfCakes;i++) {
			radii[i] = scanner.nextInt();
		}
		System.out.println("The number of guests: ");
		int guests = scanner.nextInt();
		
		String value = largestPiece(radii,guests);
		System.out.println("The largest piece is of " + value + " area");
	}
	
	/**
	 * largestPiece is used to determine the largest piece.
	 * 
	 * @param radii
	 * @param guests
	 * @return largestPiece
	 */
	private static String largestPiece(int[] radii, int guests) {
		log.info("largestPiece method starts");
		float pi = 3.14159265359f;
		
		log.info("finding area of cakes from given radii");
		float[] areaList = new float[radii.length];
		for(int i=0;i<radii.length;i++) {
			areaList[i] = (pi * radii[i] * radii[i]);
		}
		
		float largestPiece = getMaxArea(areaList,guests);
		log.info("largestPiece method ends");
		return String.valueOf(largestPiece);
	}

	/**
	 * getLargestCake is used to get the largest area.
	 * 
	 * @param sortedList
	 * @param guests
	 * @return largestCake
	 */
	public static int getLargestCake(Float[] sortedList, int guests) {
		log.info("getLargestCake method starts");
		
	    int result = 0;
	    
	    log.info("Depending on the number of guests finding intermediate value of largest area");
	    for (int i = guests; i >= 1; i--) {
	        double areaDividedByLargestCake = (double) sortedList[0]/ i;
	        int totalNumber = totalNumberofCakeWithGivenArea(
	                sortedList, areaDividedByLargestCake);
	        if (totalNumber < guests) {
	                result = i + 1;
	                break;
	        }
	    }
	    log.info("getLargestCake method ends");
	    return result;
	}


	/**
	 * totalNumberofCakeWithGivenArea is used to return number of cakes with intermediate area
	 * 
	 * @param sortedList
	 * @param givenArea
	 * @return total
	 */
	public static int totalNumberofCakeWithGivenArea(
	        Float[] sortedList, double givenArea) {
		log.info("totalNumberofCakeWithGivenArea method starts");
		
	    int totalNumber = 0;
	    for (float volume : sortedList) {
	        totalNumber += (int) (volume / givenArea);
	    }
	    log.info("totalNumberofCakeWithGivenArea method ends");
	    return totalNumber;
	}

	/**
	 * getMaxArea is used to get the max area
	 * 
	 * @param areaList
	 * @param guests
	 * @return maxArea
	 */
	public static float getMaxArea(float[] areaList, int guests) {
		log.info("getMaxArea method starts");
	    List<Float> list = new ArrayList<Float>();
	    for (float i : areaList) {
	        list.add(i);
	    }
	    
	    log.info("sorting based on area of cakes");
	    Collections.sort(list, Collections.reverseOrder());
	    Float[] sortedList = new Float[list.size()];
	    list.toArray(sortedList);

	    int previousValidGuests = getLargestCake(sortedList, guests);
	    float baseArea =  sortedList[0] / (float) previousValidGuests;

	    int totalNumberofCakeAvailable = totalNumberofCakeWithGivenArea(sortedList, baseArea);

	    if (totalNumberofCakeAvailable == guests) {
	        return baseArea;
	    } else {
	        do
	        {
	            float minimumAmountAdded = minimumAmountAdded(sortedList, baseArea);
	            if(minimumAmountAdded == 0){
	                return baseArea;
	            }else{
	                baseArea += minimumAmountAdded;
	                int newTotalNumber = totalNumberofCakeWithGivenArea(sortedList, baseArea);

	                if(newTotalNumber == guests)
	                {
	                    return baseArea;
	                }else if (newTotalNumber < guests)
	                {
	                    return (baseArea - minimumAmountAdded);
	                }else
	                {
	                    continue;
	                }
	            }

	        }while(true);
	        
	    }
	    
	}

	/**
	 * minimumAmountAdded is used to find the minimum amount
	 * 
	 * @param sortedList
	 * @param area
	 * @return minAmount
	 */
	public static float minimumAmountAdded(Float[] sortedList, float area)
	{
		log.info("minimumAmountAdded method starts");
	    float mimumAdded = Float.MAX_VALUE;
	    for(Float i:sortedList)
	    {
	        int assignedGuests = (int)(i/area);
	        if (assignedGuests == 0)
	        {
	            continue;
	        }
	        float leftPiece = (float)i - assignedGuests*area;

	        if(leftPiece == 0)
	        {
	            continue;
	        }

	        float division = leftPiece / (float)assignedGuests;
	        if (division < mimumAdded)
	        {
	            mimumAdded = division;
	        }
	    }

	    log.info("minimumAmountAdded method ends");
	    if (mimumAdded == Double.MAX_VALUE)
	    {
	        return 0;
	    }else
	    {
	        return mimumAdded;
	    }
	}

}
