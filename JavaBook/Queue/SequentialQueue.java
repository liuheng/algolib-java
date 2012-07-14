/*
 * Created on 2010-4-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Queue;

import Element.ElemItem;

/**
 * @author Lu wei
 *  顺序队列类 SequentialQueue.java
 */
public class SequentialQueue implements Queue{
	private int head;		//队列列首
	private int tail;		//队列列尾
	private int totalSize;	//队列的最大长度
	private int currSize;	//当前队列有效元素项的个数
	private ElemItem sqlQueueData[];		//用数组组织队列的元素项
	
	public SequentialQueue(int _totalsize){//构造函数
		head = 0;					//初始化列首为位置0
		tail = -1;					//初始化列尾为位置0
		totalSize = (_totalsize > 0)?(_totalsize):0;//初始化列表元素个数
		sqlQueueData = new ElemItem[totalSize];
		currSize = 0;
	}
	public SequentialQueue(){//构造函数
		head = 0;					//初始化列首为位置0
		tail = -1;					//初始化列尾为位置0
		totalSize = 0;		//初始化列表元素个数
		currSize = 0;
	}
	
	public void enqueue(ElemItem elem) {
		if(currSize >= totalSize) System.out.println("队列已满！");
		else{
			tail = (tail + 1) % totalSize;	//队列列尾循环后移
			sqlQueueData[tail] = elem;		//在队列尾添加新元素
			currSize++;						//有效元素项个数增加1
		}
		
	}

	public ElemItem dequeue() {
		if(currSize <= 0){		//当前队列中元素为空 
			System.out.println("队列为空！");
			return null;
		}
		else{
			ElemItem forReturn = sqlQueueData[head];//暂存待返回的元素项
			head = (head + 1) % totalSize;//列头循环后移
			currSize--;		//当前有效元素个数递减1
			return forReturn;	//返回出列的元素项
		}
	}

	public ElemItem frontVal() {
		if(currSize <= 0){	//当前队列为空
			System.out.println("队列为空，无法返回列首元素项");
			return null;		
		}
		else{
			return sqlQueueData[head];
		}
	}

	public int currSize() {
		return currSize;
	}

	public void printQueue() {
		int cnt = 0;
		if(currSize == 0)System.out.println("队列为空！");
		else{
			for(int i = head; cnt < currSize - 1; i = (i + 1) % totalSize){
				System.out.print(sqlQueueData[i].getElem() + ", ");
				cnt++;
			}
			System.out.println(sqlQueueData[tail].getElem() + ".");
		}
		
	}

}
