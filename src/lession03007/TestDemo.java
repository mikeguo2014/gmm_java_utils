package lession03007;

class Emp {
	private int empno;
	private String ename;
	private String job;
	private double sal;
	private double comm;

	public Emp() {
	}

	public Emp(int empno, String ename, String job, double sal, double comm) {
		this.empno = empno;
		this.ename = ename;
		this.job = job;
		this.sal = sal;
		this.comm = comm;
	}

	public int getEmpno() {
		return empno;
	}

	public void setEmpno(int empno) {
		this.empno = empno;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public double getComm() {
		return comm;
	}

	public void setComm(double comm) {
		this.comm = comm;
	}

	@Override
	public String toString() {
		return "Emp [empno=" + empno + ", ename=" + ename + ", job=" + job + ", sal=" + sal + ", comm=" + comm + "]";
	}

}

public class TestDemo {

	public static void main(String[] args) {
		System.out.println(new Emp(1, "mike", "QA", 12036.50, 50).toString());

	}

}
