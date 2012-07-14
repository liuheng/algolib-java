/*
 * Created on 2010-7-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package FlowNetwork;

import Element.ElemItem;
import NormalSort.intPQi;

/**
 * @author Wei LU
 * Ford-Fulkerson, 算法
 *
 * 
 */
public class NetworkMaxFlow {
	private Network G;
	private int s, t;
	private ElemItem[] wt;
	private NetworkEdge[] st;
	
	/**
	 * 构造函数
	 * @param G	待求解的网络
	 * @param s	起点
	 * @param t 终点
	 */
	public NetworkMaxFlow(Network G, int s, int t){
		this.G = G;
		this.s = s; 
		this.t = t;
		wt = new ElemItem[G.get_nv()];
		st = new NetworkEdge[G.get_nv()];
		
	}
	/**
	 * 获取边st[v]的终点
	 * @param v	起点
	 * @return	边st[v]的终点
	 */
	private int ST(int v){
		return st[v].other(v);
		
	}
	
	/**
	 * 增广网络；
	 * 函数
	 */
	private void augument(){
		int d = G.capRto(st[t], t);
		for(int v = ST(t); v != s; v = ST(v)){
			int tt = G.capRto(st[v], v);
			if(G.capRto(st[v], v) < d)
				d = G.capRto(st[v], v);
		}
		
		G.addflowRto(st[t], t, d);
		for(int v = ST(t); v != s; v = ST(v))
			G.addflowRto(st[v], v, d);
		
		//System.out.println(G.getEdgeFlow(st[t]));
	}
	
	/**
	 * 优先级优先遍历函数；
	 * 函数搜索网络起点s至终点t的最大流路径。
	 * @return	如果
	 */
	private boolean PFS(){
		int M = -1 * Integer.MAX_VALUE;
		// 基于下标堆得优先队列
		intPQi pQ = new intPQi(wt, 1);
		for(int v = 0; v < G.get_nv(); v++){
			wt[v] = new ElemItem<Integer>(0);
			st[v] = null;
			pQ.insert(v);
		}
		// 起点s置于优先队列顶部
		wt[s] = new ElemItem<Integer>(M);
		pQ.lower(s);
		
		// 迭代过程，寻找流量最大的路径
		while(!pQ.empty()){
			// 堆顶顶点号
			int v = pQ.getmax();
			wt[v] = new ElemItem<Integer>(M);
			// v到达终点或者st[v]为空则推出迭代
			if(v == t) break;
			if(v != s && st[v] == null) break;
			// 更新v的所有相邻顶点在扩充路径上的流
			for(NetworkEdge E = G.firstEdge(v); G.isEdge(E); E = G.nextEdge(E)){
				NetworkEdge TmpE = E;
				// 如果E的容量为负，则将E更新为E的反向边
				if(G.getEdgeC(E) < 0){
					E = G.getNetworkEdge(E.get_v2(), E.get_v1());
				}
				if(E == null) return false;
				// 获取E的另一顶点w
				int w = E.other(v);
				// 获取顶点w在扩充路径上的流
				int cap = G.capRto(E, w);
				int wt_v = ((Integer)(wt[v].getElem())).intValue();
				int P = cap < (-1 * wt_v)?cap:(-1 * wt_v);
				int wt_w = ((Integer)(wt[w].getElem())).intValue();
				if(cap > 0 && (-1 * P) < wt_w){
					// 更新顶点w在扩充路径上的流
					wt[w] = new ElemItem<Integer>(-1 * P);
					// 更新优先队列
					pQ.lower(w);
					st[w] = E;
				}
				E = TmpE;
			}
		}
		return st[t] != null;
	}
	
	/**
	 * 基于PSF算法的Ford Fulkerson算法。
	 * “最大容量扩充路径”最大流算法
	 */
	public void Ford_Fulkerson(){
		// 迭代
		while(PFS()){ 
			augument();
			// 打印当前网络各边的网络流
			for(int i = 0; i < G.get_nv(); i++){
				for(NetworkEdge E = G.firstEdge(i); G.isEdge(E); E = G.nextEdge(E)){
					if(G.getEdgeFlow(E) > 0)
						System.out.print(E.get_v1() + " <-- " + G.getEdgeC(E) + "/" +G.getEdgeFlow(E) + " --> " + E.get_v2() + " ||\t");
				}
				System.out.println();
			}
			
		}
	}
	
	
}
