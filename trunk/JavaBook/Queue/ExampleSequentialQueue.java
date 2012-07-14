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
 * 顺序链表测试示例程序，仿真生产者-消费者模型
 * ExampleSequentialQueue.java
 */
public class ExampleSequentialQueue {
	public static void main(String args[]){
		SequentialQueue sqlQueue = new SequentialQueue(5);
		sqlQueue.enqueue(new ElemItem<Double>(1.234));
		sqlQueue.enqueue(new ElemItem<String>("java"));
		sqlQueue.printQueue();
		int idxCP = 0;		//标识消费者和生产者的标识位：0——消费者；1——生产者
		int numWait[] = new int[98];
		int numOverFlow[] = new int[98];
		for(int k = 1; k < 99; k++){	//调整消费者消费速度（由小到大）
			for(int i = 0; i < 100; i++){//共竞争100次
				//用于表征当前是消费还是生产
				idxCP = (int)(Math.random() + (double)k / 100d);
				if(0 == idxCP){	// 消费者
					if(null == sqlQueue.dequeue())//队列为空
						numWait[k-1] += 1;//消费者等待
				}
				else if (1 == idxCP){//生产者
					if(sqlQueue.currSize() == 5)//队列满了
					{
						numOverFlow[k-1] += 1;//生产者等待
						continue;
					}
					//生产进行
					else sqlQueue.enqueue(new ElemItem<Integer>(1));
				}
			}
		}
		//返回生产者和消费者在不同消费速度下的等待次数
		for(int i = 0; i < 98; i++)System.out.print(numWait[i] + "\t");
		System.out.println();
		for(int i = 0; i < 98; i++)System.out.print(numOverFlow[i] + "\t");
	}
}
