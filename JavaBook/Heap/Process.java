/*
 * Created on 2010-4-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Heap;

/**
 * @author Lu Wei
 *
 * CPU �����࣬
 */
public class Process implements Comparable{
	int id;			// ���̵�ID
	int priory;		//���̵����ȼ�
	int processtime;//���̵Ĵ���ʱ��
	int cometime;	//���̵ĵ���ʱ��
	public Process(){}
	
	// �ȽϽ��̵����ȼ�
	public int compareTo(Object o) {
		Process p =(Process)(o);
		// �ϸߣ�����1
		if(this.priory > p.priory) return 1;
		// ��ͬ������0
		else if(this.priory == p.priory) return 0;
		// �ϵͣ�����-1
		else return -1;
	}
	
}
