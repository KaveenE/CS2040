package take_home.one;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import fast_input_classes.Kattio;


//We have 2 cases for each card type as per qn
//Buying up to MIN_FOR_COMBO number of cards to get combo for that type  and selling all cards of that type

//We specifically want to know  the COST to do these 2 actions

//Max profit is achieved by BUYING the CHEAPEST ones to get K combo and then SELLING the remaining EXPENSIVE ones 
//--> This part can be achieved in O(n) by sorting the pairs of {buyingCost, sellingCost} in ascending order by buyingCost+sellingCost. Why sort by this order? idk

//Subtract the buying price of first K pairs and add the selling price of remaining types to get max profit:)
public class DeckFast {

	public static final int MINIMUM_OFEACH_CARD_FOR_COMBO = 2;
	
	
	public static class PriceOfCard
	{
		private long buyingPrice;
		private long sellingPrice;
		
		public PriceOfCard(long bPrice, long sPrice) {
			
			buyingPrice=bPrice;
			sellingPrice=sPrice;
		}

		public long getBuyingPrice() {
			return buyingPrice;
		}

		public long getSellingPrice() {
			return sellingPrice;
		}
		
	}

	public static void main(String[] args) {
		
		Kattio scanner = new Kattio(System.in);
		
		int	totalCards = scanner.getInt();
		int total_Type = scanner.getInt();
		int desired_Type= scanner.getInt();
		int i;//counter
		
		//I want a frequency counter for each cards
		//A map would be perfect BUT idw to use it because
		
		//I've long forgotten procedural styles so I would partially do C-style here
		//using an array for frequency counter since key is an integer and thus can use index of array as key
		
		int[] cardFrequency = new int[total_Type+1];
		
		int card;
		for( i=0; i<= totalCards-1 ; i++)
		{
			card = scanner.getInt();
			
			cardFrequency[card]++;
			
		}
		
		
		//Reading card costs
		Map<Integer, PriceOfCard> priceOfCard = new HashMap<>();
		for(i=1; i<= total_Type ; i++)
		{
			priceOfCard.put( i, new DeckFast.PriceOfCard(scanner.getInt(), scanner.getInt()) );
		}
		
		//For each card number(row), I store the respective TOTAL buying and selling prices(2 cols) by the proposed pseduocode above
		long[][] arr= new long[total_Type+1][2];
		
		for(i=1; i<= total_Type ; i++)
		{
			//Note i did max( min_combo - cardfrequency, 0) incase cardfrequency > 2.(PS: idk why also cos i referred to web's help)
			
			arr[i][0] = totalBuyingPrice_Type(priceOfCard, i, Math.max(MINIMUM_OFEACH_CARD_FOR_COMBO - cardFrequency[i], 0)  );
			arr[i][1] = totalSellingPrice_Type(priceOfCard, i, cardFrequency[i] );
			
		}
		
		//Since this sorts arr[] in ascending order ,we have the buying prices in ascending order as intended
		Comparator<long[]> sortBySumOfPrices = (x, y) -> Long.compare(x[0]+x[1], y[0]+y[1]);
		Arrays.sort(arr, sortBySumOfPrices);
		
		long profit =0;
		
		//Subtract the first K cheapest buyingPrice
		for(i=1; i<= desired_Type ; i++)
		{
			profit -= arr[i][0];
			
		}
		
		//Add the remaining T-K expensive sellingPrice
		for (i = desired_Type + 1; i <= total_Type; i++) 
		{
			profit += arr[i][1];

		}
		
		System.out.println(profit);
		//System.out.println(Arrays.deepToString(arr));
		//System.out.println(Arrays.toString(cardFrequency));

	}

	private static long totalSellingPrice_Type(Map<Integer, PriceOfCard> priceOfCards, int cardNumber, int numOfCards)
	{
		return numOfCards * priceOfCards.get(cardNumber).sellingPrice;
	}
	
	private static long totalBuyingPrice_Type(Map<Integer, PriceOfCard> priceOfCards, int cardNumber, int numOfCards)
	{
		return numOfCards * priceOfCards.get(cardNumber).buyingPrice;
	}
}

