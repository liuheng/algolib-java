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
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SampleSequentialStack {
	public static void main(String args[]){
		SequentialStack sqlStack = new SequentialStack(10);
		for(int i = 0; i < 15; i++){
			sqlStack.push(new ElemItem<Integer>(i));
		}
		sqlStack.printStack();
		System.out.println("-����Ԫ�أ�" + sqlStack.pop().getElem());
		System.out.println("-����Ԫ�أ�" + sqlStack.pop().getElem());
		System.out.print("-��������Ԫ�غ�");
		sqlStack.printStack();
		sqlStack.push(new ElemItem<Double>(12.345));
		System.out.print("-ѹ��Ԫ��"+12.345+",");
		sqlStack.printStack();
		sqlStack.push(new ElemItem<String>("JAVA"));
		System.out.print("-ѹ��Ԫ��"+"JAVA"+",");
		sqlStack.printStack();
		System.out.print("-ѹ��Ԫ��"+"JAVA"+",");
		sqlStack.push(new ElemItem<String>("JAVA"));
		sqlStack.printStack();
		ElemItem e = new ElemItem();
		for(int i = 0; i < 15;i++) 
			if((e = sqlStack.pop())!=null)
				System.out.println("-����Ԫ�أ�" + e.getElem());
	}
}
