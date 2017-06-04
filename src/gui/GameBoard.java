package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import logic.Game;

public class GameBoard extends JFrame
{

	private JPanel mainFrame;
	private JLabel gameTitle;
	private JButton drawPile;
	private JButton discardPile;
	private JButton[] user1Cards;
	private JLabel computerBoard;
	private JButton btnNewButton;
	private Game racko;

	public GameBoard()
	{
		racko = new Game("Computer", "User");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
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
		
		
		
		
		setUpBoards();
		setUpPiles();
		mainFrame.setVisible(true);
	}
	
	private void setUpBoards()
	{
		ImageIcon background = createImageIcon("/partialCardFront.PNG");
		ImageIcon background2 = createImageIcon("/cardFront.png");
		
		
			
		for(int card = 0; card < user1Cards.length-1; card++)
		{
			user1Cards[card] = new JButton(background);
			generateCard(card);
		}
		user1Cards[9] = new JButton(background2);
		generateCard(9);
		user1Cards[9].setSize(170, 115);
				
		
		computerBoard = new JLabel(createImageIcon("/ComputerBoard.png"));
		computerBoard.setSize(700, 500);
		computerBoard.setLocation(800, 150);
		mainFrame.add(computerBoard);

	}
	
	private void generateCard(int cardNum)
	{
		Random tempGen = new Random(System.currentTimeMillis());
		
		user1Cards[cardNum].setBorder(null);
		user1Cards[cardNum].setHorizontalTextPosition(JButton.CENTER);
		user1Cards[cardNum].setVerticalTextPosition(JButton.CENTER);
		user1Cards[cardNum].setSize(170, 50);
		user1Cards[cardNum].setLocation(40, 20 + (cardNum*50));
		mainFrame.add(user1Cards[cardNum]);

		//user1Cards[card].setText(racko.drawPile.pickDrawPile());
		user1Cards[cardNum].setText(((Integer)tempGen.nextInt(60)).toString());	
	}
	
private void setUpPiles()
{
	drawPile = new JButton(createImageIcon("/RackoCardBack.PNG"));
	drawPile.setText("Draw Card");
	drawPile.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
	drawPile.setHorizontalTextPosition(JButton.CENTER);
	drawPile.setVerticalTextPosition(JButton.CENTER);
	drawPile.setLocation(575, 350);
	drawPile.setSize(220,170);
	mainFrame.add(drawPile);
	
	

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		GameBoard frame = new GameBoard();
		frame.setVisible(true);
		
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	}
}
