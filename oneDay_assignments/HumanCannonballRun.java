package oneDay_assignments;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import convenienceClass.Pair;
import fast_input_classes.FastReader;

public class HumanCannonballRun {

	public static final double SPEED = 5;
	public static final double C_DIST = 50;
	public static void main(String[] args) throws IOException 
	{
		//Vertex is represented by <vertex ID, coordinates>. We'll use the array subscript as the id
		//Edge is represented as time taken
		
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//These are not cannons so i gave them a dummy id. since there can be only 0 - 100 cannons
		Pair<Double,Double> start = new Pair<>(scanner.nextDouble(), scanner.nextDouble());
		Pair<Double,Double> dest = new Pair<>(scanner.nextDouble(), scanner.nextDouble());
		
		int numCannons = scanner.nextInt();
		List<Pair<Double,Double>> cannons = new ArrayList<>(numCannons);
		
		for(int i = 0; i <= numCannons - 1; i++)
			cannons.add(new Pair<>(scanner.nextDouble(), scanner.nextDouble()));
		
		cannons.add(0, start);
		cannons.add(dest);
		
		//We initialize adjacency matrix for Floyd Warshall.O(V^3) should pass due to complexity from reading 
		//Take note, there'll be no matrix[i][j] == null due to our definition of edge
		double[][] matrix = new double[numCannons+2][numCannons+2];
		
		initializeMatrix_ForFloyd(matrix,cannons);
		floydMarshall(matrix);
		
		out.write(matrix[0][matrix.length-1]+"");
		out.flush();
	}
	
	public static double euclid_Distance(Pair<Double,Double> start, Pair<Double,Double> end) 
	{
		double diffXSquared = (start.getFirst() - end.getFirst())*(start.getFirst() - end.getFirst());
		double diffYSquared = (start.getSecond() - end.getSecond())*(start.getSecond() - end.getSecond());
		
		return Math.sqrt(diffXSquared +diffYSquared);
	}
	
	public static void initializeMatrix_ForFloyd(double[][] matrix,List<Pair<Double,Double>> cannons )
	{
		//Main diagonal to be 0
		for(int i = 0; i <= cannons.size() - 1; i++)
			matrix[i][i] = 0;
		
		//Initialize the distances for the non main diagonal. Remember, we have no infinity as explained!
		
		//Initializing for start. Can only run since no cannon at start
		for(int other = 1; other <= cannons.size() - 1; other++)
			matrix[0][other] = euclid_Distance(cannons.get(0), cannons.get(other))/SPEED;
		
		//Initializing for everything else. Note, from can't be dest
		double distRun = 0;
		double distRunAndCannon = 0;
		for(int from = 1; from <= cannons.size() - 2; from++)
		{
			for(int other = 1; other <= cannons.size() - 1; other++)
			{
				distRun = euclid_Distance(cannons.get(from), cannons.get(other))/SPEED;
				//distRunAndCannon = euclid_Distance(cannons.get(from), cannons.get(other)) <= C_DIST ? 2:
													//(euclid_Distance(cannons.get(from), cannons.get(other)) - C_DIST)/SPEED + 2;
				distRunAndCannon =Math.abs(euclid_Distance(cannons.get(from), cannons.get(other)) - C_DIST)/SPEED + 2;
				matrix[from][other] = Math.min( distRunAndCannon, distRun);
			}
		}
					
	}
	
	public static void floydMarshall(double[][] matrix)
	{
		
		for (int k=0; k<= matrix.length - 1; k++)
			for (int i=0; i<= matrix.length - 1; i++)
				for (int j=0; j<= matrix.length - 1; j++)
					matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
	}
}
