package lession03003;

class Book {	//定义一个新的类
	String title;
	double price;
	public void getInfo(){
		System.out.println("图书名称: " + title + ", 价格: " + price);
	}
}

public class TestDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Book bk = new Book();
//		bk.title = "Java开发";
//		bk.price = 89.9;
		bk.getInfo();
	}

}
