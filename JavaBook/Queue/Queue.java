/*
 * Created on 2010-4-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Queue;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * ���нӿ���ƣ�Queue.java
 */
public interface Queue {
	public void enqueue(ElemItem elem);	//�������β������Ԫ��
	public ElemItem dequeue();			//���������״���Ԫ����ɾ��������
	public ElemItem frontVal();			//�������׵�Ԫ����
	public int currSize();				//���ص�ǰ��������ЧԪ����ĸ���
	public void printQueue();			//�Ӷ���ͷ��ʼ��ӡ�Ķ���������Ԫ����

}
