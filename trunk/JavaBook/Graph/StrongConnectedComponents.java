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
 * ǿ��ͨ��֧�࣬
 * 
 */
public class StrongConnectedComponents {
	/*�㷨1��Kosaraju,�㷨���裺
	 * 1. ��ԭʼͼG����������������ÿ���ڵ�ı�������ord[]��
	 * 2. ����ԭʼͼ�ķ���ͼGT��
	 * 3. ����ord[]�෴��˳�����GT��ÿ���ڵ㣻
	 * �������շ���һ����ά������slk��������ÿ���ڵ�����һ��������
	 * ÿ���ڵ㴦�ĵ������ʾһ����ͨ����
	 * slk�ĳ��ȴ�����ͼ����ͨ����ĸ�����*/
	public static SingleLink2 Kosaraju(GraphLnk G){
		SingleLink2 slk = new SingleLink2();
		int ord[] = new int[G.get_nv()];
		//��ԭͼ���������������
		GraphSearch.DFS(G);
		//����ͼG��������ȱ���ʱÿ���ڵ���뿪ʱ��
		for(int i = 0; i < GraphSearch.f.length; i++){
			ord[i] = GraphSearch.ord[i];
			System.out.print(GraphSearch.parent[i] + " || ");
		}
		System.out.println();
		//����G�ķ���ͼGT
		GraphLnk GT = reverseGraph(G);
		/*�����Kosaraju�㷨�����DFS�㷨KosarajuDFS����
		 * �ú�������ord������˳�����ÿ���ڵ㣬
		 * ����slk������µ�����Ԫ�أ�*/
		GraphSearch.KosarajuDFS(GT, ord, slk);
		//��ӡ���е���ͨ����
		for(slk.goFirst(); slk.getCurrVal() != null; slk.next()){
			//��ȡһ������Ԫ�����һ����ͨ����
			GNodeSingleLink comp_i = 
					(GNodeSingleLink)(slk.getCurrVal().elem);
			//��ӡ�����ͨ�����ÿ��ͼ�ڵ�
			for(comp_i.goFirst(); 
				comp_i.getCurrVal() != null; comp_i.next()){
				System.out.print(comp_i.getCurrVal().elem + "\t");
			}
			System.out.println();
		}
		//������ͨ��������
		return slk;
	}
	
	/*Trajan �㷨ʵ��ͼ��ǿ��ͨ������ң�
	 * ����Ϊͼ�ṹ���㷨���裺
	 * 
	 * �������շ���һ����ά������slk��������ÿ���ڵ�����һ��������
	 * ÿ���ڵ㴦�ĵ������ʾһ����ͨ����
	 * slk�ĳ��ȴ�����ͼ����ͨ����ĸ�����
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
		
		//��ӡ���е���ͨ����
		for(slk.goFirst(); slk.getCurrVal() != null; slk.next()){
			//��ȡһ������Ԫ�����һ����ͨ����
			GNodeSingleLink comp_i = 
					(GNodeSingleLink)(slk.getCurrVal().elem);
			//��ӡ�����ͨ�����ÿ��ͼ�ڵ�
			for(comp_i.goFirst(); 
				comp_i.getCurrVal() != null; comp_i.next()){
				System.out.print(comp_i.getCurrVal().elem + "\t");
			}
			System.out.println();
		}
		return slk;
	}
	
	/* Gabow �㷨ʵ��ͼ��ǿ��ͨ������ң�
	 * ����Ϊͼ�ṹ���㷨���裺
	 * 
	 * �������շ���һ����ά������slk��������ÿ���ڵ�����һ��������
	 * ÿ���ڵ㴦�ĵ������ʾһ����ͨ����
	 * slk�ĳ��ȴ�����ͼ����ͨ����ĸ�����
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
		
		//��ӡ���е���ͨ����
		for(slk.goFirst(); slk.getCurrVal() != null; slk.next()){
			//��ȡһ������Ԫ�����һ����ͨ����
			GNodeSingleLink comp_i = 
					(GNodeSingleLink)(slk.getCurrVal().elem);
			//��ӡ�����ͨ�����ÿ��ͼ�ڵ�
			for(comp_i.goFirst(); 
				comp_i.getCurrVal() != null; comp_i.next()){
				System.out.print(comp_i.getCurrVal().elem + "\t");
			}
			System.out.println();
		}
		return slk;
	}
	

	
	//����һ�������Gÿ���߷����෴��ͼ
	public static GraphLnk reverseGraph(GraphLnk G){
		GraphLnk GT = new GraphLnk(G.get_nv());
		for(int i = 0; i < G.get_nv(); i++){
			//GTÿ���ߵķ�����G�ķ����෴
			for(Edge w = G.firstEdge(i); G.isEdge(w); w = G.nextEdge(w)){
				GT.setEdgeWt(w.get_v2(), w.get_v1(),G.getEdgeWt(w));
			}
		}
		return GT;
	}

}
