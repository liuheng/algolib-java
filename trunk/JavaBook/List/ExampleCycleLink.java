/*
 * Created on 2010-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

import Element.ElemItem;

/**
 * @author Administrator
 *
 * ����ѡ���������⣬ѭ�������Ӧ��ExamlpeCycleLonk.java
 */
public class ExampleCycleLink {
	public static void main(String args[]){
		int num = 10; //���ӵĸ���
		int count = 0;//���ӱ����ļ�����
		//����ѭ����������ź��ӵ����
		CycleLink cyLink = new CycleLink();
		//��������������
		for(int i = 0; i < num; i++){
			cyLink.append(new ElemItem<Integer>(i));
		}
		System.out.println("1\t2\tout");
		cyLink.goFirst();//�ӵ�һֻ���ӿ�ʼ
		//���ڻ�����ӵ�״̬��Ԫ����Ϊnull��ʾ�ú��ӳ�����
		ElemItem e;		
		//ÿ��1��3������������һֻ���ӣ�����һ��num�ֱ���
		for(int i = 0; i < num; i++){
			count = 0;
			System.out.println("--------------------");
			while(true){
				//��ȡ��ǰ���ӵ���Ϣ
				e = cyLink.getCurrVal();
				if(e != null){//�ú�����Ȼ���о�ѡȨ
					count++;
					if(count == 3) break;//��������
					System.out.print(cyLink.getCurrVal().getElem() + "\t");
					cyLink.next();//����û�н�����ʱ�����
				}
				else{
					cyLink.next();//��ǰ����û�о�ѡȨ������
				}
			}
			System.out.println(cyLink.getCurrVal().getElem());
			cyLink.setCurrVal(null);//���µ�ǰ������3�ĺ��ӵ�״̬
			cyLink.next();
		}
	}
}
