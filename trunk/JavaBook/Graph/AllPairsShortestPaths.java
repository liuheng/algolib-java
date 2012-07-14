/*
 * Created on 2010-7-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AllPairsShortestPaths {
	// 待处理的图
	GraphLnk G;
	// V[i][j]表示i在生成树中的父节点
	EdgeElem P[][];
	// D[i]表示V[i]与i形成的边的权值
	double D[][];
	
	// 返回形式
	EdgeElem[] R;
	
	// 构造函数
	public AllPairsShortestPaths(GraphLnk G){
		this.G = G;
		// 根据G的节点数创建数组
		int V = G.get_nv();
		D = new double[V][V];
		// 初始化
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				D[i][j] = Double.MAX_VALUE;
		
		P = new EdgeElem[V][V];
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				if(G.isEdge(i, j)){
					// 将连接边添加到P数组中，更新D数组
					P[i][j] = new EdgeElem(new EdgeLnk(i, j, null), G.getEdgeWt(i, j));
					D[i][j] = G.getEdgeWt(i, j);
				}
		// 数组D对角元设为0
		for(int i = 0; i < V; i++) D[i][i] = 0.0;
		//  打印中间结果
		for(int i = 0; i < D.length; i++){
			for(int j = 0; j < D.length; j++){
				if(D[i][j] != Double.MAX_VALUE)
					System.out.print(D[i][j] + "\t");
				else System.out.print("oo\t");
			}
			System.out.println();
		}
		System.out.println("\n-----------------------------------");
	}
	
	/*
	 * Floyd 算法，求解全部最短路径算法 O(V^3);
	 * 函数没有入参。
	 */
	public void Floyd(){
		int V = G.get_nv();
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				if(P[j][i] != null)
					for(int t = 0; t < V; t++)
						if(j != t)
							// 更新顶点j到顶点t的距离，即D[j][t]
							if(D[j][i] != Double.MAX_VALUE &&
									D[i][t] != Double.MAX_VALUE &&
									D[j][t] > D[j][i] + D[i][t]){
								P[j][t] = P[j][i];
								D[j][t] = D[j][i] + D[i][t];
								
								// 打印中间结果
								for(int i2 = 0; i2 < D.length; i2++){
									for(int j2 = 0; j2 < D.length; j2++){
										if(D[i2][j2] != Double.MAX_VALUE)
											System.out.print(D[i2][j2] + "\t");
										else System.out.print("oo\t");
									}
									System.out.println();
								}
								System.out.println("\n-----------------------------------");
							}
	}
}
