/*
 * Created on 2010-4-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Heap;

import Element.ElemItem;

/**
 * @author Lu Wei 
 * 堆数据结构 Heap.java
 *
 */
public interface Heap {
	public boolean insert(ElemItem elem);	//堆中合适的位置插入新的元素项
	public ElemItem remove(int position);	//删除position位置的元素项
	public ElemItem removeMax();			//删除堆中最大或最小元素项
	public boolean exchange(int i, int j);	//交换位置i和位置j处的两个元素
	public void shiftdown(int k);			//自顶向下堆化
	public void shiftup(int k);				//自底向上堆化
	public ElemItem topVal();				//返回堆顶元素项
	public int heapSize();					//返回当前堆中元素项的个数
	public int leftchild(int position);		//获取position的左子节点位置
	public int rightchild(int position);	//获取position的右子节点位置
	public int parent(int position);		//获取position的父节点位置
	public boolean isleaf(int position);	//判断当前节点是否是叶节点
	public void printHeap();				//打印当前堆中所有元素
}
