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

/**
 * this is the star class
 * 
 * @author ashkan
 *
 */
public class Star {
	public int x=120;
	public int y=120;
	public boolean f = false;
	public int time = 0;
	public static int radiousTime = 0;// the time of pops for making their radious longer
	private game game1;
	private static BufferedImage starImage;
	public static ArrayList<Star> starList=new  ArrayList<>();

	public Star() {

	}

	public Star(game game1) throws IOException {
		this.game1 = game1;
		String dir= System.getProperty("user.dir");
		starImage = ImageIO.read(new File(dir+"/star.png"));
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(starImage, x, y, null);
	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, starImage.getWidth(), starImage.getHeight());
	}

	public boolean starIronWallCollision(int i) {// this function determine the toching of star and wall

		return walls.wallList[i].getBounds().intersects(getBounds());

	}

	public boolean starStonesCollision(int i) {// this function determine the toching of star and stone wall

		return Stones.stoneList.get(i).getBounds().intersects(getBounds());

	}

	public void setStars() {// in this function we make the addres of each star

		int k=1;

		for (int i = 0; i < Stones.stoneList.size(); i++)
			for (int j = 0; j < walls.wallList.length; j++) {

				Star obj=new Star();
				obj.setX(game.mainpage.setWidth(game.getPanel()));
				obj.setY(game.mainpage.setHeight(game.getPanel()));

				while (true) {
					if (starIronWallCollision(j) && starStonesCollision(i)) {
						obj.setX(game.mainpage.setWidth(game.getPanel()));
						obj.setY(game.mainpage.setHeight(game.getPanel()));
					} else
						break;
				}
				starList.add(obj);
			}
		for(int i=0;i<starList.size();i++) {
			starList.get(i).setTime(k*35);
			k++;
		}


	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public int getTime() {
		return time;
	}

}
