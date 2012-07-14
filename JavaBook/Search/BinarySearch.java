/*
 * Created on 2010-7-31
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Search;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * 折半查找（基于数组的符号表的查找）
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 */
public class BinarySearch {
	private StItem[] st;
	// 构造函数
	public BinarySearch(StItem[] _S){
		st= _S;
	}
	
	// 键值类型为ElemItem，判断键值key1是否小于键值key2
	private boolean less(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) < 0;
	}
	
	// 判断键值key1是否等于键值key2
	private boolean equals(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) == 0;
	}
	
	/**
	 * 折半查找
	 * @param l	折半查找左端下标
	 * @param r 折半查找右端下标
	 * @param key	待查找的键值
	 * @return	根据键值key进行查找
	 */
	public StItem search(int l, int r, ElemItem key){
		// 如果l大于r则说明查找失败
		if(l > r) return null;
		// 判断l和r中间的表项st[m]的键值是否等于key
		int m = (l + r) / 2;
		System.out.println("|  " + l + "\t|  " + m + "\t|  " + r + "\t|");
		// 如果等于，则返回st[m]
		if(equals(key, st[m].GetKey())) 
			return st[m];
		// 如果st[m]较小，则在左半部分进行查找
		else if(less(key, st[m].GetKey())) 
			return search(l, m - 1, key);
		// 否则，若st[m]较大，则在有半部分进行查找
		else return search(m + 1, r, key);
	}
	
	public static void main(String args[]){
		StItem[] SI = new StItem[1000000];
		/*从文件中读取数据*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// 建立FileReader对象，并实例化为fr
			fr = new FileReader("Search\\exp.txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// 建立BufferedReader对象，并实例化为br
		br = new BufferedReader(fr);
		// 读取每行字符串
		String strLine = null;;

		int c = 0;
		while (true)
		{
			try {
				// 从文件中继续读取一行数据
				strLine = br.readLine();
				// 文件读取结束
				if(strLine == null) break;
				// 将一行内容转为整数
				int it = Integer.valueOf(strLine);
				// 将整数转赋值为键值
				ElemItem key = new ElemItem<Integer>(it);
				// 由于文件中数据已排序，所以直接插入到数组SI中
				if(c < SI.length)
					SI[c++] = new StItem(key, null);
				else break;
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		// 关闭文件流
		try {
			fr.close();
			br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// 创建二分查找对象
		
		BinarySearch BS = new BinarySearch(SI);
		ElemItem key = new ElemItem<Integer>(11);
		System.out.println("|  lft\t|  mid\t|  rght\t|" );
		System.out.println("-------------------------" );
		  
		/* 此处为测试的程序代码 */  
		// 设置时间点time1   
		long time1 = System.currentTimeMillis();   
		StItem Rt = BS.search(0, SI.length - 1, key);
		// 设置时间点time2   
		long time2 = System.currentTimeMillis();   
		// 计算运行时间值   
		long period = time2 - time1; 
		System.out.println("-------------------------" );
		System.out.println(Rt);
		System.out.println(period);
	}
}
