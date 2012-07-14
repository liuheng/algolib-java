/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import List.SingleNode;

/**
 * @author Wei Lu
 *
 * 为实现连接表描述法设计的边数据结构
 * EdgeLnk.java
 */
public class EdgeLnk implements Edge{
	//私有成员变量
	//边的源顶点和终节点
	private int vert1, vert2;
	//单链表节点，
	private SingleNode itself;
	//构造函数
	public EdgeLnk(int _v1, int _v2, SingleNode _it){
		vert1 = _v1;
		vert2 = _v2;
		itself = _it;
	}
	
	public int get_v1() {
		return vert1;
	}
	
	public int get_v2() {
		return vert2;
	}
	
	public SingleNode get_lk(){
		return itself;
	}


}
