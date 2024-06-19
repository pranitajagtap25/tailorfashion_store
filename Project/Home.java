import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

class Home extends JFrame {
	private static final long serialVersionUID = 0;
	JButton b1;
	JLabel l1;

	public Home() {
		setTitle("Tailor Fashion Store");
		setSize(900, 800);

		setLayout(new BorderLayout());
		JLabel background = new JLabel(new ImageIcon("C:/Users/Lenovo/OneDrive/Documents/Pranita/fbb.jpeg"));
		
		add(background);
		background.setLayout(new FlowLayout());

		l1 = new JLabel("Tailor Fashion Store");

		b1 = new JButton("Customer Details");
		b1.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				dispose();
				Login lp = new Login();
				lp.setVisible(true);
			}
			
		});

		l1 = new JLabel("Tailor Fashion Store", JLabel.LEFT);
		l1.setFont(new Font("Serif", Font.PLAIN, 50));
		l1.setForeground(Color.white);

		background.add(l1);
		background.add(b1);
		
		setSize(900, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	public static void main(String args[]) {
		new Home();
	}
}