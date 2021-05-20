package week_9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.*;

import fast_input_classes.FastReader;

public class Virtual_Friends {

		public static void main(String[] args) throws IOException
		{
			FastReader scanner = new FastReader();
			BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
			
			int tests = scanner.nextInt();
			int friendships ;
			
			//Friendship's network is a ufds since we union them with other networks
			//Why do I use a map tho? So that I can find if we already seen that name beforehand with get() in O(1) average complexity
			Map<String, Set<String>> nameToNetwork = new HashMap<>();
			Set<String> network1, network2;
			String name1, name2;
			
			for(int i = 0; i <= tests - 1; i++)
			{
				friendships = scanner.nextInt();
				
				for(int j = 0; j <= friendships - 1; j++)
				{
					name1 = scanner.next();
					network1 = nameToNetwork.getOrDefault(name1, new HashSet<>());
					//Network include the guy himself
					if(network1.isEmpty())
						network1.add(name1);
					
					name2 = scanner.next();
					network2 = nameToNetwork.getOrDefault(name2, new HashSet<>());
					//Network include the guy himself
					if( network2.isEmpty() )
						network2.add(name2);
					
					//Union their networks(since friend's friends also becomes friends)
					//Now i need to update each friend's network in network1/network2 recursively?
					network1.addAll(network2);
					network2.addAll(network1);
					
					
					out.write(network1.size() +"\n");
					
					//Update my map
					nameToNetwork.put(name1, network1);
					nameToNetwork.put(name2, network2);
				}
			}
			
			out.flush();
			
		}
}
