package guilan.ac.ir.ashkan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Stones {
	public int x = 120;
	public int y = 120;
	private game game1;
	private static BufferedImage stoneImage;
	public static ArrayList<Stones> stoneList = new ArrayList<>();

	public Stones() {

	}

	public Stones(game game1) throws IOException {
		this.game1 = game1;
		String dir= System.getProperty("user.dir");
		stoneImage = ImageIO.read(new File(dir+"\\walls/40-40_BrickBrown.png"));
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(stoneImage, x, y, null);
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, stoneImage.getWidth(), stoneImage.getHeight());
	}

	public void setStones() {//in this function we set the stones location

		for (int j = 0; j < walls.wallList.length; j++) {
			
				Stones obj = new Stones();
				
				obj.x = game.mainpage.setWidth(game.getPanel());
				obj.y = game.mainpage.setHeight(game.getPanel());
			
				while (true) {
					
					if(obj.stonesIronaWallcollision(j)) {
						
						obj.x = game.mainpage.setWidth(game.getPanel());
						obj.y = game.mainpage.setHeight(game.getPanel());
					}
					else 
						break;
					
				}
				stoneList.add(obj);
			}
	}

	public boolean stonesIronaWallcollision(int i) {

		return walls.wallList[i].getBounds().intersects(this.getBounds());

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

}
