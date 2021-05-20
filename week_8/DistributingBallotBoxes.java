package week_8;

import java.io.BufferedWriter;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

import fast_input_classes.FastReader;

/*
 * Greedily select city with highest population/boxes [when boxes == 0, return population]
 * 
 * Reinsert modified city back into input(PQ in Java)
 * 
 * Repeat till allocatedBoxes == totalBoxes
 */
public class DistributingBallotBoxes {
	
	public static class City implements Comparable<City>{
		
		private int boxes;
		private int population;
		
		public City(int pop)
		{
			population = pop;
			
			//All cities must have atleast 1 box
			boxes = 1;
		}
		
		public void setBoxes(int boxes)
		{
			this.boxes = boxes;
		}
		
		public int getBoxes()
		{
			return boxes;
		}
		
		
		public double getMaxPeopleAssignedToBox()
		{
			return (double)population/boxes;
		}

		private static boolean isInteger(double num)
		{
			return num == (int)num;
		}
		
		@Override
		public int compareTo(City o) {
			
				return Double.compare(this.getMaxPeopleAssignedToBox(), o.getMaxPeopleAssignedToBox());
		}
	}
	
	//PQ method
	/*
	public static void main(String[] args) throws IOException {
		
		
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//Used in loop later, tryna optimize cos time exceeded
		Queue<City> pq;
		int boxesAllocated;
		City city;
		
		//Desperate to to solve time exceeded error
		//Using StrinBuilder to concatenate everything and print in 1 shot!
		StringBuilder sb = new StringBuilder();
		
		int cities = scanner.nextInt();
		int boxes = scanner.nextInt();
		
		while(cities != -1 && boxes != -1)
		{
			pq = new PriorityQueue<>(Collections.reverseOrder()); 
			//Since one is assinged to each city by default
			boxesAllocated = cities;
			
			
			for(int i = 0 ; i <= cities - 1; i++)
			{
				pq.add(new City(scanner.nextInt()));
			}
			
			while(boxesAllocated != boxes)
			{
				city = pq.poll();

				
				city.setBoxes(city.getBoxes() + 1);
				boxesAllocated++;
				
				pq.add(city);
			}
			
			sb.append(pq.remove().getMaxPeopleAssignedToBox()+"\n");
		
			cities = scanner.nextInt();
			boxes = scanner.nextInt();
		}
		
		out.write(sb.toString());
		out.flush();
	}
	*/
	
	/*Similar to PQ but used array->Got biggest one->Sort Array
	public static void main(String[] args) throws IOException
	{
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//Desperate to to solve time exceeded error
		//Using StrinBuilder to concatenate everything and print in 1 shot!
		StringBuilder sb = new StringBuilder();
		
		City[] cities;
		
		int numCities = scanner.nextInt();
		int boxesAllocated = numCities;
		int boxes = scanner.nextInt();
		
		while(numCities != -1 && boxes != -1)
		{
			cities = new City[numCities];
			boxesAllocated = numCities;
			
			for(int i = 0 ; i <= cities.length - 1; i++)
			{
				cities[i] = new City(scanner.nextInt());
			}
			
			Arrays.sort(cities);
			
			while(boxesAllocated != boxes)
			{
				//Get city with largest pop/boxesAllocated ratio and give it a box
				cities[cities.length - 1].setBoxes(cities[cities.length - 1].getBoxes() + 1);
				boxesAllocated++;
				
				//O(n) for almost sorted arrays
				insertionSort(cities);
			}
			
			sb.append(cities[cities.length - 1].getMaxPeopleAssignedToBox()+"\n");
			
			numCities = scanner.nextInt();
			boxes = scanner.nextInt();
			
		}
		
		out.write(sb.toString());
		out.flush();
		
	}*/
	
	//Based on Russell's analysis that Summation of Ceil(Population of city_i / answer to test case) = number of boxes given
	public static void main(String[] args) throws IOException
	{
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//Desperate to to solve time exceeded error
		//Using StrinBuilder to concatenate everything and print in 1 shot!
		StringBuilder sb = new StringBuilder();
		
		City[] cities;
		
		int numCities = scanner.nextInt();
		int boxesAllocated = numCities;
		int boxes = scanner.nextInt();
		
		while(numCities != -1 && boxes != -1)
		{
			cities = new City[numCities];
			boxesAllocated = numCities;
			
			for(int i = 0 ; i <= cities.length - 1; i++)
			{
				cities[i] = new City(scanner.nextInt());
			}
			
			Arrays.sort(cities);
			
			while(boxesAllocated != boxes)
			{
				//Get city with largest pop/boxesAllocated ratio and give it a box
				cities[cities.length - 1].setBoxes(cities[cities.length - 1].getBoxes() + 1);
				boxesAllocated++;
				
				//O(n) for almost sorted arrays
				insertionSort(cities);
			}
			
			sb.append(cities[cities.length - 1].getMaxPeopleAssignedToBox()+"\n");
			
			numCities = scanner.nextInt();
			boxes = scanner.nextInt();
			
		}
		
		out.write(sb.toString());
		out.flush();
		
	}
	public static void insertionSort(City[] cities)
	{
		int i,j;
		City base;
		
		//Initially sorted array is the element at 0 index
		for(i = 1; i <= cities.length - 1; i++)
		{
			//First element of unsorted array to be placed in locally optimized position
			base = cities[i];
			
			for(j = i - 1; j >= 0 && cities[j].compareTo(base) >= 0; j--)
			{
				//Copy bigger element to next index
				cities[j + 1] = cities[j];
			}
			
			//Place base in locally optimized position
			cities[j + 1] = base;
		}
	}
	
	public double incrementBoxesAllocated_AndReturnSummation(City[] cities) {
		double summation = 0;
		for(int i = 0; i <= cities.length - 1; i++) {
			cities[i].setBoxes(cities[i].getBoxes() + 1);
			summation += cities[i].getMaxPeopleAssignedToBox();
		}
		
		return summation;
			
	}
}
