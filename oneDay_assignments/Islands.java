package oneDay_assignments;

import java.io.*;
import java.util.*;
import convenienceClass.*;
import fast_input_classes.FastReader;

//Essentially counting components algo with some modification
public class Islands {

		public static void main(String[] args) throws IOException {
			//input variables
		    FastReader scanner=new FastReader();
		    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		    char[][] adjaceny_matrix = new char[scanner.nextInt()][scanner.nextInt()];
		    
		    //Read array
		    read2D(scanner, adjaceny_matrix);
		    
		    out.write(DFS_implicitStack(adjaceny_matrix)+"");
		    out.flush();
		    
		}
		
		public static void read2D(FastReader scanner, char[][] adjacency_matrix)
		{

		    //Counter
		    int row,col;
		    String letters;

		    for(row=0;row<=adjacency_matrix.length-1;row++)
		    {
		    	letters = scanner.next();
		        for(col=0;col<=adjacency_matrix[row].length-1;col++)
		        {
		        	adjacency_matrix[row][col]=letters.charAt(col);
		        }

		    }

		}
		
		//DFS
		public static int DFS_implicitStack(char[][] adjaceny_matrix)
		{
			// Mark all the vertices as not visited
			Set<Pair<Integer,Integer>> visited = new HashSet<>();
			
			Pair<Integer, Integer> vertex;
			
			int counter = 0;

			// Call the recursive helper function to DFS
			//vertex by vertex only if it hasn't been visited
			// The recursive function by itself will traverse entire graph if graph was all connected
			//We do this loop in case of multiple components
			
			//Now we go thru grid
			for (int row = 0; row <= adjaceny_matrix.length-1; row++)
			{
				
				for(int col = 0; col <= adjaceny_matrix[row].length-1; col++) {
					
					vertex = new Pair<>(row, col);
					
					if (!visited.contains(vertex) && isLand(adjaceny_matrix, vertex)) {
						counter++;
						visited.add(vertex);
						DFS_implicitStack_Rec(adjaceny_matrix, vertex, visited);
						
					}
						
				}
				
			}
			
			return counter;
		}

		private static  void DFS_implicitStack_Rec(char[][] graph,Pair<Integer, Integer> vertex, Set<Pair<Integer,Integer>> visited)
		{
			// Mark the current node as visited
			//Prevents visiting duplicate nodes.
			//If we visited duplicate nodes, we'ill end in infinite loop
			//as the same thing is gonna repeat all over
			visited.add(vertex);

			//Recursively, search along the path of this vertex till base case
			//Then backtrack
			//We can have multiple paths 
			//as current source vertex can have multiple adjacent vertices
			
			//In the context of this question, we can have atmost only 4 adjacent vertices
			
			//Traverse up if unvisited C/L
			Pair<Integer, Integer> up = new Pair<>(vertex.getFirst() - 1, vertex.getSecond());
			if(!outOfBounds(graph, up) && !visited.contains(up) && isLand_OrCloud(graph, up)) 
				DFS_implicitStack_Rec(graph, up, visited);
			
			//Traverse down if unvisited C/L
			Pair<Integer, Integer> down = new Pair<>(vertex.getFirst() + 1, vertex.getSecond());
			if(!outOfBounds(graph, down) && !visited.contains(down) && isLand_OrCloud(graph, down)) 
				DFS_implicitStack_Rec(graph, down, visited);
			
			//Traverse left if unvisited C/L
			Pair<Integer, Integer> left = new Pair<>(vertex.getFirst() , vertex.getSecond() - 1);
			if(!outOfBounds(graph, left) && !visited.contains(left) && isLand_OrCloud(graph, left)) 
				DFS_implicitStack_Rec(graph, left, visited);
			
			//Traverse right if unvisited C/L
			Pair<Integer, Integer> right = new Pair<>(vertex.getFirst() , vertex.getSecond() + 1);
			if(!outOfBounds(graph, right) && !visited.contains(right) && isLand_OrCloud(graph, right)) 
				DFS_implicitStack_Rec(graph, right, visited);
			
		}
		
		private static boolean outOfBounds(char[][] graph, Pair<Integer, Integer> vertex) 
		{
			int row = vertex.getFirst();
			int col = vertex.getSecond();
			
			return ! ((row >= 0 && row <= graph.length - 1) && (col >= 0 && col <= graph[row].length - 1));
		}
		
		private static boolean isLand_OrCloud(char[][] graph, Pair<Integer, Integer> vertex) 
		{
			return isLand(graph, vertex) || isCloud(graph, vertex);
		}
		
		private static boolean isLand(char[][] graph, Pair<Integer, Integer> vertex) {
			int row = vertex.getFirst();
			int col = vertex.getSecond();
			
			return graph[row][col] == 'L' || graph[row][col] == 'l';
		}
		
		private static boolean isCloud(char[][] graph, Pair<Integer, Integer> vertex) {
			int row = vertex.getFirst();
			int col = vertex.getSecond();
			
			return graph[row][col] == 'c' || graph[row][col] == 'C';
		}
			
}

