package guilan.ac.ir.ashkan;
/**
 * in this class we determine the siz of frame and panel
 */
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Menu {

	private JPanel contentPane;
	private JFrame frame = new JFrame();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;

	private static String FrameWidth;
	private static String Frameheigth;
	private static String PanelWidth;
	private static String PanelHeigth;
	private static String enemies;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Menu();
	}

	/**
	 * Create the frame.
	 */
	public Menu() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 683, 508);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		frame.setLocationRelativeTo(null);

		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField.setBounds(177, 142, 95, 34);
		contentPane.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_1.setBounds(177, 235, 95, 34);
		contentPane.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_2.setBounds(177, 346, 95, 34);
		contentPane.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblEnemies = new JLabel("enemies");
		lblEnemies.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblEnemies.setBounds(30, 349, 82, 26);
		contentPane.add(lblEnemies);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_3.setBounds(177, 82, 95, 34);
		contentPane.add(textField_3);
		textField_3.setColumns(10);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("Tahoma", Font.PLAIN, 19));
		textField_4.setBounds(177, 24, 95, 38);
		contentPane.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblFrameWidth = new JLabel("Frame Width");
		lblFrameWidth.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblFrameWidth.setBounds(30, 144, 118, 29);
		contentPane.add(lblFrameWidth);

		JLabel lblFrameHeigth = new JLabel("Frame heigth");
		lblFrameHeigth.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblFrameHeigth.setBounds(30, 238, 126, 26);
		contentPane.add(lblFrameHeigth);

		JLabel lblPanelWidth = new JLabel("Panel Width");
		lblPanelWidth.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPanelWidth.setBounds(30, 28, 118, 29);
		contentPane.add(lblPanelWidth);

		JLabel lblPanelHeigth = new JLabel("Panel Heigth");
		lblPanelHeigth.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblPanelHeigth.setBounds(30, 85, 118, 27);
		contentPane.add(lblPanelHeigth);

		JButton btnPlay = new JButton("Play !");//this button send us to next page with size informaation
		btnPlay.setBackground(Color.WHITE);
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				PanelWidth = textField_4.getText();
				PanelHeigth = textField_3.getText();
				Frameheigth = textField_1.getText();
				FrameWidth = textField.getText();
				enemies = textField_2.getText();

				frame.setVisible(false);
				try {
					MainPage.main(null);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		btnPlay.setFocusable(false);
		btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 19));
	btnPlay.setBounds(503, 392, 135, 44);
		contentPane.add(btnPlay);
		
		JLabel lblNewLabel = new JLabel("( the difference must be greatar than 250 )");
		lblNewLabel.setForeground(Color.RED);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblNewLabel.setBackground(Color.RED);
		lblNewLabel.setBounds(27, 192, 376, 23);
		contentPane.add(lblNewLabel);
		
		JLabel lblTheDifference = new JLabel("( the difference must be greatar than 57 )");
		lblTheDifference.setForeground(Color.RED);
		lblTheDifference.setFont(new Font("Tahoma", Font.PLAIN, 19));
		lblTheDifference.setBounds(30, 284, 378, 32);
		contentPane.add(lblTheDifference);

		frame.setVisible(true);
	}

	public static String getFrameWidth() {
		return FrameWidth;
	}

	public static String getFrameheigth() {
		return Frameheigth;
	}

	public static String getPanelWidth() {
		return PanelWidth;
	}

	public static String getPanelHeigth() {
		return PanelHeigth;
	}

	public static String getEnemies() {
		return enemies;
	}
}
