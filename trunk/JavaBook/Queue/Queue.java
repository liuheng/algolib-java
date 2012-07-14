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
 * 队列接口设计，Queue.java
 */
public interface Queue {
	public void enqueue(ElemItem elem);	//向队列列尾插入新元素
	public ElemItem dequeue();			//将队列列首处的元素项删除并返回
	public ElemItem frontVal();			//返回列首的元素项
	public int currSize();				//返回当前队列中有效元素项的个数
	public void printQueue();			//从队列头开始打印的队列中所有元素项

}
