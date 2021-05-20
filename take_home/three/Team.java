package take_home.three;

public class Team implements Comparable<Team> {
	
	private int correctAns;
	private int totalPenalties;
	private int idx;
	
	public Team(int id) 
	{
		idx = id;
	}
	
	public void incrementCorrect() {
		correctAns++;
	}
	
	public void updatePenalties(int penaltiesToIncrease) {
		totalPenalties += penaltiesToIncrease;
	}
	
	@Override
	public int compareTo(Team o) {
		
		if(correctAns != o.correctAns) 
		{
			return correctAns - o.correctAns;
		}
		else if(o.totalPenalties != totalPenalties)
		{
			return  o.totalPenalties - totalPenalties;
		}
		else
		{
			return o.idx - idx;
		}
	}
	
	

}
