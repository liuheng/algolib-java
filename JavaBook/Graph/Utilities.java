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
	 * ��ͼG��Ѱ���뵱ǰ����������ĵ�v���������ص���v�͵�ǰ
	 * ��������ĳ�����γɵĽ���ߣ��ý�����ǵ�ǰ�������ж��㼯
	 * �����ඥ���γɵĽ������Ȩ��С��һ����
	 * ���V[i] != -2��ʾ�ö���i�ڵ�ǰ���������С�
	 */
	public static Edge minNextEdge(GraphLnk G, int V[]){
		int len = V.length;
		//��С��Ȩֵ��С
		double _mind = Double.MAX_VALUE;
		// �����صĽ����
		Edge _4ret = null;
		// ��ӵ�ǰMST�е�ÿ��������������н���ߵĵ�Ȩֵ����Сֵ
		for(int i = 0; i < len; i++){
			// ��MST�е�ÿ������
			if(G.getMark(i) == 1){
				// �Ըö������������ж���
				for(Edge E = G.firstEdge(i); G.isEdge(E); E = G.nextEdge(E)){
					// ����ö�����δ���ʣ�����Ȩֵ��_mind���
					if( G.getMark(E.get_v2()) == 0 && G.getEdgeWt(E) < _mind){
						//�����С������_mind��
						_mind = G.getEdgeWt(E);
						_4ret = E;
					}
				}
			}
		}
		// �����ҵ���Ȩֵ��С�ı�
		return _4ret;
	}
	
	/*
	 * ��ȡͼ�����бߣ�������Щ�߰���Ȩֵ�ɴ�С����
	 * ��������ΪͼG������ΪITEM���飬ÿ��Ԫ��ΪEdgeElem
	 * ����Ԫ�ذ���Ȩֵ����
	 */
	public static ITEM[] GetEdgeSort(Graph G){
		if(G == null) return null;
		// ���Ƚ����бߴ���ITEM����
		int ne = G.get_ne();
		int nv = G.get_nv();
		ITEM[] E = new ITEM[ne];
		int edge_cnt = 0;
		for(int i = 0; i < nv; i++){
			for(Edge e = G.firstEdge(i); G.isEdge(e); e = G.nextEdge(e)){
				E[edge_cnt++] = new ITEM(new EdgeElem(e, G.getEdgeWt(e)));
			}
		}
		// ��ITEM��������������ÿ�������
		Sort st = new Sort();
		st.quicksort(E, 0, E.length - 1);
		// ����ITEm����
		return E;
	}
	
	
	public static EdgeElem[] GetEdge(Graph G){
		if(G == null) return null;
		// ���Ƚ����бߴ���ITEM����
		int ne = G.get_ne();
		int nv = G.get_nv();
		EdgeElem[] E = new EdgeElem[ne];
		int edge_cnt = 0;
		for(int i = 0; i < nv; i++){
			for(Edge e = G.firstEdge(i); G.isEdge(e); e = G.nextEdge(e)){
				E[edge_cnt++] = new EdgeElem(e, G.getEdgeWt(e));
			}
		}
		// ����EdgeElem����
		return E;
	}
}
