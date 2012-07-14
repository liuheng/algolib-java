/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;


/**
 * @author Wei LU
 *
 * 设计几个图结构，用于测试
 * GraphExamples.java
 */
public class GraphExamplesBasic {
	public static GraphLnk g, g2, g3, g4, g5, g6, g7, g8, g9;
	public GraphExamplesBasic(){
		g = new GraphLnk(8);
		g2 = new GraphLnk(6);
		g3 = new GraphLnk(7);
		g4 = new GraphLnk(9);
		g5 = new GraphLnk(8);
		g6 = new GraphLnk(10);
		g7 = new GraphLnk(9);
		g8 = new GraphLnk(5);
		//g9 = new GraphLnk(6);
		
		g.setEdgeWt(0, 1, 1);
		g.setEdgeWt(0, 4, 1);
		g.setEdgeWt(1, 0, 1);
		g.setEdgeWt(1, 5, 1);
		g.setEdgeWt(2, 3, 1);
		g.setEdgeWt(2, 5, 1);
		g.setEdgeWt(2, 6, 1);
		g.setEdgeWt(3, 2, 1);
		g.setEdgeWt(3, 6, 1);
		g.setEdgeWt(3, 7, 1);
		g.setEdgeWt(4, 0, 1);
		g.setEdgeWt(5, 1, 1);
		g.setEdgeWt(5, 2, 1);
		g.setEdgeWt(5, 6, 1);
		g.setEdgeWt(6, 2, 1);
		g.setEdgeWt(6, 3, 1);
		g.setEdgeWt(6, 5, 1);
		g.setEdgeWt(6, 7, 1);
		g.setEdgeWt(7, 3, 1);
		g.setEdgeWt(7, 6, 1);
		
		g2.setEdgeWt(0, 1, 1);
		g2.setEdgeWt(0, 3, 1);
		g2.setEdgeWt(1, 4, 1);
		g2.setEdgeWt(2, 4, 1);
		g2.setEdgeWt(2, 5, 1);
		g2.setEdgeWt(3, 1, 1);
		g2.setEdgeWt(4, 3, 1);
		g2.setEdgeWt(5, 5, 1);
		
		g3.setEdgeWt(0, 1, 1);
		g3.setEdgeWt(1, 2, 1);
		g3.setEdgeWt(1, 3, 1);
		g3.setEdgeWt(1, 4, 1);
		g3.setEdgeWt(1, 5, 1);
		g3.setEdgeWt(2, 3, 1);
		g3.setEdgeWt(3, 4, 1);
		g3.setEdgeWt(4, 6, 1);
		
		g4.setEdgeWt(0, 3, 1);
		g4.setEdgeWt(0, 4, 1);
		g4.setEdgeWt(1, 4, 1);
		g4.setEdgeWt(3, 4, 1);
		g4.setEdgeWt(3, 6, 1);
		g4.setEdgeWt(5, 6, 1);
		g4.setEdgeWt(5, 7, 1);
		g4.setEdgeWt(6, 8, 1);
		g4.setEdgeWt(7, 8, 1);
		
		//算法导论，P333（CH）
		g5.setEdgeWt(0, 3, 1);
		g5.setEdgeWt(0, 4, 1);
		g5.setEdgeWt(1, 2, 1);
		g5.setEdgeWt(2, 3, 1);
		g5.setEdgeWt(3, 1, 1);
		g5.setEdgeWt(3, 4, 1);
		g5.setEdgeWt(4, 2, 1);
		g5.setEdgeWt(5, 6, 1);
		g5.setEdgeWt(5, 7, 1);
		g5.setEdgeWt(6, 0, 1);
		g5.setEdgeWt(6, 4, 1);
		g5.setEdgeWt(7, 5, 1);
		g5.setEdgeWt(7, 6, 1);
		
		//算法导论，P338(CH)
		g6.setEdgeWt(0, 2, 1);
		g6.setEdgeWt(1, 0, 1);
		g6.setEdgeWt(1, 3, 1);
		g6.setEdgeWt(2, 1, 1);
		g6.setEdgeWt(2, 3, 1);
		g6.setEdgeWt(2, 4, 1);
		g6.setEdgeWt(3, 5, 1);
		g6.setEdgeWt(4, 5, 1);
		g6.setEdgeWt(4, 6, 1);
		g6.setEdgeWt(5, 3, 1);
		g6.setEdgeWt(5, 7, 1);
		g6.setEdgeWt(6, 4, 1);
		g6.setEdgeWt(6, 7, 1);
		g6.setEdgeWt(7, 7, 1);
		g6.setEdgeWt(8, 9, 1);
		g6.setEdgeWt(9, 8, 1);
		
		//算法导论例子
		g7.setEdgeWt(0, 1, 4);
		g7.setEdgeWt(0, 7, 8);
		g7.setEdgeWt(1, 0, 4);
		g7.setEdgeWt(1, 2, 8);
		g7.setEdgeWt(2, 1, 8);
		g7.setEdgeWt(2, 3, 7);
		g7.setEdgeWt(2, 5, 4);
		g7.setEdgeWt(2, 8, 2);
		g7.setEdgeWt(3, 2, 7);
		g7.setEdgeWt(3, 4, 9);
		g7.setEdgeWt(3, 5, 14);
		g7.setEdgeWt(4, 3, 9);
		g7.setEdgeWt(4, 5, 10);
		g7.setEdgeWt(5, 2, 4);
		g7.setEdgeWt(5, 3, 14);
		g7.setEdgeWt(5, 4, 10);
		g7.setEdgeWt(5, 6, 2);
		g7.setEdgeWt(6, 5, 2);
		g7.setEdgeWt(6, 7, 1);
		g7.setEdgeWt(6, 8, 6);
		g7.setEdgeWt(7, 0, 8);
		g7.setEdgeWt(7, 6, 1);
		g7.setEdgeWt(7, 8, 7);
		g7.setEdgeWt(8, 2, 2);
		g7.setEdgeWt(8, 6, 6);
		g7.setEdgeWt(8, 7, 7);
		// algo intro P367
		g8.setEdgeWt(0, 1, 10);
		g8.setEdgeWt(0, 4, 5);
		g8.setEdgeWt(1, 2, 1);
		g8.setEdgeWt(1, 4, 2);
		g8.setEdgeWt(2, 3, 4);
		g8.setEdgeWt(3, 0, 7);
		g8.setEdgeWt(3, 2, 6);
		g8.setEdgeWt(4, 1, 3);
		g8.setEdgeWt(4, 2, 9);
		g8.setEdgeWt(4, 3, 2);
		
		// java algo P239
		/*g9.setEdgeWt(0, 1, 41);
		g9.setEdgeWt(0, 5, 29);
		g9.setEdgeWt(1, 2, 51);
		g9.setEdgeWt(1, 4, 32);
		g9.setEdgeWt(2, 3, 50);
		g9.setEdgeWt(3, 0, 45);
		g9.setEdgeWt(3, 5, 38);
		g9.setEdgeWt(4, 2, 32);
		g9.setEdgeWt(4, 3, 36);
		g9.setEdgeWt(5, 1, 29);
		g9.setEdgeWt(5, 4, 21);*/
		g9 = BuildGraph.BulidGraphFromFile("Graph\\graph9.txt");
		
	}

	
	public static void main(String args[]){
		GraphExamplesBasic G1 = new GraphExamplesBasic();
		//GraphSearch.BFS(G1.g, 1);
		//GraphSearch.DFS(g2, 0);
		//GraphSearch.DFS(g2);
		//TopologicalSort tp = new TopologicalSort();
		//SingleLink2 slk = TopologicalSort.topologocal_sort(g5);
		//slk.printList();
		//StrongConnectedComponents.Kosaraju(g6);
		//StrongConnectedComponents.Trajan(g6);
		//StrongConnectedComponents.Gabow(g6);
		
		/*
		// MST
		MinimumSpanningTree mst = new MinimumSpanningTree(g7);
		mst.Krustral();
		System.out.println("//////////////////////////////");
		mst.Boruvka();
		//for(int i = 0; i < mst.D.length; i++)
			//System.out.print(mst.V[i] +" --> " + i + " " + mst.D[i] + "\t");
		
		for(int i = 0; i < mst.D.length && mst.R[i] != null; i++){
			//System.out.print(mst.V[i] +" --> " + i + " " + mst.D[i] + "\t");
			//System.out.print(mst.R[i].get_v1() +" <--> " + mst.R[i].get_v2() +  ": " +mst.R[i].get_wt() + "\t");
		}
		System.out.println();
		//mst.mst.ford_print_tree();
		*/
		
		/*
		// SPT
		SingleSourceShortestPaths sp = new SingleSourceShortestPaths(g8);
		sp.DijkstraPQ(0);
		for(int i = 0; i < sp.V.length; i++){
			System.out.print(sp.V[i] + " <--> " + i + "\t");
		}
		System.out.println();
		sp.mst.ford_print_tree();
		*/
		
		// APSP
		AllPairsShortestPaths APSP = new AllPairsShortestPaths(g9);
		APSP.Floyd();
		for(int i = 0; i < APSP.D.length; i++){
			for(int j = 0; j < APSP.D.length; j++){
				if(APSP.D[i][j] != Double.MAX_VALUE)
					System.out.print(APSP.D[i][j] + "\t");
				else System.out.print("oo\t");
			}
			System.out.println();
		}
		System.out.println("\n////////////////////////////////////////");
		for(int i = 0; i < APSP.D.length; i++){
			for(int j = 0; j < APSP.D.length; j++){
				if(APSP.P[i][j] != null)
					System.out.print(APSP.P[i][j].get_v1() + "-->" +APSP.P[i][j].get_v2() + "\t");
				else
					System.out.print("----\t");
			}
			System.out.println();
		}
	}
}
