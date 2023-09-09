package guilan.ac.ir.ashkan;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

/**
 * in this class we indicate the personal information of player
 * 
 * @author ashkan
 *
 */
public class Informations {

	private JPanel contentPane;
	private JFrame frame = new JFrame();
	private static String name;
	private static String password;
	private static String score;
	private static String looses;
	private static String wins;
	private static String path;
	private static String path2;
	private static boolean f = false;
	private static boolean h = false;
	private static Icon icn;
	private static ArrayList<String> list = new ArrayList<>();
	private static ArrayList<String> list2 = new ArrayList<>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Informations();
	}

	/**
	 * Create the frame.
	 */
	public Informations() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 541, 504);
		contentPane = new JPanel();
		contentPane.setFocusable(false);
		contentPane.setFont(new Font("Tahoma", Font.PLAIN, 19));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lblName.setBounds(15, 16, 235, 99);
		contentPane.add(lblName);
		lblName.setText(name);

		JLabel lblWins = new JLabel("wins");
		lblWins.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblWins.setBounds(35, 205, 75, 48);
		contentPane.add(lblWins);

		JLabel lblWinsmount = new JLabel("");
		lblWinsmount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblWinsmount.setBounds(167, 205, 83, 48);
		contentPane.add(lblWinsmount);
		lblWinsmount.setText(wins);

		JLabel lblScore = new JLabel("score");
		lblScore.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblScore.setBounds(35, 131, 75, 58);
		contentPane.add(lblScore);

		JLabel lblScoremount = new JLabel("\r\n");
		lblScoremount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblScoremount.setBounds(167, 131, 83, 58);
		contentPane.add(lblScoremount);
		lblScoremount.setText(score);

		JLabel lblLooses = new JLabel("looses");
		lblLooses.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLooses.setBounds(35, 269, 69, 48);
		contentPane.add(lblLooses);

		JLabel lblLoosesmount = new JLabel("");
		lblLoosesmount.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblLoosesmount.setBounds(167, 269, 69, 48);
		contentPane.add(lblLoosesmount);
		lblLoosesmount.setText(looses);

		JButton btnRanking = new JButton("Ranking");
		btnRanking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Ranking.main(null);
			}
		});
		btnRanking.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnRanking.setFocusable(false);
		btnRanking.setBackground(Color.WHITE);
		btnRanking.setBounds(347, 376, 139, 58);
		contentPane.add(btnRanking);

		JButton btnNext = new JButton("next");
		btnNext.setBackground(Color.WHITE);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				Menu.main(null);
			}
		});
		btnNext.setFocusable(false);
		btnNext.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnNext.setBounds(26, 374, 133, 58);
		contentPane.add(btnNext);

		JLabel lblPicture = new JLabel("");
		lblPicture.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPicture.setBounds(335, 86, 151, 186);
		contentPane.add(lblPicture);
		icn = new ImageIcon(path);
		lblPicture.setIcon(icn);

		JButton btnEdit = new JButton("edit");// in this section we edit the name or photo of player
		btnEdit.setBackground(Color.WHITE);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String read = "";
				String dir= System.getProperty("user.dir");
				File file = new File(dir+"/Profile.txt");

				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					

					while ((read = br.readLine()) != null) {
						list.add(read);
					}

					for (int i = 0; i < list.size(); i++) {

						if (list.get(i).startsWith(getName()))
							continue;
						list2.add(list.get(i));
					}

					System.out.println(list2 + " " + list2.size());

					PrintWriter writer = new PrintWriter("Profile.txt", "UTF-8");
					
					for (int i = 0; i < list2.size(); i++)
						writer.println(list2.get(i));

					br.close();
					writer.close();
				} catch (Exception e) {
					e.getMessage();
				}

				String name1 = nameGetter();// get the name from joption pane
				lblName.setText(name1);

				if (h == false)
					path2 = path;

				Informations obj = new Informations(name1, password, score, wins, looses, true, path2);
				frame.setVisible(false);
				Informations.main(null);
			}
		});
		btnEdit.setFocusable(false);
		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnEdit.setBounds(360, 30, 115, 40);
		contentPane.add(btnEdit);

		JButton btnBrowas = new JButton("browse");// this button is for choosing the picture
		btnBrowas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JFileChooser jf = new JFileChooser();
				jf.setFont(new Font("Simplified Arabic", Font.PLAIN, 21));
				int flag = jf.showOpenDialog(null);
				if (flag == JFileChooser.APPROVE_OPTION) {
					File file = jf.getSelectedFile();
					try {
						Image image;
						image = ImageIO.read(file);
						Image image1 = image.getScaledInstance(200, 300, image.SCALE_DEFAULT);
						icn = new ImageIcon(image1);
						lblPicture.setIcon(icn);
						path2 = file.getPath();
						h = true;

					} catch (Exception e) {
						e.getMessage();
					}
				}

			}
		});
		btnBrowas.setFocusable(false);
		btnBrowas.setBackground(Color.WHITE);
		btnBrowas.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnBrowas.setBounds(360, 289, 115, 40);
		contentPane.add(btnBrowas);
		frame.setLocationRelativeTo(null);

		frame.setVisible(true);
	}

	/**
	 * this is the main constructor of our class
	 * 
	 * @param name
	 * @param password
	 * @param score
	 * @param wins
	 * @param looses
	 * @param f
	 * @param path
	 */
	public Informations(String name, String password, String score, String wins, String looses, boolean f,
			String path) {
		Informations.name = name;
		Informations.password = password;
		Informations.score = score;
		Informations.wins = wins;
		Informations.looses = looses;
		Informations.f = f;
		Informations.path = path;

		if (f == true) {// this if for making difference between login and sign up button
			
			try {
				FileWriter fw = new FileWriter("Profile.txt", true);
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
			} catch (Exception e) {
				e.getMessage();
			}
		}

	}

	private String nameGetter() {
		return JOptionPane.showInputDialog(frame, "Choose a name:", "Name Selection", JOptionPane.PLAIN_MESSAGE);
	}

	public static String getName() {
		return name;
	}

	public static String getPassword() {
		return password;
	}

	public static String getScore() {
		return score;
	}

	public static String getLooses() {
		return looses;
	}

	public static String getWins() {
		return wins;
	}

}
