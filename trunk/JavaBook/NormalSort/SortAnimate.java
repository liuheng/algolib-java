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
	 * ѡ�������㷨�����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void selection(ITEM[] a, int l, int r) throws InterruptedException{
		for(int i = l; i < r; i++){
			//��¼ÿ��ɨ��õ�����СԪ�����λ��
			int min = i;
			//ÿ��ɨ��Ѱ����СԪ�����λ��
			for(int j = i + 1; j <= r; j++){
				if(a[j].compareTo(a[min]) == -1) min = j;
			}
			//ÿ��ɨ��󽫵�i��Ԫ�����ҵ�����С��Ԫ������л�λ
			exch(a, i, min);
		}
	}
	
	/*
	 * ���������㷨�����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void insertion(ITEM[] a, int l, int r) throws InterruptedException{
		//ѭ����
		int i;
		//�Ƚ�����ĵ�һ��λ���ϰڷ���С��Ԫ����
		for(i = r; i > l; i--) 
			compExch(a, i - 1, i);
		//�ӵ�2��Ԫ����ӵ�0����ʼ����ʼ����
		for(i = l + 2; i <= r; i++){
			//��λ��i-1��ǰ����������һλ��ֱ��a[i]�ڷŵ���ȷ��λ��
			int j = i; ITEM v = a[i];
			while(v.compareTo(a[j - 1]) == -1){
				//��������һλ
				cpyVal(a, j, a[j-1]);
				j--;
			}
			//��a[i]�ŵ���ȷ��λ�ã���λ��j
			cpyVal(a, j, v);
			Thread.sleep(Parameter.sleeptime);
		}
	}
	
	/*
	 * ð�������㷨�����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void bubble(ITEM[] a, int l, int r) throws InterruptedException{
		//��������ɨ��ÿһ��Ԫ��
		for(int i = l; i < r; i++)
			//ÿ��ɨ������Ҷ˿�ʼ�����ǰð�ݣ��ҵ���i����С��Ԫ��
			for(int j = r; j > i; j--)
				compExch(a, j - 1, j);
	}
	
	/* shell�����㷨�����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void shell(ITEM[] a, int l, int r) throws InterruptedException{
		//shell����Ĳ���
		int h;
		//�����ʼ�Ĳ���������֮��ı�Ϊ3
		for(h = l; h <= (r - l)/9; h = 3 * h + 1);
		//����С���������м���ز�������
		for(; h > 0; h /= 3)
			for(int i = l + h; i <= r; i++){
				int j = i; ITEM v = a[i];
				while(j >= l + h && v.compareTo(a[j - h]) == -1){
					cpyVal(a, j, a[j - h]);
					j -= h;/*�������Ծ�Ƚ�*/
				}
				//��ֵ
				cpyVal(a, j, v);
				Thread.sleep(Parameter.sleeptime);
			}
	}
	
	/*���������㷨���õĻ��ֺ���
	 * ���廮�ֹ��̣�����������һ��Ԫ�أ���λ��
	 * λ��r����Ԫ��v��Ϊ�ο���
	 * ���ս�v�ŵ���ȷ��λ�ã�����ߵ�Ԫ��С��v��
	 * ���ұߵ�Ԫ�ش���v
	 */
	public int partition(ITEM a[], int l, int r) throws InterruptedException{
		int i = l - 1, j = r; ITEM v = a[r];
		for(;;){
			//�ȴ�����������Ƚ�
			while(a[++i].compareTo(v) == -1);
			//�ٴ�����������Ƚ�
			while(v.compareTo(a[--j]) == -1)
				//������������Ƚϵ�ֹͣ����
				if(j == l) 
					break;
			//������ѭ��ֹͣ����
			if(i >= j) break;
			//����i��Ԫ�غ͵�j��Ԫ�ؽ���
			exch(a, i, j);
		}
		//ѭ��ֹͣ�󣬽�r����Ԫ�ط�����ȷ��λ��i
		exch(a, i, r);
		return i;
	}
	
	/* ���������㷨�ĵݹ�ʵ�֣����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void quicksort(ITEM[] a, int l, int r) throws InterruptedException{
		if(r <= l) return;
		//��������
		int i = partition(a, l, r);
		//�ݹ���ÿ�������
		quicksort(a, l, i - 1);
		quicksort(a, i + 1, r);
	}
	
	//���������㷨�ķǵݹ�ʵ�֡�
	public void quicksort2(ITEM[] a, int l, int r) throws InterruptedException{
		//����ջ���ݽṹ
		LinkStack S = new LinkStack();
		//������������±�ѹջ
		S.push(new ElemItem<Integer>(l));
		//���������Ҷ��±�ѹջ
		S.push(new ElemItem<Integer>(r));
		//��ӡջ��Ԫ�ظ���
		System.out.print(S.getSize() + " ");
		//��������
		while(S.getSize() > 0){
			//����ջ��Ԫ�أ��Ҷ��±꣩
			r = ((Integer)(S.pop().elem)).intValue();
			//����ջ��Ԫ�أ�����±꣩
			l = ((Integer)(S.pop().elem)).intValue();
			//����������Ҷ��±�С������±꣬�����˴�ѭ��
			if(r <= l) continue;
			//������±굽�Ҷ��±�֮���Ԫ�ؽ��л���
			int i = partition(a, l, r);
			//���i�ұߵ�Ԫ�ظ������٣��Ƚ����������λ��ѹջ
			if(i - l > r - i){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i - 1));
			}
			//��i�ұ�����λ��ѹջ
			S.push(new ElemItem<Integer>(i+1));
			S.push(new ElemItem<Integer>(r));
			//���i�ұߵ�Ԫ�ظ������࣬���������λ��ѹջ
			if(r - i >= i - l){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i-1));
			}
			//��ӡ��ջ��Ԫ�ظ���
			System.out.print(S.getSize() + " ");
		}
		System.out.println();
		//for(int i = l; i <= r; i++) System.out.print(a[i] + " ");
	}
	
	/*��������ȡ�з����ֵĿ����㷨
	 * С�ļ�����ֵΪM,С��M��С�ļ�������������ԣ�
	 * ��Ҫ�Ľ�˼�룬�����˹�С���ļ����������Լ�Сջ����ȣ�
	 * ��������ȡ�з��Ļ��ֹ�����ʹ���ֵ�λ�ø��ӽ���������룬
	 * ����ʹ�����ߵ�Ԫ�ظ����Ƚ�ƽ�⡣
	 */
	private final static int M = 10;
	public void quicksort3(ITEM[] a, int l, int r) throws InterruptedException{
		//����ջ
		LinkStack S = new LinkStack();
		//�Ⱥ��������Ҷ˵�λ��ѹջ
		S.push(new ElemItem<Integer>(l));
		S.push(new ElemItem<Integer>(r));
		//��ӡջ��Ԫ�صĸ���
		System.out.print(S.getSize() + " ");
		//ѭ������
		while(S.getSize() > 0){
			//����ջ��Ԫ�أ������Ҷ��±꣩
			r = ((Integer)(S.pop().elem)).intValue();
			//����ջ��Ԫ�أ���������±꣩
			l = ((Integer)(S.pop().elem)).intValue();
			//��ӡջ��Ԫ�ظ���
			System.out.print(S.getSize() + " ");
			//����Ҷ��±�С������±꣬�����˴�ѭ��
			if(r - l <= 0) continue;
			/*����4�д��������������ˡ����롢���Ҷ�����
			 * Ԫ�ؽ���ȡ�����Ĺ��̣�����λ���ϵ�Ԫ�����
			 * �����λ��r-1
			 */
			exch(a, (l + r) / 2, r - 1);
			compExch(a, l, r - 1);
			compExch(a, l, r);
			compExch(a, r - 1, r);
			//�����ʱС�ļ��Ĵ�СС��M�����Զ��������
			if(r - l <= M) continue;
			/*��l+1��r-1֮���Ԫ�ؽ��л��֣�
			 * ���ڽ�����ȡ����������r-1λ���ϵ�Ԫ��һ���Ǵ���lλ����
			 * ��Ԫ�أ�ͬʱС��rλ���ϵ�Ԫ�أ����Ի��ֵ���l+1~r-1֮��
			 * ��Ԫ�ء�
			 */
			int i = partition(a, l + 1, r - 1);
			//���i�ұߵ�Ԫ�ظ������٣��Ƚ����������λ��ѹջ
			if(i - l > r - i){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i-1));
			}
			//��i�ұ�����λ��ѹջ
			S.push(new ElemItem<Integer>(i+1));
			S.push(new ElemItem<Integer>(r));
			//���i�ұߵ�Ԫ�ظ������࣬���������λ��ѹջ
			if(r - i >= i - l){
				S.push(new ElemItem<Integer>(l));
				S.push(new ElemItem<Integer>(i-1));
			}
			System.out.print(S.getSize() + " ");
		}
	}
	
	/*�������ȡ�з��Ŀ����㷨�Ͳ������򷨵Ļ�������㷨
	 * ���M > 1������ȡ�л��ַ��Ŀ��������õ������鲻����ȫ����ģ�
	 * ֻ�ǡ�����������ģ������Ҫ�����ڲ�������������յ�����
	 * */
	public void hybridsort(ITEM[] a, int l, int r) throws InterruptedException{
		//����ȡ�з����ֵĿ�������
		quicksort3(a, l, r);
		//��������
		insertion(a, l, r);
	}
	
	
	////////////////////////////////////////////////////////////////////////////////////
	//�鲢����
	public void merge2ways(ITEM[] c, int cl, 
					ITEM[] a, int al, int ar,
					ITEM[] b, int bl, int br,
					int flag){
		int i = al, j = bl, cr = cl + ar - al + br - bl + 1;
		try{
			for(int k = cl; k <= cr; k++){
				// ���a��Ԫ��ȫ��ȡ�ߣ���b��Ԫ��ֱ����ӵ�c��
				if(i > ar){
					if(flag % 2 == 1) c[k] = b[j++];
					else cpyVal(c, k, b[j++]);
					continue;
				}
				// ���b��ԭԪ��ȫ��ȡ�ߣ���a��Ԫ��ֱ����ӵ�c��
				if(j > br){
					if(flag % 2 == 1 ) c[k] = a[i++];
					else cpyVal(c, k, a[i++]);
					continue;
				}
				// ֱ�ӱȽϣ�a[i]��b[j]������С��Ԫ�ش����c[k]��
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
	
	// ����ԭ�ع鲢
	public void merge(ITEM[] a, int l, int m, int r) throws InterruptedException{
		int i, j;
		ITEM[] aux = new ITEM[a.length];
		// ֱ�Ӹ���Ԫ��l~m������i = l
		for(i = m + 1; i > l; i--){
			aux[i - 1] = a[i - 1];
		}
		// ������Ԫ��r~m+1������j=r		
		for(j = m; j < r; j++){
			aux[r + m - j] = a[j + 1];
		}
		//����Ƚϣ�aux[i]��aux[j]������С�ķ���a��
		for(int k = l; k <= r; k++){
			if(aux[j].compareTo(aux[i]) == -1)
				cpyVal(a, k, aux[j--]);
			else
				cpyVal(a, k, aux[i++]);
		}
	}
	
	//�Զ����ϵĹ鲢����
	public void mergesort(ITEM a[], int l, int r) throws InterruptedException{
		if(r <= l) return;
		//�����м�λ��
		int m = (l + r) / 2;
		Thread.sleep(40);
		//�ݹ���ã�����l~m
		mergesort(a, l, m);
		Thread.sleep(40);
		//�ݹ���ã�����m + 1 ~ r
		mergesort(a, m + 1, r);
		Thread.sleep(100);
		// �鲢����
		merge(a, l, m, r);
	}
	
	// û�п����Ĺ鲢����
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
	 * �Ե����ϵĹ鲢����
	 * @param a	ITEM����
	 * @param l	�������λ��
	 * @param r �����Ҷ�λ��
	 * @throws InterruptedException
	 */
	public void mergesort_(ITEM a[], int l, int r) throws InterruptedException{
		// ��������
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
	 * ������
	 * @param a �����������
	 */
	public void heap_sort(ITEM[] a) {
		MaxHeap H = new MaxHeap(a);
		int N = H.heapSize();
		// ������
		for(int k = N / 2; k >= 0; k--)
			H.shiftdown(k);
		// �������
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
	 * �۰���ٲ���
	 * @param a	�����������
	 * @param l	�����������±�
	 * @param r ��������Ҷ��±�
	 * @param d	�Ƚϵ�dλ
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
			// Ϊ��ʾa��Ԫ�ص���ֵ����������b
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
	 * Most Significant Digit ��������
	 * @param a	����������飬����Ԫ������ΪWordItem
	 * @param l	��������±�
	 * @param r	�����Ҷ��±�
	 * @param d	�Ƚϵ�d���ֽڣ�
	 */
	public void radixMSD(ITEM a[], int l, int r, int d){
		// ���r����l����r<0���˳�����
		if(r - l < 0 || r < 0) return;
		// ������������cnt�����ڹؼ���������������
		int i, j, cnt[] = new int[WordItem.R + 1];
		// ��������
		ITEM aux[] = new ITEM[a.length];
		// �����˳�
		if(d > WordItem.bytesword) return;
		for(j = 0; j <= WordItem.R; j++) cnt[j] = 0;
		// �ؼ���ͳ��
		for(i = l; i <= r; i++)
			cnt[((WordItem)(a[i].elem)).digit(d) + 1]++;
		for(j = 1; j <= WordItem.R; j++)
			cnt[j] += cnt[j - 1];
		// ��a��Ԫ�ذ��յ�dλ�ֽڽ�����������������aux��
		for(i = l; i <= r; i++){
			int idx = ((WordItem)(a[i].elem)).digit(d);
			aux[cnt[idx]++] = a[i];
		}
		// ��aux��Ԫ�ظ��Ƶ�a��
		for(i = l; i <= r; i++) a[i] = aux[i - l];
		
		// Ϊ��ʾa��Ԫ�ص���ֵ����������b
		ITEM[] b = new ITEM[a.length];
		for(int k = 0; k < a.length; k++){
			double _a = (double)((WordItem)a[k].elem).a;
			double _m = (double)9218858404817501475d;
			double _d = _a / _m;
			b[k]= new ITEM<Double>(_d);
		}
		showCurve(b);
		
		// �����ذ��յ�d+1λ�ֽڽ�������
		radixMSD(a, l, l + cnt[0] - 1, d + 1);
		for(j = 0; j <= WordItem.R - 1; j++)
			radixMSD(a, l + cnt[j], l + cnt[j + 1] - 1, d + 1);
	}
	
	//ʵ��Animate�еĽӿں���Sort
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
		
		//����merge����
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
