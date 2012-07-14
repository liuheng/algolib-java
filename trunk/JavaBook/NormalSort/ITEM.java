/*
 * Created on 2010-6-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class ITEM<T  extends Comparable> extends ElemItem{
	public ITEM(T t){
		elem = t;
	}
	
	public static void exch(ITEM a[], int i, int j){
		ITEM t = a[i];
		a[i] = a[j];
		a[j] = t;
	}
	
	
	public String toString(){
		return elem.toString();
	}
	
}
