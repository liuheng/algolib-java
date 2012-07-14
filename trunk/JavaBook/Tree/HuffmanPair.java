/*
 * Created on 2010-5-8
 *
 * Huffman树节点内容类，描述了Huffman
 * 树节点中的信号符和对应的频率大小，
 * HuffmanPair.java
 */
package Tree;

/**
 * @author Wei LU
 *
 * 霍夫曼编码时，树节点的类型为字符串及其频率组成的对，
 * HuffmanPair.java
 */
public class HuffmanPair implements Comparable{
	//两个私有成员
	//字符串
	private String c;
	//字符串对应的频率
	private double freq;
	//无参数构造函数
	public HuffmanPair(){
		c = "□";
		freq = -1;
	}
	//带参数构造函数
	public HuffmanPair(String _c,
					double _f){
		c = _c;
		freq = _f;
	}
	//设置字符串成员变量
	public void setChar(String _c){
		c = _c;
	}
	//设置字符串的频率变量
	public void setFreq(double _f){
		freq = _f;
	}
	//获取字符串成员变量
	public String getChar(){
		return c;
	}
	//获取字符串的频率
	public double getFreq(){
		return freq;
	}

	//实现接口函数，对当前对象和另一个对象比较频率大小
	public int compareTo(Object arg0) {
		//当前对象较小，返回-1
		if( freq < ((HuffmanPair)(arg0)).freq)
			return -1;
		//当前对象较大，返回1
		else if(freq > ((HuffmanPair)(arg0)).freq)
			return 1;
		//相等
		else 
			return 0;
	}
	
	// 返回当前Huffman节点内容的字符串形式
	public String toString(){
		return "--" + c + " " + freq; 
	}

}
