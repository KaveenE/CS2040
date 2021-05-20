package week_5.a_classy_problem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import fast_input_classes.Kattio;

//As usual all CS2040 qns violate software principles lol cos we just want answer
public class ClassyProblem {

	private static final Map<String, Integer> CLASSVALUES = new HashMap<>();
	
	public static class Person implements Comparable<Person>
	{
		private static final int MIDDLE_VALUE =1;
		private final String name;
		private final List<String> class_String;
		
		
		public Person(String name, List<String> classString)
		{
			//To get rid of the colon lol
			this.name = name.substring(0,name.length()-1);
			
			Collections.reverse(classString);
			this.class_String =  List.copyOf(classString);
		}
		
		public String getName() {
			return name;
		}

		@Override
		public int compareTo(Person o) {
			
			int idx;
			List<String> myClassString = this.class_String;
			List<String> otherClassString = o.class_String;
			
			for(idx = 0; idx <= myClassString.size() - 1 && idx <= otherClassString.size()-1; idx++)
			{
				if( Integer.compare( CLASSVALUES.get(myClassString.get(idx)) , CLASSVALUES.get(otherClassString.get(idx) ) )  != 0 )
				{
					return Integer.compare( CLASSVALUES.get(myClassString.get(idx)) , CLASSVALUES.get(otherClassString.get(idx) ) );
				}
			}
			
			//In the case of 1 list being the suffix of another. This case test if this list is suffix of other list
			if(myClassString.size() < otherClassString.size()) {
				
				for(idx = myClassString.size(); idx <= otherClassString.size()-1; idx++) {
					
					if(CLASSVALUES.get(otherClassString.get(idx)) != MIDDLE_VALUE) {
						return Integer.compare(MIDDLE_VALUE, CLASSVALUES.get(otherClassString.get(idx)));
					}
				}
			}
			
			//In the case of 1 list being the suffix of another. This case test if other list is suffix of this list
			if(otherClassString.size() < myClassString.size()) {
				
				for(idx = otherClassString.size(); idx <= myClassString.size()-1; idx++) {
					
					if(CLASSVALUES.get(myClassString.get(idx)) != MIDDLE_VALUE) {
						return Integer.compare(CLASSVALUES.get(myClassString.get(idx)), MIDDLE_VALUE );
					}
				}
			}
			
			//Break ties by name
			return o.name.compareTo(this.name);
		}
		
	}
	
	public static void main(String[] args) {
		
		//Assign values for the classe levels
		CLASSVALUES.put("upper",2);
		CLASSVALUES.put("middle",1);
		CLASSVALUES.put("lower",0);
		
		Kattio scanner = new Kattio(System.in);
		
		//auxillary variables
		int cases = scanner.getInt();
		int numNames;
		
		
		//variables needed to pass argument to  Person constructor
		String name = null;
		List<String> classString = new ArrayList<>();
		String[] inputForClass;
		
		List<Person> listOfPersons;
		
		int i , j , k;
		for( i = 1; i <= cases; i++ )
		{
			
			numNames = scanner.getInt();
			listOfPersons = new ArrayList<>(numNames);
			
			//Read the inputs for each Person
			for(j = 0 ; j <= numNames - 1 ;j++ )
			{
				name = scanner.getWord();
				inputForClass = scanner.getWord().split("-");
				
				//Get rid of the class string at the end
				scanner.getWord();
				
				for( k = 0 ; k <= inputForClass.length-1 ; k++) {
					
					classString.add( inputForClass[k] );
				}
				
				listOfPersons.add( new ClassyProblem.Person(name, classString) );
				classString.clear();
			}
			
			Collections.sort(listOfPersons, Collections.reverseOrder());
			printList(listOfPersons);
			System.out.println("==============================");
			
		}

	}
	
	public static <E extends Person> void printList(List<E> list) {
		
		StringBuilder sb = new StringBuilder(list.size());
		
		for(int i = 0; i <= list.size()-1 ; i++) {
			
			sb.append(list.get(i).getName()+"\n");
		}
		
		System.out.print(sb);
		
	}

}

