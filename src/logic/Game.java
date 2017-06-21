package logic;

import java.util.ArrayList;


public class Game {
/*
 * Did not finish logic for the make move method...in middle of it...
 * Questions for SB,MBG...from TL
	1.why did you do two separate instances of deck class, why not 1? 
	
	2. In the deck class-you made a string array of cards, 
		I parsed it...unless we could change it back to the array
	3.	can i assume that the we dont need to do any data validation for the player, 
	 	so far now, the computer is the only one in the makeMove method. 
	 4. SB- it seems that you already did code for the player's move, so i didn't do any makeMove for the player	
	*/
	
	/*
	 * Look at one spot at a time:
	 * if(number in the spot > number after(on top of) it || number in the spot < number before(on bottom of it) it
	 * 	&& pickedCard satisfied one condition)
	 * 			switch 
	 * 
	 * each turn try has to be put into copied rack
	 * 
	 * heuristic -- one for each position in the array, at end, pick the best move
	 * 
	 * 	 the heuristic is given for the new state of the rack after makeMove is called for each position
	 * 		*amount of cards in order after we put it in
	 *  	how do calculate order?
	 *  	not just consecutive cards in order....
	 *  	also add a little for cards that are in order even with out of order in betwee -- example 3 13 9 10, --
	 *  
	 *  	*based on its placement between the lowest card possible and highest card possible, is it placed 
	 *  	correctly in the board -- for example, if lowest is 1 and highest is 60, 30 should 
	 *  	be placed in the halfway slot
	 *   	
	 * ;*/
	private Deck drawPile;
	private Deck discardPile;
	
	
	private Deck deck;
	
	private Rack[] racks; 
	int currentCard;//for keeping track of the card picked this turn easily
	//to keep track of who we're up to. there might be a much easier way
	boolean player1;
	boolean player2;
	Player player;
	//the gui passes in the names of the two players
	//and instantiates the game
	
	//Perhaps make a no args constructor that instantiates the 2 players to computer and user -- SB 
	public Game()
	{
		
		drawPile = new Deck();
		discardPile = new Deck();
		
		deck = new Deck();
		
		racks = new Rack[2];
		racks[0] = new Rack(Player.COMPUTER);
		racks[1] = new Rack(Player.HUMAN);
		
		currentCard = 0;//nothing yet, there is no card 0
		
	//	player1 = true;
	//	player2 = false;
	}
	//depending what SB says, switch the Deck to either 2 or 1 instance of the deck class
	public Game(Rack[] racks2,Deck drawPile2, Deck discardPile2){
		racks = new Rack[2];
		racks[0]= new Rack(Player.COMPUTER);
		racks[1] = new Rack(Player.HUMAN);
		
		for(int i =0;i<racks.length;i++)
		{
			for(int j =0;j<racks[i].getNumSlots();j++)
			{
				racks[i].setCard(j, racks2[i].getCard(j));
			}
		}
		
		racks[0].setCurrentHeuristic();
		racks[1].setCurrentHeuristic();
		
		drawPile = drawPile2;
		discardPile = discardPile2;
		}
	//pick cards from deck to fill the rack
	//can move
	public boolean canMove(Player who,String card){
		if(who.equals(Player.COMPUTER)){
		for(int i =0;i<racks.length;i++){
			
		}
		}
		else{
			for(int i =0;i<racks.length;i++){
				
			}
		}
		
	}
	//make move Pretty sure makeMove goes into the rack class -- MB
	public Game makeMove(int pos){
		//programming the computers move, cuz the user does the gui no?
		Game newGame = new Game(racks,drawPile,discardPile);
		int card = -1;
		//can either draw from discarded or draw pile
		//computer takes from the discarded pile and compares it with each card-till finds a place to put into
		if(!newGame.discardPile.isDiscardPileEmpty()){
			//computer will take from the discarded pile
			card = Integer.parseInt(newGame.discardPile.getTopDiscard());
			
		}
		else{
			//computer will take from the draw pile
			card = Integer.parseInt(newGame.drawPile.getTopDiscard());
		}
		//see where on the rack the card should go...
		//if anyone disagrees with my logic, feel free to change/comment
		for(int i=1;i<racks[0].getNumSlots();i++){
		
			if(card > racks[0].getCard(i-1) && card > racks[0].getCard(i))
			
			else if(card < racks[0].getCard(i) && card > racks[0].getCard(i -1) );//no need to replace
		}
		
		
		return newGame;
	}
	 /**
     * is the given player the winner
     *
     * @param Player player: COMPUTER or PLAYER
     * @return boolean decision
     */
	
	/* I copied this into rack, I think that's where it goes--MB
	public boolean isWin(Player player){
		if(player.equals(Player.COMPUTER)){
			for(int i =1;i<racks[0].getNumSlots();i++){
				if(racks[0].getCard(i)  < racks[0].getCard(i-1))return false;
			}
		}
		else{
			for(int i =1;i<racks[1].getNumSlots();i++){
				if(racks[1].getCard(i)  < racks[1].getCard(i-1))return false;
			}
		}
		//will keep track of who the winner is
		this.player = player;
		return true;
	}
	*/
	
	/*
	public void calculatePoints(){
		int winner = 25;
		int pointsPerCard = 5;
		int counter = 0;
		if(player.equals(Player.COMPUTER)){
			//calculate computers points
				racks[0].setTotalPoints(winner + (pointsPerCard*racks[0].getNumSlots()));
			//calculate players points
				for(int i =1;i<racks[0].getNumSlots();i++){
					if(racks[1].getCard(i)>racks[1].getCard(i))counter++;
				}
				racks[1].setTotalPoints(pointsPerCard * counter);
		}
		else{
		//player is winner
			racks[1].setTotalPoints(winner + (pointsPerCard*racks[1].getNumSlots()));
		//calculate computers points
			for(int i =1;i<racks[0].getNumSlots();i++){
				if(racks[0].getCard(i)>racks[0].getCard(i))counter++;
			}
			racks[0].setTotalPoints(pointsPerCard * counter);
		}
	}
	
		*/
	/*Pretty sure the heuristicValue() goes into the rack class--MB
	public double heuristicValue(){
	    double retVal = 0.0;
	    if (isWin(Player.COMPUTER))
	    {
	        retVal = 1.0;
	    }
	    else if (isWin(Player.HUMAN))
	    {
	        retVal = -1.0;
	    }
	    else
	    {
	        retVal = getHeuristicValue();
	    }
	    return retVal;
	}
	private double getHeuristicValue()
	{
		/*copied from manacala
		double value = 0.0;
		if(computerMancala > TOTAL_PER_SIDE)value = 1;
		else if(humanMancala > TOTAL_PER_SIDE)value =-1;
		else value =((computerMancala - humanMancala) /TOTAL_PIECES);
		return value;

	}
*/	

	//I created a getter so I can use the deck methods. I don't know if this is what you were planning on doing, so please feel free to
	//delete or edit it --SB
	public Deck getDrawPile()
	{
		return drawPile;
	}
	
	public Deck getDiscardPile()
	{
		return discardPile;
	}
}
