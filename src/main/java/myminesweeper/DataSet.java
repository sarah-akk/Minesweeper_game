package myminesweeper;



//saving and loading

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JButton;
import javax.swing.JOptionPane;

public class DataSet implements Serializable {
    private boolean[][] hasbomb     = new boolean[GameAdmin.Y][GameAdmin.X];
    private boolean[][] isFlagged   = new boolean[GameAdmin.Y][GameAdmin.X];
    private int[][]     bombNearby  = new int[GameAdmin.Y][GameAdmin.X];
    private JButton[][] buttons;
    private Player[]    playerData  = new Player[2];
    private int         playerNumber;
    private int         Turn;
    DataSet()
    {
          playerData[0] = new Player();
          playerData[1] = new Player();
    }
    public void save(Player[] player){
        //grid info        
        for(int i = 0 ; i<GameAdmin.Y ; i++){
            for (int j = 0; j < GameAdmin.X; j++) {
            buttons          =  Grid.buttons;
            hasbomb[i][j]    =  GameAdmin.board[i][j].hasBomb();
            isFlagged[i][j]  =  GameAdmin.board[i][j].isFlagged();
            bombNearby[i][j] =  GameAdmin.board[i][j].getBombNearby();
           } 
        }
            playerData[0]    =  player[0];
            playerData[1]    =  player[1];
            playerNumber     =  GameAdmin.playerNumber ;
            Turn             =  GuiAdmin.Turn;
    }
    public void saveGame() throws FileNotFoundException, IOException{
        try{
        FileOutputStream fileOut = new FileOutputStream("LastGame.ser");
        ObjectOutputStream out = new ObjectOutputStream(fileOut);
        System.out.println("SAVE SUCCESSFUL");
        
        out.writeObject(this);

        out.close();
        fileOut.close();
        }catch(IOException e){
            JOptionPane.showMessageDialog(null,e);
        }    
    }
    public DataSet loadGame(){
        DataSet data = null;
        try {
            FileInputStream fileIn = new FileInputStream("LastGame.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            

           
            data = (DataSet) in.readObject();

            in.close();
            fileIn.close();

        } catch (IOException | ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null,e);
        } 
        return data;
    }
    public void load(){
        for(int i = 0 ; i<GameAdmin.Y ; i++){
            for (int j = 0; j < GameAdmin.X; j++) {
            GameAdmin.board[i][j].setBomb(hasbomb[i][j]);
            GameAdmin.board[i][j].setFlagged(isFlagged[i][j]);
            GameAdmin.board[i][j].setBombNearby(bombNearby[i][j]);
           } 
        }
            GuiAdmin.player[0]    = playerData[0];
            GuiAdmin.player[1]    = playerData[1];
            GameAdmin.playerNumber= playerNumber ; 
            GuiAdmin.Turn         = Turn;
            Grid.buttons          = buttons;
        
    }
   

   
}
