/*
 * Created on 2010-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * ������ڵ���������ͣ������ڵ������ݲ��֡�ָ����һ���ڵ�Ķ���
 */
public class DoubleNode {
	private ElemItem elem;	//˽�е�Ԫ����
	private DoubleNode next;	//˽�е�ָ����һ���ڵ��ָ��
	private DoubleNode prev;	//˽�е�ָ��ǰһ���ڵ��ָ��
	public DoubleNode(ElemItem elem, DoubleNode next, DoubleNode prev){//���캯��
		this.elem = elem;
		this.next = next;
		this.prev = prev;
	}
	public DoubleNode(){//Ĭ�ϵĹ��캯��
		this.elem = null;
		this.next = null;
		this.prev = null;
	}
	public ElemItem getElem(){//��ȡԪ����
		return elem;
	}
	public void setElem(ElemItem new_elem){//��������Ԫ����
		this.elem = new_elem;
	}
	public DoubleNode getNext(){		// ��ȡ��һ���ڵ�
		return next;
	}
	public DoubleNode getPrev(){		//��ȡǰһ���ڵ�
		return prev;
	}
	public void setNext(DoubleNode new_next){//�����趨��һ���ڵ�
		this.next = new_next;
	}
	public void  setPrev(DoubleNode new_prev){//�����趨ǰһ�����
		this.prev = new_prev;
	}
	
}