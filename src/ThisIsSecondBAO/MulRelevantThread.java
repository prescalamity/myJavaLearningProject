/*
 * 
 *  ���̲߳���
 * 
 */
package ThisIsSecondBAO;

/**
 * 
 * @author ���̲߳���,��ȡ��ʾ��
 *
 */
public class MulRelevantThread 
{
	final int ALLACCOUNT=10;
	
	Account AllAcount[]=new Account[ALLACCOUNT];      // �û����ݴ洢��ַ
	
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
		 (new SaveThread(AllAcount[whichone],value)).start();     //�����߳�,������
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
				System.out.println(name+"������"+AllAcount[i].balance);
		}
		System.out.println("���޴��ˣ�");
	}
	
	/**
	 * 
	 * @author �˻���
	 *
	 */
	private class Account    //�˻���
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
	 * @author ����߳���
	 *
	 */
	private class SaveThread extends Thread    //����߳���,�����ڶ���ͬʱȡ��
	{		
		private Account account;
		private double value;
		
		public SaveThread(Account account,double value)    //���캯����ʵ������ʱ ���� ����˻�����
		{
			this.account=account;
			this.value=value;
		}
		
		public void run()
		{
			synchronized(this.account)      //�����ٽ�����������ǰ�û�
			{
				double howmatch=this.account.balance;
				this.account.put(this.value);
				
				try { Thread.sleep(1); }
				catch(InterruptedException ex) {}
				
				System.out.println(this.account.name+"�˻�������"+howmatch+
						"������"+this.value+"����"+this.account.balance);
			}
		}
		
	}//SaveThread

	/**
	 * 
	 * @author ȡ���߳���
	 *
	 */
	private class FetchThread extends Thread    //ȡ���߳���
	{
		
	}//FetchThread

}//MulRelevantThread

