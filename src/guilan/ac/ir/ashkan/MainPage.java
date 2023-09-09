package guilan.ac.ir.ashkan;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;

/**
 * this is the main page of our game
 * 
 * @author ashka
 *
 */
public class MainPage {
	private JPanel contentPane;
	private static JFrame frame = new JFrame();
	static Random input = new Random();
	private int width;
	private int height;
	private int randomStar;
	private static JLabel lblBombmount;
	private JLabel lblHeartmount;
	private JLabel lblTime;
	public static String time = "0";// this varriable is for getting the time
	static int interval;
	static Timer timer;

	/**
	 * Launch the application.
	 * 
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		new MainPage();
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */

	public MainPage() throws Exception {
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, Integer.parseInt(Menu.getFrameWidth()), Integer.parseInt(Menu.getFrameheigth()));
		contentPane = new JPanel();
		game game1 = new game(this);
		frame.addKeyListener(game1);
		game1.setBackground(new Color(173, 255, 47));
		game1.setBounds(231, 0, Integer.parseInt(Menu.getPanelWidth()), Integer.parseInt(Menu.getPanelHeigth()));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(game1);
		game1.setLayout(null);

		lblTime = new JLabel("");
		lblTime.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblTime.setBounds(86, 16, 84, 86);
		frame.getContentPane().add(lblTime);
		frame.setLocationRelativeTo(null);

		int delay = 1000;
		int period = 1000;
		timer = new Timer();
		interval = 0;
		lblTime.setText(String.valueOf(interval));

		JLabel lblBombs = new JLabel("Bombs");
		lblBombs.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBombs.setBounds(26, 164, 69, 28);
		frame.getContentPane().add(lblBombs);

		JLabel lblBombmount = new JLabel("");
		lblBombmount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblBombmount.setBounds(110, 160, 54, 37);
		frame.getContentPane().add(lblBombmount);

		JLabel lblHeart = new JLabel("Heart");
		lblHeart.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblHeart.setBounds(26, 237, 69, 20);
		frame.getContentPane().add(lblHeart);

		JLabel lblHeartmount = new JLabel("");
		lblHeartmount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblHeartmount.setBounds(110, 229, 54, 39);
		
		/**
		 * this function is for runnig the timer
		 */

		frame.getContentPane().add(lblHeartmount);
		timer.scheduleAtFixedRate(new TimerTask() {
			public void run() {
				lblTime.setText(String.valueOf(setInterval()));
				time = lblTime.getText();
				lblBombmount.setText(String.valueOf(Bomb.bombMount));
				lblHeartmount.setText(String.valueOf(Player.getHeart()));

			}
		}, delay, period);

		frame.setVisible(true);
	}

	public int setWidth(JPanel panel) {//this function return the random mount for y in other classes

		while (true) {

			width = (8 + walls.getIronWallImage().getWidth())
					+ input.nextInt((game.getPanel().getWidth() - 50) - (8 + walls.getIronWallImage().getWidth()));

			if (height != 50)
				break;

		}

		return width;
	}

	public int setHeight(JPanel panel) {//this function return the random mount for x in other classes

		while (true) {

			height = (4 + walls.getIronWallImage().getHeight())
					+ input.nextInt((game.getPanel().getHeight() - walls.getIronWallImage().getHeight() - 50)
							- (4 + walls.getIronWallImage().getHeight()));
			if (height != 50)
				break;

		}
		return height;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public static JFrame getFrame() {
		return frame;
	}

	private static final int setInterval() {

		return interval++;
	}
}
