/*
 * Created on 2010-5-9
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * Huffman树的测试示例程序，
 * ExampleHuffmanTree.java
 */
public class ExampleHuffmanTree {
	public static void main(String args[]){
		//字符串，数组表示，长度为7
		String c[] = new String[7];
		//设置字符串的值
		c[0] = "a1"; c[1] = "a2";
		c[2] = "a3"; c[3] = "a4";
		c[4] = "a5"; c[5] = "a6";
		c[6] = "a7"; 
		//字符串对应的频率，数组表示
		double frq[] = {0.2, 0.19, 
					0.18, 0.17, 
					0.15, 0.1, 
					0.01};
		//节点树数组，用于构建Huffman树
		LinkBinTreeNode n[] = new LinkBinTreeNode[7];
		//节点数字的赋值
		for(int i= 0; i < 7; i++){
			n[i] = new LinkBinTreeNode(
					new ElemItem<HuffmanPair>(
							new HuffmanPair(c[i], frq[i])),
					null, null);
		}
		//创建树对象
		HuffmanTree ht = new HuffmanTree();
		//建树
		ht.buildTree(n);
		//打印树
		ht.printTree();
		//二叉树的各种遍历算法
		ht.order_visit();
	}
}
