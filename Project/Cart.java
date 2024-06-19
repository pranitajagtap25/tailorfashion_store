import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Cart extends JFrame {
	private static final long serialVersionUID = 1L;
	List<Product> products = new ArrayList<Product>();
	
	JList<Product> list = new JList<Product>();
    DefaultListModel<Product> model = new DefaultListModel<>();
	
    Product p, selected;
    String picPath = "C:\\Users\\Lenovo\\OneDrive\\Documents\\Pranita\\Product Images\\";

	public Cart(List<Product> products) {
		this.products = products;
		
		
		
		JPanel productsPanel = new JPanel();
		
		list.setModel(model);
		
		for(int i=0;i<products.size();i++) {
			p = products.get(i);
			model.addElement(p);
		}
		
		list.getSelectionModel().addListSelectionListener(e->{
			selected = list.getSelectedValue();
		});
		
		
		JPanel panel = new JPanel();
		JLabel l1;
		ImageIcon icon;
		JPanel p1;
		Image image, newimg;
		JButton removeProductBtn;
		
		panel.setBackground(new Color(255,255,255));
		panel.setLayout(new GridLayout(5, 1, 10, 10));
		panel.setBorder( new EmptyBorder(10,10,10,10) );
		
		for(int i=0; i<products.size(); i++) {
			final int pid = products.get(i).getPid();
			p1 = new JPanel();
			
			icon = new ImageIcon( picPath + products.get(i).getFileName() );
			image = icon.getImage();
			newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);  
			icon = new ImageIcon(newimg);
			
			l1 = new JLabel("" ,icon, JLabel.CENTER);
			l1.setFont(l1.getFont().deriveFont(Font.ITALIC));
			p1.add(l1);
			
			removeProductBtn = new JButton("remove");
			p1.add(removeProductBtn);
			
			removeProductBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ee) {
					for(int i=0;i<products.size();i++) {
						if( pid == products.get(i).getPid() ) {
							products.remove(i);
							dispose();
							new Cart(products);
						}
					}
				}
			});
			
			panel.add(p1);
			
			
		}
		
		
		JScrollPane pane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setSize(900, 561);
		productsPanel.add(pane);
		
		productsPanel.setBounds(0, 50, 900, 561);
		productsPanel.setBackground(new Color(204, 204, 204));
		productsPanel.setLayout(new GridLayout(1, 1));
		add(productsPanel);
		
		
		
		
		
		int totalAmt=0;
		
		for(int i=0;i<products.size();i++) {
			totalAmt += products.get(i).getPrice();
		}
		
		JPanel actions = new JPanel();
		actions.setBounds(0, 0, 900, 50);
		actions.setBackground(new Color(150, 150, 150));
		
		JLabel totalLabel = new JLabel("Total Amount: "+totalAmt);
		totalLabel.setBounds(0, 0, 110, 30);
		totalLabel.setForeground(Color.WHITE);
		actions.add( totalLabel );
		
		JButton buyBtn = new JButton("Buy");
		buyBtn.setBounds(0, 0, 110, 30);
		actions.add( buyBtn );
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int totalAmt=0;
				
				for(int i=0;i<products.size();i++) {
					totalAmt += products.get(i).getPrice();
				}
				
				if (totalAmt == 0) {
					JOptionPane.showMessageDialog(null, "No item in the cart");
				} else {
					int n8 = JOptionPane.showConfirmDialog(null,
							"You have ordered for a total of Rs. " + totalAmt + "\n Do you want to confirm the order?");
					System.out.println(n8);
					if ( n8==0 || n8==1 || n8==2 ) {
						dispose();
						new Shop1();
					}
				}
			}
		});
		
		JButton cancelBtn = new JButton("Cancel");
		cancelBtn.setBounds(0, 0, 100, 30);
		actions.add( cancelBtn );
		cancelBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Shop1();
			}
		});
		
		add(actions);
		
		
		
		setTitle("Your Cart");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 650);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new Cart(new ArrayList<Product>());
	}
	
}
