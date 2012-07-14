/*
 * Created on 2010-8-2
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Search;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 */
public class BstNode implements Comparable{
	//私有成员变量
	private ElemItem elem;
	//左右子节点
	private BstNode leftNode;
	private BstNode rightNode;
	
	private int N;
	//无参数构造函数
	public BstNode(){
		elem = null;
		leftNode = null;
		rightNode = null;
		N = 0;
	}
	
	//带参数构造函数
	public BstNode(ElemItem _e, 
				BstNode _l,
				BstNode _r,
				int _N){
		elem = _e;
		leftNode = _l;
		rightNode = _r;
		N = _N;
	}
	
	public void setN(int _N){
		N = _N;
	}
	
	public int getN(){
		return N;
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
	public BstNode getLeft(){
		return leftNode;
	}
	
	//重设左子节点
	public void setLeft(BstNode lft){
		leftNode = lft;
	}
	
	//获取右子节点
	public BstNode getRight(){
		return rightNode;
	}
	
	//重设右子节点
	public void setRight(BstNode rgt){
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
