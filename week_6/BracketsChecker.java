package week_6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;
import fast_input_classes.FastReader;


public class BracketsChecker 
{
	//Maps closer to opener
	private Map<Character,Character> bracketsMapping;
	
	public BracketsChecker()
	{
		bracketsMapping=new HashMap<>();
		
		bracketsMapping.put(']', '[');
		bracketsMapping.put('}', '{');
		bracketsMapping.put(')', '(');
	}
	
	public String checkBrackets(String string)
	{
		Deque<Character> stackOfBrackets=new ArrayDeque<>();
		Character someBracket;
		
		
		for(int idx=0;idx<=string.length()-1;idx++)
		{
			someBracket=string.charAt(idx);
			
			//if opener,just push onto stack
			if(isOpener(someBracket))
			{
				stackOfBrackets.addLast(someBracket);
			}
			//if closer, we check if there's a matching opener.False if not matching or stack is empty(underflow).
			else if(isCloser(someBracket))
			{
				if(stackOfBrackets.isEmpty())
				{
					return someBracket+" "+idx;
				}
				else if(!areMatching(someBracket, stackOfBrackets.removeLast()))
				{
					return someBracket+" "+idx;
				}
			}
			else
			{
				//Don't bother non brackets
			}
		}
		
		/*
		 * This qn is weird, it tells to return "correct" even if stack isn't empty
		 * ie there are more openers than closer
		//Now stack must be empty since we have checked all pairs of brackets have been checked
		//if there is, non-matching brackets are present(ie extra brackets)
		if(!stackOfBrackets.isEmpty())
		{
			return stackOfBrackets.removeLast()+"";
		}
		*/
		
		return "ok so far";
	}
	
	private boolean areMatching(Character closer,Character opener)
	{
		return bracketsMapping.get(closer)==opener;
	}
	
	private boolean isOpener(Character possibleOpener)
	{
		for(char opener:bracketsMapping.values())
		{
			if(opener==possibleOpener)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isCloser(Character possibleCloser)
	{
		for(char opener:bracketsMapping.keySet())
		{
			if(opener==possibleCloser)
			{
				return true;
			}
		}
		
		return false;
	}
	
	public static void main(String[] args)
	{
		BracketsChecker bracketsChecker=new BracketsChecker();
		
		FastReader scanner=new FastReader();
		
		//Ignore the integer, we don't need it lol
		scanner.nextInt();
		
		System.out.print(bracketsChecker.checkBrackets(scanner.nextLine()));
		
	}
	
}
