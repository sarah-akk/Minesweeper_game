package myminesweeper;


import myminesweeper.GameAdmin;
import myminesweeper.Cell;
import java.io.IOException;
import java.util.Random;

public class GameMaker {
       
    private final Cell[][] board ;

    public GameMaker(int firstCellIndex_i , int firstCellIndex_j) throws IOException
    {
        board = new Cell[GameAdmin.Y][GameAdmin.X];
        int bombCount = GameAdmin.BOMB_COUNT;
        Random rand = new Random();
        //create grid
        for (int i = 0; i < GameAdmin.Y; i++) {
            for(int j = 0; j < GameAdmin.X; j++){
                board[i][j] = new Cell();
            }
        }
        //set random bombs
        while(bombCount>0)
        {
            int i = rand.nextInt(GameAdmin.Y);
            int j = rand.nextInt(GameAdmin.X);
            if( ! board[i][j].hasBomb() && !(i==firstCellIndex_i && j==firstCellIndex_j))
            {
                board[i][j].setBomb(true);
                bombCount--;
            }
        }
        //set bombearby
        for(int i = 0 ; i<GameAdmin.Y ; i++)
        {
            for (int j = 0; j < GameAdmin.X; j++) {
                if(!board[i][j].hasBomb())
                {
                    int count=0;
                    
                    if(i>0 && board[i-1][j].hasBomb())++count;
                    if(i<GameAdmin.Y-1 && board[i+1][j].hasBomb())++count;
                    if(j>0 && board[i][j-1].hasBomb())++count;
                    if(j<GameAdmin.X-1 && board[i][j+1].hasBomb())++count;
                    if(i>0 && j>0 && board[i-1][j-1].hasBomb())++count;
                    if(i<GameAdmin.Y-1 && j<GameAdmin.X-1 && board[i+1][j+1].hasBomb())++count;
                    if(i>0 && j<GameAdmin.X-1 && board[i-1][j+1].hasBomb())++count;
                    if(i<GameAdmin.Y-1 && j>0  && board[i+1][j-1].hasBomb())++count;
                    
                    board[i][j].setBombNearby(count);
                }
                            
            }
        } 
        for(int i = 0 ; i<GameAdmin.Y ; i++)
        {
            for (int j = 0; j < GameAdmin.X; j++) {
                //set thr icons
            }
        }
        
        
   }

    public Cell[][] getBoard() {
        return board;
    } 
   
}
