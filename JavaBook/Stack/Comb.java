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
 * �õݹ�͵��Ʒ�ʵ���������Comb.java
 */

public class Comb {
	int a[];//�������ڼ�¼�����
	int count = 0;//�����������¼
	public Comb(){//���캯��
		a = new int[100];
	}
	//��ӡ���
	public void print(){
		System.out.print(++count + ":\t");
		for(int i = a[0]; i >= 1; i--){
			System.out.print(a[i] + ", ");
		}
		System.out.println();
	}
	// �ݹ��㷨ʵ����������
	public void recursiveComb(int m, int k){
		if(m < k) return;//��������������
		int i;
		for(i = m; i >= k; i--){
			a[k] = i;
			if(k > 1)
				// ������
				recursiveComb(i - 1, k - 1);
			else//k == 1ʱ��ӡ
				print();
		}
	}
	
	//����ջʵ�������ĵݹ��㷨������һ�ֵ����㷨��
	public void stackComb(int m, int k){
		if(m < k) return;//��������������
		//����һ������ջ
		LinkStack lstack = new LinkStack();
		//��ѹջ�����ݶԺʹ���ջ������
		CombPair p_push, p_pop;
		int i = m;
		while (k > 0)//����ѹջ
		{
			a[k] = i;
			p_push = new CombPair(i, k);
			lstack.push(new ElemItem<CombPair>(p_push));
			i--;k--;
		}

		while(lstack.getSize() > 0)
		{
			//����ջ��Ԫ�ز�������
			p_pop = (CombPair)(lstack.pop().getElem());
			i = p_pop.i;
			k = p_pop.k;
			//�ı��¼��Ͻ��������
			a[k] = i;
			//��Ϊջ����Ԫ��һ��������k=1�ģ�����ֱ�Ӵ�ӡ
			print();
			// �����Ƿ���Ҫ�ٵ���ջ��Ԫ��
			if (i == k)
			{
				while(i == k)//���ϵ���ֱ��Ԫ�ض���������ͬλ��
				{
					if (lstack.getSize() > 0)
					{
						//Ԫ�ض���������ͬʱ����ջ��Ԫ��
						p_pop = (CombPair)(lstack.pop().getElem());
						i = p_pop.i;
						k = p_pop.k;
					}
					else break;
				}
				// ջ�е�����Ԫ�أ�k������1���Ľ�һ������
				i--;
				a[k] = i;
				while (k > 0 && i >= k)
				{
					// ���º����ѹջ����
					a[k] = i;
					p_push = new CombPair(i, k);
					lstack.push(new ElemItem<CombPair>(p_push));
					k--;i--;
				}
			}
			else //����Ҫ�ٵ���ջ��Ԫ��ʱ����ʱk��Ȼ����1��
			{
				i--;		//����
				p_push = new CombPair(i, k);
				lstack.push(new ElemItem<CombPair>(p_push));//ѹջ
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
