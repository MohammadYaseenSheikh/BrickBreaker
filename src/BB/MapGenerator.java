package BB;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class MapGenerator {
	
//	Map Generator Ingredients
	public int map [][];
	public int brickWidth;
	public int brickHeight;
	Toolkit t = Toolkit.getDefaultToolkit();
	Image img = t.getImage("C:\\Eclipse WorkPlace\\Brick_Breaker\\src\\Media_Image\\Picture1.png");
	
	
	public MapGenerator(int row,int col) {
		
		map = new int [row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = 1;
			}
		}
		
//		Bricks Width & Height
		brickWidth = 560/col;
		brickHeight = 150/row;
		
	}
		
	public void draw(Graphics g) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				if(map[i][j] > 0) {
					
//					Black Bricks
					g.drawImage(img, j*brickWidth + 80, i*brickHeight + 50, 80, 50, null);
//					g.drawRect(j*brickWidth + 80,i*brickHeight + 50, brickWidth, brickHeight);
					
				}
			}
		}
	}

	public void setBrickValue(int value, int i, int j) {

		map[i][j] = value;
		
	}

}
