/*
 * Created on 2010-4-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Heap;

import Element.ElemItem;

/**
 * @author Lu Wei 
 * �����ݽṹ Heap.java
 *
 */
public interface Heap {
	public boolean insert(ElemItem elem);	//���к��ʵ�λ�ò����µ�Ԫ����
	public ElemItem remove(int position);	//ɾ��positionλ�õ�Ԫ����
	public ElemItem removeMax();			//ɾ������������СԪ����
	public boolean exchange(int i, int j);	//����λ��i��λ��j��������Ԫ��
	public void shiftdown(int k);			//�Զ����¶ѻ�
	public void shiftup(int k);				//�Ե����϶ѻ�
	public ElemItem topVal();				//���ضѶ�Ԫ����
	public int heapSize();					//���ص�ǰ����Ԫ����ĸ���
	public int leftchild(int position);		//��ȡposition�����ӽڵ�λ��
	public int rightchild(int position);	//��ȡposition�����ӽڵ�λ��
	public int parent(int position);		//��ȡposition�ĸ��ڵ�λ��
	public boolean isleaf(int position);	//�жϵ�ǰ�ڵ��Ƿ���Ҷ�ڵ�
	public void printHeap();				//��ӡ��ǰ��������Ԫ��
}
