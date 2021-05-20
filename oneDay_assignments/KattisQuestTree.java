package oneDay_assignments;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.TreeSet;
import fast_input_classes.FastReader;


public class KattisQuestTree {
	
	public static class Quest
	{
		private final long energyRequired;
		private final long goldReward; 
		private final int idx;
		
		public Quest(int idx,long energy, long gold)
		{
			this.idx = idx;
			this.energyRequired = energy;
			this.goldReward = gold;
		}
		
		public long getGold()
		{
			return goldReward;
		}
		
		public long getEnergyRequired()
		{
			return energyRequired;
		}
	}

	public static void main(String[] args) throws IOException
	{
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int numOperations = scanner.nextInt();
		
		Comparator<Quest> sortByEnergy_ByGoldIfTied = (quest1, quest2) -> 
															{
																if(Long .compare(quest1.getEnergyRequired(), quest2.getEnergyRequired()) != 0)
																	return Long .compare(quest1.getEnergyRequired(), quest2.getEnergyRequired());
																else if(Long.compare(quest1.getGold(), quest2.getGold())  != 0)
																	return  Long.compare(quest1.getGold(), quest2.getGold());
																else
																	return quest1.idx - quest2.idx;
		
															};
		TreeSet<Quest> ts = new TreeSet<>(sortByEnergy_ByGoldIfTied);
		int id = 1;
		
		for(int i =0; i<= numOperations -1 ;i++)
		{
			if(scanner.next().equals("add"))
			{
				ts.add(new Quest(id++, scanner.nextInt(), scanner.nextInt()));
			}
			else
			{	
				System.out.println(query(scanner.nextInt(),ts));
				//sb.append(query(scanner.nextInt(),ts)+"\n");
			}
		}
		
		//out.write(sb.toString());
		//out.flush();
	}
	
	public static long query(int energyPossessed, TreeSet<Quest> ts)
	{
		Quest mostExpensiveQuestFulfillable = new Quest(Integer.MAX_VALUE, energyPossessed, Integer.MAX_VALUE);
		Quest mostExpensiveQuestWeGet;
		long goldEarned = 0;
		
		//Go thru treeSet and obtain the most expensive quest I can get with my energyPossessed
		while(!ts.isEmpty())
		{
			mostExpensiveQuestWeGet = ts.floor(mostExpensiveQuestFulfillable);
			
			if(mostExpensiveQuestWeGet == null)
			{
				break;
			}
			else
			{
				ts.remove(mostExpensiveQuestWeGet);
			}
			
			energyPossessed -= mostExpensiveQuestWeGet.getEnergyRequired();
			goldEarned += mostExpensiveQuestWeGet.getGold();
			
			if(energyPossessed <= 0 )
			{
				break;
			}
			
			mostExpensiveQuestFulfillable = new Quest(Integer.MAX_VALUE, energyPossessed, Integer.MAX_VALUE);
		}

		return goldEarned;

	}
}
