/*
 * Created on 2010-7-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class intPQi {
	// type表示类型，-1表示最大堆；1表示最小堆。
	private ElemItem[] a;
	private int[] pq, qp;
	private int N;
	private int type;
	
	/**
	 * 构造函数
	 * @param items	元素项数组
	 */
	public intPQi(ElemItem[] items, int type){
		a = items; N = 0;
		pq = new int[a.length + 1];
		qp = new int[a.length + 1];
		this.type = type;
	}
	
	/**
	 * 比较a[i]和a[j]
	 * @param i	第i个元素
	 * @param j 第j个元素
	 * @return 如果a[i]小于a[j]返回true，否则返回false
	 */
	private boolean less(int i, int j){
		return a[pq[i]].compareTo(a[pq[j]]) == type;
	}
	
	/**
	 * 交换a[i]和a[j]
	 * @param i	第i个元素
	 * @param j 第j个元素
	 */
	private void exch(int i, int j){
		int t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	
	/**
	 * 将a[k]向上移
	 * @param k	表示待移动的是a[k]
	 * 函数将元素a[k]移动到正确的位置，使得a[k]
	 * 比其子节点元素大。
	 */
	private void swim(int k){
		while(k > 1 && less(k / 2 , k)){
			exch(k, k / 2);
			k = k / 2;
		}
	}
	
	/**
	 * 自顶向下堆化，将a[k]逐渐下移
	 * @param k	表示代移动的是a[k]a
	 * @param N	表示元素总个数为N
	 * 函数将元素a[k]移动到正确的位置
	 */
	private void sink(int k, int N){
		while(2 * k <= N){
			int j = 2 * k;
			if(j < N && less(j, j + 1)) j++;
			if(!less(k, j)) break;
			exch(k, j); 
			k = j;
		}
	}
	
	//判断当前队列是否为空
	public boolean empty(){
		return N == 0;
	}
	
	// 插入一个新的元素，插入的位置为v
	public void insert(int v){
		pq[++N] = v;
		qp[v] = N;
		swim(N);
	}
	
	// 获取（删除）当前最大的元素
	public int getmax(){
		exch(1, N);
		sink(1, N - 1);
		return pq[N--];
	}
	
	// 改变第k个元素
	public void change(int k){
		swim(qp[k]);
		sink(qp[k], N);
	}
	
	// 调整第k个元素在堆中的位置
	public void lower(int k){
		swim(qp[k]);
	}

}
