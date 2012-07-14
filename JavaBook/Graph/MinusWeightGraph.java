/*
 * Created on 2010-7-20
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import Queue.LinkQueue;
import Tree.LcRsTree;

/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MinusWeightGraph {
	// 待处理的图
	GraphLnk G;
	// V[i]表示i在生成树中的父节点
	int V[];
	// D[i]表示V[i]与i形成的边的权值
	double D[];
	// 得到的生成树，广义树类型
	LcRsTree mst;
	// 返回形式
	EdgeElem[] R;
	
	// 构造函数
	public MinusWeightGraph(GraphLnk G){
		this.G = G;
		// 根据G的节点数创建数组
		V = new int[G.get_nv()];
		D = new double[G.get_nv()];
		// 广义树，大小为G节点数加1，多1个根节点
		mst = new LcRsTree(G.get_nv() + 1);
		// 
		R = new EdgeElem[G.get_ne()];
	}
	
	public void BellmanFord(int s){
		if(s < 0) return;
		int nv = G.get_nv();
		// 初始化
		for(int i = 0; i < nv; i++){
			D[i] = Double.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- 每添加到树中
		}
		// 队列Q
		LinkQueue Q = new LinkQueue();
		// 起始顶点的距离为0
		D[s] = 0;
		// 将起点s和nv添加到队列中
		Q.enqueue(new ElemItem<Integer>(s));
		Q.enqueue(new ElemItem<Integer>(nv));
		// 迭代过程，直到Q为空
		while(Q.currSize() != 0){
			int f  = -1;
			int v, N = 0;
			while(nv == (v = ((Integer)(Q.dequeue().elem)).intValue())){
				if(N++ > nv){ f = 1; break;}
				Q.enqueue(new ElemItem<Integer>(nv));
			}
			if(f == 1) break;
			// 对v的所有相连的边e
			for(Edge e = G.firstEdge(v); G.isEdge(e); e = G.nextEdge(e)){
				// 更新e的终点w的距离
				int w = e.get_v2();
				double P = D[v] + G.getEdgeWt(e);
				// 如果w经过v的路径更短，则更新w的距离
				if(P < D[w]){
					D[w] = P;
					// 将w添加到队列中
					Q.enqueue(new ElemItem<Integer>(w));
					// 将w的父节点重置为v
					V[w] = v;
				}
			}
		}
		
		// 根据V数组建立最短路径树SPT
		mst.addChild(s, s, new ElemItem<Double>(D[s]));
		mst.setRoot(s);
		int f = -1; 
		// 顶点标记数组，V_idx[i] == 1表示i顶点已经在SPT中，否则不再SPT中
		int[] V_idx = new int[V.length];
		// 初始化
		for(int i = 0; i < V_idx.length; i++)V_idx[i] = 0;
		// 起始顶点s已经在SPT中
		 V_idx[s] = 1;
		while(true){
			f = -1;
			for(int i = 0; i < V.length; i++){
				// 顶点i不在SPT中，其父顶点V[i]在SPT中，则添加到SPT中
				if(V_idx[i] == 0 && V[i] >= 0 && V_idx[V[i]] == 1 && 
						mst.addChild(V[i], i, new ElemItem<Double>(D[i]))){
						V_idx[i] = 1;
						f = 1;
				}
			}
			// 一次都没有添加，结束循环
			if(f == -1) break;
		}
	}
	
	
	
	public static void main(String args[]){
		// java algo P278
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
		
		MinusWeightGraph mg = new MinusWeightGraph(g_minus);
		mg.BellmanFord(4);
		for(int i = 0; i < mg.V.length; i++){
			System.out.print(mg.V[i] + " <- " + mg.D[i] + " -> " + i + "\t");
		}
		System.out.println();
		mg.mst.ford_print_tree();
	}
}
