/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

import Element.ElemItem;

/**
 * @author Luwei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExampleSequentialList {
	public static void main(String args[]){
		SequentialList sList = new SequentialList(10);//������󳤶�Ϊ10��˳���
		ElemItem<Integer> e1;
		//��0~9��ӵ���˳���ע��˳���ĵ�ǰλ��һֱ�Ǳ�ͷ
		for(int i = 0; i < sList.getTotalSize(); i++){
			e1 = new ElemItem<Integer>(i);
			sList.append(e1);
			//sList.next();
		}
		sList.printList();//��ӡ����Ԫ��
		//����ǰλ���������λ
		sList.next();
		sList.next();
		sList.next();
		System.out.println("��ǰλ�õ�Ԫ���" + sList.getCurrVal().getElem());
		//ɾ������Ԫ��
		sList.remove();
		sList.remove();
		System.out.println("ɾ������Ԫ�غ�˳����й���" + sList.getSize() + "�");
		sList.printList();//��ӡ����Ԫ��
		System.out.println("��ǰλ�õ�Ԫ���" + sList.getCurrVal().getElem());
		//�ڵ�ǰλ���ٲ�������Ԫ��
		sList.insert(new ElemItem<Double>(1.11));
		sList.printList();
		sList.insert(new ElemItem<String>("java"));
		sList.printList();
	}
}
