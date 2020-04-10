

import java.util.Arrays;
import java.util.Random;

import javaDataStructure.BPlusTree;

public class testBPlusTree {
	public static void main(String[] args) {
		System.out.println("Hello, world!");
		System.out.println("I am working!");
		System.out.println("That is good!");
		
		
		BPlusTree testBtree=new BPlusTree();

//		testBtree.add(4);
//		testBtree.add(13);
//		testBtree.add(13);
//		testBtree.add(100);
//		testBtree.add(101);
//		testBtree.add(77);
		
		Random random=new Random();
		int[] testArr=new int[100];
		
		for(int i=0; i<testArr.length; i++) {
			testArr[i] = random.nextInt(1000);
			System.out.print(testArr[i]+",");
			testBtree.add(testArr[i]);
		}
		System.out.println();
		System.out.println("======================The sum of tree is "+testBtree.size());
		testBtree.visitAllNode();	
		
		
		
//		int[] testArr=new int[] {61,838,998,708,855,374,742,193,761,952,254,322,952,158,331,636,560,947,343,866};
//		for(int i : testArr) System.out.print(i+",");
//		System.out.println();
//		
//		int[] testArrr=new int[]{61,838,998,708,855,374,742,193,761,952,254,322,952,158,331,636,560,947,343,866};
//		Arrays.sort(testArrr);
//		for(int i : testArrr) System.out.print(i+",");
//		System.out.println();
//		
//		for(int i=0; i<testArr.length; i++) {
//			testBtree.add(testArr[i]);
//		}
//		System.out.println();
//		System.out.println("======================The sum of tree is "+testBtree.Size());
//		testBtree.visitAllNode();
//		System.out.println();
		
		System.out.println("100-->"+testBtree.findData(100));
		System.out.println("200-->"+testBtree.findData(200));
		System.out.println("300-->"+testBtree.findData(300));
		
		System.out.println();
		System.out.println("======================removing node ======================");
		for(int i=0; i<testArr.length/2; i++) {
			System.out.println("You need to remove: " + testArr[i]);
			testBtree.remove(testArr[i]);
		}
		
//		testBtree.add(4);
//		testBtree.add(13);
//		testBtree.add(13);
//		testBtree.add(100);
//		testBtree.add(101);
//		testBtree.add(77);
		
		System.out.println("======================The sum of tree is "+testBtree.size());
		testBtree.visitAllNode();
		System.out.println();
		testBtree.visitAllLeavesNode();
		
		
	}
}
 








