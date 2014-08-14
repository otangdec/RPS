/*
 * Programmer:   Oranuch Tangdechavut
 * Course:       CS 111B - Fall 2013
 * Program Name: RPSConsoleGame.java 
 * Objective: Using RPS class to create a Graphic User Interface version of the RPS game. 
 *            Users will have option to place a bet or just play the game.
 */

import java.awt.*;
import java.awt.event.*;
import java.util.regex.*;
import javax.swing.*;

public class RPSGuiGame extends JFrame {

	// buttons for the user to enter their move
	private JButton rockButton, paperButton, scissorsButton;

	// labels to display the number of wins/losses/ties
	private JLabel statusC, statusU, statusT, statusB;

	// images and labels to display the computer and user's moves and the outcome of a match-up
	private ImageIcon rockImage, paperImage, scissorsImage, introImage;
	private JLabel compPlay, userPlay;
	private JLabel outcome;
	
	// the game object
	private RPS game;

	public RPSGuiGame(int userBet) {

		// initializes the window
		super("Rock, Paper, Scissors, Go!");
		setSize(400, 300);
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.black);
		setResizable(false);

		// creates the game object
		game = new RPS(userBet);
		// 	NOTE: IF COMPLETING THE EXTRA CREDIT, YOU WILL CHANGE THE 0 PARAMETER TO A VARIABLE THAT REPRESENTS THE BET AMOUNT

		// creates the labels for displaying the computer and user's move;
		// the images for the moves and the outcome of a match-up will be displayed
		// in a single panel
		rockImage = new ImageIcon("rock.png");
		paperImage = new ImageIcon("paper.png");
		scissorsImage = new ImageIcon("scissors.png");
		introImage = new ImageIcon("intro.png");
		
		compPlay = new JLabel();
		compPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		compPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		compPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		compPlay.setForeground(Color.white);
		userPlay = new JLabel();
		userPlay.setVerticalTextPosition(SwingConstants.BOTTOM);
		userPlay.setHorizontalTextPosition(SwingConstants.CENTER);
		userPlay.setBorder(BorderFactory.createLineBorder(Color.black, 5));
		userPlay.setForeground(Color.white);
		
		//initialize setIcon to display the introduction image at the begining of the game
		userPlay.setIcon(introImage);
		
		outcome = new JLabel();
		outcome.setHorizontalTextPosition(SwingConstants.CENTER);
		outcome.setForeground(Color.pink);
		

		
		JPanel imageOutcomePanel = new JPanel();
		imageOutcomePanel.setBackground(Color.black);
		imageOutcomePanel.setLayout(new BorderLayout());
		imageOutcomePanel.add(compPlay, BorderLayout.WEST);
		imageOutcomePanel.add(userPlay, BorderLayout.EAST);
		imageOutcomePanel.add(outcome, BorderLayout.SOUTH);
		
		// creates the labels for the status of the game (number of wins, losses, and ties);
		// the status labels will be displayed in a single panel
		statusC = new JLabel("Computer Wins: " + game.getPcWin());
		statusU = new JLabel("User Wins: " + game.getUserWin());
		statusT = new JLabel("Ties: " + game.getTie());
		statusB = new JLabel(" Balance: $" + game.getBalance());
		statusC.setForeground(Color.white);
		statusU.setForeground(Color.white);
		statusT.setForeground(Color.white);
		statusB.setForeground(Color.white);
		JPanel statusPanel = new JPanel();
		statusPanel.setBackground(Color.black);
		statusPanel.add(statusC);
		statusPanel.add(statusU);
		statusPanel.add(statusT);
		statusPanel.add(statusB);
		
		// if user did not put any bet or put an invalid amount of bet, the statusB label will be invisible
		if(userBet == 0) statusB.setVisible(false);
		
		// the play and status panels are nested in a single panel
		JPanel gamePanel = new JPanel();
		gamePanel.setPreferredSize(new Dimension(250, 250));
		gamePanel.setBackground(Color.black);
		gamePanel.add(imageOutcomePanel);
		gamePanel.add(statusPanel);
		
		// creates the buttons and displays them in a control panel;
		// one listener is used for all three buttons
		rockButton = new JButton("Play Rock");
		rockButton.addActionListener(new GameListener());
		paperButton = new JButton("Play Paper");
		paperButton.addActionListener(new GameListener());
		scissorsButton = new JButton("Play Scissors");
		scissorsButton.addActionListener(new GameListener());

		JPanel controlPanel = new JPanel();
		controlPanel.add(rockButton);
		controlPanel.add(paperButton);
		controlPanel.add(scissorsButton);
		controlPanel.setBackground(Color.black);

		// the gaming and control panel are added to the window
		contentPane.add(gamePanel, BorderLayout.CENTER);
		contentPane.add(controlPanel, BorderLayout.SOUTH);
		
		setVisible(true);
	}

	/* determines which button was clicked and updates the game accordingly */
	private class GameListener implements ActionListener {
		
		int pcMove = 0, userMove = 0;
		
		private void updateUserMoveDisplay(ActionEvent event)
		{
		// set userMove and userPlay's Icon
			if( event.getSource() == rockButton) {
				userPlay.setIcon( rockImage );
				userMove = game.ROCK;
			}
			else if (event.getSource() == paperButton){
				userPlay.setIcon(paperImage);
				userMove = game.PAPER;
			}
			else if (event.getSource() == scissorsButton){
				userPlay.setIcon(scissorsImage);
				userMove = game.SCISSORS;
			}
		}
		
		private void updateComputerMoveDisplay(ActionEvent event)
		{
		// set pcMove and comPlay's Icon
			if (pcMove == game.ROCK)
				compPlay.setIcon(rockImage);
			else if (pcMove == game.PAPER)
				compPlay.setIcon(paperImage);
			else if (pcMove == game.SCISSORS)
				compPlay.setIcon(scissorsImage);
		}
		
		private void updateOutcomeColor()
		{
		// set the color of the text according to the winning result
			String winner = game.getWinner();
			if(winner.equals("tie"))
				outcome.setForeground(Color.yellow);
			else if(winner.equals("user"))
			    outcome.setForeground(Color.blue);
			else if(winner.equals("pc"))
				outcome.setForeground(Color.red);
			
			if(game.getBalance() < 0) statusB.setForeground(Color.red);
			else statusB.setForeground(Color.green);
			
		}
		
		private void updateStatusDisplay()
		{
			// display the result , who win, and the message , Rock smashes paper
			outcome.setText(game.getMessage() + "  " + game.getResult());
			
			// display the statistics and balance
			statusC.setText("Computer Wins: " + game.getPcWin());
			statusU.setText("User Wins: " + game.getUserWin());
			statusT.setText("Ties: " + game.getTie());
			statusB.setText("Balance: $" + game.getBalance()); 
		}
		
		public void actionPerformed(ActionEvent event) {
	
			pcMove = game.generateComputerPlay();
			
			// display the label for 2 icons
			userPlay.setText("User:");
			compPlay.setText("Computer:");
			
			updateUserMoveDisplay(event);
			updateComputerMoveDisplay(event);
		
			game.setResult(game.findWinner(userMove, pcMove));
			game.setMessage(userMove, pcMove);
			
			updateOutcomeColor();
			updateStatusDisplay();

		}
	}
	
	private static int betOrNot()
	{
	// - Prompt the user to confirm the decision to place a bet or not
	// - check if the bet is input in a valid amount
		int userBet = 0;
		int userAnswer = JOptionPane.showConfirmDialog(null, "Would you like to place a bet?");
		if(userAnswer == JOptionPane.CANCEL_OPTION)
			System.exit(0); // Quit if user clicks cancel
		else if (userAnswer == JOptionPane.YES_OPTION)
		{	
			// validate if user clicks "cancel" or enter an empty string
			String userBetString = JOptionPane.showInputDialog("Enter the bet amount (or enter '0' to play for fun without betting):");
			
			// regular expression to catch whitespace (empty string)
			Pattern p = Pattern.compile("\\s+");
			Matcher m = p.matcher(userBetString);
			boolean whiteSpaces = m.matches();
			
			// Handle invalid user inputs: space(s) , click cancel , press enter key without enter a value
			if(userBetString == null || userBetString.isEmpty() || whiteSpaces)  
				JOptionPane.showMessageDialog(null, "You did not place any bet , however, you can continue to play for fun!!");
			else
			{
				userBet = Integer.parseInt(userBetString);
				
				// Handle if user enters the negative or zero bet amount
				if(userBet <= 0)
				{
					JOptionPane.showMessageDialog(null, 
							"The Bet Amount has to be greater than 0 , however, you can continue to play for fun!!");
					userBet = 0;
				}
			}
		}
		return userBet;
	}
	
	public String toString() {
		return "RPSGuiGame is a GUI (Graphic User Interface) version of RPS.java";
	}



	public static void main(String args[]) {
		int userBet = betOrNot();
		RPSGuiGame frame = new RPSGuiGame(userBet);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}