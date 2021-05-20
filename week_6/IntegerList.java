package week_6;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;


import fast_input_classes.FastReader;

//Don't use Collections.reverse() or remove, they are too slow at O(n) each. Nested gives us O(n^2).
//Use Deque for O(1) removals at head/tail
public class IntegerList {

	public static void main(String[] args) {
		
		FastReader scanner = new FastReader();
		
		int cases = scanner.nextInt();	
		String[] numbersOnly;
		
		String input;
		String instructions;
		int numElements;
		
		for(int i = 0; i <= cases - 1; i++) {
			
			
			instructions = scanner.nextLine();
			
			numElements = scanner.nextInt();
			if(numElements == 0 && instructions.contains("D"))
			{
				System.out.println("error");
				
				//Read away the input
				input = scanner.nextLine();
				continue;
			}
			
			if(numElements == 0 && !instructions.contains("D"))
			{
				System.out.println("[]");
				
				//Read away the input
				input = scanner.nextLine();
				continue;
			}
			
			input = scanner.nextLine();
			numbersOnly = input.substring(1,input.length()-1).split(",");
			
		
			System.out.println( output(instructions, getListOfNumbers(numbersOnly)));
			
		}

	}

	public static String output(String instructions, Deque<Integer> dq) {

		boolean reverse = false;
		for (int command = 0; command <= instructions.length() - 1; command++)
		{

			if (instructions.charAt(command) == 'R') 
			{
					reverse = !reverse;
			} 
			else 
			{
				if (dq.isEmpty())
					return "error";
				
				if(reverse)
				dq.removeLast();
				else
				dq.removeFirst();
			}
		}
		
		Iterator<Integer> iterator = reverse ? dq.descendingIterator() : dq.iterator();
		StringBuilder sb = new StringBuilder(dq.size());
		sb.append("[");
		
		while(iterator.hasNext()) {
			sb.append(iterator.next() + ",");
		}
		
		//We only want to delete last if it's an comma
		//For that to happen, need to enter loop above
		//If we delete without any checks, "[" can be removed
		if(!dq.isEmpty())
		sb.deleteCharAt(sb.length()-1);
		
		return sb.append("]").toString();

	}
	
	public static Deque<Integer> getListOfNumbers(String[] input) {
		
		Deque<Integer> dq = new ArrayDeque<>();
		
		for(int i = 0; i <= input.length - 1; i++) {
			
				dq.add(Integer.parseInt(input[i]))	;
		}
		
		return dq;
	}
}
