//Alan Birmaher
//COP 3503
//Program 5: ZigZag
//7/41/2014

//Library Import
import java.util.*;

public class zigzag {
	
	public static int calculate(List <Integer> sequences){
		
		//If the sequence is 2 digits or less, return its size
		if(sequences.size()<2)
			return sequences.size();
		
		//Variables and lists
		int max=0;
		boolean isItZigZag;
		List<Integer> lengths= new ArrayList<Integer>();
		List<List<Integer>> last= new ArrayList<List<Integer>>();
		List<Integer> lastDifferentSign= new ArrayList<Integer>();
		
		//Adds 1 to location zero of list of lengths
		lengths.add(0, 1);
		
		//Values are added to last list
		for(int i=0; i<sequences.size();i++){
			
			//Adds new entry to the list last
			last.add(i, new ArrayList<Integer>());
		
		}
		
		//Value modification
		last.get(0).add(0);
		
		//Adds the value at location 0 of the lastDifferentSign list
		lastDifferentSign.add(0, 0);
		
		//Initiates the max value to 1
		max = 1;
		
		//Go through the sequence
		for(int i=1; i<sequences.size();i++){
			
			//If we have 1 equal neighboring values
			if(sequences.get(i)==sequences.get(i-1)){
				
				//Adds length of previous on list to location i in lengths list
				lengths.add(i, lengths.get(i-1));
				
				//Adds prior values from last to location i
				last.get(i).addAll(last.get(i-1));
				
				//Value set at location i in list last
				last.get(i).add(i);
				
				//Value of previous location from lastDifferentSign list set to position i
				lastDifferentSign.add(i, lastDifferentSign.get(i-1));
				
			}
			
			//If the neighbors are not equal
			else{
			
				//Sets value to false as a starting point for the flag
				isItZigZag= false;
				
				//Go through the values
				for (Integer j : last.get(i-1)){
					
					//Value of the difference between value at positions i and j is stored
					int difference= sequences.get(i)-sequences.get(j);
					
					//Checks to see if this is a zig zag
					if((difference>0 && lastDifferentSign.get(i-1)<=0) || (difference<0 && lastDifferentSign.get(i-1)>=0)){
					
						//Saves value into lengths position i
						lengths.add(i, lengths.get(i-1)+1);
						
						//Sets value at location i in list last
						last.get(i).add(i);
						
						//Calculates and stores value into location i of lastDifferentSign list
						lastDifferentSign.add(i, difference/Math.abs(difference));
						
						//If value is greater than the max then it should be the next max
						if(lengths.get(i)>max){
							
							//Sets max to have the value of the value at lengths at position i
							max= lengths.get(i);
							
						}
					
						//Sets flag to true
						isItZigZag= true;
						
						//Break out of loop
						break;
					
					}
				
					//If it is not a zig zag
					if(!isItZigZag){
						
						//Value at i-1 at lengths saved in location i
						lengths.add(i,lengths.get(i-1));
						
						//Value at i-1 at last saved in location i
						try{
							last.get(i).addAll(last.get(i-1));
						}
						
						catch(OutOfMemoryError e){
							return -1;
						}
						
						//Value set at location i in list last
						last.get(i).add(i);
						
						//Value of previous location from lastDifferentSign list set to position i
						lastDifferentSign.add(i, lastDifferentSign.get(i-1));
						
						
					}
					
				}
			
			}
			
		}
		
		//Return max value to print
		return max;
	}
	
	//Main Function
	public static void main(String[] args) {
		
		//Set up scanner
		Scanner stdin = new Scanner(System.in);
		
		//Setup variables to hold read in values
		int howManyCases=stdin.nextInt();
		int howManyNums=0;
		
		//Set up list
		List <Integer> sequences= new ArrayList<Integer>();
		
		//Loops through all test cases
		for(int i=0; i<howManyCases;i++){
			
			//Reads in how many numbers in the current sequence
			howManyNums= stdin.nextInt();
			
			//Clears sequences array
			sequences.clear();
			
			//Reads the sequence values in an array
			for(int j=0; j<howManyNums;j++){
				
				sequences.add(j, (Integer)stdin.nextInt());
				
			}
			
			//Gets result
			int result=calculate(sequences);
			
			//If we had a memory issue
			if(result==-1)
				System.out.println();
			
			//Prints output if no issues
			else
				System.out.println(result);
			
		}
		
	}
	
}