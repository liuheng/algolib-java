/*
 * Created on 2010-7-30
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
 * 顺序查找
 */
public class OrderSearch {
	private StItem[] st;
	private int N = 0, M;
	// 构造函数
	public OrderSearch(int _M){
		M = _M;
		N = 0;
		st = new StItem[_M];
	}
	
	// 键值类型为ElemItem，判断键值key1是否小于键值key2
	private boolean less(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) < 0;
	}
	
	// 判断键值key1是否等于键值key2
	private boolean equals(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) == 0;
	}
	
	// 返回当前表项个数
	public int count(){
		return N;
	}
	
	/** 插入表项x；
	 * 将x插入到合适的位置，函数中包含数组元素项的移动操作；
	 * @param x 待插入的元素项
	 */
	public  void insert(StItem x){
		int i = N;
		ElemItem v = x.GetKey();
		// 移动元素项直至找到合适的位置i，
		while(i > 0 &&  less(v, st[i - 1].GetKey())){
			st[i] = st[i - 1];
			i--;
		}
		// 然后将元素项x插入到位置i
		st[i] = x;
		N++;
	}
	
	/**
	 * 搜索键值为key的表项
	 * @param key	待搜索的表项的键值
	 * @return	返回键值为key的表项
	 */
	public StItem search(ElemItem key){
		int i = 0; 
		/* 从第一个表项开始进行搜索，直至第一个键值不小于
		 * key的表项 */
		for( ; i < N; i++){
			if(!less(st[i].GetKey(), key)) break;
		}
		// 如果所有表项的键值都小于key则说明没有找到
		if(i == N) return null;
		// 如果key与第i个表项的键值相等，则返回第i个表项
		if(equals(key, st[i].GetKey())) return st[i];
		return null;
	}
	
	/**
	 * 返回第k个表项，由于表中表项是紧凑放置的，直接返回st[k]即可
	 * @param k		返回第k个表项
	 * @return	返回第k个表项
	 */
	public StItem Select(int k){
		return st[k];
	}
	
	/**
	 * 返回当前符号表中所有表项
	 */
	public String toString(){
		String s = "";
		for(int i = 0; i < N; i++){
			s = s + st[i].toString();
		}
		return s;
	}
	
	// 测试函数
	public static void main(String args[]){
		OrderSearch OS = new OrderSearch(20);
		/*从文件中读取数据*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// 建立FileReader对象，并实例化为fr
			fr = new FileReader("Search\\JavaBook.txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// 建立BufferedReader对象，并实例化为br
		br = new BufferedReader(fr);
		// 读取每行字符串
		String strLine = null;;
		String _info = "";
		int state = 0;
		ElemItem key = null, info = null;
		while (true)
		{
			try {
				// 从文件中继续读取一行数据
				strLine = br.readLine();
				// 文件读取结束
				if(strLine == null) break;
				
				// 每个记录之间的间隔为空行，记为""
				if(strLine.equalsIgnoreCase("")){
					state = 0;
					info = new ElemItem<String>(_info);
					StItem si = new StItem(key, info);
					// 将表项插入到符号表中
					OS.insert(si);
					// 将内容置为空
					_info = "";
					continue;
				}
				
				// 键值的标识符为@
				if(strLine.charAt(0) == '@'){
					state = 1;
					key = new ElemItem<String>(strLine);
					continue;
				}
				
				// 如果不是间隔行也不是键值标识符，则添加信息行
				_info += strLine + "\n";
				
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
		// 打印符号表
		System.out.println(OS.toString());
		
		// 检索
		ElemItem k = new ElemItem<String>("@TP312JA/C454");
		System.out.println("键值为" + k.elem +" 的表项为：\n");
		System.out.println(OS.search(k));
		
	}
}
