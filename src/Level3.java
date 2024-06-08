package javaProject;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class Level3 implements KeyListener,Runnable{
    private JFrame frame;
    private JPanel panel;
    private JLabel paddle;
    private JLabel ball;
    private JLabel livLabel;
    private JLabel scLabel;
    private JLabel levelLabel;
    private Icon paddleIcon;
    private Icon brickIcon;
    private Icon ballIcon;
    private ArrayList<Bricks> brickArray;
    private JButton button = new JButton("Okey");
    public boolean isgameWon = false;

    public static int lives = 3;
    public static int score = 0;
    private static int xPaddle = 400;
	private static int yPaddle = 800;
    private static int xBallPos = 450;
	private static int yBallPos = 450;

    private static int xBallSpd = 5;
    private static int yBallSpd = 8;


    public void createLevel3(){
        frame = new JFrame("Game");
        frame.setSize(900,900);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);
        String ado = "Lives: " + Integer.toString(lives);
        livLabel = new JLabel(ado);
        livLabel.setBounds(800, 25, 50, 20);

        levelLabel = new JLabel("Level 3");
        levelLabel.setBounds(430, 25, 50, 20);

        String ado2 = "Score: " + Integer.toString(score);
        scLabel = new JLabel(ado2);
        scLabel.setBounds(50, 25, 100, 20);
        paddleIcon=new ImageIcon((main.class.getResource("paddle1.png")));
        brickIcon=new ImageIcon((main.class.getResource("redBrick.png")));
        ballIcon = new ImageIcon((main.class.getResource("mikeyeni.png")));

        frame.setIconImage(((ImageIcon) ballIcon).getImage());
        ball = new JLabel();
        ball.setIcon(new ImageIcon(new ImageIcon((main.class.getResource("mikeyeni.png"))).getImage().getScaledInstance(30,30, Image.SCALE_DEFAULT)));
        ball.setBounds(xBallPos, yBallPos, 30, 30);

        brickArray = new ArrayList<>();
        paddle = new JLabel();
        paddle.setIcon(paddleIcon);
        paddle.setBounds(xPaddle, yPaddle, 100, 26);

        int brickCount = 0;
        for(int j=100;j<= 210; j+=40){
            for(int k= 20;k<=850;k+=80){
                if(brickCount <= 10){
                    Bricks n = new Bricks(new ImageIcon(new ImageIcon((main.class.getResource("blackBrick.png"))).getImage().getScaledInstance(45,20, Image.SCALE_DEFAULT)));
                    n.setBounds(k, j, 46, 20);
                    n.setLive(3);
                    brickArray.add(n);
                    frame.add(n);
                    brickCount++;
                }

                else if((10 < brickCount) && (brickCount <= 21)){
                    Bricks n = new Bricks(new ImageIcon(new ImageIcon((main.class.getResource("orangeBrick.png"))).getImage().getScaledInstance(45,20, Image.SCALE_DEFAULT)));
                    n.setBounds(k, j, 45, 20);
                    n.setLive(2);
                    brickArray.add(n);
                    frame.add(n);
                    brickCount++;
                }

                else if(brickCount > 21){
                    Bricks n = new Bricks(new ImageIcon(new ImageIcon((main.class.getResource("redBrick.png"))).getImage().getScaledInstance(45,20, Image.SCALE_DEFAULT)));
                    n.setBounds(k, j, 45, 20);
                    // n.setLive(1);
                    brickArray.add(n);
                    frame.add(n);
                    brickCount++;               
                }
            }
        }


        frame.add(levelLabel);
        frame.add(ball);
        frame.add(paddle);
        // frame.setComponentZOrder(ball, 1);
        // frame.add(panel);
        frame.add(livLabel);
        frame.add(scLabel);
        frame.addKeyListener(this);
        frame.setVisible(true);

        xPaddle = 400;
        yPaddle = 800;
        xBallPos = 450;
        yBallPos = 450;
    
        xBallSpd = 7;
        yBallSpd = -8;
        lives = 3;
        score = 0;


    }

    public void movePaddle(int newX,int newY) {
		paddle.setBounds(newX, newY, 100, 26);
	}

    public void moveBall(int newX,int newY) {
		ball.setBounds(newX, newY, 30, 30);
	}


    public void isCollidedWall(JLabel ball){
        if(ball.getX() >= 850 || ball.getX() <= 0){
            xBallSpd = -xBallSpd;
        }

        if(ball.getY() <= 0){
            yBallSpd = -yBallSpd;
        }
        else if(ball.getY() >= 870){
            if(lives-1 == 0){ ////FINISH
                JOptionPane.showMessageDialog(null,"You are out of lives");
                frame.dispose();
            }
            else{
                moveBall(450, 450);
                xBallSpd = -xBallSpd;
                xBallPos = 450;
                yBallPos = 450;
                lives--;
            }

        }
    }

    public void isCollidePaddle(JLabel ball,JLabel paddle){

        if(paddle.getBounds().intersects(ball.getBounds())){
            yBallSpd = -yBallSpd;
        }

    }

    public void isCollideBricks(ArrayList<Bricks> array,JLabel ball){
        for(Bricks b:array){
            if(ball.getBounds().intersects(b.getBounds())){
                if(b.getLive() > 2){
                    yBallSpd = -yBallSpd;
                    b.setIcon(new ImageIcon(new ImageIcon((main.class.getResource("orangeBrick.png"))).getImage().getScaledInstance(45,20, Image.SCALE_DEFAULT)));
                    score+=20;
                    b.decLive();
                }
                else if(b.getLive() == 2){
                    yBallSpd = -yBallSpd;
                    b.setIcon(new ImageIcon(new ImageIcon((main.class.getResource("redBrick.png"))).getImage().getScaledInstance(45,20, Image.SCALE_DEFAULT)));
                    score+=20;
                    b.decLive();
                }
                else if(b.getLive() < 2){
                    yBallSpd = -yBallSpd;
                    score+= 40;
                    b.disposeK();
                    array.remove(b);
                }
            }
        }
    }


    @Override
	public void keyPressed(KeyEvent event) {
		// TODO Auto-generated method stub
		String whichKey = KeyEvent.getKeyText(event.getKeyCode());

		if (whichKey.compareTo("Left") == 0) {

			changeLayoutLeft();
		} else if (whichKey.compareTo("Right") == 0) {

			changeLayoutRight();
        }else {
		}

        if ((event.getKeyCode() == KeyEvent.VK_Q) && ((event.getModifiers() & KeyEvent.CTRL_MASK) != 0)){
            frame.dispose();
        }
	}

    public void changeLayoutLeft() {
        if(xPaddle - 15 >= -5 && xPaddle - 15<=790){
            xPaddle = xPaddle - 20;
            // System.out.println(xPaddle);
            movePaddle(xPaddle,yPaddle);
        }
        else{
        }
	}

	public void changeLayoutRight() {
        if(xPaddle + 15 >= -5 && xPaddle + 15<=790){
            xPaddle = xPaddle + 20;
            // System.out.println(xPaddle);
            movePaddle(xPaddle,yPaddle);
        }
        else{
        }
	}
    @Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		while(frame.isShowing() == true) {
			try {
                xBallPos += xBallSpd;
                yBallPos += yBallSpd;
                moveBall(xBallPos, yBallPos);
				// System.out.println("ball X position" + ball.getX() + " " +"ball y position" + ball.getY());
                // System.out.println("paddle x" + paddle.getX() + " " + "paddle y" + paddle.getY());
                isCollidedWall(ball);
                isCollidePaddle(ball, paddle);
                isCollideBricks(brickArray, ball);
                String ado = ("Lives: " + Integer.toString(lives));
                livLabel.setText(ado);

                String ado2 = ("Score: " + Integer.toString(score));
                scLabel.setText(ado2);

                if(brickArray.isEmpty() == true){
                    JOptionPane.showMessageDialog(null,"You completed the game!!");
                    isgameWon = true;
                    frame.dispose();
                }

            
				Thread.sleep(30);
			} catch (Exception e) {
			}
		}
		
	}
		

}
