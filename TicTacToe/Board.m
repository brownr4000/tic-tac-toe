//
//  Board.m
//  TicTacToe
//
//

#import "Board.h"

@interface Board ()
@property (nonatomic, strong) NSMutableArray *grid;
@end

@implementation Board

// Init constructor for the board
- (instancetype)initWithSize:(NSInteger)size {
    self = [super init];
    
    if (self) {
        _size = size;
        
        // Create empty board
        self.grid = [NSMutableArray arrayWithCapacity:size];
        
        for (int i = 0; i < size; i++) {
            NSMutableArray *row = [NSMutableArray arrayWithCapacity:size];
            
            for (int j = 0; j < size; j++) {
                [row addObject:@" "];
            }
            [self.grid addObject:row];
        }
    }
    
    return self;
}

// Place mark
- (BOOL)placeMark:(NSString *)mark atRow:(NSInteger)row col:(NSInteger)col {
    // Validate input
    if (![@[@"X", @"O"] containsObject:mark]) {
        NSLog(@"Invalid mark: %@", mark);
        return NO;
    }
    
    // Check bounds
    if (row < 0 || row >= self.size || col < 0 || col >= self.size) {
        NSLog(@"Out of bounds: (%ld, %ld)", (long)row, (long)col);
        
        return NO;
    }
    
    // Check if empty
    NSString *current = self.grid[row][col];
    if (![current isEqualToString:@" "]) {
        NSLog(@"Box already taken at: (%ld, %ld)", (long)row, (long)col);
        return NO;
    }
    
    // Place valid mark
    self.grid[row][col] = mark;
    return YES;
}

// Check for full board
- (BOOL)isFull {
    for (int row = 0; row < self.size; row++) {
        for (int col = 0; col < self.size; col++) {
            if ([self.grid[row][col] isEqualToString:@" "]) {
                return NO;
            }
        }
    }
    
    return YES;
}

// Checks for winner
- (NSString *)checkWinner {
    NSArray *players = @[@"X", @"O"];
    int matchCount = 0;
    
    for (NSString *mark in players) {
        // Check rows
        for (int row = 0; row < self.size; row++) {
            matchCount = 0;
            
            for (int col = 0; col < self.size; col++) {
                if ([self.grid[row][col] isEqualToString:mark]) {
                    matchCount++;
                }
            }
            if (matchCount == self.size) return mark;
        }
        
        // Check cols
        for (int col = 0; col < self.size; col++) {
            matchCount = 0;
            
            for (int row = 0; row < self.size; row++) {
                if ([self.grid[row][col] isEqualToString:mark]) {
                    matchCount++;
                }
            }
            if (matchCount == self.size) return mark;
        }
        
        // Check diagonal left to right
        matchCount = 0;
        
        for (int i = 0; i < self.size; i++) {
            if ([self.grid[i][i] isEqualToString:mark]) {
                matchCount++;
            }
        }
        if (matchCount == self.size) return mark;
        
        // Check diagonal right to left
        matchCount = 0;
        
        for (int i = 0; i < self.size; i++) {
            if ([self.grid[i][self.size - 1 - i] isEqualToString:mark]) {
                matchCount++;
            }
        }
        if (matchCount == self.size) return mark;
        
    }
    
    // No winner
    return nil;
}

// Display board via console
- (void)printBoard {
    for (int row = 0; row < self.size; row ++) {
        NSMutableString *line = [NSMutableString string];
        
        for  (int col = 0; col < self.size; col++) {
            [line appendFormat:@" %@ ", self.grid[row][col]];
            if (col < self.size - 1) {
                [line appendString:@"|"];
            }
        }
        
        NSLog(@"%@", line);
        
        if (row < self.size - 1) {
            NSMutableString *separator = [NSMutableString string];
            for (int col = 0; col < self.size; col++) {
                [separator appendString:@"---"];
                if (col < self.size - 1) {
                    [separator appendString:@"+"];
                }
            }
            NSLog(@"%@", separator);
        }
    }
}

@end
