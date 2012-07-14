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
 * �۰���ң���������ķ��ű�Ĳ��ң�
 * Window - Preferences - Java - Code Style - Code Templates
 * 
 */
public class BinarySearch {
	private StItem[] st;
	// ���캯��
	public BinarySearch(StItem[] _S){
		st= _S;
	}
	
	// ��ֵ����ΪElemItem���жϼ�ֵkey1�Ƿ�С�ڼ�ֵkey2
	private boolean less(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) < 0;
	}
	
	// �жϼ�ֵkey1�Ƿ���ڼ�ֵkey2
	private boolean equals(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) == 0;
	}
	
	/**
	 * �۰����
	 * @param l	�۰��������±�
	 * @param r �۰�����Ҷ��±�
	 * @param key	�����ҵļ�ֵ
	 * @return	���ݼ�ֵkey���в���
	 */
	public StItem search(int l, int r, ElemItem key){
		// ���l����r��˵������ʧ��
		if(l > r) return null;
		// �ж�l��r�м�ı���st[m]�ļ�ֵ�Ƿ����key
		int m = (l + r) / 2;
		System.out.println("|  " + l + "\t|  " + m + "\t|  " + r + "\t|");
		// ������ڣ��򷵻�st[m]
		if(equals(key, st[m].GetKey())) 
			return st[m];
		// ���st[m]��С��������벿�ֽ��в���
		else if(less(key, st[m].GetKey())) 
			return search(l, m - 1, key);
		// ������st[m]�ϴ������а벿�ֽ��в���
		else return search(m + 1, r, key);
	}
	
	public static void main(String args[]){
		StItem[] SI = new StItem[1000000];
		/*���ļ��ж�ȡ����*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// ����FileReader���󣬲�ʵ����Ϊfr
			fr = new FileReader("Search\\exp.txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// ����BufferedReader���󣬲�ʵ����Ϊbr
		br = new BufferedReader(fr);
		// ��ȡÿ���ַ���
		String strLine = null;;

		int c = 0;
		while (true)
		{
			try {
				// ���ļ��м�����ȡһ������
				strLine = br.readLine();
				// �ļ���ȡ����
				if(strLine == null) break;
				// ��һ������תΪ����
				int it = Integer.valueOf(strLine);
				// ������ת��ֵΪ��ֵ
				ElemItem key = new ElemItem<Integer>(it);
				// �����ļ�����������������ֱ�Ӳ��뵽����SI��
				if(c < SI.length)
					SI[c++] = new StItem(key, null);
				else break;
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		// �ر��ļ���
		try {
			fr.close();
			br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		// �������ֲ��Ҷ���
		
		BinarySearch BS = new BinarySearch(SI);
		ElemItem key = new ElemItem<Integer>(11);
		System.out.println("|  lft\t|  mid\t|  rght\t|" );
		System.out.println("-------------------------" );
		  
		/* �˴�Ϊ���Եĳ������ */  
		// ����ʱ���time1   
		long time1 = System.currentTimeMillis();   
		StItem Rt = BS.search(0, SI.length - 1, key);
		// ����ʱ���time2   
		long time2 = System.currentTimeMillis();   
		// ��������ʱ��ֵ   
		long period = time2 - time1; 
		System.out.println("-------------------------" );
		System.out.println(Rt);
		System.out.println(period);
	}
}
