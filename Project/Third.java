import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Third {

	public static void main(String[] args) {

		JButton button = new JButton("Button");

		JButton b = new JButton("Click Here");
		b.setBounds(50, 100, 95, 30);

		// Without JPanel images would be added to JFrame on top of each other.

		// That way only last image would be visible.

		JPanel panel = new JPanel();

		panel.add(new JLabel(new ImageIcon("Documents\\Pranita\\4.jpeg")));

		panel.add(new JLabel(new ImageIcon("Documents\\Pranita\\5.jpeg")));

		panel.add(new JLabel(new ImageIcon("Documents\\Pranita\\3.jpeg")));

		panel.add(new JLabel(new ImageIcon("Documents\\Pranita\\5.jpeg")));

		panel.add(new JLabel(new ImageIcon("Documents\\Pranita\\1.jpeg")));
		panel.add(new JLabel(new ImageIcon("Documents\\Pranita\\6.jpeg")));

		JFrame frame = new JFrame("Display multiple images from files.");

		frame.getContentPane().add(panel);

		frame.pack();

		frame.setVisible(true);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
