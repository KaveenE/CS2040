package take_home.three;

import java.io.*;
import java.util.*;

import fast_input_classes.FastReader;

public class LadiceNotByRank {

	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int numItems = scanner.nextInt();
		int numDrawers= scanner.nextInt();
		UFDS1 ufds = new UFDS1(numDrawers);
		
		
		Set<Integer> seen = new HashSet<>(numDrawers);
		
		int drawer1;
		int drawer2;
		boolean enteredCondition;
		
		for(int i = 1; i <= numItems ;i++)
		{
			drawer1 = scanner.nextInt();
			drawer2 = scanner.nextInt();
			enteredCondition = true;;
			
			//1st step
			if(!seen.contains(drawer1))
			{
				seen.add(drawer1);
				ufds.unionSet(drawer1, drawer2);
				
			}
			//2nd step
			else if(!seen.contains(drawer2))
			{
				seen.add(drawer2);
				ufds.unionSet(drawer2, drawer1);
				
			}
			//3rd step
			else if(!seen.contains( ufds.findSet(drawer1)) )
			{
				seen.add(ufds.findSet(drawer1));
				ufds.unionSet(drawer1, drawer2);
			}
			//4th step
			else if(!seen.contains(ufds.findSet(drawer2)))
			{
				seen.add(ufds.findSet(drawer2));
				ufds.unionSet(drawer2, drawer1);
			}
			else
			{
				enteredCondition = false;
			}
			
			if(enteredCondition)
				sb.append("LADICA"+"\n");
			else
				sb.append("SMECE"+"\n");
		}
		
		out.write(sb.toString());
		out.flush();

	}

}

//In this question, the integer refers to the drawer ID
class UFDS1 {
	
	int[] parent;
	int[] rank;
	
	public UFDS1(int numSingletons)
	{
		parent = new int[numSingletons + 1];
		rank = new int[numSingletons + 1];
		
		//Create numSingletons sets(ie..)
		for(int i = 1; i <= numSingletons ; i++)
		{
			makeSet(i);
		}
	}
	public int findSet(int id)
	{
		if(parent[id] == id)
		{
			
		}
		else
		{
			parent[id] = findSet(parent[id]);
		}
		
		return parent[id];
	}
	
	//Pass parameter in the order of (drawer you want to fill, otherDrawer)
	public void unionSet(int i, int j)
	{
		int iRep = findSet(i);
		int jRep = findSet(j);
			
		
		parent[iRep] = jRep;
	}
	
	//Creating singleton set
	private void makeSet(int id)
	{
		parent[id] = id;
		rank[id] = 0;
	}
}