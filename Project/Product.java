
public class Product {

	private int pid;
	private String fileName;
	private int price;
	private int qty;

	public Product(int pid, String fileName, int price, int qty) {
		super();
		this.pid = pid;
		this.fileName = fileName;
		this.price = price;
		this.qty = qty;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Product [pid=" + pid + ", fileName=" + fileName + ", price=" + price + ", qty=" + qty + "]";
	}

}