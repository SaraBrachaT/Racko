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

//TODO: If pick from discard, instead of picking that, places already picked card where want to place card from discard player
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
	private ImageIcon emptyDiscardBkgrnd;
	private Dimension screenSize;
	private PlaceListener cardPlaced;
	private boolean ignoreClick;

	private Game racko;

	public GameBoard() throws PileEmptyException
	{
		//racko = new Game("Computer", "User");
		//no args constructor,which instantiates player in the game class
		racko = new Game();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);
		mainFrame = new JPanel();

		mainFrame.setLayout(null);
		mainFrame.setBackground(Color.WHITE);
		// setBackground(Color.___ or Theme.___- make a theme so user can have
		// options)
		setContentPane(mainFrame);

		gameTitle = new JLabel("Racko");
		gameTitle.setLocation(575, 0);
		gameTitle.setSize(1000, 100);
		gameTitle.setFont(new Font("Britannic Bold", Font.PLAIN, 56));
		mainFrame.add(gameTitle);

		user1Cards = new JButton[10];

		partialFrontBkgrnd = createImageIcon("/partialCardFront.PNG");
		frontBkgrnd = createImageIcon("/cardFront.png");
		backBkgrnd = createImageIcon("/RackoCardBack.PNG");
		emptyDiscardBkgrnd = createImageIcon("/DiscardPile.PNG");

		setUpBoards();
		setUpPiles();
		setUpCurrentCard();
		mainFrame.setVisible(true);
	}

	private void setUpBoards() throws PileEmptyException
	{
		for (int card = 0; card < user1Cards.length - 1; card++)
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
		user1Cards[cardNum].setLocation(40, 20 + (cardNum * 50));
		cardPlaced = new PlaceListener((user1Cards[cardNum]));
		user1Cards[cardNum].addActionListener(cardPlaced);
		mainFrame.add(user1Cards[cardNum]);

		user1Cards[cardNum].setText(racko.getDrawPile().pickDrawPile());
		// user1Cards[cardNum].setText(((Integer)tempGen.nextInt(60)).toString());
	}

	private void setUpPiles()
	{
		drawPile = new JButton(backBkgrnd);
		drawPile.setText("Draw Card");
		drawPile.setFont(new Font("Britannic Bold", Font.PLAIN, 20));
		drawPile.setHorizontalTextPosition(JButton.CENTER);
		drawPile.setVerticalTextPosition(JButton.CENTER);
		drawPile.setLocation(640, 350);
		drawPile.setSize(170, 115);
		drawPile.setBorder(null);
		mainFrame.add(drawPile);

		discardPile = new JButton(emptyDiscardBkgrnd);
		discardPile.setLocation(470, 350);
		discardPile.setSize(170, 115);
		discardPile.setHorizontalTextPosition(JButton.CENTER);
		discardPile.setVerticalTextPosition(JButton.CENTER);
		discardPile.setBorder(null);
		mainFrame.add(discardPile);

		drawPile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				pickCardFromDraw();
			}
		});

		discardPile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				if(currentCard.isVisible())
				{
					discardPickedCard();
				}
				else
				{
					pickCardFromDiscard();	
					System.out.println("In else");
				}
				
			}
		});
		

	}

	private void setUpCurrentCard()
	{
		instruction = new JLabel("Fix Wording: Click a card on your board to place this card there. \nIf you would not"
				+ " like to use this card, click the discard pile");
		instruction.setLocation(550, 5);
		instruction.setSize(800, 200);
		instruction.setVisible(false);
		mainFrame.add(instruction);

		currentCard = new JLabel(frontBkgrnd);
		currentCard.setSize(700, 500);
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
	private ImageIcon createImageIcon(String path)
	{
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null)
		{
			return new ImageIcon(imgURL);
		}
		else
		{
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	/*
	 * private JButton findPressedButton() {
	 * System.out.println("In find PressedButton"); for(JButton card :
	 * user1Cards) { if(card.getModel().isPressed()) {
	 * System.out.println(card.getText()); return card; } }
	 * 
	 * if(discardPile.getModel().isPressed()) {
	 * System.out.println(discardPile.getText()); return discardPile; }
	 * System.out.println("Returning null");
	 * 
	 * return null; }
	 */
	private void pickCardFromDraw()
	{
		if (!currentCard.isVisible())
		{
			try
			{
				setCurrentCardValue(racko.getDrawPile().pickDrawPile().toString());
				ignoreClick = false;
			}
			catch (PileEmptyException e1)
			{
				// show empty pile...shouldn't really happen cuz should keep
				// reshuffling
			}
			instruction.setVisible(true);
		}

	}

	private void pickCardFromDiscard()
	{
		System.out.println("In pick discard");
		if (!currentCard.isVisible())
		{
			try
			{
				System.out.println("Current card is not visible");
				setCurrentCardValue(racko.getDiscardPile().pickDiscardPile().toString());
				System.out.println(currentCard.getText());
				ignoreClick = false;
			}
			catch (PileEmptyException e)
			{
				// TODO get rid of this exception
			}
			instruction.setVisible(true);
		}
	}

	private void placeCard(JButton pressedCard)
	{
		String pressedText = pressedCard.getText();
		pressedCard.setText(currentCard.getText());
		discardPile.setText(pressedText);
		discardPile.setIcon(frontBkgrnd);
		currentCard.setVisible(false);
		racko.getDiscardPile().addToDiscardPile(pressedText);
		// TODO: Now if click another card, this value goes there also
		// Need to somehow reset currentCard to null, but I don't want to keep
		// reinstantiating it...
		// And turn action listener off...
		setUpCurrentCard();
	}
	
	private void discardPickedCard()
	{
		discardPile.setText(currentCard.getText());
		discardPile.setIcon(frontBkgrnd);
		currentCard.setVisible(false);
		racko.getDiscardPile().addToDiscardPile(currentCard.getText());
		setUpCurrentCard();
	}
	/**
	 * Launch the application.
	 * 
	 * @throws PileEmptyException
	 */
	public static void main(String[] args) throws PileEmptyException
	{
		GameBoard frame = new GameBoard();
		frame.setVisible(true);
	}

	private class PlaceListener implements ActionListener
	{
		private JButton theCard;

		public PlaceListener(JButton placedCard)
		{
			theCard = placedCard;
		}

		public void actionPerformed(ActionEvent arg0)
		{
			if (ignoreClick)
			{
				return;
			}
			else
			{
				placeCard(theCard);
				ignoreClick = true;
			}
		}
	}
}
