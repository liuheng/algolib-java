/*
 * Created on 2010-5-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * �����������ṹ��Ŀ�������ڶ������ĸ��ֱ����㷨��
 */
public class LinkBinTreeNode implements Comparable{
	//˽�г�Ա����
	private ElemItem elem;
	//�����ӽڵ�
	private LinkBinTreeNode leftNode;
	private LinkBinTreeNode rightNode;
	
	//�޲������캯��
	public LinkBinTreeNode(){
		elem = null;
		leftNode = null;
		rightNode = null;
	}
	
	//���������캯��
	public LinkBinTreeNode(ElemItem _e, 
				LinkBinTreeNode _l,
				LinkBinTreeNode _r){
		elem = _e;
		leftNode = _l;
		rightNode = _r;
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
	public LinkBinTreeNode getLeft(){
		return leftNode;
	}
	
	//�������ӽڵ�
	public void setLeft(LinkBinTreeNode lft){
		leftNode = lft;
	}
	
	//��ȡ���ӽڵ�
	public LinkBinTreeNode getRight(){
		return rightNode;
	}
	
	//�������ӽڵ�
	public void setRight(LinkBinTreeNode rgt){
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
