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
 * 最大堆类，MaxHeap.java
 */
public class MaxHeap implements Heap{
	protected  ElemItem maxheapdata[];	//数组形式保存堆中元素项 
	protected  int maxSize;				//堆的最大大小，对应数组的最大长度
	protected  int currSize;			//堆当前有效大小
	
	// 构造函数
	public MaxHeap(int _MaxSize){		//构造函数
		maxSize = _MaxSize;				//最大元素个数
		currSize = 0;					//当前有效元素个数
		maxheapdata = new ElemItem[maxSize];//新建数组，个数为最大元素个数
	}
	
	// 构造函数2
	public MaxHeap(ElemItem _a[]){
		maxSize = _a.length;
		currSize = maxSize;
		maxheapdata = _a;
	}
	
	// 比较堆元素项大小的私有函数。
	protected int compare(int i, int j){
		return (maxheapdata[i]).compareTo(maxheapdata[j]);
	}
	
	public boolean insert(ElemItem elem) {
		if(currSize >= maxSize){
			System.out.println("堆已满！");
			return false;
		}
		else{
			int n = currSize++;		//n为堆的最后一个的位置
			maxheapdata[n] = elem;	//将元素插入
			shiftup(n);				//自底向上堆化
		}
		return true;
	}

	public ElemItem remove(int position) {
		if(position < 0 || position >= currSize){
			System.out.println("当前位置无效！");
			return null;
		}
		else{
			//将最后一个元素与position上的元素交换
			exchange(position, currSize - 1);
			//当前个数减1，currSize位置上元素即堆最后一个的元素项
			currSize--;	
			// 如果比父节点的值更大，则向上堆化
			if(1 == compare(position, parent(position))) 
				shiftup(position);
			else shiftdown(position);//向下堆化
		}
		return maxheapdata[currSize];//返回删除的元素
	}

	public ElemItem removeMax() {
		if(currSize <= 0){
			System.out.println("当前堆为空！");
			return null;
		}
		else{
			//将最后一个元素与0位置上的元素交换
			exchange(0, currSize - 1);
			//当前个数减1，则currSize位置上元素即堆最后一个的元素项
			currSize--;		
			if(currSize > 0) shiftdown(0);	//从堆顶开始自顶向下堆化
		}
		return maxheapdata[currSize];
	}

	public boolean exchange(int i, int j) {
		if( i < 0 || i >= currSize || j < 0 || j >= currSize){
			System.out.println("待交换位置不合法");
			return false;
		}
		else{
			// 交换i和j位置上的两个元素项
			ElemItem tmp = maxheapdata[i];
			maxheapdata[i] = maxheapdata[j];
			maxheapdata[j] = tmp;
		}
		return false;
	}

	/**
	 * 下移第k个元素项，使得k的值大于其左右子节点
	 * @param k	元素项的下标
	 */
	public void shiftdown(int k) {
		if(k < 0 || k >= currSize){
			System.out.println("当前位置" + k + "不合法");
			return;
		}
		while(!isleaf(k)){
			int l = leftchild(k);
			if(l <= currSize - 2 && compare(l, l + 1) == -1)
				l++;	// l是两个子节点中较大的元素项的标号
			if(compare(k, l) != -1) return;		//结束
			else{
				exchange(l, k);	//交换
				k = l;			//下移
			}
		}
	}

	public void shiftup(int k) {
		if(k < 0 || k >= currSize){
			System.out.println("当前位置" + k + "不合法");
			return;
		}
		int i;
		//k不是堆顶而且k处小于其父节点的值，则不断上移
		while(k >= 0 && compare(parent(k), k) == -1){
			exchange(k, parent(k));
			k = parent(k);
		}
	}

	
	public int heapSize() {
		return currSize;
	}

	public int leftchild(int position) {
		// 位置不合法：小于0或者没有子节点
		if(position < 0 || position >= currSize / 2)
			return -1;
		return 2 * position + 1;
	}

	public int rightchild(int position) {
		//位置不合法：小于0或者没有子节点
		if(position < 0 || position >= (currSize - 1) / 2)
			return -1;
		return 2 * position + 2;
	}

	public int parent(int position) {
		//位置不合法
		if(position < 0)
			return -1;
		return (int)((position - 1) / 2);
	}
	//判断是否是子节点
	public boolean isleaf(int position) {
		return position >= 0 && position >= currSize / 2;
	}

	public ElemItem topVal() {
		if(currSize > 0)return maxheapdata[0];
		return null;
	}
	
	//在特定高度打印一个元素项
	protected void printnode(int ps, int h){
		for(int i = 0; i < h; i++)
			System.out.print("\t");
		System.out.println(maxheapdata[ps].getElem());
	}
	//中序遍历堆中元素项
	protected void iterative_show(int pos, int h){
		if(pos < 0 || pos >= currSize) return;
		//先访问右子节点
		iterative_show(rightchild(pos), h + 1);
		//访问父节点
		printnode(pos, h);
		//访问左子节点
		iterative_show(leftchild(pos), h + 1);
	}
	
	public void printHeap() {
		System.out.println("堆中元素旋转90度分层打印：");
		//从第一个元素开始打印，它处于第0层
		iterative_show(0, 0);
	}
}
