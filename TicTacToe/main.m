//
//  main.m
//  TicTacToe
//
//

#import <Foundation/Foundation.h>
#import "Game.h"

int main(int argc, const char * argv[]) {
    @autoreleasepool {
        // insert code here...
        Game *game = [[Game alloc] init];
        [game start];
    }
    return 0;
}
