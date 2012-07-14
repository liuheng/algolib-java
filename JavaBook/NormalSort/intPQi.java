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
	// type��ʾ���ͣ�-1��ʾ���ѣ�1��ʾ��С�ѡ�
	private ElemItem[] a;
	private int[] pq, qp;
	private int N;
	private int type;
	
	/**
	 * ���캯��
	 * @param items	Ԫ��������
	 */
	public intPQi(ElemItem[] items, int type){
		a = items; N = 0;
		pq = new int[a.length + 1];
		qp = new int[a.length + 1];
		this.type = type;
	}
	
	/**
	 * �Ƚ�a[i]��a[j]
	 * @param i	��i��Ԫ��
	 * @param j ��j��Ԫ��
	 * @return ���a[i]С��a[j]����true�����򷵻�false
	 */
	private boolean less(int i, int j){
		return a[pq[i]].compareTo(a[pq[j]]) == type;
	}
	
	/**
	 * ����a[i]��a[j]
	 * @param i	��i��Ԫ��
	 * @param j ��j��Ԫ��
	 */
	private void exch(int i, int j){
		int t = pq[i];
		pq[i] = pq[j];
		pq[j] = t;
		qp[pq[i]] = i;
		qp[pq[j]] = j;
	}
	
	/**
	 * ��a[k]������
	 * @param k	��ʾ���ƶ�����a[k]
	 * ������Ԫ��a[k]�ƶ�����ȷ��λ�ã�ʹ��a[k]
	 * �����ӽڵ�Ԫ�ش�
	 */
	private void swim(int k){
		while(k > 1 && less(k / 2 , k)){
			exch(k, k / 2);
			k = k / 2;
		}
	}
	
	/**
	 * �Զ����¶ѻ�����a[k]������
	 * @param k	��ʾ���ƶ�����a[k]a
	 * @param N	��ʾԪ���ܸ���ΪN
	 * ������Ԫ��a[k]�ƶ�����ȷ��λ��
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
	
	//�жϵ�ǰ�����Ƿ�Ϊ��
	public boolean empty(){
		return N == 0;
	}
	
	// ����һ���µ�Ԫ�أ������λ��Ϊv
	public void insert(int v){
		pq[++N] = v;
		qp[v] = N;
		swim(N);
	}
	
	// ��ȡ��ɾ������ǰ����Ԫ��
	public int getmax(){
		exch(1, N);
		sink(1, N - 1);
		return pq[N--];
	}
	
	// �ı��k��Ԫ��
	public void change(int k){
		swim(qp[k]);
		sink(qp[k], N);
	}
	
	// ������k��Ԫ���ڶ��е�λ��
	public void lower(int k){
		swim(qp[k]);
	}

}
