package lession03010;

import java.util.Arrays;

public class TestDemo {

	public static void main(String[] args) {
		//范例：实现数组拷贝
		//数组A: 1、2、3、4、5、6、7、8
		//数组B：11、22、33、44、55、66、77、88
		//要求拷贝后的数组B: 11、22、5、6、7、66、77、88
		int dataA[] = new int [] {1,2,3,4,5,6,7,8};
		int dataB[] = new int [] {11,22,33,44,55,66,77,88};
		System.arraycopy(dataA, 4, dataB, 2, 3);
		
		print(dataB);
		
		java.util.Arrays.sort(dataB);	//排序
		print(dataB);
	}
	
	public static void print(int data[]){
		for (int x = 0; x < data.length; x ++){
			System.out.print(data[x] + "、");
		}
		System.out.println();
	}

}
