package ThisIsSecondBAO;

import java.io.*;
import java.util.*;  

/**
 *   Learning I/O stream
 */
public class MyIaO 
{
	/**
	 * @for ���ڲ���  �ļ��ֽ���   ����������Ҹ�����Ҫ���ڴ�����ַ����ݣ������Σ������
	 * 
	 * @throws IOException
	 */
	public static void fileStream_Byte() throws IOException
	{
		 
		String filePath="C:\\Users\\LiuYuan\\Desktop\\JavaTest_IO_a";
		int testInt_a=1;
		int testInt_b=100;
		int testInt_c=500;       //  00000000 00000000 00000001 11110100
		int testInt_d=511;       //  00000000 00000000 00000001 11111111
		int testInt_e=1023;      //  00000000 00000000 00000011 11111111
		
		
  //�ļ�  �ֽ���  ��д��  �ֽ�  Ϊ��λ������
		System.out.println("�ļ�  �ֽ���  ��д��  �ֽ�  Ϊ��λ ");
		FileOutputStream fout =new FileOutputStream(filePath);  // FileOutputStream
		fout.write(testInt_a);
		fout.write(testInt_b); 
		fout.write(testInt_c);   //ֻ�����8λ��1���ֽڣ�
		fout.write(testInt_d);   //ֻ�����8λ��1���ֽڣ�
		fout.write(testInt_e);
		fout.write(-1);
		fout.close();
		
		FileInputStream fin =new FileInputStream(filePath);  //�ֽ��� ����,FileInputStream
		int i;
		while((i=fin.read())!=-1)
		{
			System.out.println(" "+i);
		}
		fin.close();

  //���ֽ����ж�ȡ4�ֽ�Ϊһ��int����
		System.out.println("���ֽ����ж�ȡ4�ֽ�Ϊһ��int���� ");
		FileOutputStream fOut =new FileOutputStream(filePath);
		fOut.write(-1>>>24);
		fOut.write(-1>>>16);
		fOut.write(-1>>>8);
		fOut.write(-1);
		fOut.close();
		
		int counter=0;
		FileInputStream fIn=new FileInputStream(filePath);
		while((i=fIn.read())!=-1)     //ֵ��ȡһ���ֽڣ����ֽ�������ʱ����-1
		{
			counter++;
			System.out.println("���д�����"+counter);
			int tmp;
			for(int j=0;j<3&&(tmp=fIn.read())!=-1;j++)    //��ȡʣ�����ֽڣ�����һ�ֽں��ٶ���һ���ֽ�
			{
				i=i<<8|tmp;         //����8λ���ټ����λ1�ֽ�
			}
			System.out.println(i+" ");
		}
		fIn.close();
		
  //�����ֽ���
		System.out.println("�����ֽ���");
		fout =new FileOutputStream(filePath);
		DataOutputStream dOut=new DataOutputStream(fout);  // д ��һ������������FileOutputStream����
		dOut.writeInt(testInt_e);
		dOut.close();
		
		fin =new FileInputStream(filePath);
		DataInputStream dIn=new DataInputStream(fin);      // �� ȡһ������������FileInputStream����
		int dInt=dIn.readInt();
		System.out.println(dInt);
		dIn.close();
		
  //�����ֽ���
		
	}
 	
	/**
	 * @for ���ڲ���   �ļ��ַ���   ����������Ҹ���   ������  ���ڴ����ַ�����
	 * @throws IOException
	 */
	public static void fileStream_R_W() throws IOException
	{	
		String filePath="C:\\Users\\LiuYuan\\Desktop\\JavaTest_IO_b.txt";
		String testStr="Java'IO test.";
		
		byte[] buff=new byte[]{}; 
		buff=testStr.getBytes();   //��  �ַ���    ת����   �ַ�����
		
		FileWriter fWriter=new FileWriter(filePath);
		fWriter.write(testStr+"\r\n");
		fWriter.write("This is second line!"+"\r\n");
		fWriter.write("���Ĳ��ԣ�"+"\r\n");
		fWriter.close();
		
		FileReader fReader = new FileReader(filePath);  //���� ָ�� �ļ���ȡ������д��  �ַ�  Ϊ��λ
		BufferedReader bReader=new BufferedReader(fReader);  //���� ������ ������,�ɰ��ж�ȡ	    
		String text=bReader.readLine()+"\r\n";   //�� ����ʱ���� null
		text+=bReader.readLine()+"\r\n";
		text+=bReader.readLine();
		bReader.close();
	    fReader.close();
	    System.out.println("�������ֽڳ��ȣ�"+text.getBytes().length);
        System.out.println(text);

	}
	
	/**
	 * @for ����  ϵͳ   ��������������Ӽ��̻������
	 * @throws IOException
	 */
	public static void systemIaO() throws IOException
	{
		char cText;
		String sText;
		int iText;
		float fText;
		
//		System.out.println("please input a float number:");
//		cText=System.in.read();
//		System.out.println(cText);
		
//		System.out.println("���� BufferedReaderʵ�ִӼ��̶����ַ���");
//		System.out.println("please input string:");
//		BufferedReader bReader = new BufferedReader (new InputStreamReader(System.in)); 
//		sText=bReader.readLine();
//		bReader.close();
//		System.out.println(sText);
		
		System.out.println("���� Scanner ��ʵ�ִӼ��̶�������");
		System.out.println("please input string, int, float:");
		Scanner inKeyboard=new Scanner(System.in); //ʹ��Scanner�ඨ�����
      //cText=inKeyboard.next();
		sText=inKeyboard.nextLine();
		iText=inKeyboard.nextInt();
		fText=inKeyboard.nextFloat();
		inKeyboard.close();
		System.out.println("�����������Ϊ��");
		System.out.println(sText+"\r\n"+iText+"\r\n"+fText);

	}
	
	/**
	 * @for  ���ڲ���  ���ļ�������ز���������ң����ƣ��޸�
	 */
	public static void systemFile()
	{
		
	}
	
}




