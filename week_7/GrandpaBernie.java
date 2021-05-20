package week_7;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

import fast_input_classes.FastReader;

public class GrandpaBernie {

	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int numTrips = scanner.nextInt();
		String country;
		List<Integer> listOfYears;
		Map<String,List<Integer>> countryToYear = new HashMap<>();
		
		for(int i = 1 ; i <= numTrips ; i++)
		{
			country = scanner.next();
			listOfYears = countryToYear.getOrDefault(country, new ArrayList<>());
			listOfYears.add(scanner.nextInt());
			countryToYear.put( country, listOfYears  );
		}
		
		for(List<Integer> list : countryToYear.values())
		{
			Collections.sort(list);
		}
		
		StringBuilder sb = new StringBuilder();
		int queries = scanner.nextInt();
		for(int i = 0; i <= queries - 1; i++)
		{
			country = scanner.next();
			sb.append(countryToYear.get(country).get(scanner.nextInt() - 1)+"\n"); 
			
		}

		out.write(sb.toString());
		out.flush();
	}

}
