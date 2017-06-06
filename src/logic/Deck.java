package logic;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Deck {

	private int[] cards;
	private Stack<Integer> drawPile;
	private Stack<Integer> discardPile;
	private final int numCards = 60;
	
	public Deck(){
	
		this.cards = new int[numCards];
		instanstiateCards();
		this.drawPile = new Stack<Integer>();
		fillDeck();
		this.discardPile = new Stack<Integer>();
	}
	
	/*
	 * take top card from the drawing pile
	 */
	
	public Integer pickDrawPile() throws PileEmptyException  //shouldn't really happen cuz should keep reshuffling.... Also, he doesn't like code that's on one line without{} like this, so we should probably change that --SB
	{
		if(!drawPile.isEmpty()) return drawPile.pop();   //it would be easier for me if it's returned as a string
		
		else throw new PileEmptyException();
	}
	
	/*
	 * peek top card from the discard pile
	 */
	public Integer getTopDiscard() throws PileEmptyException
	{
		if(!discardPile.isEmpty()) return discardPile.peek();
		else throw new PileEmptyException();
	}
	/*
	 * take top card from the discard pile
	 */
	public Integer pickDiscardPile() throws PileEmptyException
	{
		if(!discardPile.isEmpty()) return discardPile.pop();
		else throw new PileEmptyException();
	}
	/*
	 * add a card to the discard pile 
	 */
	public void addToDiscardPile(int num) 
	{
		this.discardPile.push(num);
	}

	private void instanstiateCards(){
		for(int i = 0; i < numCards; i++){
			cards[i] = i+1;
		}
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
			temp = cards[i];
			cards[i] = cards[value];
			cards[value] = temp;
		}
	}
	
	//There needs to be a method that shuffles the cards in the discard pile (except the top one) when the draw pile is empty and then puts them in the draw pile and then only leaves the top one in the discard pile -- SB
	
	
	
}
