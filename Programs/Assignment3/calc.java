//Alan Birmaher
//COP 3503
//Program 3: Calc
//6/20/2014

//Import libraries
import java.util.*;

//Set up points class
class points {

	//Set up points variables
	public int value, distance;

	//Sets values from incoming to set values
	public points(int val, int dist) {
		value = val;
		distance = dist;
	}
}

//Calc Class
public class calc {

	//Calculate Function which uses BFS to find the results to print
	public static int calculate(int start) {

		//Variables
		int numVisited = 0;
		int maxVal = 0;
		int testVal=0;
		
		//Set up queue
		LinkedList<points> q = new LinkedList<points>();
		
		//Set up visited array
		int[] visited = new int[100];
				
		//Adds the first entry to our queue
		q.add(new points(start, 0));
		
		//Sets all values in array to unvisited
		for (int i=0; i<100; i++){
		
			//Set to unvisited (-1)
			visited[i] = -1;
			
		}

		//While there is elements in the queue and not all nodes
		//have been visited ()
		while (q.size() > 0 && numVisited < 99) {

			//Grabs the next value in the queue and stores it to p
			points pt = q.poll();

			//If pt value is between 0 and 100
			if (pt.value > 0 && pt.value < 100) {

				//If the location of pt has not been visited
				if (visited[pt.value] == -1) {
					
					//Sets value at visited for the point value to pt distance
					visited[pt.value] = pt.distance;
					
					//Increments number of visited location by 1
					numVisited++;
					
					//Sets maxVal to be the current distance
					maxVal = pt.distance;

				}
			}

			//Calculates the value plus 1
			testVal = pt.value + 1;
			
			//If this value is between 0 and 100 and is unvisited
			if (testVal > 0 && testVal < 100 && visited[testVal] == -1)
				q.add(new points(testVal, pt.distance+1));
			
			//If the value is between 0 and the max value of 1000000000
			else if (testVal > 0 && testVal < 1000000000)
				q.add(new points(testVal, pt.distance+1));

			//Calculates the value times 3
			testVal = 3*pt.value;
			
			//If this value is between 0 and 100 and is unvisited
			if (testVal > 0 && testVal < 100 && visited[testVal] == -1)
				q.add(new points(testVal, pt.distance+1));
			
			//If the value is between 0 and the max value of 1000000000
			else if (testVal > 0 && testVal < 1000000000)
				q.add(new points(testVal, pt.distance+1));

			//Calculates the value divided by 2
			testVal = pt.value/2;
			
			//If this value is between 0 and 100 and is unvisited
			if (testVal > 0 && testVal < 100 && visited[testVal] == -1)
				q.add(new points(testVal, pt.distance+1));
			
			//If the value is between 0 and the max value of 1000000000
			else if (testVal > 0 && testVal < 1000000000) 
				q.add(new points(testVal, pt.distance+1));

		}

		//Returns the value to print
		return maxVal;
	}
	
	//Main Function
	public static void main(String[] args) {

		//Set up scanner
		Scanner stdin = new Scanner(System.in);

		// Reads in the number of cases to solve
		int numTests = stdin.nextInt();
		
		//Loop to go through correct number of times
		for (int i=0; i<numTests; i++) {

			//Reads in starting point for this case
			int startVal = stdin.nextInt();
			
			//Calculates the value starting at the start value and prints it
			System.out.println(calculate(startVal));
			
		}
	}
}