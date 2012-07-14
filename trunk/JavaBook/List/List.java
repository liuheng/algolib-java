/*
 * Created on 2010-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

/**
 * @author Administrator
 *
 * 线性表，ADT以及两种实现算法：
 * 顺序表和链表，链表又分为单链表、双链表和循环链表
 */

import Element.ElemItem;;
/*
 * 线性链表接口设计
 */
interface List {
	public void insert (ElemItem elem);	//在当前位置插入元素
	public ElemItem remove();			//删除并返回当前元素
	public void append(ElemItem elem);	//在当前线性表尾部添加元素
	public void clear();				//清空整个线性链表
	public void goFirst();				//将当前位置赋值为第一个位置
	public int next();					//设置当前位置为下一位置并返回位置
	public int prev();					//设置当前位置为前一位置并返回位置
	public void setCurrVal(ElemItem elem);	//设置当前位置的元素项
	public ElemItem getCurrVal();		//获取当前位置的元素项
	public int getSize();				//获取当前线性表的有效元素个数
	public int getTotalSize();			//获取线性表的最大尺寸
	public boolean inList(); 			//当前位置是否在链表中
	public void printList();			//打印当前线性表中的有效元素
}
