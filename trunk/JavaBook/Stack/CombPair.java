/*
 * Created on 2010-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Stack;

/**
 * @author Lu Wei
 *
 * 递推实现组合问题时用于压栈的数据对
 */
public class CombPair implements Comparable{
	int i, k;//组合问题中的总数i，待选数k
	public CombPair(int _i, int _k){//构造函数
		i = _i;
		k = _k;
	}

	public int compareTo(Object o) {
		return 0;
	}
}
