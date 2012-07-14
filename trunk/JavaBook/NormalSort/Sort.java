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
	
	/*�����i��Ԫ�ش��ڵ�j��Ԫ�أ��򽻻�������Ԫ��*/
	void compExch(ITEM[] a, int i, int j){
		if(a[i].compareTo(a[j]) == 1){
			exch(a, i, j);
		}
	}
	
	/*
	 * ѡ�������㷨�����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void selection(ITEM[] a, int l, int r){
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
	public void insertion(ITEM[] a, int l, int r) {
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
				a[j] = a[j-1];
				j--;
			}
			//��a[i]�ŵ���ȷ��λ�ã���λ��j
			a[j] = v;
		}
	}
	
	/*
	 * ð�������㷨�����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void bubble(ITEM[] a, int l, int r) {
		//��������ɨ��ÿһ��Ԫ��
		for(int i = l; i < r; i++)
			//ÿ��ɨ������Ҷ˿�ʼ�����ǰð�ݣ��ҵ���i����С��Ԫ��
			for(int j = r; j > i; j--)
				compExch(a, j - 1, j);
	}
	
	/* shell�����㷨�����Ϊһ��ITEM���͵�����ʹ������ʼĩ
	 * �±ꣻ
	 */
	public void shell(ITEM[] a, int l, int r) {
		//shell����Ĳ���
		int h;
		//�����ʼ�Ĳ���������֮��ı�Ϊ3
		for(h = l; h <= (r - l)/9; h = 3 * h + 1);
		//����С���������м���ز�������
		for(; h > 0; h /= 3)
			for(int i = l + h; i <= r; i++){
				int j = i; ITEM v = a[i];
				while(j >= l + h && v.compareTo(a[j - h]) == -1){
					a[j] = a[j - h];
					j -= h;/*�������Ծ�Ƚ�*/
				}
				//��ֵ
				a[j] = v;
			}
	}
	
	/*���������㷨���õĻ��ֺ���
	 * ���廮�ֹ��̣�����������һ��Ԫ�أ���λ��
	 * λ��r����Ԫ��v��Ϊ�ο���
	 * ���ս�v�ŵ���ȷ��λ�ã�����ߵ�Ԫ��С��v��
	 * ���ұߵ�Ԫ�ش���v
	 */
	public int partition(ITEM a[], int l, int r) {
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
	public void quicksort(ITEM[] a, int l, int r) {
		if(r <= l) return;
		//��������
		int i = partition(a, l, r);
		//�ݹ���ÿ�������
		quicksort(a, l, i - 1);
		quicksort(a, i + 1, r);
	}
	
	//���������㷨�ķǵݹ�ʵ�֡�
	public void quicksort2(ITEM[] a, int l, int r) {
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
	public void quicksort3(ITEM[] a, int l, int r) {
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
	public void hybridsort(ITEM[] a, int l, int r) {
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
					else c[k] = b[j++];
					continue;
				}
				// ���b��ԭԪ��ȫ��ȡ�ߣ���a��Ԫ��ֱ����ӵ�c��
				if(j > br){
					if(flag % 2 == 1 ) c[k] = a[i++];
					else c[k] = a[i++];
					continue;
				}
				// ֱ�ӱȽϣ�a[i]��b[j]������С��Ԫ�ش����c[k]��
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
	
	// ����ԭ�ع鲢
	public void merge(ITEM[] a, int l, int m, int r) {
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
				a[k] = aux[j--];
			else
				a[k] = aux[i++];
		}
	}
	
	//�Զ����ϵĹ鲢����
	public void mergesort(ITEM a[], int l, int r) {
		if(r <= l) return;
		//�����м�λ��
		int m = (l + r) / 2;
		//�ݹ���ã�����l~m
		mergesort(a, l, m);
		//�ݹ���ã�����m + 1 ~ r
		mergesort(a, m + 1, r);
		// �鲢����
		merge(a, l, m, r);
	}
	
	// û�п����Ĺ鲢����
	public void mergesortABr(ITEM a[], ITEM b[], int l, int r, int flag) {
		if(r <= l) return;
		int m = (l + r) / 2;
		mergesortABr(b, a, l, m, (flag + 1) % 2);
		mergesortABr(b, a, m + 1, r, (flag + 1) % 2);
		merge2ways(a, l, b, l, m, b, m + 1, r, flag);
	}
	
	// �Ե����ϵĹ鲢����
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
		}
	}
}
