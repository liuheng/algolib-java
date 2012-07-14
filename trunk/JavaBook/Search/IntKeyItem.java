/*
 * Created on 2010-7-30
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Search;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * 键值为整数的项，关键字为整数类型
 */
public class IntKeyItem implements Comparable{
	// 私有变量
	private int val;
	private ElemItem info;
	
	// 构造函数
	public IntKeyItem(int _v, ElemItem _e){
		val = _v;
		info = _e;
	}
	
	// 返回键值
	public int key(){
		return val;
	}
	
	// 返回当前项的内容
	public ElemItem getinfo(){
		return info;
	}
	
	// 比较函数
	public int compareTo(Object o){
		IntKeyItem other = (IntKeyItem)o;
		return info.compareTo(other.info);
	}
	
	// 当前类的显示
	public String toString(){
		return "[" + key() + "] " + "[" + info.elem + "]"; 
	}
}
