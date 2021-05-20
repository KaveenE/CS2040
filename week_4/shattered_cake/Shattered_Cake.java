package week_4.shattered_cake;

import fast_input_classes.Kattio;


public class Shattered_Cake {

	public static void main(String[] args) {
		
		Kattio io = new Kattio(System.in);
		
		int width = io.getInt();
		int pieces = io.getInt();
		
		int area = 0;
		
		for(int i = 1; i <= pieces ; i++)
		{
			area += io.getInt() * io.getInt();
		}
		
		System.out.println(area/width);

		io.close();
	}

}

