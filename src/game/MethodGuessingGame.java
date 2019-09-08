//Guessing Game Methods Assignment
//Chris Sun
//Guessing Game broken into methods

package game;
import java.util.Scanner;
import java.security.SecureRandom;

public class MethodGuessingGame {
	private int randomNumber=0;
	private int playerNum;
	private int computerNum;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int answer;
		Scanner keyboard = new Scanner(System.in);
		MethodGuessingGame newGame = new MethodGuessingGame();
		newGame.verifyOneDie();//calls the method that verifies secureRandom is working with one die
		newGame.verifyTwoDice();//calls the method that verifies secureRandom is working with two dice
		newGame.instructions();//calls the method that prints the instructions for the game
		System.out.println("Would you like to play the game with 1 or 2 dice. Or enter 3 if you would like for you and the computer to each roll a dice."+"\n"
			+ "Please enter '1', '2', or '3' to choose."); //asks the user which dice game they want to play
		do {//error inputing loop to start the choice dice game of the user
			answer = keyboard.nextInt();
		switch (answer) { //switch that triggers which dice game to start
		case 1: newGame.guessingGameOneDie();
		break;
		case 2: newGame.guessingGameTwoDice();
		break;
		case 3: newGame.bothRollGame();
		break;
		}
		System.out.println("Please enter a correct choice of '1', '2', or '3'.");
		}while(answer != 1 && answer !=2 && answer!= 3);
	}//end of public static void main
	
	public MethodGuessingGame() {
		
	}//end of constructor MethodGuessingGame
	
	private int randomGenerator() {//method for generating the roll of the dice
		SecureRandom rNum = new SecureRandom();
		randomNumber = 1 + rNum.nextInt(6);
		return randomNumber;
	}
	
	private int randomGenerator2() {//method for generating the roll of two dice
		SecureRandom rNum = new SecureRandom();
		randomNumber = 2 + rNum.nextInt(12);
		return randomNumber;
	}
	
	private int playerRoll() {//method that rolls the players dice
		SecureRandom rNum = new SecureRandom();
		playerNum = 1 + rNum.nextInt(6);
		return playerNum;
	}
	
	private int computerRoll() {//method that rolls the computers dice
		SecureRandom rNum = new SecureRandom();
		computerNum = 1 + rNum.nextInt(6);
		return computerNum;
	}
	
	
	
	private void verifyOneDie() {//method that verifies secure random rolling one die
		SecureRandom rNum = new SecureRandom();
		int[] frequency = new int[7];
		
		for (int roll=1; roll <=100_000; roll++) {//rolls a dice 100000 times into an array
			++frequency[1 + rNum.nextInt(6)];
		}
		
		System.out.printf("%s%10s%n", "Face", "Frequency");
		
		for(int face = 1; face < frequency.length; face++) {
			double percent=frequency[face]*.001;//math that changes the frequency of each face rolled into a percent
			System.out.printf("%4d%10.2f%n",  face, percent);
		}
	}
	
	private void verifyTwoDice() {//method that verifies secure random rolling two dice
		SecureRandom ranNum = new SecureRandom();
		int[] frequencyTwo = new int[13];
		
		
		for (int roll=1; roll <=100_000; roll++) {//rolls a dice 100000 times into an array
			++frequencyTwo[2 + ranNum.nextInt(11)];
		}
		
		System.out.printf("%s%10s%n", "Face", "Frequency");

		for(int face = 2; face < frequencyTwo.length; face++) {
			double percent=frequencyTwo[face]*.001;//math that changes the frequency of each face rolled into a percent
			System.out.printf("%4d%10.2f%n",  face, percent);
		}
	}
	
	private void instructions() {//method that has all the instructions for the game
		System.out.println("Welcome to the Guessing Game"+"\n"
			+"The goal of this game is to guess the correct dice roll."+"\n"
			+"You will be faced against the computer and you both will start with 12 tokens."+"\n"
			+"For each correct guess you will gain a token from the computer."+"\n"
			+ "If incorrect, you will lose a token to the computer."+"\n"
			+ "The game will end once either you or the computer reaches 0 tokens."+"\n"
			+ "Or you can quit the game at anytime by pressing 0 and the game will see who has the most tokens."+"\n"
			+"A winner will be decided based on who has the most."+"\n");
	}
	
	private void guessingGameOneDie() {//the first guessing game with one dice
		int pToken=12;
		int cToken=12;
		
		SecureRandom rNum = new SecureRandom();
		
		//check for the user or computer both have more then 0 tokens
				while (pToken > 0 && cToken > 0) {//loop that plays the game
					Scanner keyboard = new Scanner(System.in);
					System.out.print("Enter a guess: 1-6 (or type '0' to exit the game): ");
					int guess = keyboard.nextInt();
					
					if (guess==0) {//lets the player quit the game if they type quit and outputs a message saying who has more tokens and thanks for playing
						if (pToken==cToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("The game is a tie.");
						}
						if (pToken > cToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("You finished with more tokens then Computer");}
						else if (cToken > pToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("Computer finished with more tokens then you");}
						System.out.println("Thanks for playing!");
						System.exit(0);
					}
						
					int face=randomGenerator();//calls the method that rolls one die
					System.out.println("The result is: "+face+"\n");
					
					if (face == guess) { //if player choice was correct add token to player and subtract from computer
						System.out.println("You guessed correct! One token will be given to you from computer");
						cToken++;
						pToken--;
						System.out.printf("Current token count: Player: %d\tComputer: %d\n", pToken, cToken);
					}
					if (face !=guess) { //if the players choice was wrong out put a line and subtract token from player and add token to computer
						System.out.println("Your guess was incorrect! You will lose one token to the computer.");
						cToken++;
						pToken--;
						System.out.printf("Current token count: Player: %d\tComputer: %d\n", pToken, cToken);
					}
				}//end while loop
				
				if (pToken==0) {//declares the player has lost when he has 0 tokens
					System.out.println("You have lost! Better luck next time.");
					System.exit(0);
				}
				else if(pToken==24) {//declares the player a winner when he has all the tokens
					System.out.println("You have won! Congratulations!");
					System.exit(0);
				}
		
	}//end guessingGameOneDie
	
	private void guessingGameTwoDice() {//method for the second dice game using two dice
		int pToken=12;
		int cToken=12;
		
		SecureRandom rNum = new SecureRandom();
		
		//check for the user or computer both have more then 0 tokens
				while (pToken > 0 && cToken > 0) {//loop that plays the game
					Scanner keyboard = new Scanner(System.in);
					System.out.print("Enter a guess: 2-12 (or type '0' to exit the game): ");
					int guess = keyboard.nextInt();
					
					if (guess==0) {//lets the player quit the game if they type quit and outputs a message saying who has more tokens and thanks for playing
						if (pToken==cToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("The game is a tie.");
						}
						if (pToken > cToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("You finished with more tokens then Computer");}
						else if (cToken > pToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("Computer finished with more tokens then you");}
						System.out.println("Thanks for playing!");
						System.exit(0);
					}
						
					int face=randomGenerator2();//calls the method that rolls 2 dice
					System.out.println("The result is: "+face+"\n");
					
					if (face == guess) { //if player choice was correct add token to player and subtract from computer
						System.out.println("You guessed correct! One token will be given to you from computer");
						cToken++;
						pToken--;
						System.out.printf("Current token count: Player: %d\tComputer: %d\n", pToken, cToken);
					}
					if (face !=guess) { //if the players choice was wrong out put a line and subtract token from player and add token to computer
						System.out.println("Your guess was incorrect! You will lose one token to the computer.");
						cToken++;
						pToken--;
						System.out.printf("Current token count: Player: %d\tComputer: %d\n", pToken, cToken);
					}
				}//end while loop
				
				if (pToken==0) {//declares the player has lost when he has 0 tokens
					System.out.println("You have lost! Better luck next time.");
					System.exit(0);
				}
				else if(pToken==24) {//declares the player a winner when he has all the tokens
					System.out.println("You have won! Congratulations!");
					System.exit(0);
				}
				
		}//end guessingGameTwo Dice
	
	private void bothRollGame() { //method for 3rd dice game where computer and player each roll a die
		int pToken=12;
		int cToken=12;
		
		SecureRandom rNum = new SecureRandom();
		
		//check for the user or computer both have more then 0 tokens
				while (pToken > 0 && cToken > 0) {//loop that plays the game
					Scanner keyboard = new Scanner(System.in);
					System.out.print("Enter a guess: 2-12 (or type '0' to exit the game): ");
					int guess = keyboard.nextInt();
	
					if (guess==0) {//lets the player quit the game if they type quit and outputs a message saying who has more tokens and thanks for playing
						if (pToken==cToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("The game is a tie.");
						}
						if (pToken > cToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("You finished with more tokens then Computer");}
						else if (cToken > pToken) {
							System.out.printf("Token count: Player: %d\tComputer: %d\n", pToken, cToken);
							System.out.println("Computer finished with more tokens then you");}
						System.out.println("Thanks for playing!");
						System.exit(0);
					}
					int roll1 = playerRoll();//method where player rolls a dice
					int roll2 = computerRoll();//method where computer rolls a dice
					int sum=roll1+roll2;//adds the 2 rolls together for the sum
					System.out.printf("Player rolled: %d\tComputer rolled: %d\n", roll1, roll2);
					System.out.println("The result is: "+sum+"\n");
					
					if (sum == guess) { //if player choice was correct add token to player and subtract from computer
						System.out.println("You guessed correct! One token will be given to you from computer");
						cToken++;
						pToken--;
						System.out.printf("Current token count: Player: %d\tComputer: %d\n", pToken, cToken);
					}
					if (sum !=guess) { //if the players choice was wrong out put a line and subtract token from player and add token to computer
						System.out.println("Your guess was incorrect! You will lose one token to the computer.");
						cToken++;
						pToken--;
						System.out.printf("Current token count: Player: %d\tComputer: %d\n", pToken, cToken);
					
					}//end while loop
					
					if (pToken==0) {//declares the player has lost when he has 0 tokens
						System.out.println("You have lost! Better luck next time.");
						System.exit(0);
					}
					else if(pToken==24) {//declares the player a winner when he has all the tokens
						System.out.println("You have won! Congratulations!");
						System.exit(0);
					}
				
				}//end bothRollGame
				
	}
	
}//end of class