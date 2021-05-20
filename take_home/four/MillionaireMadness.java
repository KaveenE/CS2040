package take_home.four;

import java.util.*;
import convenienceClass.Vertex;
import convenienceClass.Pair;
import fast_input_classes.FastReader;
import java.io.*;

public class MillionaireMadness {
	
	
	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		
		
		int row = scanner.nextInt();
		int col = scanner.nextInt();
		int[][] matrix = new int[row][col];

		read2D(scanner, matrix);
		
		//Just initialize with max vertices we can have
		AdjList<Vertex> graph = new AdjList<>(row*col);
		
		initializeGraph(graph, matrix);
		
		double minEdge = graph.prim(new Vertex(0,0) , col * row - 1);
		
		out.write((int)minEdge+"");
		out.flush();
		
		
	
	}

	public static void read2D(FastReader scanner, int[][] adjacency_matrix)
	{

	    //Counter
	    int row,col;

	    for(row=0;row<=adjacency_matrix.length-1;row++)
	    {
	    
	        for(col=0;col<=adjacency_matrix[row].length-1;col++)
	        {
	        	adjacency_matrix[row][col]=scanner.nextInt();
	        }

	    }

	}
	
	public static void initializeGraph(AdjList<Vertex> graph, int[][] adjacency_matrix)
	{
		//Counter
	    int row,col;
	    Vertex from;
	    Vertex to;
	    
	    
		for(row=0;row<=adjacency_matrix.length-1;row++)
	    {
	    
	        for(col=0;col<=adjacency_matrix[row].length-1;col++)
	        {
	        	/*
	        	//Up
	        	if(!outOfBounds(row - 1, col, adjacency_matrix)) {
	        		from = new Vertex(row*adjacency_matrix.length + col, 0);
	        		to = new Vertex((row - 1)*adjacency_matrix.length +col, Math.abs(adjacency_matrix[row - 1][col] - adjacency_matrix[row][col]) );
	        		
	        		graph.addArc(from,to);
	        	}
	        	
	        	//Down
	        	if(!outOfBounds(row + 1, col, adjacency_matrix)) {
	        		from = new Vertex(row*adjacency_matrix.length + col, 0);
	        		to = new Vertex((row + 1)*adjacency_matrix.length + col, Math.abs(adjacency_matrix[row + 1][col] - adjacency_matrix[row][col]) );
	        		
	        		graph.addArc(from,to);
	        	}
	        	
	        	//Left
	        	if(!outOfBounds(row, col - 1, adjacency_matrix)) {
	        		from = new Vertex(row*adjacency_matrix.length + col, 0);
	        		to = new Vertex(row*adjacency_matrix.length + (col - 1), Math.abs(adjacency_matrix[row][col - 1] - adjacency_matrix[row][col]) );
	        		
	        		graph.addArc(from,to);
	        	}
	        	
	        	//Right
	        	if(!outOfBounds(row, col + 1, adjacency_matrix)) {
	        		from = new Vertex(row*adjacency_matrix.length +col, 0);
	        		to = new Vertex(row*adjacency_matrix.length + (col + 1), Math.abs(adjacency_matrix[row][col + 1] - adjacency_matrix[row][col]) );
	        		
	        		graph.addArc(from,to);
	        	}*/
	        	
	        	
	        	for(col=0;col<=adjacency_matrix[row].length-1;col++)
		        {
		        	
		        	//Up
		        	if(!outOfBounds(row - 1, col, adjacency_matrix)) {
		        		from = new Vertex(row*adjacency_matrix[row].length + col, 0);
		        		to = new Vertex((row - 1)*adjacency_matrix[row].length +col, Math.max(0, adjacency_matrix[row - 1][col] - adjacency_matrix[row][col]))  ;
		        		
		        		graph.addArc(from,to);
		        	}
		        	
		        	//Down
		        	if(!outOfBounds(row + 1, col, adjacency_matrix)) {
		        		from = new Vertex(row*adjacency_matrix[row].length + col, 0);
		        		to = new Vertex((row + 1)*adjacency_matrix[row].length + col, Math.max(0, adjacency_matrix[row + 1][col] - adjacency_matrix[row][col]))  ;
		        		
		        		graph.addArc(from,to);
		        	}
		        	
		        	//Left
		        	if(!outOfBounds(row, col - 1, adjacency_matrix)) {
		        		from = new Vertex(row*adjacency_matrix[row].length + col, 0);
		        		to = new Vertex(row*adjacency_matrix[row].length + (col - 1), Math.max(0, adjacency_matrix[row][col - 1] - adjacency_matrix[row][col]))  ;
		        		
		        		graph.addArc(from,to);
		        	}
		        	
		        	//Right
		        	if(!outOfBounds(row, col + 1, adjacency_matrix)) {
		        		from = new Vertex(row*adjacency_matrix[row].length +col, 0);
		        		to = new Vertex(row*adjacency_matrix[row].length + (col + 1), Math.max(0, adjacency_matrix[row][col + 1] - adjacency_matrix[row][col]))  ;
		        		
		        		graph.addArc(from,to);
		        	}
		        }

	        }

	    }
	}
	
	private static boolean outOfBounds(int row, int col, int[][] graph) 
	{
		return ! ((row >= 0 && row <= graph.length - 1) && (col >= 0 && col <= graph[row].length - 1));
	}
	
	

}


//Minimalistic adjacency list
class AdjList<E extends Vertex> {
	
	private List< ArrayList<E> > adjacency_list;
	private double maximumOfMin;
	
	public AdjList(int numVertices)
	{
		//Create my array
		adjacency_list=new ArrayList<>();
		
		//Fill up each element of array
		//I could either fill up 
		//with null AND when when I actually insert something, I do null check
		//OR straight away fill with empty linked list
		for(int idx=0;idx<=numVertices-1;idx++)
		{
			adjacency_list.add(new ArrayList<>());
			
		}
	}
	
	public void addArc(E fromVertex,E toVertex)
	{
		adjacency_list.get(fromVertex.getIndex()).add(toVertex);
	}
	
	public void addEdge(E fromVertex,E toVertex)
	{
		addArc(fromVertex, toVertex);
		addArc(toVertex, fromVertex);

	}
	
	public boolean isArc(int from, E adjacentVertex)
	{
		return adjacency_list.get(from).contains(adjacentVertex);
	}
	
	public List<E> neighbours(int vertexIdx)
	{
		//Returns an unmodifiable list of vertices
		//Dont't worry the vertices are immutable so don't have to deep copy
		return List.copyOf(adjacency_list.get(vertexIdx));
	}
	
	//Number of vertices
	public int order()
	{
		return adjacency_list.size();
	}
	

	public double prim(E source,int targetIdx) {
		
		maximumOfMin = 0;
		Set<Integer> seen = new HashSet<>(order());;
		
		PriorityQueue<E> minHeap = new PriorityQueue<>(order(), 
													   (u,v) ->{
																   if(Double.compare(u.getWeight(), v.getWeight()) != 0)
																	{
																		return (int)(u.getWeight() - v.getWeight());
																	}
																	else
																	{
																		return u.getIndex() - v.getIndex();
																	}
														   	   });
		
		minHeap.add(source);
		seen.add(source.getIndex());
		
		enqueueUnseen(source, minHeap, seen);
		
		E currCheapest;
		while(!minHeap.isEmpty()) {
			
			currCheapest = minHeap.remove();
			
			//minimax path is already formed. Not need to obtain whole MST
			if(seen.contains(source.getIndex()) && seen.contains(targetIdx) )
			{
				break;
			}
					
			if(!seen.contains(currCheapest.getIndex()))
			{
				maximumOfMin = Math.max(maximumOfMin, currCheapest.getWeight());
				
				//We have added this vertex into tree as of now
				seen.add(currCheapest.getIndex());
				
				enqueueUnseen(currCheapest, minHeap, seen);
			}
		}
		
		return maximumOfMin;
	}
	
	private  void enqueueUnseen(E source,PriorityQueue<E> minHeap,Set<Integer> seen)
	{
		for(E adjacentVertex : neighbours(source.getIndex()) )
		{
			if(!seen.contains(adjacentVertex.getIndex()))
			{
				minHeap.add(adjacentVertex);
			}
		}
	}
}



