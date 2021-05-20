package week_7;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

import fast_input_classes.FastReader;

public class BoatParts {

	public static void main(String[] args) throws IOException {
		
		FastReader scanner = new FastReader();
		Set<String> set = new HashSet<>();
		
		int numParts = scanner.nextInt();
		int numDays = scanner.nextInt();
		
		for(int i = 1; i <= numDays; i++) {
			
			set.add(scanner.next());
			
			if(set.size() == numParts)
			{
				System.out.println(i);
				break;
			}
		}
		
		if(set.size() != numParts)
		System.out.println("Paradox avoided");

	}

}
