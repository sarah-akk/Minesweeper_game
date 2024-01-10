package myminesweeper;


import myminesweeper.GuiAdmin;
import myminesweeper.Grid;
import myminesweeper.GameAdmin;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import static myminesweeper.Grid.timer1;
import static myminesweeper.Grid.turn;



public class Minesweeper2 implements Runnable{
    
    
    @Override
    public void run() {

        try {
            boolean round1 = true;
            while (round1) {
                while (round1) {
                    turn.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Sarah Akkad\\Desktop\\New folder\\pngwing.com (3) copyq.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));
                   int oldturn = GuiAdmin.Turn;
                    for (int i = 10; i > 0; i--) {
                        if (GuiAdmin.Turn % 2 == 1 || oldturn !=GuiAdmin.Turn) {
                            break;
                        }
                        Thread.sleep(1000);
                        timer1.setForeground(Color.RED);
                        timer1.setText("            " + String.valueOf(i));
                        if(i==1)
                            GuiAdmin.Turn++;
                    }
                    if( GameAdmin.playerNumber==2)
                    round1 = false;
                }
                while (!round1) {
                    turn.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\Sarah Akkad\\Downloads\\Telegram Desktop\\pngwing.com (3) copyq copy.png").getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT)));

                    for (int i = 10; i > 0; i--) {
                        if (GuiAdmin.Turn % 2 == 0) {
                            break;
                        }
                        Thread.sleep(1000);
                        timer1.setForeground(Color.BLUE);
                        timer1.setText("            " + String.valueOf(i));
                        if(i==1)
                            GuiAdmin.Turn++;
                    }

                    round1 = true;
                }
            }

        } catch (InterruptedException ex) {
            Logger.getLogger(Minesweeper2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) throws IOException{
  
      Grid grid = new Grid();
      
        
        
        //firstframe
        JFrame fram1 = new JFrame("minesweeper");
        fram1.setContentPane(new JLabel(new ImageIcon(ImageIO.read(new File("C:\\Users\\Sarah Akkad\\Desktop\\New folder\\Example.jpg.3198f1e9c7c7a5038e4179d6abc47404.jpg")))));
        fram1.setIconImage((new ImageIcon("C:\\Users\\Sarah Akkad\\Desktop\\New folder\\2wsdcv (5) copy.png")).getImage());
        JButton co = new JButton("Console Game");
        co.setBounds(120, 150, 150, 30);

        JButton gu = new JButton("Gui Game");
        gu.setBounds(120, 200, 150, 30);
        
        

        fram1.add(co);
        fram1.add(gu);
        fram1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram1.setSize(415, 500);
        fram1.setLayout(null);
        fram1.setVisible(true);
        fram1.setResizable(false);

        //consolegame
        co.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fram1.setVisible(false);
                int a;
                System.out.println("how many player ? ");
                do{
                    System.out.println("enter 1 o 2");
                    a = new Scanner(System.in).nextInt();
                }while(a!=1 && a!=2);
                try{
                if(a==1)
                    GameAdmin.ConsoleVSauto();
                else
                    GameAdmin.ConsoleVsPlayer();
                }catch (IOException ex) {
                    Logger.getLogger(Minesweeper2.class.getName()).log(Level.SEVERE, null, ex);
                }
                System.exit(0);
            }

        });
        //guigame
        gu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                fram1.getContentPane().removeAll();
                fram1.repaint();
                JButton ne = new JButton("New Game");
                JButton lo = new JButton("Loud Game");
                JButton ex = new JButton("ExitGame");
                ex.setBounds(30, 350, 150, 30);
                ne.setBounds(30, 250, 150, 30);
                lo.setBounds(30, 300, 150, 30);
                JLabel Lb1 = new JLabel("Welcome!");
                Lb1.setBounds(120, 50, 300, 100);
                Lb1.setForeground(Color.WHITE);
                Lb1.setFont(new Font("Verdana", Font.PLAIN, 30));

                fram1.add(ne);
                fram1.add(ex);
                fram1.add(lo);
                fram1.add(Lb1);
                
                
                  ex.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                       fram1.dispatchEvent(new WindowEvent(fram1, WindowEvent.WINDOW_CLOSING));

                    }
                });
                  
                //loudgame
                lo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {

                        //code?
                        //try
                                
                                fram1.setVisible(false);
                                JFrame frame = new JFrame();
                                frame.setIconImage((new ImageIcon("C:\\Users\\Sarah Akkad\\Desktop\\New folder\\2wsdcv (5) copy.png")).getImage());
                                frame.setLayout(null);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(415,500);
                                frame.setResizable(false);
                                new GuiAdmin();
                                frame.add(grid.getButtonarea());
                                frame.add(grid.getScorearea());
                                frame.setVisible(true);
                                //GameAdmin.data = new DataSet().loadGame();
                                //GameAdmin.data.load();
                                
                                
                        //
                    }
                });
                //newgame
                ne.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ae) {
                        fram1.getContentPane().removeAll();
                        fram1.repaint();
                        //secondframe
                        JButton p1 = new JButton("1 Player");
                        p1.setBounds(100, 150, 80, 80);

                        JButton p2 = new JButton("2 Players");
                        p2.setBounds(200, 150, 80, 80);
                        fram1.add(p1);
                        fram1.add(p2);
                        //firstplayer
                        p1.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                             
                                GameAdmin.playerNumber = 1 ;
                                
                                fram1.setVisible(false);
                                JFrame frame = new JFrame();
                                frame.setIconImage((new ImageIcon("C:\\Users\\Sarah Akkad\\Desktop\\New folder\\2wsdcv (5) copy.png")).getImage());
                                frame.setLayout(null);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(415,500);
                                frame.setResizable(false);
                                
                                frame.add(grid.getButtonarea());
                                frame.add(grid.getScorearea());
                                frame.setVisible(true);
                                
                                new GuiAdmin();
                                Minesweeper2 m1 = new Minesweeper2();
                                Thread t1 = new Thread(m1);
                                t1.start();
                                
                                
                                   

                            }
                        });
                        //secondplayer
                        p2.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent ae) {
                               
                                GameAdmin.playerNumber = 2 ;
                                
                                fram1.setVisible(false);
                                JFrame frame = new JFrame();
                                frame.setIconImage((new ImageIcon("C:\\Users\\User\\Desktop\\New folder\\2wsdcv (5) copy.png")).getImage());
                                frame.setLayout(null);
                                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                                frame.setSize(415,500);
                                frame.setResizable(false);
                                
                                frame.add(grid.getButtonarea());
                                frame.add(grid.getScorearea());
                                frame.setVisible(true);
                                new GuiAdmin();
                                Minesweeper2 m1 = new Minesweeper2();
                                Thread t1 = new Thread(m1);
                                t1.start();
                                
                                
         
                                 
                            }
                        });
                     

                    }

                });
            }
        });
    }
}

    

