/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import List.SingleLink2;
import Queue.LinkQueue;
import Stack.LinkStack;

/**
 * @author Wei LU
 *
 * 图的广度优先、深度优先搜索
 * GraphSearch.java
 */
public class GraphSearch {
	public enum COLOR { 
		WHITE, GRAY, BLACK 
	}; 
		
	static COLOR color[];//  = new COLOR[n];
	static int   parent[];// = new int[n];//节点的父节点，非负整数
	static int   d[];// 	   = new int[n];//深度depth	
	static int   f[];
	static int   ord[];
	static int	 time;
	
	/*广度优先搜索算法，从第start节点开始进行搜索*/
	static void BFS(GraphLnk G, int start){
		int n = G.get_nv();
		if(start < 0 || start >= n) return;

		color = new COLOR[n];
		parent = new int[n];//节点的父节点，非负整数
		d 	   = new int[n];//深度depth	
		for(int i = 0; i < n; i++){
			//将所有顶点的颜色着色为白色
			color[i] = COLOR.WHITE;
			//所有顶点的父母初始化为-1
			parent[i] = -1;
			//所有顶点的深度初始化为无穷大
			d[i] = Integer.MAX_VALUE;
		}
		color[start] = COLOR.GRAY;
		parent[start] = -1;
		d[start] = 0;
		
		LinkQueue Q = new LinkQueue();
		Q.enqueue(new ElemItem<Integer>(start));
		while(Q.currSize() > 0){
			int u = ((Integer)(Q.dequeue().elem)).intValue();
			for(Edge w = G.firstEdge(u); G.isEdge(w); w = G.nextEdge(w)){
				if(color[w.get_v2()] == COLOR.WHITE){
					color[w.get_v2()] = COLOR.GRAY;
					d[w.get_v2()] = d[u] + 1;
					parent[w.get_v2()] = u;
					Q.enqueue(new ElemItem<Integer>(w.get_v2()));
				}
			}
			color[u] = COLOR.BLACK;	
			for(int i = 0; i < n; i++){
				System.out.print(i + ")" + color[i] + "\t");
			}
			System.out.println();
		}
		System.out.println("各顶点的深度为：");
		for(int i = 0; i < n; i++){
			System.out.print(d[i] + "\t");
		}
		System.out.println("\n各顶点的父母为：");
		for(int i = 0; i < n; i++){
			System.out.print(parent[i] + "\t");
		}
		System.out.println();
	}
	
	/*深度优先搜索算法，从第start个节点开始搜索*/
	static void DFS(GraphLnk G, int start){
		int n = G.get_nv();
		time = 0;//时间戳更新计数器
		if(start < 0 || start >= n) return;
		
		color  = new COLOR[n];
		parent = new int[n];//节点的父节点，非负整数
		d 	   = new int[n];//第一个时间戳
		f	   = new int[n];//第二个时间戳
		ord	   = new int[n];//每个节点的访问次序
		
		for(int i = 0; i < n; i++){
			//将所有顶点的颜色着色为白色
			color[i] = COLOR.WHITE;
			//所有顶点的父母初始化为-1
			parent[i] = -1;
			//所有顶点的深度初始化为无穷大
			d[i] = Integer.MAX_VALUE;
			f[i] = Integer.MAX_VALUE;
			//节点个访问次序都初始设为-1
			ord[i] = -1;
		}
		//调用递归函数，进行遍历访问
		DFS_VISIT(G, start);
	}
	
	//函数重载，迭代地将图中每个顶点作为起点进行深度优先遍历
	static void DFS(GraphLnk G){
		int n = G.get_nv();
		time = 0;//时间戳更新计数器
		color  = new COLOR[n];
		parent = new int[n];//节点的父节点，非负整数
		d 	   = new int[n];//第一个时间戳
		f	   = new int[n];//第二个时间戳
		ord	   = new int[n];//每个节点的访问次序
		for(int i = 0; i < n; i++){
			//将所有顶点的颜色着色为白色
			color[i] = COLOR.WHITE;
			//所有顶点的父母初始化为-1
			parent[i] = -1;
			//所有顶点的时间戳初始化为无穷大
			d[i] = Integer.MAX_VALUE;
			f[i] = Integer.MAX_VALUE;
			//访问次序初始化为-1
			ord[i] = -1;
		}
		//将每一个顶点设为遍历起始点然后遍历，
		//避免在有向图中遍历不全的情况发生
		for(int s = 0; s < n; s++)
			if(color[s] == COLOR.WHITE)
				DFS_VISIT(G, s);
	}
	
	/*深度优先搜索算法中调用的递归函数，
	 * 实现图的递归遍历*/
	static int k = 0;
	public static void DFS_VISIT(Graph G, int u){
		color[u] = COLOR.GRAY;
		time++;
		d[u] = time;
		
		for(int i = 0; i < G.get_nv(); i++){
			System.out.print(i + ")" + color[i] + "\t");
		}
		System.out.println();
		for(int i = 0; i < G.get_nv(); i++){
			if(d[i] < Integer.MAX_VALUE && f[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/" + f[i] + "\t");
			else if(d[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/"  + "\t");
			else
				System.out.print(i + ")" + " " + "/" + " " + "\t");
		}
		System.out.println();
		
		for(Edge w = G.firstEdge(u); G.isEdge(w); w = G.nextEdge(w)){
			if(color[w.get_v2()] == COLOR.WHITE){
				parent[w.get_v2()] = u;
				DFS_VISIT(G, w.get_v2());
			}
		}
		
		color[u] = COLOR.BLACK;
		
		f[u] = ++time;
		ord[u] = k++;
		for(int i = 0; i < G.get_nv(); i++){
			System.out.print(i + ")" + color[i] + "\t");
		}
		System.out.println();
		for(int i = 0; i < G.get_nv(); i++){
			if(d[i] < Integer.MAX_VALUE && f[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/" + f[i] + "\t");
			else if(d[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/"  + "\t");
			else
				System.out.print(i + ")" + " " + "/" + " " + "\t");
		}
		System.out.println();
	}
	
	/*根据节点的遍历先后次序，获取节点新的访问次序
	 * 在函数返回时，new_ord[i]的意义是：
	 * 原图上第i个节点的新的访问次序为new_ord[i]；
	 * 其规则是：后访问的节点在新的访问次序中先访问*/
	public static void newordermap(int[] ord, int[] new_ord){
		int ord_temp[] = new int[ord.length];
		int max, max_idx;
		for(int i = 0; i < ord.length; i++) ord_temp[i] = ord[i];
 		for(int i = 0; i < ord.length; i++){
			max_idx = 0; max = ord_temp[max_idx];
			for(int j = 0; j < ord.length; j++){
				if(ord_temp[j] == -1) continue;
				if(ord_temp[j] > max){
					max = ord_temp[j];
					max_idx = j;
				}
			}
			ord_temp[max_idx] = -1;
			new_ord[i] = max_idx;
		}
	}
	
	/*将根据反向图GT和每个节点在原图G深度遍历时的离开时间数组，
	 * 生成链表slk表示的连通分支
	 * 本函数不改变图的连接关系，只是节点的访问次序有所调整*/
	public static void KosarajuDFS(GraphLnk GT, int ord[], SingleLink2 slk){
		/*根据ord数组计算new_order数组，新的访问顺序为：
		 * 第i次访问的节点为原图上的第new_order[i]个节点*/
		int new_order[] = new int[ord.length];
		//调用函数newordermap改变数组的次序
		newordermap(ord, new_order);
		int n = GT.get_nv();
		//这里只需要记录颜色，其它信息不重要了
		color  = new COLOR[n];
		//颜色初始化为白色
		for(int i = 0; i < n; i++) color[i] = COLOR.WHITE;
		//为找到图中所有的联通区域，循环迭代：
		for(int i = 0; i < new_order.length; i++){
			//第i次访问的节点为原图上的第new_order[i]个节点
			int  j= new_order[i];
			if(color[j] != COLOR.WHITE) continue;
			//创建一个图节点链表，表示一个联通区域
			GNodeSingleLink gnsk = new GNodeSingleLink();
			//调用递归函数，以j为起点深度搜索该图
			KosarajuDFSVISIT(GT, ord, j, gnsk);
			/*将联通区的节点形成的链表添加到联通区域链表中，
			 * 这里使用的实际上是二维链表*/
			slk.append(new ElemItem<GNodeSingleLink>(gnsk));
		}
		
	}
	
	/*递归函数，起点为u深度搜索图G，节点递归地调用该函数时，节点的访问顺序
	 * 由ord决定，对节点u与之相连且标记为白色的的节点v1,v2,...
	 * 先访问ord[vi]最大的节点vi.当没有与节点u相连的节点或者与u相连的所有
	 * 节点都不是白色的了，此时获得一个联通区域，函数返回
	 */
	public static void KosarajuDFSVISIT(GraphLnk G, int ord[], 
						 int u, GNodeSingleLink slk){
		//访问该节点，将其颜色标记为灰色
		color[u] = COLOR.GRAY;
		//将该节点u添加到当前的联通区域中
		slk.append(new ElemItem<Integer>(u));
		//首先统计与该节点相连的、颜色为白色的节点的个数
		int cnt = 0;
		for(Edge w = G.firstEdge(u); 
				G.isEdge(w); w = G.nextEdge(w)){
			if(color[w.get_v2()] == COLOR.WHITE)
				cnt++;
		}
		//如果此时没有与该节点相连并且颜色为白色的节点，函数返回
		if(cnt == 0) return;
		//否则，将与该节点相连的、白色节点暂存至数组e中
		Edge e[] = new Edge[cnt];
		cnt = 0;
		for(Edge w = G.firstEdge(u); G.isEdge(w); w = G.nextEdge(w)){
			if(color[w.get_v2()] == COLOR.WHITE)
				e[cnt++] = w;
		}
		/*对数组e按照边的终点的访问次序ord[..]进行排序
		 * 这里采用选择排序来完成这一过程 */
		int max_idx;
		for(int i = 0; i < e.length - 1; i++){
			//第i轮找第i大的元素
			max_idx = i;
			for(int j = i + 1; j < e.length; j++){
				if(ord[e[j].get_v2()] > ord[e[max_idx].get_v2()]){
					max_idx = j;
				}
			}
			//如果原先第i位置上不是最大的，则交换操作
			if(max_idx != i){
				Edge t = e[i];
				e[i] = e[max_idx];
				e[max_idx] = t;
			}
		}
		//对排序后的边逐个进行递归调用
		for(int i = 0; i < e.length; i++)
			KosarajuDFSVISIT(G, ord, e[i].get_v2(), slk);
	}
	
	/*Trajan算法的递归DFS函数
	 * 函数入参
	 */
	public static void TrajanDFS(GraphLnk G, int w, int pre[], int low[], int cnt[], LinkStack ls, SingleLink2 slk){
		int t , min = low[w] = pre[w] = cnt[0]++;
		// 将当前顶点号压栈
		ls.push(new ElemItem<Integer>(w));
		// 对当前顶点的每个邻点循环
		for(Edge e = G.firstEdge(w); G.isEdge(e); e = G.nextEdge(e)){
			//如果邻点没有遍历过，则对其递归调用
			if(pre[e.get_v2()] == -1){
				TrajanDFS(G, e.get_v2(), pre, low, cnt, ls, slk);
			}
			/*如果邻点已经被遍历过了，比较其编号与min,
			 * 如果编号较小，则更新min的大小*/
			if(low[e.get_v2()] < min) min = low[e.get_v2()];
		}
		//如果此时min小于low[w]，则返回
		if(min < low[w]){
			low[w] = min;
			return;
		}
		//否则，弹出栈中元素，并将元素添加到链表中
		GNodeSingleLink gnslk = new GNodeSingleLink();
		do{
			//弹出栈顶元素
			t = ((Integer)(ls.pop().elem)).intValue();
			low[t] = G.get_nv();
			//添加到链表中
			gnslk.append(new ElemItem<Integer>(t));
		}while(t != w);
		//将该链表添加到slk链表中
		slk.append(new ElemItem<GNodeSingleLink>(gnslk));
	}
	
	/*Trajan算法的递归DFS函数
	 * 函数入参
	 */
	public static void GabowDFS(GraphLnk G, int w, int pre[], int low[], int[] id, 
						int cnt[], LinkStack ls, LinkStack P, SingleLink2 slk){
		int v;
		pre[w] = cnt[0]++;
		//将当前顶点号压栈
		ls.push(new ElemItem<Integer>(w));
		P.push(new ElemItem<Integer>(w));
		
		// 对当前顶点的每个邻点循环
		for(Edge e = G.firstEdge(w); G.isEdge(e); e = G.nextEdge(e)){
			//如果邻点没有遍历过，则对其递归调用
			if(pre[e.get_v2()] == -1){
				GabowDFS(G, e.get_v2(), pre, low, id, cnt, ls, P, slk);
			}
			//否则，如果邻点被遍历过但又没有被之前的连通域包含，则循环弹出
			else if(id[e.get_v2()] == -1){
				int ptop = ((Integer)(P.getTop().elem)).intValue();
				//循环弹出，直到栈顶顶点的序号不小于邻点的序号
				while(pre[ptop] > pre[e.get_v2()]) {
					P.pop();
					ptop = ((Integer)(P.getTop().elem)).intValue();
				}
			}
		}
		if(P.getTop() != null && ((Integer)(P.getTop().elem)).intValue() == w)
			P.pop();
		else return;
		GNodeSingleLink gnslk = new GNodeSingleLink();
		do{
			v = ((Integer)(ls.pop().elem)).intValue();
			id[v] = 1;
			gnslk.append(new ElemItem<Integer>(v));
		}while(v != w);
		slk.append(new ElemItem<GNodeSingleLink>(gnslk));
		
	}
}
