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
 * ��ֵΪ��������ؼ���Ϊ��������
 */
public class IntKeyItem implements Comparable{
	// ˽�б���
	private int val;
	private ElemItem info;
	
	// ���캯��
	public IntKeyItem(int _v, ElemItem _e){
		val = _v;
		info = _e;
	}
	
	// ���ؼ�ֵ
	public int key(){
		return val;
	}
	
	// ���ص�ǰ�������
	public ElemItem getinfo(){
		return info;
	}
	
	// �ȽϺ���
	public int compareTo(Object o){
		IntKeyItem other = (IntKeyItem)o;
		return info.compareTo(other.info);
	}
	
	// ��ǰ�����ʾ
	public String toString(){
		return "[" + key() + "] " + "[" + info.elem + "]"; 
	}
}
