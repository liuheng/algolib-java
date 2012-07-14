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
 * 继承Comparable接口的EdgeElem类，其中包含一条边和这条边的权值
 */
public class EdgeElem implements Comparable{
	Edge e;
	int wt;
	// 构造函数
	public EdgeElem(Edge _e, int _wt){
		e = _e;
		wt = _wt;
	}
	// 获取边的起点
	public int get_v1(){
		return e.get_v1();
	}
	// 获取边的终点
	public int get_v2(){
		return e.get_v2();
	}
	// 获取边的权值
	public int get_wt(){
		return wt;
	}
	// 比较函数，比较对象为PrinElem的边的权值
	public int compareTo(Object o) {
		EdgeElem other = (EdgeElem)o;
		if(wt > other.wt) return 1;
		else if(wt == other.wt) return 0;
		else return -1;
	}
	
}