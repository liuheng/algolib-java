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
 * 进程抢占CPU的示例程序，CPUWaitTime.java
 */
public class CPUWaitTime {
	//进程进入进程池的时间
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
	//设置三个进程优先级，数值越大优先级越大
	private static int pry[] = {0, 1, 2};
	
	public static void main(String args[]){
		Process pc[];// = new Process[100];
		pc = new Process[100];
		for(int i = 0; i < 100; i++){
			pc[i] = new Process();
			//进程号
			pc[i].id = i;
			//随机生成进程优先级，等概率生成
			double dr = Math.random();
			int r = (dr <= 0.33)?0:((dr <=0.66)?1:2);
			pc[i].priory = pry[r];
			//设置该进程进入进程池的时间
			pc[i].cometime = cometime[i];
			//随机生成进程的处理时间，等概率生成8，9，10
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
			// 时间递增1
			cnt++;
			//如果CPU正在处理中，
			//则正在处理的进程的处理时间递减1
			if(processcnt > 0) processcnt--;
			// 如果正在处理的进程处理完毕
			if(processcnt == 0){
				//删除优先队列顶部进程（进入CPU处理）
				ElemItem e = mheap.removeMax();	
				//更新刚刚删除的进程的优先级对应的等待时间
				waittime[((Process)e.getElem()).priory]
				 += cnt-((Process)e.getElem()).cometime;
				System.out.println(waittime[0]);
				System.out.println(waittime[1]);
				System.out.println(waittime[2]);
				// 获取当前优先队列列顶部的进程
				e = mheap.topVal();
				//如果此时还没有进程在进程池中，则将
				//当前CPU正在处理的进程的处理时间设为-1
				if(e == null){
					processcnt = -1;
				}
				//将优先队列列顶的新进程的处理时间作为
				//当前CPU正在处理的进程的处理时间
				else 
					processcnt = 
					  ((Process)e.getElem()).processtime;
			}
			// 进程进入进程池
			if(idx < 100 && cnt == cometime[idx]){
				mheap.insert(new ElemItem<Process>(pc[idx]));
				//如果进入的进程是第一个，则设置当前CPU
				//正在处理的进程的处理时间
				if(mheap.heapSize() == 1) 
					processcnt = pc[idx].processtime;
				idx++;
			}
			// 100个进程都进入了进程池并且此时进程堆也空，过程结束
			if(idx == 100 && mheap.heapSize() == 0) break;
		}
	}	
}
