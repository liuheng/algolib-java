/*
 * Created on 2010-7-18
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */


package Set;

/**
 *  等价关系（等价类）ADT的实现，这里的等价类表示数组中元素的是否是同一类。
 *  数组array表示的是下标序号关联。
 **/

public class UnionFind {

  private int[] array;

  /**
   *  构造函数，参数为array的长度
   **/
  public UnionFind(int numElements) {
    array = new int [numElements];
    // 数组中数值全为 -1
    for (int i = 0; i < array.length; i++) {
      array[i] = -1;
    }
  }

  /**
   * union() 将两个集合合并为同一个集合，具体来说是将第一个集合的下标与
   * 第二个集合的下标合并到一个集合中。
   *  @param root1 第一个集合中的任意一个下标.
   *  @param root2 第二个集合中的任意一个下标.
   *  如果root1和root2分别不是两个集合的根，则首先将他们
   *  的根求出来
   **/
  public void union(int root1, int root2) {
  	// 如果root1和root2分别不是两个集合的根，则首先将他们的根求出来
  	root1 = find(root1);
  	root2 = find(root2);
  	// 如果root2更高，则将root2设为合并后的根
    if (array[root2] < array[root1]) {
      array[root1] = root2; 
    } 
    else {
      // 如果一样高，减小其中一个的高度
      if (array[root1] == array[root2]) {
      	array[root1]--; 
      }
      // 一样高或者root1更高，则将root1设为合并后的根
      array[root2] = root1;
    }
  }

  /**
   *  find() 寻找输入下标所在的集合的集合名
   *  @param x： 输入下标
   *  @return ： 集合名
   **/
  public int find(int x) {
  	// x是根，直接返回
    if (array[x] < 0) {
      return x;                         // x is the root of the tree; return it
    } 
    else {
      /* 递归寻找x的根，在递归过程中将压缩x的索引深度，使得array[x]中
       * 保存根节点的下标
       */
      array[x] = find(array[x]);
      return array[x];// 返回根节点
    }
  }

}

