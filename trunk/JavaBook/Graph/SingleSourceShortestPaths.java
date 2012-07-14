/*
 * Created on 2010-7-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import Heap.MaxHeap;
import Tree.LcRsTree;

/**
 * @author Wei LU
 *
 * ���·���࣬�����а���ͼ�ĸ������·����������㷨
 */
public class SingleSourceShortestPaths {
	//�������ͼ
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
	public SingleSourceShortestPaths(GraphLnk G){
		this.G = G;
		// ����G�Ľڵ�����������
		V = new int[G.get_nv()];
		D = new double[G.get_nv()];
		// ����������СΪG�ڵ�����1����1�����ڵ�
		mst = new LcRsTree(G.get_nv() + 1);
		// 
		R = new EdgeElem[G.get_ne()];
	}
	
	/*
	 * Dijkstra �㷨Ѱ��ͼ�е�Դ�����·��
	 * ����Ϊ��Ѱ�ҵ�ͼG�Լ�Դ��s
	 */
	public void Dijkstra(int s){
		if(s < 0) return;
		int nv = G.get_nv();
		// ��ʼ��
		for(int i = 0; i < nv; i++){
			D[i] = Integer.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- ÿ��ӵ�����
		}
		
		// �����s�����ڵ�Ϊ-1������Ϊ0
		V[s] = -1;
		D[s] = 0;
		G.setMark(s, 1);
		for(Edge w = G.firstEdge(s); G.isEdge(w); w = G.nextEdge(w)){
			D[G.edge_v2(w)] = G.getEdgeWt(w);
			V[G.edge_v2(w)] = s;
		}

		/*�����ඥ�����ҵ��뵱ǰSPT����Ķ���v����������ĸ��ڵ��
		 * ����v��ӵ�SPT�С�����ͼ��Ȩֵ����ڽڵ�v�С�
		 * ѭ��������ֱ�����ж��㶼����һ��.
		 */
		while(true){
			/*��ȡ�뵱ǰ����������ıߣ����յ�Ϊ����Ķ���
			 * ���Ϊ�������ĸ��ڵ�
			 */
			Edge E = Utilities.minNextEdge(G, V);
			//�����Ϊ�գ���������
			if(E == null) break;
			System.out.println(E.get_v1() + " -- " + E.get_v2() + " -- " + G.getEdgeWt(E));

			// E���յ�v�����ʹ���
			int v = E.get_v2();
			G.setMark(v, 1);
			// ������v���������бߵľ��루�ɳڹ��̣�
			for(Edge w = G.firstEdge(v); G.isEdge(w); w = G.nextEdge(w)){
				if(D[G.edge_v2(w)] > (D[v] + G.getEdgeWt(w))){
					// ������̾���
					D[G.edge_v2(w)] = D[v] + G.getEdgeWt(w);
					// ���¸��ڵ�
					V[G.edge_v2(w)] = v;
				}
			}
		}
		
		// ����V���齨�����·����SPT
		mst.addChild(s, s, new ElemItem<Double>(D[0]));
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
	
	/*
	 * �������ȶ��е�Dijkstra�㷨�������ڻ������߶��е�Prim�㷨
	 */
	public void DijkstraPQ(int s){
		if(s < 0) return;
		// ͼ�����ͼ�߸���
		int nv = G.get_nv();
		int ne = G.get_ne();
		// �����Ϊ�ߵ�����
		MaxHeap H = new MaxHeap(ne);
		
		// ��ʼ��
		for(int i = 0; i < nv; i++){
			D[i] = Integer.MAX_VALUE;
			V[i] = -2;
			G.setMark(i, 0);//0 -- ÿ��ӵ�����
		}

		// �����s�����ڵ�Ϊ-1������Ϊ0
		V[s] = -1;
		D[s] = 0;
		G.setMark(s, 1);
		
		// ��ʼ���ѣ�������������ı߶���ӵ�����
		for(Edge E = G.firstEdge(s); G.isEdge(E); E = G.nextEdge(E)){
			D[E.get_v2()] = G.getEdgeWt(E);
			V[E.get_v2()] = s;
			H.insert(new ElemItem<EdgeElem>(new EdgeElem(E, -1 * G.getEdgeWt(E))));
		}
		
		// ���Ѷ�Ԫ��ɾȥ������
		int v = -1; EdgeElem PE = null;
		while(true){
			v = -1;
			// ����Ѳ�Ϊ��
			while(H.topVal() != null){
				// ɾ�������ضѶ�Ԫ��
				PE = (EdgeElem)(H.removeMax().elem);
				v = PE.get_v2();
				// ����Ѷ�Ԫ�ض�Ӧ�Ķ���û�б����ʣ����˳�ѭ��
				if(G.getMark(v) == 0) break;
				// �����ʾû���ҵ���һ������ӵ�MST�Ķ���
				else v = -1;
			}
			
			// ���û�пɼ�����ӵĶ����ˣ���������
			if(v == -1) break;
			
			// ���õ��ĶѶ�Ԫ�ض�Ӧ��������Ϊ���ʹ�
			G.setMark(v, 1);
			System.out.println(PE.get_v1() + " -- " + PE.get_v2() + " -- " + PE.get_wt());
			
			// ��������v�����ġ�δ�����ʵı���ӵ�����
			for(Edge w = G.firstEdge(v); G.isEdge(w); w = G.nextEdge(w)){
				if(D[G.edge_v2(w)] > (D[v] + G.getEdgeWt(w))){
					D[G.edge_v2(w)] = (D[v] + G.getEdgeWt(w));
					V[G.edge_v2(w)] = v;
					H.insert(new ElemItem<EdgeElem>(new EdgeElem(w, -1 * (int)D[G.edge_v2(w)])));
				}
			}
			
		}
		
		// ����V���齨�����·����SPT
		mst.addChild(s, s, new ElemItem<Double>(D[0]));
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
				if(V_idx[i] == 0 && V_idx[V[i]] == 1 && 
						mst.addChild(V[i], i, new ElemItem<Double>(D[i]))){
						V_idx[i] = 1;
						f = 1;
				}
			}
			// һ�ζ�û����ӣ�����ѭ��
			if(f == -1) break;
		}
	}
	

}
