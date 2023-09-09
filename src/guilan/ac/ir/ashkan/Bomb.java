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
import java.util.ArrayList;

import javax.imageio.ImageIO;
/**
 * thisclass is for making the bombs
 * @author ashkan
 *
 */
public class Bomb implements KeyListener {

	private static int x = 0;
	private static int y = 0;
	public  int firstTime=0;//the time of bomb creation
	public static int time;//this is a empty varriable for sync the time of bombs and pops
	private game game1;
	private static BufferedImage bombImage;
	public static ArrayList<Bomb> bombList = new ArrayList<>();
	public static int bombMount = 400;

	public Bomb(int time,int x,int y) {
		firstTime=time;
		Bomb.x=x;
		Bomb.y=y;
	}

	public Bomb(game game1) throws IOException {
		this.game1 = game1;
		String dir= System.getProperty("user.dir");
		bombImage = ImageIO.read(new File(dir+"/bomb.png"));

	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.drawImage(bombImage, x, y, null);

	}

	public Rectangle getBounds() {

		return new Rectangle(x, y, bombImage.getWidth(), bombImage.getHeight());
	}

	public boolean enemyBombcollision(int i,int j) {

		return bombList.get(i).getBounds().intersects(Enemies.enemyList.get(j).getBounds());

	}

	public void enemyBombToch(int i,int j) {//this function is for toching the enemy and bombs
		if (enemyBombcollision(i,j)) {
			Enemies.enemyList.get(j).h = true;
		}
	}
	/**
	 * if the button pressed
	 */

	public void keyPressed(KeyEvent e) {

		try {
			if (e.getKeyCode() == KeyEvent.VK_SPACE && bombMount > 0) {
				
				time=game.getTime();

				Bomb bombObj=new Bomb(time,Player.getX(),Player.getY());//making the bomb object
				bombList.add(bombObj);
				
				VerticalPop veriObj=new VerticalPop(getX(),time);//making the vertical pop object
				HorizontalPop horiObj=new HorizontalPop(getY(),time);//making the horizontal object
				
				VerticalPop.veriList.add(veriObj);
				HorizontalPop.horiList.add(horiObj);
				
				bombMount--;
				
					
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}

	public int getBombFirstTime() {
		return firstTime;
	}

	public void setBombFirstTime(int bombFirstTime) {
		this.firstTime = bombFirstTime;
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	public void setX(int x) {
		Bomb.x = x;
	}

	public void setY(int y) {
		Bomb.y = y;
	}

	public static int getX() {
		return x;
	}

	public static int getY() {
		return y;
	}

}
