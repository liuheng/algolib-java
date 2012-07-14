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
 * ˳���������ʾ�����򣬷���������-������ģ��
 * ExampleSequentialQueue.java
 */
public class ExampleSequentialQueue {
	public static void main(String args[]){
		SequentialQueue sqlQueue = new SequentialQueue(5);
		sqlQueue.enqueue(new ElemItem<Double>(1.234));
		sqlQueue.enqueue(new ElemItem<String>("java"));
		sqlQueue.printQueue();
		int idxCP = 0;		//��ʶ�����ߺ������ߵı�ʶλ��0���������ߣ�1����������
		int numWait[] = new int[98];
		int numOverFlow[] = new int[98];
		for(int k = 1; k < 99; k++){	//���������������ٶȣ���С����
			for(int i = 0; i < 100; i++){//������100��
				//���ڱ�����ǰ�����ѻ�������
				idxCP = (int)(Math.random() + (double)k / 100d);
				if(0 == idxCP){	// ������
					if(null == sqlQueue.dequeue())//����Ϊ��
						numWait[k-1] += 1;//�����ߵȴ�
				}
				else if (1 == idxCP){//������
					if(sqlQueue.currSize() == 5)//��������
					{
						numOverFlow[k-1] += 1;//�����ߵȴ�
						continue;
					}
					//��������
					else sqlQueue.enqueue(new ElemItem<Integer>(1));
				}
			}
		}
		//���������ߺ��������ڲ�ͬ�����ٶ��µĵȴ�����
		for(int i = 0; i < 98; i++)System.out.print(numWait[i] + "\t");
		System.out.println();
		for(int i = 0; i < 98; i++)System.out.print(numOverFlow[i] + "\t");
	}
}
