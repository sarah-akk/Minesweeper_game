package myminesweeper;


import myminesweeper.GameAdmin;
import myminesweeper.DataSet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class Grid  {

    public static JButton[][] buttons ;
    private JButton[][] loadButtons;
    private JLabel score1;
    private JLabel name1;
    private JLabel score2;
    private JLabel name2;
    
    public static JLabel turn;
    public static JLabel timer1;
    
    private JPanel buttonarea;
    private JPanel scorearea;
    public DataSet data;

    public Grid() throws IOException {
        
        data = new DataSet();
        
        ImageIcon img = new ImageIcon("C:\\Users\\Sarah Akkad\\Desktop\\New folder\\photo_2023-01-05_20-53-39.jpg");

        scorearea = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
            }
        };
        scorearea.setLayout(null);
        scorearea.setBackground(Color.GRAY);
        scorearea.setBounds(0, 0, 415, 100);
        
      
        buttonarea = new JPanel(new GridLayout(GameAdmin.X + 1, GameAdmin.Y + 1));
        buttonarea.setBounds(0, 100, 415, 400);

        
        
        score1 = new JLabel();
        score1.setBorder(BorderFactory.createTitledBorder("score"));
        score1.setBounds(10, 8, 80, 40);
        score1.setText("250");
        score1.setForeground(Color.RED);
        score1.setBackground(Color.WHITE);
        score1.setOpaque(true);
        scorearea.add(score1);


        score2 = new JLabel();
        score2.setBorder(BorderFactory.createTitledBorder("score"));
        score2.setBounds(320, 8, 80, 40);
        score2.setText("250");
        score2.setBackground(Color.WHITE);
        score2.setForeground(Color.GREEN);
        score2.setOpaque(true);
        scorearea.add(score2);
      
        name1 = new JLabel();
        name1.setBorder(BorderFactory.createTitledBorder("player1"));
        name1.setBounds(10, 50, 80, 40);
        name1.setText("sara");
        name1.setBackground(Color.WHITE);
        name1.setForeground(Color.RED);
        name1.setOpaque(true);
        scorearea.add(name1);

        name2 = new JLabel();
        name2.setBorder(BorderFactory.createTitledBorder("player2"));
        name2.setBounds(320, 50, 80, 40);
        name2.setText("sara");
        name2.setBackground(Color.WHITE);
        name2.setForeground(Color.GREEN);
        name2.setOpaque(true);
        scorearea.add(name2);
        
        turn = new JLabel();
        turn.setBounds(182, 8, 50, 50);
        turn.setOpaque(true);

        scorearea.add(turn);

        timer1 = new JLabel();
        timer1.setBounds(167, 60, 80, 30);
        timer1.setBackground(Color.WHITE);
        timer1.setOpaque(true);

        scorearea.add(timer1);
        
        

        buttons = new JButton[GameAdmin.Y][GameAdmin.X];
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                buttons[i][j] = new JButton( );
                //buttons.button[i][j] = new JButton(new ImageIcon(ImageIO.read(new File("C:\\Users\\User\\Desktop\\img\\facingDown.png"))));
                //buttons[i][j].button.addMouseListener((MouseListener)this);
                //buttons[i][j].setFocusable(false);
                buttonarea.add(buttons[i][j]);
                buttons[i][j].setVisible(true);
                //
            
                //
            buttons[i][j].addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
          
              
            if (SwingUtilities.isRightMouseButton(e)) { 
                for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        if(e.getSource() == buttons[k][l]){
                            if(GuiAdmin.Turn != 0 && !GuiAdmin.board[k][l].isCleared() )
                            try {
                                GuiAdmin.addFlag(k,l);
                                showScore();
                            } catch (Exception ex) {
                                Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
                            }
                             System.out.println("P" + k + " " + l);
                        }
                                   
                        
                       
                    }
                }
            }
            else if (SwingUtilities.isLeftMouseButton(e)) {
            
                 for (int k = 0; k < 10; k++) {
                    for (int l = 0; l < 10; l++) {
                        if(e.getSource() == buttons[k][l])
                        {
                            if(GuiAdmin.Turn == 0 || !GuiAdmin.board[k][l].isCleared() )
                            try {  
                               GuiAdmin.play(k,l);
                               showScore();
                            } catch (Exception ex) {
                                Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
                            } 
                        }
                            
                       
                    }
                }}
            if(GameAdmin.leftCells<=0)
            GuiAdmin.Win();
         System.out.println("leftcells : " + GameAdmin.leftCells);
            
            
            
                 buttonarea.revalidate();
                 buttonarea.repaint();
                try {
                    data.saveGame();
                } catch (IOException ex) {
                    Logger.getLogger(Grid.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
 
            @Override
            public void mousePressed(MouseEvent e) {
               
            }
 
            @Override
            public void mouseReleased(MouseEvent e) {
               
            }
 
            @Override
            public void mouseEntered(MouseEvent e) {
                
            }
 
            @Override
            public void mouseExited(MouseEvent e) {
                
            }
        });
                //
            }
        }
       buttonarea.revalidate();
       buttonarea.repaint();
       loadButtons = buttons;
      
    }

    public JLabel getScore1() {
        return score1;
    }

    public JLabel getName1() {
        return name1;
    }

    public JLabel getScore2() {
        return score2;
    }

    public JLabel getName2() {
        return name2;
    }

    public JPanel getButtonarea() {
        return buttonarea;
    }

    public JPanel getScorearea() {
        return scorearea;
    }
    
    
    
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
    Image img = icon.getImage();  
    Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
    return new ImageIcon(resizedImage);
    }

    public static JButton[][] getBoard() {
        return buttons;
    }
    public void showScore(){
        if(GuiAdmin.player[0].getScore() <= 10)
        score1.setText((""+(GuiAdmin.player[0].getScore())));
        else
        score1.setText("?? > 10");
        
        if(GuiAdmin.player[1].getScore() <= 10)
        score2.setText((""+(GuiAdmin.player[1].getScore())));
        else
        score1.setText("?? > 10");
        
        name1.setText(GuiAdmin.player[0].getName());
        name2.setText(GuiAdmin.player[1].getName());
    }

}


