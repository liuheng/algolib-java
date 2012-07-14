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
 * ˳�����
 */
public class OrderSearch {
	private StItem[] st;
	private int N = 0, M;
	// ���캯��
	public OrderSearch(int _M){
		M = _M;
		N = 0;
		st = new StItem[_M];
	}
	
	// ��ֵ����ΪElemItem���жϼ�ֵkey1�Ƿ�С�ڼ�ֵkey2
	private boolean less(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) < 0;
	}
	
	// �жϼ�ֵkey1�Ƿ���ڼ�ֵkey2
	private boolean equals(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) == 0;
	}
	
	// ���ص�ǰ�������
	public int count(){
		return N;
	}
	
	/** �������x��
	 * ��x���뵽���ʵ�λ�ã������а�������Ԫ������ƶ�������
	 * @param x �������Ԫ����
	 */
	public  void insert(StItem x){
		int i = N;
		ElemItem v = x.GetKey();
		// �ƶ�Ԫ����ֱ���ҵ����ʵ�λ��i��
		while(i > 0 &&  less(v, st[i - 1].GetKey())){
			st[i] = st[i - 1];
			i--;
		}
		// Ȼ��Ԫ����x���뵽λ��i
		st[i] = x;
		N++;
	}
	
	/**
	 * ������ֵΪkey�ı���
	 * @param key	�������ı���ļ�ֵ
	 * @return	���ؼ�ֵΪkey�ı���
	 */
	public StItem search(ElemItem key){
		int i = 0; 
		/* �ӵ�һ�����ʼ����������ֱ����һ����ֵ��С��
		 * key�ı��� */
		for( ; i < N; i++){
			if(!less(st[i].GetKey(), key)) break;
		}
		// ������б���ļ�ֵ��С��key��˵��û���ҵ�
		if(i == N) return null;
		// ���key���i������ļ�ֵ��ȣ��򷵻ص�i������
		if(equals(key, st[i].GetKey())) return st[i];
		return null;
	}
	
	/**
	 * ���ص�k��������ڱ��б����ǽ��շ��õģ�ֱ�ӷ���st[k]����
	 * @param k		���ص�k������
	 * @return	���ص�k������
	 */
	public StItem Select(int k){
		return st[k];
	}
	
	/**
	 * ���ص�ǰ���ű������б���
	 */
	public String toString(){
		String s = "";
		for(int i = 0; i < N; i++){
			s = s + st[i].toString();
		}
		return s;
	}
	
	// ���Ժ���
	public static void main(String args[]){
		OrderSearch OS = new OrderSearch(20);
		/*���ļ��ж�ȡ����*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// ����FileReader���󣬲�ʵ����Ϊfr
			fr = new FileReader("Search\\JavaBook.txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// ����BufferedReader���󣬲�ʵ����Ϊbr
		br = new BufferedReader(fr);
		// ��ȡÿ���ַ���
		String strLine = null;;
		String _info = "";
		int state = 0;
		ElemItem key = null, info = null;
		while (true)
		{
			try {
				// ���ļ��м�����ȡһ������
				strLine = br.readLine();
				// �ļ���ȡ����
				if(strLine == null) break;
				
				// ÿ����¼֮��ļ��Ϊ���У���Ϊ""
				if(strLine.equalsIgnoreCase("")){
					state = 0;
					info = new ElemItem<String>(_info);
					StItem si = new StItem(key, info);
					// ��������뵽���ű���
					OS.insert(si);
					// ��������Ϊ��
					_info = "";
					continue;
				}
				
				// ��ֵ�ı�ʶ��Ϊ@
				if(strLine.charAt(0) == '@'){
					state = 1;
					key = new ElemItem<String>(strLine);
					continue;
				}
				
				// ������Ǽ����Ҳ���Ǽ�ֵ��ʶ�����������Ϣ��
				_info += strLine + "\n";
				
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
		// ��ӡ���ű�
		System.out.println(OS.toString());
		
		// ����
		ElemItem k = new ElemItem<String>("@TP312JA/C454");
		System.out.println("��ֵΪ" + k.elem +" �ı���Ϊ��\n");
		System.out.println(OS.search(k));
		
	}
}
