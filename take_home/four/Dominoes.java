package take_home.four;

import java.io.*;
import java.util.*;

import convenienceClass.*;
import fast_input_classes.FastReader;

public class Dominoes {

	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		int tests = scanner.nextInt();
		
		AdjList1<Vertex> graph;
		int vertices, arcs;
		Vertex from, to;
		
		List<Integer> topoSort;
		
		for(int i = 1; i <= tests; i++)
		{
			vertices = scanner.nextInt();
			arcs = scanner.nextInt();
			
			graph = new AdjList1<>(vertices);
			
			//Initialize graph
			for(int j = 1; j <= arcs; j++)
			{
				from = new Vertex(scanner.nextInt() - 1, 0);
				to = new Vertex(scanner.nextInt() - 1, 0);
				
				graph.addArc(from, to);
			}
			
			topoSort = AdjList1.DFS_implicitStack(graph);
			
			sb.append(AdjList1.DFS_implicitStack1(graph, topoSort)+"\n");
		}
		
		out.write(sb.toString());
		out.flush();
	}
}


class AdjList1<E extends Vertex> {
	
	private List< ArrayList<E> > adjacency_list;

	
	public AdjList1(int numVertices)
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
	
	//DFS
	public static <E extends Vertex>  List<Integer> DFS_implicitStack(AdjList1<E> graph)
	{
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[graph.order()];

		//predecessor[v] stores predecessor of v
		//Allows to obtain visitation sequence
		int[] predecessor=new int[graph.order()];
		Arrays.fill(predecessor, -1);

		List<Integer> topoSort = new ArrayList<>(graph.order());

		// Call the recursive helper function to DFS
		//vertex by vertex only if it hasn't been visited
		// The recursive function by itself will traverse entire graph if graph was all connected
		//We do this loop in case of multiple components
		for (int i = 0; i <= graph.order()-1; i++)
		{
			if (visited[i] == false)
				DFS_implicitStack_Rec( graph,i, visited, predecessor,topoSort);
		}

		return topoSort;
	}

	private static <E extends Vertex> void DFS_implicitStack_Rec(AdjList1<E> graph,int vertex_index,boolean[] visited,int[] predecessor,List<Integer> topoSort)
	{
		// Mark the current node as visited
		//Prevents visiting duplicate nodes.
		//If we visited duplicate nodes, we'ill end in infinite loop
		//as the same thing is gonna repeat all over
		visited[vertex_index] = true;

		//Recursively, search along the path of this vertex till base case
		//Then backtrack
		//We can have multiple paths 
		//as current source vertex can have multiple adjacent vertices
		Iterator<E> linkedlist_iterator=graph.adjacency_list.get(vertex_index).listIterator();

		//Do pre-order work here(this is when we visit vertex)
	
		while (linkedlist_iterator.hasNext()) 
		{
			E next = linkedlist_iterator.next();

			//Base case
			if (visited[next.getIndex()])
			{
				//Do nth
			}
			else
			{

				predecessor[next.getIndex()]=vertex_index;
				DFS_implicitStack_Rec(graph, next.getIndex(),visited, predecessor,topoSort);

			}
		}
		//Do post-order work here(this is when we backtrack to vertex)
		topoSort.add(vertex_index);

	}
	
	public static <E extends Vertex>  int DFS_implicitStack1(AdjList1<E> graph, List<Integer> topoSort)
	{
		int count = 0;
		
		// Mark all the vertices as not visited(set as
		// false by default in java)
		boolean visited[] = new boolean[graph.order()];

		//predecessor[v] stores predecessor of v
		//Allows to obtain visitation sequence
		int[] predecessor=new int[graph.order()];
		Arrays.fill(predecessor, -1);

		// Call the recursive helper function to DFS
		//vertex by vertex only if it hasn't been visited
		// The recursive function by itself will traverse entire graph if graph was all connected
		//We do this loop in case of multiple components
		for (int i = topoSort.size() - 1; i >= 0; i--)
		{
			if (visited[topoSort.get(i)] == false) {
				DFS_implicitStack_Rec1( graph,topoSort.get(i), visited, predecessor);
				count++;
			}
				
		}

		return count;
	}
	
	private static <E extends Vertex> void DFS_implicitStack_Rec1(AdjList1<E> graph,int vertex_index,boolean[] visited,int[] predecessor)
	{
		// Mark the current node as visited
		//Prevents visiting duplicate nodes.
		//If we visited duplicate nodes, we'ill end in infinite loop
		//as the same thing is gonna repeat all over
		visited[vertex_index] = true;

		//Recursively, search along the path of this vertex till base case
		//Then backtrack
		//We can have multiple paths 
		//as current source vertex can have multiple adjacent vertices
		Iterator<E> linkedlist_iterator=graph.adjacency_list.get(vertex_index).listIterator();

		//Do pre-order work here(this is when we visit vertex)
		
		while (linkedlist_iterator.hasNext()) 
		{
			E next = linkedlist_iterator.next();

			//Base case
			if (visited[next.getIndex()])
			{
				//Do nth
			}
			else
			{

				predecessor[next.getIndex()]=vertex_index;
				DFS_implicitStack_Rec1(graph, next.getIndex(),visited, predecessor);

			}
		}
		//Do post-order work here(this is when we backtrack to vertex)
	

	}
}

