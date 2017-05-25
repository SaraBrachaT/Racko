package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameBoard extends JFrame
{

	private JPanel mainFrame;
	private JLabel gameTitle;
	private JButton drawPile;
	private JButton discardPile;
	private JButton[] user1Cards;
	private JLabel computerBoard;


	public GameBoard()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0 , screenSize.width, screenSize.height);
		mainFrame = new JPanel();
		
		mainFrame.setLayout(null);
		//setBackground(Color.___ or Theme.___- make a theme so user can have options)
		setContentPane(mainFrame);
		user1Cards = new JButton[10];
		

		
		
		
		setUpBoards();
		mainFrame.setVisible(true);
	}
	
	private void setUpBoards()
	{
		ImageIcon background = createImageIcon("/cardFront.png");
			
		for(int card = 0; card < user1Cards.length; card++)
		{
			user1Cards[card] = new JButton(background);
			//user1Cards[card].setText(deck.drawCard());
			user1Cards[card].setHorizontalTextPosition(JButton.CENTER);
			user1Cards[card].setVerticalTextPosition(JButton.CENTER);
			user1Cards[card].setSize(90, 70);
			user1Cards[card].setLocation(175 + (card*100), 600);
			mainFrame.add(user1Cards[card]);

		}
	

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

	}
}
