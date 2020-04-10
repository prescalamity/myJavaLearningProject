package ForOnlineProgramming;

import java.util.LinkedList;
import java.util.Map;
//import java.util.Scanner;
//import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeSet;
import java.util.stream.Collectors;

import javax.swing.text.AbstractDocument.Content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }
}

public class Solution {
	
	{System.out.println("This is 'ForOnlineProgramming.Solution'.");}
	
	public static void main(String[] args) {
		Solution _maiN=new Solution();
		//_maiN.mainMirrorTree();
//		_maiN.intArrayToIntegerArray();
		
//		int[] pushA= {1,2,3,4,5};
//		int[] popA= {4,5,3,1,2};
//		System.out.println(_maiN.IsPopOrder(pushA,popA));
		
//		System.out.println(_maiN._VerifySquenceOfBST(new int[]{1,2,3,6,8,7,5}));
//		System.out.println(_maiN._VerifySquenceOfBST(null));
//		System.out.println(_maiN._VerifySquenceOfBST(new int[0]));
		
//		_maiN.testFindThePath();
		
//		ArrayList<String> qPL_StringList=_maiN.Permutation("ADSFG");

		
//		_maiN.useMapFindTheNumberOfMoreHalf(new int[]{1,2,3,2,2,2,5,4,2,7,7,2,2});
		
//		ArrayList<Integer> arr7=_maiN._insertionSort(new ArrayList<Integer>(Arrays.asList(4,5,1,6,2,12,7,3,8)));
		
//		ArrayList<Integer> arr7= _maiN._Quicksort(new ArrayList<Integer>(Arrays.asList(4,5,1,6,2,12,7,3,8)));
		
//		int[] arr7= _maiN.heapSort(new int[]{4,5,1,6,2,12,7,3,8});

	
//		int[] arr6=new int[]{4,5,1,6,2,12,7,3,8};
//		int[] arr7= _maiN._mergeSort(arr6,0,(arr6.length-1)/2,arr6.length-1);
//		
//		for(int ii : arr7)System.out.print(ii+",");
		
//		int[][] testFloyd= _maiN.floyd(_maiN.customArray2D());
//		System.out.println(_maiN.FirstNotRepeatingChar("asdakdjaksjdka"));
		
		
//		System.out.println( LeftRotateString( "abcdefghij", 10) );
		
//		String str="aaa";    // "aaaca" 
//		String pattern="ab*a*c*a";  // (sSum > pSum)true                   
//      //String pattern="ab*c*a";    // (sSum > pSum)false 
//		System.out.println(_maiN.match_LGY(str.toCharArray(),pattern.toCharArray()));
		
//		int[][] numberTriangle=new int[5][];
//		numberTriangle[0]=new  int[]{7};
//		numberTriangle[1]=new  int[]{3,8};
//		numberTriangle[2]=new  int[]{8,1,0};
//		numberTriangle[3]=new  int[]{2,7,4,4};
//		numberTriangle[4]=new  int[]{4,5,2,6,5};
//		int[][] maxSum=new int[5][5];
//		for(int i=0;i<maxSum.length;i++)
//			for(int j=0;j<maxSum[i].length;j++)
//				maxSum[i][j]=-1;
//		System.out.println(_maiN.dynamicProgramming(numberTriangle, maxSum, 0, 0));
		
		//_maiN.solveMaze();
		
		String string="wqweeff.g-d+e";
		//string="e";
		String[] strArr=string.split("e");
		System.out.println(strArr.length);
		for(String s:strArr) {
			System.out.print(s.length());
			System.out.println(s);
		}
		
		char[] str=new char[]{'+','2','.','3','e','-','5'};
		//System.out.println(String.valueOf(str).toUpperCase());
		System.out.println(_maiN.isNumericMatch( str ));
		
	}
	
	 public boolean isNumericMatch(char[] str) {
		 String contentStr=String.valueOf(str).toUpperCase();
		 System.out.println(contentStr);
		 
		 String patternStr="[-+]?\\d*(\\.\\d+)?(E{1}[-+]?\\d+)?";       //       //
		 
		 //patternStr="[\\+\\-]?\\d*(\\.\\d+)?([eE][\\+\\-]?\\d+)?";
		 
		 //Pattern r = Pattern.compile(patternStr);    // 创建 Pattern 对象
		 
	     //Matcher m = r.matcher(string);              // 现在创建 matcher 对象
		 
		 if(Pattern.matches(patternStr, contentStr)) {
			 return true;
		 }else {
			 return false;
		}
	    
	 }
	
	
	/**
	 * 迷宫寻路，找出走出迷宫的最佳路线; 
	 * 走过的路不能再走，以解决往回走的问题
	 * 回溯算法
	 */
	void solveMaze() {
		ArrayList<Integer[]> bestPath=new ArrayList<Integer[]>();
		Stack<Integer[]> pathStack=new Stack<Integer[]>();
		int[] pathLength=new int[]{-1};
		int maze[][]={  
			    {0,0,1,1,1,1,1,1,1,1}, //0  
			    {1,0,0,1,1,0,0,1,0,1}, //1  
			    {1,0,0,1,0,0,0,1,0,1}, //2  
			    {1,0,0,0,0,1,1,0,0,1}, //3  
			    {1,0,1,1,1,0,0,0,0,1}, //4  
			    {1,0,0,0,1,0,0,0,0,1}, //5  
			    {1,0,1,0,0,0,1,0,0,1}, //6  
			    {1,0,1,1,1,0,1,1,0,1}, //7  
			    {1,1,0,0,0,0,0,0,0,0}, //8  
			    {1,1,1,1,1,1,1,1,1,0}  //9  
			   //0 1 2 3 4 5 6 7 8 9   
			    };   
		pathStack.push(new Integer[]{0,0});
		maze[0][0]=1;
		
		findMazePath(bestPath, pathStack, pathLength, maze);

		if(bestPath.isEmpty()) {
			System.out.println("The path does not exist!");
		}else {
			while (!bestPath.isEmpty()) {
				Integer[] temp=bestPath.remove(0);
				System.out.println("("+temp[0]+","+temp[1]+")");
			}
		}
	}
	void findMazePath(ArrayList<Integer[]> bestPath, Stack<Integer[]> pathStack, int[] pathLength, int[][] maze) {
		if(pathStack.isEmpty())return;
		Integer[] tempPoint=pathStack.peek();
		if(tempPoint[0]==9 && tempPoint[1]==9) { 
			int temp=pathStack.size();
			if(pathLength[0]==-1 || pathLength[0]>temp) {
				System.out.println("The path has changed. "+pathLength[0]+","+temp);
				pathLength[0]=temp;
				bestPath.clear();
				for(Integer[] iA : pathStack) bestPath.add(iA);
			}
			return;
		}
		
		if(tempPoint[0]-1>-1 && maze[tempPoint[0]-1] [tempPoint[1]]==0) {  //上节点
			pathStack.push(new Integer[]{tempPoint[0]-1,tempPoint[1]});
			maze[tempPoint[0]-1] [tempPoint[1]]=1;
			findMazePath(bestPath, pathStack, pathLength, maze);
			pathStack.pop();
			maze[tempPoint[0]-1] [tempPoint[1]]=0;
		}
		if(tempPoint[1]+1<10 && maze[tempPoint[0]] [tempPoint[1]+1]==0) {  //右节点
			pathStack.push(new Integer[]{tempPoint[0], tempPoint[1]+1});
			maze[tempPoint[0]] [tempPoint[1]+1]=1;
			findMazePath(bestPath, pathStack, pathLength, maze);
			pathStack.pop();
			maze[tempPoint[0]] [tempPoint[1]+1]=0;
		}
		if(tempPoint[0]+1<10 && maze[tempPoint[0]+1] [tempPoint[1]]==0) {  //下节点
			pathStack.push(new Integer[]{tempPoint[0]+1,tempPoint[1]});
			maze[tempPoint[0]+1] [tempPoint[1]]=1;
			findMazePath(bestPath, pathStack, pathLength, maze);
			pathStack.pop();
			maze[tempPoint[0]+1] [tempPoint[1]]=0;
		}
		if(tempPoint[1]-1>-1 && maze[tempPoint[0]] [tempPoint[1]-1]==0) {  //左节点
			pathStack.push(new Integer[]{tempPoint[0],tempPoint[1]-1});
			maze[tempPoint[0]] [tempPoint[1]-1]=1;
			findMazePath(bestPath, pathStack, pathLength, maze);
			pathStack.pop();
			maze[tempPoint[0]] [tempPoint[1]-1]=0;
		}
		
	}

	
	/**
	 * 动态规划求解数字三角形最大路径
	 * https://blog.csdn.net/ailaojie/article/details/83014821
	*/
	public int dynamicProgramming(int[][] numberTriangle,int[][] maxSum,int r,int j) {
		
		if(maxSum[r][j]!=-1)return maxSum[r][j];
		
		if(r==numberTriangle.length-1) {
			maxSum[r][j]= numberTriangle[r][j];
		}else {
			int x=dynamicProgramming(numberTriangle, maxSum, r+1, j);
			int y=dynamicProgramming(numberTriangle, maxSum, r+1, j+1);
			maxSum[r][j] = numberTriangle[r][j] + (x>y?x:y);
		}

		return maxSum[r][j];  //当前值加上左右路径的累计值
	}


	public boolean match_LGY(char[] str, char[] pattern) {  // 不确定的分类次数
		if (str == null && pattern == null)return true;
		if (str == null || pattern == null)return false;

		int indexStr = 0, indexPattern = 0;
		while (true) {
			if (indexPattern + 1 == pattern.length || indexPattern + 1 < pattern.length && pattern[indexPattern + 1] != '*') { // 后面没有，或有但不是‘*’
				if (indexStr == str.length)
					return false;
				if (pattern[indexPattern] == '.' || pattern[indexPattern] == str[indexStr]) {
					indexPattern++;
					indexStr++;
				} else {
					return false;
				}
			} else if (indexPattern + 1 < pattern.length && pattern[indexPattern + 1] == '*') { // 后面有且是‘*’
				if (indexStr == str.length) { // 字符串为空
					indexPattern += 2;
				} else 
				if (pattern[indexPattern] == '.') { // 模式为点，且对比字符串不空

					while (indexPattern < pattern.length) { // 处理 点的跳跃
						if (indexPattern + 1 == pattern.length || pattern[indexPattern + 1] != '*') {
							break;
						} else {
							indexPattern += 2;
						}
					}

					if (indexPattern == pattern.length) {
						return true;
					} else {

						while (indexStr < str.length) {
							if (pattern[indexPattern] == str[indexStr])
								break;
							indexStr++;

						}

						if (indexStr == str.length)return false;
						// System.out.println("I am run. "+pattern[indexPattern]);
						indexPattern++;
						indexStr++;
					}
				} else 
				if (pattern[indexPattern] != '.') { // 字符串不空，模式为不点，且后面为'*'
					if (pattern[indexPattern] == str[indexStr]) {
						
					} else {
						indexPattern += 2;
					}
				}
			}

			if (indexStr == str.length && indexPattern == pattern.length)
				return true;
			if (indexStr != str.length && indexPattern == pattern.length)
				return false;
		}
	}

	
	/**
	 *  递归可以简化判断情况（如判断条件很多的时候，容易混淆条件逻辑）；
	 *  处理复杂的分支情形（如情况分支数量不确定）
	 * @param str
	 * @param pattern
	 * @return
	 */
	public boolean match(char[] str, char[] pattern) {    
		if (str == null || pattern == null) {
			return false;
		}
		int strIndex = 0;
		int patternIndex = 0;
		return matchCore(str, strIndex, pattern, patternIndex);
	}
	public boolean matchCore(char[] str, int strIndex, char[] pattern, int patternIndex) {
		// 有效性检验：str到尾，pattern到尾，匹配成功
		if (strIndex == str.length && patternIndex == pattern.length) {     // 先处理没有下一个的情况
			return true;
		}
		// pattern先到尾，匹配失败
		if (strIndex != str.length && patternIndex == pattern.length) {
			return false;
		}
		// 以上情况先判断，确保此时  模式  有下一个，以处理 “ * ”情况（最难处理的情况），
		// 模式下一个是*，且字符串第1个跟模式第1个匹配,分3种匹配模式；如不匹配，模式后移2位
		if (patternIndex + 1 < pattern.length && pattern[patternIndex + 1] == '*') {
			if ((strIndex != str.length && pattern[patternIndex] == str[strIndex]) || (pattern[patternIndex] == '.' && strIndex != str.length)) {
				// 运用“||”的短路计算，处理“ * ”情况
				return matchCore(str, strIndex, pattern, patternIndex + 2)             // 模式后移2，视为x*匹配0个字符
						|| matchCore(str, strIndex + 1, pattern, patternIndex + 2)     // 视为模式匹配1个字符
						|| matchCore(str, strIndex + 1, pattern, patternIndex);        // *匹配1个，再匹配str中的下一个
			} else {
				return matchCore(str, strIndex, pattern, patternIndex + 2);
			}
		}
		// 模式下一个不是*，且字符串第1个跟模式第1个匹配，则都后移1位，否则直接返回false
		if ((strIndex != str.length && pattern[patternIndex] == str[strIndex])
				|| (pattern[patternIndex] == '.' && strIndex != str.length)) {
			return matchCore(str, strIndex + 1, pattern, patternIndex + 1);
		}
		
		return false;    // 这里还包含“字符串到头但模式没到头”的情况的处理，以及出上述情况的任何其他情况
	}
	
	
    public static String LeftRotateString(String str,int n) {
        if(str==null||str.length()==0)return str;
        String result="";
        int realN=n%str.length();
        for(int i=realN; i<str.length(); i++) result+=str.substring(i, i+1);
        for(int i=0; i<realN; i++) result+=str.substring(i, i+1);
    	return result;
    }
	
	
	public int FirstNotRepeatingChar(String str) {
		if(str==null)return -1;
		if(str.length()==0)return -1;
		if(str.length()==1)return 0;	

		char[] cA=str.toCharArray();
		ArrayList<Character> al=new ArrayList<Character>();
		for(char c : cA)al.add(c);
		
		char cIndex='a';
		boolean hasChar=false;
		while (!al.isEmpty()) {
			cIndex=al.remove(0);
			int i=0;
			int sum=0;
			while (i<al.size()) {
				if(cIndex==al.get(i)) {
					sum++;
					al.remove(i);
					i--;
				}
				i++;
			}
			if(sum==0) {hasChar=true; break;}
		}
		
		if(hasChar) {
			int i=0;
			for(; i<cA.length; i++) {
				if(cA[i]==cIndex)break;
			}
			return i;
		}else {
			return -1;
		}
	}
	
	/**
	 * 返回定制的目标二维数组（矩阵），即该函数要根据需求重新编写而产生新的目标二维数组
	 * @return
	 */
	public int[][] customArray2D() {
		int[][] resultArray=new int[7][7];
		
		//resultArray[0][0]=0;
		resultArray[0][2]=8;resultArray[0][3]=20;resultArray[2][3]=31;resultArray[1][2]=11;
		resultArray[1][3]=32;resultArray[4][3]=14;resultArray[1][4]=33;resultArray[1][5]=13;
		resultArray[4][5]=34;resultArray[4][6]=15;resultArray[5][6]=16;
		
		for(int i=0; i<resultArray.length; i++) {
			for(int j=0; j<resultArray[0].length; j++) {
				if(resultArray[i][j]==0 && i!=j) resultArray[i][j]=374;
				if(resultArray[i][j]!=0 && resultArray[i][j]!=374) resultArray[j][i]=resultArray[i][j];
			}
		}
		
//		for(int[] i : resultArray) {
//			for(int j : i) System.out.print(" "+j+",");
//			System.out.println();
//		}
		
//		System.out.println();
//		System.out.println("\r==================");
//		int[][] ceshi=new int[4][4];
//		ceshi[0]= new int[]{0,1,2};
//		ceshi[2]= new int[]{4,5,6,7,8};
//		ceshi[3]= new int[]{9,10,11,12};
//		for(int[] i : ceshi) {
//			System.out.println();
//			for(int j : i) System.out.print(" "+j+",");
//		}
		
		return resultArray;
	}
	
	/**
	 * 弗洛伊德(floyd)算法求图中所有点对之间的最短路径；
	 * 其中‘-1’表示两点之间目前还没有联通的路径；
	 * 结论：如果A点到G点之间有最短路径，那么被这条最短路径所包含的所有点之间的最短路径一定是这条最短路径的子集（或子路段）。
	 */
	public int[][] floyd(int[][] initialGraph){
		int[][] resultGrapg=initialGraph.clone();
		
//		for(int[] i : resultGrapg) {
//			for(int j : i) System.out.print(" "+j+",");
//				System.out.println();
//		}
		
		// 加的点要写在最外层，因为写在内层的话，所加的点在i，j（如：1，2）两点间的路径是不能叠加的，而在最外层才能叠加增加的点
		// 因为k是在最外层的，所以会把所有的i到j都处理完后，才会移动到下一个k，
		// 每加一个中间点，即计算出加该点后，所有因为加该点而变短的 “所有路径”。
		for(int k=0; k<resultGrapg[0].length; k++) {       
			for(int i=0; i<resultGrapg[0].length; i++) {
				for(int j=0; j<resultGrapg[0].length; j++) {
					if(resultGrapg[i][j]>resultGrapg[i][k]+resultGrapg[k][j]) {
						resultGrapg[i][j]=resultGrapg[i][k]+resultGrapg[k][j];
					}
				}
			}
		}
		
//		System.out.println("\r==================\r");
//		for(int[] i : resultGrapg) {
//			for(int j : i) System.out.print(" "+j+",");
//			System.out.println();
//		}
		
		return resultGrapg;
	}
	
	/**
	 * merge sort, such as: low=0, middle=(0+arr.length-1)/2, high=arr.length-1, (O(nlogn))
	 * @param arr
	 * @param low
	 * @param middle
	 * @param high
	 * @return
	 */
	public int[] _mergeSort(int[] arr, int low, int middle, int high) {
		if(low==high)return new int[] {arr[high]};
		
		int[] lArr, rArr;       // 左右递归
		if(low<middle) {
			lArr = _mergeSort(arr,low,(low+middle)/2,middle);
		}else {
			lArr=new int[] {arr[low]};
		}
		if((middle+1)<high) {
			rArr = _mergeSort(arr,middle+1,(middle+1+high)/2,high);
		}else {
			rArr = new int[] {arr[high]};
		}
		
		// 合并左右
		int[] result=new int[high-low+1];
		for(int i=0,j=0,k=0; i<result.length; i++) {
			if(lArr[j]<rArr[k]) {
				result[i]=lArr[j++];
			}else {
				result[i]=rArr[k++];
			}
			if(j==lArr.length) for(;k<rArr.length;)result[++i]=rArr[k++];
			if(k==rArr.length) for(;j<lArr.length;)result[++i]=lArr[j++];
		}
			
		return result;
	}
	
	/**
	 *   heap sort, (O(nlogn))
	 * @param arr
	 */
	public int[] heapSort(int[] arr) {
		
        heapInsert(arr);  //构造大根堆
        
        int size = arr.length;
        while (size > 1) {
            
            swap(arr, 0, size - 1);  //固定最大值
            size--;
            //在已有序的剩余序列中构造大根堆，因为部分有序而不用重新全部构造，减小比较，最终降低时间复杂度
            heapify(arr, 0, size);   
        }
        return arr;
	}
	/**
	 * 一个序列即现成的按层访问的未“大顶堆”化的堆，剩下的进行大顶堆化即可
	 * 构造大根堆，通过新插入的数依次和该路径的父节点比较，实现建堆过程，关键是找父子节点的索引规律
	 * @param arr
	 */
    public void heapInsert(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            //当前插入的索引
            int currentIndex = i;
            //父结点索引
            int fatherIndex = (currentIndex - 1) / 2;
            //如果当前插入的值大于其父结点的值,则交换值，并且将索引指向父结点
            //然后继续和上面的父结点值比较，直到不大于父结点，则退出循环
            while (arr[currentIndex] > arr[fatherIndex]) {
               
                swap(arr, currentIndex, fatherIndex);      //交换当前结点与父结点的值
                
                currentIndex = fatherIndex;                //将当前索引指向父索引
                 
                fatherIndex = (currentIndex - 1) / 2;      //重新计算当前索引的父索引
            }
        }
    }
    /**
     * 将剩余的数构造成大根堆（通过顶端的数下降）
     * @param arr
     * @param index
     * @param size
     */
    public void heapify(int[] arr, int index, int size) {
        int left = 2 * index + 1;
        int right = 2 * index + 2;
        while (left < size) {
            int largestIndex;
            //判断孩子中较大的值的索引（要确保右孩子在size范围之内）
            if (arr[left] < arr[right] && right < size) {
                largestIndex = right;
            } else {
                largestIndex = left;
            }
            //比较父结点的值与孩子中较大的值，并确定最大值的索引
            if (arr[index] > arr[largestIndex]) {
                largestIndex = index;
            }
            //如果父结点索引是最大值的索引，那已经是大根堆了，则退出循环
            if (index == largestIndex) {
                break;
            }
            //父结点不是最大值，与孩子中较大的值交换
            swap(arr, largestIndex, index);
            //将索引指向孩子中较大的值的索引
            index = largestIndex;
            //重新计算交换之后的孩子的索引
            left = 2 * index + 1;
            right = 2 * index + 2;
        }
 
    }
    /**
     * 交换数组arr中两个元素的值
     * @param arr
     * @param i
     * @param j
     */
    public void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

	/**
	 * Insertion sort, from small to large (O(n^2))
	 * @param arrayL
	 * @return
	 */
	public ArrayList<Integer> _insertionSort(ArrayList<Integer> arrayL){
		
		if(arrayL==null) return null;
		if(arrayL.size()==0) return null;
		
		ArrayList<Integer> resultList=new ArrayList<Integer>();
		
		resultList.add(arrayL.remove(0));
		
		int tempInt;
		while (!arrayL.isEmpty()) {
			tempInt=arrayL.remove(0);
			for(int i=resultList.size()-1; i>-1; i--) {
				if(i==(resultList.size()-1) && tempInt>=resultList.get(i)) {
					resultList.add(tempInt);
					break;
				}else if(tempInt>=resultList.get(i)) {
					resultList.add(i+1, tempInt);
					break;
				}else if(i==0) {
					resultList.add(0, tempInt);
					break;
				}
			}
		}
		return resultList;
	}
	
	
	/**
	 * 使用递归实现快速排序  (O(nlogn))
	 * @param arrayL
	 * @return
	 */
	public ArrayList<Integer> _Quicksort(ArrayList<Integer> arrayL) {
		
		if(arrayL==null)return null;
		if(arrayL.size()<2)return arrayL;
		
		ArrayList<Integer> result=new ArrayList<Integer>();
		ArrayList<Integer> sortedLeft=new ArrayList<Integer>();
		ArrayList<Integer> sortedRight=new ArrayList<Integer>();
		
		int mid=arrayL.remove(0);
		while(!arrayL.isEmpty()) {
			if(arrayL.get(0)<=mid) {
				sortedLeft.add(arrayL.remove(0));
			}else {
				sortedRight.add(arrayL.remove(0));
			}
		}
		
		result.addAll(_Quicksort(sortedLeft));
		result.add(mid);
		result.addAll(_Quicksort(sortedRight));
		
		return result;
	} 
	
	public void useMapFindTheNumberOfMoreHalf(int [] array) {
        HashMap<Integer,Integer > counrtMap=new HashMap<>();
        for(int i=0; i<array.length; i++){
        	if(counrtMap.containsKey(array[i])) {
        		counrtMap.put(array[i],counrtMap.get(array[i])+1) ;
        	}else {
        		counrtMap.put(array[i], 1) ;
			}
        }
        
        for( Map.Entry<Integer, Integer> entry : counrtMap.entrySet()) 
        	System.out.println("KEY:" + entry.getKey() + ", " + "VALUE:" + entry.getValue());
        
        boolean notExist=true;
        for(int i : counrtMap.keySet()) 
        	if(counrtMap.get(i)>array.length/2) {
        		System.out.println(i+"-->sum:"+counrtMap.get(i));
        		notExist=false;
        		break;
        	}
        if(notExist)System.out.println("The number does not exist.");
        
	}
	
	public ArrayList<String> Permutation(String str) {
		ArrayList<String> qPL_StringList=new ArrayList<String>();
        TreeSet<String> qPL_TreeSet=new TreeSet<String>();
        
        if(str==null || str.length()==0){return qPL_StringList;}
        
		ArrayList<Character> testCharList = new ArrayList<Character>();
		for (char c : str.toCharArray()) {testCharList.add(c);}
        
		quanPaiLieDiGui("", testCharList, qPL_TreeSet);   //使用递归实现全枚举
		
		//for(String str1 : qPL_TreeSet)qPL_StringList.add(str1);
        qPL_StringList.addAll(qPL_TreeSet);
		return qPL_StringList;
    }
	public void quanPaiLieDiGui( String yiPaiXu, ArrayList<Character> testCharToStr, TreeSet<String> qPL_TreeSet) {
		if(testCharToStr.size()<2) {
			qPL_TreeSet.add( yiPaiXu+testCharToStr.remove(0) );
		}else {
			for(int i=0;i<testCharToStr.size();i++) {
				ArrayList<Character> tempCharList=new ArrayList<Character>(testCharToStr);  // 深复制testCharToStr副本
				String tempStr = yiPaiXu + tempCharList.remove(i);
				quanPaiLieDiGui(tempStr, tempCharList , qPL_TreeSet);
				
			}
		}
	}
	
	
	public void testFindThePath() {
		TreeNode root;
		root=new TreeNode(8);
		root.left=new TreeNode(6);
		root.right=new TreeNode(13);
		root.left.left=new TreeNode(5);
		root.left.right=new TreeNode(7);
		root.right.left=new TreeNode(9);
		root.right.right=new TreeNode(3);
		root.right.left.left=new TreeNode(10);
		queueVisitAllNode(root);
		
		int treeDepth=findTreeDepth(root);
		System.out.println("the depth of tree is "+ treeDepth+" by 'findTreeDepth'.");
		
		ArrayList<ArrayList<Integer>> resultList=FindPath(root, 21);
		//System.out.println("I have run!------size=" + resultList.size());
		
		for(ArrayList<Integer> tes : resultList) {
			for(Integer t : tes) {
				System.out.print(t+",");
			}
			System.out.println();
		}

		System.out.println();
		
		
//		TreeNode root1;
//		root1=new TreeNode(8);
//		root1.left=new TreeNode(13);
//		root1.right=new TreeNode(10);
//		//root1.left.left=new TreeNode(5);
//		//root1.left.right=new TreeNode(7);
//		root1.right.left=new TreeNode(9);
//		root1.right.right=new TreeNode(3);
//		queueVisitAllNode(root1);
//		ArrayList<ArrayList<Integer>> resultList1=FindPath( root1, 21);
//		//System.out.println("I have run!------size=" + resultList1.size());
//
//		for(ArrayList<Integer> tes : resultList1) {
//			for(Integer t : tes) {
//				System.out.print(t+",");
//			}
//			System.out.println();
//		}
		
	}
	
	/**
	 * finding the depth of tree. 
	 * @param root
	 * @return 
	 */
	public int findTreeDepth(TreeNode root) {
		
		if(root==null)return 0;
		
		int leftDepth=findTreeDepth(root.left);
		
		int rightDepth=findTreeDepth(root.right);
		
		return 1+(leftDepth>rightDepth?leftDepth:rightDepth);
	}
	
	public ArrayList<ArrayList<Integer>> FindPath1(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> arr=new ArrayList<ArrayList<Integer>>();
        if(root==null)
            return arr;
        ArrayList<Integer> a1=new ArrayList<Integer>();
        int sum=0;
        pa(root,target,arr,a1,sum);
        return arr;
    }
    public void pa(TreeNode root,int target,ArrayList<ArrayList<Integer>> arr, ArrayList<Integer> a1,int sum){
        if(root==null)
            return ;
        sum+=root.val;
        
        if(root.left==null&&root.right==null){
            if(sum==target)
                { a1.add(root.val);
                arr.add(new ArrayList<Integer>(a1));
                a1.remove(a1.size()-1);
               
            }
          return ;
             
        }
        
        a1.add(root.val);
        pa(root.left,target,arr,a1,sum);
        pa(root.right,target,arr,a1,sum);
        a1.remove(a1.size()-1);
         
    }
	
	
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> resultList=new ArrayList<ArrayList<Integer>>();
        if(root==null )return resultList;
       
        ArrayList<TreeNode> pathNodeList=new ArrayList<TreeNode>();
        int counter=0;
        
        pathNodeList.add(root);
        counter+=pathNodeList.get(pathNodeList.size()-1).val;
        if(counter>target)return resultList;
       
        bianliShu(resultList, pathNodeList, counter, target);
        
        if(resultList.size()<2)return resultList;

        Collections.sort(resultList, new Comparator< ArrayList<Integer>>() {
            @Override  
            public int compare( ArrayList<Integer> o1,  ArrayList<Integer> o2) {  // 确定o1和o2对象要不要调换位置，返回1则调换，-1不调换

                //return o1.size()-o2.size();
                 //return o2.size()-o1.size();
                // return o2.getAge().compareTo(o1.getAge());
            	if(o1.size()<o2.size()) return 1;      
            	return -1;
            }
        });
        
//        for(int i=0; i<resultList.size(); i++){
//            for(int j=0; j<resultList.size()-i-1; j++){
//                if(resultList.get(j).size() < resultList.get(j+1).size()){
//                    ArrayList<Integer> tempPathNode1=resultList.get(j);
//                    resultList.set(j, resultList.get(j+1));
//                    resultList.set(j+1, tempPathNode1);
//                }
//            }
//        }
        return resultList;
    }
    public void bianliShu(ArrayList<ArrayList<Integer>> resultList, ArrayList<TreeNode> pathNodeList, int counter, int target){

        if(pathNodeList.get(pathNodeList.size()-1).left==null && pathNodeList.get(pathNodeList.size()-1).right==null){
        	//System.out.println("Counter of the leaf is :"+pathNodeList.get(pathNodeList.size()-1).val+"------"+counter);
        	if(counter==target ) {
	            ArrayList<Integer> tempPathNode=new ArrayList<Integer>();
	            for(TreeNode tn : pathNodeList)tempPathNode.add(tn.val);
	            resultList.add(new ArrayList<Integer>(tempPathNode));
            }
        }
        
        if(pathNodeList.get(pathNodeList.size()-1).left!=null && counter<target){
            pathNodeList.add(pathNodeList.get(pathNodeList.size()-1).left);           // path???????????
            int tempCounter=counter+pathNodeList.get(pathNodeList.size()-1).val;
            bianliShu(resultList, pathNodeList, tempCounter, target);
            
        }
        if(pathNodeList.get(pathNodeList.size()-1).right!=null && counter<target){
            pathNodeList.add(pathNodeList.get(pathNodeList.size()-1).right);
            int tempCounter=counter+pathNodeList.get(pathNodeList.size()-1).val;
            bianliShu(resultList, pathNodeList, tempCounter, target);
        }
        
        pathNodeList.remove(pathNodeList.size()-1);
    }
    
    
	
	/**
	 * 验证指定序列是不是后续遍历序列
	 * @param sequence
	 * @return
	 */
	public boolean _VerifySquenceOfBST(int [] sequence) {
		if(sequence==null)return false;
		if(sequence.length==0)return false;
		int theIndexStart=0,theCursorIndex=theIndexStart-1,rootIndex=sequence.length-1;
		boolean isBinaryTree=true;
		for(int i=0; i<rootIndex; i++) {
			if(sequence[i]<sequence[rootIndex]) {
				theCursorIndex=i;
			}else {
				break;
			}
		}
		for(int i=theCursorIndex+1; i<rootIndex; i++) {
			if(sequence[i]<sequence[rootIndex]) {
				isBinaryTree=false;
				break;
			}
		}
		return isBinaryTree && vsOB(theIndexStart, theCursorIndex, rootIndex, sequence);
	}
	public boolean vsOB(int theIndexStart,int theCursorIndex, int rootIndex ,int [] sequence) {

		boolean tLeft=true,tRight=true;
		
		if(theIndexStart < theCursorIndex) {
			int theIndexStartLeft = theIndexStart, theCursorIndexLeft = theIndexStartLeft-1, rootIndexleft = theCursorIndex;
			for(int i=theIndexStartLeft; i<rootIndexleft; i++) {
				if(sequence[i]<sequence[rootIndexleft]) {
					theCursorIndexLeft=i;
				}else {
					break;
				}
			}	
			for(int i=theCursorIndexLeft+1; i<rootIndexleft; i++) {
				if(sequence[i]<sequence[rootIndexleft]) {
					return false;
				}
			}
			tLeft = vsOB(theIndexStartLeft, theCursorIndexLeft,  rootIndexleft, sequence);
		}
		if(theCursorIndex+1 < rootIndex-1) {
			int theIndexStartRight = theCursorIndex+1, theCursorIndexRight = theIndexStartRight-1, rootIndexRight = rootIndex-1;
			for(int i=theIndexStartRight; i<rootIndexRight; i++) {
				if(sequence[i]<sequence[rootIndexRight]) {
					theCursorIndexRight=i;
				}else {
					break;
				}
			}	
			for(int i=theCursorIndexRight+1; i<rootIndexRight; i++) {
				if(sequence[i]<sequence[rootIndexRight]) {
					return false;
				}
			}
			tRight = vsOB(theIndexStartRight, theCursorIndexRight,  rootIndexRight, sequence);
		}
		
		return tLeft && tRight;
	}
	
	public boolean VerifySquenceOfBST(int [] sequence) {
        if(sequence.length==0)return false;
        for(int i=0; i< sequence.length; i++){
            TreeNode binaryTreeRoot = bluidBinaryTree( i, sequence);
            ArrayList<Integer> postSequence = new ArrayList<Integer>();
            postOrder(postSequence, binaryTreeRoot);
            //PrintFromTopToBottom(binaryTreeRoot);
            System.out.println("================================");
            if(arrayEquals(postSequence, sequence))return true;
        }
        return false;
    }
    public TreeNode bluidBinaryTree(int rootI, int[] sequence){
        TreeNode root=new TreeNode(sequence[rootI]);
        for(int i=0; i<sequence.length; i++){
            if(i!=rootI){
                setNewDode(sequence[i], root);
            }
        }
        return root;
    }
    public void setNewDode(int nodeValue, TreeNode root) {
	    if (nodeValue<root.val) {
	    	if(root.left==null) {
	    		root.left=new TreeNode(nodeValue);
	    	}else {
	    		setNewDode(nodeValue, root.left);
	    	}
    	} else {
	    	if(root.right==null) {
	    		root.right=new TreeNode(nodeValue);
	    	}else {
	    		setNewDode(nodeValue, root.right);
	    	}
    	}
    }
    public void postOrder(ArrayList<Integer> postSequence, TreeNode root){    //树的后续遍历
        if(root.left!=null)postOrder(postSequence, root.left);
        if(root.right!=null)postOrder(postSequence, root.right);
        postSequence.add(root.val);
        
      	System.out.println(root.val);
    }
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {          // 从顶部到底部按层访问树
        ArrayList<Integer> resultArrayList=new ArrayList<Integer>();
        if(root==null)return resultArrayList;
        Queue<TreeNode> aQueue=new LinkedList<TreeNode>();
        aQueue.offer(root);
        while(!aQueue.isEmpty()){
            TreeNode tempTN=aQueue.poll();
            if(tempTN.left!=null)aQueue.add(tempTN.left);
            if(tempTN.right!=null)aQueue.add(tempTN.right);
            resultArrayList.add(tempTN.val);
        }
        for(int i : resultArrayList) System.out.println(i);
        
        return resultArrayList;
    }
    public boolean arrayEquals(ArrayList<Integer> a, int[] b){
        for(int i=0; i<b.length; i++){
            if(a.get(i)!=b[i])return false;
        }
        return true;
    }
	
    
    
    public boolean IsPopOrder(int [] pushA,int [] popA) {
    	
        Stack<Integer> aStack=new Stack<Integer>();
        aStack.push(pushA[0]);
        
        int pushIndex=1;
        int popIndex=0;
        
        while (popIndex<popA.length) {
			
        	if(popA[popIndex]==aStack.peek()) {
        		aStack.pop();
        	}else {
				for(;pushIndex<pushA.length;pushIndex++) {
					aStack.push(pushA[pushIndex]);
					if(popA[popIndex]==aStack.peek()) {
						aStack.pop();
						break;
					}
				}
				if(pushIndex==pushA.length) {
					return false;
				}

				pushIndex++;
			}
        	
        	popIndex++;
		}
        
        return true;
    }

	/**
	 *   int[] 转 Integer[]; Collections.sort(arrayList); Arrays.sort(arr);
	 */
	public void intArrayToIntegerArray() {
		
		int[] arr= {45,6,12,8,20,78,49};
		for(int i : arr) System.out.print(i+",");
		System.out.println();
		
		ArrayList<Integer> arrayList=new ArrayList<Integer>();
		
		//List<Integer> list1 = Arrays.stream(data).boxed().collect(Collectors.toList());
		arrayList = (ArrayList<Integer>) Arrays.stream(arr).boxed().collect(Collectors.toList());  //int[] 转 ArrayList<Integer>
		
		//Collections.sort(arrayList);   
		
		Collections.sort(arrayList, new Comparator<Integer>() {
			 @Override public int compare(Integer element1, Integer element2) {return element2-element1;}});
		//Collections.sort(arrayList, Collections.reverseOrder());
		for(int i : arrayList) System.out.print(i+",");
		
		System.out.println();
		
		// List<Integer> ? int[]
        // int[] arr1 = list1.stream().mapToInt(Integer::valueOf).toArray();
		
		Integer[] integers1 = Arrays.stream(arr).boxed().toArray(Integer[]::new);  // int[] 转 Integer[]
		Arrays.sort(integers1);    // 
//		Arrays.sort(integers1, new Comparator<Integer>() {
//			@Override
//			public int compare(Integer o1, Integer o2) {
//				return o1-o2;
//			}
//		});
		for(int i : integers1) System.out.print(i+",");
		
		System.out.println();
		
		Arrays.sort(integers1,Collections.reverseOrder());      
		arr=Arrays.stream(integers1).mapToInt(Integer::valueOf).toArray();     // Integer[]  转   int[]
		for(int i : arr) System.out.print(i+",");
	}
	
	
	
	public void mainMirrorTree() {
		
		TreeNode root;
		root=new TreeNode(8);
		root.left=new TreeNode(6);
		root.right=new TreeNode(10);
		root.left.left=new TreeNode(5);
		root.left.right=new TreeNode(7);
		root.right.left=new TreeNode(9);
		root.right.right=new TreeNode(11);
		queueVisitAllNode(root);

		TreeNode mRoot = Mirror(root);         // 可用于对树的深度clone
		queueVisitAllNode(mRoot);
		
		Mirror1(root);
		queueVisitAllNode(root);              
		
	}
	
	public void queueVisitAllNode(TreeNode root) {
		
		Queue<TreeNode> queueTree=new LinkedList<TreeNode>();
		
		queueTree.add(root);
		while (!queueTree.isEmpty()) {
			TreeNode tempNode=queueTree.poll();
			if(tempNode.left!=null)queueTree.add(tempNode.left);
			if (tempNode.right!=null) queueTree.add(tempNode.right);
			System.out.print(tempNode.val+" ");
		}
		System.out.println();
	}
	
	public TreeNode Mirror(TreeNode root) {
		TreeNode mRoot=new TreeNode(root.val);
		LR_excahnge(mRoot,root);
		return mRoot;
	}
	
	public void LR_excahnge( TreeNode mRoot, TreeNode root) {
		if(root.left!=null) {
			mRoot.right=new TreeNode(root.left.val);
			LR_excahnge(mRoot.right,root.left);
		}	
		if(root.right!=null) {
			mRoot.left=new TreeNode(root.right.val);
			LR_excahnge(mRoot.left,root.right);
		}
	}
	
	public void Mirror1(TreeNode root) {
        if(root!=null){
            TreeNode temp = root.left;
            root.left=root.right;
            root.right=temp;
            Mirror1(root.left);
            Mirror1(root.right);
       }
	}

	
}




