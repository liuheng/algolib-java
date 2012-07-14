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
 * 图的接口定义，
 * 包括对图以及边的各种操作
 * Graph.java
 */
public interface Graph {
	//获取顶点个数
	public int get_nv();
	//获取边的条数
	public int get_ne();
	//获取顶点v的第一条边
	public Edge firstEdge(int v);
	//获取边w的下一条同源点的边
	public Edge nextEdge(Edge w);
	//判断w是否是图上的边，是返回true
	public boolean isEdge(Edge w);
	//判断顶点i和j之间是否是边
	public boolean isEdge(int i, int j);
	//返回边w的源点
	public int edge_v1(Edge w);
	//返回边w的终点
	public int edge_v2(Edge w);
	//设定顶点i，j之间边的权重
	public void setEdgeWt(int i, int j, int wt);
	//设定边w的权重
	public void setEdgeWt(Edge w, int wt);
	//获取顶点i,j之间边上的权重
	public int getEdgeWt(int i, int j);
	//获取边w上权重
	public int getEdgeWt(Edge w);
	//删除顶点i,j之间的边
	public void delEdge(int i, int j);
	//删除边w
	public void delEdge(Edge w);
	//设定顶点v的标记
	public void setMark(int v, int val);
	//读取顶点v的标记
	public int getMark(int v);
	
}
