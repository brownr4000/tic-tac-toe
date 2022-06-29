//package tictactoe_game

import java.util.Scanner;

/**
 * Project: Tic-Tac-Toe Game
 * Class: MainGame
 *
 * This is a program that plays a TicTacToe game with two users.
 * The program utilizes the TicTacToe class to generate and display a grid,
 * and the users place their pieces on the board.
 * The TicTacToe class handles all checks and validations of the user moves
 * and win-conditions, while this program calls the related methods to play
 * the game as a demonstration. The game may also be repeated as many times
 * as the user would like.
 *
 * This program has been modified to allow the user to set the size of the
 * TicTacToe board prior to playing the game.
 *
 * @author Robert Brown brownr4000
 * @version 2.0
 */
public class MainGame
{
	/**
	 * The main method generates a new Scanner object, a new TicTacToe object,
	 * and calls other methods.
	 * A loop allows for the game to be played as many times as the user
	 * would like.
	 *
	 * @param args String array
	 */
	public static void main(String[] args)
	{
		char ans;	// Char to hold for return from repeat method

		// Create Scanner object
		Scanner keyboard = new Scanner(System.in);

		// Call welcome method
		welcome();

		// Int to hold size of board
		// Call method inputBoardSize to initialize gridSize
		// by passing in keyboard object
		int gridSize = inputBoardSize(keyboard);

		// Create TicTacToe object in order to allow TicTacToe
		// class to track player wins over multiple games
		TicTacToe game = new TicTacToe(gridSize);


		// do loop to repeat program per user choice
		do
		{
			// Call playGame method, passing in keyboard object and game object
			playGame(keyboard, game);

			// Clearing scanner input to resume String inputs
			keyboard.nextLine();

			// Assigns first character returned from repeat method
			ans = repeat(keyboard);

			// Returns user to a new game if 'y' or 'Y" is entered
		} while (ans == 'y' || ans == 'Y');

		// Close Scanner
		keyboard.close();

		// Call goodbye method
		goodbye();
	}

	/**
	 * The welcome method displays a greeting message to the user.
	 */
	public static void welcome()
	{
		System.out.println("Welcome to TicTacToe!");
	}

	/**
	 * The goodbye method displays a program exit message to the user.
	 */
	public static void goodbye()
	{
		System.out.println("\nGoodbye, and thanks for playing "
				+ "TicTacToe!\n");
	}

	/**
	 *  The repeat method prompts the user to decided if they would like
	 *  to repeat the program. A Scanner object is passed in, and the
	 *  user input character is returned.
	 *
	 * @param keyboard The Scanner object passed in.
	 * @return The first character of the user input String.
	 */
	public static char repeat(Scanner keyboard)
	{
		String input;			// String to hold keyboard input

		// Question to let user choose to repeat program inputs
		System.out.print("\nWould you like to play again (y/n)? ");

		// Scanner looks for whole line
		input = keyboard.nextLine();

		// Assigns first character from line input
		return input.charAt(0);
	}

	/**
	 * The playGame method plays a TicTacToe game with the users by calling
	 * the TicTacToe class methods. The game is continued until there is a
	 * winner, or the board is completely filled. If a winner is found or a
	 * tie occurs, the TicTacToe setScore method is called. The results are
	 * displayed on the screen, and the statistics for all the previous games
	 * (since the P1 program was initialized) are shown. The board is set to
	 * the starting empty condition and player order is reset in case the
	 * users decide to play again. A Scanner object and a TicTacToe object
	 * are passed in.
	 *
	 * @param keyboard The Scanner object passed in.
	 * @param game The TicTacToe object passed in.
	 */
	public static void playGame(Scanner keyboard, TicTacToe game)
	{
		char delta = 'D';	// Char to to be used for tie conditions

		// do loop to repeat player moves until end conditions are met
		do
		{
			// Call displayBoard method to show board to users
			game.displayBoard();

			// Call playerInput method to get current player move
			// by passing in keyboard (Scanner) object
			game.playerInput(keyboard);

			// Exits game when the board is full or a winner is found
		} while (!game.boardFull() && !game.checkWinner());

		// Check to determine winner
		if (game.checkWinner())
		{
			// Call setScore method by passing winning player
			game.setScore(game.getPlayer());

			// Display message to winning player
			System.out.println(game.getPlayer() + " is the winner!");
		}
		else
		{
			// Call setScore method by passing a character that is not X or O
			game.setScore(delta);

			// Display message indicating draw/tied game
			System.out.println("No winner. It was a draw!");
		}

		// Call displayStatistics method
		game.displayStatistics();

		// Call createBoard method of the same size as the initial board
		// This is to allow another game to be played
		game.createBoard(game.getBoardSize());

		// Call setPlayer method to reset to starting player (X)
		game.setPlayer();
	}

	/**
	 * The inputBoardSize method creates a prompt for the user to enter
	 * an odd number between 3 and 25 for the size of the TicTacToe board.
	 *
	 * @param keyboard The Scanner object passed in.
	 * @return The size of the TicTacToe board to be created.
	 */
	public static int inputBoardSize(Scanner keyboard)
	{
		final int MIN_SIZE = 3;		// Int constant for minimum board size
		final int MAX_SIZE = 25;	// Int constant for maximum board size
		int size;						// Int to hold user input number
		boolean check = false;		// Boolean to hold for valid input check

		// Display message to user
		System.out.println("\nYou can choose the size of the TicTacToe board!");

		// do loop to repeat user prompt until valid input is given
		do
		{
			System.out.print("\nEnter an odd number between " + MIN_SIZE
					+ " and " + MAX_SIZE + " \nfor the size of the board: ");
			size = keyboard.nextInt();

			// Check to determine if user input is out of bounds
			if (size < MIN_SIZE || size > MAX_SIZE)
			{
				System.out.println("The number is out of bounds. Please try"
										+ " again.");
			}
			// Check to determine if user input is an even number
			else if ((size % 2) == 0)
			{
				System.out.println("The number is even. Please try again.");
			}
			else
			{
				check = true;
			}

			// Returns to main when valid input is given
		} while (!check);

		// Return size of board
		return size;
	}
}
