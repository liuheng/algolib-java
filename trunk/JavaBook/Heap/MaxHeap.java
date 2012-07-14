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
 * �����࣬MaxHeap.java
 */
public class MaxHeap implements Heap{
	protected  ElemItem maxheapdata[];	//������ʽ�������Ԫ���� 
	protected  int maxSize;				//�ѵ�����С����Ӧ�������󳤶�
	protected  int currSize;			//�ѵ�ǰ��Ч��С
	
	// ���캯��
	public MaxHeap(int _MaxSize){		//���캯��
		maxSize = _MaxSize;				//���Ԫ�ظ���
		currSize = 0;					//��ǰ��ЧԪ�ظ���
		maxheapdata = new ElemItem[maxSize];//�½����飬����Ϊ���Ԫ�ظ���
	}
	
	// ���캯��2
	public MaxHeap(ElemItem _a[]){
		maxSize = _a.length;
		currSize = maxSize;
		maxheapdata = _a;
	}
	
	// �Ƚ϶�Ԫ�����С��˽�к�����
	protected int compare(int i, int j){
		return (maxheapdata[i]).compareTo(maxheapdata[j]);
	}
	
	public boolean insert(ElemItem elem) {
		if(currSize >= maxSize){
			System.out.println("��������");
			return false;
		}
		else{
			int n = currSize++;		//nΪ�ѵ����һ����λ��
			maxheapdata[n] = elem;	//��Ԫ�ز���
			shiftup(n);				//�Ե����϶ѻ�
		}
		return true;
	}

	public ElemItem remove(int position) {
		if(position < 0 || position >= currSize){
			System.out.println("��ǰλ����Ч��");
			return null;
		}
		else{
			//�����һ��Ԫ����position�ϵ�Ԫ�ؽ���
			exchange(position, currSize - 1);
			//��ǰ������1��currSizeλ����Ԫ�ؼ������һ����Ԫ����
			currSize--;	
			// ����ȸ��ڵ��ֵ���������϶ѻ�
			if(1 == compare(position, parent(position))) 
				shiftup(position);
			else shiftdown(position);//���¶ѻ�
		}
		return maxheapdata[currSize];//����ɾ����Ԫ��
	}

	public ElemItem removeMax() {
		if(currSize <= 0){
			System.out.println("��ǰ��Ϊ�գ�");
			return null;
		}
		else{
			//�����һ��Ԫ����0λ���ϵ�Ԫ�ؽ���
			exchange(0, currSize - 1);
			//��ǰ������1����currSizeλ����Ԫ�ؼ������һ����Ԫ����
			currSize--;		
			if(currSize > 0) shiftdown(0);	//�ӶѶ���ʼ�Զ����¶ѻ�
		}
		return maxheapdata[currSize];
	}

	public boolean exchange(int i, int j) {
		if( i < 0 || i >= currSize || j < 0 || j >= currSize){
			System.out.println("������λ�ò��Ϸ�");
			return false;
		}
		else{
			// ����i��jλ���ϵ�����Ԫ����
			ElemItem tmp = maxheapdata[i];
			maxheapdata[i] = maxheapdata[j];
			maxheapdata[j] = tmp;
		}
		return false;
	}

	/**
	 * ���Ƶ�k��Ԫ���ʹ��k��ֵ�����������ӽڵ�
	 * @param k	Ԫ������±�
	 */
	public void shiftdown(int k) {
		if(k < 0 || k >= currSize){
			System.out.println("��ǰλ��" + k + "���Ϸ�");
			return;
		}
		while(!isleaf(k)){
			int l = leftchild(k);
			if(l <= currSize - 2 && compare(l, l + 1) == -1)
				l++;	// l�������ӽڵ��нϴ��Ԫ����ı��
			if(compare(k, l) != -1) return;		//����
			else{
				exchange(l, k);	//����
				k = l;			//����
			}
		}
	}

	public void shiftup(int k) {
		if(k < 0 || k >= currSize){
			System.out.println("��ǰλ��" + k + "���Ϸ�");
			return;
		}
		int i;
		//k���ǶѶ�����k��С���丸�ڵ��ֵ���򲻶�����
		while(k >= 0 && compare(parent(k), k) == -1){
			exchange(k, parent(k));
			k = parent(k);
		}
	}

	
	public int heapSize() {
		return currSize;
	}

	public int leftchild(int position) {
		// λ�ò��Ϸ���С��0����û���ӽڵ�
		if(position < 0 || position >= currSize / 2)
			return -1;
		return 2 * position + 1;
	}

	public int rightchild(int position) {
		//λ�ò��Ϸ���С��0����û���ӽڵ�
		if(position < 0 || position >= (currSize - 1) / 2)
			return -1;
		return 2 * position + 2;
	}

	public int parent(int position) {
		//λ�ò��Ϸ�
		if(position < 0)
			return -1;
		return (int)((position - 1) / 2);
	}
	//�ж��Ƿ����ӽڵ�
	public boolean isleaf(int position) {
		return position >= 0 && position >= currSize / 2;
	}

	public ElemItem topVal() {
		if(currSize > 0)return maxheapdata[0];
		return null;
	}
	
	//���ض��߶ȴ�ӡһ��Ԫ����
	protected void printnode(int ps, int h){
		for(int i = 0; i < h; i++)
			System.out.print("\t");
		System.out.println(maxheapdata[ps].getElem());
	}
	//�����������Ԫ����
	protected void iterative_show(int pos, int h){
		if(pos < 0 || pos >= currSize) return;
		//�ȷ������ӽڵ�
		iterative_show(rightchild(pos), h + 1);
		//���ʸ��ڵ�
		printnode(pos, h);
		//�������ӽڵ�
		iterative_show(leftchild(pos), h + 1);
	}
	
	public void printHeap() {
		System.out.println("����Ԫ����ת90�ȷֲ��ӡ��");
		//�ӵ�һ��Ԫ�ؿ�ʼ��ӡ�������ڵ�0��
		iterative_show(0, 0);
	}
}
