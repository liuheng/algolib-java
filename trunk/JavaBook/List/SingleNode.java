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
public class SingleNode {
	private ElemItem elem;	//˽�е�Ԫ����
	private SingleNode next;	//˽�е�ָ����һ���ڵ��ָ��
	public SingleNode(ElemItem elem, SingleNode next){//���캯��
		this.elem = elem;
		this.next = next;
	}
	public SingleNode(){//Ĭ�ϵĹ��캯��
		this.elem = null;
		this.next = null;
	}
	public ElemItem getElem(){//��ȡԪ����
		return elem;
	}
	public void setElem(ElemItem new_elem){//��������Ԫ����
		this.elem = new_elem;
	}
	public SingleNode getNext(){		// ��ȡ��һ���ڵ�
		return next;
	}
	public void setNext(SingleNode new_next){//�����趨��һ���ڵ�
		this.next = new_next;
	}
	
}
