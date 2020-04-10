package ThisIsSecondBAO;

import java.io.*;
import java.util.*;  

/**
 *   Learning I/O stream
 */
public class MyIaO 
{
	/**
	 * @for 用于测试  文件字节流   输入输出，且该流主要用于传输非字符数据，如整形，对象等
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
		
		
  //文件  字节流  读写以  字节  为单位，可以
		System.out.println("文件  字节流  读写以  字节  为单位 ");
		FileOutputStream fout =new FileOutputStream(filePath);  // FileOutputStream
		fout.write(testInt_a);
		fout.write(testInt_b); 
		fout.write(testInt_c);   //只存最低8位（1个字节）
		fout.write(testInt_d);   //只存最低8位（1个字节）
		fout.write(testInt_e);
		fout.write(-1);
		fout.close();
		
		FileInputStream fin =new FileInputStream(filePath);  //字节流 输入,FileInputStream
		int i;
		while((i=fin.read())!=-1)
		{
			System.out.println(" "+i);
		}
		fin.close();

  //从字节流中读取4字节为一个int整数
		System.out.println("从字节流中读取4字节为一个int整数 ");
		FileOutputStream fOut =new FileOutputStream(filePath);
		fOut.write(-1>>>24);
		fOut.write(-1>>>16);
		fOut.write(-1>>>8);
		fOut.write(-1);
		fOut.close();
		
		int counter=0;
		FileInputStream fIn=new FileInputStream(filePath);
		while((i=fIn.read())!=-1)     //值读取一个字节，当字节流结束时返回-1
		{
			counter++;
			System.out.println("运行次数："+counter);
			int tmp;
			for(int j=0;j<3&&(tmp=fIn.read())!=-1;j++)    //读取剩余三字节，读完一字节后再读下一个字节
			{
				i=i<<8|tmp;         //左移8位，再加入低位1字节
			}
			System.out.println(i+" ");
		}
		fIn.close();
		
  //数据字节流
		System.out.println("数据字节流");
		fout =new FileOutputStream(filePath);
		DataOutputStream dOut=new DataOutputStream(fout);  // 写 入一个整数，传入FileOutputStream对象
		dOut.writeInt(testInt_e);
		dOut.close();
		
		fin =new FileInputStream(filePath);
		DataInputStream dIn=new DataInputStream(fin);      // 读 取一个整数，传入FileInputStream对象
		int dInt=dIn.readInt();
		System.out.println(dInt);
		dIn.close();
		
  //对象字节流
		
	}
 	
	/**
	 * @for 用于测试   文件字符流   输入输出，且该流   ‘仅’  用于传输字符数据
	 * @throws IOException
	 */
	public static void fileStream_R_W() throws IOException
	{	
		String filePath="C:\\Users\\LiuYuan\\Desktop\\JavaTest_IO_b.txt";
		String testStr="Java'IO test.";
		
		byte[] buff=new byte[]{}; 
		buff=testStr.getBytes();   //将  字符串    转换成   字符数组
		
		FileWriter fWriter=new FileWriter(filePath);
		fWriter.write(testStr+"\r\n");
		fWriter.write("This is second line!"+"\r\n");
		fWriter.write("中文测试！"+"\r\n");
		fWriter.close();
		
		FileReader fReader = new FileReader(filePath);  //创建 指定 文件读取流，读写以  字符  为单位
		BufferedReader bReader=new BufferedReader(fReader);  //创建 输入流 缓冲区,可按行读取	    
		String text=bReader.readLine()+"\r\n";   //流 结束时返回 null
		text+=bReader.readLine()+"\r\n";
		text+=bReader.readLine();
		bReader.close();
	    fReader.close();
	    System.out.println("变量的字节长度："+text.getBytes().length);
        System.out.println(text);

	}
	
	/**
	 * @for 测试  系统   输入输出流，及从键盘获得数据
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
		
//		System.out.println("利用 BufferedReader实现从键盘读入字符串");
//		System.out.println("please input string:");
//		BufferedReader bReader = new BufferedReader (new InputStreamReader(System.in)); 
//		sText=bReader.readLine();
//		bReader.close();
//		System.out.println(sText);
		
		System.out.println("利用 Scanner 类实现从键盘读入数据");
		System.out.println("please input string, int, float:");
		Scanner inKeyboard=new Scanner(System.in); //使用Scanner类定义对象
      //cText=inKeyboard.next();
		sText=inKeyboard.nextLine();
		iText=inKeyboard.nextInt();
		fText=inKeyboard.nextFloat();
		inKeyboard.close();
		System.out.println("你输入的数据为：");
		System.out.println(sText+"\r\n"+iText+"\r\n"+fText);

	}
	
	/**
	 * @for  用于测试  对文件进行相关操作，如查找，复制，修改
	 */
	public static void systemFile()
	{
		
	}
	
}




