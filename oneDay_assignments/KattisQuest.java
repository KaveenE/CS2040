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

public class KattisQuest 
{
	public static class Quest
	{
		private final int energyRequired;
		private final int goldReward; 
		
		public Quest(int energy, int gold)
		{
			this.energyRequired = energy;
			this.goldReward = gold;
		}
		
		public int getGold()
		{
			return goldReward;
		}
		
		public int getEnergyRequired()
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
																	return quest1.getEnergyRequired() - quest2.getEnergyRequired();
																else
																	return quest1.getGold() - quest2.getGold();
		
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
				sb.append(query(scanner.nextInt(),pq)+"\n");
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
		
		//Now pq only have quests whose energy requirements we can fulfill(initially)
		while(!pq.isEmpty())
		{
			q = pq.remove();

			if(q.getEnergyRequired() <= energyRemaining)
			{
				goldEarned += q.getGold();
				energyRemaining -= q.getEnergyRequired();
			}
			else
			{
				tooExpensiveQuests.add(q);
			}

			if(energyRemaining <= 0)
				break;

		}


		//Reinsert back the expensive quests
		pq.addAll(tooExpensiveQuests);

		return goldEarned;

	}
	
	public static int query1(int energyPossessed, PriorityQueue<Quest> pq)
	{
		
		Quest[] questsInOrder = convert_PQtoArray(pq);
		Arrays.sort(questsInOrder, pq.comparator());
		
		int goldEarned = 0;
		int energyRemaining = energyPossessed;

		for(int i = 0; i <= questsInOrder.length - 1 ; i++)
		{
			if(questsInOrder[i].getEnergyRequired() <= energyRemaining )
			{
				goldEarned += questsInOrder[i].getGold();
				energyRemaining -= questsInOrder[i].getEnergyRequired();
				pq.remove(questsInOrder[i]);
				
				
			}
			if(energyRemaining < questsInOrder[questsInOrder.length-1].getEnergyRequired())
			{
				break;
			}
			if(energyRemaining <= 0)
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
