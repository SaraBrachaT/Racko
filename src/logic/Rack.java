package logic;

import java.util.ArrayList;

import mancala.Player;

public class Rack {

	private static final int numSlots = 10;
	
	private int[] cards;
	
	private Player player;
	
	//the way to set the heuristic of a move to 
	//negative is if the heuristic will be worse than the current one
	//this should probably be set when the game fills the cards
	private double currentHeuristic;
	
	public Rack(Player player){
		
		this.cards = new int[numSlots];
		this.player = player;
		
		//fill the rack -- happens in Game
		
	}
	
	public int getCard(int index)
	{
		return cards[index];
	}
	
	public void setCard(int i,int val )
	{
		cards[i] = val;	
	}
	
	public int getNumSlots()
	{
		return numSlots;
	}
	
	//this method should be called right after game fills the racks
	public void setCurrentHeuristic()
	{
		currentHeuristic = heuristicValue();
	}
	/* I don't think there are points in racko --MB
	public int getTotalPoints()
	{
		return this.totalPoints;
	}
	
	public void setTotalPoints(int amnt)
	{
		this.totalPoints = amnt;
	} 
	*/

	private double heuristicValue() {
		double retVal = 0.0;
        if (isWin())
        {
            retVal = 1.0;
        }
        else if (isWin(Player.MIN))
        {
            retVal = -1.0;
        }
        else
        {
            retVal = heuristicPercent();
        }
        return retVal;
	}

	private boolean isWin() {
		
		for(int i = 1;i < cards.length; i++)
		{
			if(cards[i]  < cards[i-1]) return false;
		}
		return true;
	}
	
	/*We have getCard and setCard and numSlots to provide all of the functinality
	 * without this -- we don't want to return the actual array
	public int[] getCards(){
		return this.cards;
	}
	*/
	
	/*We are not using either of these because the game goes once
	 * private int totalPoints;
	private String Username*/
}
