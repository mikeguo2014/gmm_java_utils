package lession03027;

class Emp {
	private int empno;
	private String ename;
	private double salary;
	private String dept;

	public Emp() {
	}

	public Emp(int empno) {
		this(empno, "������", 0.0, "δ��");
	}

	public Emp(int empno, String ename) {
		this(empno, ename, 1000.0, "����");
	}

	public Emp(int empno, String ename, double salary, String dept) {
		this.empno = empno;
		this.ename = ename;
		this.salary = salary;
		this.dept = dept;
	}

	public String getInfo() {
		return "��Ա��ţ�" + this.empno + "��������" + this.ename + "�����ʣ�" + this.salary + "�����ţ�" + this.dept;
	}
}

public class TestDemo {
	public static void main(String args[]) {
		Emp emp = new Emp(7369, "SMITH");
		System.out.println(emp.getInfo());
	}
}
