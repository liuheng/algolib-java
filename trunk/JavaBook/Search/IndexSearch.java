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
 * �ؼ�����������
 */
public class IndexSearch {
	private IntKeyItem[] st;
	private int M, N;
	// ���캯��
	public IndexSearch(int _M){
		N = 0;
		M = _M;
		st = new IntKeyItem[_M];
	}
	/**
	 * ��ǰ���ű�Ĵ�С
	 * @return ����
	 */
	public int count(){
		return N;
	}
	/**
	 * ������x������ű���
	 * @param x	������ı���
	 * x�ļ�ֵΪx��st�����е��±�
	 */
	public void insert(IntKeyItem x){
		if(x.key() < 0 || x.key() >= M){
			System.out.println("��ֵ�����޷����룡");
			return;
		}
		st[x.key()] = x;
		N++;
	}
	/**
	 * ɾ����ֵΪkey����
	 * @param key	��ֵ
	 * ɾ������ͨ������st[key]Ϊnull��ʵ��
	 */
	public void remove(int key){
		if(key < 0 || key >= M){
			System.out.println("��ֵ�����޷�ɾ����");
			return;
		}
		st[key] = null;
		N--;
	}
	/**
	 * ������ֵΪkey����
	 * @param key
	 * @return
	 */
	public IntKeyItem search(int key){
		if(key < 0 || key >= M){
			System.out.println("��ֵ�����޷�������");
			return null;
		}
		return st[key];
	}
	/**
	 * ѡ���k����
	 * @param k  ���ص����Ϊk
	 * @return	���صĵ�k�����
	 */
	public IntKeyItem select(int k){
		for(int i = 0; i < st.length; i++)
			if(st[i] != null && k-- == 0)
				return st[i];
		return null;
	}
	/**
	 * ���ص�ǰ���ű�������Ԫ��
	 */
	public String toString(){
		String s = "";
		for(int i = 0; i < M; i++){
			if(st[i] != null) s +=  st[i] + ";\n";
			else s += "null;\n";
		}
		return s;
	}
	// ����ʵ�������ļ��л�ȡ���ݲ�������ű����
	public static void main(String args[]){
		IndexSearch ST = new IndexSearch(10);
		/*���ļ��ж�ȡ����*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// ����FileReader���󣬲�ʵ����Ϊfr
			fr = new FileReader("Search\\student1.txt");
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// ����BufferedReader���󣬲�ʵ����Ϊbr
		br = new BufferedReader(fr);
		// ��ȡÿ���ַ���
		String strLine = null;;
		
		while (true)
		{
			try {
				// ���ļ��м�����ȡһ������
				strLine = br.readLine();
				if(strLine == null) break;
				// ��ÿ���ַ������в��
				String st[] = strLine.split("\t");
				// ����ֺ���ַ��ֱ���Student��ĸ�����Ա
				int id = Integer.parseInt(st[0]);
				String name = st[1];
				int age = Integer.parseInt(st[2]);
				int score = Integer.parseInt(st[3]);
				// ����Student����
				Student s = new Student(id, name, age, score);
				// �����µķ��ű����
				IntKeyItem it = new IntKeyItem(id, new ElemItem<Student>(s));
				// ��������뵽���ű���
				ST.insert(it);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
		}
		// ��ӡ���ű�ST
		System.out.println(ST);
		// ɾ����5��
		ST.remove(5);
		// ɾ����15��
		ST.remove(15);
		System.out.println("ɾ����5��15�����ű�");
		System.out.println(ST);
		// �����ؼ���Ϊ6�ı���
		System.out.println("��ֵΪ6�ı��");
		System.out.println(ST.search(6));
		// ѡ���7������
		System.out.println("���е�7��Ϊ��");
		System.out.println(ST.select(7));
	}
}
