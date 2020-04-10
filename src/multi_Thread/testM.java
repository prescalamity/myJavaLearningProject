package multi_Thread;

public class testM {
	
	int i=10;
	
	public static void main(String[] args) {
		classA cAA=new classA();
		cAA.testFunc();
		
		
	}
	
	public void testFunction(int i) {
		System.out.println(i);
	}
	
}


class classA{
	
	testM tM=new testM();
	
	String[] args= {"s","a"};
	
	public void testFunc() {
		tM.testFunction(111);
		//testM.main(args);
	}
	
	
}


