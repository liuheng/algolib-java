/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

import Element.ElemItem;
import List.SingleLink2;
import Queue.LinkQueue;
import Stack.LinkStack;

/**
 * @author Wei LU
 *
 * ͼ�Ĺ�����ȡ������������
 * GraphSearch.java
 */
public class GraphSearch {
	public enum COLOR { 
		WHITE, GRAY, BLACK 
	}; 
		
	static COLOR color[];//  = new COLOR[n];
	static int   parent[];// = new int[n];//�ڵ�ĸ��ڵ㣬�Ǹ�����
	static int   d[];// 	   = new int[n];//���depth	
	static int   f[];
	static int   ord[];
	static int	 time;
	
	/*������������㷨���ӵ�start�ڵ㿪ʼ��������*/
	static void BFS(GraphLnk G, int start){
		int n = G.get_nv();
		if(start < 0 || start >= n) return;

		color = new COLOR[n];
		parent = new int[n];//�ڵ�ĸ��ڵ㣬�Ǹ�����
		d 	   = new int[n];//���depth	
		for(int i = 0; i < n; i++){
			//�����ж������ɫ��ɫΪ��ɫ
			color[i] = COLOR.WHITE;
			//���ж���ĸ�ĸ��ʼ��Ϊ-1
			parent[i] = -1;
			//���ж������ȳ�ʼ��Ϊ�����
			d[i] = Integer.MAX_VALUE;
		}
		color[start] = COLOR.GRAY;
		parent[start] = -1;
		d[start] = 0;
		
		LinkQueue Q = new LinkQueue();
		Q.enqueue(new ElemItem<Integer>(start));
		while(Q.currSize() > 0){
			int u = ((Integer)(Q.dequeue().elem)).intValue();
			for(Edge w = G.firstEdge(u); G.isEdge(w); w = G.nextEdge(w)){
				if(color[w.get_v2()] == COLOR.WHITE){
					color[w.get_v2()] = COLOR.GRAY;
					d[w.get_v2()] = d[u] + 1;
					parent[w.get_v2()] = u;
					Q.enqueue(new ElemItem<Integer>(w.get_v2()));
				}
			}
			color[u] = COLOR.BLACK;	
			for(int i = 0; i < n; i++){
				System.out.print(i + ")" + color[i] + "\t");
			}
			System.out.println();
		}
		System.out.println("����������Ϊ��");
		for(int i = 0; i < n; i++){
			System.out.print(d[i] + "\t");
		}
		System.out.println("\n������ĸ�ĸΪ��");
		for(int i = 0; i < n; i++){
			System.out.print(parent[i] + "\t");
		}
		System.out.println();
	}
	
	/*������������㷨���ӵ�start���ڵ㿪ʼ����*/
	static void DFS(GraphLnk G, int start){
		int n = G.get_nv();
		time = 0;//ʱ������¼�����
		if(start < 0 || start >= n) return;
		
		color  = new COLOR[n];
		parent = new int[n];//�ڵ�ĸ��ڵ㣬�Ǹ�����
		d 	   = new int[n];//��һ��ʱ���
		f	   = new int[n];//�ڶ���ʱ���
		ord	   = new int[n];//ÿ���ڵ�ķ��ʴ���
		
		for(int i = 0; i < n; i++){
			//�����ж������ɫ��ɫΪ��ɫ
			color[i] = COLOR.WHITE;
			//���ж���ĸ�ĸ��ʼ��Ϊ-1
			parent[i] = -1;
			//���ж������ȳ�ʼ��Ϊ�����
			d[i] = Integer.MAX_VALUE;
			f[i] = Integer.MAX_VALUE;
			//�ڵ�����ʴ��򶼳�ʼ��Ϊ-1
			ord[i] = -1;
		}
		//���õݹ麯�������б�������
		DFS_VISIT(G, start);
	}
	
	//�������أ������ؽ�ͼ��ÿ��������Ϊ������������ȱ���
	static void DFS(GraphLnk G){
		int n = G.get_nv();
		time = 0;//ʱ������¼�����
		color  = new COLOR[n];
		parent = new int[n];//�ڵ�ĸ��ڵ㣬�Ǹ�����
		d 	   = new int[n];//��һ��ʱ���
		f	   = new int[n];//�ڶ���ʱ���
		ord	   = new int[n];//ÿ���ڵ�ķ��ʴ���
		for(int i = 0; i < n; i++){
			//�����ж������ɫ��ɫΪ��ɫ
			color[i] = COLOR.WHITE;
			//���ж���ĸ�ĸ��ʼ��Ϊ-1
			parent[i] = -1;
			//���ж����ʱ�����ʼ��Ϊ�����
			d[i] = Integer.MAX_VALUE;
			f[i] = Integer.MAX_VALUE;
			//���ʴ����ʼ��Ϊ-1
			ord[i] = -1;
		}
		//��ÿһ��������Ϊ������ʼ��Ȼ�������
		//����������ͼ�б�����ȫ���������
		for(int s = 0; s < n; s++)
			if(color[s] == COLOR.WHITE)
				DFS_VISIT(G, s);
	}
	
	/*������������㷨�е��õĵݹ麯����
	 * ʵ��ͼ�ĵݹ����*/
	static int k = 0;
	public static void DFS_VISIT(Graph G, int u){
		color[u] = COLOR.GRAY;
		time++;
		d[u] = time;
		
		for(int i = 0; i < G.get_nv(); i++){
			System.out.print(i + ")" + color[i] + "\t");
		}
		System.out.println();
		for(int i = 0; i < G.get_nv(); i++){
			if(d[i] < Integer.MAX_VALUE && f[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/" + f[i] + "\t");
			else if(d[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/"  + "\t");
			else
				System.out.print(i + ")" + " " + "/" + " " + "\t");
		}
		System.out.println();
		
		for(Edge w = G.firstEdge(u); G.isEdge(w); w = G.nextEdge(w)){
			if(color[w.get_v2()] == COLOR.WHITE){
				parent[w.get_v2()] = u;
				DFS_VISIT(G, w.get_v2());
			}
		}
		
		color[u] = COLOR.BLACK;
		
		f[u] = ++time;
		ord[u] = k++;
		for(int i = 0; i < G.get_nv(); i++){
			System.out.print(i + ")" + color[i] + "\t");
		}
		System.out.println();
		for(int i = 0; i < G.get_nv(); i++){
			if(d[i] < Integer.MAX_VALUE && f[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/" + f[i] + "\t");
			else if(d[i] < Integer.MAX_VALUE)
				System.out.print(i + ")" + d[i] + "/"  + "\t");
			else
				System.out.print(i + ")" + " " + "/" + " " + "\t");
		}
		System.out.println();
	}
	
	/*���ݽڵ�ı����Ⱥ���򣬻�ȡ�ڵ��µķ��ʴ���
	 * �ں�������ʱ��new_ord[i]�������ǣ�
	 * ԭͼ�ϵ�i���ڵ���µķ��ʴ���Ϊnew_ord[i]��
	 * ������ǣ�����ʵĽڵ����µķ��ʴ������ȷ���*/
	public static void newordermap(int[] ord, int[] new_ord){
		int ord_temp[] = new int[ord.length];
		int max, max_idx;
		for(int i = 0; i < ord.length; i++) ord_temp[i] = ord[i];
 		for(int i = 0; i < ord.length; i++){
			max_idx = 0; max = ord_temp[max_idx];
			for(int j = 0; j < ord.length; j++){
				if(ord_temp[j] == -1) continue;
				if(ord_temp[j] > max){
					max = ord_temp[j];
					max_idx = j;
				}
			}
			ord_temp[max_idx] = -1;
			new_ord[i] = max_idx;
		}
	}
	
	/*�����ݷ���ͼGT��ÿ���ڵ���ԭͼG��ȱ���ʱ���뿪ʱ�����飬
	 * ��������slk��ʾ����ͨ��֧
	 * ���������ı�ͼ�����ӹ�ϵ��ֻ�ǽڵ�ķ��ʴ�����������*/
	public static void KosarajuDFS(GraphLnk GT, int ord[], SingleLink2 slk){
		/*����ord�������new_order���飬�µķ���˳��Ϊ��
		 * ��i�η��ʵĽڵ�Ϊԭͼ�ϵĵ�new_order[i]���ڵ�*/
		int new_order[] = new int[ord.length];
		//���ú���newordermap�ı�����Ĵ���
		newordermap(ord, new_order);
		int n = GT.get_nv();
		//����ֻ��Ҫ��¼��ɫ��������Ϣ����Ҫ��
		color  = new COLOR[n];
		//��ɫ��ʼ��Ϊ��ɫ
		for(int i = 0; i < n; i++) color[i] = COLOR.WHITE;
		//Ϊ�ҵ�ͼ�����е���ͨ����ѭ��������
		for(int i = 0; i < new_order.length; i++){
			//��i�η��ʵĽڵ�Ϊԭͼ�ϵĵ�new_order[i]���ڵ�
			int  j= new_order[i];
			if(color[j] != COLOR.WHITE) continue;
			//����һ��ͼ�ڵ�������ʾһ����ͨ����
			GNodeSingleLink gnsk = new GNodeSingleLink();
			//���õݹ麯������jΪ������������ͼ
			KosarajuDFSVISIT(GT, ord, j, gnsk);
			/*����ͨ���Ľڵ��γɵ�������ӵ���ͨ���������У�
			 * ����ʹ�õ�ʵ�����Ƕ�ά����*/
			slk.append(new ElemItem<GNodeSingleLink>(gnsk));
		}
		
	}
	
	/*�ݹ麯�������Ϊu�������ͼG���ڵ�ݹ�ص��øú���ʱ���ڵ�ķ���˳��
	 * ��ord�������Խڵ�u��֮�����ұ��Ϊ��ɫ�ĵĽڵ�v1,v2,...
	 * �ȷ���ord[vi]���Ľڵ�vi.��û����ڵ�u�����Ľڵ������u����������
	 * �ڵ㶼���ǰ�ɫ���ˣ���ʱ���һ����ͨ���򣬺�������
	 */
	public static void KosarajuDFSVISIT(GraphLnk G, int ord[], 
						 int u, GNodeSingleLink slk){
		//���ʸýڵ㣬������ɫ���Ϊ��ɫ
		color[u] = COLOR.GRAY;
		//���ýڵ�u��ӵ���ǰ����ͨ������
		slk.append(new ElemItem<Integer>(u));
		//����ͳ����ýڵ������ġ���ɫΪ��ɫ�Ľڵ�ĸ���
		int cnt = 0;
		for(Edge w = G.firstEdge(u); 
				G.isEdge(w); w = G.nextEdge(w)){
			if(color[w.get_v2()] == COLOR.WHITE)
				cnt++;
		}
		//�����ʱû����ýڵ�����������ɫΪ��ɫ�Ľڵ㣬��������
		if(cnt == 0) return;
		//���򣬽���ýڵ������ġ���ɫ�ڵ��ݴ�������e��
		Edge e[] = new Edge[cnt];
		cnt = 0;
		for(Edge w = G.firstEdge(u); G.isEdge(w); w = G.nextEdge(w)){
			if(color[w.get_v2()] == COLOR.WHITE)
				e[cnt++] = w;
		}
		/*������e���ձߵ��յ�ķ��ʴ���ord[..]��������
		 * �������ѡ�������������һ���� */
		int max_idx;
		for(int i = 0; i < e.length - 1; i++){
			//��i���ҵ�i���Ԫ��
			max_idx = i;
			for(int j = i + 1; j < e.length; j++){
				if(ord[e[j].get_v2()] > ord[e[max_idx].get_v2()]){
					max_idx = j;
				}
			}
			//���ԭ�ȵ�iλ���ϲ������ģ��򽻻�����
			if(max_idx != i){
				Edge t = e[i];
				e[i] = e[max_idx];
				e[max_idx] = t;
			}
		}
		//�������ı�������еݹ����
		for(int i = 0; i < e.length; i++)
			KosarajuDFSVISIT(G, ord, e[i].get_v2(), slk);
	}
	
	/*Trajan�㷨�ĵݹ�DFS����
	 * �������
	 */
	public static void TrajanDFS(GraphLnk G, int w, int pre[], int low[], int cnt[], LinkStack ls, SingleLink2 slk){
		int t , min = low[w] = pre[w] = cnt[0]++;
		// ����ǰ�����ѹջ
		ls.push(new ElemItem<Integer>(w));
		// �Ե�ǰ�����ÿ���ڵ�ѭ��
		for(Edge e = G.firstEdge(w); G.isEdge(e); e = G.nextEdge(e)){
			//����ڵ�û�б������������ݹ����
			if(pre[e.get_v2()] == -1){
				TrajanDFS(G, e.get_v2(), pre, low, cnt, ls, slk);
			}
			/*����ڵ��Ѿ����������ˣ��Ƚ�������min,
			 * �����Ž�С�������min�Ĵ�С*/
			if(low[e.get_v2()] < min) min = low[e.get_v2()];
		}
		//�����ʱminС��low[w]���򷵻�
		if(min < low[w]){
			low[w] = min;
			return;
		}
		//���򣬵���ջ��Ԫ�أ�����Ԫ����ӵ�������
		GNodeSingleLink gnslk = new GNodeSingleLink();
		do{
			//����ջ��Ԫ��
			t = ((Integer)(ls.pop().elem)).intValue();
			low[t] = G.get_nv();
			//��ӵ�������
			gnslk.append(new ElemItem<Integer>(t));
		}while(t != w);
		//����������ӵ�slk������
		slk.append(new ElemItem<GNodeSingleLink>(gnslk));
	}
	
	/*Trajan�㷨�ĵݹ�DFS����
	 * �������
	 */
	public static void GabowDFS(GraphLnk G, int w, int pre[], int low[], int[] id, 
						int cnt[], LinkStack ls, LinkStack P, SingleLink2 slk){
		int v;
		pre[w] = cnt[0]++;
		//����ǰ�����ѹջ
		ls.push(new ElemItem<Integer>(w));
		P.push(new ElemItem<Integer>(w));
		
		// �Ե�ǰ�����ÿ���ڵ�ѭ��
		for(Edge e = G.firstEdge(w); G.isEdge(e); e = G.nextEdge(e)){
			//����ڵ�û�б������������ݹ����
			if(pre[e.get_v2()] == -1){
				GabowDFS(G, e.get_v2(), pre, low, id, cnt, ls, P, slk);
			}
			//��������ڵ㱻����������û�б�֮ǰ����ͨ���������ѭ������
			else if(id[e.get_v2()] == -1){
				int ptop = ((Integer)(P.getTop().elem)).intValue();
				//ѭ��������ֱ��ջ���������Ų�С���ڵ�����
				while(pre[ptop] > pre[e.get_v2()]) {
					P.pop();
					ptop = ((Integer)(P.getTop().elem)).intValue();
				}
			}
		}
		if(P.getTop() != null && ((Integer)(P.getTop().elem)).intValue() == w)
			P.pop();
		else return;
		GNodeSingleLink gnslk = new GNodeSingleLink();
		do{
			v = ((Integer)(ls.pop().elem)).intValue();
			id[v] = 1;
			gnslk.append(new ElemItem<Integer>(v));
		}while(v != w);
		slk.append(new ElemItem<GNodeSingleLink>(gnslk));
		
	}
}
