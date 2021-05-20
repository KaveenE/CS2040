package take_home.two;


import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;
import fast_input_classes.FastReader;

/*I initially concatenated everytime indices were read
 * This was too costly at O(appending string) [Although amortized O(1) like dynamic resizing of array]
 * 
 * Instead store the indices in a manner that will compute the final string
 * This is done via storing associated indices with a respective index.
 * 
 */
public class Join_Strings {
	//Fast I/O
	public static FastReader scanner = new FastReader();
	public static BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	public static void main(String[] args) throws IOException 
	{
		String[] array = new String[scanner.nextInt() + 1];
		
		for(int i = 1; i <= array.length - 1;i++){
			
			array[i] = scanner.next();
		}
		
		int string1Pos = 1;
		int string2Pos = 1;
		
		//list of queues
		//Each queue tracks the indices appeneded to some index
		List< Deque<Integer> > list = new  ArrayList<>(array.length);
		initializeList(list,array.length);
		
		for(int i = 1 ; i <= array.length - 2;i++){

			string1Pos = scanner.nextInt();
			string2Pos = scanner.nextInt();
			
			list.get(string1Pos).addLast(string2Pos);
		}
		
		printRecursively(list, array, string1Pos);
		out.flush();

	}
	
	public static void printRecursively(List< Deque<Integer> > list,String[] array,int startIdx) throws IOException
	{
		out.write(array[startIdx]);
		if(list.get(startIdx).isEmpty())
			return;
		else
		{
			for(int otherStringPos : list.get(startIdx))
			{
				printRecursively(list, array, otherStringPos);
			}
		}
		
	}

	public static void initializeList(List<Deque<Integer>> list,int size)
	{
		for(int i=1; i<=size ;i++)
		{
			list.add(new ArrayDeque<>());
		}
	}
}

