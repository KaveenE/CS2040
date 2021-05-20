package week_3.skener;

import java.util.Scanner;

public class Skener {

	public static void main(String[] args) {
		//Creating scanner and associated input variables
		Scanner scan= new Scanner(System.in);
		int r,zr,c,zc;


		//Reading input variables
		r=scan.nextInt();
		c=scan.nextInt();
		zr=scan.nextInt();
		zc=scan.nextInt();

		scan.nextLine(); //clear buffer
		String[] lineByline_input = new String[r];
		readToStringArray(lineByline_input, scan);
		
		System.out.println(zoomedInVersion(lineByline_input, zr, zc));
		
		scan.close();
		
	}
	
	public static void readToStringArray(String[] array, Scanner scanner)
	{
		for(int row=0; row<=array.length-1; row++)
		{
			String sth=scanner.nextLine();
			array[row]=sth;
		}
	}
	
	public static String zoomedInVersion(String[] array, int zoomRow, int zoomCol )
	{
		StringBuilder copyOfSomeRow = new StringBuilder();
		StringBuilder sbForInput = new StringBuilder();
		String zoomedVersionOfCol;
		
		for(int row=0; row<=array.length-1; row++)
		{
			for(int character=0; character<=array[row].length()-1 ; character++)
			{
				zoomedVersionOfCol = (array[row].charAt(character)+"").repeat(zoomCol);
				//Append the zoomCol amount for each column
				copyOfSomeRow.append(zoomedVersionOfCol);
				sbForInput.append(zoomedVersionOfCol);
			}
			
			sbForInput.append("\n");
			for(int copyOfRow=1; copyOfRow<= zoomRow - 1 ; copyOfRow++)
			{
				sbForInput.append(copyOfSomeRow+"\n");
			}
			
			//Reset my copy
			copyOfSomeRow.setLength(0);;
		}
		
		return sbForInput.toString();
	}

}
