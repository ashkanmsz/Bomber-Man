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
import javax.swing.JPanel;

public class walls {

	private static BufferedImage ironWallImage;
	private game game1;
	public int x;
	public int y;
	public static walls wallList[];//this is a array for keeping the walls

	public walls() {

	}
	public walls(game game) throws IOException {

		this.game1 = game;
		String dir= System.getProperty("user.dir");
		ironWallImage = ImageIO.read(new File(dir+"\\walls/40-40_BrickDarkGray.png"));

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(ironWallImage, x, y, null);

	}

	public static BufferedImage getIronWallImage() {
		return ironWallImage;
	}

	public  Rectangle getBounds() {

		return new Rectangle(x, y, ironWallImage.getWidth(), ironWallImage.getHeight());

	}
	public void makingWalls(walls list[]) {//in this function we add the walls in array
		int k = 0;
		for (int i = 120; i <= game.getPanel().getWidth() - 2 * walls.getIronWallImage().getWidth(); i += 120)
			for (int j = 120; j <= game.getPanel().getHeight() - 2 * walls.getIronWallImage().getHeight(); j += 120) {
				walls obj = new walls();
				list[k] = obj;
				list[k].x = i;
				list[k].y = j;
				k++;
			}
	}
	
	public void setWallList() {
		wallList = new walls[wallsMount(game.getPanel())];
	}
	
	public int wallsMount(JPanel panel) {//this function return the mount of walls 
		int a = 0;
		for (int i = 120; i <= panel.getWidth() - 2 * walls.getIronWallImage().getWidth(); i += 120)
			for (int j = 120; j <= panel.getHeight() - 2 * walls.getIronWallImage().getHeight(); j += 120)
				a++;
		return a;
	}


	

}
