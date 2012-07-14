/*
 * Created on 2010-4-27
 *
 * 树的“左子节点/右兄弟节点”描述方法的节点类的实现
 */
package Tree;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * 左子树-右兄弟树节点数据结构，
 */
public class LcRsTNode{
	//节点元素项
	private ElemItem elem;
	//节点序号
	private int idx;
	//节点的父节点
	private int parentNode;
	//节点的左子节点
	private int leftMostChildNode;
	//节点的右兄弟节点
	private int rightSiblingNode;
	
	//无参数构造函数
	public LcRsTNode(){
		elem = null;
		parentNode = -1;
		leftMostChildNode = -1;
		rightSiblingNode = -1;
	}
	
	//有参数构造函数
	public LcRsTNode(ElemItem _elem, 
					int _parentNode,
					int _leftMtChild, 
					int _rightSibling){
		elem = _elem;
		parentNode = _parentNode;
		leftMostChildNode = _leftMtChild;
		rightSiblingNode = _rightSibling;
	}
	
	//设置节点的序号
	public void setIdx(int _idx){
		idx = _idx;
	}
	
	//获取节点的序号
	public int getIdx(){
		return idx;
	}
	
	//获取元素项
	public ElemItem getElem() {
		return elem;
	}
	
	//设置元素项
	public void setElem(ElemItem _elem) {
		elem = _elem;
	}
	
	//获取节点的父节点
	public int parent() {
		return parentNode;
	}
	
	//获取节点的最左子节点
	public int leftMostChild() {
		return leftMostChildNode;
	}

	//获取节点的右兄弟节点
	public int rightSibling() {
		return rightSiblingNode;
	}
	
	//重设节点的父节点
	public void setParent(int n) {
		parentNode = n;
	}
	
	//重设节点的最左子节点
	public void setLeftMostChild(int n) {
		leftMostChildNode = n;
	}
	
	//重设节点的右兄弟节点
	public void setNextSibling(int n) {
		rightSiblingNode = n;
	}
	
	//节点是否是叶节点
	public boolean isLeaf() {
		return leftMostChildNode == -1;
	}
	
	//返回元素项String形式
	public String toString(){
		return elem.getElem().toString();
	}
}
