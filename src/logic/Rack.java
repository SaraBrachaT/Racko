package racko;

import java.util.ArrayList;

public class Rack {

	private int[] cards;
	private String userName;
	private final int numSlots = 10;
	
	public Rack(String user){
		
		this.cards = new int[numSlots];
		this.userName = user;
	}
	
}
