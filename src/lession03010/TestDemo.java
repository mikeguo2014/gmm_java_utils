package lession03010;

import java.util.Arrays;

public class TestDemo {

	public static void main(String[] args) {
		//������ʵ�����鿽��
		//����A: 1��2��3��4��5��6��7��8
		//����B��11��22��33��44��55��66��77��88
		//Ҫ�󿽱��������B: 11��22��5��6��7��66��77��88
		int dataA[] = new int [] {1,2,3,4,5,6,7,8};
		int dataB[] = new int [] {11,22,33,44,55,66,77,88};
		System.arraycopy(dataA, 4, dataB, 2, 3);
		
		print(dataB);
		
		java.util.Arrays.sort(dataB);	//����
		print(dataB);
	}
	
	public static void print(int data[]){
		for (int x = 0; x < data.length; x ++){
			System.out.print(data[x] + "��");
		}
		System.out.println();
	}

}
