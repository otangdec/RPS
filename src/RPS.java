/*
 * Programmer:   Oranuch Tangdechavut
 * Course:       CS 111B - Fall 2013
 * Program Name: RPS.java 
 * Objective: To create a class for the game of Rock-Paper-Scissor. 
 *            RPS class contains methods to generate random move for the computer
 *            and to determine the winning outcome of the game.
 *            This class will be used by other 2 classes; RPSConsoleGame and RPSGuiGame
 */

import java.util.Random;

public class RPS {
	
    public final int ROCK = 1;
    public final int PAPER = 2;
    public final int SCISSORS = 3;
	
    private String userMove, pcMove, result, message, winner;
    private int pcWin, userWin, tie, betAmount, balance; 
    
    // constructor accepts amount as an argument
    // amount will be set to 0 unless the user input a valid bet amount
	public RPS(int amount)
	{
	    pcWin = 0;
	    userWin = 0;
	    tie = 0;
	    betAmount = amount;
	    balance = 0;
	    userMove = "";
	    pcMove = "";
	    result = "";
	    message = "";
	}
	
	// Method to generate random move for computer
	public int generateComputerPlay()
	{
		Random generator = new Random();
		int move = generator.nextInt(3) +1;
		
		if(move == 1) pcMove = "ROCK";
		else if (move == 2) pcMove = "PAPER";
		else if (move == 3) pcMove = "SCISSORS";
		
		return move;
	}
	
	// find the winner of the game and update the statistics as necessary 
	public String findWinner(int userMove, int pcMove)
	{
		String result = "";
		// Tie
		if( userMove == pcMove)
		{
			tie++;
			winner = "tie";
			result = "It\'s a tie!!";
		}
		// User wins
		else if ((userMove - pcMove + 3) % 3 == 1)
		{
			userWin++;
			winner = "user";
			result = "You Win!!";
			if(betAmount != 0) increaseBalance();
		}
		// PC Win
		else 
		{
			pcWin++;
			winner = "pc";
			result = "You Lose!!";
			if(betAmount != 0) decreaseBalance();
			
		}
		
		return result;		
	}
	
	public void setMessage(int userMove, int pcMove)
	{
		if((userMove == ROCK && pcMove == SCISSORS) || (userMove == SCISSORS && pcMove == ROCK))
			message = "Rock smashes Scissors!";
		else if((userMove == SCISSORS && pcMove == PAPER) || (userMove == PAPER && pcMove == SCISSORS))
			message = "Scissors shred Paper!";
		else if( (userMove == PAPER && pcMove == ROCK) || (userMove == ROCK && pcMove == PAPER))
			message = "Paper smothers Rock!";
		else
			message = "TIE....";
	}
	
	// setters
	public void setUserMove(String move)
	{
		this.userMove = move;
	}
	public void setResult(String result)
	{
		this.result = result;
	}
	private void increaseBalance()
	{
		this.balance += betAmount;
	}
	private void decreaseBalance()
	{
		this.balance -= betAmount;
	}
	
	// getters
	public String getUserMove()
	{
		return userMove;
	}
	public String getPcMove()
	{
		return pcMove;
	}
	public String getResult()
	{
		return result;
	}
	public int getBetAmount()
	{
		return betAmount;
	}
	public int getPcWin()
	{
		return pcWin;
	}
	public int getUserWin()
	{
		return userWin;
	}
	public int getTie()
	{
		return tie;
	}
	public int getBalance()
	{
		return balance;
	}
	public String getMessage()
	{
		return message;
	}
	public String getWinner()
	{
		return winner;
	}

	public String toString() {
		String str = "RPS: A Game of Rock Papaer Scissors\n" +
	                 "Current Number of Wins: PC = " + getPcWin() +
	                 " | User = " + getUserWin() + " | Tie = " + getTie();
		return str;
	}
	
}
