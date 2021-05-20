package oneDay_assignments;

import java.util.*;
import convenienceClass.*;
import fast_input_classes.FastReader;

import java.io.*;

//Had TLE! Then I changed terminating condition of prim algo from
//!min.isEmpty to num vertices in mst != num vertices in graph
//This sho
public class LostMap {

	public static void main(String[] args) throws IOException {
		FastReader scanner = new FastReader();
		BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();

		
		int villages = scanner.nextInt();
		AdjList<Vertex> graph = new AdjList<>(villages);
		
		Vertex to, from;
		
		for(int i = 0; i <= villages - 1; i++)
		{
			from = new Vertex(i, 0);
			
			for(int j = 0; j <= villages - 1; j++)
			{
				if(i == j)
				{
					scanner.nextInt();
					continue;
				}
				
				to = new Vertex(j, scanner.nextInt());
				graph.addArc(from, to);
			}
		}
		
		AdjList<Vertex> mst =  graph.prim(new Vertex(0,0));
		
		//Variables for the loop below lol
		Iterator<Vertex> iterator;
		Set<Pair<Integer,Integer>> dontWant = new HashSet<>();
		Pair<Integer,Integer> oppositeArc, originalArc;
		int v;
		
		for(int i = 0; i <= mst.order() - 1 ; i++) {
			iterator = mst.adjacency_list.get(i).listIterator();
			
			while(iterator.hasNext()) {
				
				v = iterator.next().getIndex();
				originalArc = new Pair<>(i,v);
				
				
				if(!dontWant.contains(originalArc)) {
					sb.append( (i+1) +" "+ (v + 1) +"\n");	
					
					oppositeArc = new Pair<>(v, i);
					dontWant.add(oppositeArc);
				}
				
			}
		}
		
		out.write(sb.toString());
		out.flush();
	}

}

//Minimalistic adjacency list
class AdjList<E extends Vertex> {
	
	public List< ArrayList<E> > adjacency_list;

	
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
	

	public AdjList<Vertex> prim(E source) {
		
		Map<Vertex,Vertex> srcToNeighbour = new HashMap<>(order());
		Set<Integer> seen = new HashSet<>(order());;
		AdjList<Vertex> mst = new AdjList<>(order());
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
		int count = 1;
		enqueueUnseen(source, minHeap, seen, srcToNeighbour);

		E currCheapest;
		while(count != order()) {

			currCheapest = minHeap.remove();



			if(!seen.contains(currCheapest.getIndex()))
			{
				mst.addEdge(srcToNeighbour.get(currCheapest), currCheapest);

				//We have added this vertex into tree as of now
				seen.add(currCheapest.getIndex());
				count++;
				enqueueUnseen(currCheapest, minHeap, seen, srcToNeighbour);
			}
		}

		return mst;
	}
	
	private  void enqueueUnseen(E source,PriorityQueue<E> minHeap,Set<Integer> seen, Map<Vertex,Vertex> srcToNeighbour)
	{
		for(E adjacentVertex : neighbours(source.getIndex()) )
		{
			if(!seen.contains(adjacentVertex.getIndex()))
			{
				minHeap.add(adjacentVertex);
				srcToNeighbour.put(adjacentVertex, source);
			}
		}
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i <= order() - 1; i++ )
		{
			sb.append(adjacency_list.get(i)+"\n");
		}
		
		return sb.toString();
	}
}

