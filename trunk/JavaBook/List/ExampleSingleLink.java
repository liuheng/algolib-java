/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

import Element.ElemItem;

/**
 * @author Lu wei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ExampleSingleLink {
	public static void main(String args[]){
		SingleLink sglList = new SingleLink();//��������
		ElemItem<Integer> e1;
		//��0~9��ӵ���˳���ע��˳���ĵ�ǰλ��һֱ�Ǳ�ͷ
		for(int i = 0; i < 10; i++){
			e1 = new ElemItem<Integer>(i);
			sglList.append(e1);
		}
		sglList.printList();//��ӡ����Ԫ��
		//����ǰλ���������λ
		sglList.next();
		sglList.next();
		sglList.next();
		System.out.println("��ǰλ�õ�Ԫ���" + sglList.getCurrVal().getElem());
		//ɾ������Ԫ��
		sglList.remove();
		sglList.remove();
		System.out.println("ɾ������Ԫ�غ������й���" + sglList.getSize() + "�");
		sglList.printList();//��ӡ����Ԫ��
		System.out.println("��ǰλ�õ�Ԫ���" + sglList.getCurrVal().getElem());
		//�ڵ�ǰλ���ٲ�������Ԫ��
		sglList.insert(new ElemItem<Double>(1.11));
		sglList.printList();
		sglList.insert(new ElemItem<String>("java"));
		sglList.printList();
	}

}
