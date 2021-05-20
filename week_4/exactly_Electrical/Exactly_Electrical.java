package week_4.exactly_Electrical;

import java.awt.Point;
import java.util.Scanner;

//For this problem, we make the observation that if the car can reach any intersection (x,y) using n electric charges, it can do so in n+2 charges. 
//This means that if the number of charges left over after reaching destination with shortest distance is not even-->N else-->Y

public class Exactly_Electrical {

	public static void main(String[] args) 
	{
		Scanner scan=new Scanner(System.in);
		Point[] points=new Point[2];
		
		for(int i=0;i<=points.length-1;i++)
		{
			points[i]=new Point(scan.nextInt(),scan.nextInt());
		}
		
		int energy=scan.nextInt();
		int distance=(int)shortestUnitsToTravel(points[0],points[1]);
		char output='N';
		
		
	    if(distance>energy || (energy-distance)%2==1 )
			output='N';
		else
			output='Y';
		
		System.out.println(output);
	}
	
	public static double shortestUnitsToTravel(Point x,Point y)
	{
		
		return Math.abs( x.getX()-y.getX() ) + Math.abs( x.getY()-y.getY() );
	}

}
