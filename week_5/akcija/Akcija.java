package week_5.akcija;

import java.util.Arrays;

import fast_input_classes.*;
public class Akcija {

	public static void main(String[] args) {

		Kattio scanner = new Kattio(System.in);
		
		int numBooks = scanner.getInt();
		int[] bookPrices = new int[numBooks];
		int totalPriceToPay = 0;
		
		for(int i = 0; i <= numBooks-1 ;i++) {
			
			bookPrices[i] = scanner.getInt();
			totalPriceToPay += bookPrices[i];
		}
		
		//To pay the minimal price, we want the 3rd book in a group to have the highest price as possible
		//So I sort them->iterate array from back in groups of 3->get the price of free book in each group which is the highest we can get.
		
		Arrays.sort(bookPrices);
		
		int isGroupOfThree = 0;
		int totalPriceOOfFreeBooks = 0;
		
		for(int idx = bookPrices.length - 1; idx >= 0; idx--) {
			
			isGroupOfThree++;
			
			if(isGroupOfThree % 3 == 0)
				totalPriceOOfFreeBooks += bookPrices[idx];
		}
		
		System.out.println(totalPriceToPay - totalPriceOOfFreeBooks);
	}

}
