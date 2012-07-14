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
 * Ford-Fulkerson, �㷨
 *
 * 
 */
public class NetworkMaxFlow {
	private Network G;
	private int s, t;
	private ElemItem[] wt;
	private NetworkEdge[] st;
	
	/**
	 * ���캯��
	 * @param G	����������
	 * @param s	���
	 * @param t �յ�
	 */
	public NetworkMaxFlow(Network G, int s, int t){
		this.G = G;
		this.s = s; 
		this.t = t;
		wt = new ElemItem[G.get_nv()];
		st = new NetworkEdge[G.get_nv()];
		
	}
	/**
	 * ��ȡ��st[v]���յ�
	 * @param v	���
	 * @return	��st[v]���յ�
	 */
	private int ST(int v){
		return st[v].other(v);
		
	}
	
	/**
	 * �������磻
	 * ����
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
	 * ���ȼ����ȱ���������
	 * ���������������s���յ�t�������·����
	 * @return	���
	 */
	private boolean PFS(){
		int M = -1 * Integer.MAX_VALUE;
		// �����±�ѵ����ȶ���
		intPQi pQ = new intPQi(wt, 1);
		for(int v = 0; v < G.get_nv(); v++){
			wt[v] = new ElemItem<Integer>(0);
			st[v] = null;
			pQ.insert(v);
		}
		// ���s�������ȶ��ж���
		wt[s] = new ElemItem<Integer>(M);
		pQ.lower(s);
		
		// �������̣�Ѱ����������·��
		while(!pQ.empty()){
			// �Ѷ������
			int v = pQ.getmax();
			wt[v] = new ElemItem<Integer>(M);
			// v�����յ����st[v]Ϊ�����Ƴ�����
			if(v == t) break;
			if(v != s && st[v] == null) break;
			// ����v���������ڶ���������·���ϵ���
			for(NetworkEdge E = G.firstEdge(v); G.isEdge(E); E = G.nextEdge(E)){
				NetworkEdge TmpE = E;
				// ���E������Ϊ������E����ΪE�ķ����
				if(G.getEdgeC(E) < 0){
					E = G.getNetworkEdge(E.get_v2(), E.get_v1());
				}
				if(E == null) return false;
				// ��ȡE����һ����w
				int w = E.other(v);
				// ��ȡ����w������·���ϵ���
				int cap = G.capRto(E, w);
				int wt_v = ((Integer)(wt[v].getElem())).intValue();
				int P = cap < (-1 * wt_v)?cap:(-1 * wt_v);
				int wt_w = ((Integer)(wt[w].getElem())).intValue();
				if(cap > 0 && (-1 * P) < wt_w){
					// ���¶���w������·���ϵ���
					wt[w] = new ElemItem<Integer>(-1 * P);
					// �������ȶ���
					pQ.lower(w);
					st[w] = E;
				}
				E = TmpE;
			}
		}
		return st[t] != null;
	}
	
	/**
	 * ����PSF�㷨��Ford Fulkerson�㷨��
	 * �������������·����������㷨
	 */
	public void Ford_Fulkerson(){
		// ����
		while(PFS()){ 
			augument();
			// ��ӡ��ǰ������ߵ�������
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
