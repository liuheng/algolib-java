/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package FlowNetwork;

/**
 * @author Wei LU
 *
 * 用连接表法描述网络结构，每个顶点对应一个
 * 链表，链表节点包含结束顶点和对应的权值
 * NetworkLLinkNode.java
 */
public class NetworkLLinkNode implements Comparable{
	//私有成员，终点、权重、流
	private int des, weight, flow;
	//构造函数
	public NetworkLLinkNode(int _des, int _wt, int _flow){
		des = _des;
		weight = _wt;
		flow  = _flow;
	}
	
	// 设置终点 
	public void set_des(int _d){
		des = _d;
	}
	
	// 设置权重
	public void set_wt(int _wt){
		weight = _wt;
	}
	
	// 设置流
	public void set_flow(int f){
		flow = f;
	}
	
	// 获取终点
	public int get_des(){
		return des;
	}
	
	// 获取权重
	public int get_wt(){
		return weight;
	}
	
	// 获取流
	public int get_flow(){
		return flow;
	}
	
	// 比较两个两个顶点的权重
	public int compareTo(Object arg0) {
		int _wt = ((NetworkLLinkNode)(arg0)).get_wt();
		if(weight > _wt) return 1;
		else if(weight < _wt) return -1;
		else return 0;
	}

}
