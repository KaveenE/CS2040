package oneDay_assignments;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import fast_input_classes.FastReader;

/* NOTE to self: 
 * Study up on event-driven programming questions, where we "execute things" only when events happen.
 * This "execution" refers to selecting the some "max/min" thing from the pq.
 * In this case, the event is the researcher's arrival.
 *
 * 
 * We sort the reseacher's arrivals in chronological order
 * 
 * For each arrival(event), we want the workstation that was unlocked earliest(the "min" thing where we want earliest logOut times)
 * -we check if the dude came in too early before the machines is free(since the pq's ordering
 * is the node having earliest ending times, this means that if this machine is not free none are)
 * -came just nice before machines locks but also unused
 * -gg, machine is locked and researcher has to look for the next one.(we can remove the locked machine
 * since we need unlocked ones in heap)
 * 
 * 
 * 
 */
public class Assigning_Workstations {

	public static void main(String[] args) {
		
		FastReader scanner = new FastReader();
		
		int numResearchers = scanner.nextInt();
		WorkStation.MINUTES_TO_LOCK = scanner.nextInt();
		
		//numberOfUnlocks that's saved = orginalUnlocks(equal to numResearchers) - actualUnlocks
		int actualUnlocks = 0;
		
		Researcher[] researchers = new Researcher[numResearchers];

		//Read inputs
		for(int i = 0; i <=numResearchers - 1; i++)
		{
			researchers[i] = new Researcher(scanner.nextInt(), scanner.nextInt());
			
		}
		
		//Sort by the chronological order
		Arrays.sort(researchers);
		
		Queue<WorkStation> pqOfStations = new PriorityQueue<>();
		
		for(Researcher researcher : researchers)
		{
			if(unlockNewStation(pqOfStations, researcher))
			{
				actualUnlocks++;
			}
		}
		
		System.out.println(numResearchers - actualUnlocks);

	}
	
	public static PriorityQueue<Integer> update_pq(PriorityQueue<Integer> pq)
	{
		PriorityQueue<Integer> new_pq = new PriorityQueue<>(pq.size(), Collections.reverseOrder());
		
		while(!pq.isEmpty())
			new_pq.add(pq.remove());
		
		return new_pq;
	}
	
	public static boolean unlockNewStation(Queue<WorkStation> pqOfStations, Researcher researcher)
	{
		WorkStation workStation;
		
		while(!pqOfStations.isEmpty()) 
		{
			workStation = pqOfStations.peek();
			
			//By explanation above, this means none of the other stations can serve
			//Thus unlock new station
			if(workStation.researcherArrives_TooEarly(researcher))
			{
				pqOfStations.add(new WorkStation(researcher.endTime));
				return true;
			}
			//By on above explanation, we have an available station!
			else if(workStation.researcherArrives_whenUnlockedUnused(researcher))
			{
				pqOfStations.remove();
				workStation.setLatestLogOut(researcher);
				pqOfStations.add(workStation);
				return false;
			}
			//This researcher came way too late for this station.This station is locked.
			//We remove locked stations since pq is for unlocked ones to compute number of times to unlock.
			//We check next best station(defined by ordering).
			else
			{
				pqOfStations.remove();
			}
		}
		
		pqOfStations.add(new WorkStation(researcher.endTime));
		return true;
	}

}

class WorkStation implements Comparable<WorkStation>{
	
	public static  int MINUTES_TO_LOCK;
	
	//Going to be equal to researcher's end
	private int latestLogOut;
	
	public WorkStation(int latestLogOut) {
		this.latestLogOut = latestLogOut;
	}

	public boolean researcherArrives_TooEarly(Researcher r)
	{
		 return r.startTime < latestLogOut;
	}
	
	public boolean researcherArrives_whenUnlockedUnused(Researcher r)
	{
		return latestLogOut + MINUTES_TO_LOCK >= r.startTime;
	}



	public void setLatestLogOut(Researcher r) {
		this.latestLogOut = r.endTime;
	}
	
	//we want the workstation that was unlocked earliest
	//This is so we can get the workstation before it locks
	//Unlocked earliest can be determined by earliest endtime
	@Override
	public int compareTo(WorkStation other) {
		//We want to be ordered in terms of which workstation is unlocked the longest
		//We want a max-heap where the ordering is dictated by longest duration workstation is unlocked 
		return this.latestLogOut - other.latestLogOut;
	}
	
	
}

class Researcher implements Comparable<Researcher>{
	
	public int startTime;
	public int endTime;
	
	public Researcher(int startTime, int usageDuration)
	{
		this.startTime = startTime;
		this.endTime = usageDuration + startTime;
	}

	@Override
	public int compareTo(Researcher o) {
		// TODO Auto-generated method stub
		return this.startTime - o.startTime; 
	}
}