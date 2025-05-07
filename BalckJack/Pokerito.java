import java.util.Arrays;
import java.util.Scanner;
import java.util.LinkedList;
import java.util.List;
import java.util.AbstractList;

public class Pokerito{
	
	 static int deckCount = 0;
	 static List<card> cardsDrawn = new LinkedList<card>();
	 static List<card> playerHand = new LinkedList<card>();
	 static List<card> river = new LinkedList<card>();
	 static List<card>computerHand = new LinkedList<card>();
	 static int numbCardsPerSuit = 13;	
	 static String[] cardPrefixNames = new String[numbCardsPerSuit];
	 static Scanner scan = new Scanner(System.in);
	 static List<cardSuit>deck = new LinkedList();
	
	
	public static void main(String[] args) {
	
    cardPrefixNames[0] = "A";
    cardPrefixNames[10] = "J";
    cardPrefixNames[11] = "Q";
    cardPrefixNames[12] = "K";
    
	while(runGame()) {
		
		
	}
	
	   }
	
	
	public static boolean runGame(){
		
		   deck = generateSuits(new String[] {"hearts|red","diamonds|red","puppytoes|black","spades|black"},numbCardsPerSuit,cardPrefixNames);
		

		 
		   draw(1,deck, playerHand);
		   draw(1,deck,computerHand);
		   draw(5,deck,river);
		   
		   int playerMatches =  0;
		   playerMatches = returnMatches(playerHand,river);
		   int dealerMatches = 0; 
		   dealerMatches = returnMatches(computerHand,river);
		   
		   System.out.println("\nThe River:");
		   printHand(river);
		   
		   System.out.println("\nYour hand:");
		   printHand(playerHand);
		   System.out.println("Your Matches: " + playerMatches);
		   
		   System.out.println("\nComputer Hand:");
		   printHand(computerHand);
		   System.out.println("Computer Matches: " + dealerMatches);
		   
		  
		  if(playerMatches > dealerMatches) {
			  
			 System.out.println("\nYou Win!");
		  }
		  else if(dealerMatches > playerMatches) {
			  
			  System.out.println("\nComputer Wins!");
		  }
		  else if(playerMatches == dealerMatches) {
			  
			  System.out.println("\nEvery one wins!");
		  }
		  System.out.print("\nWould you like to play again type 'yes' to continue: ");
		  
		  if(scan.nextLine().toLowerCase().equals("yes")) {
			  
			  
		  reset(); 
		  return(true);
		  
		  }
		  else {
			  
			  System.out.println("\nGame Over - Good Bye!");
			  scan.close();
			 
			  return(false);
		  }
		  
	}
	
	public static void reset() {
		
		  playerHand.clear();
		  computerHand.clear();
		  river.clear();
		  cardsDrawn.clear();
		  deck.clear();
		  
	}
	
	public static int returnMatches(List<card>hand, List<card> river) {
		int match = 0;
	   for(card i : river) {
		   for(int t = 0; t < hand.size(); t++) {
			   
			   if(hand.get(t).value == i.value) {
				   
				   match++;
			   }
		   }
	   }
	   return(match);
	}
	
	public static void printHand(List<card> hand) {
		
		for(card t : hand) {
			printCard(t);
		}
	}
	
	public static void printCard(card vCard) {
		if(vCard.name != null) {
			
			System.out.println(vCard.name + " " + vCard.suitName + " " + vCard.color);
		}
		else {
			
			System.out.println(vCard.value + " " + vCard.suitName + " " + vCard.color);
		}
	}
	
    public static void draw(int numToDraw,List<cardSuit> cardDeck,List<card>Hand) {
    	
    	for(int i = 0; i < numToDraw; i++) {
    		
    		Hand.add(getCard(cardDeck));
    	}
    }
    
	public static card getCard(List<cardSuit> cardDeck) {
		
		 int pickedsuit = randomSuit(cardDeck);
		 int pickedCard = randomCard(cardDeck.get(pickedsuit).cards);
		 card cardPicked = cardDeck.get(pickedsuit).cards.get(pickedCard);
		 cardsDrawn.add(cardPicked);
		 cardDeck.get(pickedsuit).cards.remove(pickedCard);
	     deckCount = getDeckCount(cardDeck);
	     
		 return(cardPicked);
		
	
	}
	
	public static int getDeckCount(List<cardSuit> deck) {
		
		int deckCount = 0;
		for(cardSuit t: deck) {
			
			deckCount += t.cards.size();
			
		}
		return(deckCount);
	}
	
    public static int randomCard(List<card> cardsToChooseFrom) {
    	
    return((int)(Math.random() * cardsToChooseFrom.size()));
    
    }
    
	public static int randomSuit(List<cardSuit> suitsToChooseFrom) {
		
		return((int)(Math.random() * suitsToChooseFrom.size()));
	}
	
	public static List<cardSuit> generateSuits(String[] suitNames,int numbCards,String[] cardNames){
		List<cardSuit> suitList = new LinkedList();
		for(String t:suitNames) {
			
			cardSuit newSuit = new cardSuit(t,generateCards(t,numbCards));
			for(int i = 0; i < cardNames.length; i++) {
				
				if(cardNames[i] != null) {
					
					newSuit.cards.get(i).name = cardNames[i];
				}
				
			}
			
			suitList.add(newSuit);
		}
	
		return(new LinkedList(suitList));
	
	}
	public static List<String> split(String strToSplit,char splitByChar) {
		List<String> result = new LinkedList();
		String text = "";
	
		for(int i = 0; i < strToSplit.length();i++) {
			
			if(strToSplit.charAt(i) != splitByChar) {
				
				text += strToSplit.charAt(i);
			}
			else {
				
				result.add(text);
				text = "";
				
			}
		}
		result.add(text);
		return(result);
	}
	public static class cardSuit{
		
		public cardSuit(String nameSuit, List<card> crds) {
			if(nameSuit.contains("|")) {
				
				suitName = split(nameSuit,'|').get(0);
				color = split(nameSuit,'|').get(1);
				
			}
			else {
				
			suitName = nameSuit;
			
			}
		
			cards = crds;
		}
		public String suitName;
		public List<card> cards;
		public String color;
		
	
	}
	public static class card{
		
		public card(String cardSuit, int cardValue) {
			
			if(cardSuit.contains("|")) {
				
				suitName = split(cardSuit,'|').get(0);
				color = split(cardSuit,'|').get(1);
			}
			else {
				
			suitName = cardSuit;
			}
			value = cardValue;
		}
		public String suitName;
		public int value;
		public String color;
		public String name;
	}
	
public static List<card> generateCards(String suitName,int cardCount){
		
		card[] cards = new card[cardCount];
		List<card>cardList;
		for(int i = 0; i < cards.length; i++) {
			card newCard = new card(suitName,i + 1);
			cards[i] = newCard;
		}
		
	   cardList = new LinkedList<card>(Arrays.asList(cards));
	   return(cardList);
		
	}

}