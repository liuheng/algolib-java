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
 * ջ���ݽṹ����ز���
 */
public interface Stack {
	public ElemItem pop();				//����������ջ��Ԫ��
	public void push(ElemItem elem);	//��Ԫ��elemѹ��ջ��
	public ElemItem getTop();			//��ȡջ����Ԫ��
	public int getSize();				//��ȡջ�Ĵ�С
	public int getTotalSize();			//��ȡջ����������С
	public void clear();				//���ջ������Ԫ��
	public void printStack();			//��ӡջ������Ԫ��
}
