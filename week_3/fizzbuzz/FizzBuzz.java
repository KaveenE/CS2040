package week_3.fizzbuzz;

import java.util.Scanner;

public class FizzBuzz 
{
	public static void main(String[] args)
	{
		Scanner scan=new Scanner(System.in);
		
		int[] input=new int[3]; //using 3 input variables

		//Reading input
		String[] string=scan.nextLine().split(" ");
	
		for(int i=0;i<=string.length-1;i++)
		{
			//either use parse int or Integer.valueOf()
			input[i]=Integer.parseInt(string[i]);
		}
		
		//Decision logic.
		for(int i=1;i<=input[input.length-1];i++)
		{
			if( isDivisible(i, input[0]) && isDivisible(i, input[1]) )
				System.out.println("FizzBuzz");	
			else if( isDivisible(i, input[0]) )
				System.out.println("Fizz");
			else if( isDivisible(i, input[1]) )
				System.out.println("Buzz");
			else
				System.out.println(i);
		}
		
		scan.close();
	}
	
	public static boolean isDivisible(int n,int x)
	{
		return n%x==0? true:false;
			
	}
	
}
