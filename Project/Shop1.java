import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Shop1 extends JFrame {
	private static final long serialVersionUID = 1L;
	String picPath = "C:\\Users\\Lenovo\\OneDrive\\Documents\\Pranita\\Product Images\\";
	
	JList<Product> list = new JList<Product>();
    DefaultListModel<Product> model = new DefaultListModel<>();

    final List<Product> cartProducts = new ArrayList<Product>();

	Product p;
	Product selected;

	public Shop1() {
		
		JPanel actions = new JPanel();
		actions.setBounds(0, 0, 900, 50);
		actions.setBackground(new Color(150, 150, 150));
		
		JButton newProductBtn = new JButton("New Product");
		newProductBtn.setBounds(0, 0, 110, 30);
		actions.add( newProductBtn );
		newProductBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new AddProduct();
			}
		});
		
		JButton viewCartBtn = new JButton("View Cart");
		viewCartBtn.setBounds(0, 0, 100, 30);
		actions.add( viewCartBtn );
		viewCartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new Cart(cartProducts);
			}
		});
		
		add(actions);
		
		JPanel products = new JPanel();
		
		list.setModel(model);
		
		List<Product> pList = getProducts();
		for(int i=0;i<pList.size();i++) {
			p = pList.get(i);
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
		JButton addToCartBtn;
		
		panel.setBackground(new Color(255,255,255));
		panel.setLayout(new GridLayout(5, 1, 10, 10));
		panel.setBorder( new EmptyBorder(10,10,10,10) );
		
		for(int i=0; i<pList.size(); i++) {
			final Integer pid = pList.get(i).getPid();
			p1 = new JPanel();
			
			icon = new ImageIcon( picPath + pList.get(i).getFileName() );
			image = icon.getImage();
			newimg = image.getScaledInstance(120, 120,  java.awt.Image.SCALE_SMOOTH);  
			icon = new ImageIcon(newimg);
			
			l1 = new JLabel("" ,icon, JLabel.CENTER);
			l1.setFont(l1.getFont().deriveFont(Font.ITALIC));
			p1.add(l1);
			
			addToCartBtn = new JButton("Add to cart");
			p1.add(addToCartBtn);
			
			panel.add(p1);
			
			addToCartBtn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent ee) {
					addProductToCart(pList.get(pid));
				}
			});
			
		}
		
		
		JScrollPane pane = new JScrollPane(panel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pane.setSize(900, 561);
		products.add(pane);
		
		products.setBounds(0, 50, 900, 561);
		products.setBackground(new Color(204, 204, 204));
		products.setLayout(new GridLayout(1, 1));
		add(products);
		
		setTitle("Tailor Fashion Store");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(900, 650);
		setLocationRelativeTo(null);
		setLayout(null);
		setVisible(true);
	}
	
	public List<Product> getProducts() {
		List<Product> list = new ArrayList<Product>();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root");
			Statement stmt = con.createStatement();
			ResultSet rst = stmt.executeQuery("select * from products1");
			while(rst.next()) {
				p = new Product(
					rst.getInt("pid"),
					rst.getString("fname"),
					rst.getInt("price"),
					rst.getInt("qty")
				);
				list.add(p);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return list;
	}
	
	public void addProductToCart( Product p ) {
		boolean bool=false;
		for(int i=0;i<cartProducts.size();i++) {
			if( cartProducts.get(i).getPid() == p.getPid() ) {
				int quantity = p.getQty() + cartProducts.get(i).getQty();
				int price = p.getPrice() + cartProducts.get(i).getPrice();
				
				p.setQty( quantity );
				p.setPrice( price );
				cartProducts.set( i, p );
				bool = true;
				break;
			}
		}
		if(!bool) {
			cartProducts.add(p);
		}
	}
	
	public static void main(String[] args) {
		new Shop1();
	}
	
}