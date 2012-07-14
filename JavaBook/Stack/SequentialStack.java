/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Stack;

import Element.ElemItem;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class SequentialStack implements Stack{
	private int top;				//栈顶位置，同时也表示栈中元素的个数
	private int totalSize;			//线性栈中元素的最大个数
	private ElemItem sqlStackData[];	//线性栈中的元素
	
	public SequentialStack(int _totalSize){	//有参数的构造函数
		top = 0;					//初始栈顶为0
		totalSize = _totalSize;		//栈的最大元素个数
		if (_totalSize > 0) sqlStackData = 
			new ElemItem[totalSize];
	}
	public SequentialStack()		//无参数的构造函数
	{	
		top = 0;					//栈顶为0
		totalSize = 0;				//最大栈的元素个数为0
	}
	/* (non-Javadoc)
	 * @see Stack.Stack#pop()
	 */
	public ElemItem pop() {
		if(top > 0) return sqlStackData[--top];	//返回栈顶元素并将栈顶下移
		System.out.println("栈为空！");
		return null;
	}

	/* (non-Javadoc)
	 * @see Stack.Stack#push(Element.ElemItem)
	 */
	public void push(ElemItem elem) {
		//向栈顶压入元素，并将栈顶指向上移；在栈已满时不压栈
		if(top < totalSize) sqlStackData[top++] = elem;
		else	System.out.println("栈已满！");
	}

	/* (non-Javadoc)
	 * @see Stack.Stack#getTop()
	 */
	public ElemItem getTop() {
		//返回栈顶元素
		if(top > 0) return sqlStackData[top - 1]; 
		System.out.println("栈为空！");
		return null;
	}

	/* (non-Javadoc)
	 * @see Stack.Stack#getSize()
	 */
	public int getSize() {
		return top;
	}

	/* (non-Javadoc)
	 * @see Stack.Stack#clear()
	 */
	public void clear() {
		top = 0;
	}
	/* (non-Javadoc)
	 * @see Stack.Stack#getTotalSize()
	 */
	public int getTotalSize() {
		return totalSize;
	}
	/* (non-Javadoc)
	 * @see Stack.Stack#printStack()
	 */
	public void printStack() {
		if(top == 0){
			System.out.println("当前栈为空！");
			return;
		}
		System.out.println("当前栈从栈顶至栈底元素为：");
		for(int i = top - 1; i >= 1; i--)
			System.out.print(sqlStackData[i].getElem() + ", ");
		System.out.println(sqlStackData[0].getElem() + ".");
		
	}

}
