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
 * Huffman���Ĳ���ʾ������
 * ExampleHuffmanTree.java
 */
public class ExampleHuffmanTree {
	public static void main(String args[]){
		//�ַ����������ʾ������Ϊ7
		String c[] = new String[7];
		//�����ַ�����ֵ
		c[0] = "a1"; c[1] = "a2";
		c[2] = "a3"; c[3] = "a4";
		c[4] = "a5"; c[5] = "a6";
		c[6] = "a7"; 
		//�ַ�����Ӧ��Ƶ�ʣ������ʾ
		double frq[] = {0.2, 0.19, 
					0.18, 0.17, 
					0.15, 0.1, 
					0.01};
		//�ڵ������飬���ڹ���Huffman��
		LinkBinTreeNode n[] = new LinkBinTreeNode[7];
		//�ڵ����ֵĸ�ֵ
		for(int i= 0; i < 7; i++){
			n[i] = new LinkBinTreeNode(
					new ElemItem<HuffmanPair>(
							new HuffmanPair(c[i], frq[i])),
					null, null);
		}
		//����������
		HuffmanTree ht = new HuffmanTree();
		//����
		ht.buildTree(n);
		//��ӡ��
		ht.printTree();
		//�������ĸ��ֱ����㷨
		ht.order_visit();
	}
}
