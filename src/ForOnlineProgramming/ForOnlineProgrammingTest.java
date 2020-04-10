package ForOnlineProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class ForOnlineProgrammingTest  {

	public static void main(String[] args) {
		ForOnlineProgrammingTest fopt=new ForOnlineProgrammingTest();
		
//		System.out.println("=============多态=========同类型，同方法，不同结果=============");
//		classA cAA = new classA();
//		System.out.print("("+cAA.str+")");		cAA._print();
//		
//		classA cAB = new classB();
//		System.out.print("("+cAB.str+")");		cAB._print();
//
//		classA cAC = new classC();
//		System.out.print("("+cAC.str+")");		cAC._print();
//
//		
//		System.out.println("===================================");
//		
//		classB cBB = new classB();
//		cBB._print();
//		cBB._print();
		
//		int[] numbers= {45,456,321,32,3,9};
//		System.out.println( fopt.PrintMinNumber(numbers));
		
		System.out.println(fopt.GetUglyNumber_Solution(1200));
		System.out.println(fopt._GetUglyNumber_Solution(1200));
				
	}
    public int GetUglyNumber_Solution(int index) {
        if(index<1)return 0;
        
        ArrayList<Integer> resultList=new ArrayList<Integer>();
        resultList.add(1);
        int beishu2=0, beishu3=0, beishu5=0, next2Shu, next3Shu, next5Shu, nextShu;
        while(resultList.size() < index){
            next2Shu=2*resultList.get(beishu2);      //丑数=2*x+3*y+5*z
            next3Shu=3*resultList.get(beishu3);
            next5Shu=5*resultList.get(beishu5);
            nextShu=Math.min(next2Shu, Math.min(next3Shu, next5Shu));
            resultList.add( nextShu );
            if(next2Shu==nextShu)beishu2++;
            if(next3Shu==nextShu)beishu3++;
            if(next5Shu==nextShu)beishu5++;
        }
        
        return resultList.get(resultList.size()-1);
    }
    
   
    
    public int _GetUglyNumber_Solution(int index) {
        if(index<2)return index;
        
        int sumUglyNumber=1;
        int uglyNumber=1;
        int inscreseNum=2;
        while(sumUglyNumber < index){
            if(isUglyNumber(inscreseNum)){
            	//System.out.println(inscreseNum);
                uglyNumber = inscreseNum;
                sumUglyNumber++;
            }
            inscreseNum++;
        }
        System.out.println("-->"+sumUglyNumber);
        return uglyNumber;
    }
    
    public boolean isUglyNumber(int theNum){
        while(theNum%2==0){theNum=theNum/2;}
        while(theNum%3==0){theNum=theNum/3;}
        while(theNum%5==0){theNum=theNum/5;}
        if(theNum==1)return true;
        return false;
    }

	public String PrintMinNumber(int [] numbers) {
       
        if(numbers==null){return null;}
        if(numbers.length==0){return "";}
        
        Integer[] numsInteger= Arrays.stream(numbers).boxed().toArray(Integer[]::new);
        Arrays.sort(numsInteger, new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {     // 根据返回值决定是否调换o1和o2的位置 ，如果返回整数（>1）则调换，负数则不调换。
				String A=o1+""+o2;
				String B=o2+""+o1;
				return A.compareTo(B);
			}
        });
        
        StringBuilder resultStr=new StringBuilder("");
        for(int i : numsInteger)resultStr.append(i);
        
        return resultStr.toString();
    }
    
	
	
}

class classA{
	
	String str="classA";
	
	void _print() {
		System.out.println(str);
	}
}


class classB extends classA{
	
	String str="classB";
	
	void _print() {
		System.out.println("classB");
	}
}


class classC extends classA{
	
	String str="classC";
	
	void _print() {
		System.out.println("classC");
	}
}

