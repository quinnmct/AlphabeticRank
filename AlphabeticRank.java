import java.util.Arrays;

/*Class AlphabeticRank
 * *******************
 * Quinn McTiernan
 * *******************
 * This program determines the alphabetical rank of a word based upon all combinations of letters making up the word.
 * If running in Eclipse, be sure to add a word as an argument by selecting Run > Run Configurations > Arguments.
 * If the argument includes numeric values or symbols, the program will order them based on their Ascii values.
 */

public class AlphabeticRank {
	 public static void main (String[] args) {
	    	
	    	String initString = args[0]; //store init string
	    	initString=initString.toLowerCase(); //prevent incorrect ascii values
	    	char initCharArr[] = initString.toCharArray();  //store string as char array to compare ascii values
	    	char sortedCharArr[] = sort(initString.toCharArray()); //sorted char array
	    	
	    	//find alphabetic rank, calculate time
	    	long startNano = System.nanoTime(); //start time
	    	int alphabeticalRank = rank(initCharArr, sortedCharArr);
	    	long timeElapsedNano  = System.nanoTime() - startNano; //finish time

	    	
	    	System.out.println("Alphabetical rank for '" +initString +"' : "+alphabeticalRank);
	    	double timeElapsed = (double)timeElapsedNano/1000000000;
	    	System.out.println("Time Elapsed: "+ timeElapsed +" seconds.");
	    }
	    
	    //recursive alphabetic rank function
	    private static int rank(char[] initCharArr, char[] sortedCharArr) {
	    	if(initCharArr.length == 0){
	    		return 1; //add a single rank for this word itself!
	    	}
	    	
	    	int currentLength = initCharArr.length; //store char[] length
	    	
	    	//find char rank of initCharArr[0]
	    	int chRank=0;
	    	for(int i=0; i<currentLength; i++){
	    		if(sortedCharArr[i] == initCharArr[0]){
	    			chRank = i; 
	    			break;
	    		}
	    	}
	    	
	    	//calculate alphabetic rank of all words before the current index's first letter
	    	int currentRank = ( factorial(currentLength-1) * chRank ) / currentDenominator(initCharArr);
	    		
	    	//Prepare variables for next recursive call:
		    	//1. remove initCharArr[0] from sortedCharArr
		    	String newSortedCharArr = "";
		    	boolean foundLetter = false;
		    	int i=0;
		    	while(foundLetter == false){
		    		if(sortedCharArr[i] == initCharArr[0]){
		    			foundLetter = true;
		    			i++;
		    			while(i < currentLength){
		    				newSortedCharArr += sortedCharArr[i];
		    				i++;
		    			}
		    		}else{
		    			newSortedCharArr += sortedCharArr[i];
		    			i++;
		    		}
		    	}
		    	//2. remove first letter from initial
		    	char newInitCharArr[] = Arrays.copyOfRange(initCharArr, 1, currentLength);
	    	
	    	////RECURSIVE CALL////
	    	return currentRank + rank(newInitCharArr, newSortedCharArr.toCharArray());
		}
		
	    //find duplicates, calculate their factorials
	    private static int currentDenominator(char[] initCharArr) {
			int denom = 1; //if no duplicates, return 1
			int duplicates=1;
			
			if(initCharArr.length>1){
				for(int i=0; i<initCharArr.length-1; i++){
					duplicates=1;
					for(int j=i+1; j<initCharArr.length; j++){
						if(initCharArr[i]==initCharArr[j]){
							duplicates++;
						}
					}
					if(duplicates!=1){ 
						denom *= factorial(duplicates);
						if(duplicates>2){ //fixes duplicate factorials
							denom /= factorial(duplicates-1);
						}
					}
				}
			
			}
			return denom;
		}

		//function calculates factorial
		public static int factorial(int n){
		   if (n == 0) {
		       return 1;
		   }else{
		       return n*factorial(n-1);
		   }
		}

		//simple bubble sort
	    public static char[] sort(char[] c){
	    	int n = c.length;
		    for (int pass=1; pass < n; pass++) { 
		        for (int i=0; i < n-pass; i++) {
		            if (c[i] > c[i+1]) {  // swap
		                char temp = c[i];  c[i] = c[i+1];  
		                c[i+1] = temp;
		            }
		        }
		    }
	    	return c;
	    }
}
