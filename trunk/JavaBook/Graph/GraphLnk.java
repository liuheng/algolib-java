/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * 连接表实现图算法，
 * GraphLnk.java
 */
public class GraphLnk implements Graph{
	//私有成员变量
	//顶点链表数组，数组的每个元素对应于
	//与顶点相连的所有顶点形成的链表
	private GraphNodeLList[] vertexList;
	//边的个数和顶点的个数
	private int num_Edge, num_Vertex;
	//节点标记数组
	private int[] mark;
	public GraphLnk(int n){
		vertexList = new GraphNodeLList[n];
		for(int i = 0; i < n; i++){
			vertexList[i] = new GraphNodeLList();
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
	
	public Edge firstEdge(int v) {
		vertexList[v].goFirst();
		if(vertexList[v].getCurrVal() == null) return null;
		return new EdgeLnk(v, ((GraphLLinkNode)(vertexList[v].getCurrVal().getElem())).get_des(), vertexList[v].currNode());
	}
	
	public Edge nextEdge(Edge w) {
		if(w == null) return null;
		int v = w.get_v1();
		vertexList[v].setCurr(((EdgeLnk)w).get_lk());
		vertexList[v].next();
		if(vertexList[v].getCurrVal() == null) return null;
		return new EdgeLnk(v, ((GraphLLinkNode)(vertexList[v].getCurrVal().getElem())).get_des(), vertexList[v].currNode());
	}
	
	public boolean isEdge(Edge w) {
		if(w == null) return false;
		int v = w.get_v1();
		vertexList[v].setCurr(((EdgeLnk)w).get_lk());
		if(!vertexList[v].inList()) return false;
		return ((GraphLLinkNode)(vertexList[v].getCurrVal().getElem())).get_des() == w.get_v2();
	}
	
	public boolean isEdge(int i, int j) {
		for(vertexList[i].goFirst();
			vertexList[i].getCurrVal() != null &&
				((GraphLLinkNode)(vertexList[i].getCurrVal().getElem())).get_des() < j;
			vertexList[i].next());
		return vertexList[i].getCurrVal() != null
				&& ((GraphLLinkNode)(vertexList[i].getCurrVal().getElem())).get_des() == j;
	}
	
	
	public int edge_v1(Edge w) {
		if(w == null) return -1;
		return w.get_v1();
	}
	
	public int edge_v2(Edge w) {
		if(w == null) return -1;
		return w.get_v2();
	}
	
	public void setEdgeWt(int i, int j, int wt) {
		if(i < 0 || j < 0/* || wt == 0*/) return;
		GraphLLinkNode gln = new GraphLLinkNode(j, wt);
		if(isEdge(i, j)) vertexList[i].setCurrVal(new ElemItem<GraphLLinkNode>(gln));
		else{
			vertexList[i].insert(new ElemItem<GraphLLinkNode>(gln));
			num_Edge++;
		}
	}
	
	public void setEdgeWt(Edge w, int wt) {
		if(w != null)
			setEdgeWt(w.get_v1(), w.get_v2(), wt);
		
	}
	
	public int getEdgeWt(int i, int j) {
		if(isEdge(i, j))
			return ((GraphLLinkNode)(vertexList[i].getCurrVal().getElem())).get_wt();
		else return Integer.MAX_VALUE;
	}
	
	public int getEdgeWt(Edge w) {
		if(w != null) return getEdgeWt(w.get_v1(), w.get_v2());
		else return Integer.MAX_VALUE;
	}
	
	public void delEdge(int i, int j) {
		if(isEdge(i, j)){
			vertexList[i].remove();
			num_Edge--;
		}
	}
	
	public void delEdge(Edge w) {
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

}
