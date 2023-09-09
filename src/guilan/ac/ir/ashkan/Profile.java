package guilan.ac.ir.ashkan;
/**
 * Hi this is a lovely game for passing the secound term
 * 
 * @author ashkan
 * 
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.SystemColor;

public class Profile {

	private JPanel contentPane;
	private JFrame frame = new JFrame();
	private JTextField textField;
	private JPasswordField passwordField;
	private String name;
	private String score;
	private String wins;
	private String looses;
	private String password;
	private String path;
	private boolean f;
	private boolean h;
	private static Icon icn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Profile();
	}

	/**
	 * Create the frame.
	 */
	public Profile() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 450, 543);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaption);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField.setBackground(SystemColor.window);
		textField.setBounds(146, 294, 191, 26);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBackground(SystemColor.window);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		passwordField.setBounds(146, 336, 191, 26);
		contentPane.add(passwordField);

		JLabel lblUserName = new JLabel("user name");
		lblUserName.setForeground(SystemColor.menuText);
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblUserName.setBounds(36, 297, 116, 20);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("password");
		lblPassword.setForeground(SystemColor.desktop);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPassword.setBounds(36, 339, 95, 20);
		contentPane.add(lblPassword);

		JLabel label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBackground(Color.RED);
		label.setFont(new Font("Tahoma", Font.PLAIN, 19));
		label.setBounds(36, 378, 377, 20);
		contentPane.add(label);

		JButton btnLogin = new JButton("login");// with this button if we have accout from last play we can login
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				f = false;
				name = textField.getText();
				password = new String(passwordField.getPassword());

				/**
				 * in this part we get the informations from txt file
				 */
				
				String st = "";
				String dir= System.getProperty("user.dir");
				File file = new File(dir+"/Profile.txt");
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					while (st != null) {
						st = br.readLine();
						if (st.equals(name) && br.readLine().substring(name.length() + 11).equals(password)) {
							score = br.readLine().substring(name.length() + 8);
							wins = br.readLine().substring(name.length() + 7);
							looses = br.readLine().substring(name.length() + 9);
							path = br.readLine().substring(name.length() + 7);
							f = true;// this flag indicate that the password and name is correct to send them to
										// informations constructor
						}
					}
					br.close();
				} catch (Exception e) {
					e.getMessage();
				}
				if (f == true) {
					Informations obj = new Informations(name, password, score, wins, looses, false, path);
					frame.setVisible(false);
					Informations.main(null);
				} else
					label.setText("user name or password is incorrect");
			}
		});
		btnLogin.setBackground(SystemColor.window);
		btnLogin.setFocusable(false);
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnLogin.setBounds(248, 427, 107, 44);
		contentPane.add(btnLogin);

		JButton btnSignUp = new JButton("sign up");// with this button we can regestration for first time
		btnSignUp.setBackground(SystemColor.window);
		btnSignUp.setFocusable(false);
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				h = false;
				name = textField.getText();
				password = new String(passwordField.getPassword());
				String st = "";

				/**
				 * write our new contact informations in txt file
				 */
				String dir= System.getProperty("user.dir");
				File file = new File(dir+"/Profile.txt");
				try {
					BufferedReader br = new BufferedReader(new FileReader(file));
					while (true) {
						st = br.readLine();
						if (st.equals(name)) {
							label.setText("the account has already exist try annother ");
							h = true;
							break;
						}
					}
				} catch (Exception e) {
					e.getMessage();
				}
				if (h == false) {
					score = "0";
					wins = "0";
					looses = "0";
					Informations obj = new Informations(name, password, score, wins, looses, true, path);
					frame.setVisible(false);
					Informations.main(null);
				}
			}
		});
		btnSignUp.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnSignUp.setBounds(61, 427, 115, 44);
		contentPane.add(btnSignUp);

		JLabel lblPicture = new JLabel("");
		lblPicture.setBounds(97, 16, 212, 185);
		contentPane.add(lblPicture);

		JButton btnBrows = new JButton("Browse");
		btnBrows.addActionListener(new ActionListener() {
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
						path = file.getPath();

					} catch (Exception e) {
						e.getMessage();
					}
				}

			}
		});
		btnBrows.setBackground(Color.WHITE);
		btnBrows.setFocusable(false);
		btnBrows.setFont(new Font("Tahoma", Font.PLAIN, 19));
		btnBrows.setBounds(146, 217, 115, 47);
		contentPane.add(btnBrows);

		frame.setVisible(true);
	}

}
