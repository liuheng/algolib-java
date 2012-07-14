/*
 * Created on 2010-5-6
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * 二叉树，本结构的目的是用于二叉树的各种遍历算法等
 */
public class LinkBinTreeNode implements Comparable{
	//私有成员变量
	private ElemItem elem;
	//左右子节点
	private LinkBinTreeNode leftNode;
	private LinkBinTreeNode rightNode;
	
	//无参数构造函数
	public LinkBinTreeNode(){
		elem = null;
		leftNode = null;
		rightNode = null;
	}
	
	//带参数构造函数
	public LinkBinTreeNode(ElemItem _e, 
				LinkBinTreeNode _l,
				LinkBinTreeNode _r){
		elem = _e;
		leftNode = _l;
		rightNode = _r;
	}
	
	//获取元素项
	public ElemItem getElem(){
		return elem;
	}
	
	//设置元素项
	public void setElem(ElemItem _elem){
		elem = _elem;
	}
	
	//获取左子节点
	public LinkBinTreeNode getLeft(){
		return leftNode;
	}
	
	//重设左子节点
	public void setLeft(LinkBinTreeNode lft){
		leftNode = lft;
	}
	
	//获取右子节点
	public LinkBinTreeNode getRight(){
		return rightNode;
	}
	
	//重设右子节点
	public void setRight(LinkBinTreeNode rgt){
		rightNode = rgt;
	}
	
	//判断节点是否是叶节点
	public boolean isLeaf(){
		return (leftNode == null) &&
				(rightNode == null);
	}
	
	//返回字符串
	public String toString(){
		return elem.getElem().toString();
	}

	// 实现接口函数
	public int compareTo(Object arg0) {
		return elem.compareTo((ElemItem)arg0);
	}
}
