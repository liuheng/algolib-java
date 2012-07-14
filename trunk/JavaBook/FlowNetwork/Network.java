/*
 * Created on 2010-7-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package FlowNetwork;

import Element.ElemItem;


/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Network {
	//	私有成员变量
	//顶点链表数组，数组的每个元素对应于
	//与顶点相连的所有顶点形成的链表
	private NetworkNodeLList[] vertexList;
	//边的个数和顶点的个数
	private int num_Edge, num_Vertex;
	//节点标记数组
	private int[] mark;
	public Network(int n){
		vertexList = new NetworkNodeLList[n];
		for(int i = 0; i < n; i++){
			vertexList[i] = new NetworkNodeLList();
		}
		num_Edge = 0;
		num_Vertex = n;
		mark = new int[n];
	}
	
	public int get_nv() {
		return num_Vertex;
	}
	
	public int get_ne() {
		return num_Edge;
	}
	
	public NetworkEdge firstEdge(int v) {
		vertexList[v].goFirst();
		if(vertexList[v].getCurrVal() == null) return null;
		return new NetworkEdge(v, ((NetworkLLinkNode)(vertexList[v].getCurrVal().getElem())).get_des(), vertexList[v].currNode());
	}
	
	public NetworkEdge nextEdge(NetworkEdge w) {
		if(w == null) return null;
		int v = w.get_v1();
		vertexList[v].setCurr(w.get_lk());
		vertexList[v].next();
		if(vertexList[v].getCurrVal() == null) return null;
		return new NetworkEdge(v, ((NetworkLLinkNode)(vertexList[v].getCurrVal().getElem())).get_des(), vertexList[v].currNode());
	}
	
	public boolean isEdge(NetworkEdge w) {
		if(w == null) return false;
		int v = w.get_v1();
		vertexList[v].setCurr(w.get_lk());
		if(!vertexList[v].inList()) return false;
		return ((NetworkLLinkNode)(vertexList[v].getCurrVal().getElem())).get_des() == w.get_v2();
	}
	
	public boolean isEdge(int i, int j) {
		for(vertexList[i].goFirst();
			vertexList[i].getCurrVal() != null &&
				((NetworkLLinkNode)(vertexList[i].getCurrVal().getElem())).get_des() < j;
			vertexList[i].next());
		return vertexList[i].getCurrVal() != null
				&& ((NetworkLLinkNode)(vertexList[i].getCurrVal().getElem())).get_des() == j;
	}
	
	
	public int edge_v1(NetworkEdge w) {
		if(w == null) return -1;
		return w.get_v1();
	}
	
	public int edge_v2(NetworkEdge w) {
		if(w == null) return -1;
		return w.get_v2();
	}
	
	public void setEdgeC(int i, int j, int wt) {
		if(i < 0 || j < 0/* || wt == 0*/) return;
		NetworkLLinkNode gln = new NetworkLLinkNode(j, wt, 0);
		if(isEdge(i, j)){ 
			vertexList[i].setCurrVal(new ElemItem<NetworkLLinkNode>(gln));}
		else{
			vertexList[i].insert(new ElemItem<NetworkLLinkNode>(gln));
			num_Edge++;
		}
	}
	
	public void setEdgeC(NetworkEdge w, int wt) {
		if(w != null)
			setEdgeC(w.get_v1(), w.get_v2(), wt);
		
	}
	
	public int getEdgeC(int i, int j) {
		if(isEdge(i, j))
			return ((NetworkLLinkNode)(vertexList[i].getCurrVal().getElem())).get_wt();
		else return Integer.MAX_VALUE;
	}
	
	public int getEdgeC(NetworkEdge w) {
		if(w != null) return getEdgeC(w.get_v1(), w.get_v2());
		else return Integer.MAX_VALUE;
	}
	
	/**
	 * 新添加的函数，获取i为始点，j为终点的边
	 */
	public NetworkEdge getNetworkEdge(int i, int j){
		/*for(vertexList[i].goFirst();
			vertexList[i].getCurrVal() != null &&
				((NetworkLLinkNode)(vertexList[i].getCurrVal().getElem())).get_des() < j;
			vertexList[i].next());

*/
		if(isEdge(i, j))
			return new NetworkEdge(i, j, vertexList[i].currNode());
		else return null;
	}
	
	public void setEdgeFlow(int i, int j, int flow) {
		if(i < 0 || j < 0/* || wt == 0*/) return;
		int wt = getEdgeC(i, j);
		NetworkLLinkNode gln = new NetworkLLinkNode(j, wt, flow);
		if(isEdge(i, j)){ 
			vertexList[i].setCurrVal(new ElemItem<NetworkLLinkNode>(gln));}
		else{
			vertexList[i].insert(new ElemItem<NetworkLLinkNode>(gln));
			num_Edge++;
		}
	}
	
	public void setEdgeFlow(NetworkEdge w, int flow) {
		if(w != null)
			setEdgeFlow(w.get_v1(), w.get_v2(), flow);
	}
	
	public int getEdgeFlow(int i, int j) {
		if(isEdge(i, j))
			return ((NetworkLLinkNode)(vertexList[i].getCurrVal().getElem())).get_flow();
		else return Integer.MAX_VALUE;
	}
	
	public int getEdgeFlow(NetworkEdge w) {
		if(w != null) return getEdgeFlow(w.get_v1(), w.get_v2());
		else return Integer.MAX_VALUE;
	}
	
	public void addflowRto(NetworkEdge w, int v, int d){
		int pflow = (w.get_v1() == v)?(-1 * d):d;
		pflow += getEdgeFlow(w);
		setEdgeFlow(w, pflow);
		
	}
	
	public void delEdge(int i, int j) {
		if(isEdge(i, j)){
			vertexList[i].remove();
			num_Edge--;
		}
	}
	
	public void delEdge(NetworkEdge w) {
		if(w != null)
			delEdge(w.get_v1(), w.get_v2());
	}
	
	public void setMark(int v, int val) {
		if(v >= 0 && v < num_Vertex) mark[v] = val;
		
	}
	
	public int getMark(int v) {
		if(v >= 0 && v < num_Vertex) return mark[v];
		else return -1;
	}
	


	int getEdgeCap(NetworkEdge e) {return this.getEdgeC(e); }
	
//	int getEdgeFlow(NetworkEdge e) {return e.flow();}
	// 如果v是e的起点，则返回e的流（f）；若v是e的终点，则返回e的容量-e的流（c-f）
	int capRto(NetworkEdge e, int v) {
		return e.from(v)?getEdgeFlow(e):(getEdgeC(e) - getEdgeFlow(e));
	}
	
}
