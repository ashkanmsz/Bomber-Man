package guilan.ac.ir.ashkan;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * this class is the main handler of our game that handle the events
 * 
 * @author ashkan
 *
 */
public class game extends JPanel implements Runnable, KeyListener {

	private static String name;
	private static int score;
	private static int wins;
	private static int looses;
	private static String password;
	private static String path;
	public static int k = 1;
	public static int random;

	private static boolean f = false;// this boolean is for run once
	private static boolean h = false;// this boolean is for run once
	public static boolean loose = false;// this boolean is for loosing
	public static boolean win = false;// this boolean is for wining the game
	public static boolean enfejar = false;// this boolean shows the pop
	public static boolean eatHeart = false;// this boolean is for indicate to show the heart
	public static boolean eatStar = false;// this is boolean for ruunung once the random time

	private Thread t;
	private static JPanel panel;
	static Random input = new Random();
	public static MainPage mainpage;
	/**
	 * our objects
	 */
	Player playerObject = new Player(this);
	walls wallObject = new walls(this);
	Enemies enemyObject = new Enemies(this);
	Stones stoneObject = new Stones(this);
	Bomb bombObject = new Bomb(this);
	VerticalPop verticalPopObject = new VerticalPop(this);
	HorizontalPop horizontalPopObject = new HorizontalPop(this);
	Star starObject = new Star(this);
	Heart heartObject = new Heart(this);

	public void addNotify() {
		super.addNotify();
		t = new Thread(this);
		t.start();
	}

	public game(MainPage mainpage) throws Exception {
		game.panel = this;
		game.mainpage = mainpage;

		String read = "";
		String dir= System.getProperty("user.dir");
		File file = new File(dir+"/Profile.txt");

		/**
		 * in this section we get the information of player
		 */
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while (read != null) {
				read = br.readLine();
				if (read.equals(Informations.getName())) {
					name = read;
					password = br.readLine().substring(Informations.getName().length() + 11);
					score = Integer.parseInt(br.readLine().substring(Informations.getName().length() + 8));
					wins = Integer.parseInt(br.readLine().substring(Informations.getName().length() + 7));
					looses = Integer.parseInt(br.readLine().substring(Informations.getName().length() + 9));
					path = br.readLine().substring(name.length() + 7);
				}
			}
			br.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	/**
	 * this function is for painting the whole of thing in main page
	 */
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		/**
		 * these for loops is for painting the iron walls
		 */
		for (int i = 0; i < panel.getWidth() - walls.getIronWallImage().getWidth(); i += 50)
			g2.drawImage(walls.getIronWallImage(), i, 0, null);

		for (int i = 0; i < panel.getHeight() - 2 * walls.getIronWallImage().getHeight(); i += 50)
			g2.drawImage(walls.getIronWallImage(), 0, i, null);

		for (int i = 0; i < panel.getWidth() - walls.getIronWallImage().getWidth(); i += 50)
			g2.drawImage(walls.getIronWallImage(), i, panel.getHeight() - walls.getIronWallImage().getHeight(), null);

		for (int i = 0; i < Enemies.enemyMount; i++)// painting the enemies
			Enemies.enemyList.get(i).paint(g2);

		for (int i = 0; i < walls.wallList.length; i++)// painting the iron walls
			walls.wallList[i].paint(g2);

		playerObject.paint(g2);// painting the player

		for (int i = 0; i < Stones.stoneList.size(); i++)// painting the stones
			Stones.stoneList.get(i).paint(g2);

		for (int i = 0; i < Bomb.bombList.size(); i++) {// painting the bobms
			Bomb.bombList.get(i).paint(g2);

			if (getTime() - Bomb.bombList.get(i).firstTime == 4) {// this if showing the time of poping

				Bomb.bombList.remove(i);
				enfejar = true;
			}
		}
		if (enfejar == true) {

			for (int i = 0; i < VerticalPop.veriList.size(); i++) {// after removing bomb the poping should be run

				VerticalPop.veriList.get(i).paint(g2);
				HorizontalPop.horiList.get(i).paint(g2);

				if (getTime() - VerticalPop.veriList.get(i).firstTime == 5) {// the time of removing pops
					VerticalPop.veriList.remove(i);
					HorizontalPop.horiList.remove(i);
					enfejar = false;
				}
			}
		}

		for (int i = 0; i < Heart.heartList.size(); i++) {// this loop is for painting the heart

			if (getTime() == Heart.heartList.get(i).getTime())

				Heart.heartList.get(i).f = true;

			if (Heart.heartList.get(i).f == true) {

				Heart.heartList.get(i).paint(g2);

				if (getTime() - Heart.heartList.get(i).getTime() == 20 || eatHeart == true) {// after 20 secounds
					Heart.heartList.remove(i);
					eatHeart = false;
				}
			}
		}

		for (int i = 0; i < Star.starList.size(); i++) {

			if (getTime() == Star.starList.get(i).getTime())

				Star.starList.get(i).f = true;

			if (Star.starList.get(i).f == true) {
				Star.starList.get(i).paint(g2);

				if (getTime() - Star.starList.get(i).getTime() == 10 || eatStar == true) {
					Star.starList.remove(i);
					eatStar = false;
				}
			}

		}

		
	}

	public void run() {

		while (loose == false && win == false) {
			try {
				if (f == false) {
					wallObject.setWallList();
					wallObject.makingWalls(walls.wallList);
					stoneObject.setStones();
					enemyObject.makingEnemies();
					starObject.setStars();
					heartObject.setHeart();
					f = true;
				}
				repaint();
				move();
				Thread.sleep(6);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (win == true) {

			JOptionPane.showMessageDialog(MainPage.getFrame(), "you win the game");
			wins++;
			int e = Integer.parseInt(Menu.getEnemies());
			int soorat = (int) (Math.pow(e, 4));
			int makhraj = getTime() + (int) (Math.log(Bomb.bombMount) / Math.log(2));
			score = soorat / makhraj;

		}
		if (loose == true) {
			JOptionPane.showMessageDialog(MainPage.getFrame(), "you loose the game");
			looses++;
			int e = Integer.parseInt(Menu.getEnemies());
			int soorat = (int) (Math.pow(e, 1));
			int makhraj = getTime() + (int) (Math.log(Bomb.bombMount) / Math.log(2));
			score = soorat / makhraj;

		}

		try {
			String dir= System.getProperty("user.dir");
			FileWriter fw = new FileWriter(dir+"/Profile.txt", true);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter out = new PrintWriter(bw);
			out.println(name);
			out.println(name + "password : " + password);
			out.println(name + "score : " + score);
			out.println(name + "wins : " + wins);
			out.println(name + "looses : " + looses);
			out.println(name + "path : " + path);
			out.println("*************");
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		MainPage.getFrame().setVisible(false);
	}

	public void move() throws Exception {

		for (int i = 0; i < walls.wallList.length; i++)
			playerObject.playerIronWallToch(i);

		for (int i = 0; i < Star.starList.size(); i++)
			if (Star.starList.get(i).f == true)
				playerObject.playerStarToch(i);

		for (int i = 0; i < Enemies.enemyMount; i++)
			playerObject.enemyPlayerToch(i);

		for (int i = 0; i < Enemies.enemyMount; i++)
			for (int j = 0; j < walls.wallList.length; j++)
				enemyObject.enemyIronWallToch(i, j);

		for (int j = 0; j < Enemies.enemyMount; j++)
			for (int i = 0; i < Bomb.bombList.size(); i++)
				bombObject.enemyBombToch(i, j);

		for (int i = 0; i < Heart.heartList.size(); i++)
			if (Heart.heartList.get(i).f == true)
				playerObject.playerHeartToch(i);

		if (enfejar == true) {

			for (int i = 0; i < Enemies.enemyMount; i++)
				for (int j = 0; j < VerticalPop.veriList.size(); j++)
					try {
						verticalPopObject.enemyVerticalPopToch(i, j);
					} catch (Exception e) {
					}

			for (int i = 0; i < Enemies.enemyMount; i++)
				for (int j = 0; j < HorizontalPop.horiList.size(); j++)
					try {
						horizontalPopObject.enemyHorizontalPopToch(i, j);
					} catch (Exception e) {
					}

			for (int j = 0; j < VerticalPop.veriList.size(); j++)
				try {
					playerObject.playerVerticalPopToch(j);
				} catch (Exception e) {

				}

			for (int j = 0; j < HorizontalPop.horiList.size(); j++)
				try {
					playerObject.playerHorizontalPopToch(j);
				} catch (Exception e) {

				}
			for (int i = 0; i < Stones.stoneList.size(); i++)
				for (int j = 0; j < VerticalPop.veriList.size(); j++)
					try {
						verticalPopObject.stonesVerticalPopToch(i, j);
					} catch (Exception e) {

					}

			for (int i = 0; i < Stones.stoneList.size(); i++)
				for (int j = 0; j < HorizontalPop.horiList.size(); j++)
					try {
						horizontalPopObject.stonesHorizontalPopToch(i, j);
					} catch (Exception e) {

					}
		}

		for (int j = 0; j < Stones.stoneList.size(); j++)
			for (int i = 0; i < Enemies.enemyList.size(); i++)
				enemyObject.enemyStonesToch(i, j);

		for (int j = 0; j < Stones.stoneList.size(); j++)
			playerObject.playerStonesToch(j);

		for (int i = 0; i < Enemies.enemyMount; i++) {
			Enemies.enemyList.get(i).move();
		}

		playerObject.move();

	}

	/**
	 * this section is for using from key board
	 */

	public void keyPressed(KeyEvent e) {
		playerObject.keyPressed(e);
		bombObject.keyPressed(e);

	}

	public void keyReleased(KeyEvent e) {
		playerObject.keyReleased(e);

	}

	public void keyTyped(KeyEvent e) {

	}

	public static int getTime() {// get the time from this function
		int time = Integer.parseInt(MainPage.time);
		return time;
	}

	public static JPanel getPanel() {
		return panel;
	}

}
