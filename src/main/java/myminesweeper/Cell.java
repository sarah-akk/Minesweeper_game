package myminesweeper;



import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;



class Cell{
    private boolean bomb;
    private boolean flagged;
    private boolean cleared;
    private int bombNearby;
    private String mark = "";
    public Cell() {
        bomb = false;
        flagged=false;
        cleared=false;
        bombNearby=0;
    }

    public boolean hasBomb() {
        return bomb;
    }

    public void setBomb(boolean bomb) {
        this.bomb = bomb;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean flagged) {
        this.flagged = flagged;
    }

    public boolean isCleared() {
        return cleared;
    }

    public void setCleared(boolean cleared) throws IOException {
        this.cleared = cleared;
    }

     public int getBombNearby() {
        return bombNearby;
    }

    public void setBombNearby(int bombNearby) {
        this.bombNearby = bombNearby;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }
    
    
        
   


 

    
}