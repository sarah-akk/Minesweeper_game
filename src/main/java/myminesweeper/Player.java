package myminesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import myminesweeper.GameAdmin;
import java.awt.Color;
import java.io.IOException;
import java.io.Serializable;
import java.util.Random;
import java.util.Scanner;


class Player implements Serializable{
    private String Name = "no name";
    private int Score = 0;
    private boolean winner = false ;
    private boolean auto = false;
    private Color color = Color.BLACK ;
    private String Mark;
    private boolean Order;//true if it is the firet // false if it is second
    
    public Player(){}//forSaveing data
    public Player(String Mark){this.Mark = Mark; }
    public Player(String Name , Color color ,boolean Order ,String Mark){
        this.Name = Name ;
        this.color = color ;
        this.Order = Order;
        this.Mark = Mark; 
    }
    public Player(boolean auto,String Mark)//mr robot
    {
        this.auto = true;
        Name="mr.robot";
        color = Color.RED;
        this.Mark = Mark; 
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int Score) {
        this.Score = Score;
    }

    public boolean isWinner() {
        return winner;
    }

    public void setWinner(boolean winner) {
        this.winner = winner;
    }

    public boolean isOrder() {
        return Order;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getMark() {
        return Mark;
    }

    public void setMark(String Mark) {
        this.Mark = Mark;
    }

    public boolean isAuto() {
        return auto;
    }

    public void setAuto(boolean auto) {
        this.auto = auto;
    }
    
    
    public void playConsole() throws IOException{
        int r=0,c=0;
        int flag;
        if(this.auto==false)
        {
        Scanner in = new Scanner(System.in);
        System.out.println(this.Name + " turn ");
        do {   
            System.out.println("Enter number of col ");
            c=in.nextInt();
            System.out.println("Enter number of row ");
            r=in.nextInt();
        } while (c<0 || c >= GameAdmin.Y || r <0 || r >= GameAdmin.X || GameAdmin.board[c][r].isCleared());
        //flags
        int tmp;
            System.out.println("to flage control enter 0 : to scan the cell enter another number  ");
            flag = in.nextInt();
            if(flag==0)
            {
                GameAdmin.board[c][r].setFlagged(!GameAdmin.board[c][r].isFlagged());//revers the flag
                GameAdmin.scoreCount(this, c, r);
                return;
            }
        }else{
            //الغباء الاصطناعي
            Random rand = new Random();
            boolean cc = false;
            boolean rr = false;
            while(!cc)//let's find c
            {
                c = rand.nextInt(GameAdmin.Y);
                for (int i = 0; i < 10; i++) {
                    if(!GameAdmin.board[c][i].isCleared())
                        cc=true;
                }
            }//now let's find r
            while(!rr)
            {
                r = rand.nextInt(GameAdmin.X);
                    if(!GameAdmin.board[c][r].isCleared())
                        rr=true;
            }
            System.out.println("i am in auto");
            
        }
        
        GameAdmin.scoreCount(this, c, r);
        GameAdmin.Floodfil(this, c, r);
        if(GameAdmin.leftCells==0)this.Score+=100;
    }
    
   
}