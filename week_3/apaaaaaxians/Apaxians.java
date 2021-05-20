package week_3.apaaaaaxians;
import java.util.Scanner;

public class Apaxians {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		Scanner scan=new Scanner(System.in);
		String string=scan.nextLine();
		
		//Realized my methods are ill equipped for string length of 1
		//Anw we know if the string is of length 1, output is just that string itself
		if(string.length()!=1)
		{
			solve1(string);
			//solve2(string);
		}
		else
		{
			System.out.println(string);
		}
		
		scan.close();
	}
	
	//Lame way but efficient way lol
	public static void solve1(String string)
	{		
		int i;
		char prevLetter=' ';
		for(i=0;i<=string.length()-1;i++)
		{
			//print out current letter only if not duplicate of prev letter
			if( string.charAt(i)!=prevLetter )
			{
				System.out.print(string.charAt(i));
			}
			
			prevLetter=string.charAt(i);
		}
		
		System.out.println();
		
	}
	
	// Cooler way  PS:Stringbuilder won't help as we in the end have to change the value of string itself
	//But very inefficient since we're creating alot of strings in an attempt to modify it
	//Plus goes thru the letters that we have replaced already. 
	
	//Eg: if you see 'k' and replace all of k(s), then you see a 'k' and replace again(inefficient)
	public static void solve2(String string)
	{
		String stringToModify=new String(string);
		
		int i;
		for(i=0;i<=string.length()-2;i++)
		{
			//print out only if not duplicate of previous letter
			if( string.charAt(i+1)==string.charAt(i) )
			{
				stringToModify = stringToModify.replaceAll(string.charAt(i)+"+", string.charAt(i)+"") ;
			}
				
		}
		
		//ATP haven't tested last letter since there is no succeeding letter to test it with
		//Use example to understand what I mean
		
		//if last letter  same as the 2nd last print it, replaceAll it
		if(string.charAt(i)==string.charAt(i-1))
		{
			stringToModify = stringToModify.replaceAll(string.charAt(i)+"+", string.charAt(i)+"") ;
		}
		
		System.out.println(stringToModify);
	}

}
