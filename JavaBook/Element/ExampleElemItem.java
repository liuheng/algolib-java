/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Element;

/**
 * @author LuWei
 *
 * ElemItem��Ĳ���ʾ�����룬ExampleElemItem.java
 * ������Integer��Double��String��Student���͵�
 * ElemItem
 */
public class ExampleElemItem {
	
	public static void main(String args[]){
		//�ַ������͵�Ԫ����
		ElemItem<String> elemString = 
			new ElemItem<String>("String type elem item");
		//�������͵�Ԫ����
		ElemItem<Integer> elemInt 
				= new ElemItem<Integer>(123);
		//Double�͵�Ԫ����
		ElemItem<Double> elemDouble 
				= new ElemItem<Double>(12.345);
		//��ӡ����Ԫ����
		System.out.println(elemString.getElem().toString());
		System.out.println(elemInt.getElem());
		System.out.println(elemDouble.getElem());
		
		// Student ��Ĳ���ʾ��
		//ѧ��1��ѧ�ţ�1������15���ɼ�97
		ElemItem S1 
			= new ElemItem<Student>(new Student(1, "Jack", 15, 97));
		//ѧ��2,ѧ�ţ�2������16���ɼ�95
		ElemItem S2 
			= new ElemItem<Student>(new Student(2, "Lucy", 16, 95));
		System.out.println(S1.getElem());
		System.out.println(S2.getElem());
		int cmp = S1.compareTo(S2);
		System.out.println("S1 �� S2�ɼ�" + 
				(cmp>0?"��":(cmp<0?"��":"��ͬ")));
		
	}
}
