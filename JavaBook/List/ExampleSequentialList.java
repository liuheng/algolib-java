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
		SequentialList sList = new SequentialList(10);//创建最大长度为10的顺序表
		ElemItem<Integer> e1;
		//将0~9添加到该顺序表，注意顺序表的当前位置一直是表头
		for(int i = 0; i < sList.getTotalSize(); i++){
			e1 = new ElemItem<Integer>(i);
			sList.append(e1);
			//sList.next();
		}
		sList.printList();//打印所有元素
		//将当前位置向后移三位
		sList.next();
		sList.next();
		sList.next();
		System.out.println("当前位置的元素项：" + sList.getCurrVal().getElem());
		//删除两个元素
		sList.remove();
		sList.remove();
		System.out.println("删除两个元素后顺序表中共有" + sList.getSize() + "项：");
		sList.printList();//打印所有元素
		System.out.println("当前位置的元素项：" + sList.getCurrVal().getElem());
		//在当前位置再插入两个元素
		sList.insert(new ElemItem<Double>(1.11));
		sList.printList();
		sList.insert(new ElemItem<String>("java"));
		sList.printList();
	}
}
