package javaDataStructure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 5�� B+ ��������Ψһ����ʵ�����ӣ�ɾ�������ң����������
 * ����ʱ�����Ĵ���
 * 1���ڽڵ㸴��ʱ��������Ϣ�����ף���û�н�ָ��Ҳ�����ƶ�����
 * 2��ָ��ָ�����Ӳ�ȫ���е��ǵ���������׺���Ҫ ˫������ �����
 * 3��ָ��ı�ʱ���׷����߼������統ָ��ָ�����ı�ʱ������ʹ��λָ�뷢����λʧЧ������ָ��ԭ���Ľڵ�
 * @author lenovo
 */
public class BPlusTree {
	
	/**
	 * MaxOrder=5
	 */
	private final int MaxOrder=5;

	private class BTreeNode{
		int nodeTotal=0;                                  // "Max" = 5 ; ��¼���ڵ����
		int[] values=new int[MaxOrder];                   // ���ڴ洢����
		BTreeNode[] children=new BTreeNode[MaxOrder];     // �ӽڵ�ָ��
		
		BTreeNode parent=null;
		
		BTreeNode frontDataLeaf = null;          // ��������Ҷ�ӣ�ֱ��ǰ��Ҷ�����ݽڵ�
		BTreeNode laterDataLeaf = null;          // ��������Ҷ�ӣ�ֱ�Ӻ��Ҷ�����ݽڵ�
		
		public BTreeNode(int data) {             // �½��ڵ�ʱĬ�ϲ����һ����
			this.values[0]=data;
			this.nodeTotal++;
		}
		
	}
	
	private BTreeNode ROOT=null;
	private int SIZE=0;
	public int size() {
		return this.SIZE;
	}
	
	public void visitAllLeavesNode() {
		if(this.ROOT==null) {
			System.out.println("The tree is null by 'visitAllLeavesNode'!");
			return;
		}
		 
		int sum=0;
		
		BTreeNode tempNode = this.ROOT;
		while (tempNode.children[0]!=null) {						// visiting from front to back
			tempNode=tempNode.children[0];
		}
		while (tempNode!=null) {
			System.out.print("(");
			for(int i=0; i<tempNode.nodeTotal; i++) {
				System.out.print(tempNode.values[i]+",");
				sum++;
			}
			System.out.print(")");
			tempNode=tempNode.laterDataLeaf;
		}
		System.out.println();
		
		tempNode = this.ROOT;										// visiting from back to front
		while (tempNode.children[tempNode.nodeTotal-1]!=null) {
			tempNode=tempNode.children[tempNode.nodeTotal-1];
		}
		while (tempNode!=null) {
			System.out.print("(");
			for(int i=tempNode.nodeTotal-1; i>-1; i--) {
				System.out.print(tempNode.values[i]+",");
			}
			System.out.print(")");
			tempNode=tempNode.frontDataLeaf;
		}
		System.out.println();
		
		System.out.println("(visitAllLeavesNode): The data of leaves is "+sum);
		
	}
	
	public void visitAllNode() {
		
		if(this.ROOT==null) {
			System.out.println("The tree is null by 'visitAllNode'!");
			return;
		}
		
		Queue<BTreeNode> aQueue=new LinkedList<BTreeNode>();
		int thisFlower=0;
		int nextFlower=0;
		
		aQueue.add(this.ROOT);
		thisFlower++;
		BTreeNode temp;
		while (!aQueue.isEmpty()) {
			temp = aQueue.poll();
			thisFlower--;
			
			System.out.print("(");
			for(int i=0; i<temp.nodeTotal; i++) {
				if(temp.children[i]!=null) {
					aQueue.add(temp.children[i]);
					nextFlower++;
				}
				if(i<temp.nodeTotal-1) {
					System.out.print(temp.values[i]+",");
				}else {
					System.out.print(temp.values[i]);
				}
			}
			System.out.print(")");
			
			if(thisFlower==0) {
				thisFlower=nextFlower;
				nextFlower=0;
				System.out.println();
			}
			
		}
		
	}
	
	public boolean findData(int data) {
		if(this.ROOT==null) return false;
		if(findTheNodePositionForRemove(data,this.ROOT)!=null) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * @param data : only 'int'
	 */
	public void add(int data) {
		if(this.ROOT==null) {                     // ����Ϊ�գ��������ڵ�
			this.ROOT = new BTreeNode(data);
			this.SIZE = 1;
			return;
		}
		
		BTreeNode goalNode = findTheNodePositionForAdd(data, this.ROOT);     // �ҵ�Ҫ�����Ҷ�ӽڵ�λ��
		
		if(goalNode == null) return;              // ����ڵ�Ϊ�գ��������Ѵ��ڣ�ֱ�ӷ���
		
		insertTheAddDataToTheNode(data, goalNode);
		this.SIZE++;				              // ��������������һ

	}
	
	private void insertTheAddDataToTheNode(int data, BTreeNode goalNode) {
		
		if(goalNode.nodeTotal<this.MaxOrder) {   // ����õ�ǰ�ڵ�����С��������
			dataInsertInSequence(goalNode,  data);
			if(data==goalNode.values[goalNode.nodeTotal]) {    
				// ��������Ǹýڵ�����һ���ڵ㣨���������ݱ��������ֵ����Ҫ���ϸ��¸ýڵ�����ֵ������������
				updataParetFormationForAdd(goalNode.parent, goalNode.values[goalNode.nodeTotal-1] ,data);
			}
			goalNode.nodeTotal++;          // ��ǰ�ڵ�������һ              
			return; 					   // �������
		}
		
		if(goalNode.nodeTotal==this.MaxOrder) {   // �����ǰ�ڵ����ݵ���������������������ӣ�������з���

			// �ڵ����
			BTreeNode newDivisiveNode = new BTreeNode(goalNode.values[this.MaxOrder/2]);   // �ȴ���һ�����ѽڵ�
			goalNode.values[this.MaxOrder/2]=0;
			
			for(int i=this.MaxOrder/2+1, j=1; i<this.MaxOrder; i++) {            // ���ɽڵ㲿�����ݸ��Ƶ��½ڵ�
				newDivisiveNode.values[j++]=goalNode.values[i];
				goalNode.values[i]=0;
			}
			goalNode.nodeTotal=this.MaxOrder/2;									   // ������Ѻ�ڵ�洢���ݸ���
			newDivisiveNode.nodeTotal=this.MaxOrder-this.MaxOrder/2;
			
			if(data<newDivisiveNode.values[0]) {                                   // �жϽ��²�������ݣ�����ǰ�벿�֣����Ǻ�벿��
				dataInsertInSequence(goalNode,data); 
				goalNode.nodeTotal++;                           				   // �ڵ�洢���ݸ�����һ
			}else {
				dataInsertInSequence(newDivisiveNode,data);     
				newDivisiveNode.nodeTotal++;                                       
			}
			
			//�����Ҷ�ӵ�ԭʼ���ѣ���Ҫ����Ҷ�ӽڵ����˫������
			if(goalNode.children[0]==null) {  
				BTreeNode tempNode=goalNode.laterDataLeaf;
				
				goalNode.laterDataLeaf=newDivisiveNode;
				newDivisiveNode.frontDataLeaf=goalNode;
				if(tempNode!=null) {
					tempNode.frontDataLeaf=newDivisiveNode;
					newDivisiveNode.laterDataLeaf=tempNode;
				}
			}
			
			nodeInsertInTree(goalNode, newDivisiveNode);
			
		}
		
	}
	
	/**
	 * 
	 * @param letfNode : has existed in the tree.
	 * @param rightNode : is being inserted to the tree.
	 */
	private void nodeInsertInTree(BTreeNode letfNode, BTreeNode rightNode) {
		// ���½ڵ���Ѻ󸸽ڵ����Ϣ
		if(letfNode.parent==null) {                 //���û�и��ڵ�ʹ�һ�����ڵ�
			BTreeNode newParent=new BTreeNode(letfNode.values[letfNode.nodeTotal-1]);
			newParent.children[0]=letfNode;
			letfNode.parent=newParent;
			
			newParent.values[1]=rightNode.values[rightNode.nodeTotal-1];
			newParent.nodeTotal++;
			newParent.children[1]=rightNode;
			rightNode.parent=newParent;
			
			this.ROOT=newParent;
			
			return;
		}
		
		if(letfNode.parent.nodeTotal<this.MaxOrder) {           // �������������
			
			//��������
			int iLR=0;
			for(iLR=0; iLR < letfNode.parent.nodeTotal; iLR++) { if(letfNode.parent.children[iLR]==letfNode) break; }
			
			boolean theAddDataIsMax=false;         // �ж��²���������ǲ������������е����ֵ���������Ҫ�ݹ�����ϲ�����ֵ
			if(letfNode.parent.values[iLR]!=rightNode.values[rightNode.nodeTotal-1])theAddDataIsMax=true;
			
			letfNode.parent.values[iLR]=letfNode.values[letfNode.nodeTotal-1];  // ���ݷ��ѵ���ڵ㣬�����󸸽ڵ����ֵ
			
			for(int i = this.MaxOrder-1; i>iLR; i--) {                      //���ڵ��е����ݺ���һλ���Բ����½ڵ�
				letfNode.parent.values[i]=letfNode.parent.values[i-1];
				letfNode.parent.children[i]=letfNode.parent.children[i-1];
			}
			
			letfNode.parent.values[iLR+1]=rightNode.values[rightNode.nodeTotal-1]; 
			letfNode.parent.children[iLR+1]=rightNode;                      // ���ڵ�ָ���ӽڵ�
			rightNode.parent=letfNode.parent;								// �ӽڵ�ָ�򸸽ڵ�
			
			rightNode.parent.nodeTotal++;                                   // �����ڵ����ݼ�һ
			
			if(theAddDataIsMax) {    
				// ��������Ǹýڵ�����һ���ڵ㣨���������ݱ��������ֵ����Ҫ���ϸ��¸ýڵ�����ֵ������������
				if(rightNode.parent.parent!=null)
				updataParetFormationForAdd(rightNode.parent.parent, rightNode.values[rightNode.nodeTotal-2] ,rightNode.values[rightNode.nodeTotal-1]);
			}
			
			return;   // ���Ѻ󸸼������㹻������������
		}
		
		// ������������������������з��������ϵݹ�
		if (letfNode.parent.nodeTotal==this.MaxOrder) {
	
			// ������������������ڸ��ڵ�ķ��ѽڵ㣬��ô�ݹ�ʱ���ᶪʧ���ڵ����ڵ㣨��ԭ���Ľڵ㣩
			BTreeNode parentNodeLeft = letfNode.parent;
			
			// ���޸ĸ��ڵ�ľ�ֵ�������¼letfNode�����ֵ��������rightNode�����ֵ
			int iLR=0;
			for(iLR=0; iLR < letfNode.parent.nodeTotal; iLR++) { if(letfNode.parent.children[iLR]==letfNode) break; }
			letfNode.parent.values[iLR]=letfNode.values[letfNode.nodeTotal-1];
			
			// �ٽ�   ���ڵ�  ���з��ѣ�
			BTreeNode newDivisiveNode = new BTreeNode(parentNodeLeft.values[this.MaxOrder/2]);   // �ȴ���һ�����ѽڵ�
			newDivisiveNode.children[0]=parentNodeLeft.children[this.MaxOrder/2];         // ���ڵ�ָ���ӽڵ�
			newDivisiveNode.children[0].parent=newDivisiveNode;							  // �ӽڵ�ָ�򸸽ڵ�
			
			parentNodeLeft.values[this.MaxOrder/2]=0;
			parentNodeLeft.children[this.MaxOrder/2]=null;
			
			for(int i=this.MaxOrder/2+1, j=1; i<this.MaxOrder; i++) {                   // ���ɸ��ڵ㲿������ת�Ƶ��½ڵ�
				newDivisiveNode.values[j]=parentNodeLeft.values[i]; 					  
				newDivisiveNode.children[j]=parentNodeLeft.children[i];				  
				newDivisiveNode.children[j++].parent=newDivisiveNode;
				
				parentNodeLeft.values[i]=0;
				parentNodeLeft.children[i]=null;
			}
			parentNodeLeft.nodeTotal=this.MaxOrder/2;									  // ������Ѻ�ڵ�洢���ݸ���
			newDivisiveNode.nodeTotal=this.MaxOrder-this.MaxOrder/2;
			
			if(rightNode.values[rightNode.nodeTotal-1]<newDivisiveNode.values[0]) {       // �жϽ��²�������ݣ�����ǰ�벿�֣����Ǻ�벿��
				int pos = dataInsertInSequence(parentNodeLeft, rightNode.values[rightNode.nodeTotal-1]); 
				parentNodeLeft.children[pos]=rightNode;                                   // ���ڵ�ָ���ӽڵ�
				rightNode.parent = parentNodeLeft;										  // �ӽڵ�ָ�򸸽ڵ�
				parentNodeLeft.nodeTotal++;                             				  // ������Ѻ�ڵ�洢���ݸ���
			}else {
				int pos = dataInsertInSequence(newDivisiveNode, rightNode.values[rightNode.nodeTotal-1]); 
				newDivisiveNode.children[pos]=rightNode;
				rightNode.parent = newDivisiveNode;
				newDivisiveNode.nodeTotal++;
			}
			// ���ѽ���

			nodeInsertInTree(parentNodeLeft, newDivisiveNode);  						 // ���ϵݹ����
		}
		
		// ���ˣ�������Ҫ���ѵ�����������
	}
	
	private int dataInsertInSequence(BTreeNode goalNode, int data) {
		int i=0;
		for(i=0; i<goalNode.nodeTotal; i++) {   
			if(data<goalNode.values[i]){
				break;
			}
		}
		int tempValue;
		BTreeNode tempNode,dataNode=null;
		for(int j=i; j<goalNode.nodeTotal+1; j++) {    // �����ݲ��뵱ǰ�ڵ�
			tempValue=goalNode.values[j];
			tempNode=goalNode.children[j];
			
			goalNode.values[j]=data;
			goalNode.children[j]=dataNode;
			
			data=tempValue;
			dataNode=tempNode;
		}
		return i;
	}
	
	/**
	 * updating formation of parent node about a child maximal value.
	 * @param nodeIndex
	 * @param oldData
	 * @param newData
	 */
	private void updataParetFormationForAdd(BTreeNode nodeIndex, int oldData, int newData) {
		if(nodeIndex==null) {
			return;
		}else {
			int i=0;
			for(i=0; i<nodeIndex.nodeTotal; i++) {
				if(nodeIndex.values[i] == oldData) break;
			}
			if(i==nodeIndex.nodeTotal) {   // ���ڵ㲻��������Ϣ���������ϵݹ鴦��
				return;
			}else {
				nodeIndex.values[i]=newData;
				updataParetFormationForAdd(nodeIndex.parent,oldData,newData);   // ��һ���м������ϵݹ����
			}
			  
		}
	}
	
	private BTreeNode findTheNodePositionForAdd(int data, BTreeNode nodeIndex){
		int i=0;
		for(i=0; i<nodeIndex.nodeTotal; i++) {   // �ҵ�����������ĳ�ڵ������������֮�䣬��ȷ���ӽڵ�ָ��
			if(data==nodeIndex.values[i]) {
				return null;
			}else if(data<nodeIndex.values[i]){
				break;
			}
		}
		
		if(i==nodeIndex.nodeTotal) {             // ���������ݱ����е����ֵ��Ҫ��ʱ��ָ��ֱ���Ƶ����������ң����ݹ�
			BTreeNode tempNode=this.ROOT;
			while (tempNode.children[tempNode.nodeTotal-1]!=null) {
				tempNode=tempNode.children[tempNode.nodeTotal-1];
			}
			return tempNode;
		}
		
		if(nodeIndex.children[i]==null) {        // ����ýڵ�ΪѰ�ҵ�Ҷ�ӽڵ㣬�����ݹ�
			return nodeIndex;
		}else {
			return findTheNodePositionForAdd(data, nodeIndex.children[i]);
		}
		
	}
	
	public void remove(int data) {
		if(this.ROOT==null)return;
		
		BTreeNode targetNode=findTheNodePositionForRemove(data, this.ROOT);
		
		if(targetNode==null) { return;  }       
			
		int iIndex=0;
		while (iIndex<targetNode.nodeTotal) {
			if(data==targetNode.values[iIndex])break;
			iIndex++;
		}
		
		for(int i=iIndex; i<targetNode.nodeTotal-1; i++) {   // ��ǰ�ڵ�����һλ����ɾ��Ŀ������
			targetNode.values[i]=targetNode.values[i+1];
		}
		targetNode.nodeTotal--;								 // ��ǰ�ڵ����ݸ�����һ

		if(iIndex==targetNode.nodeTotal && targetNode.parent!=null) {					 // ���ɾ�����ǵ�ǰ�ڵ�����ֵ����Ҫ���ϸ��½ڵ����ֵ
			updataParetFormationForAdd( targetNode.parent,  targetNode.values[targetNode.nodeTotal],  targetNode.values[targetNode.nodeTotal-1]);
		}
		
		// �����ʱ��ǰ�ڵ����ݸ���С�����Ҫ����ݹ鴦������������
		if(targetNode.nodeTotal < this.MaxOrder/2) removeUpwordParent(targetNode);   
		
		this.SIZE--;
		
	}
	
	private void removeUpwordParent(BTreeNode nodeIndex) {
		
		// ��ʱ�ýڵ�Ϊ���ڵ㣬�ж��Ƿ�Ҫɾ���ø��ڵ㣬����������
		if(nodeIndex.parent==null) {                                         
			if(nodeIndex.nodeTotal==1 && nodeIndex.children[0]!=null) {      // ������ڵ����Ϊ1���һ����ӽڵ㣬ɾ���ø��ڵ�
				this.ROOT=nodeIndex.children[0];
				this.ROOT.parent=null;
			}
			if(nodeIndex.nodeTotal==0) this.ROOT = null;
			return;
		}

		// �������ڵ㲻�Ǹ��ڵ㣬������С����СҪ�󣬽�����Ӧ����
		
		int iParentIndex=0;      // ��λ��ǰ�ڵ��ڸ��ڵ��е�λ��
		while (iParentIndex < nodeIndex.parent.nodeTotal) {
			if(nodeIndex == nodeIndex.parent.children[iParentIndex]) break;
			iParentIndex++;
		} 
		
		// ��������ֵ� �� ���ֵܴ�����СҪ�������������ֵܽ�ڵ�
		if(iParentIndex+1 < nodeIndex.parent.nodeTotal && nodeIndex.parent.children[iParentIndex+1].nodeTotal > this.MaxOrder/2) {
			// �����ֵܽ�һλ����
			nodeIndex.values[nodeIndex.nodeTotal]=nodeIndex.parent.children[iParentIndex+1].values[0];
			nodeIndex.children[nodeIndex.nodeTotal]=nodeIndex.parent.children[iParentIndex+1].children[0];
			if(nodeIndex.children[nodeIndex.nodeTotal]!=null) nodeIndex.children[nodeIndex.nodeTotal].parent=nodeIndex;
			nodeIndex.nodeTotal++;
			updataParetFormationForAdd(nodeIndex.parent, nodeIndex.values[nodeIndex.nodeTotal-2],nodeIndex.values[nodeIndex.nodeTotal-1]);
			
			for(int i=0; i < nodeIndex.parent.children[iParentIndex+1].nodeTotal-1; i++) {          // ���ֵ�����һλ
				nodeIndex.parent.children[iParentIndex+1].values[i] = nodeIndex.parent.children[iParentIndex+1].values[i+1];
				nodeIndex.parent.children[iParentIndex+1].children[i] = nodeIndex.parent.children[iParentIndex+1].children[i+1];
			}
			nodeIndex.parent.children[iParentIndex+1].nodeTotal--;
			return;
		}
		
		// ��������ֵ������ֵܴ�����СҪ�������������ֵܽ�ڵ�
		if(iParentIndex-1 > -1 && nodeIndex.parent.children[iParentIndex-1].nodeTotal > this.MaxOrder/2) {
			for(int i=nodeIndex.nodeTotal; i>0; i--) {									   			// ��ǰ�ڵ�����һλ
				nodeIndex.values[i]=nodeIndex.values[i-1];
				nodeIndex.children[i]=nodeIndex.children[i-1];
			}
			// �����ֵܽ�һλ
			nodeIndex.values[0]=nodeIndex.parent.children[iParentIndex-1].values[nodeIndex.parent.children[iParentIndex-1].nodeTotal-1];
			nodeIndex.children[0]=nodeIndex.parent.children[iParentIndex-1].children[nodeIndex.parent.children[iParentIndex-1].nodeTotal-1];
			if(nodeIndex.children[0]!=null) nodeIndex.children[0].parent=nodeIndex;
			nodeIndex.nodeTotal++;
			
			nodeIndex.parent.children[iParentIndex-1].nodeTotal--; 									// ���ֵܽڵ��һ����ȥ���һλ��
			updataParetFormationForAdd(nodeIndex.parent, 
					nodeIndex.parent.children[iParentIndex-1].values[nodeIndex.parent.children[iParentIndex-1].nodeTotal], 
					nodeIndex.parent.children[iParentIndex-1].values[nodeIndex.parent.children[iParentIndex-1].nodeTotal-1]);
			return;
		}
		
		// �����ǰ�ڵ�ΪҶ�ӽڵ㣬�Ҽ���ɾ������ǰ��Ҷ�ӽ���˫������
		if( nodeIndex.children[0]==null ) {                      
			if(nodeIndex.frontDataLeaf!=null) nodeIndex.frontDataLeaf.laterDataLeaf=nodeIndex.laterDataLeaf;
			if(nodeIndex.laterDataLeaf!=null) nodeIndex.laterDataLeaf.frontDataLeaf=nodeIndex.frontDataLeaf;
		}
		
		// ��������ֵ� �� ���ֵ�С�ڵ�����СҪ�������������ֵܺϲ�
		if(iParentIndex+1 < nodeIndex.parent.nodeTotal && nodeIndex.parent.children[iParentIndex+1].nodeTotal<=this.MaxOrder/2) {
			// �����ֵ������Կճ���ǰ�ڵ����ݵ�λ��
			for(int i = nodeIndex.parent.children[iParentIndex+1].nodeTotal + nodeIndex.nodeTotal-1; i > nodeIndex.nodeTotal-1; i--) {
				if(nodeIndex.parent.children[iParentIndex+1].nodeTotal + nodeIndex.nodeTotal-1 == 5) {
					System.out.println(nodeIndex.values[0]+","+nodeIndex.values[1]+","+nodeIndex.values[2]+","+nodeIndex.values[3]);
					System.out.println("I am '5'."+nodeIndex.parent.children[iParentIndex+1].nodeTotal + "+" + nodeIndex.nodeTotal);
				}
				nodeIndex.parent.children[iParentIndex+1].values[i]=nodeIndex.parent.children[iParentIndex+1].values[i-nodeIndex.nodeTotal];
				nodeIndex.parent.children[iParentIndex+1].children[i]=nodeIndex.parent.children[iParentIndex+1].children[i-nodeIndex.nodeTotal];
			}
			
			for(int i = nodeIndex.nodeTotal-1; i > -1; i--) {									   // ��ǰ�ڵ��Ƶ����ֵܽڵ�
				nodeIndex.parent.children[iParentIndex+1].values[i]=nodeIndex.values[i];
				nodeIndex.parent.children[iParentIndex+1].children[i]=nodeIndex.children[i];
				if(nodeIndex.parent.children[iParentIndex+1].children[i]!=null)
					nodeIndex.parent.children[iParentIndex+1].children[i].parent=nodeIndex.parent.children[iParentIndex+1];
			}
			nodeIndex.parent.children[iParentIndex+1].nodeTotal += nodeIndex.nodeTotal;
			
			for(int i=iParentIndex; i<nodeIndex.parent.nodeTotal-1; i++) {                         // ���ڵ�������ɾ������
				nodeIndex.parent.values[i]=nodeIndex.parent.values[i+1];
				nodeIndex.parent.children[i]=nodeIndex.parent.children[i+1];
			}
			nodeIndex.parent.nodeTotal--;
		
			if(nodeIndex.parent.nodeTotal<this.MaxOrder/2) {             							   // ������ڵ�ҲС����СҪ�������ϵݹ�
				removeUpwordParent(nodeIndex.parent);
			}else {
				return;
			}
		}
		
		// ��������ֵ� �� ���ֵ�С�ڵ�����СҪ�������������ֵܺϲ�
		if(iParentIndex-1 > -1 && nodeIndex.parent.children[iParentIndex-1].nodeTotal <= this.MaxOrder/2) {
			// �Լ��Ƶ����ֵܽڵ㣬���кϲ�
			for(int i=0; i<nodeIndex.nodeTotal; i++) { 		
				if(nodeIndex.parent.children[iParentIndex-1].nodeTotal + i==5)System.out.println("I am '5'"+nodeIndex.nodeTotal);
				nodeIndex.parent.children[iParentIndex-1].values[nodeIndex.parent.children[iParentIndex-1].nodeTotal + i ]=nodeIndex.values[i];
				nodeIndex.parent.children[iParentIndex-1].children[nodeIndex.parent.children[iParentIndex-1].nodeTotal + i ]=nodeIndex.children[i];
				if(nodeIndex.parent.children[iParentIndex-1].children[nodeIndex.parent.children[iParentIndex-1].nodeTotal + i ] != null)
					nodeIndex.parent.children[iParentIndex-1].children[nodeIndex.parent.children[iParentIndex-1].nodeTotal + i ].parent=
							nodeIndex.parent.children[iParentIndex-1];
			}
			nodeIndex.parent.children[iParentIndex-1].nodeTotal += nodeIndex.nodeTotal;
			updataParetFormationForAdd(nodeIndex.parent, 
					nodeIndex.parent.children[iParentIndex-1].values[nodeIndex.parent.children[iParentIndex-1].nodeTotal-nodeIndex.nodeTotal-1], 
					nodeIndex.parent.children[iParentIndex-1].values[nodeIndex.parent.children[iParentIndex-1].nodeTotal-1]);
			
			for(int i=iParentIndex; i<nodeIndex.parent.nodeTotal-1; i++) {							// ���ڵ�������ɾ������
				nodeIndex.parent.values[i]=nodeIndex.parent.values[i+1];
				nodeIndex.parent.children[i]=nodeIndex.parent.children[i+1];
			}
			nodeIndex.parent.nodeTotal--;															// ���ڵ����ݼ�һ
			
			if(iParentIndex==nodeIndex.parent.nodeTotal)                // ��Ϊ��ʱ���ڵ��в�һ�����ҽڵ㣬����Ҫ�����������ֵ�����ж�
				updataParetFormationForAdd(nodeIndex.parent.parent, 
						nodeIndex.parent.values[nodeIndex.parent.nodeTotal], nodeIndex.parent.values[nodeIndex.parent.nodeTotal-1]);
			
			if(nodeIndex.parent.nodeTotal<this.MaxOrder/2) {              // ������ڵ�ҲС����СҪ�������ϵݹ�
				removeUpwordParent(nodeIndex.parent);
			}else {
				return;
			}
		}
		
		
	}
	
	private BTreeNode findTheNodePositionForRemove(int data, BTreeNode nodeIndex){
		int i=0;
		for(i=0; i<nodeIndex.nodeTotal; i++) if(data <= nodeIndex.values[i]) break;

		if(i==nodeIndex.nodeTotal)return null;   // ��ֵ�����ڣ����ݹ�
		
		if(nodeIndex.children[i]==null) {        // �ýڵ�����Ҷ�ӽڵ�
			if(nodeIndex.values[i]!=data) {
				return null;					 // ��ֵ������
			} else {
				return nodeIndex;
			}
		}else {
			return findTheNodePositionForRemove(data, nodeIndex.children[i]);   // �ýڵ㲻��Ҷ�ӽڵ㣬���еݹ鴦��
		}
	}
	
	
}





