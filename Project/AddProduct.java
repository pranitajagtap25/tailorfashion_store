import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class AddProduct extends JFrame {
	String picPath = "C:\\Users\\Lenovo\\OneDrive\\Documents\\Pranita\\Product Images\\";
	String fileName = "";

	private static final long serialVersionUID = 1L;

	public AddProduct() {
		
		JFrame frame = new JFrame("Add Product");
		JPanel r1, r2, r3, r4, r5;
		JLabel l1, l2, l3, l4;
		JTextField f1, f2, f3;
		JButton b1,sb;

		frame.setLayout(null);

		r1 = new JPanel();
		r1.setBounds(0, 20, 300, 50);
		l1 = new JLabel("Item Code: ");
		r1.add(l1);
		f1 = new JTextField(10);
		f1.setBounds(0, 36, 120, 20);
		r1.add(f1);

		r2 = new JPanel();
		r2.setBounds(0, 70, 300, 50);
		l2 = new JLabel("Price: ");
		r2.add(l2);
		f2 = new JTextField(10);
		f2.setBounds(0, 36, 120, 20);
		r2.add(f2);

		r3 = new JPanel();
		r3.setBounds(0, 120, 300, 50);
		l3 = new JLabel("Quantity: ");
		r3.add(l3);
		f3 = new JTextField(10);
		f3.setBounds(0, 36, 120, 20);
		r3.add(f3);

		r4 = new JPanel();
		r4.setBounds(0, 170, 300, 50);
		l4 = new JLabel("Image: ");
		r4.add(l4);
		b1 = new JButton("Select File");
		b1.setBounds(0, 36, 120, 20);
		r4.add(b1);
		
		r5 = new JPanel();
		r5.setBounds(0, 220, 300, 50);
		sb = new JButton("Add Product");
		sb.setBounds(0, 36, 120, 20);
		r5.add(sb);
		
		b1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new ImageFilter());
				fc.setAcceptAllFileFilterUsed(false);

				int option = fc.showOpenDialog(frame);
				if (option == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					final byte[] bytes = new byte[1024];
					String path = picPath + file.getName();
                    try {
                    	File image = new File(path);
                    	
                        if( image.exists() ) {
                            image.delete();
                        }
                        if( image.createNewFile() ) {
                            OutputStream os = new FileOutputStream(new File(path));
                            InputStream is = new FileInputStream(file);
                            int read=0;
                            while ((read = is.read(bytes)) != -1) {
                                os.write(bytes, 0, read);
                            }
                            fileName = file.getName();
                            is.close();
                            os.close();
                            JOptionPane.showMessageDialog( frame, "File uploaded successfully!");
                        } else {
                            JOptionPane.showMessageDialog( frame, "Failed to upload file!");
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
				} else {
					JOptionPane.showMessageDialog(null, "Open command canceled");
				}
			}
		});
		
		sb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				}
				try {
					Connection con = DriverManager.getConnection("jdbc:mysql://localhost/jdbc", "root", "root");
					PreparedStatement pstmt = con.prepareStatement("insert into products1(pid,fname,price,qty) values(?,?,?,?)");
					pstmt.setString(1,f1.getText());
					pstmt.setString(2,fileName);
					pstmt.setString(3,f2.getText());
					pstmt.setString(4,f3.getText());
					int i = pstmt.executeUpdate();
					if( i==1 ) {
						JOptionPane.showMessageDialog(frame, "Product Added Successfully!");
						dispose();
						new Shop1();
					} else {
						JOptionPane.showMessageDialog(frame, "Failed to add product!");
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
				
			}
		});

		frame.add(r1);
		frame.add(r2);
		frame.add(r3);
		frame.add(r4);
		frame.add(r5);
		frame.setTitle("Add Product");
		frame.setSize(300, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

	}

	public static void main(String[] args) {
		new AddProduct();
	}

}

class ImageFilter extends FileFilter {
	public final static String JPEG = "jpeg";
	public final static String JPG = "jpg";
	public final static String GIF = "gif";
	public final static String TIFF = "tiff";
	public final static String TIF = "tif";
	public final static String PNG = "png";

	@Override
	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = getExtension(f);
		if (extension != null) {
			if (extension.equals(TIFF) || extension.equals(TIF) || extension.equals(GIF) || extension.equals(JPEG)
					|| extension.equals(JPG) || extension.equals(PNG)) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}

	@Override
	public String getDescription() {
		return "Image Only";
	}

	String getExtension(File f) {
		String ext = null;
		String s = f.getName();
		int i = s.lastIndexOf('.');

		if (i > 0 && i < s.length() - 1) {
			ext = s.substring(i + 1).toLowerCase();
		}
		return ext;
	}
}