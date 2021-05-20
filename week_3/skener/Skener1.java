package week_3.skener;
import java.util.Scanner;

//Qn: It doesnt work when I copy paste input. Says string index out of range: 0. 
//This error is typically generated when i have some sneaky newline character left on buffer so when "input" is read, we can't access it causing the error
//But I made sure there arent any sneaky guys in the buffer tho so why is dis happening to me?



public class Skener1 {

	public static void main(String[] args) 
	{
		//Creating scanner and associated input variables
		Scanner scan= new Scanner(System.in);
		int r,zr,c,zc;

		
		//Reading input variables
		r=scan.nextInt();
		c=scan.nextInt();
		zr=scan.nextInt();
		zc=scan.nextInt();
		
		scan.nextLine();//cleaning up buffer
		
		//Can use array of string but I  using char would allow me to access characters faster than the string's charAt method boys
		char[][] matrix= {};
		
		matrix=read2DArray(r, c, matrix);
		printZoomified(r,c,zr,zc,matrix);
		
		scan.close();
	

	}

	public static void printZoomified(int r,int c,int zr,int zc,char[][] matrix)
	{
		//I'll write down my 1st implmentation which is of O(n^3)!
		
		/*
		
		int row,col,i;
		 
		
		for(row=0;row<=r-1;row++)
		{
			for(i=1;i<=zr;i++)
			{
				for(col=0;col<=c-1;col++)
				{
					System.out.print( zoomifiedCol(matrix[row][col],zc) );
				}
				
				System.out.println();
			}
		}
		
		*/
		
		int row,col;
		StringBuilder string=new StringBuilder();
		//An O(n^2) solution
		for(row=0;row<=r-1;row++)
		{

			for(col=0;col<=c-1;col++)
			{
				string.append(zoomifiedCol(matrix[row][col],zc));
			}

			System.out.println(string);
			
			//Reset stringbuilder. Note u can reset by assigning newStringbuilder()
			//But that is just more work for GC to to garbage collect and more work for allocator to allocate space
			string.setLength(0);
			
		}
		
	}
	
	public static char[][] read2DArray(int r,int c,char[][] twoD_array)
	{
		Scanner scan= new Scanner(System.in);
		
		int row,col;
		String input;
		twoD_array=new char[r][c];
		
		for(row=0;row<=r-1;row++)
		{	
			input=scan.nextLine();
			for(col=0;col<=c-1;col++)
			{
				System.out.print(" "+input.length()+" "+c);
				twoD_array[row][col]=input.charAt(col);
			}
		
		}
		
		return twoD_array;
	}
	
	private static String zoomifiedCol(char character,int zc)
	{
		
		StringBuilder string=new StringBuilder();
		for(int i=1;i<=zc;i++)
		string.append(character);
		return string.toString();
	}
}
