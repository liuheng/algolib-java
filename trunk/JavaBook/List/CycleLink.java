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
	private DoubleNode head;	//循环链表的表头
	private DoubleNode tail;	//循环链表的表尾
	private DoubleNode curr;	//循环链表的当前位置
	private int currSize;	// 当前的循环链表长度
	
	public CycleLink(){
		head = new DoubleNode(null, null, null);
		tail = head;
		curr = head;
		currSize = 0;
	}

	public void insert(ElemItem elem) {
		if(curr == tail) return;//不能插入
		//用当前节点作为后驱节点、curr作为前去节点、elem作为元素构造新的节点，并作为新的当前节点
		curr.setNext(new DoubleNode(elem, curr.getNext(), curr));
		//插入前当前节点的前向节点改为新节点
		curr.getNext().getNext().setPrev(curr.getNext());
		currSize++;
	}


	public ElemItem remove() {
		if(curr == null || curr.getNext() == null) return null;	//无删除对象
		//若curr==tail，此时curr.getNext()指向head，不能直接删除head
		if(curr == tail) return null;//不能删除
		ElemItem forReturn = curr.getNext().getElem();//当前位置的元素项
		//若要删除的是表尾，则特殊对待
		if(curr.getNext() == tail)	tail = curr;
		//下面两行执行删除节点
		curr.setNext(curr.getNext().getNext());
		curr.getNext().setPrev(curr);
		currSize--;
		return forReturn;
	}


	public void append(ElemItem elem) {
		tail.setNext(new DoubleNode(elem, head, tail));//在表尾添加元素项
		tail = tail.getNext();//将表尾位置向后移
		head.setPrev(tail);		//表头的前向节点为表尾节点
		currSize++;
	}


	public void clear() {
		head = new DoubleNode(null, null, null);	//将表头赋值为空节点
		curr = head;
		tail = head;	//还原为初始状态
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
		// 如果curr为tail。则将设置的是
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
