package myminesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import myminesweeper.DataSet;
import myminesweeper.Cell;
import java.io.IOException;
import java.util.Scanner;

public class GameAdmin {
    public static GameMaker grid;
    public static Cell[][] board;
    public static  Player[] player;
    public static DataSet data = null;
    public static int playerNumber=1;
    //set up
    public static final int X = 10 ;
    public static final int Y = 10;
    
    //game attributes
    public static int BOMB_COUNT = 10 ;
    public static int flags = 10;
    public static int leftCells = (X*Y)-BOMB_COUNT;

    static void setBoard(Cell[][] b) {
        board = b;
    }
    //
    public GameAdmin(int firstCellIndex_i,int firstCellIndex_j) throws IOException{
        flags = BOMB_COUNT;
        grid = new GameMaker(firstCellIndex_i,firstCellIndex_j);
        board = grid.getBoard() ;
        leftCells=(X*Y)-BOMB_COUNT;
        initPlayers(playerNumber);  
    }
    //
    public  static void initPlayers(int playerNumber){        
        //set the players
        player = new Player[2];
        if(data == null)
        data = new DataSet();
        if(playerNumber==1)
        {// player VS robot
        player[0] = new Player("*");
        
        player[1] = new Player(true,"%");
        } else             
        {// player VS player
        player[0] = new Player("*");
        
        player[1] = new Player("^");
        }
    }
    public  static void Floodfil(Player player , int col , int row  ) throws IOException{
         
        if( board[col][row].isCleared() )return; 
        board[col][row].setCleared(true);
        board[col][row].setFlagged(false);
        --leftCells;
        board[col][row].setMark(player.getMark());
        if(board[col][row].getBombNearby()==0)
        {
            //go left
            if(row>=1)Floodfil(player , col, row-1);
            //go right
            if(row<X-1)Floodfil(player , col, row+1);
            //go up
            if(col>=1)Floodfil(player , col-1, row);
            //go down
            if(col<Y-1)Floodfil(player , col+1, row);
        }
        return; 
    }
    public  static void scoreCount(Player player ,int c , int r){
        if(board[c][r].isCleared())return;
        if(board[c][r].isFlagged())
        {
            if(board[c][r].hasBomb())
            {
                player.setScore(player.getScore()+5);
            }else{
                player.setScore(player.getScore()-1);
            }
        }else{
            if(board[c][r].hasBomb())
            {
                player.setScore(player.getScore()-250);
            }else{
                if(board[c][r].hasBomb()){
                player.setScore(player.getScore()-5);//un do a good flag   
                }else{
                    player.setScore(player.getScore()+ board[c][r].getBombNearby());
                    if(board[c][r].getBombNearby()==0)
                    {
                        player.setScore(player.getScore()+1);
                    }
                }
            }
        }
    }
    public  static void PrintGrid(){
        System.out.println(player[0].getName() +" score is :" + player[0].getScore()  );
        System.out.println(player[1].getName() +" score is :" + player[1].getScore()  );
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {
               if(board[i][j].isFlagged()){
                      System.out.print("?"+board[i][j].getMark()+"    ");   
               }else if(board[i][j].isCleared())
                {
                  if(board[i][j].hasBomb())
                  {
                    System.out.print("#   ");
                  } 
                  else{
                    System.out.print(""+board[i][j].getBombNearby()+ board[i][j].getMark() + "    ");
                  }
                }else{
                    System.out.print("" + i + j +  "  ");
                    if(i<10)System.out.print(" ");
                    if(j<10)System.out.print(" ");
                    }
            }System.out.println("");          
        }
     }
    public  static void PrintFinalGrid(){
     System.out.println(player[0].getName() +" score is :" + player[0].getScore()  );
     System.out.println(player[1].getName() +" score is :" + player[1].getScore()  );
        for (int i = 0; i < Y; i++) {
            for (int j = 0; j < X; j++) {  
               
                  if(board[i][j].hasBomb())
                  {
                    System.out.print("#     ");
                  } 
                  else{
                    System.out.print(""+board[i][j].getBombNearby()+"     ");
                  }
                }System.out.println("");
            }          
        
     }
    public  static void ConsoleVsPlayer() throws IOException{
         initPlayers(2);
         
         System.out.println("enter your names pleas ");
         String name = new Scanner(System.in).nextLine();
         player[0].setName(name);
                name = new Scanner(System.in).nextLine();
         player[1].setName(name);
         
         startConsole();
     }
    public  static void ConsoleVSauto() throws IOException{
         initPlayers(1);
         System.out.println("enter your name pleas ");
         String name = new Scanner(System.in).nextLine();
         player[0].setName(name);
         startConsole();
     }
    private static void startConsole() throws IOException{
        Scanner in = new Scanner(System.in);
        int c,r;
        boolean swtch = true;
       do{
                    System.out.println("enter colm 0.." + (Y-1));
                    c=in.nextInt();
                    System.out.println("enter row 0.." + (X-1));
                    r=in.nextInt();
                    }while(c<0 || c >= Y || r <0 || r >= X );
        
        new GameAdmin(c,r);//init the game board
        scoreCount(player[0], c, r);
        Floodfil(player[0], c, r);
 
        if(leftCells==0)player[0].setScore(100);//win from the first move
        
        data.save(player);
        
        while (leftCells>0){
           swtch=!swtch;
           if(swtch)
           {
               player[0].playConsole();
               data.save(player);
           }
           else
           {
               player[1].playConsole();
               data.save(player);
           }
           PrintGrid();     
         }
         PrintFinalGrid();
     }

    public  Cell[][] getBoard() {
        return board;
    }
    

}
