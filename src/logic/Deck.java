package logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Deck {

	private String[] cards;
	private Stack<String> drawPile;
	private Stack<String> discardPile;
	private final int numCards = 60;
	
	public Deck(){
	
		this.cards = new String [numCards];
		instanstiateCards();
		this.drawPile = new Stack<String>();
		fillDeck();
		this.discardPile = new Stack<String>();
	}
	
	/*
	 * take top card from the drawing pile
	 */
	
	public String pickDrawPile() throws PileEmptyException  //shouldn't really happen cuz should keep reshuffling.... Also, he doesn't like code that's on one line without{} like this, so we should probably change that --SB
	{
		if(!drawPile.isEmpty()) return drawPile.pop();   //it would be easier for me if it's returned as a string
		
		else throw new PileEmptyException();
	}
	
	/*
	 * peek top card from the discard pile
	 */
	public String getTopDiscard() throws PileEmptyException
	{
		if(!discardPile.isEmpty()) return discardPile.peek();
		else throw new PileEmptyException();
	}
	/*
	 * take top card from the discard pile
	 */
	public String pickDiscardPile() throws PileEmptyException
	{
		if(!discardPile.isEmpty()) return discardPile.pop();
		else throw new PileEmptyException();
	}
	/*
	 * add a card to the discard pile 
	 */
	public void addToDiscardPile(String num) 
	{
		this.discardPile.push(num);
	}

	private void instanstiateCards(){
		for(int i = 0; i < numCards; i++){
			cards[i] = String.valueOf(i+1);
		}
	}
	public boolean isDiscardPileEmpty(){
		if(discardPile.isEmpty())return true;
		else return false;
	}
	private void fillDeck(){
		
		shuffleCards();
		
		for(int i = 0; i < cards.length; i++)
		{
			drawPile.push(cards[i]);
		}
	}
	
	private void shuffleCards()
	{
		Random rand = new Random(System.currentTimeMillis());
		int value;
		int temp;
		
		for(int i = 0; i < numCards; i++)
		{
			value = rand.nextInt(60);
			temp = Integer.valueOf(cards[i]);
			cards[i] = cards[value];
			cards[value] = String.valueOf(temp);
		}
	}
	
	//There needs to be a method that shuffles the cards in the discard pile (except the top one) when the draw pile is empty and then puts them in the draw pile and then only leaves the top one in the discard pile -- SB
	
	
	
}
