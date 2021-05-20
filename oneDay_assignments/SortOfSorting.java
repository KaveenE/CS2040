package oneDay_assignments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fast_input_classes.FastReader;


public class SortOfSorting implements Comparable<SortOfSorting>{

	private static int NUMBER_OF_INITIAL_LETTERS_TO_COMPARE = 2;
	private final String name;
	
	public SortOfSorting(String pName) {
		this.name = pName;
	}
	
	@Override
	public int compareTo(SortOfSorting o) {
		
		return this.name.substring(0, NUMBER_OF_INITIAL_LETTERS_TO_COMPARE).compareTo(o.name.substring(0, NUMBER_OF_INITIAL_LETTERS_TO_COMPARE));
	}
	
	@Override
	public String toString() {
		return name;
	}
	public static void main(String[] args) {
		FastReader scanner = new FastReader();
		
		int numNames = scanner.nextInt();
		List<SortOfSorting> list;
		int i;
		
		while(numNames != 0) {
			
			list= new ArrayList<>(numNames);
			
			for(i = 0; i <= numNames - 1; i++)
				list.add(new SortOfSorting(scanner.nextLine()));
			
			Collections.sort(list);
			printList(list);
			System.out.println();
			
			numNames = scanner.nextInt();
		}

	}


public static <E extends SortOfSorting> void printList(List<E> list) {
		
		StringBuilder sb = new StringBuilder(list.size());
		
		for(int i = 0; i <= list.size()-1 ; i++) {
			
			sb.append(list.get(i).toString()+"\n");
		}
		
		System.out.print(sb);
		
	}





}
