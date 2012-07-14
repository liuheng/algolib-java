/*
 * Created on 2010-7-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

/**
 * @author Wei LU
 *32位关键字，8位字节
 */
public class WordItem implements Comparable{
	// a为数据内容，位数从左到右编号：从0到bitsword-1。
	long  a;
	// 数据 字的位数
	static final int bitsword = 64;
	// 数据 字节的位数
	static final int bitsbyte = 8;
	// 数据 字的字节位数
	static final int bytesword = bitsword / bitsbyte;
	// 基
	static final int R = (int) Math.pow(2, bitsbyte);
	// 构造函数
	public WordItem(long _a){
		this.a = _a;
	}
	
	/**
	 * 获取a的第b个字节
	 * @param b	第b个字节
	 * @return 第b个字节的内容
	 */
	int digit(int b){
		int _b = (bytesword - 1 - b) * bitsbyte;
		long _a = a >> _b;
		long _b2 = (_a & (long)(R - 1));
		return (int)_b2;
	}
	
	
	/**
	 * 测试函数
	 */
	public static void main(String args[]){
		
		long b = Long.parseLong("0101110100001001101100011101110000100100101100010110111110000010", 2);
		WordItem WI = new WordItem(b);
		System.out.println("\n" + b);
		
		System.out.println(Long.toBinaryString(b));
		
		System.out.println(WI.digit(0));
		
		for(int i = 0; i < 8; i++)
			System.out.print(i + ": " + Integer.toBinaryString(WI.digit(i)) + "; ");
		
	}

	
	public int compareTo(Object o) {
		return 0;
	} 
	
	public String toString(){
		return Long.toBinaryString(this.digit(0)) + "," + Long.toBinaryString(this.digit(1)) + "";
		//return "" + a;
		
	}
}
