/*
 * Programmer:   Oranuch Tangdechavut
 * Course:       CS 111B - Fall 2013
 * Program Name: RPSConsoleGame.java 
 * Objective: Using RPS class to create a text-base version of the RPS game. 
 *            Users will have option to place a bet or just play the game
 */

import java.util.*;

public class RPSConsoleGame {

	private static RPS objRPS;
	private static String userInput;
	private static int userMove, pcMove;
	
	private static int getUserBet()
	{
	// prompt asking whether users want to place a bet
		System.out.println("Enter the bet amount (or enter '0' to play for fun without betting)");
		Scanner scan = new Scanner(System.in);
		int amount = 0;
		try{
		amount = scan.nextInt();
		} catch(Exception e){
			System.err.println("Error: Only integer is acceptable");
			System.err.println("You did not enter the correct bet amount " +
					           "but You can continue to play for fun....");
		}

		return amount;
	}
	private static String getUserInput() 
	{
		System.out.println("\nTo play, enter:\n" 
				 + "\'r\' to play ROCK \n"
				 + "\'p\' to play PAPER \n" 
				 + "\'s\' to play SCISSORS \n"
				 + "or any other character to quit.");
		Scanner scan = new Scanner(System.in);
		return scan.nextLine();
	}
	private static boolean isPlaying(String userInput)
	{
	// check if users input a correct character in order to continue playing
		return  userInput.equalsIgnoreCase("r") || 
			    userInput.equalsIgnoreCase("p") ||
			    userInput.equalsIgnoreCase("s");
	}
	private static void play(String userInput)
	{
	// Get user input and assign to the userMove property of RPS object
		if(userInput.equalsIgnoreCase("r"))
		{
			userMove = objRPS.ROCK;
			objRPS.setUserMove("ROCK");
		}
		else if(userInput.equalsIgnoreCase("p"))
		{
			userMove = objRPS.PAPER;
			objRPS.setUserMove("PAPER");
		}
		else
		{
			userMove = objRPS.SCISSORS;
			objRPS.setUserMove("SCISSORS");
		}
					
		pcMove = objRPS.generateComputerPlay();						
		objRPS.setResult(objRPS.findWinner(userMove, pcMove));
		
	}
	private static void displayResult()
	{
		// Display Result of the game; win, lose, tie, (balance)
		System.out.println("\nYou played: " + objRPS.getUserMove());
		System.out.println("The Computer played: " + objRPS.getPcMove());
		System.out.println(objRPS.getResult() + "\n");
		
		System.out.println("Ties: " + objRPS.getTie() + 
				           "  Wins: " + objRPS.getUserWin() + 
				           "  Losses: " + objRPS.getPcWin());
		
		if(objRPS.getBetAmount() != 0) 
			System.out.println("Your balance is $" + objRPS.getBalance());
	}
	
	public String toString() {
		return "RPSConsoleGame is a Console version of RPS.java";
	}
	
	public static void main(String[] args){
				
		System.out.println("Welcome to Rock, Paper, Scissors!");
		int userBet = getUserBet();
		
		objRPS = new RPS(userBet);

		do {
			userInput = getUserInput();
			
			if(!isPlaying(userInput))
			{
				System.out.println("\nThank you for playing RPS Console Game!!");
				break;
			}
			else
			{
				play(userInput);
			}
				
			displayResult();
		} while (true);
	}

}
