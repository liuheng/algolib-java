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
import List.SingleLink2;
import List.SingleNode;

/**
 * @author Wei LU
 * 基于链表的符号表（无序）
 */
public class LinkSearch extends SingleLink2{
	// 构造函数1
	public LinkSearch(){
		super();
	}
	// 构造函数2
	public LinkSearch(SingleNode _h, SingleNode _t, SingleNode _crr, int _cs){
		super( _h, _t, _crr, _cs);
	}
	
	// 键值类型为ElemItem，判断键值key1是否小于键值key2
	private boolean less(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) < 0;
	}
	
	// 判断键值key1是否等于键值key2
	private boolean equals(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) == 0;
	}
	
	// 返回当前链表中表项的个数
	public int count(){
		return currSize;
	}
	
	/**
	 * 插入表项，直接将表项插入到链表中
	 * @param x	待插入的表项
	 */
	public void insert(StItem x){
		insert(new ElemItem<StItem>(x));
	}
	
	/**
	 * 搜索键值为key的表项
	 * @param key	待检索的表项的键值
	 * @return	返回键值为key的表项
	 */
	public StItem search(ElemItem key){
		// 从链表的表首开始进行搜索
		this.goFirst();
		SingleNode ptr = head;
		while(ptr.getNext() != null){
			// 链表节点逐个进行判断键值是否为key，如果节点键值等于key
			if(equals(key, ((StItem)(ptr.getNext().getElem().elem)).GetKey()))
				// 则返回节点中存放的表项
				return (StItem)(ptr.getNext().getElem().elem);
			// 否则判断下一个节点
			ptr = ptr.getNext();
		}
		// 如果搜索到链表的表尾也没有搜索到，则返回null
		return null;
	}
	
	/**
	 * 返回当前链表中所有表项
	 */
	public String toString(){
		String str = "";
		SingleNode ptr = head;
		while(ptr.getNext() != null){
			str += ptr.getNext().getElem().getElem() + "\n";
			ptr = ptr.getNext();
		}
		return str;
	}
	
	// 测试示例
	public static void main(String args[]){
		LinkSearch LS = new LinkSearch();
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
					LS.insert(si);
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
		System.out.println(LS.toString());
		
		// 检索
		ElemItem k = new ElemItem<String>("@TP312JA/C454");
		System.out.println("键值为" + k.elem +" 的表项为：\n");
		System.out.println(LS.search(k));
	}
}
