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
 * this class is for vertical pop
 * 
 * @author ashkan
 *
 */
public class VerticalPop {

	private static int x;
	private static int y;
	public static int n;// the mount that if we eat star plus to our vertical radious
	private game game1;
	private static BufferedImage verticalPopImage;
	private static BufferedImage verticalPopImage2;
	private static BufferedImage img;
	public static boolean f = false;// this boolean indicate that we eat the star or not
	public int firstTime = 0;// the time that the radoius shown
	public static ArrayList<VerticalPop> veriList = new ArrayList<>();

	public VerticalPop(int x, int time) throws IOException {
		this.x = x;
		firstTime = time;
		String dir= System.getProperty("user.dir");
		verticalPopImage = ImageIO.read(new File(dir+"/verticalpop.png"));

	}

	public VerticalPop(game game1) {
		this.game1 = game1;

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		if (game.getTime() - Star.radiousTime > 40) {// after 40 secounds the radious will be decrease
			f = false;
		}

		if (n / 149 > 3)// after eating three stars
			n = 447;

		if (f == false) {

			n = 0;
			setY(HorizontalPop.getY() - 60);// setting the y
			g2.drawImage(verticalPopImage, x, y, null);

		} else {
			verticalPopImage2 = resize(verticalPopImage, verticalPopImage.getWidth(), verticalPopImage.getHeight() + n);
			setY(HorizontalPop.getY() - (n / 149) * 120);// setting the y
			g2.drawImage(verticalPopImage2, x, y, null);
		}

	}

	public Rectangle getBounds() {

		try {
			if (f == false) {// if we didn't eat the star
				img = verticalPopImage;
				return new Rectangle(x, y, img.getWidth(), img.getHeight());
			} else {// if we ate the star
				img = verticalPopImage2;
				return new Rectangle(x, y, img.getWidth(), img.getHeight());
			}
		} catch (Exception e) {

		}
		return null;
	}

	public boolean enemyVerticalPopCollision(int i, int j) {
		try {
			return Enemies.enemyList.get(i).getBounds().intersects(veriList.get(j).getBounds());
		} catch (Exception e) {

		}
		return (Boolean) null;
	}

	public void enemyVerticalPopToch(int i, int j) {
		try {
			if (enemyVerticalPopCollision(i, j)) {
				Enemies.enemyList.remove(i);
				Enemies.enemyMount--;
				if (Enemies.enemyMount == 0)
					game.win = true;
			}
		} catch (Exception e) {

		}
	}

	public boolean stonesVerticalPopCollision(int i, int j) {
		try {
			return Stones.stoneList.get(i).getBounds().intersects(veriList.get(j).getBounds());
		} catch (Exception e) {

		}
		return (Boolean) null;
	}

	public void stonesVerticalPopToch(int i, int j) {
		try {
			if (stonesVerticalPopCollision(i, j)) {
				Stones.stoneList.remove(i);
			}
		} catch (Exception e) {

		}
	}

	/**
	 * this function is for resizing the radious
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
		VerticalPop.x = x;
	}

	public static void setY(int y) {
		VerticalPop.y = y;
	}

	public static void setN(int i) {
		n += i;

	}

}
