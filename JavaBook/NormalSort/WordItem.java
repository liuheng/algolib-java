/*
 * Created on 2010-7-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

/**
 * @author Wei LU
 *32λ�ؼ��֣�8λ�ֽ�
 */
public class WordItem implements Comparable{
	// aΪ�������ݣ�λ�������ұ�ţ���0��bitsword-1��
	long  a;
	// ���� �ֵ�λ��
	static final int bitsword = 64;
	// ���� �ֽڵ�λ��
	static final int bitsbyte = 8;
	// ���� �ֵ��ֽ�λ��
	static final int bytesword = bitsword / bitsbyte;
	// ��
	static final int R = (int) Math.pow(2, bitsbyte);
	// ���캯��
	public WordItem(long _a){
		this.a = _a;
	}
	
	/**
	 * ��ȡa�ĵ�b���ֽ�
	 * @param b	��b���ֽ�
	 * @return ��b���ֽڵ�����
	 */
	int digit(int b){
		int _b = (bytesword - 1 - b) * bitsbyte;
		long _a = a >> _b;
		long _b2 = (_a & (long)(R - 1));
		return (int)_b2;
	}
	
	
	/**
	 * ���Ժ���
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
