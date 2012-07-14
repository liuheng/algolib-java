/*
 * Created on 2010-7-20
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
public class JohnsonAlgo {
	double D[][];
	int P[][];
	GraphLnk G;
	
	/**
	 * 构造函数
	 */
	public JohnsonAlgo(GraphLnk G) {
		this.G = G;
		D = new double[G.get_nv()][G.get_nv()];
		P = new int[G.get_nv()][G.get_nv()];
	}
	
	public boolean Johnson(){
		// 创建一个图_G
		GraphLnk _G = new GraphLnk(G.get_nv() + 1);
		for(int i = 0; i < G.get_nv(); i++){
			for(Edge e = G.firstEdge(i); G.isEdge(e); e = G.nextEdge(e))
				_G.setEdgeWt(e.get_v1(), e.get_v2(), G.getEdgeWt(e));
		}
		int ad = _G.get_nv() - 1;
		for(int i = 0; i < G.get_nv(); i++){
			_G.setEdgeWt(ad, i, 0);
		}
		
		MinusWeightGraph swg = new MinusWeightGraph(_G);
		swg.BellmanFord(ad);
		
		// 检测是否有负环
		for(int i = 0; i < _G.get_nv(); i++)
			for(Edge e = _G.firstEdge(i); _G.isEdge(e); e = _G.nextEdge(e))
				if(swg.D[e.get_v2()] > swg.D[e.get_v1()] + _G.getEdgeWt(e))
				{
					System.out.println("图中有负环。");
					return false;
				}
				else{
					int u = _G.edge_v1(e), v = _G.edge_v2(e);
					int wt = (int) (_G.getEdgeWt(e) + 
								swg.D[_G.edge_v1(e)] - swg.D[_G.edge_v2(e)]);
					_G.setEdgeWt(u, v, wt);
				}
		
		// Dijkstra 算法求解每一个顶点的最短路径树
		SingleSourceShortestPaths sssp = new SingleSourceShortestPaths(_G);
		for(int i = 0; i < _G.get_nv() - 1; i++){
			sssp.Dijkstra(i);
			for(int j = 0; j < _G.get_nv() - 1; j++){
				D[i][j] = sssp.D[j] + swg.D[j] - swg.D[i];
				P[i][j] = sssp.V[j];
			}
		}
		return true;
	}
	
	public static void main(String args[]){
		GraphLnk g_minus = new GraphLnk(6);
		g_minus.setEdgeWt(0, 1, 41);
		g_minus.setEdgeWt(0, 5, 29);
		g_minus.setEdgeWt(1, 2, 51);
		g_minus.setEdgeWt(1, 4, 32);
		g_minus.setEdgeWt(2, 3, 50);
		g_minus.setEdgeWt(3, 0, 45);
		g_minus.setEdgeWt(3, 5, -38);
		g_minus.setEdgeWt(4, 2, 32);
		g_minus.setEdgeWt(4, 3, 36);
		g_minus.setEdgeWt(5, 1, -29);
		g_minus.setEdgeWt(5, 4, 21);
		JohnsonAlgo ja = new JohnsonAlgo(g_minus);
		ja.Johnson();
		for(int i = 0; i < g_minus.get_nv(); i++){
			for(int j = 0; j < g_minus.get_nv(); j++){
				System.out.print(ja.D[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println("////////////////////////////////////////");
		for(int i = 0; i < g_minus.get_nv(); i++){
			for(int j = 0; j < g_minus.get_nv(); j++){
				System.out.print(ja.P[i][j] + "\t");
			}
			System.out.println();
		}
	}
}
