//Alan Birmaher
//COP 3503
//Assignment 1: Sudoku
//5/20/14

//Import Libraries
import java.util.*;
import java.io.*;

public class sudoku {
	
	//Finds the next empty location, if one exists
	private static int[] nextLocation(int[][] board) {
		
		//Variables
		int row, col;
		
		for(row=0; row<9; row++){
			for(col=0;col<9; col++){
				
				if(board[row][col]==0){
					int[] a={row, col};		//Puts row and col values in array
					return a;				//blank at board[row][col], pass row & col values
				}
				
			}
		}
		
		//Creates array for returning no more blanks
		int[]a= {-1,-1};
		
		//No more blank boxes
		return a;	
		
	}
	
	//Checks to see if any values in the row don't work with value
	public static boolean rowCheck(int[][] board, int row, int value){
		
		//Loops through checking values
		for(int col=0; col< 9; col++){
			
			if(board[row][col]==value)
				return true;	//Conflict!
			
		}
		
		return false;	//No issue
		
	}
	
	//Checks to see if any values in the column don't work with value
	public static boolean colCheck(int[][] board, int col, int value){
	
		//Loops through checking values
		for(int row=0; row< 9; row++){
			
			if(board[row][col]==value)
				return true;	//Conflict!
			
		}
		
		return false;	//No issue
		
	}
	
	//Checks to see if any values in the box don't work with value
	public static boolean boxCheck(int[][] board, int startRow, int startCol, int value){
		
		//Loops through checking values
		for (int row = 0; row < 3; row++){
	        for (int col = 0; col < 3; col++){
	        	
	            if (board[row+startRow][col+startCol] == value)
	                return true;	//Conflict!
	            
	        } 
		}
		
	    return false;	//No issue
		
	}
	
	//Checks whether values are possible
	public static boolean issueCheck(int[][] board, int row, int col, int value) {
		
		//Checks for each of the 3 checks for validity of value
		return (!rowCheck(board, row, value) && !colCheck(board, col, value) 
				&& !boxCheck(board, row-row%3, col-col%3, value));
	
	}
	
	//Solve function
	public static boolean solve(int[][] board){
		
		//Variables
		int row=0, col=0, i=0;
		int[] next;
		
		//Saves the return from nextLocation function into next array
		next= nextLocation(board);
		
		//If we returned {-1, -1} we are done with the board
		if (next[0]==-1 && next[1]==-1)
			return true;	//Board is done
		
		//If we don't return {-1,-1}, the values are out next space
		else{
			
			row=next[0];	//Value of next[0] is new value for row
			col=next[1];	//Value of next[1] is new value for col
			
		}

		//Check possible values
		for (i=1; i<=9; i++){
			
			//Checks for errors
			if(issueCheck(board, row, col, i)){
				
				//Temporary value assignment
				board[row][col]=i;
				
				//Check for success
				if(solve(board))
					return true;
					
				//Failed, undo and reset
				board[row][col]=0;
				
			}
			
		}
		
		//Backtracking triggered
		return false;
		
	}
	
	//Main function
	
	//Main
	public static void main(String[] args){
		
		//Setup Scanner
		Scanner stdin = new Scanner(System.in);
		
		//Variables
		int numPuzzles= stdin.nextInt();
		int[][] board = new int[9][9];
		int row, col, i;
		
		//Loop through for each puzzle to solve it
		for (i=0; i< numPuzzles; i++){
			
			//Store values into 2D array
			for(row=0; row<9; row++){
				for(col=0; col<9; col++){
					
					board[row][col]= stdin.nextInt();
					
				}
			}
			
			//If solution Exists print it
			if(solve(board) == true){
				System.out.println("Test case: "+(i+1)+"\n");
				for(row=0; row<9; row++){
					for(col=0; col<9; col++){
						System.out.print(board[row][col]+" ");
					}
					System.out.println();
				}
			}
			
			//If no solution exists print it
			else{
				
				System.out.println("Test case "+(i+1)+"\n\nNo solution possible.");
				
			}
			
			//Next line
			System.out.println("\n"); 
			
		}
		
	}

}