/*
 * Created on 2010-4-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Heap;

import Element.ElemItem;

/**
 * @author Lu wei
 *
 * ���ѵĲ���ʵ�����룬ExampleMaxHeap.java
 */
public class ExampleMaxHeap {
	public static void main(String args[]){
		MaxHeap mheap = new MaxHeap(20);
		//����
		for(int i = 0; i < 12; i++){
			mheap.insert(new ElemItem<Integer>(i));
		}
		mheap.printHeap();
		//����Ԫ����ֵΪ20
		mheap.insert(new ElemItem<Integer>(20));
		System.out.println("����Ԫ����20��");
		mheap.printHeap();
		ElemItem e = mheap.removeMax();
		System.out.println("ɾ��������");
		System.out.println("ɾ���ĶѶ�Ϊ��" + e.getElem());
		mheap.printHeap();
		mheap.insert(new ElemItem<Integer>(8));
		System.out.println("����Ԫ����8��");
		mheap.printHeap();
		System.out.println("ɾ��λ��4�ϵ�Ԫ�����");
		mheap.remove(4);
		mheap.printHeap();
	}
}
