import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	Container c;
	JPanel Pnl_Panel1;
	JLabel lbl_Username, lbl_Id, lbl_Mobile, lbl_Mea, lbl_Shul, lbl_Len, lbl_Wai, lbl_Bust, lbl_Bot, lbl_Arm, lbl_title,
			lbl_Icon, lbl_icon2, lbl_image3, lbl_image4;
	JTextField txt_Username, txt_Mobile, txt_Mea, txt_Password, txt_Shul, txt_Len, txt_Wai, txt_Bust, txt_Bot, txt_Arm;
	JButton btn_Login, btn_Cancel;

	public Login() {
		c = getContentPane();
		Pnl_Panel1 = new JPanel();
		lbl_Id = new JLabel();
		lbl_Username = new JLabel();
		lbl_Mobile = new JLabel();
		lbl_Mea = new JLabel();
		lbl_Shul = new JLabel();
		lbl_Len = new JLabel();
		lbl_Wai = new JLabel();
		lbl_Bust = new JLabel();
		lbl_Bot = new JLabel();
		lbl_Arm = new JLabel();
		lbl_icon2 = new JLabel();
		lbl_image3 = new JLabel();
		lbl_image4 = new JLabel();
		txt_Password = new JTextField();
		txt_Username = new JTextField();
		txt_Mobile = new JTextField();
		txt_Shul = new JTextField();
		txt_Len = new JTextField();
		txt_Wai = new JTextField();
		txt_Bust = new JTextField();
		txt_Bot = new JTextField();
		txt_Arm = new JTextField();
		btn_Login = new JButton();
		btn_Cancel = new JButton();
		lbl_title = new JLabel();
		lbl_Icon = new JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		c.setLayout(null);

		Pnl_Panel1.setBorder(BorderFactory.createTitledBorder(""));
		Pnl_Panel1.setLayout(null);
		Pnl_Panel1.setBackground(new Color(149, 165, 166));

		lbl_image3.setIcon(new ImageIcon("Documents\\Pranita\\img\\thread.jpg"));
		lbl_image4.setIcon(new ImageIcon("Documents\\Pranita\\img\\thread.jpg"));
		lbl_image3.setBounds(10, 0, 3000, 350);
		lbl_image4.setBounds(500, 250, 700, 400);

		lbl_icon2.setIcon(new ImageIcon("Documents\\Pranita\\img\\thr.jpg"));
		lbl_icon2.setBounds(0, 0, 400, 500);

		lbl_Id.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Id.setText("Enter Username :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Id);
		lbl_Id.setBounds(30, 35, 130, 14);

		lbl_Username.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Username.setText("Enter Password :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Username);
		lbl_Username.setBounds(30, 60, 140, 20);

		lbl_Mobile.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Mobile.setText("Enter Mobile number :");
		// lbl_Password.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Mobile);
		lbl_Mobile.setBounds(30, 88, 150, 25);

		lbl_Mea.setFont(new java.awt.Font("Aerial", 0, 18));
		lbl_Mea.setText("Measurement :");
		// lbl_Password.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Mea);
		lbl_Mea.setBounds(10, 115, 170, 35);

		lbl_Shul.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Shul.setText("Shoulder :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Shul);
		lbl_Shul.setBounds(76, 151, 190, 40);

		lbl_Len.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Len.setText("Length :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Len);
		lbl_Len.setBounds(85, 180, 190, 40);

		lbl_Wai.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Wai.setText("Waist :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Wai);
		lbl_Wai.setBounds(95, 215, 190, 40);

		lbl_Bust.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Bust.setText("Bust :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Bust);
		lbl_Bust.setBounds(100, 248, 190, 40);

		lbl_Bot.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Bot.setText("Bottom Length :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Bot);
		lbl_Bot.setBounds(43, 280, 190, 40);

		lbl_Arm.setFont(new java.awt.Font("Leelawadee", 0, 13));
		lbl_Arm.setText("Arm :");
		// lbl_Username.setForeground(new Color(255,255,255));
		Pnl_Panel1.add(lbl_Arm);
		lbl_Arm.setBounds(100, 315, 190, 40);

		Pnl_Panel1.add(txt_Username);
		txt_Username.setBounds(160, 29, 130, 25);

		Pnl_Panel1.add(txt_Password);
		txt_Password.setBounds(160, 57, 130, 25);

		Pnl_Panel1.add(txt_Mobile);
		txt_Mobile.setBounds(160, 88, 130, 25);

		Pnl_Panel1.add(txt_Shul);
		txt_Shul.setBounds(160, 153, 130, 25);

		Pnl_Panel1.add(txt_Len);
		txt_Len.setBounds(160, 182, 130, 25);

		Pnl_Panel1.add(txt_Wai);
		txt_Wai.setBounds(160, 215, 130, 25);

		Pnl_Panel1.add(txt_Bust);
		txt_Bust.setBounds(160, 249, 130, 25);

		Pnl_Panel1.add(txt_Bot);
		txt_Bot.setBounds(160, 289, 130, 25);

		Pnl_Panel1.add(txt_Arm);
		txt_Arm.setBounds(160, 325, 130, 25);

		btn_Login.setText("Proceed");
		btn_Login.setIcon(new ImageIcon(""));
		Pnl_Panel1.add(btn_Login);
		btn_Login.setBounds(59, 400, 102, 32);

		btn_Cancel.setText("Cancel");
		btn_Cancel.setIcon(new ImageIcon(""));
		Pnl_Panel1.add(btn_Cancel);
		btn_Cancel.setBounds(200, 400, 100, 32);

		c.add(Pnl_Panel1);
		Pnl_Panel1.setBounds(270, 50, 400, 480);

		lbl_title.setFont(new java.awt.Font("Aerial", 0, 30));
		lbl_title.setText("Customer Details");
		c.add(lbl_title);
		lbl_title.setBounds(25, 25, 600, 35);
		lbl_title.setForeground(Color.white);

		lbl_Icon.setIcon(new ImageIcon(""));
		c.add(lbl_Icon);
		lbl_Icon.setBounds(0, 100, 250, 310);

		setSize(950, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);

		Pnl_Panel1.add(lbl_icon2);
		c.add(lbl_image3);
		c.add(lbl_image4);

		btn_Cancel.addActionListener(this);
		btn_Login.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		int cid=0;
		String id = null;
		String userName = txt_Username.getText();
		String password = txt_Password.getText();
		String mobile = txt_Mobile.getText();
		String shul = txt_Shul.getText();
		String len = txt_Len.getText();
		String wai = txt_Wai.getText();
		String bust = txt_Bust.getText();
		String bot = txt_Bot.getText();
		String arm = txt_Arm.getText();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root");

			PreparedStatement st,st1;
			ResultSet rst;
			
			st = connection.prepareStatement("select * from customer where name=? & password=?");
			st.setString(1, userName.trim());
			st.setString(2, password.trim());
			rst = st.executeQuery();
			if(rst.next()) {
				cid = rst.getInt("id");
				JOptionPane.showMessageDialog(this, "Logged in");
			} else {
				st1 = (PreparedStatement) connection
						.prepareStatement("insert into customer values(?,?,?,?,?,?,?,?,?,?)");
				st1.setString(1, id);
				st1.setString(2, userName);
				st1.setString(3, password);
				st1.setString(4, mobile);
				st1.setString(5, shul);
				st1.setString(6, len);
				st1.setString(7, wai);
				st1.setString(8, bust);
				st1.setString(9, bot);
				st1.setString(10, arm);
				st1.executeUpdate();
			}
			
			dispose();
			Shop1 sh1 = new Shop1();
			sh1.setVisible(true);
		} catch (SQLException e) {
			System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		new Login();
	}
}