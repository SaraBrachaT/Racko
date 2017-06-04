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

public class GameBoard extends JFrame
{

	private JPanel mainFrame;
	private JLabel gameTitle;
	private JButton drawPile;
	private JButton discardPile;
	private JButton[] user1Cards;
	private JTabbedPane user1CardPane;
	private JLabel computerBoard;
	private JButton btnNewButton;


	public GameBoard()
	{
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
		user1CardPane = new JTabbedPane(JTabbedPane.RIGHT);
		user1CardPane.setBounds(337, 22, 300, 200);
		mainFrame.add(user1CardPane);
		
		
		
		
		setUpBoards();
		setUpPiles();
		mainFrame.setVisible(true);
	}
	
	private void setUpBoards()
	{
		ImageIcon background = createImageIcon("/partialCardFront.PNG");
		ImageIcon background2 = createImageIcon("/cardFront.png");
		
		Random tempGen = new Random(System.currentTimeMillis());
			
		for(int card = 0; card < user1Cards.length-1; card++)
		{
			user1Cards[card] = new JButton(background);
			user1Cards[card].setBorder(null);
			//user1Cards[card].setText(deck.drawCard());

			user1Cards[card].setHorizontalTextPosition(JButton.CENTER);
			user1Cards[card].setVerticalTextPosition(JButton.CENTER);
			user1Cards[card].setSize(170, 50);
			user1Cards[card].setLocation(40, 20 + (card*50));
			mainFrame.add(user1Cards[card]);
			//setComponentZOrder(user1Cards[card], card);
/*			
			user1Cards[card] = new JButton(background2);
			user1CardPane.addTab("Card " + card, null, user1Cards[card], null);
			user1Cards[card].setText(((Integer)tempGen.nextInt(60)).toString());
			user1Cards[card].setText("Hello World");*/
		}
		user1Cards[9] = new JButton(background2);
		user1Cards[9].setSize(170, 115);
		user1Cards[9].setLocation(40, 20 + (9*50));
		mainFrame.add(user1Cards[9]);
		
		
		
		computerBoard = new JLabel(createImageIcon("/ComputerBoard.png"));
		computerBoard.setSize(700, 500);
		computerBoard.setLocation(800, 150);
		mainFrame.add(computerBoard);

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
