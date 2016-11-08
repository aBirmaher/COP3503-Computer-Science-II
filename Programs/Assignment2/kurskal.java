//Alan Birmaher
//COP 3503
//Assignment 2: Kurskal
//6/03/14

//Import Libraries
import java.util.*;
import java.io.*;

public class kurskal{
	
	//Constructor of disjoint set object
	public kurskal (int numPoints){
		
		//New array of the size of the number of points
		array= new int[numPoints];
		
		//Set whole array's values to -1
		for(int i=0; i<array.length; i++){
			array[i]=-1;
		}
		
	}
	
	//Union Function
	public void union(int rootA, int rootB){
		
		//If root 2 is deeper
		if(array[rootB]<array[rootA]){
			
			//Makes rootB the new root
			array[rootA]=rootB;
			
		}
		
		//If root 1 is deeper
		else{
			
			//If they are equal
			if(array[rootA]==array[rootB]){
				
				//Subtract one from the value at rootA in array
				array[rootA]--;
				
			}
			
			//Make rootA the new root
			array[rootB]=rootA;
			
		}
		
	}
	
	//Find function
	public int find(int value){
		
		//If the value has not yet been assigned
		if(array[value]<0){
			
			//Returns the value
			return value;
			
		}
		
		//If it has been assigned
		else{
		
			//Returns location
			return array[value]=find(array[value]);
			
		}
		
	}
	
	//Function to calculate distance
	public static double weight(int x2, int y2, int x1, int y1){
		
		//Does calculation for x and for y
		double xDist= x2-x1;
		double yDist=y2-y1;
		
		//Returns value
		return Math.sqrt((xDist)*(xDist)+(yDist)*(yDist));
		
	}
	
	//Function to calculate how many edges can exist
	public static int edgeNum(int numPoints){
		
		//Returns number of edges
		return ((factorial(numPoints))/((factorial(numPoints-2))*(2)));
		
	}
	
	//Calculates factorial values
	public static int factorial(int n) {
		
		//Variables
        int value = 1, i;
        
        //Calculate factorial value
        for (i = 1; i <= n; i++) {
            value *= i;
        }
        
        //Returns value
        return value;
    }
	
	//Set up array
	private int[] array;
	
	//Main
	public static void main(String[] args){
		
		//Setup Scanner
		Scanner stdin = new Scanner(System.in);
		
		//Variables
		int i, numPoints=-1, numEdges, j;
		double totalWeight=0;
		
		//While loop
		while(numPoints!=0){
			
			//Read in first number of points
			numPoints=stdin.nextInt();
			
			//Create new 2D array to hold point location
	       // kurskal disjointSet = new kurskal( numPoints );
	        
	        //Array to store points
	        int[][] points= new int[numPoints][3];
			
			//For Loop that reads in locations of the points
			for(i=0;i<numPoints; i++){
				
				//Read in X and Y values
				points[i][0]=stdin.nextInt();
				points[i][1]=stdin.nextInt();
				
				//Sets arbitrary location value
				points[i][2]=i;
				
			}
			
			//Find number of potential edges
			numEdges= edgeNum(numPoints);
			
			//Array to store edges
	        int[][] edges= new int[numEdges][2];
	        
	        //Array to store weight of edges
	        double[] edgeWeight= new double[numEdges];
			
	        //Sets counter
	        int count=0;
	        
			//Store all possible combinations of values and the weight of the edge
	        for(i=0;i<numPoints; i++){
	        	for(j=i+1;j<numPoints;j++){
	        		
	        		//Saves values of endpoints of edges
	        		edges[count][0]=i;
	        		edges[count][1]=j;
	        		
	        		//Calculates weight and stores it
	        		edgeWeight[count]=weight(points[i][0], points[j][0], points[i][1], points[j][1]);
	        		
    				//Increments the count
	        		count++;
	        				
	        	}
	        }
	        
	        //Sort edges
	        
	        //Work out way from lowest to highest list of edges, if an edge between those 2 does not yet exist add that edge weight to totalWeight
	        
	        //To verify we print in the right case
			if(numPoints!=0){
				
				//Print value
				System.out.printf("%.2f\n", totalWeight);
			
			}
			
		}
		
	}

}