package guilan.ac.ir.ashkan;
/**
 * this class is for indicating the records
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Ranking  {

	private JPanel contentPane;
	private JFrame frame=new JFrame();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new Ranking();
	}

	/**
	 * Create the frame.
	 */
	public Ranking() {
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 427, 522);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(15, 16, 375, 370);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setFont(new Font("Arial", Font.PLAIN, 19));
		scrollPane.setViewportView(textArea);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				Informations.main(null);
			}
		});
		btnOk.setFocusable(false);
		btnOk.setBackground(Color.WHITE);
		btnOk.setBounds(139, 407, 125, 43);
		contentPane.add(btnOk);
		frame.setLocationRelativeTo(null);
		
		String st="";
		String dir= System.getProperty("user.dir");
		File file = new File(dir+"/Profile.txt");
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			while(st!=null) {
				st = br.readLine();
				if(st.contains("score"))
				textArea.append(st);
				textArea.append("\n");
				}
		
			br.close();

		} catch (Exception e) {
			e.getMessage();

		}
		
		
		
		
		
		
		
		
		frame.setVisible(true);
	}
}
