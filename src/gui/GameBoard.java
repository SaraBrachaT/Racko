package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
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
		
		mainFrame.setLayout(new BorderLayout());
		//setBackground(Color.___ or Theme.___- make a theme so user can have options)
		setContentPane(mainFrame);
		user1Cards = new JButton[10];
		

		
		
		
		setUpBoards();
	}
	
	private void setUpBoards()
	{
		ImageIcon background = createImageIcon("/cardFront.png");
		
		   JPanel subPanel = new JPanel();
		    
		for(int card = 0; card < user1Cards.length; card++)
		{
			user1Cards[card] = new JButton(background);
			//user1Cards[card].setIcon(createImageIcon("/RackoCardBack.PNG"));
			user1Cards[card].setPreferredSize(new Dimension(90, 70));
			//user1Cards[card].setBounds((10+30*card), 300, 20, 18);
			subPanel.add(user1Cards[card], BorderLayout.PAGE_END);
		}
		mainFrame.add(subPanel, BorderLayout.PAGE_END);

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
