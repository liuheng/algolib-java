/*
 * Created on 2010-7-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import Heap.MaxHeap;
import Tree.LcRsTree;

/**
 * @author Wei LU
 *
 * 最短路径类，本类中包含图的各种最短路径问题求解算法
 */
public class SingleSourceShortestPaths {
	//待处理的图
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
	public SingleSourceShortestPaths(GraphLnk G){
		this.G = G;
		// 根据G的节点数创建数组
		V = new int[G.get_nv()];
		D = new double[G.get_nv()];
		// 广义树，大小为G节点数加1，多1个根节点
		mst = new LcRsTree(G.get_nv() + 1);
		// 
		R = new EdgeElem[G.get_ne()];
	}
	
	/*
	 * Dijkstra 算法寻找图中单源点最短路径
	 * 输入为待寻找的图G以及源点s
	 */
	public void Dijkstra(int s){
		if(s < 0) return;
		int nv = G.get_nv();
		// 初始化
		for(int i = 0; i < nv; i++){
			D[i] = Integer.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- 每添加到树中
		}
		
		// 对起点s，父节点为-1，距离为0
		V[s] = -1;
		D[s] = 0;
		G.setMark(s, 1);
		for(Edge w = G.firstEdge(s); G.isEdge(w); w = G.nextEdge(w)){
			D[G.edge_v2(w)] = G.getEdgeWt(w);
			V[G.edge_v2(w)] = s;
		}

		/*在其余顶点中找到与当前SPT最近的顶点v，并将顶点的父节点和
		 * 顶点v添加到SPT中。其中图的权值存放在节点v中。
		 * 循环迭代，直至所有顶点都遍历一遍.
		 */
		while(true){
			/*获取与当前树距离最近的边，其终点为最近的顶点
			 * 起点为最近顶点的父节点
			 */
			Edge E = Utilities.minNextEdge(G, V);
			//如果边为空，函数返回
			if(E == null) break;
			System.out.println(E.get_v1() + " -- " + E.get_v2() + " -- " + G.getEdgeWt(E));

			// E的终点v被访问过了
			int v = E.get_v2();
			G.setMark(v, 1);
			// 更新与v相连的所有边的距离（松弛过程）
			for(Edge w = G.firstEdge(v); G.isEdge(w); w = G.nextEdge(w)){
				if(D[G.edge_v2(w)] > (D[v] + G.getEdgeWt(w))){
					// 更新最短距离
					D[G.edge_v2(w)] = D[v] + G.getEdgeWt(w);
					// 更新父节点
					V[G.edge_v2(w)] = v;
				}
			}
		}
		
		// 根据V数组建立最短路径树SPT
		mst.addChild(s, s, new ElemItem<Double>(D[0]));
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
	
	/*
	 * 基于优先队列的Dijkstra算法，类似于基于有线队列的Prim算法
	 */
	public void DijkstraPQ(int s){
		if(s < 0) return;
		// 图顶点和图边个数
		int nv = G.get_nv();
		int ne = G.get_ne();
		// 堆最大为边的条数
		MaxHeap H = new MaxHeap(ne);
		
		// 初始化
		for(int i = 0; i < nv; i++){
			D[i] = Integer.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- 每添加到树中
		}

		// 对起点s，父节点为-1，距离为0
		V[s] = -1;
		D[s] = 0;
		G.setMark(s, 1);
		
		// 初始化堆，将与起点相连的边都添加到堆中
		for(Edge E = G.firstEdge(s); G.isEdge(E); E = G.nextEdge(E)){
			D[E.get_v2()] = G.getEdgeWt(E);
			V[E.get_v2()] = s;
			H.insert(new ElemItem<EdgeElem>(new EdgeElem(E, -1 * G.getEdgeWt(E))));
		}
		
		// 将堆顶元素删去并返回
		int v = -1; EdgeElem PE = null;
		while(true){
			v = -1;
			// 如果堆不为空
			while(H.topVal() != null){
				// 删除并返回堆顶元素
				PE = (EdgeElem)(H.removeMax().elem);
				v = PE.get_v2();
				// 如果堆顶元素对应的顶点没有被访问，则退出循环
				if(G.getMark(v) == 0) break;
				// 否则表示没有找到下一个可添加到MST的顶点
				else v = -1;
			}
			
			// 如果没有可继续添加的顶点了，函数返回
			if(v == -1) break;
			
			// 将得到的堆顶元素对应顶点重置为访问过
			G.setMark(v, 1);
			System.out.println(PE.get_v1() + " -- " + PE.get_v2() + " -- " + PE.get_wt());
			
			// 继续将与v相连的、未经访问的边添加到堆中
			for(Edge w = G.firstEdge(v); G.isEdge(w); w = G.nextEdge(w)){
				if(D[G.edge_v2(w)] > (D[v] + G.getEdgeWt(w))){
					D[G.edge_v2(w)] = (D[v] + G.getEdgeWt(w));
					V[G.edge_v2(w)] = v;
					H.insert(new ElemItem<EdgeElem>(new EdgeElem(w, -1 * (int)D[G.edge_v2(w)])));
				}
			}
			
		}
		
		// 根据V数组建立最短路径树SPT
		mst.addChild(s, s, new ElemItem<Double>(D[0]));
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
				if(V_idx[i] == 0 && V_idx[V[i]] == 1 && 
						mst.addChild(V[i], i, new ElemItem<Double>(D[i]))){
						V_idx[i] = 1;
						f = 1;
				}
			}
			// 一次都没有添加，结束循环
			if(f == -1) break;
		}
	}
	

}
