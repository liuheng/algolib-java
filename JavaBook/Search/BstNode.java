/*
 * Created on 2010-8-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Search;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 */
public class BstNode implements Comparable{
	//˽�г�Ա����
	private ElemItem elem;
	//�����ӽڵ�
	private BstNode leftNode;
	private BstNode rightNode;
	
	private int N;
	//�޲������캯��
	public BstNode(){
		elem = null;
		leftNode = null;
		rightNode = null;
		N = 0;
	}
	
	//���������캯��
	public BstNode(ElemItem _e, 
				BstNode _l,
				BstNode _r,
				int _N){
		elem = _e;
		leftNode = _l;
		rightNode = _r;
		N = _N;
	}
	
	public void setN(int _N){
		N = _N;
	}
	
	public int getN(){
		return N;
	}
	
	//��ȡԪ����
	public ElemItem getElem(){
		return elem;
	}
	
	//����Ԫ����
	public void setElem(ElemItem _elem){
		elem = _elem;
	}
	
	//��ȡ���ӽڵ�
	public BstNode getLeft(){
		return leftNode;
	}
	
	//�������ӽڵ�
	public void setLeft(BstNode lft){
		leftNode = lft;
	}
	
	//��ȡ���ӽڵ�
	public BstNode getRight(){
		return rightNode;
	}
	
	//�������ӽڵ�
	public void setRight(BstNode rgt){
		rightNode = rgt;
	}
	
	//�жϽڵ��Ƿ���Ҷ�ڵ�
	public boolean isLeaf(){
		return (leftNode == null) &&
				(rightNode == null);
	}
	
	//�����ַ���
	public String toString(){
		return elem.getElem().toString();
	}

	// ʵ�ֽӿں���
	public int compareTo(Object arg0) {
		return elem.compareTo((ElemItem)arg0);
	}
}
