package BB;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GamePlay extends JPanel implements ActionListener, KeyListener{

//	Defining and Declaring variables
	
	private boolean play = false;
	private int score = 0;
	
	private int totalBrick = 21;
	
	private Timer timer;
	private int delay = 8;
	private int PlayerX = 340;
	
	private int BallPosX = 350;
	private int BallPosY = 100;
	private int BallXdir = -1;
	private int BallYdir = -2;

	private boolean flag = false;
	
	Toolkit t = Toolkit.getDefaultToolkit();
	Image img = t.getImage("src\\Media_Image\\Background.jpg");
	
	Image img2 = t.getImage("C:\\Eclipse WorkPlace\\Brick_Breaker\\src\\Media_Image\\Ball.png");
	
	MapGenerator map;
	
	public GamePlay() {
		
		map = new MapGenerator(3,7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
//		timer = new Timer(delay, this);
		timer.start();
		
	}
	
	public void paint(Graphics g) {
		
//		Background
		g.drawImage(img, 20,20,this);
		
//		Drawing Map of Bricks
		map.draw((Graphics2D)g);
		
//		Border of Game
		g.setColor(Color.red);
		g.fillRect(20, 20, 3, 597);
		g.fillRect(20, 20, 697, 3);
		g.fillRect(717, 20, 3, 597);
		
//		Pedal Attributes
		g.setColor(Color.black);
		g.fillRoundRect(PlayerX, 580, 80, 8, 2, 3);
		
//		Ball Attributes
		g.setColor(Color.red);
		g.fillOval(BallPosX, BallPosY, 20, 20);
		g.setColor(Color.white);
		g.fillOval(BallPosX+12, BallPosY+4, 4, 6);
		
//		Score Board
//		g.setColor(Color.DARK_GRAY);
//		g.setFont(new Font("serif",Font.BOLD, 25));
//		g.drawString("Score : " + score, 335 , 615);
		
		if(BallPosY > 580) {
			
			flag = true;
			
			play = false;
			BallXdir = 0;
			BallYdir = 0;
			
			g.setColor(Color.red);
			g.setFont(new Font("Pixeboy", 0, 55));
			g.drawString("GAME OVER", 260, 350);
			
			g.setFont(new Font("Pixeboy", 0, 30));
			g.drawString("Your Score : "+score, 285, 400);
			
			g.setFont(new Font("consolas", 0, 20));
			g.drawString("Press enter to continue...", 230, 450);
			
			g.setColor(Color.red);
			g.fillOval(BallPosX, BallPosY, 20, 20);
			g.setColor(Color.white);
			g.fillOval(BallPosX+12, BallPosY+4, 4, 6);
			
		}
		
		if(totalBrick == 0) {
			
			flag = true;
			
			play = false;
			BallXdir = 0;
			BallYdir = 0;
			
			g.setColor(Color.green.darker());
			g.setFont(new Font("Pixeboy", 0, 55));
			g.drawString("Well Done!", 265, 350);

			g.setColor(Color.black);
			g.setFont(new Font("Pixeboy", 0, 30));
			g.drawString("Your Score : "+score, 285, 400);
			
		}
		
//		Releasing Resources which won't be use anymore
		g.dispose();
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		
		if(e.getKeyCode() == KeyEvent.VK_ENTER && flag ==true) {
			map = new MapGenerator(3,7);
			score = 0;
			totalBrick = 21;
			play = true;
			BallPosY = 350;
			BallXdir = -1;
			BallYdir = -2;
			
			flag = false;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			if(PlayerX >= 620) {
				PlayerX = 620;
			}else {
				moveRight();
			}
		}
		
		if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			if(PlayerX <= 40) {
				PlayerX = 40;
			}else {
				moveLeft();
			}
		}
	}
	
	private void moveLeft() {
		
		play = true;
		PlayerX -= 20;
		
	}


	private void moveRight() {
		
		play = true;
		PlayerX += 20;
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		timer.start();
		
//		Ball Pedal Interaction
		if(play) {
			
			if(new Rectangle(BallPosX, BallPosY, 20, 20).intersects(new Rectangle(PlayerX, 580, 80, 8))) {
//				BallYdir = -BallYdir;
				if(BallPosY+19 <= 584) {
					BallYdir = -BallYdir;
				}else{
					BallXdir = -BallXdir;
				}
			}
			
			for (int i = 0; i < map.map.length; i++) {
				for (int j = 0; j < map.map[0].length; j++) {
					if(map.map[i][j] > 0) {
						
//						Bricks location
						int BrickX = j*map.brickWidth + 80;
						int BrickY = i*map.brickHeight + 50;
						
//						Bricks Dimension
						int brickWidth = map.brickWidth;
						int brickHeight = map.brickHeight;
						
//						Generating Bricks
						Rectangle Brick = new Rectangle(BrickX, BrickY, brickWidth, brickHeight);
						Rectangle Ball = new Rectangle(BallPosX, BallPosY, 20, 20);
						
						if(Ball.intersects(Brick)) {
							map.setBrickValue(0, i, j);
							totalBrick--;
							score += 5;
							
							if(BallPosX + 19 <= Brick.x || BallPosX + 1 >= Brick.x + Brick.width) {
								BallXdir = -BallXdir;
							}else {
								BallYdir = -BallYdir;
							}
						}
					}
				}
			}
			BallPosX += BallXdir;
			BallPosY += BallYdir;
				
			if(BallPosX <= 23 || BallPosX >= 700) {
				BallXdir = -BallXdir;
			}
				
			if(BallPosY <= 24) {
				BallYdir = -BallYdir;
			}
			
		}
		
		repaint();
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
