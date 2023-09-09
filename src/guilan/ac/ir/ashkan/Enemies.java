package guilan.ac.ir.ashkan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemies {

	public int x;
	public int y;
	private int x1;
	private int y1 = 1;
	public static int enemyMount = Integer.parseInt(Menu.getEnemies());// this is the varriable of ennemies mount
	private boolean f = false;// this boolean is for toching the walls
	public boolean h = false;// this boolean is for toching the bombs
	private game game1;
	private static BufferedImage enemyImage;
	public static ArrayList<Enemies> enemyList = new ArrayList<>();
	Random input = new Random();

	public Enemies() {

	}

	public Enemies(game game1) throws IOException {
		this.game1 = game1;
		String dir= System.getProperty("user.dir");
		enemyImage = ImageIO.read(new File(dir+"\\enemies/spider.png"));
	}

	public void paint(Graphics g) {

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(enemyImage, x, y, null);

	}

	public void move() throws Exception {

		if (x + x1 >= game.getPanel().getWidth() - 50)// this if is for keeping the object in the frame
			x1 = -x1;
		if (x + x1 <= 8 + walls.getIronWallImage().getWidth())// this if is for keeping the object in the frame
			x1 = -x1;

		if (y + y1 >= game.getPanel().getHeight() - walls.getIronWallImage().getHeight() - 50)// this if is for keeping
																								// the object in the
																								// frame
			y1 = -y1;

		if (y + y1 <= 4 + walls.getIronWallImage().getHeight())// this if is for keeping the object in the frame
			y1 = -y1;

		if (h == true) {

			if (x1 == 1 || x1 == -1)
				x1 = -x1;

			if (y1 == 1 || y1 == -1)
				y1 = -y1;

			h = false;
		}

		if (f == true) {
			y += 1;
			y1 = 0;
			x1 = 1;
			f = false;
		}

		x += x1;
		y += y1;

	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, enemyImage.getWidth(), enemyImage.getHeight());
	}

	public void makingEnemies() {// in this function we make the enemies
		for (int i = 0; i < enemyMount; i++) {
			Enemies obj = new Enemies();
			enemyList.add(obj);
			enemyList.get(i).x = game.mainpage.setWidth(game.getPanel());
			enemyList.get(i).y = game.mainpage.setHeight(game.getPanel());
		}
	}

	public boolean enemyIronaWallcollision(int i, int j) {

		return walls.wallList[j].getBounds().intersects(enemyList.get(i).getBounds());

	}

	public void enemyIronWallToch(int i, int j) throws Exception {
		if (enemyIronaWallcollision(i, j))
			enemyList.get(i).f = true;
	}

	public boolean enemyStonescollision(int i, int j) {

		return Stones.stoneList.get(j).getBounds().intersects(enemyList.get(i).getBounds());

	}

	public void enemyStonesToch(int i, int j) throws Exception {
		if (enemyStonescollision(i, j)) {

			enemyList.get(i).f = true;
		}
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

	public void setX(int x) {
		this.x += x;
	}

}
