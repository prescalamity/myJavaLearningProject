/*
 * 
 * 图形用户界面 编程
 * 
 */

package ThisIsSecondBAO;

import java.awt.*;

public class LoginFrame extends Frame
{
	
	private static final long serialVersionUID = 1L;

	public LoginFrame()                          //类的构造方法，用于创建用户登入窗口
	{
		super("User Login");                           //设置框架窗口标题
		this.setSize(200,130);                         //设置（窗口）组件尺寸
		this.setLocation(300,240);                     //设置组件的初始显示位置
		this.setBackground(Color.lightGray);           //设置组件的背景颜色
		this.setLayout(new FlowLayout());              //设置容器位 流 布局，居中
		
		
		this.add(new Label("userID"));                 //在此窗口中添加 ‘标签’ 组件，并·输入构造参数
		this.add(new TextField("User1",10));           //在此窗口中添加
		this.add(new Label("password"));               //
		this.add(new TextField("Over 6 word!",10));
		this.add(new Button("OK"));
		this.add(new Button("Cancel"));
		
		this.setVisible(true);                          //显示框架窗口，其必须在添加组件后
	}
}
