//package tictactoe_game

import java.util.Scanner;

/**
 * Project: Tic-Tac-Toe Game
 * Class: TicTacToe
 *
 * The TicTacToe class simulates a TicTacToe game with two users.
 * This class generates and displays the board, allows users to place
 * pieces by taking turns, and checks for winners. The TicTacToe class also
 * maintains and displays winning statistics while the class object is
 * instantiated for the players. The TicTacToe class contains all the methods
 * to play and score a TicTacToe game.
 *
 * No modifications needed to allow for the user to set the size of the
 * TicTacToe board.
 *
 *
 * @author Robert Brown brownr4000
 * @version 2.0
 */
public class TicTacToe
{
	private int boardSize;	// Int to hold size of the board
	private char[][] board;	// Char 2D array to hold the TicTacToe board
	private char player;		// Char to hold current player
	private final char SPACE = ' ';	// Char to hold blank spaces on board
	private final char XRAY = 'X';	// Char for 'X' player
	private final char OSCAR = 'O';	// Char for 'O' player
	private int row;			// Int to hold value for row of board
	private int col;			// Int to hold value for column of board
	private int turnNumber;	// Int to hold number of turns
	private int xrayWins;	// Int to hold number of 'X' player wins
	private int oscarWins;	// Int to hold number of 'O' player wins
	private int drawNum;		// Int to hold number of ties/draws

	/**
	 * The TicTacToe default no-input constructor.
	 * Creates a standard 3 x 3 TicTacToe board.
	 */
	public TicTacToe()
	{
		boardSize = 3;		// Set boardSize to 3
		xrayWins = 0;		// Initialize xrayWins to 0
		oscarWins = 0;		// Initialize oscarWins to 0
		drawNum = 0;		// Initialize drawNum to 0

		// Call createBoard method by passing in boardSize
		createBoard(boardSize);
	}

	/**
	 * The TicTacToe constructor initializes a TicTacToe object based on the
	 * passed in value size. It creates a TicTacToe board based on the size
	 * value passed in.
	 *
	 * @param size The passed in value for the board size
	 */
	public TicTacToe(int size)
	{
		boardSize = size;	// Set boardSize to size
		xrayWins = 0;		// Initialize xrayWins to 0
		oscarWins = 0;		// Initialize oscarWins to 0
		drawNum = 0;		// Initialize drawNum to 0

		// Call createBoard method by passing in boardSize
		createBoard(boardSize);
	}

	/**
	 * The getBoardSize method returns the size of the board.
	 *
	 * @return boardSize The value of the size of the board.
	 */
	public int getBoardSize()
	{
		return boardSize;
	}

	/**
	 * The displayStatistics method displays the number of wins for each
	 * player, and the number of tie games.
	 */
	public void displayStatistics()
	{
		// Displays message about the game statistics to the screen
		System.out.println("\nGame Statistics:");
		System.out.printf("%c has won %1d games.", XRAY, xrayWins);
		System.out.printf("\n%c has won %1d games.", OSCAR, oscarWins);
		System.out.printf("\nThere has been %1d tie games.", drawNum);
	}

	/**
	 * The createBoard method creates a new TicTacToe board by filling in
	 * blank characters in a 2D array of the size value passed in.
	 *
	 * @param size The size of the board
	 */
	public void createBoard(int size)
	{
		// Initialize 2D array board of dimensions size x size
		board = new char[size][size];

		// for loop to fill board array with blank spaces
		for (int i = 0; i < boardSize; i++)
		{
			for (int j = 0; j < boardSize; j++)
			{
				board[i][j] = SPACE;
			}
		}

		// Initialize turn number when board is created
		turnNumber = 0;

	}

	/**
	 * The displayBoard method outputs the current board to the screen.
	 */
	public void displayBoard()
	{
		// Blank lines for formatting
		System.out.println();
		System.out.print("  ");

		// for loop to display column headers
		for (int h = 0; h < boardSize; h++)
		{
			System.out.printf("%3d", h);
		}

		// for loop to display board and contents
		for (int i = 0; i < boardSize; i++)
		{
			// Displays row header
			System.out.printf("\n%3d", i);

			// for loop to display board array contents & vertical lines
			for (int j = 0; j < boardSize; j++)
			{
				System.out.printf("%2c|",board[i][j]);
			}
			System.out.print("\n   ");

			// for loop to display horizontal lines
			for (int k = 0; k < boardSize; k++)
			{
				System.out.print("---");
			}
		}
		System.out.println();
	}

	/**
	 * The setPlayer method determines the current player, either 'X' or 'O'.
	 */
	public void setPlayer()
	{
		// Check if current player is 'X'
		if (player == XRAY)
		{
			// Sets player to 'O'
			player = OSCAR;
		}
		else
		{
			// Sets player to 'X'
			player = XRAY;
		}
	}

	/**
	 * The getPlayer method returns the value of the current player.
	 */
	public char getPlayer() { return player; }

	/**
	 * The setScore method increments the win counters for the players, or
	 * the counter for the number of draw/tie games.
	 *
	 * @param winner The character value of the winning player.
	 */
	public void setScore(char winner)
	{
		// Check if winner is 'X'
		if (winner == XRAY)
		{
			xrayWins++;
		}
		// Check if winner is '0'
		else if (winner == OSCAR)
		{
			oscarWins++;
		}
		// The game must be a draw/tie
		else
		{
			drawNum++;
		}
	}

	/**
	 * The playerInput method takes in player input for the values of the row
	 * and column where they would like to place their piece on the TicTacToe
	 * board.
	 *
	 * @param keyboard The passed in Scanner keyboard object.
	 */
	public void playerInput(Scanner keyboard)
	{
		// Calling setPlayer method to change players
		setPlayer();

		// Display message indicating current player
		System.out.println(player + ", it is your turn.");

		// do loop to repeat input until a valid placement is made
		do
		{
			// Ask user for row
			System.out.print("Which row? ");
			row = keyboard.nextInt();

			// Ask user for column
			System.out.print("Which column? ");
			col = keyboard.nextInt();

			// Increment turnNumber
			turnNumber++;

			// Call placePiece method to verify input
		} while (!placePiece(row, col));
	}

	/**
	 * The placePiece method checks to determine if a player's indicated
	 * piece placement is valid. It checks if the space is already filled by
	 * a piece, or if the location is out of bounds for the current board.
	 * The row and column values from the user are passed in.
	 *
	 * @param row The value of the row on the board for the user's placement
	 * @param col The value of the column on the board for the user's placement
	 * @return The boolean value if the player's move was valid
	 */
	public boolean placePiece(int row, int col)
	{
		// Check to validate placement is within the bounds of the board array
		if ((row < boardSize) && (col < boardSize))
		{
			// Check to validate placement on blank space
			if (board[row][col] == SPACE)
			{
				// Set location on board to current player character
				board[row][col] = player;

				return true;
			}
			// Placement at location is already filled
			else
			{
				// Display bad location message
				System.out.println("Bad location, try again!");
			}
		}
		// Outside of board array size
		else
		{
			// Display out of bounds message to user
			System.out.println("Out of bounds");
		}
		return false;
	}

	/**
	 * The boardFull method checks to determine if the board has been filled
	 * by player pieces.
	 *
	 * @return The boolean value whether the number of turns has equaled the
	 * 			number of spaces on the board
	 */
	public boolean boardFull()
	{
		return turnNumber == (boardSize * boardSize);
	}

	/**
	 * The checkWinner method determines if the current player has
	 * covered an entire row, column or diagonal with their pieces.
	 *
	 * @return The boolean value if a winning condition has been met by the
	 * 			current player.
	 */
	public boolean checkWinner()
	{
		int countCol = 0;			// Int to count of pieces in column
		int countRow = 0;			// Int to count of pieces in row
		int countDiag = 0;		// Int to count of pieces in LR diagonal
		int countAntiDiag = 0;	// Int to count of pieces in RL diagonal

		// for loop to check for filled column
		for (int i = 0; i < boardSize; i++)
		{
			// Check if space contains player piece
			if (board[i][col] == player)
			{
				// Increment count for columns
				countCol++;
			}
			// Check if column count equal to board size
			if (countCol == boardSize)
			{
				return true;
			}
		}

		// for loop to check for filled row
		for (int i = 0; i < boardSize; i++)
		{
			// Check if space contains player piece
			if (board[row][i] == player)
			{
				// Increment count for rows
				countRow++;
			}
			// Check if row count equal to board size
			if (countRow == boardSize)
			{
				return true;
			}
		}

		// Check to determine if piece is placed on the left to right diagonal
		if (row == col)
		{
			// for loop to check for filled left to right diagonal
			for (int i = 0; i < boardSize; i++)
			{
				// Check if space contains player piece
				if (board[i][i] == player)
				{
					// Increment count for left to right diagonal
					countDiag++;
				}
				// Check if LR diagonal count equal to board size
				if (countDiag == boardSize)
				{
					return true;
				}
			}
		}

		// Check to determine if piece is placed on the right to left diagonal
		if (row + col == boardSize - 1)
		{
			// for loop to check for filled right to left diagonal
			for (int i = 0; i < boardSize; i++)
			{
				// Check if space contains player piece
				if (board[i][(boardSize - 1) - i] == player)
				{
					// Increment count for right to left diagonal
					countAntiDiag++;
				}
				// Check if RL diagonal count equal to board size
				if (countAntiDiag == boardSize)
				{
					return true;
				}
			}
		}
		return false;
	}
}
