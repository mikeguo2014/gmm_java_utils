package lession03027;

class Emp {
	private int empno;
	private String ename;
	private double salary;
	private String dept;

	public Emp() {
	}

	public Emp(int empno) {
		this(empno, "无名氏", 0.0, "未定");
	}

	public Emp(int empno, String ename) {
		this(empno, ename, 1000.0, "后勤");
	}

	public Emp(int empno, String ename, double salary, String dept) {
		this.empno = empno;
		this.ename = ename;
		this.salary = salary;
		this.dept = dept;
	}

	public String getInfo() {
		return "雇员编号：" + this.empno + "，姓名：" + this.ename + "，工资：" + this.salary + "，部门：" + this.dept;
	}
}

public class TestDemo {
	public static void main(String args[]) {
		Emp emp = new Emp(7369, "SMITH");
		System.out.println(emp.getInfo());
	}
}
