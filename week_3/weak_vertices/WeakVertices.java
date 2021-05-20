package week_3.weak_vertices;
import java.util.*;

public class WeakVertices 
{
	public static void main(String[] arg)
	{
		solve();
	}
	
	public static void solve()
	{
		//input variables
		Scanner scanner=new Scanner(System.in);
		int numVertices=0;
		int[][] adjaceny_matrix;
		
		numVertices=Integer.valueOf(scanner.nextLine());
		
		while(numVertices!=-1)
		{
			adjaceny_matrix= read2D(scanner, numVertices);
			
			//Printing out the results after computing it
			for(int weak: processMatrix(adjaceny_matrix))
			{
				System.out.print(weak+" ");
			}
			System.out.println();
			
			numVertices=Integer.valueOf(scanner.nextLine());
		}
	}
	
	//Helper for solve
	private static int[][] read2D(Scanner scanner, int rows)
	{

		//Counter
		int row,col;

		int[][] adjaceny_matrix=new int[rows][rows];

		//I iterate through the row of grid
		for(row=0;row<=rows-1;row++)
		{
			for(col=0;col<=rows-1;col++)
			{
				adjaceny_matrix[row][col]=scanner.nextInt();
			}

		}

		//Clear buffer
		scanner.nextLine();

		return adjaceny_matrix;

	}
	
	//Helper for solve
	private static List<Integer> processMatrix (int[][] adjaceny_matrix)
	{
		List<Integer> weakVerticesPos=new ArrayList<>();
		boolean hasTriangle;
		
		for(int row=0;row<=adjaceny_matrix.length-1;row++)
		{
			hasTriangle=false;//reset
			
			//If I see a row with total degree <2
			//I add to my list that particular row number
			//Since vertex must atleast have 2 edges to form triangle
			if(proceduralSum(adjaceny_matrix[row])<2)
			{
				weakVerticesPos.add(row);
				continue;
			}
			
			//Checking if the >=2 vertices connected to the current vertx
			//are connected to each other themselves to form a triangle
			current_row:
			for(int base=0;base<=adjaceny_matrix.length-1;base++)
			{
				//no point checking if the current vertex is not connected to this
				if(adjaceny_matrix[row][base]==0)
				{
					continue;
				}
				
				for(int comparedTo=base+1;comparedTo<=adjaceny_matrix.length-1;comparedTo++)
				{
					if(adjaceny_matrix[row][comparedTo]!=0)
					{
						//if the other 2 vertices are connected to each other
						//traingle!
						if(adjaceny_matrix[base][comparedTo]!=0)
						{
							//We've now know this current vertex is not a weak one
							//we can proceed to next row(next vertex)
							hasTriangle=true;
							break current_row;
						}
					}
				}
			}
			
			if(hasTriangle==false)
				weakVerticesPos.add(row);
		}
		
		
		
		return weakVerticesPos;
	}
	
	
	
	
	//Helper for helper
	private static int proceduralSum(int[] array)
	{
		int sum=0;
		for(int degree: array)
		{
			sum+=degree;
		}
		
		return sum;
	}
	
	private static int declarativeSum(int[] array)
	{
		return Arrays.stream(array)
		             .sum();
	}
}
