package pickyourtrail;

import java.util.Scanner;
import java.util.logging.Logger;

/**
 * PalindromeCounter class
 * 
 * @author Muthukumar PL
 *
 */
public class PalindromeCounter {
	
	private static final Logger log = Logger.getLogger(PalindromeCounter.class.getName());
	
	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("The String : ");
		String value = scanner.next();
		int length = value.length();
		int totalPalindromicValues = countSubstrings(value.toCharArray(), length) + length; 
		System.out.println("No of Palindromic Substrings : " + totalPalindromicValues); 
		
	}

	/**
	 * countSubstrings is used to count the number of subStrings.
	 * 
	 * @param charArray
	 * @param length
	 * @return count
	 */
	private static int countSubstrings(char[] charArray, int length) {
		log.info("countSubstrings method starts");
		
		log.info("Creating Empty 2-D matrix");
        int countArray[][] = new int[length][length]; 
        boolean palindromeArray[][] = new boolean[length][length]; 
       
        // palindrome of single length 
        for (int i= 0; i< length; i++) 
        	palindromeArray[i][i] = true; 
       
        // palindrome of length 2 
        for (int i=0; i<length-1; i++) 
        { 
            if (charArray[i] == charArray[i+1]) 
            { 
            	palindromeArray[i][i+1] = true; 
            	countArray[i][i+1] = 1 ; 
            } 
        } 
       
        // Palindromes of length more then 2. 
        for (int gap=2 ; gap<length; gap++) 
        { 
            // Pick starting point for current gap 
            for (int i=0; i<length-gap; i++) 
            { 
                // Set ending point 
                int j = gap + i; 
       
                // If current string is palindrome 
                if (charArray[i] == charArray[j] && palindromeArray[i+1][j-1] ) 
                	palindromeArray[i][j] = true; 
       
                log.info("Adding Palindrome SubString");
                if (palindromeArray[i][j] == true) 
                	countArray[i][j] = countArray[i][j-1] + countArray[i+1][j] + 1 - countArray[i+1][j-1]; 
                else
                	countArray[i][j] = countArray[i][j-1] + countArray[i+1][j] - countArray[i+1][j-1]; 
            } 
        } 
        log.info("countSubstrings method ends");
        return countArray[0][length-1];
	}

}
