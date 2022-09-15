package gntp.lesson.mvc.vo;

public class BookVO {
	private int bookSeq;
	private String bookTitle;
	private String bookAuthor;
	private int bookPrice;
	private String bookImage;
	private String bookAttachment;
	
	public BookVO() {}
	
	public BookVO(int bookSeq, String bt, String ba, int bp, String bi, String attach) {
		this.bookSeq = bookSeq;
		this.bookTitle = bt;
		this.bookAuthor = ba;
		this.bookPrice = bp;
		this.bookImage = bi;
		this.bookAttachment = attach;
	}

	public int getBookSeq() {
		return bookSeq;
	}

	public void setBookSeq(int bookSeq) {
		this.bookSeq = bookSeq;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setBookAuthor(String bookAutho) {
		this.bookAuthor = bookAutho;
	}

	public int getBookPrice() {
		return bookPrice;
	}

	public void setBookPrice(int bookPrice) {
		this.bookPrice = bookPrice;
	}

	public String getBookImage() {
		return bookImage;
	}

	public void setBookImage(String bookImage) {
		this.bookImage = bookImage;
	}

	public String getBookAttachment() {
		return bookAttachment;
	}

	public void setBookAttachment(String bookAttachment) {
		this.bookAttachment = bookAttachment;
	}
	
	
}
