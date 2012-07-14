/*
 * Created on 2010-4-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Lu Wei
 * 链表树结构测试示例程序，
 * 测试树中插入、删除节点以及打印树中元素项
 * ExampleLinkTree.java
 * 
 */
public class ExampleLinkTree {
	public static void main(String args[]){
		LinkTree ltree = new LinkTree(100);
		ltree.addChild(0, 1, 
				new ElemItem<String>("Root"));
		ltree.setRoot(1);
		ltree.addChild(1, 2, 
				new ElemItem<String>("C:"));
		ltree.addChild(1, 3, 
				new ElemItem<String>("D:"));
		ltree.addChild(1, 4, 
				new ElemItem<String>("E:"));
		ltree.addChild(2, 5, 
				new ElemItem<String>("System"));
		ltree.addChild(2, 6, 
				new ElemItem<String>("Programme"));
		ltree.addChild(3, 7, 
				new ElemItem<String>("EBook"));
		ltree.addChild(7, 13, 
				new ElemItem<String>("Communication Network"));
		ltree.addChild(3, 8, 
				new ElemItem<String>("Game"));
		ltree.addChild(8, 14, 
				new ElemItem<String>("LianLianKan"));
		ltree.addChild(8, 15, 
				new ElemItem<String>("LianLianKan2"));
		ltree.addChild(8, 16, 
				new ElemItem<String>("LianLianKan3"));
		ltree.addChild(8, 17, 
				new ElemItem<String>("LianLianKan4"));
		ltree.addChild(4, 9, 
				new ElemItem<String>("Work"));
		ltree.addChild(4, 10, 
				new ElemItem<String>("Paper"));
		ltree.addChild(9, 11, 
				new ElemItem<String>("Java Algo"));
		ltree.addChild(9, 12, 
				new ElemItem<String>("Java Code"));
		//调用前序、后序打印函数打印该树
		ltree.ford_print_tree();
		ltree.post_print_tree();
		//删除节点8和节点12
		ltree.removeChild(8);
		ltree.removeChild(12);
		//前序打印节点删除后的树
		System.out.println("前序打印节点8和12被删除后的树：");
		ltree.ford_print_tree();
	}
}
