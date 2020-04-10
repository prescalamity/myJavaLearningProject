package javaDataStructure;

import java.util.LinkedList;
import java.util.Queue;

class TreeNode{
	public int value;
	public char colorRB='r';
	
	public TreeNode parent=null;
	public TreeNode left=null;
	public TreeNode right=null;
	
	public TreeNode(int value) {
		this.value=value;
	}
}

/**
 *   ����� int �����ݣ��������������ӣ�ɾ�������ң�https://www.jianshu.com/p/e136ec79235c
 * @author LiuYuan
 */
public class RedBlackTree {
	
//	public static void main(String[] args) {
//		System.out.println("I am OK!");
//		TreeNode ceshi=new TreeNode(1);
//		ceshi.colorRB='b';
//		System.out.println(ceshi.colorRB);
//	}
	
	private TreeNode root;
	
	private int SIZE=0;
	public int size() {
		return this.SIZE;
	}
	
	private int TREEDEPTH=0;
	public int treeDepth(){
		return this.TREEDEPTH;
	}
	
	/**
	 * ����ָ������
	 * @param value
	 * @return
	 */
	public boolean findData(int value) {
		if(this.root==null)return false;
		
		if(findTargetNode(this.root, value)==null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * only for testing
	 */
//	public int zhongXuVisit() {
//		if(this.root==null) {
//			System.out.println("(zhongXuVisit)The tree is null.");
//			return 0;
//		}
//		int result=zXbianli(this.root);
//		//System.out.println("The real sum is "+ result );
//		return result;
//	}
//	private int zXbianli(TreeNode root) {
//		int sum=1;
//		if(root.left!=null)sum+=zXbianli(root.left);
//		if(root.right!=null)sum+=zXbianli(root.right);
//		return sum;
//	}
	
	/**
	 * Visiting all tree node from top to bottom by queue, and don't return.
	 */
	public void visitAllNode() {
		if(this.root==null) {
			System.out.println("(visitAllNode)The tree is null.");
			return;
		}
		
		Queue<TreeNode> queueTree=new LinkedList<TreeNode>();
		int thisFloorSum=1;
		int nextFloorSum=0;

		queueTree.add(root);
		while (!queueTree.isEmpty()) {
			TreeNode tempNode=queueTree.poll();
			thisFloorSum--;
			
			if(tempNode.left!=null) {
				queueTree.add(tempNode.left);
				nextFloorSum++;
			}
			if (tempNode.right!=null) {
				queueTree.add(tempNode.right);
				nextFloorSum++;
			}
			System.out.print(tempNode.value+""+tempNode.colorRB+" ");
			
			if(thisFloorSum==0) {
				thisFloorSum=nextFloorSum;
				nextFloorSum=0;
				System.out.println();
			}
		}
	}
	
	/**
	 * ���ڵ����ӵ������У��Ѿ����ڵĽ����ᱻ���
	 * @param value
	 */
	public void add(int value) {
		
		TreeNode addNode=new TreeNode(value);
		
		if(this.root==null) {
			this.root=addNode;
			resetTreeInformation(true);
			return;
		}

		TreeNode targetNode=null;
		targetNode=findTargetNode(this.root, value);

		if(targetNode==null) {return;}
		if(value < targetNode.value) {
			targetNode.left=addNode;
		}else {
			targetNode.right=addNode;
		}
		addNode.parent=targetNode;        //�������ӽڵ�ĸ��ڵ�

		balance_RB_Tree(addNode);         // �������½ڵ������ƽ������
		
		resetTreeInformation(true);
		
	}
	
	/**
	 *  Atfer changing this tree, it reset the information of this tree, such as the root, size, depth.
	 * @param sizeIsPlus
	 */
	private void resetTreeInformation(boolean sizeIsPlus) {
		this.root=findRootNode(this.root);                              // ����ָ�ڵ㲻�Ǹ��ڵ�ʱ�������һظ��ڵ�
		this.root.colorRB='b';
		this.TREEDEPTH=findTreeDepth(this.root);
		if(sizeIsPlus) {
			this.SIZE++;
		}else {
			this.SIZE--;
		}
	}
	
	private TreeNode findRootNode(TreeNode nodeIndex) {
		return nodeIndex.parent==null?nodeIndex:findRootNode(nodeIndex.parent);
	}
	
	/**
	 * finding the depth of tree. 
	 * @param root
	 * @return 
	 */
	private int findTreeDepth(TreeNode root) {
		if(root==null)return 0;
		
		int leftDepth=findTreeDepth(root.left);
		int rightDepth=findTreeDepth(root.right);
		
		return 1+(leftDepth>rightDepth?leftDepth:rightDepth);
	}
	
	private TreeNode findTargetNode(TreeNode nodeIndex, int value) {
		
		if( value < nodeIndex.value && nodeIndex.left!=null) return findTargetNode(nodeIndex.left,value);
		
		if( nodeIndex.value == value ) return null;
		
		if( nodeIndex.value < value && nodeIndex.right!=null) return findTargetNode(nodeIndex.right,value);
		
		return nodeIndex;
	}
	
	private void balance_RB_Tree(TreeNode nodeIndex) {
		
		if(nodeIndex==null)return;
		
		if(nodeIndex.parent==null)return;         // �����ǰ�ڵ��Ǻ�ڵ���Ϊ���ڵ㣨��û�и��ڵ㣩���˳����ݹ�ƽ��ʱ������
		
		if(nodeIndex.parent.colorRB=='b')return;  // ������ڵ����ɫ�Ǻ�ɫ���򲻸ı����ĵ�ǰ�ڵ���ɫ
		
		// ��ʱ��ǰ�ڵ�ĸ��ڵ�Ϊ��ɫ��һ�����游����Ϊ�ڲ���֮ǰ��Ϊ�����
		// �ұ�����ڵ㲻�����ڻ������Ϊ�ڣ����ڵ����游�ڵ�����
		if(nodeIndex.parent.parent.right==null || nodeIndex.parent.parent.right.colorRB=='b') {   
			if ( nodeIndex.value< nodeIndex.parent.value) {          // ��ǰ�ڵ��ڸ��ڵ����ߣ���������ת
				rotateRight(nodeIndex.parent.parent);           
			}else {                                                  // ��ǰ�ڵ��ڸ��ڵ���ұߣ�������ת��������ת
				rotateLeft(nodeIndex.parent);            
				rotateRight(nodeIndex.parent);            
			}
			return;
		}
		
		// �������ڵ㲻�����ڻ������Ϊ�ڣ����ڵ����游�ڵ���ұ�
		if(nodeIndex.parent.parent.left==null || nodeIndex.parent.parent.left.colorRB=='b') {  
			if (nodeIndex.parent.value < nodeIndex.value) {          // ��ǰ�ڵ��ڸ��ڵ���ұߣ���������ת
				rotateLeft(nodeIndex.parent.parent);           
			}else {                                                  // ��ǰ�ڵ��ڸ��ڵ����ߣ�������ת��������ת
				rotateRight(nodeIndex.parent);            
				rotateLeft(nodeIndex.parent);            
			}
			return;
		}
		          
		if( nodeIndex.parent.parent.left.colorRB=='r' && nodeIndex.parent.parent.right.colorRB=='r') {
			nodeIndex.parent.parent.left.colorRB = 'b';
			nodeIndex.parent.parent.right.colorRB = 'b';
			nodeIndex.parent.parent.colorRB = 'r';
			balance_RB_Tree(nodeIndex.parent.parent);
			return;
		}

	}
	
	private void rotateRight(TreeNode nodeIndex) {
		TreeNode PP=nodeIndex;
		TreeNode P=nodeIndex.left;
		
		if(PP.parent==null) {	
			this.root=P;
		}else if(PP.value<PP.parent.value) {
			PP.parent.left=P;
		}else {
			PP.parent.right=P;
		}
		
		P.parent=PP.parent;
		PP.left=P.right;
		if(PP.left!=null)PP.left.parent=PP;
		P.right=PP;
		PP.parent=P;
		
		P.colorRB='b';
		PP.colorRB='r';
	}
	
	private void rotateLeft(TreeNode nodeIndex) {
		TreeNode PP=nodeIndex;
		TreeNode P=nodeIndex.right;
		
		if(nodeIndex.parent==null) {
			this.root=P;
		}else if(PP.value<PP.parent.value){
			PP.parent.left=P;
		}else {
			PP.parent.right=P;
		}
		
		P.parent=PP.parent;
		PP.right=P.left;
		if(PP.right!=null)PP.right.parent=PP;
		P.left=PP;
		PP.parent=P;
		
		P.colorRB='b';
		PP.colorRB='r';
	}
	
	/**
	 * ��Ŀ��ڵ������ɾ��
	 * @param value
	 */
	public void remove(int value) {

		if(this.root==null) return;                                // �����Ϊ�գ������κ�ɾ������

		TreeNode removeNode=findRemoveTargetNode(this.root, value);
		
		if(removeNode!=null) {

			// ��ֱ�Ӻ�̴���ڵ㣬ʹֻ֮����ɾ��ĩ�˽ڵ������     
			TreeNode replaceNode=removeNode;     
			if(removeNode.right!=null) {                            // �ҵ� ��� ����ڵ� 
				replaceNode=removeNode.right;                       // ��������������� ��� ����ڵ㼴Ϊ�������е�����
				while(replaceNode.left!=null)replaceNode=replaceNode.left;
			}
			
			if(replaceNode.left!=null) {                            // �����ʱ�������ӽڵ㣬��һ��Ϊ���  ��Ҷ���͡� �ڵ�
				removeNode.value=removeNode.left.value;             // ��ʱreplaceNode == removeNode
				removeNode.left=null;
				resetTreeInformation(false);
				return;
			}
			
			if(replaceNode.colorRB=='r') {                          // ��ʱ���Ϊĩ�� ��ڵ�ֱ��ɾ��������ƽ��
				removeNode.value=replaceNode.value;
				if(replaceNode.value<replaceNode.parent.value) {
					replaceNode.parent.left=null;
				}else {
					replaceNode.parent.right=null;
				}
				resetTreeInformation(false);
				return;
			}

			if(replaceNode.colorRB=='b') {
				if(replaceNode.right!=null) {           				// �����ʱ������������һ��Ϊ��ɫ�ú����ڼ��ɣ�����ƽ��
					removeNode.value=replaceNode.value;                 // �滻  ɾ���ڵ�
					replaceNode.value=replaceNode.right.value;          // �滻  �滻�ڵ�
					replaceNode.right=null;                             // ɾ���滻�ڵ�Ҷ�ӽڵ�
				}else { 												
					balanceForRemove(replaceNode);                      // ���  ɾ���ڵ�û���ӽڵ㣬���Ƴ��ڵ�֮ǰ��ƽ�⣬ƽ��֮��ɾ��
					removeNode.value=replaceNode.value;                 // �滻  ɾ���ڵ�
					if(replaceNode.parent==null) {                      // ֻ��һ�����ڵ㣬ֱ��ɾ��
						this.root=null;
						this.TREEDEPTH=0;
						this.SIZE=0;
						return;
					}else if(replaceNode.value<replaceNode.parent.value) {    // ɾ��  �滻�ڵ�
						replaceNode.parent.left=null;
					}else {
						replaceNode.parent.right=null;
					}
				}
				resetTreeInformation(false);
			}
			
		}
//		else {
//			System.out.println("ʵ��ɾ���Ľڵ��ǣ��ýڵ㲻���ڡ�");
//		}
	}
	
	private TreeNode findRemoveTargetNode(TreeNode nodeIndex, int value) {
		if( value < nodeIndex.value && nodeIndex.left!=null) return findRemoveTargetNode(nodeIndex.left,value);
		if( nodeIndex.value == value ) return nodeIndex;
		if( nodeIndex.value < value && nodeIndex.right!=null) return findRemoveTargetNode(nodeIndex.right,value);
		return null;
	}

	private void balanceForRemove(TreeNode replaceNode) {
		
		// ƽ��������ɾ�������˼���� ��ƽ�����ڵ㡱 ��ʹ��ÿ������������ƽ�⣬������������ƽ��ġ�
		
		if(replaceNode.parent==null) return;      // ������ƽ��
		
		if(replaceNode.colorRB =='r') {           // �滻�ڵ��Ǻ�ɫ����ʼʱû�У�ƽ��ݹ�֮�����ָ��������
			replaceNode.colorRB = 'b';            // �����ֱ�Ӹĳɺ�ɫ�����Ӹ�·���ϵ�һ����ɫ�ڵ㳤�ȣ���������ƽ���ˣ�����
			return;
		}
		
		// ����滻�ڵ��Ǻڽڵ㣬һ�����ֵܽڵ�
		if(replaceNode.value < replaceNode.parent.value) {            // �����ǰ�ڵ��ڸ��ڵ�����
			
			if(replaceNode.parent.right.colorRB=='r') {		          // �����ǰ�ڵ���ֵܽڵ��Ǻ�ڵ㣬ת�ɺںڵ����������ƽ��
				rotateLeft(replaceNode.parent);
				replaceNode.parent.colorRB='r';
				replaceNode.parent.parent.colorRB='b';
				balanceForRemove(replaceNode);                        // ��������ƽ�⣬�󻰴�ʱ��ʵֻҪ�ٰ��ֵܽڵ��ɺ�����ڵ��ɺڼ���
				return;                                
			}
			
			if(replaceNode.parent.right.colorRB=='b' && replaceNode.parent.right.right!=null ) { 
				if( replaceNode.parent.right.right.colorRB=='r') {    // �����ǰ�ڵ���ֵܽڵ��Ǻڽڵ����к����ӽڵ�
					char pColor=replaceNode.parent.colorRB;
					
					rotateLeft(replaceNode.parent);                   // ��������
					
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.parent.right.colorRB='b';
					return ;
				}
			}
			
			if(replaceNode.parent.right.colorRB=='b' && replaceNode.parent.right.left!=null) {              
				if( replaceNode.parent.right.left.colorRB=='r') {      // �����ǰ�ڵ���ֵܽڵ��к����ӽڵ�
					
					rotateRight(replaceNode.parent.right);
					replaceNode.parent.right.right.colorRB='r';
					replaceNode.parent.right.colorRB='b';
					
					char pColor=replaceNode.parent.colorRB;
					rotateLeft(replaceNode.parent);                   // ��������
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.parent.right.colorRB='b';
					
					return ;
				}
			}
			
			// �ֵ�Ϊ�ڣ����ֵ���������û�к�
			replaceNode.parent.right.colorRB='r';
			balanceForRemove(replaceNode.parent);                      // ���ϵݹ飬�Ե����ϴ���
			return;
		}
		
		// �����ǰ�ڵ��ڸ��ڵ���ұ�
		if(replaceNode.parent.value < replaceNode.value) {             
			
			if(replaceNode.parent.left.colorRB=='r') {		           // �����ǰ�ڵ�ģ����ֵܽڵ��Ǻ�ڵ�
				rotateRight(replaceNode.parent);					   // ��������	
				replaceNode.parent.colorRB='r';
				replaceNode.parent.parent.colorRB='b'; 
				balanceForRemove(replaceNode);                         // ��������ƽ�⣬�󻰴�ʱ��ʵֻҪ�ٰ��ֵܽڵ��ɺ�����ڵ��ɺڼ���
				return;                                 
			}
			
			if(replaceNode.parent.left.colorRB=='b' && replaceNode.parent.left.left!=null ) { 
				if( replaceNode.parent.left.left.colorRB=='r') {       // �����ǰ�ڵ���ֵܽڵ��Ǻڽڵ����к����ӽڵ�
					char pColor=replaceNode.parent.colorRB;
					
					rotateRight(replaceNode.parent);                   // ��������
					
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.left.colorRB='b';
					return ;
				}
			}
			
			// �����ǰ�ڵ���ֵܽڵ��к����ӽڵ�ͺ�������
			if(replaceNode.parent.left.colorRB=='b' && replaceNode.parent.left.right!=null) {              
				if(replaceNode.parent.left.right.colorRB=='r') {     
					rotateLeft(replaceNode.parent.left);
					replaceNode.parent.left.left.colorRB='r';
					replaceNode.parent.left.colorRB='b';
					
					char pColor=replaceNode.parent.colorRB;
					rotateRight(replaceNode.parent);                   // ��������
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.left.colorRB='b';
					
					return ;
				}
			}
			
			// �ֵ�Ϊ�ڣ����ֵ���������û�к�
			replaceNode.parent.left.colorRB='r';
			balanceForRemove(replaceNode.parent);                      // ���ϵݹ飬�Ե����ϴ���
			return;
		}

	}

}




