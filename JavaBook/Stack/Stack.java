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
 * 栈数据结构及相关操作
 */
public interface Stack {
	public ElemItem pop();				//弹出并返回栈顶元素
	public void push(ElemItem elem);	//将元素elem压入栈中
	public ElemItem getTop();			//获取栈顶的元素
	public int getSize();				//获取栈的大小
	public int getTotalSize();			//获取栈的允许最大大小
	public void clear();				//清除栈中所有元素
	public void printStack();			//打印栈中所有元素
}
