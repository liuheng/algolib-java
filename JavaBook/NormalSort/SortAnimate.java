/*
 * Created on 2010-6-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

import Element.*;
import Heap.MaxHeap;
import Stack.LinkStack;

/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SortAnimate extends Animate {

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * 选择排序算法，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void selection(ITEM[] a, int l, int r) throws InterruptedException{
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
	public void insertion(ITEM[] a, int l, int r) throws InterruptedException{
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
				cpyVal(a, j, a[j-1]);
				j--;
			}
			//将a[i]放到正确的位置，即位置j
			cpyVal(a, j, v);
			Thread.sleep(Parameter.sleeptime);
		}
	}
	
	/*
	 * 冒泡排序算法，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void bubble(ITEM[] a, int l, int r) throws InterruptedException{
		//从左向右扫描每一个元素
		for(int i = l; i < r; i++)
			//每次扫描从最右端开始逐个向前冒泡，找到第i个最小的元素
			for(int j = r; j > i; j--)
				compExch(a, j - 1, j);
	}
	
	/* shell排序算法，入参为一个ITEM类型的数组和待排序的始末
	 * 下标；
	 */
	public void shell(ITEM[] a, int l, int r) throws InterruptedException{
		//shell排序的步长
		int h;
		//计算初始的步长，增量之间的比为3
		for(h = l; h <= (r - l)/9; h = 3 * h + 1);
		//逐渐缩小步长，进行间隔地插入排序
		for(; h > 0; h /= 3)
			for(int i = l + h; i <= r; i++){
				int j = i; ITEM v = a[i];
				while(j >= l + h && v.compareTo(a[j - h]) == -1){
					cpyVal(a, j, a[j - h]);
					j -= h;/*间隔地跳跃比较*/
				}
				//赋值
				cpyVal(a, j, v);
				Thread.sleep(Parameter.sleeptime);
			}
	}
	
	/*快速排序算法调用的划分函数
	 * 具体划分过程：以数组的最后一个元素，即位于
	 * 位置r处的元素v作为参考，
	 * 最终将v放到正确的位置：其左边的元素小于v，
	 * 其右边的元素大于v
	 */
	public int partition(ITEM a[], int l, int r) throws InterruptedException{
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
	public void quicksort(ITEM[] a, int l, int r) throws InterruptedException{
		if(r <= l) return;
		//划分数组
		int i = partition(a, l, r);
		//递归调用快速排序
		quicksort(a, l, i - 1);
		quicksort(a, i + 1, r);
	}
	
	//快速排序算法的非递归实现。
	public void quicksort2(ITEM[] a, int l, int r) throws InterruptedException{
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
	public void quicksort3(ITEM[] a, int l, int r) throws InterruptedException{
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
	public void hybridsort(ITEM[] a, int l, int r) throws InterruptedException{
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
					else cpyVal(c, k, b[j++]);
					continue;
				}
				// 如果b中原元素全被取走，将a中元素直接添加到c中
				if(j > br){
					if(flag % 2 == 1 ) c[k] = a[i++];
					else cpyVal(c, k, a[i++]);
					continue;
				}
				// 直接比较，a[i]和b[j]并将较小的元素存放入c[k]中
				//c[k] = (a[i].compareTo(b[j]) == -1)?a[i++]:b[j++];
				try{
					if(a[i].compareTo(b[j]) == -1){
						if(flag % 2 == 1) c[k] = a[i++];
						else cpyVal(c, k, a[i++]);
					}
					else {
						if(flag % 2 == 1) c[k] = b[j++];
						else cpyVal(c, k, b[j++]);
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}catch(Exception e){e.printStackTrace();}
	}
	
	// 抽象原地归并
	public void merge(ITEM[] a, int l, int m, int r) throws InterruptedException{
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
				cpyVal(a, k, aux[j--]);
			else
				cpyVal(a, k, aux[i++]);
		}
	}
	
	//自顶向上的归并排序
	public void mergesort(ITEM a[], int l, int r) throws InterruptedException{
		if(r <= l) return;
		//计算中间位置
		int m = (l + r) / 2;
		Thread.sleep(40);
		//递归调用，排序l~m
		mergesort(a, l, m);
		Thread.sleep(40);
		//递归调用，排序m + 1 ~ r
		mergesort(a, m + 1, r);
		Thread.sleep(100);
		// 归并排序
		merge(a, l, m, r);
	}
	
	// 没有拷贝的归并排序
	public void mergesortABr(ITEM a[], ITEM b[], int l, int r, int flag) throws InterruptedException{
		if(r <= l) return;
		int m = (l + r) / 2;
		Thread.sleep(30);
		mergesortABr(b, a, l, m, (flag + 1) % 2);
		Thread.sleep(30);
		mergesortABr(b, a, m + 1, r, (flag + 1) % 2);
		Thread.sleep(30);
		merge2ways(a, l, b, l, m, b, m + 1, r, flag);
	}
	
	/**
	 * 自底向上的归并排序
	 * @param a	ITEM数组
	 * @param l	数组左端位置
	 * @param r 数组右端位置
	 * @throws InterruptedException
	 */
	public void mergesort_(ITEM a[], int l, int r) throws InterruptedException{
		// 辅助数组
		ITEM aux[] = new ITEM[a.length];
		if( r <= l) return;
		for(int m = 1; m <= r - l; m = m + m ){
			for(int i = l; i <= r - m; i += (m + m)){
				Thread.sleep(30);
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
			showCurve(a);
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 折半快速查找
	 * @param a	待排序的数组
	 * @param l	待排序的左端下标
	 * @param r 待排序的右端下标
	 * @param d	比较第d位
	 */
	public void quicksortB(ITEM[] a, int l, int r, int d){
		int i = l, j = r;
		if(r <= l || d > BitItem.bitsword) return;
		while(j != i){
			while(((BitItem)(a[i].elem)).bit(d) == 0 && i < j) i++;
			while(((BitItem)(a[j].elem)).bit(d) == 1 && i < j) j--;
			ITEM T = a[i];
			a[i] = a[j];
			a[j] = T;
			// 为显示a中元素的数值，创建数组b
			ITEM[] b = new ITEM[a.length];
			for(int k = 0; k < a.length; k++){
				double _d = ((double)((BitItem)a[k].elem).a) / 32737;
				b[k]= new ITEM<Double>(_d);
			}
			showCurve(b);
			
		}
		if(((BitItem)(a[r].elem)).bit(d) == 0) j++;
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		quicksortB(a, l, j - 1, d + 1);
		quicksortB(a, j, r, d + 1);
	}


	/**
	 * Most Significant Digit 基数排序法
	 * @param a	待排序的数组，数组元素类型为WordItem
	 * @param l	数组左端下标
	 * @param r	数组右端下标
	 * @param d	比较第d个字节；
	 */
	public void radixMSD(ITEM a[], int l, int r, int d){
		// 如果r大于l或者r<0则退出函数
		if(r - l < 0 || r < 0) return;
		// 创建数组数组cnt，用于关键字索引基数排序
		int i, j, cnt[] = new int[WordItem.R + 1];
		// 辅助数组
		ITEM aux[] = new ITEM[a.length];
		// 函数退出
		if(d > WordItem.bytesword) return;
		for(j = 0; j <= WordItem.R; j++) cnt[j] = 0;
		// 关键字统计
		for(i = l; i <= r; i++)
			cnt[((WordItem)(a[i].elem)).digit(d) + 1]++;
		for(j = 1; j <= WordItem.R; j++)
			cnt[j] += cnt[j - 1];
		// 将a中元素按照第d位字节进行排序，排序结果放置aux中
		for(i = l; i <= r; i++){
			int idx = ((WordItem)(a[i].elem)).digit(d);
			aux[cnt[idx]++] = a[i];
		}
		// 将aux中元素复制到a中
		for(i = l; i <= r; i++) a[i] = aux[i - l];
		
		// 为显示a中元素的数值，创建数组b
		ITEM[] b = new ITEM[a.length];
		for(int k = 0; k < a.length; k++){
			double _a = (double)((WordItem)a[k].elem).a;
			double _m = (double)9218858404817501475d;
			double _d = _a / _m;
			b[k]= new ITEM<Double>(_d);
		}
		showCurve(b);
		
		// 迭代地按照第d+1位字节进行排序
		radixMSD(a, l, l + cnt[0] - 1, d + 1);
		for(j = 0; j <= WordItem.R - 1; j++)
			radixMSD(a, l + cnt[j], l + cnt[j + 1] - 1, d + 1);
	}
	
	//实现Animate中的接口函数Sort
	void sort(ITEM[] a, int l, int r) {			
		//try {
			//selection(a, l, r);
			//insertion(a, l, r);
			//bubble(a, l, r);
			//shell(a, l, r);
			//quicksort(a, l, r);
		//	quicksort2(a, l, r);
			//hybridsort(a, l, r);
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}
		
		//测试merge函数
		/*
		ITEM a1[] = new ITEM[5];//{1,3,5,7,9};
		ITEM a2[] = new ITEM[8];//{2,4,6,8,10,12,13,15};
		for(int i = 0; i < 5; i++) a1[i] =new ITEM<Integer>(2 * i); 
		for(int i = 0; i < 8; i++) a2[i] =new ITEM<Integer>(2 * i + 1); 
		ITEM c[] = new ITEM[a1.length + a2.length];
		for(int i = 0; i < c.length; i++) c[i] = new ITEM<Integer>(0);
		merge2ways(c, 0, a1, 0,4,a2, 0,7);
		for(int i = 0; i < c.length; i++)
			System.out.println(c[i]);
			*/
		/*
		try {
			mergesort(a, l, r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		/*
		ITEM aux[] = new ITEM[a.length];
		for(int i = l; i <= r; i++){
			aux[i] = a[i];
		}
		try {
			mergesortABr(a, aux, l, r, 0);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		*/
		
		
		/*
		try {
			mergesort_(a, l, r);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(int i = 0; i < a.length; i++)
			System.out.println(a[i]);
			
			*/
		
		/*
		heap_sort(a);
		*/
		//for(int k = 0; k < a.length; k++)
			//System.out.print(Integer.toBinaryString(((BitItem)(a[k].elem)).a) + "  ");
			//System.out.print(a[k] + " ");
		//System.out.println();
		
		/*
		quicksortB(a, l, r, 0);
		*/
		//for(int i = 0; i < a.length; i++)
			//System.out.println(a[i]);
		
		
		
		radixMSD(a, l, r, 0);
		try{
			for(int i = 0; i < a.length; i++)
				System.out.println((long)((WordItem)a[i].elem).a);
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}

	
}
