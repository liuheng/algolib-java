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
	private DoubleNode head;	//双链表的表头
	private DoubleNode tail;	//双链表的表尾
	private DoubleNode curr;	//双链表的当前位置
	private int currSize;	// 当前的双链表长度
	
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
		//用当前节点作为后驱节点、curr作为前去节点、elem作为元素构造新的节点，并作为新的当前节点
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
		if(curr == null || curr.getNext() == null) return null;	//无删除对象
		ElemItem forReturn = curr.getNext().getElem();//当前位置的元素项
		if(curr.getNext() == tail)	//若要删除的是表尾，则特殊对待
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
		tail.setNext(new DoubleNode(elem, null, curr));//在表尾添加元素项
		tail = tail.getNext();//将表尾位置向后移
		currSize++;
		
	}

	/* (non-Javadoc)
	 * @see List.List#clear()
	 */
	public void clear() {
		head = new DoubleNode(null, null, null);	//将表头赋值为空节点
		curr = head;
		tail = head;	//还原为初始状态
		
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
