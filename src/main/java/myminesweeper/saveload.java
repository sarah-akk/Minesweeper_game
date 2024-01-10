package myminesweeper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import myminesweeper.GameAdmin;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;


/**
 * @author HOME
 */
public class saveload {

    public void save() {

        PrintWriter s;
        try {
            s = new PrintWriter("GameAdmin.txt");
            if (GameAdmin.playerNumber == 1) {
                s.println(GameAdmin.player[0]);
                s.println(GameAdmin.player[1]);
                s.println(GameAdmin.data);
            } else {
                s.println(GameAdmin.player[0]);
                s.println(GameAdmin.player[1]);
                s.println(GameAdmin.data);

                s.print(GameAdmin.grid);
                s.print(GameAdmin.board);
                s.print(GameAdmin.flags);
            }
            s.close();
        } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void reload() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("GameAdmin.txt"));
            String str;
        } catch (Exception e) {
            System.out.println(e.getMessage() + "err");
        }
    }
}



