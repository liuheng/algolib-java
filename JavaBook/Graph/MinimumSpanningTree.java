/*
 * Created on 2010-6-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import Heap.MaxHeap;
import NormalSort.ITEM;
import NormalSort.Sort;
import Set.UnionFind;
import Tree.LcRsTree;

/**
 * @author Wei LU
 *
 * 最小生成树，最小支撑树
 */
public class MinimumSpanningTree {
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
	
	//构造函数
	public MinimumSpanningTree(GraphLnk G){
		this.G = G;
		// 根据G的节点数创建数组
		V = new int[G.get_nv()];
		D = new double[G.get_nv()];
		// 广义树，大小为G节点数加1，多1个根节点
		mst = new LcRsTree(G.get_nv() + 1);
		// 
		R = new EdgeElem[G.get_ne()];
	}
	
	/*Prim算法，求解图G的最小生成树
	 * 输入s：最小生成树的树根
	 * 函数返回时V，D，mst都重新赋值
	 */
	public void Prim(int s){
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
		//将起点添加到广义树中
		mst.addChild(0, s, new ElemItem<Double>(D[s]));

		/*在其余顶点中找到与当前MST最近的顶点v，并将顶点的父节点和
		 * 顶点v添加到MST中。其中图的权值存放在节点v中。
		 * 循环迭代，直至所有顶点都遍历一遍
		 */
		while(true){
			/*获取与当前树距离最近的边，其终点为最近的顶点
			 * 终点为最近顶点的父节点
			 */
			Edge E = Utilities.minNextEdge(G, V);
			//如果边为空，函数返回
			if(E == null) return;
			System.out.println(E.get_v1() + " -- " + E.get_v2() + " -- " + G.getEdgeWt(E));
			// E的起点赋值给V[E的终点]
			V[E.get_v2()] = E.get_v1();
			// E的权值赋值给D[E的终点]
			D[E.get_v2()] = G.getEdgeWt(E);
			// E的终点被访问过了
			G.setMark(E.get_v2(), 1);
			// 在最小生成树中添加边E对应的信息
			mst.addChild(E.get_v1(), E.get_v2(),
					new ElemItem<Double>(D[E.get_v2()]));
		}
		
	}
	
	
	
	/*
	 * 基于有线队列的Prim算法；
	 * 需要利用最小堆结构，但是我们之前只设计了最大堆，所以这里将堆节点中用于表示
	 * 大小关系的元素值乘以 -1，这样的最大堆作用等效于最小堆；
	 * 堆节点元素为 EdgeElem。
	 */
	public void PrimPQ(int s){
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
		//将起点添加到广义树中
		mst.addChild(0, s, new ElemItem<Double>(D[s]));
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
			if(v == -1) return;
			
			// 将得到的堆顶元素对应顶点重置为访问过
			G.setMark(v, 1);
			
			// 在最小生成树中添加边E对应的信息
			mst.addChild(PE.get_v1(), PE.get_v2(),
					new ElemItem<Double>(D[PE.get_v2()]));
			
			// 继续将与v相连的、未经访问的边添加到堆中
			for(Edge w = G.firstEdge(v); G.isEdge(w); w = G.nextEdge(w)){
				if(G.getMark(w.get_v2()) == 0 && D[G.edge_v2(w)] > G.getEdgeWt(w)){
					D[G.edge_v2(w)] = G.getEdgeWt(w);
					V[G.edge_v2(w)] = v;
					H.insert(new ElemItem<EdgeElem>(new EdgeElem(w, -1 * G.getEdgeWt(w))));
				}
			}
			
		}
	}
	
	
	
	
	/*
	 * Krustral算法获取最小支撑树；
	 */
	public void Krustral(){
		int nv = G.get_nv();
		int ne = G.get_ne();
		// 初始化
		for(int i = 0; i < nv; i++){
			D[i] = Integer.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- 每添加到树中
		}
		// 获取图上的每一条边，并将边按照权值由大到小排序
		ITEM[] E = Utilities.GetEdgeSort(G);
		// 集合形式的等价类
		UnionFind UF = new UnionFind(nv);
		// 待返回的EdgeElem数组
		R = new EdgeElem[nv]; int r = 0;
		for(int i = 0, k = 1; i < ne && k < nv; i++){
			// 获取一条边
			EdgeElem pe = (EdgeElem)(E[i].getElem());
			int v1 = pe.get_v1();
			int v2 = pe.get_v2();
			// 如果这条边的两个顶点不在同一个等价类中
			if(UF.find(v1) != UF.find(v2)){
				// 则将这两个顶点合并，
				UF.union(v1, v2);
				// 并将这条边添加到EdgeElem数组中
				R[r++] = pe;
				System.out.print(R[r - 1].get_v1() + " <- (" + 
						R[r - 1].get_wt() +
						") -> " + 
						R[r - 1].get_v2() + "\t|");
				System.out.println("\n=================");
			}
		}
	}
	
	/**
	 * Boruvka 算法求解最小支撑树
	 **/
	public void Boruvka(){
		int nv = G.get_nv();
		int ne = G.get_ne();
		// 初始化
		for(int i = 0; i < nv; i++){
			D[i] = Integer.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- 每添加到树中
		}
		// 获取图上的每一条边
		EdgeElem[] E = Utilities.GetEdge(G);
		EdgeElem[] B = new EdgeElem[nv];
		// 集合形式的等价类
		UnionFind UF = new UnionFind(nv);
		// 待返回的EdgeElem数组
		R = new EdgeElem[nv]; int r = 0;
		int N = 0;
		// 权值为无穷大的边
		EdgeElem _z = new EdgeElem(null, Integer.MAX_VALUE);
		// 对每一个子树
		for(int e = ne; e != 0; e = N){
			int h, i, j;
			// 权值初始化为 oo
			for(int t = 0; t < nv; t++) B[t] = _z;
			// 对每一条边
			for(h = 0, N = 0; h < e; h++){
				EdgeElem pe = E[h];
				// 获取边的起点和终点
				i = UF.find(pe.get_v1());
				j = UF.find(pe.get_v2());
				// 起点和终点如果在同一个等价类中，则跳出本次循环
				if(i == j) continue;
				// 更新每个节点与其它树之间的最邻近距离的边
				if(pe.get_wt() < B[i].get_wt()) B[i] = pe;
				if(pe.get_wt() < B[j].get_wt()) B[j] = pe;
				// N表示的是当前的子树个数
				E[N++] = pe;
			}
			// 对MST中每条边
			for(h = 0; h < nv; h++){
				// B[h]是第h个顶点与其它树之间的最近距离的边
				if(B[h] != _z)
					// 如果B[h]的起点和终点不在同一个等价类中
					if(UF.find(B[h].get_v1()) != UF.find(B[h].get_v2())){
						// 将起点和终点放置到同一个等价类中
						UF.union(B[h].get_v1(), B[h].get_v2());
						// 并将这条边添加到EdgeElem数组中
						R[r++] = B[h];
						System.out.print(R[r - 1].get_v1() + " <- (" + 
								R[r - 1].get_wt() +
								") -> " + 
								R[r - 1].get_v2() + "\t|");
					}
			}
			System.out.println("\n ======================================");
		}
	}
	
	
	
	
	
}
