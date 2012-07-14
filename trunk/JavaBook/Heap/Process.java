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
 * CPU 进程类，
 */
public class Process implements Comparable{
	int id;			// 进程的ID
	int priory;		//进程的优先级
	int processtime;//进程的处理时间
	int cometime;	//进程的到达时间
	public Process(){}
	
	// 比较进程的优先级
	public int compareTo(Object o) {
		Process p =(Process)(o);
		// 较高，返回1
		if(this.priory > p.priory) return 1;
		// 相同，返回0
		else if(this.priory == p.priory) return 0;
		// 较低，返回-1
		else return -1;
	}
	
}
