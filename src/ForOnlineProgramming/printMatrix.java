package ForOnlineProgramming;

import java.util.ArrayList;

public class printMatrix {

	public static void main(String[] args) {
		
		int [][] matrix= {{1,2},{3,4}};
//		System.out.println(matrix[0][0]);
//		System.out.println(matrix[0][1]);
//		System.out.println(matrix[1][1]);
//		System.out.println(matrix[1][0]);
		
		ArrayList<Integer> result= printMatrix(matrix);
		
		for(int i:result) {
			System.out.println(i);
		}
	}
	
	public static ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> result=new ArrayList<Integer>();
        boolean flag_IsHangPrint=true;
        int hangDirection=1;
        int lieDirection=1;
        int[] hangRange={-1,matrix[0].length};
        int[] lieRange={-1,matrix.length};
        int xIndex=0, yIndex=0;
        int i=0; 
        while(i<matrix[0].length * matrix.length){
            if(flag_IsHangPrint){
                if(xIndex > hangRange[0] && xIndex < hangRange[1]){
                	//System.out.println("("+yIndex +","+xIndex+")"+matrix[yIndex][xIndex]);
                    result.add(matrix[yIndex][xIndex]);
                    i++;
                    xIndex = xIndex + hangDirection;
                }else{
                    flag_IsHangPrint=false;
                    if(hangDirection==1){
                        hangDirection=-1;
                        lieRange[0]+=1;
                        xIndex --;
                        yIndex ++;
                    }else{
                        hangDirection=1;
                        lieRange[1]-=1;
                        yIndex --;
                        xIndex ++;
                    }
                    
                }
            }else{
                if(yIndex > lieRange[0] && yIndex < lieRange[1]){
                	//System.out.println("("+yIndex +","+xIndex+")"+matrix[yIndex][xIndex]);
                    result.add(matrix[yIndex][xIndex]);
                    i++;
                    yIndex = yIndex + lieDirection;
                }else{
                    flag_IsHangPrint=true;
                    if(lieDirection==1){
                        lieDirection=-1;
                        hangRange[1]-=1;
                        yIndex --;
                        xIndex --;
                    }else{
                        lieDirection=1;
                        hangRange[0]+=1;
                        xIndex ++;
                        yIndex ++;
                    }
                }
            }
        }
        return result;
    }
	
}
