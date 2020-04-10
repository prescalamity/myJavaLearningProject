import java.util.HashMap;
//import java.util.Scanner;
import java.util.Stack;

// Definition for binary tree
class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

public class testonly {
	
	public static void main(String[] args) {
		testonly testonly=new testonly();
		testonly.runMainNoStatic();
	}
	
	public void runMainNoStatic() {
		//Scanner sc = new Scanner(System.in);
		int[] pre={1,2,4,7,3,5,6,8};
		int[] in={4,7,2,1,5,3,8,6};
		TreeNode result=reConstructBinaryTree( pre,  in);
		
		//System.out.println(result.val);/////////////
		


//		result.val=2;
//		result.left=new TreeNode(3);
//		result.right=new TreeNode(4);
		
		System.out.println();
		System.out.println("--------------------前序遍历树-----------------------"); ///////////////
		bianliTree( result);
	}
	
	/**
	 * 前序遍历树
	 * @param result
	 */
	public void bianliTree(TreeNode result) {
		
		System.out.println(result.val);
		
		if(result.left==null) {
			//System.out.println("(bianliTree) The left is Null!");
		}else {
			bianliTree(result.left);
		}
		
		if(result.right==null) {
			//System.out.println("(bianliTree) The Right is Null!");
		}else {
			bianliTree(result.right);
		}
	}
	
	/**
	 * 中序遍历树
	 * @param result
	 */
    public void inOrderTraverse(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                TreeNode tem = stack.pop();
                System.out.print(tem.val + "->");
                node = tem.right;
            }
        }
    }
	
	public TreeNode reConstructBinaryTree(int[] pre, int[] in) {

		if (pre == null || in == null) {
			return null;
		}

		TreeNode resultTree = new TreeNode(pre[0]);
		TreeNode treeNodeIndex;

		HashMap<Integer, Integer> preFlag = new HashMap<Integer, Integer>();
		int i = 0;
		while (i < pre.length) {
			preFlag.put(in[i], i);
			i++;
		}
		
		//System.out.println(preFlag.get(1)); ///////////////
		
		int preIndex = 1;
		while (preIndex < pre.length) {
			treeNodeIndex = resultTree;
			System.out.println("-------------------------------------------"); ///////////////
			System.out.println("pre[preIndex]-->"+pre[preIndex]); ///////////////
			setNewDode(pre[preIndex], treeNodeIndex, preFlag);
			preIndex++;
		}
		return resultTree;
	}

	public void setNewDode(int targetNumber, TreeNode treeNodeIndex, HashMap<Integer, Integer> preFlag) {
//		if (treeNodeIndex == null) {
//			treeNodeIndex = new TreeNode(targetNumber);
//			System.out.println("(NEW)"+treeNodeIndex.val);
//		} else 
		if (preFlag.get(targetNumber) < preFlag.get(treeNodeIndex.val)) {
			System.out.println("(< left)");
			if(treeNodeIndex.left==null) {
				treeNodeIndex.left=new TreeNode(targetNumber);
			}else {
				setNewDode(targetNumber, treeNodeIndex.left, preFlag);
			}
			
		} else {
			System.out.println("(> right)");
			if(treeNodeIndex.right==null) {
				treeNodeIndex.right=new TreeNode(targetNumber);
			}else {
				setNewDode(targetNumber, treeNodeIndex.right, preFlag);
			}
		}
	}
}







