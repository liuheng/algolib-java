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
 * ����ջ��ʾ������SampleLinkStackjava
 */
public class SampleLinkStack {
	public static void main(String args[]){
		LinkStack lnkStack = new LinkStack();
		for(int i = 0; i < 10; i++){
			lnkStack.push(new ElemItem<Integer>(i));
		}
		lnkStack.printStack();
		System.out.println("-����Ԫ�أ�" + lnkStack.pop().getElem());
		System.out.println("-����Ԫ�أ�" + lnkStack.pop().getElem());
		System.out.print("-��������Ԫ�غ�");
		lnkStack.printStack();
		lnkStack.push(new ElemItem<Double>(12.345));
		System.out.print("-ѹ��Ԫ��"+12.345+",");
		lnkStack.printStack();
		lnkStack.push(new ElemItem<String>("JAVA"));
		System.out.print("-ѹ��Ԫ��"+"JAVA"+",");
		lnkStack.printStack();
		System.out.print("-ѹ��Ԫ��"+"JAVA"+",");
		lnkStack.push(new ElemItem<String>("JAVA"));
		lnkStack.printStack();
		ElemItem e = new ElemItem();
		for(int i = 0; i < 15;i++) 
			if((e = lnkStack.pop())!=null)
				System.out.println("-����Ԫ�أ�" + e.getElem());
	}
}
