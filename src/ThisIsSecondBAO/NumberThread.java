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
 * 奇偶数线程类
 */
public class NumberThread extends Thread
{
    private int first;
    public NumberThread(String name,int first)       //重定义类的构造函数
    {
    	super(name);
    	this.first=first;
    	//this.setPriority(prio);
    }
    
    public void run()         //‘NumberThread’类的run（）方法，重写以覆盖Thread类的run（）
    {
    	System.out.print("\n"+this.getName()+": ");
    	for(int i=first;i<50;i+=2)             //输出序列值，步长为2
    	{
    		System.out.print(i+"  ");
    	}
    	System.out.println(this.getName()+"finish!\n");
    }
}//NumberThread

/**
 * 滚动字窗口类,访问缺省类，本包共享
 */
class WelcomeJFrame extends JFrame
{
	private static final long serialVersionUID = 1L;

	public WelcomeJFrame(String texts[])       //类WelcomeJFrame的构造函数
	{
		super("滚动字");
		this.setBounds(300,240,400,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);     //点击“X”关闭窗口
		if(texts==null||texts.length==0)
		{
			this.getContentPane().add(new RollbyJPanel("Welcome!"));
		}else {
			this.getContentPane().setLayout(new GridLayout(texts.length,1));   //网格布局，多行一列
			for(int i=0;i<texts.length;i++)       // 循 环， 创 建 若 干‘RollbyJPanel’类对象
			{
				this.getContentPane().add(new RollbyJPanel(texts[i]));
			}
		}
		this.setVisible(true);
	}
	
	public WelcomeJFrame() {this(null);}       //构造函数，重置对象为空
	
	/**
	 * 内部面板类
	 */
	private class RollbyJPanel extends JPanel implements ActionListener,Runnable  
	{
		 // 自定义私有内部面板类，且实现动作监听接口和线程接口

		private static final long serialVersionUID = 1L;
		
		JTextField text_word,text_sleep,text_state;    //定义输入框
		JButton button_start,button_interrupt;     // 按 钮 对象
		Thread thread;           //线程对象
		int sleeptime;
		
		RollbyJPanel(String text)      // 内部面板类 构造函数
		{
			this.setLayout(new GridLayout(2,1));   // ‘this’ 窗口 网格布局
			text_word=new JTextField(String.format("%115s", text));  //text后加空格字符串
			this.add(text_word);              // 在 窗口 中增加组件， 滚动字文本行
			JPanel panel_sub=new JPanel();    //定 义 组 件 容 器
			this.add(panel_sub);              //在  窗口容器 中加 入 面板容器
			panel_sub.add(new JLabel("sleep"));  
			this.sleeptime=(int)(Math.random()*100);
			text_sleep=new JTextField(""+sleeptime,5);
			panel_sub.add(text_sleep);       //增加组件，线程休眠文本行
			text_sleep.addActionListener(this);
			
			button_start=new JButton("启动");    //创建 按钮 实例对象
			panel_sub.add(button_start);         //容器增加按钮组件
			//给‘开始’按钮增加动作事件监听，且执行本类中的actionPerformed（）方法
			button_start.addActionListener(this); 
			button_interrupt=new JButton("中断");
			panel_sub.add(button_interrupt);
			button_interrupt.addActionListener(this);  //给‘中断’按钮增加动作事件监听
			
			thread=new Thread(this);     // 创 建 线 程，且执行目标类的 Run（）方法
			button_interrupt.setEnabled(false);  //中断按钮为false
			panel_sub.add(new JLabel("state"));
			text_state=new JTextField(""+thread.getState(),10);    //创建 输入框实例
			text_state.setEditable(false);
			panel_sub.add(text_state);
		}

		public void run()    //线程 执行内容代码
		{
			while(true)      //永真循环，即此线程方法将一直执行而线程也不会结束，达到使文字 “运动”效果
				try
			{
					String str=text_word.getText();   //将输入框中的文本值（包括空格）赋值给 ‘str’
					text_word.setText(str.substring(1)+str.substring(0,1));
					Thread.sleep(sleeptime);     //线程睡眠，被中断时抛出中断异常
			}catch(InterruptedException ex) {
				break;      //退出循环，此时                    该线程处于终止态
			}
		}

		//实现‘ActionListener’接口中的‘actionPerformed’方法覆盖，动作事件处理函数，当一个动作发生时系统调用该函数
		public void actionPerformed(ActionEvent ev) 
		{
			if(ev.getSource()==button_start)
			{
				thread=new Thread(this);     //执行目标对象的run（）方法
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
							"\""+text_sleep.getText()+"\"不能转化成整数，请重新输入！");
				}
			}
		}
		
	}//RollbyJPanel
}//WelcomeJFrame





