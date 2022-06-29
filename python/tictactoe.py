# Tic-Tac-Toe
import random

def drawBoard(board):
    # This function prints out the board that it was passed.
    # "board" is a list of 10 strings representing the board ~ ignoring idx 0
    print(board[7] + "|" + board[8] + "|" + board[9])
    print("-+-+-")
    print(board[4] + "|" + board[5] + "|" + board[6])
    print("-+-+-")
    print(board[1] + "|" + board[2] + "|" + board[3])

def inputPlayerLetter():
    # Let's the player type which letter they want to be.
    # Returns a list with the player's letter as the first item and the
    # computer's letter as the second.
    letter = ""
    while not (letter == "X" or letter == "O"):
        letter = input("Do you want to be X or O? ").upper()

    # The first element in the list is the player's letter;
    # The second is the computer's letter.
    if letter == "X":
        return ["X", "O"]
    else:
        return ["O", "X"]

def whoGoesFirst():
    # Flips a coin to choose which player goes first.
    if random.randint(0,1) == 0:
        return "computer"
    else:
        return "player"

def makeMove(board, letter, move):
    board[move] = letter

def isWinner(board, letter):
    # Given a board and a player's letter, this function returns True if
    # that player has won.
    return ((board[7] == letter and board[8] == letter and board[9] == letter) 
        or (board[4] == letter and board[5] == letter and board[6] == letter)
        or (board[1] == letter and board[2] == letter and board[3] == letter)
        or (board[7] == letter and board[4] == letter and board[1] == letter)
        or (board[8] == letter and board[5] == letter and board[2] == letter)
        or (board[9] == letter and board[6] == letter and board[3] == letter)
        or (board[4] == letter and board[5] == letter and board[6] == letter)
        or (board[7] == letter and board[5] == letter and board[3] == letter)
        or (board[9] == letter and board[5] == letter and board[1] == letter))

def getBoardCopy(board):
    # Make a copy of the board list and return it.
    boardCopy = []
    for i in board:
        boardCopy.append(i)
    return boardCopy

def isSpaceFree(board, move):
    # Return True if the passed move is free on the passed board.
    return board[move] == " "

def getPlayerMove(board):
    # Let the player enter their move.
    allowedMoves = "1 2 3 4 5 6 7 8 9"
    move = " "
    while move not in allowedMoves.split() or not isSpaceFree(board, int(move)):
        move = input("what is your next move? (1-9) ")
    return int(move)

def chooseRandomMoveFromList(board, movesList):
    # Returns a valud move from the passed list on the passed board.
    # Returns None if there is no valid move.
    possibleMoves = []
    for i in movesList:
        if isSpaceFree(board, i):
            possibleMoves.append(i)
    
    if len(possibleMoves) != 0:
        return random.choice(possibleMoves)
    else:
        return None

def getComputerMove(board, computerLetter):
    # Given a board and the computer's letter, determine where to move and
    # return that move.
    if computerLetter == "X":
        playerLetter = "O"
    else:
        playerLetter = "X"
    
    # Tic-Tac-Toe AI algorithm:
    # First, check if we can win in the next move.
    for i in range(1, 10):
        boardCopy = getBoardCopy(board)
        if isSpaceFree(boardCopy, i):
            makeMove(boardCopy, computerLetter, i)
            if isWinner(boardCopy, computerLetter):
                return i
    
    # Check if the player could win on their next move and block them.
    for i in range(1, 10):
        boardCopy = getBoardCopy(board)
        if isSpaceFree(boardCopy, i):
            makeMove(boardCopy, playerLetter, i)
            if isWinner(boardCopy, playerLetter):
                return i

    # Try to take one of the conrers, if they are free.
    move =  chooseRandomMoveFromList(board, [1, 3, 7, 9])
    if move != None:
        return move
    
    # Try to take the center, if it is free.
    if isSpaceFree(board, 5):
        return 5
    
    # Move on one of the sides.
    return chooseRandomMoveFromList(board, [2, 4, 6, 8])

def isBoardFull(board):
    # Return True if every space on the board has been taken.
    # Otherwise, return False.
    for i in range(1, 10):
        if isSpaceFree(board, i):
            return False
    return True

print("---TIC-TAC-TOE: The Movie: The Game---")

while True:
    # Reset the board.
    theBoard = [" "] * 10
    playerLetter, computerLetter = inputPlayerLetter()
    turn = whoGoesFirst()
    print("The " + turn + " will go first.")
    gameIsPlaying = True

    while gameIsPlaying:
        if turn == "player":
            # Player's Turn
            drawBoard(theBoard)
            move = getPlayerMove(theBoard)
            makeMove(theBoard, playerLetter, move)

            if isWinner(theBoard, playerLetter):
                drawBoard(theBoard)
                print("You have won the game!")
                gameIsPlaying = False
            else:
                if isBoardFull(theBoard):
                    drawBoard(theBoard)
                    print("Tie game!")
                    break
                else:
                    turn = "computer"
        
        else:
            # Computer's turn
            move = getComputerMove(theBoard, computerLetter)
            makeMove(theBoard, computerLetter, move)

            if isWinner(theBoard, computerLetter):
                drawBoard(theBoard)
                print("The computer has beaten you! You lose.")
                gameIsPlaying = False
            else:
                if isBoardFull(theBoard):
                    drawBoard(theBoard)
                    print("Tie game!")
                    break
                else:
                    turn = "player"

    if not input("Do you want to play again?"
                + " (yes or no) ").lower().startswith('y'):
        break 