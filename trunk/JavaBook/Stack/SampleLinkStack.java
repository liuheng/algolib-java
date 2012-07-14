/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Stack;

import Element.ElemItem;

/**
 * @author Lu Wei
 * 链表栈的示例程序，SampleLinkStackjava
 */
public class SampleLinkStack {
	public static void main(String args[]){
		LinkStack lnkStack = new LinkStack();
		for(int i = 0; i < 10; i++){
			lnkStack.push(new ElemItem<Integer>(i));
		}
		lnkStack.printStack();
		System.out.println("-弹出元素：" + lnkStack.pop().getElem());
		System.out.println("-弹出元素：" + lnkStack.pop().getElem());
		System.out.print("-弹出两个元素后");
		lnkStack.printStack();
		lnkStack.push(new ElemItem<Double>(12.345));
		System.out.print("-压入元素"+12.345+",");
		lnkStack.printStack();
		lnkStack.push(new ElemItem<String>("JAVA"));
		System.out.print("-压入元素"+"JAVA"+",");
		lnkStack.printStack();
		System.out.print("-压入元素"+"JAVA"+",");
		lnkStack.push(new ElemItem<String>("JAVA"));
		lnkStack.printStack();
		ElemItem e = new ElemItem();
		for(int i = 0; i < 15;i++) 
			if((e = lnkStack.pop())!=null)
				System.out.println("-弹出元素：" + e.getElem());
	}
}
