/*
 * Created on 2010-7-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import NormalSort.ITEM;
import NormalSort.Sort;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Utilities {
	////////////////////////////////////////////////////////////////////////////////
	//   utilities			//
	////////////////////////////////////////////////////////////////////////////////
	/*
	 * 在图G中寻找与当前生成树最近的点v，函数返回的是v和当前
	 * 生成树中某个点形成的交叉边，该交叉边是当前生成树中顶点集
	 * 与其余顶点形成的交叉边种权最小的一个。
	 * 如果V[i] != -2表示该顶点i在当前的生成树中。
	 */
	public static Edge minNextEdge(GraphLnk G, int V[]){
		int len = V.length;
		//最小的权值大小
		double _mind = Double.MAX_VALUE;
		// 待返回的交叉便
		Edge _4ret = null;
		// 求从当前MST中的每个顶点出发的所有交叉边的的权值的最小值
		for(int i = 0; i < len; i++){
			// 对MST中的每个顶点
			if(G.getMark(i) == 1){
				// 对该顶点相连的所有顶点
				for(Edge E = G.firstEdge(i); G.isEdge(E); E = G.nextEdge(E)){
					// 如果该顶点尚未访问，将其权值与_mind相比
					if( G.getMark(E.get_v2()) == 0 && G.getEdgeWt(E) < _mind){
						//如果更小，更新_mind等
						_mind = G.getEdgeWt(E);
						_4ret = E;
					}
				}
			}
		}
		// 返回找到的权值最小的边
		return _4ret;
	}
	
	/*
	 * 获取图中所有边，并将这些边按照权值由大到小排序；
	 * 函数输入为图G，返回为ITEM数组，每个元素为EdgeElem
	 * 对象，元素按照权值排序；
	 */
	public static ITEM[] GetEdgeSort(Graph G){
		if(G == null) return null;
		// 首先将所有边存至ITEM数组
		int ne = G.get_ne();
		int nv = G.get_nv();
		ITEM[] E = new ITEM[ne];
		int edge_cnt = 0;
		for(int i = 0; i < nv; i++){
			for(Edge e = G.firstEdge(i); G.isEdge(e); e = G.nextEdge(e)){
				E[edge_cnt++] = new ITEM(new EdgeElem(e, G.getEdgeWt(e)));
			}
		}
		// 将ITEM数组排序，这里采用快速排序
		Sort st = new Sort();
		st.quicksort(E, 0, E.length - 1);
		// 返回ITEm数组
		return E;
	}
	
	
	public static EdgeElem[] GetEdge(Graph G){
		if(G == null) return null;
		// 首先将所有边存至ITEM数组
		int ne = G.get_ne();
		int nv = G.get_nv();
		EdgeElem[] E = new EdgeElem[ne];
		int edge_cnt = 0;
		for(int i = 0; i < nv; i++){
			for(Edge e = G.firstEdge(i); G.isEdge(e); e = G.nextEdge(e)){
				E[edge_cnt++] = new EdgeElem(e, G.getEdgeWt(e));
			}
		}
		// 返回EdgeElem数组
		return E;
	}
}
