package ForOnlineProgramming;

import java.util.ArrayList;
import java.util.Scanner;

public class ti2 {

	public static void main(String[] args) {
		ti2 _maiN=new ti2();
		_maiN.mainNoStatic();
	}
	
	public void mainNoStatic() {
		
		//Scanner input = new Scanner(System.in);

				//input.nextLine();
				
				//while (input.hasNextLine()) {
					
					//String strInput=input.nextLine();
					
					String testStr="ASDFGH";   
				
					String strInput="ASFHFSAG";   // 测试组 （ ASDFDSAG --> 8）
					
					String[] qPL_String=quanPaiLie(testStr);     // 返回 字符串“testStr”的全排序
					
					int[] zuiXiaoDaiJia=jiSuanZuiXiaoDaiJia(qPL_String, strInput);      // 找出最小代价和排列
					
					System.out.println("("+strInput+")minCost-->"+zuiXiaoDaiJia[0]);
					System.out.println("minCostPermutation-->"+ qPL_String[zuiXiaoDaiJia[1]]);
					
				//}
				
		//input.close();

	}

	public String[] quanPaiLie(String testStr) {
		
		String[] qPL_String=new String[jieCheng(testStr.length())];
		int[] qPL_Controler = new int[1];
		
		ArrayList<Character> testCharList = new ArrayList<Character>();
		for (char c : testStr.toCharArray()) {testCharList.add(c);}

		quanPaiLieDiGui("", testCharList, qPL_String, qPL_Controler);   //将结果存入qPL_String
		
		return qPL_String;
	}
	public int jieCheng(int n) {
		if(n==1) return 1;
		return n*jieCheng(n-1);
	}
	public void quanPaiLieDiGui( String yiPaiXu, ArrayList<Character> testCharToStr, String[] qPL_String, int[] qPL_Controler) {
		if(testCharToStr.size()<2) {
			qPL_String[qPL_Controler[0]] = yiPaiXu + testCharToStr.remove(0);
			qPL_Controler[0]++;
		}else {
			for(int i=0;i<testCharToStr.size();i++) {
				
				ArrayList<Character> tempCharList=new ArrayList<Character>(testCharToStr);  // 创建新的和testCharToStr一样的ArrayList
				//for(char c:testCharToStr) {tempCharList.add(c);}               

				String tempStr = yiPaiXu + tempCharList.remove(i);
				
				quanPaiLieDiGui(tempStr, tempCharList , qPL_String, qPL_Controler);
				
			}
		}
	}
	
	public int[] jiSuanZuiXiaoDaiJia(String[] qPL_String, String targetStr) {
		int[] result=new int[2];
		
		int zuiXiaoDaiJia=jiSuanDaiJia(qPL_String[0],targetStr);
		int daiJiaWeiZhi=0;
		
		int tempDJ;
		for(int i=1; i<qPL_String.length; i++) {
			tempDJ = jiSuanDaiJia(qPL_String[i],targetStr);
			if(tempDJ < zuiXiaoDaiJia) {
				zuiXiaoDaiJia=tempDJ;
				daiJiaWeiZhi=i;
			}
		}
		
		result[0]=zuiXiaoDaiJia;
		result[1]=daiJiaWeiZhi;
		return result;
	}
	public int jiSuanDaiJia(String qPL_Str, String targetStr) {
		int result=0;
		
		char[] qPL_Char=qPL_Str.toCharArray();
		char[] tarStr_Char=targetStr.toCharArray();
		
		int preDJ=0;
		for(int i=0; i<tarStr_Char.length; i++) {
			for(int j=0; j<qPL_Char.length; j++) {
				if(tarStr_Char[i]==qPL_Char[j]) { 
					result+=Math.abs(j-preDJ);
					preDJ=j;
					break;
				}
			}
		}
		
		return result;
	}
	
}
