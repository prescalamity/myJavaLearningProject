/*
 * 
 * ͼ���û����� ���
 * 
 */

package ThisIsSecondBAO;

import java.awt.*;

public class LoginFrame extends Frame
{
	
	private static final long serialVersionUID = 1L;

	public LoginFrame()                          //��Ĺ��췽�������ڴ����û����봰��
	{
		super("User Login");                           //���ÿ�ܴ��ڱ���
		this.setSize(200,130);                         //���ã����ڣ�����ߴ�
		this.setLocation(300,240);                     //��������ĳ�ʼ��ʾλ��
		this.setBackground(Color.lightGray);           //��������ı�����ɫ
		this.setLayout(new FlowLayout());              //��������λ �� ���֣�����
		
		
		this.add(new Label("userID"));                 //�ڴ˴�������� ����ǩ�� ������������빹�����
		this.add(new TextField("User1",10));           //�ڴ˴��������
		this.add(new Label("password"));               //
		this.add(new TextField("Over 6 word!",10));
		this.add(new Button("OK"));
		this.add(new Button("Cancel"));
		
		this.setVisible(true);                          //��ʾ��ܴ��ڣ����������������
	}
}
