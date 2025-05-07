import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class cmr {
	
	  public class cmrString{
		  
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
		
		
	
		
	  }
	  public class cards{
			
			 static int deckCount = 0;
			 private static List<card> cardsDrawn = new LinkedList<card>();
			 private static int numbCardsPerSuit = 13;	
			 private static String[] cardPrefixNames = new String[numbCardsPerSuit];
			
			 private static List<card>deck = new LinkedList();
			 private static List<cardSuit>suitDeck = new LinkedList();
			
			 public static class hand{
				public hand(String handName, List<card> listOfCards) {
					playerName = handName;
					cardList = listOfCards;
				}
				public  String playerName;
				public  List<card> cardList = new LinkedList<card>();
			 }
			public static List<card> cardsPlayed(List<card> drawn){
				
				if(drawn != null) {
					
					cardsDrawn = drawn;
				}
				else {
					
					return(cardsDrawn);
				}
				return(null);
			}
			public static List<card>cardDeck(List<card>cDeck){
				
				if(cDeck != null) {
					
					deck = cDeck;
				}
				else {
					
			
				return(deck);
				}
				return(null);
			}
			public static List<cardSuit>suitSet(List<cardSuit>cSuit){
				
				if(cSuit != null) {
					
					suitDeck = cSuit;
				}
				else
				{
					
				return(suitDeck);
				
				}
				return(null);
			}
		    public static List<card> makeDeck(String[] suiteNames,int cardsPerSuit){
		    	
		     setNumbCardsPerSuit(cardsPerSuit);
		     suitDeck = generateSuits(suiteNames,numbCardsPerSuit,cardPrefixNames);
		     deck = createDeck(suitDeck);
		     deckCount = cardCount(new hand("Deck",deck));
		     return(deck);
		     
		    }
			
			
			public static void setCardNameByIndex(String[] names) {
				cardPrefixNames = new String[numbCardsPerSuit];
				int ts = 0;
				
				for(String t : names) {
					
					cardPrefixNames[ts] = t;
					ts++;
				}
			}
			private static void setNumbCardsPerSuit(int t) {
				
				numbCardsPerSuit = t;
			}
			public static void reset(List<hand> resetHands) {
				
				for(hand i : resetHands) {
					
					i.cardList.clear();
				}
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
			
			public static void printHand(hand cHand) {
				
				for(card t : cHand.cardList) {
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
		   public static int cardCount(hand cardsToCount) {
			   
			  return(cardsToCount.cardList.size());
		   }
		   private static List<card> createDeck(List<cardSuit> cardSuitDeck) {
			   
		   	List<card> newDeck = new LinkedList();
		   	System.out.println("Suit Size:" + getDeckCount(cardSuitDeck));
		   	int decksize = getDeckCount(cardSuitDeck);
		   	
		   	for(int i = 0; i < decksize; i++) {
		   		
		   		newDeck.add(getCard(cardSuitDeck));
		   		System.out.println("Deck Size: " + newDeck.size());
		   	}
		   	return(newDeck);
		   }
		   public static void draw(int numb,List<card>deck,hand cHand) {
			   
			   for(int i = 0; i < numb; i++) {
				   
				   if(deck.size() > 0)
				   {
				   cHand.cardList.add(deck.get(0));
				   cardsDrawn.add(deck.get(0));
				   deck.remove(0);
				   }
				   else {
					   
					   break;
				   }
			   }
			   
		   
		   }
		   public static void discardCard(hand cHand,int cardIndex) {
			   
			   cHand.cardList.remove(cardIndex);
		   }
			public static card getCard(List<cardSuit> cardDeck) {
				
				 int pickedsuit = randomSuit(cardDeck);
				 int pickedCard = randomCard(cardDeck.get(pickedsuit).cards);
				 card cardPicked = cardDeck.get(pickedsuit).cards.get(pickedCard);
				
				 cardDeck.get(pickedsuit).cards.remove(pickedCard);
			     deckCount = getDeckCount(cardDeck);
			     if(cardDeck.get(pickedsuit).cards.size() == 0) {
			    	 
			    	 cardDeck.remove(pickedsuit);
			    	 
			     }
			    	
			     
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
			
			public static class cardSuit{
				
				public cardSuit(String nameSuit, List<card> crds) {
					if(nameSuit.contains("|")) {
						
						suitName = cmr.cmrString.split(nameSuit,'|').get(0);
						color = cmr.cmrString.split(nameSuit,'|').get(1);
						
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
						
						suitName = cmr.cmrString.split(cardSuit,'|').get(0);
						color = cmr.cmrString.split(cardSuit,'|').get(1);
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

}
