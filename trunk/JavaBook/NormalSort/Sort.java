/*
 * Created on 2010-7-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

import Element.ElemItem;
import Element.utility;
import Heap.MaxHeap;
import Stack.LinkStack;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Sort {
	
	void exch(ITEM[] a, int i, int j){
		ITEM t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	/*如果第i个元素大于第j个元素，则交换着两个元素*/
	void compExch(ITEM[] a, int i, int j){
		if(a[i].compareTo(a[j]) == 1){
			exch(a, i, j);
		}
	}
	
	/*
	 * 选择排序算法，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void selection(ITEM[] a, int l, int r){
		for(int i = l; i < r; i++){
			//记录每次扫描得到的最小元素项的位置
			int min = i;
			//每次扫描寻找最小元素项的位置
			for(int j = i + 1; j <= r; j++){
				if(a[j].compareTo(a[min]) == -1) min = j;
			}
			//每次扫描后将第i个元素与找到的最小的元素项进行换位
			exch(a, i, min);
		}
	}
	
	/*
	 * 插入排序算法，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void insertion(ITEM[] a, int l, int r) {
		//循环数
		int i;
		//先将数组的第一个位置上摆放最小的元素项
		for(i = r; i > l; i--) 
			compExch(a, i - 1, i);
		//从第2个元素项（从第0个开始）开始迭代
		for(i = l + 2; i <= r; i++){
			//从位置i-1向前，逐个向后移一位，直至a[i]摆放到正确的位置
			int j = i; ITEM v = a[i];
			while(v.compareTo(a[j - 1]) == -1){
				//逐个向后移一位
				a[j] = a[j-1];
				j--;
			}
			//将a[i]放到正确的位置，即位置j
			a[j] = v;
		}
	}
	
	/*
	 * 冒泡排序算法，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void bubble(ITEM[] a, int l, int r) {
		//从左向右扫描每一个元素
		for(int i = l; i < r; i++)
			//每次扫描从最右端开始逐个向前冒泡，找到第i个最小的元素
			for(int j = r; j > i; j--)
				compExch(a, j - 1, j);
	}
	
	/* shell排序算法，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void shell(ITEM[] a, int l, int r) {
		//shell排序的步长
		int h;
		//计算初始的步长，增量之间的比为3
		for(h = l; h <= (r - l)/9; h = 3 * h + 1);
		//逐渐缩小步长，进行间隔地插入排序
		for(; h > 0; h /= 3)
			for(int i = l + h; i <= r; i++){
				int j = i; ITEM v = a[i];
				while(j >= l + h && v.compareTo(a[j - h]) == -1){
					a[j] = a[j - h];
					j -= h;/*间隔地跳跃比较*/
				}
				//赋值
				a[j] = v;
			}
	}
	
	/*快速排序算法调用的划分函数
	 * 具体划分过程：以数组的最后一个元素，即位于
	 * 位置r处的元素v作为参考，
	 * 最终将v放到正确的位置：其左边的元素小于v，
	 * 其右边的元素大于v
	 */
	public int partition(ITEM a[], int l, int r) {
		int i = l - 1, j = r; ITEM v = a[r];
		for(;;){
			//先从左向右逐个比较
			while(a[++i].compareTo(v) == -1);
			//再从右向左逐个比较
			while(v.compareTo(a[--j]) == -1)
				//从右向左逐个比较的停止条件
				if(j == l) 
					break;
			//函数的循环停止条件
			if(i >= j) break;
			//将第i个元素和第j个元素交换
			exch(a, i, j);
		}
		//循环停止后，将r处的元素放置正确的位置i
		exch(a, i, r);
		return i;
	}
	
	/* 快速排序算法的递归实现，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void quicksort(ITEM[] a, int l, int r) {
		if(r <= l) return;
		//划分数组
		int i = partition(a, l, r);
		//递归调用快速排序
		quicksort(a, l, i - 1);
		quicksort(a, i + 1, r);
	}
	
	//快速排序算法的非递归实现。
	public void quicksort2(ITEM[] a, int l, int r) {
		//创建栈数据结构
		LinkStack S = new LinkStack();
		//将数组最左端下标压栈
		S.push(new ElemItem<Integer>(l));
		//将数组最右端下标压栈
		S.push(new ElemItem<Integer>(r));
		//打印栈中元素个数
		System.out.print(S.getSize() + " ");
		//迭代过程
		while(S.getSize() > 0){
			//弹出栈顶元素（右端下标）
			r = ((Integer)(S.pop().elem)).intValue();
			//弹出栈顶元素（左端下标）
			l = ((Integer)(S.pop().elem)).intValue();
			//如果弹出的右端下标小于左端下标，跳过此次循环
			if(r <= l) continue;
			//将左端下标到右端下标之间的元素进行划分
			int i = partition(a, l, r);
			//如果i右边的元素个数更少，先将其左边两端位置压栈
			if(i - l > r - i){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i - 1));
			}
			//将i右边两端位置压栈
			S.push(new ElemItem<Integer>(i+1));
			S.push(new ElemItem<Integer>(r));
			//如果i右边的元素个数更多，后将左边两端位置压栈
			if(r - i >= i - l){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i-1));
			}
			//打印出栈中元素个数
			System.out.print(S.getSize() + " ");
		}
		System.out.println();
		//for(int i = l; i <= r; i++) System.out.print(a[i] + " ");
	}
	
	/*基于三者取中法划分的快速算法
	 * 小文件的阈值为M,小于M的小文件被快速排序忽略；
	 * 主要改进思想，忽略了过小的文件，这样可以减小栈的深度；
	 * 基于三者取中法的划分过程能使划分的位置更接近数组的中央，
	 * 这样使得两边的元素个数比较平衡。
	 */
	private final static int M = 10;
	public void quicksort3(ITEM[] a, int l, int r) {
		//创建栈
		LinkStack S = new LinkStack();
		//先后将数组左右端的位置压栈
		S.push(new ElemItem<Integer>(l));
		S.push(new ElemItem<Integer>(r));
		//打印栈中元素的个数
		System.out.print(S.getSize() + " ");
		//循环迭代
		while(S.getSize() > 0){
			//弹出栈顶元素（数组右端下标）
			r = ((Integer)(S.pop().elem)).intValue();
			//弹出栈顶元素（数组左端下标）
			l = ((Integer)(S.pop().elem)).intValue();
			//打印栈中元素个数
			System.out.print(S.getSize() + " ");
			//如果右端下标小于左端下标，跳出此次循环
			if(r - l <= 0) continue;
			/*下面4行代码的作用是最左端、中央、最右端三个
			 * 元素进行取中数的过程，中央位置上的元素最后
			 * 存放至位置r-1
			 */
			exch(a, (l + r) / 2, r - 1);
			compExch(a, l, r - 1);
			compExch(a, l, r);
			compExch(a, r - 1, r);
			//如果此时小文件的大小小于M，忽略对其的排序
			if(r - l <= M) continue;
			/*对l+1到r-1之间的元素进行划分，
			 * 由于进行了取中数操作，r-1位置上的元素一定是大于l位置上
			 * 的元素，同时小于r位置上的元素，所以划分的是l+1~r-1之间
			 * 的元素。
			 */
			int i = partition(a, l + 1, r - 1);
			//如果i右边的元素个数更少，先将其左边两端位置压栈
			if(i - l > r - i){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i-1));
			}
			//将i右边两端位置压栈
			S.push(new ElemItem<Integer>(i+1));
			S.push(new ElemItem<Integer>(r));
			//如果i右边的元素个数更多，后将左边两端位置压栈
			if(r - i >= i - l){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i-1));
			}
			System.out.print(S.getSize() + " ");
		}
	}
	
	/*结合三者取中法的快速算法和插入排序法的混合排序算法
	 * 如果M > 1，基于取中划分法的快速排序后得到的数组不是完全有序的，
	 * 只是“基本”有序的；最后还需要借助于插入排序完成最终的排序。
	 * */
	public void hybridsort(ITEM[] a, int l, int r) {
		//基于取中法划分的快速排序
		quicksort3(a, l, r);
		//插入排序
		insertion(a, l, r);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////
	//归并排序
	public void merge2ways(ITEM[] c, int cl, 
					ITEM[] a, int al, int ar,
					ITEM[] b, int bl, int br,
					int flag){
		int i = al, j = bl, cr = cl + ar - al + br - bl + 1;
		try{
			for(int k = cl; k <= cr; k++){
				// 如果a中元素全被取走，将b中元素直接添加到c中
				if(i > ar){
					if(flag % 2 == 1) c[k] = b[j++];
					else c[k] = b[j++];
					continue;
				}
				// 如果b中原元素全被取走，将a中元素直接添加到c中
				if(j > br){
					if(flag % 2 == 1 ) c[k] = a[i++];
					else c[k] = a[i++];
					continue;
				}
				// 直接比较，a[i]和b[j]并将较小的元素存放入c[k]中
				//c[k] = (a[i].compareTo(b[j]) == -1)?a[i++]:b[j++];
				try{
					if(a[i].compareTo(b[j]) == -1){
						if(flag % 2 == 1) c[k] = a[i++];
						else c[k] = a[i++];
					}
					else {
						if(flag % 2 == 1) c[k] = b[j++];
						else c[k] = b[i++];
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	// 抽象原地归并
	public void merge(ITEM[] a, int l, int m, int r) {
		int i, j;
		ITEM[] aux = new ITEM[a.length];
		// 直接复制元素l~m，最终i = l
		for(i = m + 1; i > l; i--){
			aux[i - 1] = a[i - 1];
		}
		// 倒序复制元素r~m+1，最终j=r		
		for(j = m; j < r; j++){
			aux[r + m - j] = a[j + 1];
		}
		//逐个比较，aux[i]和aux[j]，将较小的放入a中
		for(int k = l; k <= r; k++){
			if(aux[j].compareTo(aux[i]) == -1)
				a[k] = aux[j--];
			else
				a[k] = aux[i++];
		}
	}
	
	//自顶向上的归并排序
	public void mergesort(ITEM a[], int l, int r) {
		if(r <= l) return;
		//计算中间位置
		int m = (l + r) / 2;
		//递归调用，排序l~m
		mergesort(a, l, m);
		//递归调用，排序m + 1 ~ r
		mergesort(a, m + 1, r);
		// 归并排序
		merge(a, l, m, r);
	}
	
	// 没有拷贝的归并排序
	public void mergesortABr(ITEM a[], ITEM b[], int l, int r, int flag) {
		if(r <= l) return;
		int m = (l + r) / 2;
		mergesortABr(b, a, l, m, (flag + 1) % 2);
		mergesortABr(b, a, m + 1, r, (flag + 1) % 2);
		merge2ways(a, l, b, l, m, b, m + 1, r, flag);
	}
	
	// 自底向上的归并排序
	public void mergesort_(ITEM a[], int l, int r) {
		ITEM aux[] = new ITEM[a.length];
		if( r <= l) return;
		for(int m = 1; m <= r - l; m = m + m ){
			for(int i = l; i <= r - m; i += (m + m)){
				merge(a, i, i + m - 1, utility.min(i + m + m - 1, r));
			}
		}
	}
	
	/**
	 * 堆排序
	 * @param a 待排序的数组
	 */
	public void heap_sort(ITEM[] a) {
		MaxHeap H = new MaxHeap(a);
		int N = H.heapSize();
		// 创建堆
		for(int k = N / 2; k >= 0; k--)
			H.shiftdown(k);
		// 排序过程
		while(H.heapSize() > 0){
			H.removeMax();
		}
	}
}
