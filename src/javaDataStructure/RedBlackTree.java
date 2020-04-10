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
 *   （存放 int 型数据）常见操作：增加，删除，查找，https://www.jianshu.com/p/e136ec79235c
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
	 * 查找指定数据
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
	 * 将节点增加到该树中，已经存在的将不会被添加
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
		addNode.parent=targetNode;        //设置增加节点的父节点

		balance_RB_Tree(addNode);         // 当加入新节点后，重新平衡红黑树
		
		resetTreeInformation(true);
		
	}
	
	/**
	 *  Atfer changing this tree, it reset the information of this tree, such as the root, size, depth.
	 * @param sizeIsPlus
	 */
	private void resetTreeInformation(boolean sizeIsPlus) {
		this.root=findRootNode(this.root);                              // 当所指节点不是跟节点时，重新找回根节点
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
		
		if(nodeIndex.parent==null)return;         // 如果当前节点是红节点且为根节点（即没有父节点），退出，递归平衡时会遇到
		
		if(nodeIndex.parent.colorRB=='b')return;  // 如果父节点的颜色是黑色，则不改变树的当前节点颜色
		
		// 此时当前节点的父节点为红色，一定有祖父，因为在插入之前即为红黑树
		// 右边叔叔节点不存现在或存在且为黑，父节点在祖父节点的左边
		if(nodeIndex.parent.parent.right==null || nodeIndex.parent.parent.right.colorRB=='b') {   
			if ( nodeIndex.value< nodeIndex.parent.value) {          // 当前节点在父节点的左边，进行右旋转
				rotateRight(nodeIndex.parent.parent);           
			}else {                                                  // 当前节点在父节点的右边，先左旋转，再右旋转
				rotateLeft(nodeIndex.parent);            
				rotateRight(nodeIndex.parent);            
			}
			return;
		}
		
		// 左边叔叔节点不存现在或存在且为黑，父节点在祖父节点的右边
		if(nodeIndex.parent.parent.left==null || nodeIndex.parent.parent.left.colorRB=='b') {  
			if (nodeIndex.parent.value < nodeIndex.value) {          // 当前节点在父节点的右边，进行左旋转
				rotateLeft(nodeIndex.parent.parent);           
			}else {                                                  // 当前节点在父节点的左边，先右旋转，再左旋转
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
	 * 将目标节点从树中删除
	 * @param value
	 */
	public void remove(int value) {

		if(this.root==null) return;                                // 如果树为空，不做任何删除操作

		TreeNode removeNode=findRemoveTargetNode(this.root, value);
		
		if(removeNode!=null) {

			// 找直接后继代替节点，使之只出现删除末端节点的情形     
			TreeNode replaceNode=removeNode;     
			if(removeNode.right!=null) {                            // 找到 后继 替代节点 
				replaceNode=removeNode.right;                       // 如果有右子树，则 后继 代替节点即为右子树中的最左
				while(replaceNode.left!=null)replaceNode=replaceNode.left;
			}
			
			if(replaceNode.left!=null) {                            // 如果此时还有左子节点，那一定为红的  “叶子型” 节点
				removeNode.value=removeNode.left.value;             // 此时replaceNode == removeNode
				removeNode.left=null;
				resetTreeInformation(false);
				return;
			}
			
			if(replaceNode.colorRB=='r') {                          // 此时如果为末端 红节点直接删除，不用平衡
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
				if(replaceNode.right!=null) {           				// 如果此时右子树不空那一定为红色用红代替黑即可，不用平衡
					removeNode.value=replaceNode.value;                 // 替换  删除节点
					replaceNode.value=replaceNode.right.value;          // 替换  替换节点
					replaceNode.right=null;                             // 删除替换节点叶子节点
				}else { 												
					balanceForRemove(replaceNode);                      // 如果  删除节点没有子节点，在移除节点之前先平衡，平衡之后删除
					removeNode.value=replaceNode.value;                 // 替换  删除节点
					if(replaceNode.parent==null) {                      // 只有一个根节点，直接删除
						this.root=null;
						this.TREEDEPTH=0;
						this.SIZE=0;
						return;
					}else if(replaceNode.value<replaceNode.parent.value) {    // 删除  替换节点
						replaceNode.parent.left=null;
					}else {
						replaceNode.parent.right=null;
					}
				}
				resetTreeInformation(false);
			}
			
		}
//		else {
//			System.out.println("实际删除的节点是：该节点不存在。");
//		}
	}
	
	private TreeNode findRemoveTargetNode(TreeNode nodeIndex, int value) {
		if( value < nodeIndex.value && nodeIndex.left!=null) return findRemoveTargetNode(nodeIndex.left,value);
		if( nodeIndex.value == value ) return nodeIndex;
		if( nodeIndex.value < value && nodeIndex.right!=null) return findRemoveTargetNode(nodeIndex.right,value);
		return null;
	}

	private void balanceForRemove(TreeNode replaceNode) {
		
		// 平衡红黑树的删除情况的思想是 “平衡代替节点” ，使得每棵子树都保持平衡，最终整棵总是平衡的。
		
		if(replaceNode.parent==null) return;      // 无需再平衡
		
		if(replaceNode.colorRB =='r') {           // 替换节点是红色（初始时没有，平衡递归之后会出现该情况），
			replaceNode.colorRB = 'b';            // 该情况直接改成黑色（增加该路径上的一个黑色节点长度），其它都平衡了，结束
			return;
		}
		
		// 如果替换节点是黑节点，一定有兄弟节点
		if(replaceNode.value < replaceNode.parent.value) {            // 如果当前节点在父节点的左边
			
			if(replaceNode.parent.right.colorRB=='r') {		          // 如果当前节点的兄弟节点是红节点，转成黑黑的情况，重新平衡
				rotateLeft(replaceNode.parent);
				replaceNode.parent.colorRB='r';
				replaceNode.parent.parent.colorRB='b';
				balanceForRemove(replaceNode);                        // 继续进行平衡，后话此时其实只要再把兄弟节点变成红而父节点变成黑即可
				return;                                
			}
			
			if(replaceNode.parent.right.colorRB=='b' && replaceNode.parent.right.right!=null ) { 
				if( replaceNode.parent.right.right.colorRB=='r') {    // 如果当前节点的兄弟节点是黑节点且有红右子节点
					char pColor=replaceNode.parent.colorRB;
					
					rotateLeft(replaceNode.parent);                   // 进行左旋
					
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.parent.right.colorRB='b';
					return ;
				}
			}
			
			if(replaceNode.parent.right.colorRB=='b' && replaceNode.parent.right.left!=null) {              
				if( replaceNode.parent.right.left.colorRB=='r') {      // 如果当前节点的兄弟节点有红左子节点
					
					rotateRight(replaceNode.parent.right);
					replaceNode.parent.right.right.colorRB='r';
					replaceNode.parent.right.colorRB='b';
					
					char pColor=replaceNode.parent.colorRB;
					rotateLeft(replaceNode.parent);                   // 进行左旋
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.parent.right.colorRB='b';
					
					return ;
				}
			}
			
			// 兄弟为黑，且兄弟左右子树没有红
			replaceNode.parent.right.colorRB='r';
			balanceForRemove(replaceNode.parent);                      // 向上递归，自底向上处理
			return;
		}
		
		// 如果当前节点在父节点的右边
		if(replaceNode.parent.value < replaceNode.value) {             
			
			if(replaceNode.parent.left.colorRB=='r') {		           // 如果当前节点的（左）兄弟节点是红节点
				rotateRight(replaceNode.parent);					   // 进行右旋	
				replaceNode.parent.colorRB='r';
				replaceNode.parent.parent.colorRB='b'; 
				balanceForRemove(replaceNode);                         // 继续进行平衡，后话此时其实只要再把兄弟节点变成红而父节点变成黑即可
				return;                                 
			}
			
			if(replaceNode.parent.left.colorRB=='b' && replaceNode.parent.left.left!=null ) { 
				if( replaceNode.parent.left.left.colorRB=='r') {       // 如果当前节点的兄弟节点是黑节点且有红左子节点
					char pColor=replaceNode.parent.colorRB;
					
					rotateRight(replaceNode.parent);                   // 进行右旋
					
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.left.colorRB='b';
					return ;
				}
			}
			
			// 如果当前节点的兄弟节点有红右子节点和黑左子树
			if(replaceNode.parent.left.colorRB=='b' && replaceNode.parent.left.right!=null) {              
				if(replaceNode.parent.left.right.colorRB=='r') {     
					rotateLeft(replaceNode.parent.left);
					replaceNode.parent.left.left.colorRB='r';
					replaceNode.parent.left.colorRB='b';
					
					char pColor=replaceNode.parent.colorRB;
					rotateRight(replaceNode.parent);                   // 进行右旋
					replaceNode.parent.parent.colorRB=pColor;
					replaceNode.parent.colorRB='b';
					replaceNode.parent.parent.left.colorRB='b';
					
					return ;
				}
			}
			
			// 兄弟为黑，且兄弟左右子树没有红
			replaceNode.parent.left.colorRB='r';
			balanceForRemove(replaceNode.parent);                      // 向上递归，自底向上处理
			return;
		}

	}

}




