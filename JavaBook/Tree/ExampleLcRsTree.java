/*
 * Created on 2010-5-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * 左子节点/右兄弟节点描述法示例程序，
 * 包括树的创、节点删除和树的前序遍历打印
 */
public class ExampleLcRsTree {
	public static void main(String[] args){
		LcRsTree lrTree = new LcRsTree(100);
		lrTree.addChild(0, 0, 
				new ElemItem<String>("Root"));
		lrTree.addChild(0, 1, 
				new ElemItem<String>("C:"));
		lrTree.addChild(0, 2, 
				new ElemItem<String>("D:"));
		lrTree.addChild(0, 3, 
				new ElemItem<String>("E:"));
		lrTree.addChild(1, 4, 
				new ElemItem<String>("System"));
		lrTree.addChild(1, 5, 
				new ElemItem<String>("Programme"));
		lrTree.addChild(2, 6, 
				new ElemItem<String>("EBook"));
		lrTree.addChild(2, 7, 
				new ElemItem<String>("Game"));
		lrTree.addChild(3, 8, 
				new ElemItem<String>("Work"));
		lrTree.addChild(3, 9, 
				new ElemItem<String>("Paper"));
		lrTree.addChild(8, 10, 
				new ElemItem<String>("Java Algo"));
		lrTree.addChild(8, 11, 
				new ElemItem<String>("Java Code"));
		lrTree.addChild(6, 12, 
				new ElemItem<String>("Communication Network"));
		lrTree.addChild(7, 13, 
				new ElemItem<String>("LianLianKan"));
		lrTree.addChild(7, 14, 
				new ElemItem<String>("LianLianKan2"));
		lrTree.addChild(7, 15, 
				new ElemItem<String>("LianLianKan3"));
		lrTree.addChild(7, 16, 
				new ElemItem<String>("LianLianKan4"));
		System.out.println("整棵树的节点如下：");
		lrTree.ford_print_tree();
		System.out.println("删除节点二以及其子树后树的节点如下：");
		lrTree.removeChild(2);
		lrTree.ford_print_tree();
	}
}
