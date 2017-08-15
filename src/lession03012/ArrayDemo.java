package lession03012;

class Book {
	private String title;
	private double price;

	public Book(String title, double price) {
		this.title = title;
		this.price = price;
	}

	// setter、getter、无参构造略
	@Override
	public String toString() {
		return "Book [title=" + title + ", price=" + price + "]";
	}

}

public class ArrayDemo {

	public static void main(String[] args) {
		// 开辟了一个三个长度的对象数组
		Book books [] = new Book[3];
		books[0] = new Book("Java", 79.8);
		books[1] = new Book("Python", 52.6);
		books[2] = new Book("C++", 49.9);
		for (int x = 0; x < books.length; x ++){
			System.out.println(books[x]);
		}
		
		// 静态初始化
		Book books_b [] = new Book[]{
				new Book("Java_b", 79.8),
				new Book("Python_b", 52.6),
				new Book("C++_b", 49.9)
		};
		for (int x = 0; x < books_b.length; x ++){
			System.out.println(books_b[x]);
		}
	}

}
