package oneDay_assignments;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

import fast_input_classes.FastReader;

public class WeakVertices {
	
	public static void main(String[] args) throws IOException {
		//input variables
	    FastReader scanner=new FastReader();
	    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
	    int numVertices=0;
	    int[][] adjaceny_matrix;
	    StringBuilder sb = new StringBuilder();
	    
	    numVertices=scanner.nextInt();
	    
	    while(numVertices!=-1)
	    {
	        adjaceny_matrix= read2D(scanner, numVertices);
	        
	        //Printing out the results after computing it
	        for(int weak: processMatrix(adjaceny_matrix))
	        {
	            sb.append(weak+" ");
	        }
	        out.write(sb.toString()+"\n");
	        out.flush();
	        
	        //Reset
	        sb.setLength(0);
	        numVertices=scanner.nextInt();
	    }
	}
	
	public static int[][] read2D(FastReader scanner, int rows)
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

      

        return adjaceny_matrix;

    }
	
	public static List<Integer> processMatrix(int[][] matrix) {
		int start, end, i;
		boolean hasTriangle;
		List<Integer> weak = new ArrayList<>();
		
		for(start = 0; start <= matrix.length - 1; start++) {
			
			hasTriangle = false;
			//If vertex is less than 2 degree, confirm, can't form triangle
			if(outputSum(matrix[start]) < 2) {
				weak.add(start);
				continue;
			}
			
			breakHere:
			for(end = 0; end <= matrix.length - 1; end++) {
				
				
				if(isEdge(matrix, start, end)) {
					for(i = end + 1; i <= matrix.length - 1; i++) {
						
						//Triangle present!
						if(isEdge(matrix,start,i) && isEdge(matrix,end,i)) {
							hasTriangle = true;
							break breakHere;
						}
					}
				}
			}
			
			if(!hasTriangle)weak.add(start);
		}
		
		return weak;
	}
	
	private static int outputSum(int[] arr) {
		int sum=0;
		
		for(int i = 0; i <= arr.length - 1 ; i++)sum+=arr[i];
		
		return sum;
	}
	public static boolean isEdge(int[][] matrix, int vertex1, int vertex2) {
		return isArc(matrix, vertex1, vertex2) && isArc(matrix,vertex2, vertex1);
	}
	
	private static  boolean isArc(int[][] matrix, int vertex1, int vertex2) {
		return matrix[vertex1][vertex2] != 0;
	}

}
