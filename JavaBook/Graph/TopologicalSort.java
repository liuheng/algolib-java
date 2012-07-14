/*
 * Created on 2010-6-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import List.SingleLink2;

/**
 * @author Wei LU
 *
 * ÕºµƒÕÿ∆À≈≈–Ú£¨
 * TopologicalSort.java
 */
public class TopologicalSort {
	public static SingleLink2 topologocal_sort(GraphLnk g){
		GraphSearch.DFS(g);
		int n = g.get_nv();
		int[] f = new int[n];
		for(int i = 0; i < n; i++){
			f[i] = GraphSearch.f[i];
		}
		SingleLink2 slk = new SingleLink2();
		for(int i = 0; i < n; i++){
			int min = f[0], min_idx = 0;
			for(int j = 1; j < n; j++)
				if(f[j] < min){
					min = f[j];
					min_idx = j;
				}
			f[min_idx] = Integer.MAX_VALUE;
			slk.append(new ElemItem<Integer>(min_idx));
		}
		
		return slk;
		
	}
}
