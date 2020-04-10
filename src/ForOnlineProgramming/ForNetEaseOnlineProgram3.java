package ForOnlineProgramming;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class ForNetEaseOnlineProgram3 {

	public static void main(String[] args) {
		ForNetEaseOnlineProgram3 fNEOP3=new ForNetEaseOnlineProgram3();
		fNEOP3.runMainNoStatic();
	}
	
	public void runMainNoStatic() {
		//System.out.println("(This is 'main' function but do not need 'Static' for called function.)");
		
		Scanner input=new Scanner(System.in);
		
		String  lineStr=input.nextLine();
		
		String[] shuju=lineStr.split(" ");
		
		ArrayList<Integer> yuGongJiLi=new ArrayList<Integer>();
		
		//int nTiaoYu=Integer.parseInt(shuju[0]);
		int mLun=Integer.parseInt(shuju[1]);
		
		for(int i=2; i<shuju.length; i++) {
			yuGongJiLi.add(Integer.parseInt(shuju[i]));
		}
		Collections.sort(yuGongJiLi);
		
		for(int m=0; m<mLun; m++) {
			int tempGJL=yuGongJiLi.remove(0);
			for(int j=0; j<yuGongJiLi.size(); j++) {
				if(tempGJL != yuGongJiLi.get(j)) {
					yuGongJiLi.set(j, tempGJL + yuGongJiLi.get(j) );
					break;
				}
			}
			
			Collections.sort(yuGongJiLi);
		}
		
		System.out.println(yuGongJiLi.get(0));
		
		input.close();
		
	}
	
}
