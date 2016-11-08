//Chris Grenci & Alan Birmaher
//COP 3503
//RP8
//7/28/14

//Import Libraries
import java.util.*;

//Birdman class
public class birdman {

	//Point structure
	static class point{
		int x,y;
	}
	
	//Birdcheck function
	public static boolean birdCheck(point B, point S, point P1, point P2){
		
		//Variables
		boolean result;
		int L1_dirX, L1_dirY, L2_dirX, L2_dirY, c1, c2;
		int det1, det2, detDenom;
		double ans1, ans2;
		
		// Calculate and store L1 values in the x and y direction
		L1_dirX = S.x - B.x;
		L1_dirY = S.y - B.y;
		
		// Calculate and store L2 values in the x and y direction
		L2_dirX = P1.x - P2.x;
		L2_dirY = P1.y - P2.y;
		
		// Calculate and store C1 and C2 values
		c1 = P1.x - B.x;
		c2 = P1.y - B.y; 
		
	
		//Calculates the denominator for the determinants
		detDenom = (L1_dirX * L2_dirY) - (L1_dirY * L2_dirX);
		
		//If the denominator is zero, either coincidental or parallel
		if(detDenom == 0){
			
			//Calculates and stores value for evaluation
			ans1 = c1/(double)L1_dirX;
			
			//If , result to be returned is false
			if(ans1 >= 0 && ans1 <= 1)
				result = false;
			
			//Otherwise, result to be returned is true
			else
				result = true;
			
		}
		
		//Non coincidental or parallel
		else{
			//Calculates the top of the determinate for the first one
			
			det1 = c1*(L2_dirY) - c2*(L2_dirX);
			
			//Calculates the top of the determinate for the second one
			det2 = (L1_dirX)*c2 - (L1_dirY)*c1;		
			
			//Calculates and stores values for evaluation
			ans1 = (double)det1/detDenom;
			ans2 = (double)det2/detDenom;
			
			//If , result to be returned is false
			if((ans1 >= 0 && ans1 <= 1) && (ans2 >= 0 && ans2 <= 1))
				result = false;
			
			//Otherwise, result to be returned is true
			else
				result = true;
			
		}
		
		//Returns result value which yields the correct output in the main
		return result;
		
	}
	
	//Main
	public static void main(String args[]){
		
		//Scanner
		Scanner stdin= new Scanner(System.in);
		
		//Read in number of cases
		int pictureNum= stdin.nextInt();
		point B, S, P1, P2; 
		boolean result;
		
		//Loops for the appropriate number of cases
		for(int i=0; i< pictureNum; i++){
			B = new point();
			S = new point();
			P1 = new point();
			P2 = new point();
			B.x = stdin.nextInt();
			B.y = stdin.nextInt();
			S.x = stdin.nextInt();
			S.y = stdin.nextInt();
			P1.x = stdin.nextInt();
			P1.y = stdin.nextInt();
			P2.x = stdin.nextInt();
			P2.y = stdin.nextInt();
			
			//Finds the result of the check
			result= birdCheck(B, S, P1, P2);
			
			if(result)
				System.out.println("Good picture, Birdman!");
			
			else
				System.out.println("Move to the left or right!");
				
				
		}
		
		
	}
	
}
