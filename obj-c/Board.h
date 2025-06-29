//
//  Board.h
//  TicTacToe
//
//

#import <Foundation/Foundation.h>

NS_ASSUME_NONNULL_BEGIN

@interface Board : NSObject
@property (nonatomic, readonly) NSInteger size;

- (instancetype)initWithSize:(NSInteger)size;
- (BOOL)placeMark:(NSString *)mark atRow:(NSInteger)row col:(NSInteger)col;
- (BOOL)isFull;
- (NSString *)checkWinner;
- (void)printBoard;

@end

NS_ASSUME_NONNULL_END
