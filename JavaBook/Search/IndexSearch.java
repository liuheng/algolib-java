/*
 * Created on 2010-7-30
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
import Element.Student;



/**
 * @author Wei LU
 *
 * 关键字索引查找
 */
public class IndexSearch {
	private IntKeyItem[] st;
	private int M, N;
	// 构造函数
	public IndexSearch(int _M){
		N = 0;
		M = _M;
		st = new IntKeyItem[_M];
	}
	/**
	 * 当前符号表的大小
	 * @return 个数
	 */
	public int count(){
		return N;
	}
	/**
	 * 将表项x插入符号表中
	 * @param x	待插入的表项
	 * x的键值为x在st数组中的下标
	 */
	public void insert(IntKeyItem x){
		if(x.key() < 0 || x.key() >= M){
			System.out.println("键值错误，无法插入！");
			return;
		}
		st[x.key()] = x;
		N++;
	}
	/**
	 * 删除键值为key的项
	 * @param key	键值
	 * 删除操作通过设置st[key]为null来实现
	 */
	public void remove(int key){
		if(key < 0 || key >= M){
			System.out.println("键值错误，无法删除！");
			return;
		}
		st[key] = null;
		N--;
	}
	/**
	 * 搜索键值为key的项
	 * @param key
	 * @return
	 */
	public IntKeyItem search(int key){
		if(key < 0 || key >= M){
			System.out.println("键值错误，无法检索！");
			return null;
		}
		return st[key];
	}
	/**
	 * 选择第k个项
	 * @param k  返回的序号为k
	 * @return	返回的第k个序号
	 */
	public IntKeyItem select(int k){
		for(int i = 0; i < st.length; i++)
			if(st[i] != null && k-- == 0)
				return st[i];
		return null;
	}
	/**
	 * 返回当前符号表中所有元素
	 */
	public String toString(){
		String s = "";
		for(int i = 0; i < M; i++){
			if(st[i] != null) s +=  st[i] + ";\n";
			else s += "null;\n";
		}
		return s;
	}
	// 测试实例，从文件中获取数据并构造符号表表项
	public static void main(String args[]){
		IndexSearch ST = new IndexSearch(10);
		/*从文件中读取数据*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// 建立FileReader对象，并实例化为fr
			fr = new FileReader("Search\\student1.txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// 建立BufferedReader对象，并实例化为br
		br = new BufferedReader(fr);
		// 读取每行字符串
		String strLine = null;;
		
		while (true)
		{
			try {
				// 从文件中继续读取一行数据
				strLine = br.readLine();
				if(strLine == null) break;
				// 将每行字符串进行拆分
				String st[] = strLine.split("\t");
				// 将拆分后的字符分别赋予Student类的各个成员
				int id = Integer.parseInt(st[0]);
				String name = st[1];
				int age = Integer.parseInt(st[2]);
				int score = Integer.parseInt(st[3]);
				// 创建Student对象
				Student s = new Student(id, name, age, score);
				// 创建新的符号表表项
				IntKeyItem it = new IntKeyItem(id, new ElemItem<Student>(s));
				// 将表项插入到符号表中
				ST.insert(it);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		// 打印符号表ST
		System.out.println(ST);
		// 删除第5项
		ST.remove(5);
		// 删除第15项
		ST.remove(15);
		System.out.println("删除第5和15项后符号表：");
		System.out.println(ST);
		// 搜索关键字为6的表项
		System.out.println("键值为6的表项：");
		System.out.println(ST.search(6));
		// 选择第7个表项
		System.out.println("表中第7项为：");
		System.out.println(ST.select(7));
	}
}
