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
 * ͼ�����Ӿ��󷨣�
 * GraphMtrx.java
 */
public class GraphMtrx implements Graph{
	// ˽�г�Ա����
	// ���Ӿ��󣬶�ά����
	private int[][] mtrx;
	// �ߵĸ���
	private int numEdge;
	// ����ĸ���
	private int numVertex;
	// ��Ǿ���
	private int[] mark;
	/**
	 * ���캯�������ݶ��������ʼ��������Ա����
	 * @param n	�������
	 */
	public GraphMtrx(int n){
		mtrx = new int[n][n];
		numVertex = n;
		numEdge = 0;
		mark = new int[n];
	}
	
	// ��ȡͼ�ж������
	public int get_nv() {
		return numVertex;
	}
	
	// ��ȡͼ�б߸���
	public int get_ne() {
		return numEdge;
	}
	
	/**
	 * ��ȡ��������ΪԴ��ĵ�һ���ߣ����յ�ı����С����
	 * @param v	��������
	 */
	public Edge firstEdge(int v) {
		// ����ÿ�����㣬������v�����ĵ�һ����
		for(int i = 0; i < numVertex; i++)
			if(isEdge(v, i))
				return new EdgeMtrx(v, i);
		return null;
	}
	
	/**
	 * ��ȡ�����������ͬԴ�����һ���ߣ����յ�ı�Ŵ���
	 * �����ߵ��յ��ţ���������С�ģ�
	 * @param w	�����ı�
	 * @return �������ͬԴ�����һ����
	 */
	public Edge nextEdge(Edge w) {
		if(w == null) return null;
		/* �Ӷ�����Ϊw���յ���+1��ʼѰ�ҵ�һ����wԴ��
		 * �����ıߣ�������������*/
		for(int i = w.get_v2() + 1; i < numEdge; i++)
			if(isEdge(w.get_v1(), i))
				return new EdgeMtrx(w.get_v1(), i);
		return null;
	}
	
	/**
	 * �жϸ������Ƿ�Ϊͼ��һ����;
	 * @param w	�����ı�
	 * @return	�����һ���ߣ�����true�����򷵻�false
	 */
	public boolean isEdge(Edge w) {
		if(w == null) 
			return false;
		// �жϾ����ж�ӦԪ���Ƿ�Ϊ�����
		else{
			int v1 = w.get_v1(), v2 = w.get_v2();
			return mtrx[v1][v2] != Integer.MAX_VALUE;
		}
	}
	
	/**
	 * �жϸ�������Զ�Ӧ�ı��Ƿ�Ϊͼ��һ����;
	 * @param i, j	����Զ�Ӧ�ı�ţ�
	 * @return �����һ�����򷵻�true������false
	 */
	public boolean isEdge(int i, int j) {
		if(i < 0 || j < 0) 
			return false;
		// �жϾ����ж�ӦԪ���Ƿ�Ϊ�����
		return mtrx[i][j] != Integer.MAX_VALUE;
	}
	
	/**
	 * ��ȡ�����ߵ�Դ�㣻
	 * @param w	������
	 * @return ����w��Դ��
	 */
	public int edge_v1(Edge w) {
		if(w == null) return -1;
		return w.get_v1();
	}
	
	/**
	 * ��ȡ�����ߵ��յ�
	 * @param w	������
	 * @return	����w���յ�
	 */
	public int edge_v2(Edge w) {
		if(w == null) return -1;
		return w.get_v2();
	}
	
	/**
	 * �趨�Զ���i�Ͷ���j��ɵıߵ�Ȩ�أ������������ͼ��
	 * �������ڣ��򴴽������ߣ�������ȨֵΪwt
	 * @param i,j	��������ı��
	 * @param wt	���趨��Ȩ�ش�С
	 */
	public void setEdgeWt(int i, int j, int wt) {
		if(i < 0 || j < 0) return;
		// ����߲���������½ڵ����
		if(!isEdge(i, j))
			numEdge++;
		// ����ߵ�Ȩ��
		mtrx[i][j] = wt;
	}
	
	/**
	 * ���ñ�w��Ȩֵ������������setEdgeWt(int, int, int)
	 * @param w	������ı�
	 * @param wt	�������Ȩֵ
	 */
	public void setEdgeWt(Edge w, int wt) {
		//������һ����
		if(w == null) return;
		setEdgeWt(w.get_v1(), w.get_v2(), wt);
	}
	
	/**
	 * ��ȡ����i�Ͷ���j��ɵıߵ�Ȩ��
	 * @param i, j	����ȡȨ�صıߵ�Դ�㡢�յ�
	 * @return	����i�Ͷ���j��ɵıߵ�Ȩ��
	 */
	public int getEdgeWt(int i, int j) {
		//δ���ӻ���β��Ϸ������������
		if(i < 0 || j < 0 ||
		   !isEdge(i, j))
			return Integer.MAX_VALUE;
		return mtrx[i][j];
	}
	
	/**
	 * ��ȡ�����ߵ�Ȩ�أ����ú���getEdgeWt(i, j)
	 * @param w	�����ı�
	 * @return w��Ȩ��
	 */
	public int getEdgeWt(Edge w) {
		//������һ����
		if(w == null) 
			return Integer.MAX_VALUE;
		return getEdgeWt(w.get_v1(), w.get_v2());
	}
	
	/**
	 * ɾ������i�Ͷ���j��ɵı�
	 */
	public void delEdge(int i, int j) {
		if(i < 0 || j < 0)return;
		// ����ߴ����򽫾����Ӧλ��ȡֵΪ�����
		if(isEdge(i, j)){
			mtrx[i][j] = Integer.MAX_VALUE;
			numEdge--;
		}
	}
	
	/**
	 * ɾ���ߣ�����delEdge(int, int)
	 */
	public void delEdge(Edge w) {
		// ������һ����
		if(w == null) return;
		delEdge(w.get_v1(), w.get_v2());
	}
	
	/**
	 * ���趥��ı��ֵ������ͼ�ı����㷨�н�������
	 */
	public void setMark(int v, int val) {
		if(v < 0) return;
		mark[v] = val;
	}
	
	/**
	 * ��ȡ����v�ı��ֵ
	 */
	public int getMark(int v) {
		// ��ȷ�ı�ǵ�ȡֵΪ0,1,...
		if(v < 0) return -1;
		return mark[v];
	}
}
