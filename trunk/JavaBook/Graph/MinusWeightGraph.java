/*
 * Created on 2010-7-20
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import Queue.LinkQueue;
import Tree.LcRsTree;

/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MinusWeightGraph {
	// �������ͼ
	GraphLnk G;
	// V[i]��ʾi���������еĸ��ڵ�
	int V[];
	// D[i]��ʾV[i]��i�γɵıߵ�Ȩֵ
	double D[];
	// �õ���������������������
	LcRsTree mst;
	// ������ʽ
	EdgeElem[] R;
	
	// ���캯��
	public MinusWeightGraph(GraphLnk G){
		this.G = G;
		// ����G�Ľڵ�����������
		V = new int[G.get_nv()];
		D = new double[G.get_nv()];
		// ����������СΪG�ڵ�����1����1�����ڵ�
		mst = new LcRsTree(G.get_nv() + 1);
		// 
		R = new EdgeElem[G.get_ne()];
	}
	
	public void BellmanFord(int s){
		if(s < 0) return;
		int nv = G.get_nv();
		// ��ʼ��
		for(int i = 0; i < nv; i++){
			D[i] = Double.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- ÿ��ӵ�����
		}
		// ����Q
		LinkQueue Q = new LinkQueue();
		// ��ʼ����ľ���Ϊ0
		D[s] = 0;
		// �����s��nv��ӵ�������
		Q.enqueue(new ElemItem<Integer>(s));
		Q.enqueue(new ElemItem<Integer>(nv));
		// �������̣�ֱ��QΪ��
		while(Q.currSize() != 0){
			int f  = -1;
			int v, N = 0;
			while(nv == (v = ((Integer)(Q.dequeue().elem)).intValue())){
				if(N++ > nv){ f = 1; break;}
				Q.enqueue(new ElemItem<Integer>(nv));
			}
			if(f == 1) break;
			// ��v�����������ı�e
			for(Edge e = G.firstEdge(v); G.isEdge(e); e = G.nextEdge(e)){
				// ����e���յ�w�ľ���
				int w = e.get_v2();
				double P = D[v] + G.getEdgeWt(e);
				// ���w����v��·�����̣������w�ľ���
				if(P < D[w]){
					D[w] = P;
					// ��w��ӵ�������
					Q.enqueue(new ElemItem<Integer>(w));
					// ��w�ĸ��ڵ�����Ϊv
					V[w] = v;
				}
			}
		}
		
		// ����V���齨�����·����SPT
		mst.addChild(s, s, new ElemItem<Double>(D[s]));
		mst.setRoot(s);
		int f = -1; 
		// ���������飬V_idx[i] == 1��ʾi�����Ѿ���SPT�У�������SPT��
		int[] V_idx = new int[V.length];
		// ��ʼ��
		for(int i = 0; i < V_idx.length; i++)V_idx[i] = 0;
		// ��ʼ����s�Ѿ���SPT��
		 V_idx[s] = 1;
		while(true){
			f = -1;
			for(int i = 0; i < V.length; i++){
				// ����i����SPT�У��丸����V[i]��SPT�У�����ӵ�SPT��
				if(V_idx[i] == 0 && V[i] >= 0 && V_idx[V[i]] == 1 && 
						mst.addChild(V[i], i, new ElemItem<Double>(D[i]))){
						V_idx[i] = 1;
						f = 1;
				}
			}
			// һ�ζ�û����ӣ�����ѭ��
			if(f == -1) break;
		}
	}
	
	
	
	public static void main(String args[]){
		// java algo P278
		GraphLnk g_minus = new GraphLnk(6);
		g_minus.setEdgeWt(0, 1, 41);
		g_minus.setEdgeWt(0, 5, 29);
		g_minus.setEdgeWt(1, 2, 51);
		g_minus.setEdgeWt(1, 4, 32);
		g_minus.setEdgeWt(2, 3, 50);
		g_minus.setEdgeWt(3, 0, 45);
		g_minus.setEdgeWt(3, 5, -38);
		g_minus.setEdgeWt(4, 2, 32);
		g_minus.setEdgeWt(4, 3, 36);
		g_minus.setEdgeWt(5, 1, -29);
		g_minus.setEdgeWt(5, 4, 21);
		
		MinusWeightGraph mg = new MinusWeightGraph(g_minus);
		mg.BellmanFord(4);
		for(int i = 0; i < mg.V.length; i++){
			System.out.print(mg.V[i] + " <- " + mg.D[i] + " -> " + i + "\t");
		}
		System.out.println();
		mg.mst.ford_print_tree();
	}
}
