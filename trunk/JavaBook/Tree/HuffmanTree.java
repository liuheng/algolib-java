/*
 * Created on 2010-5-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * ������������Ҫ��Ա����Ϊ���ĸ��ڵ㣬
 * ��Ҫ��Ա����Ϊ���������Ĵ�ӡ
 * HuffmanTree.java
 */
public class HuffmanTree {
	//˽�г�Ա����
	//�����ڵ�
	private LinkBinTreeNode root;
	
	//�޲������캯��
	public HuffmanTree(){
		root = null;
	}
	
	//�в����Ĺ��캯��
	public HuffmanTree(LinkBinTreeNode r){
		root = r;
	}
	
	//����huffman�������Ϊ����
	//����Ԫ����ΪHuffmanPair
	public void buildTree(LinkBinTreeNode hufflist[]){
		//���ȶ�hufflist��Ԫ��������
		//���鳤��
		int n = hufflist.length;
		//�����е�һ����Ч�ڵ��λ��
		int validpos = 0;
		//�򵥵�ѡ������
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				//�����i��Ƶ�ʴ��ڵ�j��Ƶ�ʣ��򽻻�
				if(((HuffmanPair)(
						hufflist[i].getElem().getElem()
						)).compareTo(
					(HuffmanPair)(
						hufflist[j].getElem().getElem()
						)) == 1){
					LinkBinTreeNode t = hufflist[i];
					hufflist[i] = hufflist[j];
					hufflist[j] = t;
				}
			}
		}//�������
		
		//��ʼ����
		while(validpos != n -1){
			//�½��ڵ����ڱ�ʾ���������ĸ��ڵ�
			LinkBinTreeNode r 
				= new LinkBinTreeNode();
			//���ø��ڵ�r����������
			r.setLeft(hufflist[validpos]);
			r.setRight(hufflist[validpos + 1]);
			//�������ӽڵ��Ƶ�����
			double _f = 
				((HuffmanPair)(
					hufflist[validpos].getElem().getElem()
					)).getFreq()
			  + ((HuffmanPair)(
			  		hufflist[validpos+1].getElem().getElem()
			  		)).getFreq();
			//���ýڵ��Ƶ�ʣ�ͬʱ�ڵ���ַ���"��"��ʾ
			r.setElem(new ElemItem<HuffmanPair>(
					new HuffmanPair("��", _f)));
			//��Чλ�������
			validpos++;
			//����validposλ��Ϊ�½��Ľڵ�
			hufflist[validpos] = r;
			//����validpos�ڵ��λ�ã�
			//��Ϊ��Ƶ��ֵ�Ĵ�С��һ������С��
			int idx;
			//validposλ�õ�huffmanPair
			HuffmanPair tt1 =
				(HuffmanPair)(
					hufflist[validpos].getElem().getElem());
			//��λ��һ����tt1��Ƶ�ʴ�Ľڵ��λ��idx
			for(idx = validpos + 1; idx < n; idx++){
				HuffmanPair tt2 = 
				(HuffmanPair)(
					hufflist[idx].getElem().getElem());
				//�ҵ���һ������idx
				if(tt1.compareTo(tt2) <= 0){
					break;
				}
			}
			//��validpos�ڵ㻺�棬
			//����validpos��idx - 1huffmanPairһһǰ��
			LinkBinTreeNode t = hufflist[validpos];
			for(int i = validpos; i < idx - 1; i++){
				hufflist[i] = hufflist[i + 1];
			}
			//��t�ŵ����ʵ�λ��
			hufflist[idx - 1] = t;
		}
		//���յõ��ĸ��ڵ����hufflist�����һ��huffmanPair
		root = hufflist[n - 1];
	}
	
	//�ڸ߶�h����ӡһ���ڵ�n
	protected void printnode(LinkBinTreeNode n, int h){
		//�߶�Ϊh���Ʊ�λ
		for(int i = 0; i < h; i++){
			System.out.print("\t");
		}
		//��ȡ�ڵ��Ƶ��ֵ�ĵ�������ʽ
		double _f = (
			(HuffmanPair)(
				n.getElem().getElem())).getFreq();
		float f = (float)(_f);
		//�ڵ���ַ���������Ҷ�ڵ���м�ڵ�
		String c;
		//Ҷ�ڵ�ʱ��cΪ�ڵ���ַ���ֵ
		if(n.isLeaf()){
			c = ((HuffmanPair)(
				n.getElem().getElem())).getChar();
		}
		//�м�ڵ�ʱ��cΪ��
		else{
			c = "��";
		}
		//��ӡ�ڵ�
		System.out.println("--" + c + " " + f);
	}
	
	//��ӡ�Խڵ�rΪ���ڵ��huffman����
	//r�ĸ߶�Ϊh�����ýڵ��ӡ������
	//������Ϊ�ݹ麯��
	public void ShowHT(LinkBinTreeNode r, int h){
		//��Ϊ�գ�ֱ�ӷ���
		if(r == null){
			return;
		}
		//�ݹ���ã���ʾ��r���ҽڵ�Ϊ���ڵ����
		//�߶�Ϊh+1
		ShowHT(r.getRight(), h + 1);
		//��ӡr�ڵ�
		printnode(r, h);
		//�ݹ���ã���ʾ��r����ڵ�Ϊ���ڵ����
		//�߶�Ϊh+1
		ShowHT(r.getLeft(), h + 1);
	}
	
	//��ӡhuffman��������ӡ��rootΪ���ڵ����
	//��ʼ�߶�Ϊ0
	public void printTree(){
		ShowHT(root, 0);
	}
	
	//����ǰ�����򡢺���Ͳ������
	public void order_visit(){
		LinkBinTree lbt = new LinkBinTree(root);
		//����ǰ�����
		System.out.println("\n�ݹ��㷨ʵ��ǰ�������");
		lbt.rec_preorder(lbt.getRoot());
		System.out.println("\n�����㷨ʵ��ǰ�������");
		lbt.itr_preorder(lbt.getRoot());
		//�����������
		System.out.println("\n�ݹ��㷨ʵ�����������");
		lbt.rec_inorder(lbt.getRoot());
		System.out.println("\n�����㷨ʵ�����������");
		lbt.itr_inorder(lbt.getRoot());
		//���ֺ������
		System.out.println("\n�ݹ��㷨ʵ�ֺ��������");
		lbt.rec_postorder(lbt.getRoot());
		System.out.println("\n�����㷨ʵ�ֺ��������");
		lbt.itr_postoder(lbt.getRoot());
		//�������
		System.out.println("\n�����㷨ʵ�ֲ��������");
		lbt.layer_order(lbt.getRoot());
	}
}
