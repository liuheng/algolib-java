/*
 * Created on 2010-7-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package FlowNetwork;

import List.SingleNode;

/**
 * @author Administrator
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class NetworkEdge {
	
	//˽�г�Ա����
	//�ߵ�Դ������սڵ�
	private int vert1, vert2;
	//������ڵ㣬
	private SingleNode itself;
	//���캯��
	public NetworkEdge(int _v1, int _v2, SingleNode _it){
		vert1 = _v1;
		vert2 = _v2;
		itself = _it;
	}
	
	public int get_v1() {
		return vert1;
	}
	
	public int get_v2() {
		return vert2;
	}
	
	public SingleNode get_lk(){
		return itself;
	}
	
	public boolean from(int v){	return v == get_v1();}
	
	public int other(int v){
		return from(v)?vert2:vert1;
	}
	/*
	// �ñ��ϵ���
	private int pflow;
		
	public NetworkEdge(int _v1, int _v2, SingleNode _it, int p){
		super(_v1, _v2, _it);
		this.pflow = p;
	}
	
	public int flow(){return pflow;}
	
	
	
	
	*/
	
}
