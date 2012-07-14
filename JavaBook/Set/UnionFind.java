/*
 * Created on 2010-7-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


package Set;

/**
 *  �ȼ۹�ϵ���ȼ��ࣩADT��ʵ�֣�����ĵȼ����ʾ������Ԫ�ص��Ƿ���ͬһ�ࡣ
 *  ����array��ʾ�����±���Ź�����
 **/

public class UnionFind {

  private int[] array;

  /**
   *  ���캯��������Ϊarray�ĳ���
   **/
  public UnionFind(int numElements) {
    array = new int [numElements];
    // ��������ֵȫΪ -1
    for (int i = 0; i < array.length; i++) {
      array[i] = -1;
    }
  }

  /**
   * union() ���������Ϻϲ�Ϊͬһ�����ϣ�������˵�ǽ���һ�����ϵ��±���
   * �ڶ������ϵ��±�ϲ���һ�������С�
   *  @param root1 ��һ�������е�����һ���±�.
   *  @param root2 �ڶ��������е�����һ���±�.
   *  ���root1��root2�ֱ����������ϵĸ��������Ƚ�����
   *  �ĸ������
   **/
  public void union(int root1, int root2) {
  	// ���root1��root2�ֱ����������ϵĸ��������Ƚ����ǵĸ������
  	root1 = find(root1);
  	root2 = find(root2);
  	// ���root2���ߣ���root2��Ϊ�ϲ���ĸ�
    if (array[root2] < array[root1]) {
      array[root1] = root2; 
    } 
    else {
      // ���һ���ߣ���С����һ���ĸ߶�
      if (array[root1] == array[root2]) {
      	array[root1]--; 
      }
      // һ���߻���root1���ߣ���root1��Ϊ�ϲ���ĸ�
      array[root2] = root1;
    }
  }

  /**
   *  find() Ѱ�������±����ڵļ��ϵļ�����
   *  @param x�� �����±�
   *  @return �� ������
   **/
  public int find(int x) {
  	// x�Ǹ���ֱ�ӷ���
    if (array[x] < 0) {
      return x;                         // x is the root of the tree; return it
    } 
    else {
      /* �ݹ�Ѱ��x�ĸ����ڵݹ�����н�ѹ��x��������ȣ�ʹ��array[x]��
       * ������ڵ���±�
       */
      array[x] = find(array[x]);
      return array[x];// ���ظ��ڵ�
    }
  }

}

