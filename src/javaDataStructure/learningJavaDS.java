/**
 * 
 *  learning java data structure
 * 
 * 
 *   ѧϰ�ʼǣ�LinkedList��ArrayList����ǿ�󣬵�ArrayListЧ�����LinkedList��
 */
package javaDataStructure;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

public class learningJavaDS {

	public static void main(String[] args) {
		
		point[] pointSet=new point[100];
		
		for (int i = 0; i < pointSet.length; i++) {
			float x= (float) Math.random()*100f;
			float y= (float) Math.random()*100f;
			pointSet[i]=new point(x,y);
		}
		
		System.out.println("====================== ��x�Ƚ� ==========================");
		Collections.sort(Arrays.asList(pointSet), new ComparaX());
		for (int i = 0; i < pointSet.length; i++) {
			pointSet[i].print();
		}
		
		System.out.println();
		System.out.println("====================== ��y�Ƚ� ==========================");
		Collections.sort(Arrays.asList(pointSet));
		
		for (point point : pointSet) {
			point.print();
		}
		
	}
	
	ArrayList<String> mylist= new ArrayList<>();
	
}

/**
 *  ��С�����ȱȽϵ��x���ꣻ
 *  
 *  ʵ�ֱȽ����ӿ�
 *
 */
class ComparaX implements Comparator<point>{

	@Override
	public int compare(point o1, point o2) {
		
		if(o1.x>o2.x) {
			return 1;
		}else if(o1.x==o2.x) {
			return (int)(o1.y-o2.y);
		}else {
			return -1;
		}
	}
	
}

/**
 * @author �����࣬���ڽṹ�幦��
 * 
 * @author ���㡱�࣬ʵ�֡��Ƚϡ���Comparable���ӿ�
 */
class point implements Comparable<point>{
	
	float x;
	float y;
	
	public point(float x,float y) {
		this.x=x;
		this.y=y;
	}

	public point() {}

    public void print() {
    	System.out.println("("+this.x+","+this.y+")");
    }

	@Override
	public int compareTo(point o) {
		if(this.y>o.y) {
			return 1;
		}else if(this.y==o.y) {
			return (int)(this.x-o.x);
		}else {
			return -1;
		}
	}
}
