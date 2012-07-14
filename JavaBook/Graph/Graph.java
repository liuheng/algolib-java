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
 * ͼ�Ľӿڶ��壬
 * ������ͼ�Լ��ߵĸ��ֲ���
 * Graph.java
 */
public interface Graph {
	//��ȡ�������
	public int get_nv();
	//��ȡ�ߵ�����
	public int get_ne();
	//��ȡ����v�ĵ�һ����
	public Edge firstEdge(int v);
	//��ȡ��w����һ��ͬԴ��ı�
	public Edge nextEdge(Edge w);
	//�ж�w�Ƿ���ͼ�ϵıߣ��Ƿ���true
	public boolean isEdge(Edge w);
	//�ж϶���i��j֮���Ƿ��Ǳ�
	public boolean isEdge(int i, int j);
	//���ر�w��Դ��
	public int edge_v1(Edge w);
	//���ر�w���յ�
	public int edge_v2(Edge w);
	//�趨����i��j֮��ߵ�Ȩ��
	public void setEdgeWt(int i, int j, int wt);
	//�趨��w��Ȩ��
	public void setEdgeWt(Edge w, int wt);
	//��ȡ����i,j֮����ϵ�Ȩ��
	public int getEdgeWt(int i, int j);
	//��ȡ��w��Ȩ��
	public int getEdgeWt(Edge w);
	//ɾ������i,j֮��ı�
	public void delEdge(int i, int j);
	//ɾ����w
	public void delEdge(Edge w);
	//�趨����v�ı��
	public void setMark(int v, int val);
	//��ȡ����v�ı��
	public int getMark(int v);
	
}
