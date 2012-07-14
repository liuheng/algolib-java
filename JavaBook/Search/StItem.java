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
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StItem implements Comparable{
	private ElemItem key;
	private ElemItem info;
	
	public StItem(ElemItem _k, ElemItem _i){
		key = _k;
		info = _i;
	}
	
	public void SetKey(ElemItem _k){
		key = _k;
	}
	
	public void SetInfo(ElemItem _I){
		info = _I;
	}
	
	public ElemItem GetKey(){
		return key;
	}
	
	public ElemItem GetInfo(){
		return info;
	}
	
	public String toString(){
		String ky, ifo;
		if (key == null) ky = "null";
		else ky = key.elem.toString();
		if (info == null) ifo = "null";
		else ifo = info.elem.toString();
		return "[key: " + ky + "]\n[info: " + ifo + "]\n";
	}

	public int compareTo(Object o) {
		return 0;
	}
	
	
}
