/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Stack;

import Element.ElemItem;
import List.SingleNode;

/**
 * @author Administrator
 *
 * ����ջ�࣬LinkStackjava
 */
public class LinkStack implements Stack{
	private SingleNode top;	// ջ�����������õ������еĽڵ����
	private int currSize;	// ��ǰջ�Ĵ�С
	
	public LinkStack(){	//��ʼ��ջ
		top = null;
		currSize = 0;
	}

	public ElemItem pop() {
		if(top == null){
			System.out.println("��ǰջΪ�գ�");
			return null;
		}
		currSize--;
		SingleNode forReturn = top;
		top = top.getNext();
		return forReturn.getElem();
	}

	public void push(ElemItem elem) {
		top = new SingleNode(elem, top);
		currSize++;
	}

	public ElemItem getTop() {
		if(top != null)return top.getElem();
		System.out.println("��ǰջΪ�գ�");
		return null;
	}

	public int getSize() {
		return currSize;
	}

	public int getTotalSize() {
		return currSize;
	}

	public void clear() {
		currSize = 0;
		top = null;
	}

	public void printStack() {
		SingleNode ptr = top;
		if(ptr == null)
		{
			System.out.println("��ǰջΪ�գ�");
			return;
		}
		System.out.println("��ǰջ��ջ����ջ��Ԫ��Ϊ��");
		while(ptr!=null && ptr.getNext()!=null)
		{
			System.out.print(ptr.getElem().getElem() + ", ");
			ptr = ptr.getNext();
		}
		System.out.println(ptr.getElem().getElem() + ".");

	}
}
