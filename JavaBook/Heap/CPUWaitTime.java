/*
 * Created on 2010-4-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Heap;

import Element.ElemItem;

/**
 * @author LuWei
 *
 * ������ռCPU��ʾ������CPUWaitTime.java
 */
public class CPUWaitTime {
	//���̽�����̳ص�ʱ��
	static int cometime[]={6, 10, 18, 23, 31, 35, 41, 45, 
		49, 52, 55, 60, 65, 70, 78, 83, 89, 92, 98, 104, 
		110, 117, 122, 124, 130, 136, 140, 144, 151, 156,
		161, 165, 168, 171, 176, 180, 184, 189, 195, 203,
		207, 215, 219, 225, 231, 236, 242, 246, 247, 253,
		257, 263, 268, 270, 276, 281, 287, 295, 303, 309,
		315, 321, 323, 326, 333, 341, 345, 352, 354, 360,
		363, 368, 370, 372, 376, 383, 388, 393, 401, 403,
		409, 415, 421, 424, 430, 433, 437, 442, 448, 454,
		462, 470, 475, 482, 486, 495, 501, 506, 511, 520};
	//���������������ȼ�����ֵԽ�����ȼ�Խ��
	private static int pry[] = {0, 1, 2};
	
	public static void main(String args[]){
		Process pc[];// = new Process[100];
		pc = new Process[100];
		for(int i = 0; i < 100; i++){
			pc[i] = new Process();
			//���̺�
			pc[i].id = i;
			//������ɽ������ȼ����ȸ�������
			double dr = Math.random();
			int r = (dr <= 0.33)?0:((dr <=0.66)?1:2);
			pc[i].priory = pry[r];
			//���øý��̽�����̳ص�ʱ��
			pc[i].cometime = cometime[i];
			//������ɽ��̵Ĵ���ʱ�䣬�ȸ�������8��9��10
			dr = Math.random();
			r = (dr <= 0.33)?8:((dr <=0.66)?9:10);
			pc[i].processtime = r;
		}
		
		int waittime[] = {0, 0, 0};
		int cnt = 0; int idx = 1;
		int processcnt = pc[0].processtime;
		MaxHeap mheap = new MaxHeap(100);
		mheap.insert(new ElemItem<Process>(pc[0]));
		while(idx <= 100){
			// ʱ�����1
			cnt++;
			//���CPU���ڴ����У�
			//�����ڴ���Ľ��̵Ĵ���ʱ��ݼ�1
			if(processcnt > 0) processcnt--;
			// ������ڴ���Ľ��̴������
			if(processcnt == 0){
				//ɾ�����ȶ��ж������̣�����CPU����
				ElemItem e = mheap.removeMax();	
				//���¸ո�ɾ���Ľ��̵����ȼ���Ӧ�ĵȴ�ʱ��
				waittime[((Process)e.getElem()).priory]
				 += cnt-((Process)e.getElem()).cometime;
				System.out.println(waittime[0]);
				System.out.println(waittime[1]);
				System.out.println(waittime[2]);
				// ��ȡ��ǰ���ȶ����ж����Ľ���
				e = mheap.topVal();
				//�����ʱ��û�н����ڽ��̳��У���
				//��ǰCPU���ڴ���Ľ��̵Ĵ���ʱ����Ϊ-1
				if(e == null){
					processcnt = -1;
				}
				//�����ȶ����ж����½��̵Ĵ���ʱ����Ϊ
				//��ǰCPU���ڴ���Ľ��̵Ĵ���ʱ��
				else 
					processcnt = 
					  ((Process)e.getElem()).processtime;
			}
			// ���̽�����̳�
			if(idx < 100 && cnt == cometime[idx]){
				mheap.insert(new ElemItem<Process>(pc[idx]));
				//�������Ľ����ǵ�һ���������õ�ǰCPU
				//���ڴ���Ľ��̵Ĵ���ʱ��
				if(mheap.heapSize() == 1) 
					processcnt = pc[idx].processtime;
				idx++;
			}
			// 100�����̶������˽��̳ز��Ҵ�ʱ���̶�Ҳ�գ����̽���
			if(idx == 100 && mheap.heapSize() == 0) break;
		}
	}	
}
