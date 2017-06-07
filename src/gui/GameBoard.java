package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import logic.Game;
import logic.PileEmptyException;

public class GameBoard extends JFrame
{

	private JPanel mainFrame;
	private JLabel gameTitle;
	private JButton drawPile;
	private JButton discardPile;
	private JButton[] user1Cards;
	private JLabel computerBoard;
	private JButton btnNewButton;
	private JLabel currentCard;
	private JLabel instruction;
	private ImageIcon partialFrontBkgrnd;
	private ImageIcon frontBkgrnd;
	private ImageIcon backBkgrnd;
	private Dimension screenSize;
	
	
	private Game racko;
		
	public GameBoard() throws PileEmptyException
	{
		racko = new Game("Computer", "User");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0 , screenSize.width, screenSize.height);
		mainFrame = new JPanel();
		
		mainFrame.setLayout(null);
		mainFrame.setBackground(Color.WHITE);
		//setBackground(Color.___ or Theme.___- make a theme so user can have options)
		setContentPane(mainFrame);

		gameTitle = new JLabel("Racko");
		gameTitle.setLocation(575, 0);
		gameTitle.setSize(1000,100);
		gameTitle.setFont(new Font("Britannic Bold", Font.PLAIN, 56));
		mainFrame.add(gameTitle);
		
		user1Cards = new JButton[10];
		
		partialFrontBkgrnd = createImageIcon("/partialCardFront.PNG");
		frontBkgrnd = createImageIcon("/cardFront.png");
		backBkgrnd = createImageIcon("/RackoCardBack.PNG");
		
		
		
		setUpBoards();
		setUpPiles();
		setUpCurrentCard();
		mainFrame.setVisible(true);
	}
	
	private void setUpBoards() throws PileEmptyException
	{
		for(int card = 0; card < user1Cards.length-1; card++)
		{
			user1Cards[card] = new JButton(partialFrontBkgrnd);
			generateCard(card);
		}
		user1Cards[9] = new JButton(frontBkgrnd);
		generateCard(9);
		user1Cards[9].setSize(170, 115);
				
		
		computerBoard = new JLabel(createImageIcon("/ComputerBoard.png"));
		computerBoard.setSize(700, 500);
		computerBoard.setLocation(800, 150);
		mainFrame.add(computerBoard);

	}
	
	private void generateCard(int cardNum) throws PileEmptyException
	{
		Random tempGen = new Random(System.currentTimeMillis());
		
		user1Cards[cardNum].setBorder(null);
		user1Cards[cardNum].setHorizontalTextPosition(JButton.CENTER);
		user1Cards[cardNum].setVerticalTextPosition(JButton.CENTER);
		user1Cards[cardNum].setSize(170, 50);
		user1Cards[cardNum].setLocation(40, 20 + (cardNum*50));
		mainFrame.add(user1Cards[cardNum]);

		user1Cards[cardNum].setText(((Integer)racko.getDrawPile().pickDrawPile()).toString());
		//user1Cards[cardNum].setText(((Integer)tempGen.nextInt(60)).toString());	
	}
	
private void setUpPiles()
{
	drawPile = new JButton(backBkgrnd);
	drawPile.setText("Draw Card");
	drawPile.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
	drawPile.setHorizontalTextPosition(JButton.CENTER);
	drawPile.setVerticalTextPosition(JButton.CENTER);
	drawPile.setLocation(625, 350);
	drawPile.setSize(170, 115);
	drawPile.setBorder(null);
	mainFrame.add(drawPile);
	
	discardPile = new JButton(frontBkgrnd);
	discardPile.setLocation(470, 350);
	discardPile.setSize(170, 115);
	discardPile.setBorder(null);
	discardPile.setVisible(false);
	mainFrame.add(discardPile);
	
	
	
	drawPile.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    	pickCardFromDraw();
	    	//check which button clicked on
	    	JButton pressedButton = findPressedButton();
	    	pressedButton.addActionListener(new ActionListener() {
	    		public void actionPerformed(ActionEvent e) {
	    			placeCard(pressedButton);
	    		}
	    });
	    }
	});
	

}

private void setUpCurrentCard()
{
	instruction = new JLabel("Fix Wording: Click a card on your board to place this card there. \nIf you would not"
			+ " like to use this card, click the discard pile");
	instruction.setLocation(550, 5);
	instruction.setSize(800,200);
	instruction.setVisible(false);
	mainFrame.add(instruction);
			
	currentCard = new JLabel(frontBkgrnd);
	currentCard.setSize(700,500);
	currentCard.setLocation(300, 10);
	currentCard.setHorizontalTextPosition(JButton.CENTER);
	currentCard.setVerticalTextPosition(JButton.CENTER);
	currentCard.setVisible(false);
	mainFrame.add(currentCard);
	mainFrame.repaint();
}

private void setCurrentCardValue(String cardNum)
{
	currentCard.setText(cardNum.toString());
	currentCard.setVisible(true);	
}

	/** Returns an ImageIcon, or null if the path was invalid. */
	private ImageIcon createImageIcon(String path) {
	    java.net.URL imgURL = getClass().getResource(path);
	    if (imgURL != null) {
	        return new ImageIcon(imgURL);
	    } else {
	        System.err.println("Couldn't find file: " + path);
	        return null;
	    }
	}

	private JButton findPressedButton()
	{
		for(JButton card : user1Cards)
		{
			if(card.getModel().isPressed())
			{
				return card;
			}
		}
		
		if(discardPile.getModel().isPressed())
		{
			return discardPile;
		}
		return null;
	}
	
	private void pickCardFromDraw()
	{
		if(!currentCard.isVisible())
    	{
    		try
    		{
    			setCurrentCardValue(racko.getDrawPile().pickDrawPile().toString());
    		}
    		catch (PileEmptyException e1)
    		{
    			//show empty pile...shouldn't really happen cuz should keep reshuffling
    		}
    		instruction.setVisible(true);
    	}

	}
	
	private void placeCard(JButton pressedCard)
	{
    	//do this when discard
/*	    	if(!discardPile.isVisible())
    	{
    		discardPile.setVisible(true);
    	}*/
	}

	/**
	 * Launch the application.
	 * @throws PileEmptyException 
	 */
	public static void main(String[] args) throws PileEmptyException
	{
		GameBoard frame = new GameBoard();
		frame.setVisible(true);
	}
	
}
