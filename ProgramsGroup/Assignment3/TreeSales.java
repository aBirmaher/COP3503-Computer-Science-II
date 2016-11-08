//Alan Birmaher, Christopher Grenci
//COP 3503
//Assignment RP3: TreeSales
//6/9/14

//Import Libraries
import java.util.*;
import java.io.*;

public class TreeSales {

	//Main
	public static void main(String[] args){
		
		//Setup Scanner
		Scanner stdin = new Scanner(System.in);
		
		//Variables
		int i, j, operationNum, hashVal, parentVal, sale;
		String next, newEntry;
		Hashtable<Integer, Employee> valueStore; 
		
		//Read in number of companies to analyze
		int numCompanies= stdin.nextInt();
		
		//Loop through for each company
		for (i=0; i< numCompanies; i++){
			
			//Prints the number of the company
			System.out.println("COMPANY "+(i+1));
			
			//Number of operations for current company
			operationNum=stdin.nextInt();
			
			//Create hashtable for information storage
			valueStore = new Hashtable<Integer, Employee>(operationNum);
			
			//Execute the operations for the current company
			for(j=0;j<operationNum; j++){
				
				//Read in operation string
				next=stdin.next();
				
				//Add new employee
				if(next.compareTo("ADD")==0){
					
					//Read in parent
					next=stdin.next();
					
					//Read in new name
					newEntry = stdin.next();
					
					//If the new employee is the root
					if(next.compareTo("ROOT")==0){
						
						//Add new entry into our hashtable
						valueStore.put(newEntry.hashCode(), new Employee(newEntry, newEntry.hashCode()));
					}
					
					//If the new employee has a parent and is not a root
					else{
						
						//Find parent's hash value
						parentVal = next.hashCode();
						
						//Add new entry into our hashtable
						valueStore.put(newEntry.hashCode(), new Employee(newEntry, parentVal));
						
					}
					
				}
				
				//Add new sale
				else if(next.compareTo("SALE")==0){
					
					//Read in seller's name
					next=stdin.next();
					
					//Read in sale amount
					sale = stdin.nextInt();
					
					//Calculates hash value of the seller
					hashVal = next.hashCode();
					
					//Calculates the hash value of the parent
					parentVal = valueStore.get(hashVal).parent;
					
					//If the hash value is the same as the parent value
					if(hashVal!=parentVal){
						
						//Adds sale amount to current person
						valueStore.get(hashVal).addSale(sale);
						
						//If the hash value is the same as its parent's
						while(hashVal!= valueStore.get(hashVal).parent){
							
							//Adds the value of the sale to parent
							valueStore.get(parentVal).addSale(sale);
							
							//Makes the new curent the parent
							hashVal = parentVal;
							
							//Saves the hash value of the parent as parent value
							parentVal = valueStore.get(hashVal).parent;

						}
					}
					
					//if hash value isn't the same as the parent value
					else{
						
						//Adds sale amount to the current
						valueStore.get(hashVal).addSale(sale);
						
					}
					
				}
				
				//If we are querying for someone
				else if(next.compareTo("QUERY")==0){
					
					//Read person to query
					next=stdin.next();
					
					//Print out the sale value for the queried person
					System.out.println(valueStore.get(next.hashCode()).sales);
					
				}
				
			}
			
		}
		
	}

}

//Employee class
class Employee{
	
	//Variables
	String name;
	int sales;
	Integer parent;
	
	//Employee constructor
	public Employee(String n, Integer p){
		
		//Sets values
		name = n;
		parent = p;
		sales = 0;
		
	}
	
	//Method to add sale value to employee
	public void addSale(int s){
		
		//Adds value to sales for that employee
		sales += s;
		
	}
	
}