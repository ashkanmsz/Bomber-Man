package guilan.ac.ir.ashkan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
/**
 * this is the class for hozrizontal pop
 * @author ashkan
 *
 */
public class HorizontalPop {

	private static int x;
	private static int y;
	public static int n;
	public static boolean f = false;//this boolean show that we ate the star or not
	public int firstTime = 0;
	public static ArrayList<HorizontalPop> horiList = new ArrayList<>();
	private game game1;
	private static BufferedImage horizontalPopImage;
	private static BufferedImage horizontalPopImage2;
	private static BufferedImage img;

	public HorizontalPop(int y, int time) throws IOException {
		this.y = y;
		firstTime = time;
		String dir= System.getProperty("user.dir");
		horizontalPopImage = ImageIO.read(new File(dir+"/horizontalpop.png"));
	}

	public HorizontalPop(game game1) {
		this.game1 = game1;

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		

		if (game.getTime() - Star.radiousTime > 40) {//after 40 secounds the radious should be decrease
			f = false;
		}
		if (n / 153 > 3)//after eating 3 stars
			n = 459;

		if (f == false) {
			n = 0;
			setX(VerticalPop.getX() - 68);
			g2.drawImage(horizontalPopImage, x, y, null);

		} else {

			horizontalPopImage2 = resize(horizontalPopImage, horizontalPopImage.getWidth() + n,
					horizontalPopImage.getHeight());
			setX(VerticalPop.getX() - (n / 153) * 120);
			g2.drawImage(horizontalPopImage2, x, y, null);
		}

	}

	public Rectangle getBounds() {
		try {
			if (f == false) {
				img=horizontalPopImage;
				return new Rectangle(x, y, img.getWidth(), img.getHeight());
			}
			else {
				img=horizontalPopImage2;
				return new Rectangle(x, y, img.getWidth(), img.getHeight());
			}
			} catch (Exception e) {

		}
		return null;
	}

	public boolean enemyHorizontalPopCollision(int i, int j) {

		try {
			return Enemies.enemyList.get(i).getBounds().intersects(horiList.get(j).getBounds());
		} catch (Exception e) {

		}
		return (Boolean) null;
	}

	public void enemyHorizontalPopToch(int i, int j) {
		try {
			if (enemyHorizontalPopCollision(i, j)) {
				Enemies.enemyList.remove(i);
				Enemies.enemyMount--;
				if(Enemies.enemyMount==0)
					game.win=true;
			}
		} catch (Exception e) {

		}
	}

	public boolean stonesHorizontalPopCollision(int i, int j) {
		try {
		return Stones.stoneList.get(i).getBounds().intersects(horiList.get(j).getBounds());
		}catch (Exception e) {
		}
		return (Boolean) null;
	}

	public void stonesHorizontalPopToch(int i, int j) {
		try {
		if (stonesHorizontalPopCollision(i, j)) {
			Stones.stoneList.remove(i);
		}
		}catch (Exception e) {
			
		}
	}
	/**
	 * this function is for resizenig the horizontal pop
	 * @param img
	 * @param newW
	 * @param newH
	 * @return
	 */
	public static BufferedImage resize(BufferedImage img, int newW, int newH) {
		Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
		BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2d = dimg.createGraphics();
		g2d.drawImage(tmp, 0, 0, null);
		g2d.dispose();

		return dimg;
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static void setX(int x) {
		HorizontalPop.x = x;
	}

	public static void setY(int y) {
		HorizontalPop.y = y;
	}

	public static void setN(int i) {
		n += i;
	}

}
