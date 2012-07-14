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
 * 最大堆的测试实例代码，ExampleMaxHeap.java
 */
public class ExampleMaxHeap {
	public static void main(String args[]){
		MaxHeap mheap = new MaxHeap(20);
		//建堆
		for(int i = 0; i < 12; i++){
			mheap.insert(new ElemItem<Integer>(i));
		}
		mheap.printHeap();
		//插入元素项值为20
		mheap.insert(new ElemItem<Integer>(20));
		System.out.println("插入元素项20后：");
		mheap.printHeap();
		ElemItem e = mheap.removeMax();
		System.out.println("删除最大项后：");
		System.out.println("删除的堆顶为：" + e.getElem());
		mheap.printHeap();
		mheap.insert(new ElemItem<Integer>(8));
		System.out.println("插入元素项8后：");
		mheap.printHeap();
		System.out.println("删除位置4上的元素项后：");
		mheap.remove(4);
		mheap.printHeap();
	}
}
