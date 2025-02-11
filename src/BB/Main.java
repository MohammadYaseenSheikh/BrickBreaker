package BB;

import java.awt.Canvas;

import javax.swing.JFrame;

public class Main extends Canvas{

	public static void main(String[] args) {
		
//		Creating Frame object
		JFrame obj = new JFrame();
		
//		Creating game play object
		GamePlay gP = new GamePlay();
		
//		Creating Frames of Game
		obj.setBounds(10,10,755,665);
		
//		Giving title to frame
		obj.setTitle("Brick Breaker");
//		Fixing size of Frame
		obj.setResizable(false);
//		Making Visible Frame
		obj.setVisible(true);
		
//		Defining Close Operation
		obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		obj.add(gP);
		
	}

}
