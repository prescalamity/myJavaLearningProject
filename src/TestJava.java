
/*
 * 
 * �� J2SE 1.4 ��ʼ��Java ����������֧�� import ��δ���������ࡢ�ӿڡ� 
 * ��ô����Ĭ�ϰ��ǿ��Ե��õ�Ĭ�ϰ�������أ�
 * ������ ���� �Ϳ��ԡ�
 * 
 */


public class TestJava {
	
	 public String Adress1="I am belong to class of TestJava in Default Package ��������";
	
	 private String AdressTJ="I am belong to class of TestJava in Default Package.";
	 public String disp() {  
	        
		 return AdressTJ;
		 
		 //System.out.println("Hello World!");  
	 }
	
}

/*

package test;  

import java.lang.reflect.*;  
  
public class TestDefaultPackage {  
  
    public static void main(String[] args) throws Exception {  
        Class c = Class.forName("DefaultPackage");  //���� Ĭ�ϰ�����δ���������е� ��������ȡ��
        Method m = c.getDeclaredMethod("disp", null);   //Method�� ��,��λĿ�����е�Ŀ�귽��
        m.invoke(c.newInstance(), null);  
    }  
} 

*/