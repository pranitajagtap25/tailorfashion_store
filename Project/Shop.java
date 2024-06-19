import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

public class Shop extends JFrame {
	private static final long serialVersionUID = 1L;
	
	ImageIcon format = null;

	public Shop() {
		setTitle("Tailor Fashion Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.Y_AXIS));
		panel2.setBackground(Color.lightGray);
		
		JLabel l7, l8, l9, l10, l11, l12, l13, l14, l15, img;// try creating arrays of Jlabel and JTextField
		JTextField code, price, oqty;

		int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
		int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
		JScrollPane jsp = new JScrollPane(panel2, v, h);
		jsp.setPreferredSize(new Dimension(600, 600));
		jsp.setBounds(10, 22, 940, 320);

		l7 = new JLabel("Tailor Fashion Store");
		l7.setFont(new Font("Serif", Font.BOLD, 18));
		l7.setBounds(280, 10, 230, 20);

		l8 = new JLabel("Item Code:");
		l8.setBounds(10, 36, 80, 20);
		code = new JTextField();
		code.setBounds(90, 36, 120, 20);
		code.setEditable(false);
		
		l9 = new JLabel("Img:");
		l9.setBounds(10, 63, 80, 20);
		img = new JLabel();
		img.setBounds(90, 63, 340, 120);
		
		l11 = new JLabel("Price:");
		l11.setBounds(10, 190, 80, 20);
		price = new JTextField();
		price.setBounds(90, 190, 120, 20);
		price.setEditable(false);

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root");

			Statement s2 = con.createStatement();
			ResultSet rs1 = s2.executeQuery("select * from products");

			while (rs1.next()) {
				code.setText("" + rs1.getInt("pid"));
				byte[] imagedata = rs1.getBytes("img");
				format = new ImageIcon(imagedata);
				Image mm = format.getImage();
				Image img2 = mm.getScaledInstance(160, 160, Image.SCALE_SMOOTH);
				ImageIcon image = new ImageIcon(img2);
				img.setIcon(image);
				price.setText("" + rs1.getInt("price"));
			}
			rs1.close();
			con.close();
			s2.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error in Connectivity");
		}

		l15 = new JLabel("Order Quntity:");
		l15.setBounds(10, 230, 120, 20);
		oqty = new JTextField();
		oqty.setBounds(110, 230, 120, 20);
		oqty.setEditable(true);
		//
		JButton AddToCartbtn = new JButton("ADD to Cart");
		AddToCartbtn.setBounds(250, 280, 120, 20);

		panel2.add(l7);
		panel2.add(l8);
		panel2.add(l9);
		panel2.add(l11);
		panel2.add(l15);
		panel2.add(code);
		panel2.add(img);
		panel2.add(price);
		panel2.add(oqty);
		panel2.add(AddToCartbtn);
		panel2.setLayout(null);

		/*****************************************************************************************
		 * Panel 3
		 *****************************************************************************************/
		JPanel panel3 = new JPanel();
		panel3.setBounds(10, 350, 940, 250);
		panel3.setBackground(Color.lightGray);
		JScrollPane scr2 = new JScrollPane();
		String column[] = { "Itemcode", "Price", "Qty. Ordered", "Amount" };
		JTable Items = new JTable();
		scr2.setBounds(30, 50, 880, 170);
		Items.setEnabled(false);
		DefaultTableModel dtm = new DefaultTableModel(0, 0);
		dtm.setColumnIdentifiers(column);
		Items.setModel(dtm);
		scr2.setViewportView(Items);

		JLabel l16 = new JLabel("CART");
		l16.setBounds(430, 10, 80, 20);
		l16.setFont(new Font("Serif", Font.BOLD, 16));
		panel3.add(scr2);
		panel3.add(l16);
		panel3.setLayout(null);

		/*****************************************************************************************
		 * Panel 4
		 *****************************************************************************************/
		JPanel panel4 = new JPanel();
		panel4.setBounds(10, 600, 940, 70);
		JLabel l22;
		JTextField finalamt;
		panel4.setBackground(Color.lightGray);
		l22 = new JLabel("TOTAL AMOUNT TO BE PAYED(Rs.) :");
		l22.setFont(new Font("Serif", Font.BOLD, 13));
		l22.setBounds(30, 20, 230, 20);
		finalamt = new JTextField();
		finalamt.setText("0");
		finalamt.setBounds(270, 20, 120, 20);
		finalamt.setEditable(false);
		JButton Buybtn = new JButton("BUY");
		Buybtn.setBounds(600, 20, 120, 20);
		JButton Exitbtn = new JButton("EXIT");
		Exitbtn.setBounds(800, 20, 120, 20);
		panel4.add(l22);
		panel4.add(Buybtn);
		panel4.add(Exitbtn);
		panel4.add(finalamt);
		panel4.setLayout(null);

		// ******************************************************AddToCartbtn****************************************/
		AddToCartbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int orderqty = Integer.parseInt(oqty.getText());
					int pri = Integer.parseInt(price.getText());

					Object[] QO1 = { code.getText(), price.getText(), oqty.getText(), pri * orderqty };
					dtm.addRow(QO1);
					int n = 0;
					int d;
					for (int m = 0; m < dtm.getRowCount(); m++) {
						d = (int) dtm.getValueAt(m, 3);
						n = n + Integer.parseInt("" + d);
					}
					finalamt.setText("" + n);
				} catch (Exception ee) {
					JOptionPane.showMessageDialog(null, "Please enter quantity");
				}
			}
		});

		Buybtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				int am = Integer.parseInt(finalamt.getText());
				if (am == 0) {
					JOptionPane.showMessageDialog(null, "No item in the cart");
				} else {
					int n8 = JOptionPane.showConfirmDialog(null,
							"You have ordered for a total of Rs. " + am + "\n Do you want to confirm the order?");
					if (n8 == 0) {
						JOptionPane.showMessageDialog(null, "Your order have been confirmed");
						int c = dtm.getRowCount();

						for (int m = 0; m < c; m++) {
							dtm.removeRow(0);
						}

						finalamt.setText("0");
					}
				}
			}
		});

		Exitbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ee) {
				int n = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit?");
				if (n == 0) {
					System.exit(0);
				}
			}
		});
		
		/*************************************************************************************
		 * Adding Panels to frame
		 *************************************************************************************/
		add(jsp);
		add(panel3);
		add(panel4);
		setSize(960, 800);// 400 width and 500 height
		setLocationRelativeTo(null);
		setLayout(null);// using no layout managers
		setVisible(true);// making the frame visible
	}

	public static void main(String[] args) {
		new Shop();
	}
}