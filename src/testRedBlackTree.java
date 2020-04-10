import java.util.Arrays;
import java.util.Random;

import javaDataStructure.RedBlackTree;

/**
 * 
 * @author  only for test Red Black Tree
 *
 */
public class testRedBlackTree {
	public static void main(String[] args) {
		
		RedBlackTree rhTree=new RedBlackTree();

		Random random=new Random();
	
		rhTree.add(1000);
//		rhTree.add(2);
//		rhTree.add(3);
//		rhTree.add(4);
//		rhTree.add(5);
		System.out.println(1000);

		int i=0;
		
		int[] ceshi=new int[100];
		while(i<ceshi.length) {
			ceshi[i]=random.nextInt(1000);
			System.out.print(ceshi[i] + ", ");
			rhTree.add(ceshi[i++]);
			if(i%15==0)System.out.println();
		}
		
//		int[] ceshi=new int[] {
//				71, 741, 576, 582, 771, 571, 690, 448, 614, 927, 122, 408, 508, 541, 10, 
//				12, 638, 562, 223, 327, 857, 53, 521, 686, 836, 21, 582, 382, 544, 904, 
//				442, 701, 368, 100, 401, 409, 239, 886, 92, 288, 352, 710, 26, 734, 993, 
//				139, 302, 517, 753, 512, 390, 348, 568, 338, 340, 174, 118, 941, 149, 205, 
//				603, 354, 566, 475, 654, 823, 727, 533, 905, 611, 424, 44, 521, 76, 823, 
//				506, 420, 239, 900, 38, 491, 411, 12, 739, 775, 235, 481, 987, 952, 318, 
//				739, 403, 152, 517, 999, 500, 181, 512, 5, 886, 
//				};
//		i=0;
//		while(i<100) {
//			System.out.print(ceshi[i] + ", ");
//			rhTree.add(ceshi[i++]);
//		}
		
		System.out.println();
		System.out.println("================������������================");
		
		// ���������Ƿ���ȷ
		rhTree.visitAllNode();
		System.out.println("ָ��Ŀ����(1000)�Ƿ���ڣ�"+rhTree.findData(1000));
		System.out.println("���Ĵ�С��"+rhTree.size());
		System.out.println("������ȣ�"+rhTree.treeDepth());
		
		// ����ɾ���Ƿ���ȷ
		System.out.print("-------------��Ҫɾ���Ľڵ��ǣ�1000��");rhTree.remove(1000);System.out.println();

		int tempRealSum;
		for(i=0; i<ceshi.length/2; i++) {
			System.out.println("-------------��Ҫɾ���Ľڵ��ǣ�"+ ceshi[i]+"��");
			rhTree.remove(ceshi[i]);
			
//			tempRealSum=rhTree.zhongXuVisit();
//			if(realSum-tempRealSum==1) {  // Ҫô��ɾ����Ҫôֻɾ��һ��
//				realSum--;
//			}else if(realSum-tempRealSum==0){
//				
//			}else {
//				System.out.println("It is wrong!");
//				return;
//			}
		}
		
		System.out.println("ָ��Ŀ����(1000)�Ƿ���ڣ�"+rhTree.findData(1000));
		System.out.println("���Ĵ�С��"+rhTree.size());
		rhTree.visitAllNode();
	}
}

