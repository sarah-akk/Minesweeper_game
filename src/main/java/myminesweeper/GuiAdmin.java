package myminesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import myminesweeper.Grid;
import myminesweeper.GameMaker;
import myminesweeper.GameAdmin;
import myminesweeper.DataSet;
import myminesweeper.Cell;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class GuiAdmin {
    public static int Turn = 0 ;
    //
    public static GameMaker grid;
    public static Cell[][] board;
    public static Player[] player;
    
    
                                

    static void play(int k, int l) throws IOException, InterruptedException {
      
       if(GameAdmin.leftCells<=0)
       {
           Win();
           //System.exit(0);
       }
       if(Turn==0)
           {
               board = new GameAdmin(k,l).getBoard();
               initPlayer(GameAdmin.playerNumber);
               System.out.println(k+" "+l);
           } 
           
           scoreCount(player[Turn%2], k, l);
           FloodFill(player[Turn%2],k,l);
           /*
           Thread myThread = new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   GameAdmin.data.save(player);
                   GameAdmin.data.saveGame();
               } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, ex);
               }
           }
           });
           //myThread.start();*/
           Turn++;
           if(GameAdmin.playerNumber==1 && Turn%2==1)
           {        
               autoplay();
           }
    }
    static void initPlayer(int playerNumber){
        player = new Player[2];
        GameAdmin.data = new DataSet();
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
    static void addFlag(int k , int l) throws IOException, InterruptedException{ 
        System.out.println("flag");
        board[k][l].setFlagged(!board[k][l].isFlagged());
        if(board[k][l].isFlagged()){//put flag
            Grid.buttons[k][l].setIcon(Grid.resizeIcon(setIcon(9), 40, 40));
            scoreCount(player[Turn%2], k, l);
        }else{                      //شيل العلم شيل شيل يا طويل العمر شيل
            Grid.buttons[k][l].setIcon(setIcon(100));
            System.out.println("شيل العلم شيل شيل يا طويل العمر شيل");
            if(board[k][l].hasBomb())
               player[Turn%2].setScore(player[Turn%2].getScore()-5);
        } 
           /*Thread myThread = new Thread(new Runnable(){
           @Override
           public void run() {
               try {
                   GameAdmin.data.save(player);
                   GameAdmin.data.saveGame();
               } catch (IOException ex) {
                   JOptionPane.showMessageDialog(null, ex);
               }
           }
           });
           //myThread.start();*/
        Turn++;
        if(GameAdmin.playerNumber==1 && Turn%2==1)
           {
               autoplay();
           }
        
    }
    
    public GuiAdmin(){
    
    }
    
    public static ImageIcon setIcon(int x) throws IOException{
            String path = "C:\\Users\\User\\Desktop\\New folder\\facingDown.png";
            if(Turn%2==0)
            {//RED
               switch (x) {
                case -1 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\pngwing.com copy.png";
                break;
                case 0 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\New Canvas.png";
                break;
                case 1 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\1 copy.png";
                break;
                case 2 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\2 copy.png";
                break;
                case 3 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\3 copy.png";
                break;
                case 4 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\4 copy.png";
                break;
                case 5 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\5 copy.png";
                break;
                case 6 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\6 copy.png";
                break;
                case 7 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\7 copy.png";
                break;
                case 8 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\8 copy.png";
                break;
                case 9 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\unnamed copy.png";
               }
            }else{//GREEN
                switch (x) {
                case -1 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\pngwing.com copy1.png";
                break;
                case 0 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\New Canvas.png";
                break;
                case 1 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\1 copy1.png";
                break;
                case 2 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\2 copy1.png";
                break;
                case 3 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\3 copy1.png";
                break;
                case 4 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\4 copy1.png";
                break;
                case 5 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\5 copy1.png";
                break;
                case 6 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\6 copy1.png";
                break;
                case 7 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\7 copy1.png";
                break;
                case 8 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\8 copy1.png";
                break;
                case 9 : path = "C:\\Users\\Sarah Akkad\\Desktop\\New folder\\unnamed copy1.png";
               }
                
            }
        return new ImageIcon(path);

       }
    public static void FloodFill(Player player , int col , int row ) throws IOException, InterruptedException{
        if(board[col][row].isCleared() )return; 
        if(board[col][row].hasBomb())
        {
            Grid.buttons[col][row].setIcon(Grid.resizeIcon(setIcon(-1), 40, 40));
            board[col][row].setCleared(true);
            return;
        }else{
            Grid.buttons[col][row].setIcon(Grid.resizeIcon(setIcon(board[col][row].getBombNearby()), 40, 40));
        }
        board[col][row].setCleared(true);
        board[col][row].setFlagged(false);
        --GameAdmin.leftCells;
        board[col][row].setMark(player.getMark());
        if(board[col][row].getBombNearby()==0)
        {
            for (int i = col-1; i <= col+1; i++) {
                for (int j = row-1; j <= row+1; j++) {
                    //if(i>0 && i < Y-1 && j >0 && j < X-1 && (i!=col && j!=row))
                        try {
                        FloodFill(player,i,j);
                    } catch (Exception e) {
                        continue;
                    }

                }
            }
        }
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
        System.out.println( player.getMark() + "score is " + player.getScore());
    }
    public static void autoplay() throws IOException, InterruptedException{
       //الغباء الاصطناعي
       int c = 0 ,r = 0;
       if(GameAdmin.leftCells<1)return;
            Random rand = new Random();
            boolean cc = false;
            boolean rr = false;
            while(!cc)//let's find c
            {
                c = rand.nextInt(GameAdmin.Y);
                for (int i = 0; i < 10; i++) {
                    if(!board[c][i].isCleared())
                        cc=true;
                }
            }//now let's find r
            while(!rr)
            {
                r = rand.nextInt(GameAdmin.X);
                    if(!board[c][r].isCleared())
                        rr=true;
            }
            if(board[c][r].hasBomb())
            {
                if(rand.nextInt(6)==2)
                {
                    play(c,r);
                }else{
                    addFlag(c, r);
                }
            }else{
                if(rand.nextInt(18)==5)
                {
                    addFlag(c,r);
                }else{
                    play(c,r);
                }
            }
        
    }   
    public static void Win(){
        //win frame
                                Player p;
                                if(player[0].getScore() > player[1].getScore())
                                {
                                   p = player[0];
                                }else{
                                   p = player[1];
                                }
                                JFrame winfram = new JFrame();
                                winfram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                winfram.setSize(300, 300);
                                BufferedImage bufferedImage = null;
                                try {
                                    bufferedImage = ImageIO.read(new File("C:\\Users\\Sarah Akkad\\Desktop\\New folder\\photo_2023-01-05_18-54-18.jpg"));
                                } catch (IOException ex1) {
                                    Logger.getLogger(Minesweeper2.class.getName()).log(Level.SEVERE, null, ex1);
                                }
                                Image image = bufferedImage.getScaledInstance(300, 300, Image.SCALE_DEFAULT);
                                ImageIcon iconw = new ImageIcon(image);
                                winfram.setVisible(true);
                                JLabel jLabel = new JLabel();
                                jLabel.setIcon(iconw);
                                JLabel winner = new JLabel( p.getName() + " wins !");
                                winner.setForeground(Color.white);

                                winner.setBounds(10, 100, 100, 30);
                                winner.setBackground(Color.blue);
                                winner.setOpaque(true);

                                jLabel.add(winner);
                                winfram.add(jLabel);
    }
   

}
