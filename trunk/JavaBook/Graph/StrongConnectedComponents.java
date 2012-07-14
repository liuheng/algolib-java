/*
 * Created on 2010-6-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import List.SingleLink2;
import Stack.LinkStack;

/**
 * @author Wei LU
 *
 * 强连通分支类，
 * 
 */
public class StrongConnectedComponents {
	/*算法1：Kosaraju,算法步骤：
	 * 1. 对原始图G深度优先搜索，获得每个节点的遍历次序ord[]；
	 * 2. 创建原始图的反向图GT；
	 * 3. 按照ord[]相反的顺序访问GT中每个节点；
	 * 函数最终返回一个二维单链表slk，单链表每个节点又是一个单链表，
	 * 每个节点处的单链表表示一个联通区域；
	 * slk的长度代表了图中联通区域的个数。*/
	public static SingleLink2 Kosaraju(GraphLnk G){
		SingleLink2 slk = new SingleLink2();
		int ord[] = new int[G.get_nv()];
		//对原图进行深度优先搜索
		GraphSearch.DFS(G);
		//拷贝图G的深度优先遍历时每个节点的离开时间
		for(int i = 0; i < GraphSearch.f.length; i++){
			ord[i] = GraphSearch.ord[i];
			System.out.print(GraphSearch.parent[i] + " || ");
		}
		System.out.println();
		//构造G的反向图GT
		GraphLnk GT = reverseGraph(G);
		/*用针对Kosaraju算法而设计DFS算法KosarajuDFS函数
		 * 该函数按照ord的逆向顺序访问每个节点，
		 * 并向slk中添加新的链表元素；*/
		GraphSearch.KosarajuDFS(GT, ord, slk);
		//打印所有的联通区域
		for(slk.goFirst(); slk.getCurrVal() != null; slk.next()){
			//获取一个链表元素项，即一个联通区域
			GNodeSingleLink comp_i = 
					(GNodeSingleLink)(slk.getCurrVal().elem);
			//打印这个联通区域的每个图节点
			for(comp_i.goFirst(); 
				comp_i.getCurrVal() != null; comp_i.next()){
				System.out.print(comp_i.getCurrVal().elem + "\t");
			}
			System.out.println();
		}
		//返回联通区域链表
		return slk;
	}
	
	/*Trajan 算法实现图的强连通区域查找；
	 * 输入为图结构，算法步骤：
	 * 
	 * 函数最终返回一个二维单链表slk，单链表每个节点又是一个单链表，
	 * 每个节点处的单链表表示一个联通区域；
	 * slk的长度代表了图中联通区域的个数。
	 */
	public static SingleLink2 Trajan(GraphLnk G){
		SingleLink2 slk = new SingleLink2();
		LinkStack ls = new LinkStack();
		int pre[] = new int[G.get_nv()];
		int low[] = new int[G.get_nv()];
		int cnt[] = new int[1];
		for(int i = 0; i < G.get_nv(); i++){
			pre[i] = -1;
			low[i] = -1;
		}
		for(int i = 0; i < G.get_nv(); i++){
			if(pre[i] == -1) {
				//GNodeSingleLink gnslk = new GNodeSingleLink();
				GraphSearch.TrajanDFS(G, i, pre, low, cnt, ls, slk);
				//slk.append(new ElemItem<GNodeSingleLink>(gnslk));
			}
		}
		
		//打印所有的联通区域
		for(slk.goFirst(); slk.getCurrVal() != null; slk.next()){
			//获取一个链表元素项，即一个联通区域
			GNodeSingleLink comp_i = 
					(GNodeSingleLink)(slk.getCurrVal().elem);
			//打印这个联通区域的每个图节点
			for(comp_i.goFirst(); 
				comp_i.getCurrVal() != null; comp_i.next()){
				System.out.print(comp_i.getCurrVal().elem + "\t");
			}
			System.out.println();
		}
		return slk;
	}
	
	/* Gabow 算法实现图的强连通区域查找；
	 * 输入为图结构，算法步骤：
	 * 
	 * 函数最终返回一个二维单链表slk，单链表每个节点又是一个单链表，
	 * 每个节点处的单链表表示一个联通区域；
	 * slk的长度代表了图中联通区域的个数。
	 */
	public static SingleLink2 Gabow(GraphLnk G){
		SingleLink2 slk = new SingleLink2();
		LinkStack ls = new LinkStack();
		LinkStack P = new LinkStack();
		int pre[] = new int[G.get_nv()];
		int low[] = new int[G.get_nv()];
		int cnt[] = new int[1];
		int id[]  = new int[G.get_nv()];
		for(int i = 0; i < G.get_nv(); i++){
			pre[i] = -1;
			low[i] = -1;
			id[i] = -1;
		}
		for(int i = 0; i < G.get_nv(); i++){
			if(pre[i] == -1) {
				//GNodeSingleLink gnslk = new GNodeSingleLink();
				GraphSearch.GabowDFS(G, i, pre, low, id, cnt, ls, P, slk);
				//slk.append(new ElemItem<GNodeSingleLink>(gnslk));
			}
		}
		
		//打印所有的联通区域
		for(slk.goFirst(); slk.getCurrVal() != null; slk.next()){
			//获取一个链表元素项，即一个联通区域
			GNodeSingleLink comp_i = 
					(GNodeSingleLink)(slk.getCurrVal().elem);
			//打印这个联通区域的每个图节点
			for(comp_i.goFirst(); 
				comp_i.getCurrVal() != null; comp_i.next()){
				System.out.print(comp_i.getCurrVal().elem + "\t");
			}
			System.out.println();
		}
		return slk;
	}
	

	
	//创建一个与入参G每条边方向相反的图
	public static GraphLnk reverseGraph(GraphLnk G){
		GraphLnk GT = new GraphLnk(G.get_nv());
		for(int i = 0; i < G.get_nv(); i++){
			//GT每条边的方向与G的方向相反
			for(Edge w = G.firstEdge(i); G.isEdge(w); w = G.nextEdge(w)){
				GT.setEdgeWt(w.get_v2(), w.get_v1(),G.getEdgeWt(w));
			}
		}
		return GT;
	}

}
