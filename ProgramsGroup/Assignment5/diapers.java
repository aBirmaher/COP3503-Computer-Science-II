//Christopher Grenci & Alan Birmaher
//COP 3503
//RP-5: Diapers
//6/30/2014

//Import libraries
import java.util.*;

//Set up diaperBox class
class diaperBox {

	//Set up points variables
	public int quantity, capacity;
	public double price;

	//Sets values from incoming to set values
	public diaperBox(int howMany, double val, int maxAmnt) {
		quantity = howMany;
		price = val;
		capacity= maxAmnt;
	}
}

public class diapers {
	
	//Main Function
	public static void main(String[] args) {

		//Set up scanner
		Scanner stdin = new Scanner(System.in);

		// Reads in the number of cases to solve
		int numScenario = stdin.nextInt();
		
		//Loop to go through correct number of times
		for (int i=0; i<numScenario; i++) {
			
			//Set up best trackers
			int bestBox=-1;
			double bestPrice=0.0;

			//Read in number of wastes
			int wasteNum= stdin.nextInt();
			
			//Read in number of diaper types
			int diaperTypes= stdin.nextInt();
			
			//Set up array of diaper box objects
			diaperBox[] boxes= new diaperBox[diaperTypes];
			
			//Read in info for each types of diaper
			for(int j=0;j<diaperTypes; j++){
				
				//Create objects and store in array
				boxes[j]= new diaperBox(stdin.nextInt(), stdin.nextDouble(), stdin.nextInt());
				
			}
			
			//Waste Array
			int[] waste= new int[wasteNum];
			
			//Store waste amounts
			for(int j=0; j<wasteNum; j++){
				
				//Read value into array
				waste[j]=stdin.nextInt();
				
			}
			
			//Set flag to know if its the first diaper box
			boolean first= true;
			
			//Cycle through and calculate
			for(int j=0; j<diaperTypes; j++){
				
				//Calculate capacity per box
				int boxCapacity= (boxes[j].quantity)*(boxes[j].capacity);
				
				//Sets up num of boxes counter
				int boxNum= 1;
				
				//Flag to do a check
				boolean notUseable= false;
				
				//Cycle through all the waste
				for(int k=0; k< waste.length; k++){
					
					//If the waste cant fit into a single diaper then it wont work
					if(waste[k]>boxes[j].capacity){
						
						//Set flag
						notUseable=true;
						
					}
					
					//If we run out of capacity add another box of diapers
					if(boxCapacity-waste[k]<0){
						
						//Number of boxes increases
						boxNum+=1;
						
						//Capacity increases
						boxCapacity+=(boxes[j].quantity)*(boxes[j].capacity);
						
					}
					
					//Subtracts necessary waste from available capacity
					boxCapacity-=waste[k];
					
				}
				
				//Calculate how much it would be with current boxes
				double price= ((boxNum)*(boxes[j].price));
				
				//If this is the first box
				if(first && !notUseable){
					
					//Set current values for price and location to best
					bestBox=j+1;
					bestPrice= price;
					first= false;
					
				}
				
				//If not first but better price
				else if(price<bestPrice && !notUseable){
					
					//Set current values for price and location to best
					bestBox=j+1;
					bestPrice= price;
					
				}
				
			}
			
			//Print results
			System.out.printf("Diaper Scenario #%d: Buying box %d, you spend $%.2f.\n", (i+1), bestBox, bestPrice);
			
		}
	}

}
