package javaProject;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;



public class Bricks extends JLabel{
    private int lives;

    public Bricks(Icon a){
        super(a);
        this.lives = lives;
    }

    public void disposeK(){
        setVisible(false);
    }

    public void show(){
        setVisible(true);
    }

    public void setLive(int live){
        this.lives = live;
    }

    public int getLive(){
        return lives;
    }

    public void decLive(){
        lives--;
    }
}
