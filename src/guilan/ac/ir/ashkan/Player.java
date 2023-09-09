package guilan.ac.ir.ashkan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * this is the player class
 * @author ashkan
 *
 */
public class Player implements KeyListener {

	private game game1;
	private static int x = 50;
	private static int y = 50;
	private int x1 = 0;
	private int y1 = 0;
	private static int heart=2;
	private static int i = 0;
	private BufferedImage playerImage;
	private static boolean right;
	private static boolean left;
	private static boolean up;
	private static boolean down;
	String dir= System.getProperty("user.dir");
	private File Red_Back1 = new File(dir+"\\character/Red_Back1.png");
	private File Red_Back2 = new File(dir+"\\character/Red_Back2.png");
	private File Red_Back3 = new File(dir+"\\character/Red_Back3.png");
	private File Red_Front1 = new File(dir+"\\character/Red_Front1.png");
	private File Red_Front2 = new File(dir+"\\character/Red_Front2.png");
	private File Red_Front3 = new File(dir+"\\character/Red_Front3.png");
	private File Red_Left1 = new File(dir+"\\character/Red_Left1.png");
	private File Red_Left2 = new File(dir+"\\character/Red_Left2.png");
	private File Red_Left3 = new File(dir+"\\character/Red_Left3.png");
	private File Red_Right1 = new File(dir+"\\character/Red_Right1.png");
	private File Red_Right2 = new File(dir+"\\character/Red_Right2.png");
	private File Red_Right3 = new File(dir+"\\character/Red_Right3.png");

	public Player() {
	
	}
	
	public Player(game game1) throws IOException {
		this.game1 = game1;
		playerImage = ImageIO.read(Red_Back1);
	}

	public void paint(Graphics g) {
	
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(playerImage, x, y, null);	
	}

	public void move() {
		if (x + x1 > game.getPanel().getWidth() - 50)//this if is for keeping the object in the frame
			x1 = 0;
		if (y + y1 > game.getPanel().getHeight() - walls.getIronWallImage().getHeight() - 50)//this if is for keeping the object in the frame
			y1 = 0;
		if (x + x1 <= 8 + walls.getIronWallImage().getWidth())//this if is for keeping the object in the frame
			x1 = 0;
		if (y + y1 <= 4 + walls.getIronWallImage().getHeight())//this if is for keeping the object in the frame
			y1 = 0;
		x += x1;
		y += y1;
	}
	/**
	 * in this section we move our player
	 */
	public void keyPressed(KeyEvent e) {
		i++;
		try {
			if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
				x1 = 1;
				y1 = 0;
				if (i % 2 == 0)//for making the animation
					playerImage = ImageIO.read(Red_Right2);
				else
					playerImage = ImageIO.read(Red_Right3);
				right = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT) {
				x1 = -1;
				y1 = 0;
				if (i % 2 == 0)
					playerImage = ImageIO.read(Red_Left2);
				else
					playerImage = ImageIO.read(Red_Left3);
				left = true;
			}

			if (e.getKeyCode() == KeyEvent.VK_UP) {
				y1 = -1;
				x1 = 0;
				if (i % 2 == 0)
					playerImage = ImageIO.read(Red_Back2);
				else
					playerImage = ImageIO.read(Red_Back3);
				up = true;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				y1 = 1;
				x1 = 0;
				if (i % 2 == 0)
					playerImage = ImageIO.read(Red_Front2);
				else
					playerImage = ImageIO.read(Red_Front3);

				down = true;
			}
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	/**
	* for releasing the key board
	*/
	public void keyReleased(KeyEvent arg0) {

		try {
			if (right == true) {
				playerImage = ImageIO.read(Red_Right1);
				right = false;
			}
			if (left == true) {
				playerImage = ImageIO.read(Red_Left1);
				left = false;
			}
			if (up == true) {
				playerImage = ImageIO.read(Red_Back1);
				up = false;
			}
			if (down == true) {
				playerImage = ImageIO.read(Red_Front1);
				down = false;
			}
			x1 = 0;
			y1 = 0;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public Rectangle getBounds() {//this function return the bounds of our object

		return new Rectangle(x, y, playerImage.getWidth(), playerImage.getHeight());
	}
	
	public boolean playerIronWallCollision(int i) {

		return walls.wallList[i].getBounds().intersects(getBounds());

	}
	
	public void playerIronWallToch(int i) {//this function is for toching between player and iron wall
		if (playerIronWallCollision(i)) {
			x1=-x1;
			y1=-y1;
		}
	}
	public boolean enemyPlayerCollision(int i) {

		return Enemies.enemyList.get(i).getBounds().intersects(getBounds());

	}
	
	public void enemyPlayerToch(int i) {//this function is for toching between enemy and player
		
		if(enemyPlayerCollision(i)) {
			
			Enemies.enemyList.remove(i);
			Enemies.enemyMount--;
			heart=(int)heart/2;
			if(heart==0)
				game.loose=true;
			if(Enemies.enemyMount==0)
				game.win=true;
		}
			
	}
	
	
	public boolean playerStarCollision(int i) {
		
		
	    
		return Star.starList.get(i).getBounds().intersects(getBounds());

	}
	
	public void playerStarToch(int i) {
		
		if (playerStarCollision(i)) {
			
			Star.radiousTime=game.getTime();
			game.eatStar=true;
			HorizontalPop.f=true;
			VerticalPop.f=true;
			HorizontalPop.setN(153);
			VerticalPop.setN(149);
			
		}
	}
	
	public boolean playeVerticalPopCollision(int i) throws Exception {
		try {
		return VerticalPop.veriList.get(i).getBounds().intersects(getBounds());
		}catch(Exception e) {
			
		}
		return (Boolean) null;
	}
	
	public void playerVerticalPopToch(int i) throws Exception {
		try {
		
		if(playeVerticalPopCollision(i)) {
			heart=(int)heart/2;
			if(heart==0)
				game.loose=true;
		}
		} catch (Exception e) {
			
		}
	}
	
	public boolean playerHorizontalPopCollision(int i) {
		try {
		return HorizontalPop.horiList.get(i).getBounds().intersects(getBounds());
		}catch (Exception e) {
			
		}
		return (Boolean) null;
	}
	
	public void playerHorizontalPopToch(int i) {
		try {
		if(playerHorizontalPopCollision(i)) {
			heart=(int)heart/2;
			if(heart==0)
				game.loose=true;
		}
		}catch (Exception e) {
			
		}
	}
	
	public boolean playerStonesCollision(int i) {

		return Stones.stoneList.get(i).getBounds().intersects(getBounds());

	}
	
	public void playerStonesToch(int i) {
		if (playerStonesCollision(i)) {
			x1=-x1;
			y1=-y1;
		}
	}
	
	public boolean playerHeartCollision(int i) {

		return Heart.heartList.get(i).getBounds().intersects(getBounds());

	}
	
	public void playerHeartToch(int i) {
		
		if (playerHeartCollision(i)) {
			game.eatHeart=true;
			heart=2;
			}
	}
	
	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

	public static int getHeart() {
		return heart;
	}

	public static void setHeart(int heart) {
		Player.heart = heart;
	}
	
	
	
}
