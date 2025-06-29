//
//  Game.m
//  TicTacToe
//
//

#import "Game.h"

@interface Game ()
@end

@implementation Game

/**
 Starts a game of Tic-Tac-Toe. Initalizes board, sets up player, and runs the
 main turn sequence.
 */
- (void)start {
    NSLog(@"Let's get this party started!");
    
    // Initalize board and gameOver check
    self.board = [[Board alloc] initWithSize:3];
    self.isGameOver = NO;
    self.currentPlayer = @"X";
    
    // Game loop
    while (!self.isGameOver) {
        [self processMove];
        [self.board printBoard];
        
        NSString *winner = [self.board checkWinner];
        
        if (winner) {
            NSLog(@"Player %@ wins!", winner);
            self.isGameOver = YES;
        } else if  ([self.board isFull]) {
            NSLog(@"It's a draw!");
            self.isGameOver = YES;
        } else {
            [self switchPlayer];
        }
    }
}

/**
 Switches current player between "X" and "O".
 */
- (void)switchPlayer {
    if ([self.currentPlayer isEqualToString:@"X"]) {
        self.currentPlayer = @"O";
    } else {
        self.currentPlayer = @"X";
    }
}

/**
 Processes move for the current player.
 Chooses a random empty cell and places the player's mark.
 */
- (void)processMove {
    NSInteger size = self.board.size;
    BOOL moveMade = NO;
    
    while (!moveMade) {
        NSInteger row = arc4random_uniform((uint32_t)size);
        NSInteger col = arc4random_uniform((uint32_t)size);
        
        moveMade = [self.board placeMark:self.currentPlayer atRow:row col:col];
        
        if (moveMade) {
            NSLog(@"Player %@ place mark at row %ld, col %ld",
                  self.currentPlayer, (long)row, (long)col);
        }
    }
}

/**
 Resets the game state to initial configuration.
 */
- (void)reset {
    self.board = [[Board alloc] initWithSize:3];
    self.isGameOver = NO;
    self.currentPlayer = @"X";
}

@end
