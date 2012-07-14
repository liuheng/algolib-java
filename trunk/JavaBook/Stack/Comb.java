/*
 * Created on 2010-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Stack;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * 用递归和递推法实现组合数，Comb.java
 */

public class Comb {
	int a[];//数组用于记录组合数
	int count = 0;//组合数个数记录
	public Comb(){//构造函数
		a = new int[100];
	}
	//打印结果
	public void print(){
		System.out.print(++count + ":\t");
		for(int i = a[0]; i >= 1; i--){
			System.out.print(a[i] + ", ");
		}
		System.out.println();
	}
	// 递归算法实现组合数输出
	public void recursiveComb(int m, int k){
		if(m < k) return;//参数不符合条件
		int i;
		for(i = m; i >= k; i--){
			a[k] = i;
			if(k > 1)
				// 子问题
				recursiveComb(i - 1, k - 1);
			else//k == 1时打印
				print();
		}
	}
	
	//利用栈实现上述的递归算法（这是一种递推算法）
	public void stackComb(int m, int k){
		if(m < k) return;//参数不符合条件
		//创建一个链表栈
		LinkStack lstack = new LinkStack();
		//待压栈的数据对和待退栈的数据
		CombPair p_push, p_pop;
		int i = m;
		while (k > 0)//首先压栈
		{
			a[k] = i;
			p_push = new CombPair(i, k);
			lstack.push(new ElemItem<CombPair>(p_push));
			i--;k--;
		}

		while(lstack.getSize() > 0)
		{
			//弹出栈顶元素并作处理
			p_pop = (CombPair)(lstack.pop().getElem());
			i = p_pop.i;
			k = p_pop.k;
			//改变记录组合结果的数组
			a[k] = i;
			//因为栈顶的元素一定是满足k=1的，所以直接打印
			print();
			// 考虑是否需要再弹出栈中元素
			if (i == k)
			{
				while(i == k)//不断弹出直到元素对两个数不同位置
				{
					if (lstack.getSize() > 0)
					{
						//元素对两个数相同时弹出栈顶元素
						p_pop = (CombPair)(lstack.pop().getElem());
						i = p_pop.i;
						k = p_pop.k;
					}
					else break;
				}
				// 栈中弹出的元素（k不等于1）的进一步处理
				i--;
				a[k] = i;
				while (k > 0 && i >= k)
				{
					// 更新后进行压栈操作
					a[k] = i;
					p_push = new CombPair(i, k);
					lstack.push(new ElemItem<CombPair>(p_push));
					k--;i--;
				}
			}
			else //不需要再弹出栈中元素时（此时k依然等于1）
			{
				i--;		//更新
				p_push = new CombPair(i, k);
				lstack.push(new ElemItem<CombPair>(p_push));//压栈
			}
		}
	}
	
	public static void main(String agrs[]){
		Comb c = new Comb();
		c.a[0] = 4;
		//c.recursiveComb(8, 4);
		c.stackComb(8, 4);
	}
}
