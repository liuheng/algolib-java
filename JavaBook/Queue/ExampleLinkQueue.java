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
 * 链表队列测试示例程序，ExampleLinkQueue.java
 */
public class ExampleLinkQueue {
	public static void main(String args[]){
		//创建队列
		LinkQueue lqueue = new LinkQueue();
		//相对列中添加10个整数类型的元素项
		for (int i = 0; i < 10; i++){
			lqueue.enqueue(new ElemItem<Integer>(i));
		}
		//打印当前队列中的所有元素
		lqueue.printQueue();
		//添加双精度类型的元素12.345
		lqueue.enqueue(new ElemItem<Double>(12.345));
		//添加字符串类型的元素项"JAVA"
		lqueue.enqueue(new ElemItem<String>("JAVA"));
		//打印队列中所有元素项
		lqueue.printQueue();
		//去除队列中前三项
		lqueue.dequeue();
		lqueue.dequeue();
		lqueue.dequeue();
		//打印当前队列中的所有元素
		lqueue.printQueue();
	}
	
}
