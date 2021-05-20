package take_home.one;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Deck {
	
	public static final int MINIMUM_OFEACH_CARD_FOR_COMBO = 2;
	private List<Integer> deck;
	private static Map<Integer, PriceOfCard> priceOfCards;
	
	public Deck(List<Integer> deck, Map<Integer, PriceOfCard> priceOfCards)
	{
		this.deck = new ArrayList<>(deck);
		this.priceOfCards = new HashMap<>(priceOfCards);
	}
	
	public Deck(List<Integer> deck)
	{
		this.deck = new ArrayList<>(deck);
	}
	
	
	
	public List<Integer> getDeck()
	{
		return new ArrayList<>(deck);
	}
	
	
	public Deck copyOf(Deck deck)
	{
		return new Deck(deck.getDeck());
	}
	
	
	public boolean has_Ktypes(int desired_Types)
	{
		int types=0;
		for(Integer card: this.getDeck())
		{
			if( Collections.frequency(this.getDeck(), card) >= 2 )
			{
				types++;
			}
		}
		
		return types==desired_Types;
	}
	
	public static int totalSellingPrice_Type(int cardNumber, int numOfCards)
	{
		return numOfCards * priceOfCards.get(cardNumber).sellingPrice;
	}
	
	public static int totalBuyingPrice_Type(int cardNumber, int numOfCards)
	{
		return numOfCards * priceOfCards.get(cardNumber).buyingPrice;
	}
	
	
	public static class PriceOfCard
	{
		private int buyingPrice;
		private int sellingPrice;
		
		public PriceOfCard(int bPrice, int sPrice) {
			
			buyingPrice=bPrice;
			sellingPrice=sPrice;
		}

		public int getBuyingPrice() {
			return buyingPrice;
		}

		public int getSellingPrice() {
			return sellingPrice;
		}
		
	}

	public static void main(String[] args) 
	{
		Kattio scanner = new Kattio(System.in);
	
		int	totalCards = scanner.getInt();
		int total_Type = scanner.getInt();
		int desired_Type= scanner.getInt();
		
		//To keep count on the cards. O(1) access when we need access to repetitions!
		Map<Integer, Integer> startingHand = new HashMap<>();
		
		//To read input
		int card;
		
		//Reading my cards
		List<Integer> deckToReadInput = new ArrayList<>(totalCards);
		for(int i=0; i<= totalCards-1 ; i++)
		{
			card = scanner.getInt();
			deckToReadInput.add(card);
			
			int numberOfDuplicates = startingHand.getOrDefault(card,0)  + 1;
			startingHand.put(card, numberOfDuplicates);
			
		}
		
		//Reading card costs
		Map<Integer, PriceOfCard> priceOfCard = new HashMap<>();
		for(int i=1; i<= total_Type ; i++)
		{
			priceOfCard.put(i, new Deck.PriceOfCard(scanner.getInt(), scanner.getInt()));
		}

		
		//Creating Deck Anthony has
		Deck deck = new Deck(deckToReadInput, priceOfCard);
		
		
		int maxProfit = maxProfit(startingHand, total_Type, desired_Type);
		
		System.out.println(maxProfit);
		
		
		
	}
	
	public static int maxProfit(Map<Integer, Integer> startingHand, int total_Type, int desired_Type)
	{
		//Set<List<Integer>> combinationEncountered = new HashSet<>();
		//List<Integer> someCombination = new ArrayList<>(desired_Type);
		
		Map<Integer, Integer> endingHand = new HashMap<>();
		int types = 0;
		int maxProfit = Integer.MIN_VALUE;
		
		for(int i = 1; i <= total_Type; i++)
		{
			endingHand.put(i, MINIMUM_OFEACH_CARD_FOR_COMBO);
			//someCombination.add(i);
			types++;
			
			for(int j = 1; j <= total_Type; j++)
			{
				//Add card only if it's not the base card
				if( j!=i )
				{
					endingHand.put(j, MINIMUM_OFEACH_CARD_FOR_COMBO);
					types++;
				}
				
				if( types == desired_Type)
				{
				
						maxProfit = Math.max(profitBetween_2Hands(startingHand, endingHand, total_Type), maxProfit);
						
					
					
					//After updating maxProfit XOR if got duplicate, we continue on iterating with the card from outer loop as base
					endingHand.remove(j);
					types--;
				}
			}
			
			//Reset
			endingHand.clear();
			//someCombination.clear();
			types = 0;
		}
		
		return maxProfit;
		
	}
	
	private static int profitBetween_2Hands(Map<Integer, Integer> startingHand, Map<Integer, Integer> endingHand, int total_Type)
	{
		int profit = 0;
		
		
		int startHand_Cards = 0;
		int endHand_Cards = 0;
		
		//We see the net gain by looking at his starting hand and seeing what cards he bought and sold to achieve final hand
		//Logic from here kind of follow merge function logic
		for(int i = 1 ; i <= total_Type ; i++)
		{
			startHand_Cards = startingHand.getOrDefault(i, 0);
			endHand_Cards = endingHand.getOrDefault(i, 0);
			
			if(startHand_Cards >= endHand_Cards)
			{
				profit += totalSellingPrice_Type(i, startHand_Cards - endHand_Cards );
			}
			
			if(endHand_Cards > startHand_Cards)
			{
				profit -= totalBuyingPrice_Type( i , endHand_Cards - startHand_Cards );
			}
		}
		
	
		return profit;
	}

}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }
    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }



    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null) return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) { }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}


