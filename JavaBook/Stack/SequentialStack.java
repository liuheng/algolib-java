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
	private int top;				//ջ��λ�ã�ͬʱҲ��ʾջ��Ԫ�صĸ���
	private int totalSize;			//����ջ��Ԫ�ص�������
	private ElemItem sqlStackData[];	//����ջ�е�Ԫ��
	
	public SequentialStack(int _totalSize){	//�в����Ĺ��캯��
		top = 0;					//��ʼջ��Ϊ0
		totalSize = _totalSize;		//ջ�����Ԫ�ظ���
		if (_totalSize > 0) sqlStackData = 
			new ElemItem[totalSize];
	}
	public SequentialStack()		//�޲����Ĺ��캯��
	{	
		top = 0;					//ջ��Ϊ0
		totalSize = 0;				//���ջ��Ԫ�ظ���Ϊ0
	}
	/* (non-Javadoc)
	 * @see Stack.Stack#pop()
	 */
	public ElemItem pop() {
		if(top > 0) return sqlStackData[--top];	//����ջ��Ԫ�ز���ջ������
		System.out.println("ջΪ�գ�");
		return null;
	}

	/* (non-Javadoc)
	 * @see Stack.Stack#push(Element.ElemItem)
	 */
	public void push(ElemItem elem) {
		//��ջ��ѹ��Ԫ�أ�����ջ��ָ�����ƣ���ջ����ʱ��ѹջ
		if(top < totalSize) sqlStackData[top++] = elem;
		else	System.out.println("ջ������");
	}

	/* (non-Javadoc)
	 * @see Stack.Stack#getTop()
	 */
	public ElemItem getTop() {
		//����ջ��Ԫ��
		if(top > 0) return sqlStackData[top - 1]; 
		System.out.println("ջΪ�գ�");
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
			System.out.println("��ǰջΪ�գ�");
			return;
		}
		System.out.println("��ǰջ��ջ����ջ��Ԫ��Ϊ��");
		for(int i = top - 1; i >= 1; i--)
			System.out.print(sqlStackData[i].getElem() + ", ");
		System.out.println(sqlStackData[0].getElem() + ".");
		
	}

}
