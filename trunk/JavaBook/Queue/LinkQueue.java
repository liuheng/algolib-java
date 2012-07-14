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
 * 链表队列类，LinkQueue.java
 */
public class LinkQueue implements Queue{
	private SingleNode head;	// 队列头
	private SingleNode tail;	// 队列尾
	private int currSize;		// 当前队列的元素项的个数
	public LinkQueue(){	//构造函数
		head = null;
		tail = null;
		currSize = 0;
	}

	public void enqueue(ElemItem elem) {
		// 以创建新的节点
		if(tail == null){//当前队列为空
			//创建新节点作为tail和head
			tail = new SingleNode(elem, null);
			head = tail;
			currSize++;
			return;
		}
		//当前队列不是空的，则新节点添加到队列尾部
		tail.setNext(new SingleNode(elem, null));
		tail = tail.getNext();
		currSize++;
	}

	public ElemItem dequeue() {
		ElemItem forReturn;
		// 当前元素大于一个，正常处理
		if(currSize > 1){
			forReturn = head.getElem();
			head = head.getNext();
			currSize--;
		}
		//当前只有一个元素项，直接将head和tail重置为空
		else if(currSize == 1){
			forReturn = head.getElem();
			head = null;
			tail = null;
			currSize--;
		}
		//队列为空，返回null
		else forReturn = null;
		return forReturn;
	}

	public ElemItem frontVal() {
		if(currSize == 0)return null;//当前队列为空
		else return head.getElem();
	}

	public int currSize() {
		return currSize;
	}

	public void printQueue() {
		if(currSize <= 0) System.out.println("队列为空");
		else{
			System.out.println("队列的元素项cong列首到列尾为：");
			for(SingleNode node = head; node != tail; 
							node = node.getNext()){
				System.out.print(node.getElem().getElem() + ", ");
			}
			System.out.println(tail.getElem().getElem() + ".");
		}
	}
}
