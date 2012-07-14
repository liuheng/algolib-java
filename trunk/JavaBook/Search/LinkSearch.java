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
 * ��������ķ��ű�����
 */
public class LinkSearch extends SingleLink2{
	// ���캯��1
	public LinkSearch(){
		super();
	}
	// ���캯��2
	public LinkSearch(SingleNode _h, SingleNode _t, SingleNode _crr, int _cs){
		super( _h, _t, _crr, _cs);
	}
	
	// ��ֵ����ΪElemItem���жϼ�ֵkey1�Ƿ�С�ڼ�ֵkey2
	private boolean less(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) < 0;
	}
	
	// �жϼ�ֵkey1�Ƿ���ڼ�ֵkey2
	private boolean equals(ElemItem key1, ElemItem key2){
		return key1.compareTo(key2) == 0;
	}
	
	// ���ص�ǰ�����б���ĸ���
	public int count(){
		return currSize;
	}
	
	/**
	 * ������ֱ�ӽ�������뵽������
	 * @param x	������ı���
	 */
	public void insert(StItem x){
		insert(new ElemItem<StItem>(x));
	}
	
	/**
	 * ������ֵΪkey�ı���
	 * @param key	�������ı���ļ�ֵ
	 * @return	���ؼ�ֵΪkey�ı���
	 */
	public StItem search(ElemItem key){
		// ������ı��׿�ʼ��������
		this.goFirst();
		SingleNode ptr = head;
		while(ptr.getNext() != null){
			// ����ڵ���������жϼ�ֵ�Ƿ�Ϊkey������ڵ��ֵ����key
			if(equals(key, ((StItem)(ptr.getNext().getElem().elem)).GetKey()))
				// �򷵻ؽڵ��д�ŵı���
				return (StItem)(ptr.getNext().getElem().elem);
			// �����ж���һ���ڵ�
			ptr = ptr.getNext();
		}
		// �������������ı�βҲû�����������򷵻�null
		return null;
	}
	
	/**
	 * ���ص�ǰ���������б���
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
	
	// ����ʾ��
	public static void main(String args[]){
		LinkSearch LS = new LinkSearch();
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
					LS.insert(si);
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
		System.out.println(LS.toString());
		
		// ����
		ElemItem k = new ElemItem<String>("@TP312JA/C454");
		System.out.println("��ֵΪ" + k.elem +" �ı���Ϊ��\n");
		System.out.println(LS.search(k));
	}
}
