package week_5.password_hacking;


import java.util.*;

import fast_input_classes.Kattio;


public class Password_Hacking{

	public class Password {
		private final String password;
		private final double probability;
		
		public Password( String passwordP, double probabilityP)
		{
			password = passwordP;
			probability = probabilityP;
		}
		
		public double getProbability() {
			
			return probability;
		}

		
		
		
	}
	public static void main(String[] args) {
		
		Kattio scanner = new Kattio(System.in);
		
		
		
		Password_Hacking ph = new Password_Hacking();
		Password_Hacking.Password[] passwords = new Password[scanner.getInt()];
		
		
		for(int i = 0; i <= passwords.length -1 ;i++) {
			
			passwords[i] = ph.new Password(scanner.getWord(), scanner.getDouble() );
		}
		
		Comparator<Password> sortByProbability = (Password x, Password y)-> Double.compare( y.getProbability(), x.getProbability()) ;
		//Sorts in descending order
		Arrays.sort(passwords, sortByProbability);
		
		double totalTries = 0;
		for(int i = 0 ; i <= passwords.length-1 ; i++)
		{
			totalTries+= passwords[i].getProbability() * (i+1);
		}
		
		
		System.out.println(totalTries);
	}

}
