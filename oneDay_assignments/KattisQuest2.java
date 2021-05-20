package oneDay_assignments;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

import fast_input_classes.FastReader;

public class KattisQuest2 
{
	public static class Quest
	{
		private final long energyRequired;
		private final long goldReward; 
		
		public Quest(long energy, long gold)
		{
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
																if(quest1.getEnergyRequired() != quest2.getEnergyRequired())
																	return Long.compare(quest1.getEnergyRequired(), quest2.getEnergyRequired());
																else
																	return Long.compare(quest1.getGold(), quest2.getGold());
		
															};
														
		//We want max-heap, thus reverse my ascending comparator.
		PriorityQueue<Quest> pq = new PriorityQueue<>(sortByEnergy_ByGoldIfTied.reversed());
		
		for(int i =0; i<= numOperations -1 ;i++)
		{
			if(scanner.next().equals("add"))
			{
				pq.add(new Quest(scanner.nextInt(), scanner.nextInt()));
			}
			else
			{
				sb.append(query1(scanner.nextInt(),pq)+"\n");
			}
		}
		
		out.write(sb.toString());
		out.flush();
	}
	
	public static int query(int energyPossessed, Queue<Quest> pq)
	{
		List<Quest> tooExpensiveQuests = new ArrayList<>();
		Quest q;
		int goldEarned = 0;
		int energyRemaining = energyPossessed;
		
		//Find the largest energy quest from the current pool of quest which is smaller or equal to X, if tied, by the largest gold reward,
		while(!pq.isEmpty() && pq.peek().getEnergyRequired() > energyPossessed)
		{
			tooExpensiveQuests.add(pq.remove());
		}
		
		//Now pq only have quests whose energy requirements we can fulfill
		if(!pq.isEmpty())
		{
			
			while(!pq.isEmpty() && pq.peek().getEnergyRequired() <= energyRemaining )
			{
				q = pq.remove();
				
				goldEarned += q.getGold();
				energyRemaining -= q.getEnergyRequired();
			}

			
			//Reinsert back the expensive quests
			pq.addAll(tooExpensiveQuests);
			
			return goldEarned;
		}	
		else
		{
			return 0;
		}
			
	}
	
	public static long query1(int energyPossessed, PriorityQueue<Quest> pq)
	{
		
		Quest[] questsInOrder = convert_PQtoArray(pq);
		Arrays.sort(questsInOrder, pq.comparator());
		
		long goldEarned = 0;
		long energyRemaining = energyPossessed;
		boolean noMoreRemoval = false;
		
		for(int i = 0; i <= questsInOrder.length - 1 ; i++)
		{
			if(questsInOrder[i].getEnergyRequired() <= energyRemaining )
			{
				goldEarned += questsInOrder[i].getGold();
				energyRemaining -= questsInOrder[i].getEnergyRequired();
				pq.remove(questsInOrder[i]);
				
				noMoreRemoval = true;
			}
			else if(noMoreRemoval)
			{
				break;
			}
		}
		
		return goldEarned;
			
	}
	
	private static Quest[] convert_PQtoArray(Queue<Quest> pq)
	{
		Quest[] questsInOrder = new Quest[pq.size()];
		
		Queue<Quest> auxPQ = new PriorityQueue<>(pq);
		
		for(int i = 0; i <= pq.size() - 1; i++)
		{
			questsInOrder[i] = auxPQ.remove();
		}
		
		return questsInOrder;
	}
}
