/*
 * Created on 2010-7-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package FlowNetwork;

/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetworkExample {
	public static void main(String args[]){
		Network N = new Network(6);
		N.setEdgeC(0, 1, 2);
		N.setEdgeC(0, 2, 3);
		N.setEdgeC(1, 3, 3);
		N.setEdgeC(1, 4, 1);
		N.setEdgeC(2, 3, 1);
		N.setEdgeC(2, 4, 1);
		N.setEdgeC(3, 5, 2);
		N.setEdgeC(4, 5, 3);
		
		
		N.setEdgeC(1, 0, -1);
		N.setEdgeC(2, 0, -1);
		N.setEdgeC(3, 1, -1);
		N.setEdgeC(4, 1, -1);
		N.setEdgeC(3, 2, -1);
		N.setEdgeC(4, 2, -1);
		N.setEdgeC(5, 3, -1);
		N.setEdgeC(5, 4, -1);
		
		NetworkMaxFlow NF = new NetworkMaxFlow(N, 0, 5);
		NF.Ford_Fulkerson();
	}
}
