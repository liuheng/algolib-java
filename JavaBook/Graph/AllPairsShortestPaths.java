/*
 * Created on 2010-7-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class AllPairsShortestPaths {
	// �������ͼ
	GraphLnk G;
	// V[i][j]��ʾi���������еĸ��ڵ�
	EdgeElem P[][];
	// D[i]��ʾV[i]��i�γɵıߵ�Ȩֵ
	double D[][];
	
	// ������ʽ
	EdgeElem[] R;
	
	// ���캯��
	public AllPairsShortestPaths(GraphLnk G){
		this.G = G;
		// ����G�Ľڵ�����������
		int V = G.get_nv();
		D = new double[V][V];
		// ��ʼ��
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				D[i][j] = Double.MAX_VALUE;
		
		P = new EdgeElem[V][V];
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				if(G.isEdge(i, j)){
					// �����ӱ���ӵ�P�����У�����D����
					P[i][j] = new EdgeElem(new EdgeLnk(i, j, null), G.getEdgeWt(i, j));
					D[i][j] = G.getEdgeWt(i, j);
				}
		// ����D�Խ�Ԫ��Ϊ0
		for(int i = 0; i < V; i++) D[i][i] = 0.0;
		//  ��ӡ�м���
		for(int i = 0; i < D.length; i++){
			for(int j = 0; j < D.length; j++){
				if(D[i][j] != Double.MAX_VALUE)
					System.out.print(D[i][j] + "\t");
				else System.out.print("oo\t");
			}
			System.out.println();
		}
		System.out.println("\n-----------------------------------");
	}
	
	/*
	 * Floyd �㷨�����ȫ�����·���㷨 O(V^3);
	 * ����û����Ρ�
	 */
	public void Floyd(){
		int V = G.get_nv();
		for(int i = 0; i < V; i++)
			for(int j = 0; j < V; j++)
				if(P[j][i] != null)
					for(int t = 0; t < V; t++)
						if(j != t)
							// ���¶���j������t�ľ��룬��D[j][t]
							if(D[j][i] != Double.MAX_VALUE &&
									D[i][t] != Double.MAX_VALUE &&
									D[j][t] > D[j][i] + D[i][t]){
								P[j][t] = P[j][i];
								D[j][t] = D[j][i] + D[i][t];
								
								// ��ӡ�м���
								for(int i2 = 0; i2 < D.length; i2++){
									for(int j2 = 0; j2 < D.length; j2++){
										if(D[i2][j2] != Double.MAX_VALUE)
											System.out.print(D[i2][j2] + "\t");
										else System.out.print("oo\t");
									}
									System.out.println();
								}
								System.out.println("\n-----------------------------------");
							}
	}
}
