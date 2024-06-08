package javaProject;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Iterator;
import java.util.*;
import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.sound.sampled.*;


public class main extends Thread {
    static int userC = 1;

     ///////////////////////////////////////////////////////////////////////
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        
        JFrame frame = new JFrame();
        JPanel panel=new JPanelWithBackground("background.png");

        // File file = new File("Dababy.wav");
        // AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);           //// I TRIED TO ADD SOUND BUT UNLUCKY 
        // Clip clip = AudioSystem.getClip();
        // clip.open(audioStream);

        frame.setSize(950,950);
        frame.setTitle("My game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.getContentPane().add(panel);
		frame.add(panel);
        ImageIcon ballIcon = new ImageIcon((main.class.getResource("mikeyeni.png")));

        frame.setIconImage(((ImageIcon) ballIcon).getImage());
        panel.setLayout(null);
        JLabel label = new JLabel("Arkonoid Game");
        label.setBounds(20, 5, 200, 200);
        label.setFont(new Font("Serif", Font.PLAIN,28));
        panel.add(label);

////////////////////////////////////////////////////////////////////                         BUTTONS 
        JButton newgameButton = new JButton("New Game");
        newgameButton.setBounds(20, 200, 100, 50);
        newgameButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent n){
                // JOptionPane.showMessageDialog(frame, "NEW GAME", "TBD", 0);
                // clip.start();
                if(userC == 1){
                    Level1 gmp = new Level1();
                    gmp.createLevel1();
                    Thread nice = new Thread(gmp);
                    nice.start();
                }
                else if(userC == 2){
                    Level2 gmm = new Level2();
                    gmm.createLevel2();
                    Thread nice = new Thread(gmm);
                    nice.start();
                }

                else if(userC == 3){
                    Level3 gmn = new Level3();
                    gmn.createLevel3();
                    Thread nice = new Thread(gmn);
                    nice.start();
                }

            }
        });
        panel.add(newgameButton);


        JButton optionsButton = new JButton("Options");
        optionsButton.setBounds(20, 300, 100, 50);
        optionsButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent n){
                String temp = JOptionPane.showInputDialog(null, "For Level 1 enter: 1\nFor Level 2 enter: 2\nFor Level 3 enter: 3\n");
                userC = Integer.parseInt(temp);
            }
        });
        panel.add(optionsButton);


        JButton scoresButton = new JButton("Scores");
        scoresButton.setBounds(20, 400, 100, 50);
        scoresButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent n){
                JOptionPane.showMessageDialog(frame, "Scores", "TBD", 2);
            }
        });
        panel.add(scoresButton);


        JButton helpButton = new JButton("Help");
        helpButton.setBounds(20, 500, 100, 50);
        helpButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent n){
                JOptionPane.showMessageDialog(frame, "Aim is that break all the bricks on the screen with ball using your LEFT and RIGHT buttons", "HELP", 1);
            }
        });
        panel.add(helpButton);


        JButton aboutButton = new JButton("About");
        aboutButton.setBounds(20, 600, 100, 50);
        ImageIcon myIcon=new ImageIcon(main.class.getResource("ali.png"));
        JLabel mylabel = new JLabel("Name: Ali Fatih\nSurname: Durgut\n"+ 
                                    "School Number: 20190702068\n" +           
                                    "Email: alifatih.durgut@std.yeditepe.edu.tr");
        // mylabel.setFont(new Font("Arial" , Font.HANGING_BASELINE, 25));
        aboutButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent n){
                JOptionPane.showMessageDialog(frame, "Name: Ali Fatih\n"+
                                                    "Surname: Durgut\n"+ 
                                                    "School Number: 20190702068\n" +           
                                                    "Email: alifatih.durgut@std.yeditepe.edu.tr","ABOUT" , JOptionPane.PLAIN_MESSAGE,myIcon);
            }
        });
        panel.add(aboutButton);



        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent n){
                int cho = JOptionPane.showConfirmDialog(frame, "EXIT NOW ?", "TBD", 2);
                if(cho == JOptionPane.YES_OPTION){
                    frame.dispose();
                }
            }
        });
        exitButton.setBounds(20, 700, 100, 50);
        panel.add(exitButton);

        



        frame.setVisible(true);


    }
    ////////////////////////////////////////////////////////////////////

}
