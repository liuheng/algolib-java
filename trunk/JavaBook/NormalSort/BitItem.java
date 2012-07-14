/*
 * Created on 2010-7-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

/**
 * @author Wei LU
 *
 * BitItem 类，实现16位关键字，1位字节
 */
public class BitItem implements Comparable{
	// a为数据内容，位数从左到右编号：从0到bitsword-1。
	short  a;
	// 数据 字的位数
	static final int bitsword = 16;
	// 数据 字节的位数
	static final int bitsbyte = 1;
	// 数据 字的字节位数
	static final int bytesword = bitsword / bitsbyte;
	// 基
	static final int R = (int) Math.pow(2, bitsbyte);
	// 构造函数
	public BitItem(short _a){
		this.a = _a;
	}
	
	/**
	 * 获取a的第b个字节
	 * @param b	第b个字节
	 * @return 第b个字节的内容
	 */
	int bit(int b){
		b = bitsword - 1 - b;
		return (int)(a / Math.pow(R, b)) % R;
	}
	
	
	/**
	 * 测试函数
	 */
	public static void main(String args[]){
		int a = 30000;
		System.out.println(Integer.toBinaryString(a));
		BitItem BI = new BitItem((short)a);
		for(int i = 0; i <= 15; i++)
			System.out.print(BI.bit(i));
	}

	
	public int compareTo(Object o) {
		return 0;
	} 
	
	public String toString(){
		return a + "";
	}
}
