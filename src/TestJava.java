
/*
 * 
 * 从 J2SE 1.4 开始，Java 编译器不再支持 import 进未命包名的类、接口。 
 * 那么，非默认包是可以调用到默认包里的类呢？
 * 答案是用 反射 就可以。
 * 
 */


public class TestJava {
	
	 public String Adress1="I am belong to class of TestJava in Default Package 。。。。";
	
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
        Class c = Class.forName("DefaultPackage");  //根据 默认包（或未命名包）中的 类名，获取类
        Method m = c.getDeclaredMethod("disp", null);   //Method型 类,定位目标类中的目标方法
        m.invoke(c.newInstance(), null);  
    }  
} 

*/