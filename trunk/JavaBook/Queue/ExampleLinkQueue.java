/*
 * Created on 2010-4-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Queue;

import Element.ElemItem;

/**
 * @author lu wei
 * ������в���ʾ������ExampleLinkQueue.java
 */
public class ExampleLinkQueue {
	public static void main(String args[]){
		//��������
		LinkQueue lqueue = new LinkQueue();
		//����������10���������͵�Ԫ����
		for (int i = 0; i < 10; i++){
			lqueue.enqueue(new ElemItem<Integer>(i));
		}
		//��ӡ��ǰ�����е�����Ԫ��
		lqueue.printQueue();
		//���˫�������͵�Ԫ��12.345
		lqueue.enqueue(new ElemItem<Double>(12.345));
		//����ַ������͵�Ԫ����"JAVA"
		lqueue.enqueue(new ElemItem<String>("JAVA"));
		//��ӡ����������Ԫ����
		lqueue.printQueue();
		//ȥ��������ǰ����
		lqueue.dequeue();
		lqueue.dequeue();
		lqueue.dequeue();
		//��ӡ��ǰ�����е�����Ԫ��
		lqueue.printQueue();
	}
	
}
