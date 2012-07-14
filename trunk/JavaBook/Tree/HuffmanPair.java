/*
 * Created on 2010-5-8
 *
 * Huffman���ڵ������࣬������Huffman
 * ���ڵ��е��źŷ��Ͷ�Ӧ��Ƶ�ʴ�С��
 * HuffmanPair.java
 */
package Tree;

/**
 * @author Wei LU
 *
 * ����������ʱ�����ڵ������Ϊ�ַ�������Ƶ����ɵĶԣ�
 * HuffmanPair.java
 */
public class HuffmanPair implements Comparable{
	//����˽�г�Ա
	//�ַ���
	private String c;
	//�ַ�����Ӧ��Ƶ��
	private double freq;
	//�޲������캯��
	public HuffmanPair(){
		c = "��";
		freq = -1;
	}
	//���������캯��
	public HuffmanPair(String _c,
					double _f){
		c = _c;
		freq = _f;
	}
	//�����ַ�����Ա����
	public void setChar(String _c){
		c = _c;
	}
	//�����ַ�����Ƶ�ʱ���
	public void setFreq(double _f){
		freq = _f;
	}
	//��ȡ�ַ�����Ա����
	public String getChar(){
		return c;
	}
	//��ȡ�ַ�����Ƶ��
	public double getFreq(){
		return freq;
	}

	//ʵ�ֽӿں������Ե�ǰ�������һ������Ƚ�Ƶ�ʴ�С
	public int compareTo(Object arg0) {
		//��ǰ�����С������-1
		if( freq < ((HuffmanPair)(arg0)).freq)
			return -1;
		//��ǰ����ϴ󣬷���1
		else if(freq > ((HuffmanPair)(arg0)).freq)
			return 1;
		//���
		else 
			return 0;
	}
	
	// ���ص�ǰHuffman�ڵ����ݵ��ַ�����ʽ
	public String toString(){
		return "--" + c + " " + freq; 
	}

}
