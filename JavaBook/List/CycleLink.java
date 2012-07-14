/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

import Element.ElemItem;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class CycleLink implements List{
	private DoubleNode head;	//ѭ������ı�ͷ
	private DoubleNode tail;	//ѭ������ı�β
	private DoubleNode curr;	//ѭ������ĵ�ǰλ��
	private int currSize;	// ��ǰ��ѭ��������
	
	public CycleLink(){
		head = new DoubleNode(null, null, null);
		tail = head;
		curr = head;
		currSize = 0;
	}

	public void insert(ElemItem elem) {
		if(curr == tail) return;//���ܲ���
		//�õ�ǰ�ڵ���Ϊ�����ڵ㡢curr��Ϊǰȥ�ڵ㡢elem��ΪԪ�ع����µĽڵ㣬����Ϊ�µĵ�ǰ�ڵ�
		curr.setNext(new DoubleNode(elem, curr.getNext(), curr));
		//����ǰ��ǰ�ڵ��ǰ��ڵ��Ϊ�½ڵ�
		curr.getNext().getNext().setPrev(curr.getNext());
		currSize++;
	}


	public ElemItem remove() {
		if(curr == null || curr.getNext() == null) return null;	//��ɾ������
		//��curr==tail����ʱcurr.getNext()ָ��head������ֱ��ɾ��head
		if(curr == tail) return null;//����ɾ��
		ElemItem forReturn = curr.getNext().getElem();//��ǰλ�õ�Ԫ����
		//��Ҫɾ�����Ǳ�β��������Դ�
		if(curr.getNext() == tail)	tail = curr;
		//��������ִ��ɾ���ڵ�
		curr.setNext(curr.getNext().getNext());
		curr.getNext().setPrev(curr);
		currSize--;
		return forReturn;
	}


	public void append(ElemItem elem) {
		tail.setNext(new DoubleNode(elem, head, tail));//�ڱ�β���Ԫ����
		tail = tail.getNext();//����βλ�������
		head.setPrev(tail);		//��ͷ��ǰ��ڵ�Ϊ��β�ڵ�
		currSize++;
	}


	public void clear() {
		head = new DoubleNode(null, null, null);	//����ͷ��ֵΪ�սڵ�
		curr = head;
		tail = head;	//��ԭΪ��ʼ״̬
	}


	public void goFirst() {
		curr = head;
	}

	public int next() {
		if(curr != null && curr.getNext() != null) 
			curr = curr.getNext();
		return 0;
	}


	public int prev() {
		curr = curr.getPrev();
		return 0;
	}


	public void setCurrVal(ElemItem elem) {
		// ���currΪtail�������õ���
		if(curr == tail ) return; 
		if(curr != null && curr.getNext() != null)
			curr.getNext().setElem(elem);
	}


	public ElemItem getCurrVal() {
		if(curr != null && curr.getNext() != null)
			return curr.getNext().getElem();
		return null;
	}

	public int getSize() {
		return currSize;
	}

	public int getTotalSize() {
		return currSize;
	}

	public boolean inList() {
		return curr == null || curr.getNext() == null;
	}

	public void printList() {
		DoubleNode ptr = head;
		while(ptr.getNext() != tail){
			System.out.print(ptr.getNext().getElem().getElem() + ", ");
			ptr = ptr.getNext();
		}
		System.out.println(tail.getElem().getElem() + ".");
		
	}

}
