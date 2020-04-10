/*
 * 
 *  多线程并行
 * 
 */
package ThisIsSecondBAO;

/**
 * 
 * @author 多线程并行,存取款示例
 *
 */
public class MulRelevantThread 
{
	final int ALLACCOUNT=10;
	
	Account AllAcount[]=new Account[ALLACCOUNT];      // 用户数据存储地址
	
	private int AccountNumber=0;
	
	public String IntsAccount(String name)
	{
		if(AccountNumber>=10)
		{
			return "It is filled!";
		}else {
			AllAcount[AccountNumber]=new Account(name);
			AccountNumber+=1;
			return name+" has built!";
		}
	}
	
	public String IntsSaveThread(String name,double value)
	{
		int whichone=-1;
		
		for (int i=0;i<=AccountNumber-1;i++)
		{
			if(AllAcount[i].name==name)
				whichone=i;
		}

		if(whichone!=-1) 
		{
		 (new SaveThread(AllAcount[whichone],value)).start();     //创建线程,并运行
		 return "That has saved!";
		}else {
			return name+" dose not exist!";
		}
	}
	
	public void ChaXun(String name)
	{
		for (int i=0;i<=AccountNumber-1;i++)
		{
			if(AllAcount[i].name==name)
				System.out.println(name+"此人余额："+AllAcount[i].balance);
		}
		System.out.println("查无此人！");
	}
	
	/**
	 * 
	 * @author 账户类
	 *
	 */
	private class Account    //账户类
	{
		String name;
		double balance;
		
		public Account(String name)
		{
			this.name=name;
			this.balance=0;
		}
		
		public void put(double value)
		{
			if(value>0)
			{
				this.balance+=value;
			}
		}
		
		public double get(double value)
		{
			if(value<=0)
			{
				return 0;
			}
			
			if(value<=this.balance)
			{
				this.balance-=value;
			}else
			{
				value=this.balance;
				this.balance=0;
			}
			return value;
		}
		
		
	}//Account

	/**
	 * 
	 * @author 存款线程类
	 *
	 */
	private class SaveThread extends Thread    //存款线程类,可用于多人同时取款
	{		
		private Account account;
		private double value;
		
		public SaveThread(Account account,double value)    //构造函数，实例化类时 传入 存款账户与金额
		{
			this.account=account;
			this.value=value;
		}
		
		public void run()
		{
			synchronized(this.account)      //声明临界区，锁定当前用户
			{
				double howmatch=this.account.balance;
				this.account.put(this.value);
				
				try { Thread.sleep(1); }
				catch(InterruptedException ex) {}
				
				System.out.println(this.account.name+"账户：现有"+howmatch+
						"，存入"+this.value+"，余额："+this.account.balance);
			}
		}
		
	}//SaveThread

	/**
	 * 
	 * @author 取款线程类
	 *
	 */
	private class FetchThread extends Thread    //取款线程类
	{
		
	}//FetchThread

}//MulRelevantThread

