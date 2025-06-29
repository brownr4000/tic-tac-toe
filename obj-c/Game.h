//
//  Game.h
//  TicTacToe
//
//

#import <Foundation/Foundation.h>
#import "Board.h"

NS_ASSUME_NONNULL_BEGIN

@interface Game : NSObject
@property (nonatomic, strong) Board *board;
@property (nonatomic, assign) BOOL isGameOver;
@property (nonatomic, strong) NSString *currentPlayer; // @"X" or @"O"

- (void)start;
- (void)switchPlayer;
- (void)processMove;
- (void)reset;

@end

NS_ASSUME_NONNULL_END
