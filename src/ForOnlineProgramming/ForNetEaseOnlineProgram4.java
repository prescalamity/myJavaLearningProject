package ForOnlineProgramming;

import java.util.Scanner;

public class ForNetEaseOnlineProgram4 {

	public static void main(String[] args) {
		ForNetEaseOnlineProgram4 fNEOP4=new ForNetEaseOnlineProgram4();
		fNEOP4.runMainNoStatic();
	}
	
	public void runMainNoStatic() {
		
		Scanner input=new Scanner(System.in);
		
		String  lineStr=input.nextLine();
		
		int n=Integer.parseInt(lineStr);
		
		ReviewEncourage T1 = new ReviewEncourage( n);
	    T1.start();
	    ReviewEncourage T2 = new ReviewEncourage( n);
	    T2.start();
	    ReviewEncourage T3 = new ReviewEncourage( n);
	    T3.start();
		
		System.out.println();
		
		input.close();
		
	}
	
}


class ReviewEncourage extends Thread{
	
	  int SumUser;
	  
	  public ReviewEncourage(int n) { 
		  
		  SumUser=n;
	  }      

	  public void bonus(PrizePool prizePool) { 
		  if(SumUser%2==0) {
			  prizePool.send("A");
			  SumUser++;
		  }
	  }  // ���ܴ�ӡA����ʾ���Ż���

	  public void coupon(PrizePool prizePool) { 
		  if(SumUser%2!=0) {
			  prizePool.send("B");
			  SumUser++;
		  }
	  }  // ���ܴ�ӡB����ʾ�����Ż�ȯ

	  public void contribution(PrizePool prizePool) { 
		  if(SumUser%2!=0) {
			  prizePool.send("C");
			  SumUser++;
		  }
	  }  // ���ܴ�ӡC����ʾ���Ź���ֵ

	}

/**
	PrizePool�����һ��send������ʵ�����£�
*/
class PrizePool {

    public void send(String input) {

        System.out.print(input);

    }

}

////lineStr.sub
//
//		String[] shuju=lineStr.split(" ");
//		
//		long min= Long.parseLong(shuju[0]);
//		long max= Long.parseLong(shuju[1]);
//
//		char[] theChar=shuju[2].toCharArray();
//		
//		int sum=0;
//		for(long i=min; i<max+1; i++) {
//			char[] charS=(i+"").toCharArray();
//			for(char c : charS) {
//				if(c==theChar[0]) sum++;
//			}
//		}
