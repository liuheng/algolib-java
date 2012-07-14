/*
 * Created on 2010-5-7
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * 测试二叉树的遍历函数：
 * 1. 两种前序遍历算法 OK
 * 2. 两种后序遍历算法 OK
 * 3. 两种中序遍历算法 OK
 * 4. 层序遍历算法 OK
 */
public class ExampleLinkBinTree {
	public static void main(String args[]){
		//二叉树创建
		LinkBinTreeNode n0 = 
			new LinkBinTreeNode(new ElemItem<Integer>(-1),
								null, null);
		LinkBinTreeNode n1 = 
			new LinkBinTreeNode(new ElemItem<Integer>(10),
								null, n0);
		LinkBinTreeNode n2 = 
			new LinkBinTreeNode(new ElemItem<Integer>(6),
								n1, null);
		LinkBinTreeNode n3 = 
			new LinkBinTreeNode(new ElemItem<Integer>(7),
								null, null);
		LinkBinTreeNode n4 = 
			new LinkBinTreeNode(new ElemItem<Integer>(4),
								n2, n3);
		LinkBinTreeNode n5 = 
			new LinkBinTreeNode(new ElemItem<Integer>(8),
								null, null);
		LinkBinTreeNode n6 = 
			new LinkBinTreeNode(new ElemItem<Integer>(9),
								null, null);
		LinkBinTreeNode n7= 
			new LinkBinTreeNode(new ElemItem<Integer>(5),
								n5, n6);
		LinkBinTreeNode n8 = 
			new LinkBinTreeNode(new ElemItem<Integer>(2),
								n4, n7);
		LinkBinTreeNode n9 = 
			new LinkBinTreeNode(new ElemItem<Integer>(12),
								null, null);
		LinkBinTreeNode n10 = 
			new LinkBinTreeNode(new ElemItem<Integer>(13),
								null, null);
		LinkBinTreeNode n11 = 
			new LinkBinTreeNode(new ElemItem<Integer>(11),
								n9, n10);
		LinkBinTreeNode n12 = 
			new LinkBinTreeNode(new ElemItem<Integer>(14),
								null, null);
		LinkBinTreeNode n13 = 
			new LinkBinTreeNode(new ElemItem<Integer>(3),
								n12, n11);
		LinkBinTreeNode n14 = 
			new LinkBinTreeNode(new ElemItem<Integer>(1),
								n8, n13);
		
		LinkBinTree lbt = new LinkBinTree(n14);
		//两种前序测试
		System.out.println("\n递归算法实现前序遍历：");
		lbt.rec_preorder(lbt.getRoot());
		System.out.println("\n迭代算法实现前序遍历：");
		lbt.itr_preorder(lbt.getRoot());
		//两种中序测试
		System.out.println("\n递归算法实现中序遍历：");
		lbt.rec_inorder(lbt.getRoot());
		System.out.println("\n迭代算法实现中序遍历：");
		lbt.itr_inorder(lbt.getRoot());
		//两种后序测试
		System.out.println("\n递归算法实现后序遍历：");
		lbt.rec_postorder(lbt.getRoot());
		System.out.println("\n迭代算法实现后序遍历：");
		lbt.itr_postoder(lbt.getRoot());
		//层序测试
		System.out.println("\n迭代算法实现层序遍历：");
		lbt.layer_order(lbt.getRoot());
	}
}
