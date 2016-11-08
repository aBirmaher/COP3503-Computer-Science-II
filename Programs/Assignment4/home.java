//Alan Birmaher
//COP 3503
//Program 4: Home
//6/28/2014

//Import libraries
import java.util.*;
import java.io.*;

//Stares information for each vertex
class vertex {
	
	//Variable set up
	public Double distance;
	public boolean chosen;
	public int pre;
	
	//Set up vertex object
	public vertex(double d, int source) {
		distance = new Double(d);
		pre = source;
		chosen = false;
	}
}

//Home class
public class home {
	
	public static vertex[] dijkstra(double[][] adjacent, int previous){
		
		//Variables
		int i, j, vert;
		double best= 1000000000;
		
		//Set up array
		vertex[] estimations= new vertex[adjacent.length];
		
		//Initial Estimate Set up
		for(i=0; i<estimations.length; i++){
		
			//Sets values in estimations array
			estimations[i]= new vertex(1000000000, previous);
			
		}
		
		//Sets value of estimations at the previous to 0.0
		estimations[previous].distance=0.0;
		
		//Loops through
		for(i=0; i<estimations.length; i++){
		
			//Set values
			best= 1000000000;
			vert= 0;
			
			//Loop through all values
			for(j=0; j<estimations.length; j++){
			
				//Find distance values smaller than all other previous
				//values and that were not previously visited
				if(estimations[j].distance < best && estimations[j].chosen== false){
				
					//Set best value to the new value
					best= estimations[j].distance;
					
					//Save vertex value as current j
					vert=j;
				
				}
			
			}
			
			//Set this vertex as chosen
			estimations[vert].chosen=true;
			
			//Loop through values to update estimations
			for(j=0; j<estimations.length; j++){
				
				//Check if there's a shorter distance traveling to the vertex, 
				//and then for the edge from the vertex to j
				if(estimations[vert].distance+adjacent[vert][j] <estimations[j].distance){
					
					//Estimation to reach j via vertex
					estimations[j].distance= estimations[vert].distance+adjacent[vert][j];
					
					//Set value of vertex to the pre value of location in estimation array
					estimations[j].pre= vert;
					
				}
				
			}
			
		}
		
		//Return array of estimates
		return estimations;
		
	}

	//Main Function
	public static void main(String[] args) {
		
		//Set up 
		Scanner stdin = new Scanner(System.in);
		
		//Variables
		double[][] adjacent;
		int next, source, end, finish, i, j;
		double roundTemp;
		long finalDigit;
		String path;
		boolean first;

		//Reads in the number of cases to solve
		int numMaps = stdin.nextInt();
		
		//Loop to go through correct number of times
		for (i=1; i<=numMaps; i++) {
			
			//Read in adjacency matrix
			next=stdin.nextInt();
			adjacent= new double[next][next];
			
			//Read values into the matrix
			for(j=0;j<next*next; j++){
				
				//Reads double values from input to location in matrix
				adjacent[j/next][j%next]= stdin.nextDouble();
				
			}
			
			//Get the source value and store
			source= stdin.nextInt();
			
			//Get the end value and store
			end= stdin.nextInt();
			
			//Set the finish value
			finish=end;
			
			//Run Dijkstra and store value back into our returnVal array
			vertex[] returnVal= dijkstra(adjacent, source);
			
			//Print Map number were on
			System.out.println("Map #"+ i);
			
			//Find rounded value for printing
			//Remove values past the second decimal place
			roundTemp= (long)(returnVal[end].distance*100)/(100.0);
			
			//Find third/ final digit
			finalDigit=(long)(returnVal[end].distance*1000*(10e-9))%10;
			
			//Determine to round up or not
			//If last value is greater than or equal to 5 round up and store
			if(finalDigit>=5)
				roundTemp+=0.01;
			
			//Print next chunk of information
			System.out.printf("The shortest distance between %d and %d is %.2f.\n", source, end, roundTemp);
			
			//Reset path string to blank
			path = "";
			
			//Set value of first to true, first time
			first= true;
			
			//Put path from the end into string to print
			while(end!=source){
				
				//If its out first time set the path
				if(first)
					path=end+path;
			
				//If not first time add to the string with next part
				else
					path= (end + "->") + path;
				
				//Set new end value
				end= returnVal[end].pre;
				
				//Set first value to false to not do the same for non first values in the if statement
				first= false;
			
			}
			
			//Add a finally edge from the source to the path we built
			path= source + "->" + path;
			
			//Print out final part for that map
			System.out.println("The shortest path from " + source + " to " + finish + " is " + path + ".\n");
			
		}	

	}
	
}
