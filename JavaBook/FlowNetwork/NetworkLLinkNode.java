/*
 * Created on 2010-6-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package FlowNetwork;

/**
 * @author Wei LU
 *
 * �����ӱ���������ṹ��ÿ�������Ӧһ��
 * ��������ڵ������������Ͷ�Ӧ��Ȩֵ
 * NetworkLLinkNode.java
 */
public class NetworkLLinkNode implements Comparable{
	//˽�г�Ա���յ㡢Ȩ�ء���
	private int des, weight, flow;
	//���캯��
	public NetworkLLinkNode(int _des, int _wt, int _flow){
		des = _des;
		weight = _wt;
		flow  = _flow;
	}
	
	// �����յ� 
	public void set_des(int _d){
		des = _d;
	}
	
	// ����Ȩ��
	public void set_wt(int _wt){
		weight = _wt;
	}
	
	// ������
	public void set_flow(int f){
		flow = f;
	}
	
	// ��ȡ�յ�
	public int get_des(){
		return des;
	}
	
	// ��ȡȨ��
	public int get_wt(){
		return weight;
	}
	
	// ��ȡ��
	public int get_flow(){
		return flow;
	}
	
	// �Ƚ��������������Ȩ��
	public int compareTo(Object arg0) {
		int _wt = ((NetworkLLinkNode)(arg0)).get_wt();
		if(weight > _wt) return 1;
		else if(weight < _wt) return -1;
		else return 0;
	}

}
