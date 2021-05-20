package week_9;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import fast_input_classes.FastReader;

public class Virtual_Friends1 
{
	public static void main(String[] args) throws IOException
	{
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int tests = scanner.nextInt();
		int friendships ;
		
		//Friendship's network is a ufds since we union them with other networks
		//Why do I use a map tho? So that I can find if we already seen that name beforehand with get() in O(1) average complexity
		Set< Set<String> > setOfNetworks = new HashSet<>();
		Set<String> network1, network2;
		Set<String> oldNetwork1, oldNetwork2;
		String name1, name2;
		
		for(int i = 0; i <= tests - 1; i++)
		{
			friendships = scanner.nextInt();
			
			for(int j = 0; j <= friendships - 1; j++)
			{
				name1 = scanner.next();
				oldNetwork1 = networkNameIsIn_or_SingletonNetWorkForNewName(setOfNetworks, name1);
				network1 = new HashSet<>(oldNetwork1);
				
				name2 = scanner.next();
				oldNetwork2 = networkNameIsIn_or_SingletonNetWorkForNewName(setOfNetworks, name2);
				network2 = new HashSet<>(oldNetwork2);
				
				//Union their networks(since friend's friends also becomes friends)
				network1.addAll(network2);
				
				
				out.write(network1.size() +"\n");
				
				//Updating the setOfNetworks where we simulate merge by adding in the merged set and removing the unmerged sets
				setOfNetworks.add(network1);
				setOfNetworks.remove(oldNetwork1);
				setOfNetworks.remove(oldNetwork2);
			}
		}
		
		out.flush();
		
	}
	
	
	public static Set<String> networkNameIsIn_or_SingletonNetWorkForNewName(Set<Set<String>> setOfNetworks, String name)
	{
		
		
		for(Set<String> someNetwork : setOfNetworks)
		{
			if(someNetwork.contains(name))
			{
				return someNetwork;
			}
		}
		
		
		//Return singleton set if we don't find the name
		Set<String> newNetwork = new HashSet<>();
		newNetwork.add(name);
		return newNetwork;
	}
}
