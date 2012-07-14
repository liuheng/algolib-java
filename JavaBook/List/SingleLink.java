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

	private SingleNode head;	//链表的表头
	private SingleNode curr;	//链表的当前位置
	private int currSize;	// 当前的链表长度
	public SingleLink(){	//构造函数
		head = null;
		curr = null;
		currSize = 0;
	}
	/* (non-Javadoc)
	 * @see List.List#insert(Element.ElemItem)
	 */
	public void insert(ElemItem elem) {
		SingleNode forInsertNode = new SingleNode(elem, curr);
		if(curr == head){//当前位置在表头，则直接修改表头
			head = forInsertNode;
			currSize++;
			return;
		}
		SingleNode preNode = head;	//用于寻找当前节点的前一个节点
		while(preNode.getNext() != curr){//从头开始循环寻找前一个节点
			preNode = preNode.getNext();
		}
		preNode.setNext(forInsertNode);//将curr的前向节点的下一个节点
		curr = forInsertNode;
		currSize++;
	}

	/* (non-Javadoc)
	 * @see List.List#remove()
	 */
	public ElemItem remove() {
		ElemItem forReturn = curr.getElem();
		if(curr == head){//若当前指向位置为头部，则直接将头往后移，并将当前位置往后移
			head = head.getNext();
			curr = curr.getNext();
			currSize--;	//当前链表大小减1
			return forReturn;
		}
		SingleNode preNode = head;	//用于寻找当前节点的前一个节点
		while(preNode.getNext() != curr){//从头开始循环寻找前一个节点
			preNode = preNode.getNext();
		}
		preNode.setNext(curr.getNext());//首先重新设定前一节点的下一节点
		if(curr.getNext() == null)curr = preNode;//当前位置为链表尾部时，则当前节点置为前一节点
		else curr = curr.getNext();//当前位置不是链表尾部时，则当前节点置为后一节点
		currSize--;
		return forReturn;
	}

	/* (non-Javadoc)
	 * @see List.List#append(Element.ElemItem)
	 */
	public void append(ElemItem elem) {
		if (null == head){ 	// 当前表为空表
			head = new SingleNode(elem, null);// 当前链表为空时将新建的节点赋值给表头
			curr = head;
			return;
		}
		SingleNode tail = head;
		while(tail != null &&tail.getNext() != null){	//位置后移直至到链表的尾部
			tail = tail.getNext();
		}
		tail.setNext(new SingleNode(elem, null));	//当前链表不为空时，将新建节点赋值为表尾的下一节点
		currSize++;
	}

	/* (non-Javadoc)
	 * @see List.List#clear()
	 */
	public void clear() {
		head = null;	//将表头和当前位置都指向空
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
			System.out.println("当前链表为空！");
		System.out.println("当前链表中元素为：");
		while(ptr.getNext() != null){
			System.out.print(ptr.getElem().getElem() + ", ");
			ptr= ptr.getNext();
		}
		System.out.println(ptr.getElem().getElem() + ".");
	}

}
