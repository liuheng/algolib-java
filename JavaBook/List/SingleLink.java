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
public class SingleLink implements List{

	private SingleNode head;	//����ı�ͷ
	private SingleNode curr;	//����ĵ�ǰλ��
	private int currSize;	// ��ǰ��������
	public SingleLink(){	//���캯��
		head = null;
		curr = null;
		currSize = 0;
	}
	/* (non-Javadoc)
	 * @see List.List#insert(Element.ElemItem)
	 */
	public void insert(ElemItem elem) {
		SingleNode forInsertNode = new SingleNode(elem, curr);
		if(curr == head){//��ǰλ���ڱ�ͷ����ֱ���޸ı�ͷ
			head = forInsertNode;
			currSize++;
			return;
		}
		SingleNode preNode = head;	//����Ѱ�ҵ�ǰ�ڵ��ǰһ���ڵ�
		while(preNode.getNext() != curr){//��ͷ��ʼѭ��Ѱ��ǰһ���ڵ�
			preNode = preNode.getNext();
		}
		preNode.setNext(forInsertNode);//��curr��ǰ��ڵ����һ���ڵ�
		curr = forInsertNode;
		currSize++;
	}

	/* (non-Javadoc)
	 * @see List.List#remove()
	 */
	public ElemItem remove() {
		ElemItem forReturn = curr.getElem();
		if(curr == head){//����ǰָ��λ��Ϊͷ������ֱ�ӽ�ͷ�����ƣ�������ǰλ��������
			head = head.getNext();
			curr = curr.getNext();
			currSize--;	//��ǰ�����С��1
			return forReturn;
		}
		SingleNode preNode = head;	//����Ѱ�ҵ�ǰ�ڵ��ǰһ���ڵ�
		while(preNode.getNext() != curr){//��ͷ��ʼѭ��Ѱ��ǰһ���ڵ�
			preNode = preNode.getNext();
		}
		preNode.setNext(curr.getNext());//���������趨ǰһ�ڵ����һ�ڵ�
		if(curr.getNext() == null)curr = preNode;//��ǰλ��Ϊ����β��ʱ����ǰ�ڵ���Ϊǰһ�ڵ�
		else curr = curr.getNext();//��ǰλ�ò�������β��ʱ����ǰ�ڵ���Ϊ��һ�ڵ�
		currSize--;
		return forReturn;
	}

	/* (non-Javadoc)
	 * @see List.List#append(Element.ElemItem)
	 */
	public void append(ElemItem elem) {
		if (null == head){ 	// ��ǰ��Ϊ�ձ�
			head = new SingleNode(elem, null);// ��ǰ����Ϊ��ʱ���½��Ľڵ㸳ֵ����ͷ
			curr = head;
			return;
		}
		SingleNode tail = head;
		while(tail != null &&tail.getNext() != null){	//λ�ú���ֱ���������β��
			tail = tail.getNext();
		}
		tail.setNext(new SingleNode(elem, null));	//��ǰ����Ϊ��ʱ�����½��ڵ㸳ֵΪ��β����һ�ڵ�
		currSize++;
	}

	/* (non-Javadoc)
	 * @see List.List#clear()
	 */
	public void clear() {
		head = null;	//����ͷ�͵�ǰλ�ö�ָ���
		curr = null;
		currSize = 0;
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
		if(curr != null && curr.getNext() != null)curr = curr.getNext();
		return 0;
	}

	/* (non-Javadoc)
	 * @see List.List#prev()
	 */
	public int prev() {
		if(curr == head){
			return -1;
		}
		SingleNode preNode = head;
		while(preNode.getNext() != curr){
			preNode = preNode.getNext();
		}
		curr = preNode;
		return 0;
	}

	/* (non-Javadoc)
	 * @see List.List#setCurrVal(Element.ElemItem)
	 */
	public void setCurrVal(ElemItem elem) {
		if(curr == null) return;
		curr.setElem(elem);
		
	}

	/* (non-Javadoc)
	 * @see List.List#getCurrVal()
	 */
	public ElemItem getCurrVal() {
		if(curr == null) return null;
		return curr.getElem();
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
		// TODO Auto-generated method stub
		return currSize;
	}

	/* (non-Javadoc)
	 * @see List.List#inList()
	 */
	public boolean inList() {
		// TODO Auto-generated method stub
		return curr == null;
	}

	/* (non-Javadoc)
	 * @see List.List#printList()
	 */
	public void printList() {
		// TODO Auto-generated method stub
		SingleNode ptr = head;
		if(ptr == null) 
			System.out.println("��ǰ����Ϊ�գ�");
		System.out.println("��ǰ������Ԫ��Ϊ��");
		while(ptr.getNext() != null){
			System.out.print(ptr.getElem().getElem() + ", ");
			ptr= ptr.getNext();
		}
		System.out.println(ptr.getElem().getElem() + ".");
	}

}
