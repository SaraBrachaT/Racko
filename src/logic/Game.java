package logic;

public class Game {

	
	private Deck drawPile;
	private Rack[] racks;
	int currentCard;//for keeping track of the card picked this turn easily
	//to keep track of who we're up to. there might be a much easier way
	boolean player1;
	boolean player2;
	
	//the gui passes in the names of the two players
	//and instantiates the game
	
	//Perhaps make a no args constructor that instantiates the 2 players to computer and user -- SB 
	public Game(String player1, String player2)
	{
		drawPile = new Deck();
		
		racks = new Rack[2];
		racks[0] = new Rack(player1);
		racks[1] = new Rack(player2);
		
		currentCard = 0;//nothing yet, there is no card 0
		
	//	player1 = true;
	//	player2 = false;
	}

	//I created a getter so I can use the deck methods. I don't know if this is what you were planning on doing, so please feel free to
	//delete or edit it --SB
	public Deck getDrawPile()
	{
		return drawPile;
	}
}
