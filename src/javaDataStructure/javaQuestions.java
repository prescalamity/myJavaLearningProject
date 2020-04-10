package javaDataStructure;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.TreeSet;

public class javaQuestions {

	public void run_javaQuestions() {
		System.out.println("------------------I am 'javaQuestions'. ------------------");
		
		//testQueue();
		//question21_2();
		
		System.out.println("------------------I am 'javaQuestions'. ------------------");
	}

	void testQueue() {
		PriorityQueue<String> que1 = new PriorityQueue<>();
		que1.offer("George");
		que1.offer("Jim");
		que1.offer("John");
		que1.offer("Blake");
		que1.offer("Kevin");
		que1.offer("Michae");
		
		while(que1.size()>0) {
			System.out.println(que1.remove()+",");
		}
	}
	
	void question21_2() {
		
		String filePath ="C:\\Users\\LiuYuan\\Desktop\\ceshishuju.txt";
		File file=new File(filePath);
		TreeSet<String> myTreeSet=new TreeSet<String>();
		
		try {
			Scanner inputFile =new Scanner(file);
			
			while(inputFile.hasNext()) {
				myTreeSet.add(inputFile.next());
			}
			
			inputFile.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		for (String string : myTreeSet) {
			System.out.println(string);
		}
	}
	
	void question22_3() {
		String input1="dasjdajsdashdakjhdajsdhabuereotlpmxznqowqowosmc";
		String input2="dsad";
		
		System.out.println(input1+input2);
		
	}

	
}





