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
 * ͼ�ṹ�����Ӿ��󷨱�ʾʱ�ߵ�������
 * EdgeMMtrx.java
 */
public class EdgeMtrx implements Edge{
	//˽�г�Ա�������ߵ���������
	private int vert1, vert2;
	public EdgeMtrx(int _v1, int _v2){
		vert1 = _v1;
		vert2 = _v2;
	}
	public int get_v1() {
		return vert1;
	}

	public int get_v2() {
		return vert2;
	}
	
}
