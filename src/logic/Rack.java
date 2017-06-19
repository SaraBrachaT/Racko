package logic;

import java.util.ArrayList;

public class Rack {

	private int[] cards;
//	private String userName;
	private static final int numSlots = 10;
	private Player player;
	private int totalPoints;
	
	public Rack(Player player){
		
		this.cards = new int[numSlots];
		//this.userName = computer;
		this.player = player;
		this.totalPoints = 0;
		//fill the rack
		
	}
	/*
	public int[] getCards(){
		return this.cards;
	}
	*/
	public int getCard(int index){
		return cards[index];
	}
	public void setCard(int i,int val ){
	cards[i] = val;	
	}
	public int getTotalPoints(){
		return this.totalPoints;
	}
	public void setTotalPoints(int amnt){
		this.totalPoints = amnt;
	} 
	public int getNumSlots(){
		return numSlots;
	}
	
}
