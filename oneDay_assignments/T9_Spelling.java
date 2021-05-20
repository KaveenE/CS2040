package oneDay_assignments;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class T9_Spelling {
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		
		Map<Character, Integer> keypadMappings = new HashMap<>();
		establishMappings(keypadMappings);
		
		int outputs = scanner.nextInt();
		scanner.nextLine();
		String desiredOutput;
		
		for(int i = 0; i <= outputs - 1; i++)
		{
			desiredOutput = scanner.nextLine();
			String inputUserMustType = getIntput(desiredOutput, keypadMappings);
			
			System.out.println("Case #"+(i+1)+": "+inputUserMustType);
		}
		
		//System.out.println(keypadMappings);
	}
	
	public static String getIntput(String string, Map<Character, Integer> keypadMappings )
	{
		StringBuilder sb = new StringBuilder();
		char currLetter, nextLetter;
		
		for(int i = 0; i <= string.length() - 2; i++)
		{
			currLetter = string.charAt(i);
			nextLetter = string.charAt(i + 1);
			
			sb.append(keypadMappings.get(currLetter));

			if(haveOneRecurringDigit(keypadMappings.get(currLetter), keypadMappings.get(nextLetter)))
			{
				sb.append(" ");
			}
		}
		
		//Loop doesnt append output of last letter
		sb.append( keypadMappings.get(string.charAt(string.length() -1) ) );
		
		return sb.toString();
	}
	public static void establishMappings(Map<Character, Integer> keypadMappings)
	{
		int i = 1;
		int numberToMapTo = 2;
		
		for(char letter ='a'; letter<= 'z'; letter++)
		{
			keypadMappings.put(letter, repeatDigit(numberToMapTo, i) );
			
			if( ('p' <= letter && letter <= 's') || ('w' <= letter && letter <= 'z') )
			{
				if( i % 4 == 0)
				{
					numberToMapTo++;	
					//Reset since digits are pasted on dials in groups of 4 or 3
					i = 1;
					continue;
				}	
				
			}
			else
			{
				if( i % 3 == 0)
				{
					numberToMapTo++;	
					//Reset since digits are pasted on dials in groups of 4 or 3
					i = 1;
					continue;
				}	
			}
			
			i++;
		}
		
		//Map space to 0
		keypadMappings.put(' ', 0);
	}
	
	
	private static int repeatDigit(int digit, int repetitions)
	{
		int digitCopy = digit;
		repetitions--;
		for(int i=1; i<= repetitions; i++)
		{
			digit *= 10;
			digit += digitCopy;
			
		}
		return digit;
		
	}
	
	private static boolean haveOneRecurringDigit(int num1, int num2)
	{
		//Special case for if numbers are 0s
		if(num1 == 0 && num2 == 0) return true;
		if(num1 == 0 && num2 != 0) return false;
		if(num1 != 0 && num2 == 0) return false;
		
		int digit = num2 % 10;
		
		while(num1 > 0)
		{
			if(num1 % 10 != digit) return false;
			
			num1 /= 10;
		}
		
		while(num2 > 0)
		{
			if(num2 % 10 != digit) return false;
			
			num2 /= 10;
		}
		
		return true;
	}
}
