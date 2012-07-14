/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

/**
 * @author Wei LU
 *
 * 用连接表法描述图结构，每个顶点对应一个
 * 链表，链表节点包含结束顶点和对应的权值
 * GraphLLinkNode.java
 */
public class GraphLLinkNode implements Comparable{
	//私有成员，终点和权重
	private int des, weight;
	//构造函数
	public GraphLLinkNode(int _des, int _wt){
		des = _des;
		weight = _wt;
	}
	
	public void set_des(int _d){
		des = _d;
	}
	
	public void set_wt(int _wt){
		weight = _wt;
	}
	
	public int get_des(){
		return des;
	}
	
	public int get_wt(){
		return weight;
	}
	
	public int compareTo(Object arg0) {
		int _wt = ((GraphLLinkNode)(arg0)).get_wt();
		if(weight > _wt) return 1;
		else if(weight < _wt) return -1;
		else return 0;
	}

}
