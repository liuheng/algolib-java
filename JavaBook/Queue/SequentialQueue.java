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
 *  ˳������� SequentialQueue.java
 */
public class SequentialQueue implements Queue{
	private int head;		//��������
	private int tail;		//������β
	private int totalSize;	//���е���󳤶�
	private int currSize;	//��ǰ������ЧԪ����ĸ���
	private ElemItem sqlQueueData[];		//��������֯���е�Ԫ����
	
	public SequentialQueue(int _totalsize){//���캯��
		head = 0;					//��ʼ������Ϊλ��0
		tail = -1;					//��ʼ����βΪλ��0
		totalSize = (_totalsize > 0)?(_totalsize):0;//��ʼ���б�Ԫ�ظ���
		sqlQueueData = new ElemItem[totalSize];
		currSize = 0;
	}
	public SequentialQueue(){//���캯��
		head = 0;					//��ʼ������Ϊλ��0
		tail = -1;					//��ʼ����βΪλ��0
		totalSize = 0;		//��ʼ���б�Ԫ�ظ���
		currSize = 0;
	}
	
	public void enqueue(ElemItem elem) {
		if(currSize >= totalSize) System.out.println("����������");
		else{
			tail = (tail + 1) % totalSize;	//������βѭ������
			sqlQueueData[tail] = elem;		//�ڶ���β�����Ԫ��
			currSize++;						//��ЧԪ�����������1
		}
		
	}

	public ElemItem dequeue() {
		if(currSize <= 0){		//��ǰ������Ԫ��Ϊ�� 
			System.out.println("����Ϊ�գ�");
			return null;
		}
		else{
			ElemItem forReturn = sqlQueueData[head];//�ݴ�����ص�Ԫ����
			head = (head + 1) % totalSize;//��ͷѭ������
			currSize--;		//��ǰ��ЧԪ�ظ����ݼ�1
			return forReturn;	//���س��е�Ԫ����
		}
	}

	public ElemItem frontVal() {
		if(currSize <= 0){	//��ǰ����Ϊ��
			System.out.println("����Ϊ�գ��޷���������Ԫ����");
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
		if(currSize == 0)System.out.println("����Ϊ�գ�");
		else{
			for(int i = head; cnt < currSize - 1; i = (i + 1) % totalSize){
				System.out.print(sqlQueueData[i].getElem() + ", ");
				cnt++;
			}
			System.out.println(sqlQueueData[tail].getElem() + ".");
		}
		
	}

}
