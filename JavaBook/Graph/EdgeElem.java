/*
 * Created on 2010-7-19
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

/**
 * @author Wei LU
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
/*
 * �̳�Comparable�ӿڵ�EdgeElem�࣬���а���һ���ߺ������ߵ�Ȩֵ
 */
public class EdgeElem implements Comparable{
	Edge e;
	int wt;
	// ���캯��
	public EdgeElem(Edge _e, int _wt){
		e = _e;
		wt = _wt;
	}
	// ��ȡ�ߵ����
	public int get_v1(){
		return e.get_v1();
	}
	// ��ȡ�ߵ��յ�
	public int get_v2(){
		return e.get_v2();
	}
	// ��ȡ�ߵ�Ȩֵ
	public int get_wt(){
		return wt;
	}
	// �ȽϺ������Ƚ϶���ΪPrinElem�ıߵ�Ȩֵ
	public int compareTo(Object o) {
		EdgeElem other = (EdgeElem)o;
		if(wt > other.wt) return 1;
		else if(wt == other.wt) return 0;
		else return -1;
	}
	
}