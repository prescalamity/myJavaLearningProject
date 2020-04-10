/*
 * 
 *   Learning  threads of JAVA
 * 
 */

package ThisIsSecondBAO;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * ��ż���߳���
 */
public class NumberThread extends Thread
{
    private int first;
    public NumberThread(String name,int first)       //�ض�����Ĺ��캯��
    {
    	super(name);
    	this.first=first;
    	//this.setPriority(prio);
    }
    
    public void run()         //��NumberThread�����run������������д�Ը���Thread���run����
    {
    	System.out.print("\n"+this.getName()+": ");
    	for(int i=first;i<50;i+=2)             //�������ֵ������Ϊ2
    	{
    		System.out.print(i+"  ");
    	}
    	System.out.println(this.getName()+"finish!\n");
    }
}//NumberThread

/**
 * �����ִ�����,����ȱʡ�࣬��������
 */
class WelcomeJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public WelcomeJFrame(String texts[])       //��WelcomeJFrame�Ĺ��캯��
	{
		super("������");
		this.setBounds(300,240,400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);     //�����X���رմ���
		if(texts==null||texts.length==0)
		{
			this.getContentPane().add(new RollbyJPanel("Welcome!"));
		}else {
			this.getContentPane().setLayout(new GridLayout(texts.length,1));   //���񲼾֣�����һ��
			for(int i=0;i<texts.length;i++)       // ѭ ���� �� �� �� �ɡ�RollbyJPanel�������
			{
				this.getContentPane().add(new RollbyJPanel(texts[i]));
			}
		}
		this.setVisible(true);
	}
	
	public WelcomeJFrame() {this(null);}       //���캯�������ö���Ϊ��
	
	/**
	 * �ڲ������
	 */
	private class RollbyJPanel extends JPanel implements ActionListener,Runnable  
	{
		 // �Զ���˽���ڲ�����࣬��ʵ�ֶ��������ӿں��߳̽ӿ�

		private static final long serialVersionUID = 1L;
		
		JTextField text_word,text_sleep,text_state;    //���������
		JButton button_start,button_interrupt;     // �� ť ����
		Thread thread;           //�̶߳���
		int sleeptime;
		
		RollbyJPanel(String text)      // �ڲ������ ���캯��
		{
			this.setLayout(new GridLayout(2,1));   // ��this�� ���� ���񲼾�
			text_word=new JTextField(String.format("%115s", text));  //text��ӿո��ַ���
			this.add(text_word);              // �� ���� ����������� �������ı���
			JPanel panel_sub=new JPanel();    //�� �� �� �� �� ��
			this.add(panel_sub);              //��  �������� �м� �� �������
			panel_sub.add(new JLabel("sleep"));  
			this.sleeptime=(int)(Math.random()*100);
			text_sleep=new JTextField(""+sleeptime,5);
			panel_sub.add(text_sleep);       //����������߳������ı���
			text_sleep.addActionListener(this);
			
			button_start=new JButton("����");    //���� ��ť ʵ������
			panel_sub.add(button_start);         //�������Ӱ�ť���
			//������ʼ����ť���Ӷ����¼���������ִ�б����е�actionPerformed��������
			button_start.addActionListener(this); 
			button_interrupt=new JButton("�ж�");
			panel_sub.add(button_interrupt);
			button_interrupt.addActionListener(this);  //�����жϡ���ť���Ӷ����¼�����
			
			thread=new Thread(this);     // �� �� �� �̣���ִ��Ŀ����� Run��������
			button_interrupt.setEnabled(false);  //�жϰ�ťΪfalse
			panel_sub.add(new JLabel("state"));
			text_state=new JTextField(""+thread.getState(),10);    //���� �����ʵ��
			text_state.setEditable(false);
			panel_sub.add(text_state);
		}

		public void run()    //�߳� ִ�����ݴ���
		{
			while(true)      //����ѭ���������̷߳�����һֱִ�ж��߳�Ҳ����������ﵽʹ���� ���˶���Ч��
				try
			{
					String str=text_word.getText();   //��������е��ı�ֵ�������ո񣩸�ֵ�� ��str��
					text_word.setText(str.substring(1)+str.substring(0,1));
					Thread.sleep(sleeptime);     //�߳�˯�ߣ����ж�ʱ�׳��ж��쳣
			}catch(InterruptedException ex) {
				break;      //�˳�ѭ������ʱ                    ���̴߳�����ֹ̬
			}
		}

		//ʵ�֡�ActionListener���ӿ��еġ�actionPerformed���������ǣ������¼�����������һ����������ʱϵͳ���øú���
		public void actionPerformed(ActionEvent ev) 
		{
			if(ev.getSource()==button_start)
			{
				thread=new Thread(this);     //ִ��Ŀ������run��������
				thread.start();
				text_state.setText(""+thread.getState());
				button_start.setEnabled(false);
				button_interrupt.setEnabled(true);
			}
			
			if(ev.getSource()==button_interrupt)
			{
				thread.interrupt();
				text_state.setText(""+thread.getState());
				button_start.setEnabled(true);
				button_interrupt.setEnabled(false);
			}
			
			if(ev.getSource()==text_sleep)
			{
				try {
					sleeptime=Integer.parseInt(text_sleep.getText());
				}catch(NumberFormatException nfex){
					JOptionPane.showMessageDialog(this,
							"\""+text_sleep.getText()+"\"����ת�������������������룡");
				}
			}
		}
		
	}//RollbyJPanel
}//WelcomeJFrame





