/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import List.SingleLink2;
import List.SingleNode;

/**
 * @author Wei LU
 *
 * �̳е������ͼ�ڵ�����
 * GraphNodeLList.java
 */
public class GraphNodeLList extends SingleLink2{
	public SingleNode currNode(){
		return curr;
	}
	public void setCurr(SingleNode nd){
		curr = nd;
	}
}
