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
 * 图的连接矩阵法，
 * GraphMtrx.java
 */
public class GraphMtrx implements Graph{
	// 私有成员变量
	// 连接矩阵，二维数组
	private int[][] mtrx;
	// 边的个数
	private int numEdge;
	// 顶点的个数
	private int numVertex;
	// 标记矩阵
	private int[] mark;
	/**
	 * 构造函数，根据顶点个数初始化各个成员变量
	 * @param n	顶点个数
	 */
	public GraphMtrx(int n){
		mtrx = new int[n][n];
		numVertex = n;
		numEdge = 0;
		mark = new int[n];
	}
	
	// 获取图中顶点个数
	public int get_nv() {
		return numVertex;
	}
	
	// 获取图中边个数
	public int get_ne() {
		return numEdge;
	}
	
	/**
	 * 获取给定顶点为源点的第一条边（其终点的标号最小）；
	 * @param v	给定顶点
	 */
	public Edge firstEdge(int v) {
		// 遍历每个顶点，返回与v相连的第一条边
		for(int i = 0; i < numVertex; i++)
			if(isEdge(v, i))
				return new EdgeMtrx(v, i);
		return null;
	}
	
	/**
	 * 获取与给定边有相同源点的下一条边，其终点的标号大于
	 * 给定边的终点标号，但又是最小的；
	 * @param w	给定的边
	 * @return 与给定边同源点的下一条边
	 */
	public Edge nextEdge(Edge w) {
		if(w == null) return null;
		/* 从顶点标号为w的终点标号+1开始寻找第一个与w源点
		 * 相连的边，并返回这条边*/
		for(int i = w.get_v2() + 1; i < numEdge; i++)
			if(isEdge(w.get_v1(), i))
				return new EdgeMtrx(w.get_v1(), i);
		return null;
	}
	
	/**
	 * 判断给定边是否为图中一条边;
	 * @param w	给定的边
	 * @return	如果是一条边，返回true；否则返回false
	 */
	public boolean isEdge(Edge w) {
		if(w == null) 
			return false;
		// 判断矩阵中对应元素是否为无穷大
		else{
			int v1 = w.get_v1(), v2 = w.get_v2();
			return mtrx[v1][v2] != Integer.MAX_VALUE;
		}
	}
	
	/**
	 * 判断给定顶点对对应的边是否为图中一条边;
	 * @param i, j	顶点对对应的标号；
	 * @return 如果是一条边则返回true；否则false
	 */
	public boolean isEdge(int i, int j) {
		if(i < 0 || j < 0) 
			return false;
		// 判断矩阵中对应元素是否为无穷大
		return mtrx[i][j] != Integer.MAX_VALUE;
	}
	
	/**
	 * 获取给定边的源点；
	 * @param w	给定边
	 * @return 返回w的源点
	 */
	public int edge_v1(Edge w) {
		if(w == null) return -1;
		return w.get_v1();
	}
	
	/**
	 * 获取给定边的终点
	 * @param w	给定边
	 * @return	返回w的终点
	 */
	public int edge_v2(Edge w) {
		if(w == null) return -1;
		return w.get_v2();
	}
	
	/**
	 * 设定以顶点i和顶点j组成的边的权重，如果这条边在图中
	 * 并不存在，则创建这条边，并且其权值为wt
	 * @param i,j	两个顶点的标号
	 * @param wt	待设定的权重大小
	 */
	public void setEdgeWt(int i, int j, int wt) {
		if(i < 0 || j < 0) return;
		// 如果边不存在则更新节点个数
		if(!isEdge(i, j))
			numEdge++;
		// 重设边的权重
		mtrx[i][j] = wt;
	}
	
	/**
	 * 设置边w的权值，本函数调用setEdgeWt(int, int, int)
	 * @param w	待重设的边
	 * @param wt	待重设的权值
	 */
	public void setEdgeWt(Edge w, int wt) {
		//调用上一函数
		if(w == null) return;
		setEdgeWt(w.get_v1(), w.get_v2(), wt);
	}
	
	/**
	 * 获取顶点i和顶点j组成的边的权重
	 * @param i, j	待获取权重的边的源点、终点
	 * @return	顶点i和顶点j组成的边的权重
	 */
	public int getEdgeWt(int i, int j) {
		//未连接或入参不合法，返回无穷大
		if(i < 0 || j < 0 ||
		   !isEdge(i, j))
			return Integer.MAX_VALUE;
		return mtrx[i][j];
	}
	
	/**
	 * 获取给定边的权重，调用函数getEdgeWt(i, j)
	 * @param w	给定的边
	 * @return w的权重
	 */
	public int getEdgeWt(Edge w) {
		//调用上一函数
		if(w == null) 
			return Integer.MAX_VALUE;
		return getEdgeWt(w.get_v1(), w.get_v2());
	}
	
	/**
	 * 删除顶点i和顶点j组成的边
	 */
	public void delEdge(int i, int j) {
		if(i < 0 || j < 0)return;
		// 如果边存在则将矩阵对应位置取值为无穷大
		if(isEdge(i, j)){
			mtrx[i][j] = Integer.MAX_VALUE;
			numEdge--;
		}
	}
	
	/**
	 * 删除边，调用delEdge(int, int)
	 */
	public void delEdge(Edge w) {
		// 调用上一函数
		if(w == null) return;
		delEdge(w.get_v1(), w.get_v2());
	}
	
	/**
	 * 重设顶点的标记值，这在图的遍历算法中将起作用
	 */
	public void setMark(int v, int val) {
		if(v < 0) return;
		mark[v] = val;
	}
	
	/**
	 * 获取顶点v的标记值
	 */
	public int getMark(int v) {
		// 正确的标记的取值为0,1,...
		if(v < 0) return -1;
		return mark[v];
	}
}
