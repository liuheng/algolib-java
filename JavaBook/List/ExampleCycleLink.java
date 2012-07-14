/*
 * Created on 2010-4-16
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

import Element.ElemItem;

/**
 * @author Administrator
 *
 * 猴子选大王的问题，循环链表的应用ExamlpeCycleLonk.java
 */
public class ExampleCycleLink {
	public static void main(String args[]){
		int num = 10; //猴子的个数
		int count = 0;//猴子报数的计数器
		//建立循环链表来存放猴子的序号
		CycleLink cyLink = new CycleLink();
		//项链表中添加序号
		for(int i = 0; i < num; i++){
			cyLink.append(new ElemItem<Integer>(i));
		}
		System.out.println("1\t2\tout");
		cyLink.goFirst();//从第一只猴子开始
		//用于缓存猴子的状态：元素项为null表示该猴子出列了
		ElemItem e;		
		//每轮1至3报数都将出列一只猴子，所以一共num轮报数
		for(int i = 0; i < num; i++){
			count = 0;
			System.out.println("--------------------");
			while(true){
				//获取当前猴子的信息
				e = cyLink.getCurrVal();
				if(e != null){//该猴子依然具有竞选权
					count++;
					if(count == 3) break;//报数结束
					System.out.print(cyLink.getCurrVal().getElem() + "\t");
					cyLink.next();//报数没有结束的时候继续
				}
				else{
					cyLink.next();//当前猴子没有竞选权，跳过
				}
			}
			System.out.println(cyLink.getCurrVal().getElem());
			cyLink.setCurrVal(null);//更新当前报数是3的猴子的状态
			cyLink.next();
		}
	}
}
