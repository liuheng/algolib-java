/*
 * Created on 2010-4-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Queue;

import Element.ElemItem;
import List.SingleNode;

/**
 * @author Lu wei
 * ��������࣬LinkQueue.java
 */
public class LinkQueue implements Queue{
	private SingleNode head;	// ����ͷ
	private SingleNode tail;	// ����β
	private int currSize;		// ��ǰ���е�Ԫ����ĸ���
	public LinkQueue(){	//���캯��
		head = null;
		tail = null;
		currSize = 0;
	}

	public void enqueue(ElemItem elem) {
		// �Դ����µĽڵ�
		if(tail == null){//��ǰ����Ϊ��
			//�����½ڵ���Ϊtail��head
			tail = new SingleNode(elem, null);
			head = tail;
			currSize++;
			return;
		}
		//��ǰ���в��ǿյģ����½ڵ���ӵ�����β��
		tail.setNext(new SingleNode(elem, null));
		tail = tail.getNext();
		currSize++;
	}

	public ElemItem dequeue() {
		ElemItem forReturn;
		// ��ǰԪ�ش���һ������������
		if(currSize > 1){
			forReturn = head.getElem();
			head = head.getNext();
			currSize--;
		}
		//��ǰֻ��һ��Ԫ���ֱ�ӽ�head��tail����Ϊ��
		else if(currSize == 1){
			forReturn = head.getElem();
			head = null;
			tail = null;
			currSize--;
		}
		//����Ϊ�գ�����null
		else forReturn = null;
		return forReturn;
	}

	public ElemItem frontVal() {
		if(currSize == 0)return null;//��ǰ����Ϊ��
		else return head.getElem();
	}

	public int currSize() {
		return currSize;
	}

	public void printQueue() {
		if(currSize <= 0) System.out.println("����Ϊ��");
		else{
			System.out.println("���е�Ԫ����cong���׵���βΪ��");
			for(SingleNode node = head; node != tail; 
							node = node.getNext()){
				System.out.print(node.getElem().getElem() + ", ");
			}
			System.out.println(tail.getElem().getElem() + ".");
		}
	}
}
