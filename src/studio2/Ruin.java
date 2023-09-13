package studio2;

import java.util.Scanner;

public class Ruin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		
		//Getting inputs
		System.out.println("How much money do you start with?");
		int startAmount = in.nextInt();
		System.out.println("What is the probability that you win a single play?");
		double winChance = in.nextDouble();
		System.out.println("How much money do you want to win for a successful day?");
		int winLimit = in.nextInt();
		System.out.println("How many simulations do you want to run?");
		int totalSimulations = in.nextInt();
		
		//In order to store the value of how much money was won/lost, we create int currentMoney
		
		boolean wonGame;
		
		//to calculate ruin rate
		int numRuinDays=0;
		
		//Simulate the correct number of days using totalSimulations
		for (int i = 1; i < totalSimulations + 1; i++)
		{
			int numGames = 1;
			int currentMoney = startAmount;
			//Simulate one day of games with a while loop
			while (currentMoney >0 && currentMoney < winLimit)
			{
				//Calculate if we won
				double randomNum= Math.random();
				if (randomNum < winChance)
				{
					wonGame=true;
				}
				else
				{
					wonGame=false;
				}

				numGames++;

				//Now we apply the correct "consequence"
				if (wonGame) {
					currentMoney++;
				}
				else
				{
					currentMoney--;
				}
			}

			if (currentMoney==winLimit) {
				System.out.println("Day " + i + ": " + "Number of plays: " + numGames + "  Your day ended in SUCCESS! Congratulations! You had a successful day!");
			}
			else
			{
				numRuinDays++;
				System.out.println("Day " + i + ": " + "Number of plays: " + numGames + "  Your day ended in RUIN");
			}
	}
		//calculating actual ruin rate
		double actualRuinRate = (numRuinDays/ (double) totalSimulations) *100;
		System.out.println("Actual Ruin rate: " + actualRuinRate + "%");
		
		//calculating expected ruin rate
		double a = (1-winChance)/winChance;
		double expectedRuinRate;
		if (winChance == 0.5) {
			expectedRuinRate= 1 - (startAmount/winLimit);
		}
		else
		{
			expectedRuinRate = ((Math.pow(a, startAmount))-(Math.pow(a,winLimit)))/ ( 1- Math.pow(a,winLimit));
		}
		
		System.out.println("Expected ruin rate:" + expectedRuinRate);
	}
}
