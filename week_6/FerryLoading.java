package week_6;

import java.util.ArrayDeque;
import java.util.Deque;
import fast_input_classes.FastReader;

/*
 * Had a big issue with this program because of how the qn was phrased!
It said "The cars are loaded in the order of their arrival" so I assumed it must load in that order
Example: If first car is on right side, and there are cars on left side in the queue, I assumed, 
we must load go to right side to load that car first even though I'm at the left side already!
I'm wrong

We should have queues on both sides and the "The cars are loaded in the order of their arrival" applies to these 2 queues independently
*/
public class FerryLoading {

	public static Side currentSide;
	
	//For each case, we need to output this
	public static int numTrips;
	
	//To calculate current length that ferry is holding
	public static int totalCurrLength = 0;
	
	public static class Car {

		private int length;
		private Side sideOfBank;

		private Car(int length, Side sideOfBank) {

			this.length = length;
			this.sideOfBank = sideOfBank;
		}

		public static Car createCar(int length, Side sideOfBank) {

			return new Car(length, sideOfBank);
		}

		public int getLength() {
			return length;
		}

		public Side getSideOfBank() {
			return sideOfBank;
		}

		@Override
		public String toString() {
			return length+" "+sideOfBank;
		}
	}
	
	public static enum Side {
		LEFT("left"),
		RIGHT("right");
		
		private String side;
		
		private Side(String side) {
			
			this.side = side;
		}
		
		public String getSide()
		{
			return side;
		}
		
		public Side changeSide() {
			
			if(this == LEFT)
				return RIGHT;
			else
				return LEFT;
		}
		
	}

	public static void main(String[] args) {

		FastReader scanner = new FastReader();

		Deque<Car> queueOnLeftSide = new ArrayDeque<>(10000);
		Deque<Car> queueOnRightSide = new ArrayDeque<>(10000);
		
		//Variables I'll be using later
		Car boardingCar;
		

		//Basic input variables
		int cases = scanner.nextInt();
		int ferryLength;
		int numCars;
		int carLength;
		Side sideCarAt;

		for(int i = 1; i <= cases; i++) {

			ferryLength = scanner.nextInt();
			numCars = scanner.nextInt();
			
			//Reset
			numTrips = 0;
			currentSide = Side.LEFT;

			//Read my inputs for each case
			for(int j = 1; j <= numCars ; j++) {
				
				carLength = scanner.nextInt();
				if(carLength > ferryLength * 100)continue;
				
				sideCarAt = scanner.next().equals("left") ? Side.LEFT : Side.RIGHT;
				
				boardingCar = Car.createCar( carLength, sideCarAt );
				
				if(boardingCar.getSideOfBank() == Side.LEFT) 
				{
					queueOnLeftSide.addLast(boardingCar);
				}
				else
				{
					queueOnRightSide.addLast(boardingCar);
				}
				
			}

			
			while(!queueOnLeftSide.isEmpty() || !queueOnRightSide.isEmpty()) 
			{
				if(currentSide == Side.LEFT) 
				{
					if(queueOnLeftSide.isEmpty())
					{
						moveToOtherSide();
						continue;
					}
					
					boardingCar = queueOnLeftSide.peek();
					
					if(!canAccomodate(totalCurrLength, ferryLength, boardingCar)) 
					{
						moveToOtherSide();
						
					}
					else //board ferry
					{
						queueOnLeftSide.removeFirst();
						totalCurrLength += boardingCar.getLength();
					}
					
				}
				else if(currentSide == Side.RIGHT)
				{
					if(queueOnRightSide.isEmpty())
					{
						moveToOtherSide();
						continue;
					}
					
					boardingCar = queueOnRightSide.peek();
					
					if(!canAccomodate(totalCurrLength, ferryLength, boardingCar)) 
					{
						moveToOtherSide();
					}
					else //board ferry
					{
						queueOnRightSide.removeFirst();
						totalCurrLength += boardingCar.getLength();
					}
				}
				
			}

			//That means no more queue on both side but ferry might still have to go other side to drop off
			moveToOtherSide();

			System.out.println(numTrips);
		}

	}

	public static boolean canAccomodate(int totalCurrLength, int ferryLength, Car boardingCar)
	{
		return ferryLength*100 >= totalCurrLength + boardingCar.getLength();
	}
	
	public static void moveToOtherSide() {
		numTrips++;	
		currentSide = currentSide.changeSide();
		
		//Ferry dequeues the cars
		totalCurrLength = 0;
	}

	public static boolean noneToLoadOn_CurrentBank(Deque<Car> queueOfCars, Side currentSide)
	{
		//no cars to load, just go to opposite bank
		if(queueOfCars.isEmpty())
			return true;
		
		return !queueOfCars.peek().getSideOfBank().equals(currentSide);
	}

}

