//Chris Grenci & Alan Birmaher
//COP 3503
//Assignment 1
//5/14/14

//Import Libraries
import java.util.*;
import java.io.*;


//Target Class
public class target {
    
    //Main
	public static void main(String[] args){
        
        //Variables
		Scanner stdin = new Scanner(System.in);
		int target, numEntries, numTests;
		numTests = stdin.nextInt();
		int[] set = new int[1000000];
        boolean noflag = false;
        
        //Main loop for number of tests
		for(int j = 0; j < numTests; j++){
            
            //Input reading
			numEntries = stdin.nextInt();
			target = stdin.nextInt();
            
            //Reading in of set of test numbers
			for(int k = 0; k < numEntries; k++){
				set[k] = stdin.nextInt();
			}
            
            
			int tail = numEntries-1, head = 0, sum = 0;
            
            //Inner loop
			for (int i = 0; i < numEntries; i++){
				
                //Calculates sum
                sum = set[head]+set[tail];
                
                //If sum > target move tail down
				if(sum>target){
					tail--;
					noflag = true;
					if(head == tail){
						break;
					}
				}
                
                //If sum < target move head up
				else if(sum<target){
					head++;
					noflag = true;
					if(head == tail){
						break;
					}
				}
                
                //IF sum and target are equal and target is found
				else{
					System.out.println("YES");
					noflag = false;
					break;			
				}
			}
            
            //Prints if target not found
			if(noflag) System.out.println("NO");
        }
        
	}
    
}//END
