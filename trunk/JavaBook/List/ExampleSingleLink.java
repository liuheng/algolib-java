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
		SingleLink sglList = new SingleLink();//创建链表
		ElemItem<Integer> e1;
		//将0~9添加到该顺序表，注意顺序表的当前位置一直是表头
		for(int i = 0; i < 10; i++){
			e1 = new ElemItem<Integer>(i);
			sglList.append(e1);
		}
		sglList.printList();//打印所有元素
		//将当前位置向后移三位
		sglList.next();
		sglList.next();
		sglList.next();
		System.out.println("当前位置的元素项：" + sglList.getCurrVal().getElem());
		//删除两个元素
		sglList.remove();
		sglList.remove();
		System.out.println("删除两个元素后单链表中共有" + sglList.getSize() + "项：");
		sglList.printList();//打印所有元素
		System.out.println("当前位置的元素项：" + sglList.getCurrVal().getElem());
		//在当前位置再插入两个元素
		sglList.insert(new ElemItem<Double>(1.11));
		sglList.printList();
		sglList.insert(new ElemItem<String>("java"));
		sglList.printList();
	}

}
