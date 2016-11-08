//Alan Birmaher
//COP 3503
//Program 6: Tolls
//7/22/2014

//Import libraries
import java.util.*;

//Tolls class
public class tolls {
	
	//Function finds the quickest way and returns the cheapest possible cost
		public static int tollCalc(int numTolls, int[] between, int[][] tollPrices) {
			
			//Variables
			int cheapest=-1;
			int[] lowestCost= new int[60];
			
			//Go through each possible start time and store values
			for(int j=0; j< 60; j++){
				
				//Saves current time
				int time= j;
				int price= 0;
				
				//Cycle through all tolls
				for(int tollNum=0; tollNum< numTolls; tollNum++){
					
					//If its early toll time
					if(time < 30){
						
						//Find time to wait until late toll
						int tillLate= 30-time;
						int newPrice= tollPrices[tollNum][1]+tillLate;
						
						//If time till late and cost of late toll is less than the cost of early toll
						if(newPrice<tollPrices[tollNum][0]){
							
							//Add minutes necessary for it to be later time
							time+= tillLate;
							
							//Add how much it was to price
							price+= newPrice;
							
						}
						
						//If the cost of the later toll is not less
						else{
							
							//Take the toll now and add price to counter
							price+= tollPrices[tollNum][0];
							
						}
						
					}
					
					//If its late toll time
					else{
						
						//Find time to wait until early toll
						int tillEarly= 60-time;
						int newPrice= tollPrices[tollNum][0]+tillEarly;
						
						//If time till early and cost of early toll is less than the cost of late toll
						if(newPrice<tollPrices[tollNum][1]){
							
							//Add minutes necessary for it to be later time
							time+= tillEarly;
							
							//Add how much it was to price
							price+= newPrice;
							
						}
						
						//If the cost of the later toll is not less
						else{
							
							//Take the toll now and add price to counter
							price+= tollPrices[tollNum][1];
							
						}
						
					}
					
					//Add time for between tolls
					if(between.length<tollNum)
						time += between[tollNum];
					
					//Wrap around if we move to the next hour
					if(time>59)
						time%=60;
					
				}
				
				//Store value into array to be compared after
				lowestCost[j]= price;
				
			}
			
			//Find lowest cost and store it to be returned
			for(int j=0; j< 60; j++){
				
				//If the value in the location is less than current lowest, replace it
				if(cheapest<0 || lowestCost[j]<cheapest)
					cheapest= lowestCost[j];
				
			}
		
			//Return cheapest value
			return cheapest;
			
		}
	
	//Main Function
		public static void main(String[] args) {

			//Set up scanner
			Scanner stdin = new Scanner(System.in);

			// Reads in the number of cases to solve
			int numTests = stdin.nextInt();
			
			//Loop to go through correct number of times
			for (int i=1; i<=numTests; i++) {
				
				//Reads in number of tolls
				int numTolls=stdin.nextInt();
				
				//Sets up time between tolls array
				int[] between= new int[numTolls-1];
				
				//Sets up 2d array of early and late toll price for each
				int[][] tollPrice= new int[numTolls][2];
				
				//Read in value for between toll times
				for(int j=0; j<numTolls-1; j++){
					
					//Stores values in to the between array
					between[j]= stdin.nextInt();
				}
				
				//Read in values for toll prices
				for(int j=0; j<numTolls; j++){
					
					//Read in and store early toll
					tollPrice[j][0]= stdin.nextInt();
					
					//Read in and store late toll
					tollPrice[j][1]= stdin.nextInt();
					
				}
				
				//Calculates the value starting at the start value and prints it
				System.out.println("Case #" + i + ": " + tollCalc(numTolls, between, tollPrice));
				
			}
		}

}
