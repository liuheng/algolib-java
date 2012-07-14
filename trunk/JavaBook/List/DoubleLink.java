/*
 * Created on 2010-4-14
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
public class DoubleLink implements List{
	private DoubleNode head;	//˫����ı�ͷ
	private DoubleNode tail;	//˫����ı�β
	private DoubleNode curr;	//˫����ĵ�ǰλ��
	private int currSize;	// ��ǰ��˫������
	
	public DoubleLink(){
		head = new DoubleNode(null, null, null);
		tail = head;
		curr = head;
		currSize = 0;
	}

	/* (non-Javadoc)
	 * @see List.List#insert(Element.ElemItem)
	 */
	public void insert(ElemItem elem) {
		//�õ�ǰ�ڵ���Ϊ�����ڵ㡢curr��Ϊǰȥ�ڵ㡢elem��ΪԪ�ع����µĽڵ㣬����Ϊ�µĵ�ǰ�ڵ�
		curr.setNext(new DoubleNode(elem, curr.getNext(), curr));
		curr.getNext().getNext().setPrev(curr.getNext());
		if(curr == tail){
			tail = tail.getNext();
		}
		currSize++;
		
	}

	/* (non-Javadoc)
	 * @see List.List#remove()
	 */
	public ElemItem remove() {
		if(curr == null || curr.getNext() == null) return null;	//��ɾ������
		ElemItem forReturn = curr.getNext().getElem();//��ǰλ�õ�Ԫ����
		if(curr.getNext() == tail)	//��Ҫɾ�����Ǳ�β��������Դ�
			tail = curr;
		curr.setNext(curr.getNext().getNext());
		if(curr.getNext() != null){
			curr.getNext().setPrev(curr);
		}
		currSize--;
		return forReturn;
	}

	/* (non-Javadoc)
	 * @see List.List#append(Element.ElemItem)
	 */
	public void append(ElemItem elem) {
		tail.setNext(new DoubleNode(elem, null, curr));//�ڱ�β���Ԫ����
		tail = tail.getNext();//����βλ�������
		currSize++;
		
	}

	/* (non-Javadoc)
	 * @see List.List#clear()
	 */
	public void clear() {
		head = new DoubleNode(null, null, null);	//����ͷ��ֵΪ�սڵ�
		curr = head;
		tail = head;	//��ԭΪ��ʼ״̬
		
	}

	/* (non-Javadoc)
	 * @see List.List#goFirst()
	 */
	public void goFirst() {
		curr = head;
		
	}

	/* (non-Javadoc)
	 * @see List.List#next()
	 */
	public int next() {
		if(curr != null && curr.getNext() != null) curr = curr.getNext();
		return 0;
	}

	/* (non-Javadoc)
	 * @see List.List#prev()
	 */
	public int prev() {
		if(curr == null || curr == head){
			return -1;
		}
		else{
			curr = curr.getPrev();
		}
		return 0;
	}

	/* (non-Javadoc)
	 * @see List.List#setCurrVal(Element.ElemItem)
	 */
	public void setCurrVal(ElemItem elem) {
		if(curr != null && curr.getNext() != null)
			curr.getNext().setElem(elem);
		
	}

	/* (non-Javadoc)
	 * @see List.List#getCurrVal()
	 */
	public ElemItem getCurrVal() {
		if(curr != null && curr.getNext() != null)
			return curr.getNext().getElem();
		return null;
	}

	/* (non-Javadoc)
	 * @see List.List#getSize()
	 */
	public int getSize() {
		return currSize;
	}

	/* (non-Javadoc)
	 * @see List.List#getTotalSize()
	 */
	public int getTotalSize() {
		return currSize;
	}

	/* (non-Javadoc)
	 * @see List.List#inList()
	 */
	public boolean inList() {
		return curr == null || curr.getNext() == null;
	}

	/* (non-Javadoc)
	 * @see List.List#printList()
	 */
	public void printList() {
		DoubleNode ptr = head;
		while(ptr.getNext() != tail){
			System.out.print(ptr.getNext().getElem().getElem() + ", ");
			ptr = ptr.getNext();
		}
		System.out.println(tail.getElem().getElem() + ".");
		
	}

}
