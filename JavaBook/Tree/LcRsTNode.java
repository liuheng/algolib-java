/*
 * Created on 2010-4-27
 *
 * ���ġ����ӽڵ�/���ֵܽڵ㡱���������Ľڵ����ʵ��
 */
package Tree;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * ������-���ֵ����ڵ����ݽṹ��
 */
public class LcRsTNode{
	//�ڵ�Ԫ����
	private ElemItem elem;
	//�ڵ����
	private int idx;
	//�ڵ�ĸ��ڵ�
	private int parentNode;
	//�ڵ�����ӽڵ�
	private int leftMostChildNode;
	//�ڵ�����ֵܽڵ�
	private int rightSiblingNode;
	
	//�޲������캯��
	public LcRsTNode(){
		elem = null;
		parentNode = -1;
		leftMostChildNode = -1;
		rightSiblingNode = -1;
	}
	
	//�в������캯��
	public LcRsTNode(ElemItem _elem, 
					int _parentNode,
					int _leftMtChild, 
					int _rightSibling){
		elem = _elem;
		parentNode = _parentNode;
		leftMostChildNode = _leftMtChild;
		rightSiblingNode = _rightSibling;
	}
	
	//���ýڵ�����
	public void setIdx(int _idx){
		idx = _idx;
	}
	
	//��ȡ�ڵ�����
	public int getIdx(){
		return idx;
	}
	
	//��ȡԪ����
	public ElemItem getElem() {
		return elem;
	}
	
	//����Ԫ����
	public void setElem(ElemItem _elem) {
		elem = _elem;
	}
	
	//��ȡ�ڵ�ĸ��ڵ�
	public int parent() {
		return parentNode;
	}
	
	//��ȡ�ڵ�������ӽڵ�
	public int leftMostChild() {
		return leftMostChildNode;
	}

	//��ȡ�ڵ�����ֵܽڵ�
	public int rightSibling() {
		return rightSiblingNode;
	}
	
	//����ڵ�ĸ��ڵ�
	public void setParent(int n) {
		parentNode = n;
	}
	
	//����ڵ�������ӽڵ�
	public void setLeftMostChild(int n) {
		leftMostChildNode = n;
	}
	
	//����ڵ�����ֵܽڵ�
	public void setNextSibling(int n) {
		rightSiblingNode = n;
	}
	
	//�ڵ��Ƿ���Ҷ�ڵ�
	public boolean isLeaf() {
		return leftMostChildNode == -1;
	}
	
	//����Ԫ����String��ʽ
	public String toString(){
		return elem.getElem().toString();
	}
}
