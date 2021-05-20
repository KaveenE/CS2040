package take_home.one;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class BestRelayTeam 
{
	static class Runner
	{
		private final String name;
		private final double firstLegTime;
		private final double otherLegTime;
		
		public Runner(String name, double firstLegTime, double otherLegTime)
		{
			this.name=name;
			this.firstLegTime=firstLegTime;
			this.otherLegTime=otherLegTime;
		}
		
		public double getFirstLegTime()
		{
			return firstLegTime;
		}
		
		public double getOtherLegTime()
		{
			return otherLegTime;
		}
		
		
		
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Runner other = (Runner) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			return true;
		}

		@Override
		public String toString()
		{
			return name;
		}
		
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int numRunner = scanner.nextInt();
		
		List<Runner> listOfRunners = new ArrayList<>(numRunner);
		
		String name;
		double firstlegTime;
		double otherLegTime;
		for(int idx=0; idx<= numRunner-1; idx++)
		{
			name = scanner.next();
			firstlegTime = Double.parseDouble(scanner.next());
			otherLegTime = Double.parseDouble(scanner.next());
			listOfRunners.add( new BestRelayTeam.Runner(name, firstlegTime, otherLegTime) ) ;
			
			scanner.nextLine();
		}
				
		//i sort by the otherLegTimes since we want to add them in ascending order after we get a a frontRunner.
		Comparator<Runner> sortByOtherLeg = (x , y) -> Double.compare(x.getOtherLegTime(), y.getOtherLegTime());
	
		Collections.sort(listOfRunners, sortByOtherLeg);
		
		listOfRunners = getBestTeam(listOfRunners, 4);
		
		listOfRunners.stream().forEach(x -> System.out.println(x));

	}
	
	public static List<Runner> getBestTeam(List<? extends Runner> listOfRunners, int teamSize)
	{
		List<Runner> fastestTeam= new ArrayList<>(teamSize);
		List<Runner> aux_fastestTeam = new ArrayList<>(teamSize);
		
		double finalfastestTime=Double.POSITIVE_INFINITY;
		double currfastestTime=0;
		
		
		for(int frontRunner=0; frontRunner<=listOfRunners.size()-1;frontRunner++)
		{
			aux_fastestTeam.add(listOfRunners.get(frontRunner));
			currfastestTime += listOfRunners.get(frontRunner).getFirstLegTime();
			

			for(int otherRunner = 0; otherRunner<= listOfRunners.size()-1; otherRunner++)
			{
				//team is only 4 peeps
				if(aux_fastestTeam.size() >=4 )
				{
					break;
				}
				
				//otherRunner can't be also the frontRunner
				if( !listOfRunners.get(frontRunner).equals( listOfRunners.get(otherRunner) ) )
				{
					aux_fastestTeam.add(listOfRunners.get(otherRunner));
					currfastestTime += listOfRunners.get(otherRunner).getOtherLegTime();
				}
			}
			
			//Update fastest team and time
			if( Double.compare(currfastestTime, finalfastestTime) < 0)
			{
				finalfastestTime = currfastestTime;
				fastestTeam = List.copyOf(aux_fastestTeam);
			}

			currfastestTime=0;
			aux_fastestTeam.clear();
		}
		
		//Bad practice. Violates SRP. 
		//But im lazy to calculate fastest time again by iterating over fastest team after I get oout of this method
		System.out.println(finalfastestTime);
		return fastestTeam;
	}
	
}
