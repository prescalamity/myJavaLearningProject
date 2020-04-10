/*
 *    本Java项目主要用于学习java
 * 
 *    主  函  数  类
 * 
 */
package ThisIsSecondBAO;

import defaultpackage.*;
import java.lang.reflect.*;  

public class ClassOfSecondBAO  
{
	private void AbandonedFunction() 
	{
		/*
		Scanner MyIn = new Scanner(System.in);
		
		String Iam="ClassOfDefaultpackage.Adress";
		ClassOfDefaultpackage InstCOD=new ClassOfDefaultpackage();
		//String I'm=ClassOfDefaultpackage.Adress;
		System.out.println(InstCOD.Adress);
	  	System.out.println(Iam);
	  	InstCOD.COD(); 
	  	
		int mat[]=new int[5];
		mat[1]=23;
		mat[2]=MyIn.nextInt();
		
		System.out.println("ha? "+mat[1]);
		System.out.println("ha? "+mat[2]);
		
		for(int i=0;i<=4;i++) 
		{
			mat[i]=	MyIn.nextInt();
		}
		for(int i =0;i<=4;i++) 
		{
			System.out.println(mat[i]);
		}
		System.out.println("ha? "+mat[1]);
		System.out.println("ha? "+mat[2]);
		MyIn.close();
		
		int i =0;
		ClassOfDefaultpackage1 Cod1=new ClassOfDefaultpackage1(i);
		Cod1.PointResult();
		Cod1.buildStudent();
		
		String str="I'm string.";
		System.out.println("My length is "+str.length()+"。。。。");
		
		new LoginFrame();
		
		System.out.println("currentThread="+Thread.currentThread().getName());
		NumberThread thread_odd=new NumberThread("奇数线程",1);
		NumberThread thread_even=new NumberThread("偶数线程",2);
		thread_odd.start();          // 启 动 线程对象
		thread_even.start();
		System.out.println("activeCount="+Thread.activeCount());//输出当前活动线程数，结果为main线程先于？其它

		String texts[]= {"Welcome","Hello","Rollby"};
		new WelcomeJFrame(texts);
		
		MulRelevantThread AboutAccount=new MulRelevantThread();
		System.out.println(AboutAccount.IntsAccount("zhao"));
		System.out.println(AboutAccount.IntsSaveThread("zhao", 100));
		AboutAccount.ChaXun("Wang");

		MyIaO.fileStream_Byte();
		
		MyIaO.fileStream_R_W();
		
		MyIaO.systemIaO();
		*/
	}

	public static void main(String[] args) throws Exception 
	{
		System.out.println("The main function of 'ClassOfSecondBAO' class is running...");
		
		MulRelevantThread AboutAccount=new MulRelevantThread();
		System.out.println(AboutAccount.IntsAccount("zhao"));
		System.out.println(AboutAccount.IntsSaveThread("zhao", 100));
		AboutAccount.ChaXun("zhao");
		
    } 
	
}





