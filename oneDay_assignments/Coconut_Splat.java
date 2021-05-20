package oneDay_assignments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fast_input_classes.FastReader;

/*
 * Initially I thought of the algo in terms of PPL and 2 hands
 * This was too hard to implement for me
 * 
 * Then saw this pseudocode:
 * Can represent players as lists of pairs (p,s) where 1 ≤ p ≤ n is the
	number of the player and s the state of the hand (s ∈ {FOLDED, FIST, PALM})

Rhyme selects player (i + s − 1) mod n after player i.
if (p, FOLDED) is hit, remove it and insert two copies of (p, FIST) at
current position.
if (p, FIST) is hit, replace with (p, PALM).
if (p, PALM) is hit, remove.
Repeat until only one hand is left.

This is thinking in terms of coconuts, ie just 1 variable. Way Simpler
 */

/*
 * Other bugs:
 * When splitting my coconut, I created a 2nd coconut
 * with currPersonIdx(WRONG) as it's just a counter. I got mixed up with currPerson.index!
 */

public class Coconut_Splat {
	
	public static class Coconut
	{
		private int idx;
		private State coconutState;
		
		public Coconut(int idx)
		{
			this.idx = idx;
			coconutState = State.FOLDED;
		}


		public State getCoconut() {
			return coconutState;
		}

		public void setCoconut(State hand) {
			this.coconutState = hand;
		}

		public int getIdx() {
			return idx;
		}
		
		public String toString()
		{
			return idx+" "+coconutState;
		}
		
	}

	public enum State{
		FOLDED,
		FIST,
		PALM,

	}
	public static void main(String[] args) 
	{
		Map<State, State> transitionsMapping = new HashMap<>();
		transitionsMapping.put(State.FOLDED, State.FIST);
		transitionsMapping.put(State.FIST, State.PALM);

		FastReader scanner = new FastReader();
		
		int syllables = scanner.nextInt();
		int persons = scanner.nextInt();
		
		
		List<Coconut> circle = new ArrayList<>(persons);
		initializeList(circle, persons);
		
		Coconut currPerson;
		Coconut auxPerson;
		int currPersonIdx = 0;
		
		
		while(circle.size()>=2)
		{
			currPersonIdx = moveToNextPerson(currPersonIdx, syllables, circle.size());
			currPerson = circle.get(currPersonIdx);
			
			//Add two fists in place of 1 folded
			if(currPerson.getCoconut() == State.FOLDED)
			{
				currPerson.setCoconut(transitionsMapping.get(currPerson.getCoconut()));
				
				auxPerson = new Coconut(currPerson.getIdx());
				auxPerson.setCoconut(State.FIST);
				
				circle.add(currPersonIdx ,auxPerson );
			}
			else if(currPerson.getCoconut() == State.FIST)
			{
				currPerson.setCoconut(transitionsMapping.get(currPerson.getCoconut()));
				currPersonIdx = (currPersonIdx + 1)%circle.size();
			
				//currPersonIdx++;
			}
			else
			{
				circle.remove(currPersonIdx);
			}
			
			
		}
		
		System.out.println(circle.get(0).idx + 1);

	}

	public static void initializeList(List<? super Coconut> collection, int numPersons)
	{
		for(int i = 0; i <= numPersons - 1; i++)
		{
			collection.add(new Coconut(i));
		}
	}
	public static int moveToNextPerson(int currPerson, int syllables, int numPersons)
	{
		return (currPerson + syllables - 1) % numPersons;
	}
	

}
