package lession03003;

class Book {	//����һ���µ���
	String title;
	double price;
	public void getInfo(){
		System.out.println("ͼ������: " + title + ", �۸�: " + price);
	}
}

public class TestDemo {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Book bk = new Book();
//		bk.title = "Java����";
//		bk.price = 89.9;
		bk.getInfo();
	}

}
