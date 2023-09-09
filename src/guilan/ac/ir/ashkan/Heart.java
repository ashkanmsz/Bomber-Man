package guilan.ac.ir.ashkan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Heart {

	public int x = 120;
	public int y = 120;
	public int Time = 0;
	public boolean f=false;
	private static BufferedImage heartImage;;
	public static ArrayList<Heart> heartList = new ArrayList<>();
	game game1;
	
	public Heart() {

	}

	public Heart(game game1) throws IOException {
		this.game1 = game1;
		String dir= System.getProperty("user.dir");
		heartImage = ImageIO.read(new File(dir+"/LifeHearth_Full.png"));
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(heartImage, x, y, null);

	}

	public boolean wallHeartCollision(int i) {

		return walls.wallList[i].getBounds().intersects(getBounds());

	}

	public boolean stonesHeartCollision(int i) {

		return Stones.stoneList.get(i).getBounds().intersects(getBounds());

	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, heartImage.getWidth(), heartImage.getHeight());
	}

	public void setHeart() {//in this function we set the hearts location and time

		int k = 1;
		
		for (int i = 0; i < Stones.stoneList.size(); i++)
			for (int j = 0; j < walls.wallList.length; j++) {

				Heart obj = new Heart();
				obj.setX(game.mainpage.setWidth(game.getPanel()));
				obj.setY(game.mainpage.setHeight(game.getPanel()));

				while (true) {
					if (obj.wallHeartCollision(j) && obj.stonesHeartCollision(i)) {
						obj.setX(game.mainpage.setWidth(game.getPanel()));
						obj.setY(game.mainpage.setHeight(game.getPanel()));
					} else
						break;
				}

				heartList.add(obj);
			}
		for (int i = 0; i < heartList.size(); i++) {
			heartList.get(i).setTime(k * 40);
			k++;	
		}
	}

	public int getTime() {
		return Time;
	}

	public void setTime(int time) {
		Time = time;
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
