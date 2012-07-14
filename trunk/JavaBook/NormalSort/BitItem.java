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
 * BitItem �࣬ʵ��16λ�ؼ��֣�1λ�ֽ�
 */
public class BitItem implements Comparable{
	// aΪ�������ݣ�λ�������ұ�ţ���0��bitsword-1��
	short  a;
	// ���� �ֵ�λ��
	static final int bitsword = 16;
	// ���� �ֽڵ�λ��
	static final int bitsbyte = 1;
	// ���� �ֵ��ֽ�λ��
	static final int bytesword = bitsword / bitsbyte;
	// ��
	static final int R = (int) Math.pow(2, bitsbyte);
	// ���캯��
	public BitItem(short _a){
		this.a = _a;
	}
	
	/**
	 * ��ȡa�ĵ�b���ֽ�
	 * @param b	��b���ֽ�
	 * @return ��b���ֽڵ�����
	 */
	int bit(int b){
		b = bitsword - 1 - b;
		return (int)(a / Math.pow(R, b)) % R;
	}
	
	
	/**
	 * ���Ժ���
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
