/*
 * Created on 2010-6-21
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package NormalSort;

import java.applet.Applet;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * @author Wei LU
 *
 * �̳�Applet��Runnable�ĳ�����
 */
public abstract class Animate extends Applet implements Runnable{
	/*_old��ɫ��ʾ���Ǳ�����ǰ��ֵ��
	 * _new��ɫ��ʾ���Ǳ�����֮���ֵ*/
	Color _old = Color.white;
	Color _new = Color.red;
	Graphics g;
	/*���߳�*/
	Thread animatorThread;
	/*N��ʾ���Ǳ������Ӧ��������ĸ���*/
	int N;
	/*a���鳤��ΪN�������������*/
	ITEM[] a;
	
	/*Ϊ����ʾ��������ͣ����*/
	private void pause() throws InterruptedException{
		Thread.sleep(Parameter.sleeptime);
	}
	
	public void init(){
		this.resize(640, 480);
	}
	/*�߳�����*/
	public void start(){
		init();
		g = getGraphics();
		new Thread(this).start();
	}
	/*�߳̽���*/
	public void stop(){animatorThread = null;}
	//�߳����к���
	public void  run(){
		/*��ȡ���ò���*/
		N = Parameter.N;
		a = new ITEM[N];
		String strLine = null;// ���ļ���ȡһ���ַ���
		/*�������������������ͼ�ϻ���ÿ��ֵ��Ӧ�ĵ�*/
		int i = 0;
		//for(int i = 0; i < N; i++){
		/*���ļ��ж�ȡ����*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// ����FileReader���󣬲�ʵ����Ϊfr
			fr = new FileReader(Parameter.filename);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// ����BufferedReader���󣬲�ʵ����Ϊbr
		br = new BufferedReader(fr);
		
		while (i < N)
		{
			try {
				// ���ļ��м�����ȡһ������
				strLine = br.readLine();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			if(strLine == null) break;
			/*
			// ����������֮�⣬������double���͵�Ԫ����
			double d = Double.parseDouble(strLine);
			a[i] = new ITEM<Double>(d);
			dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _new);
			*/
			
			/*
			// �۰��������
			short d = (short)(Integer.parseInt(strLine));
			a[i] = new ITEM<BitItem>(new BitItem(d));
			i++;
			*/
			
			long d = (long)(Long.parseLong(strLine, 2));
			a[i] = new ITEM<WordItem>(new WordItem(d));
			i++;
		}
	
		try {
			fr.close();
			br.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//�����������������sort�ǳ�����
		sort(a, 0, N - 1);
	}
	
	/*�������±�iתΪ��ͼ����ʾ�ĺ�����*/
	int X(int i){
		return (i * getSize().width) / N;
	}
	/*�������ֵתΪ��ͼ����ʾ��������*/
	int Y(double v){
		return (int)((1 - v)*getSize().height);
	}
	/*��λ�ã�x, y������ʾ��ɫΪc��һ����*/
	void dot(int x, int y, Color c){
		g.setColor(c);
		g.fillOval(x, y, 5, 5);
	}
	/*������i�͵�j��Ԫ�أ�
	 * ԭ�ȵ�i��λ�ú͵�j��λ����ɫΪ_old��
	 * �������iλ�ú͵�jλ����ɫΪ_new*/
	void exch(ITEM[] a, int i, int j) throws InterruptedException{
		ITEM t = a[i];
		a[i] = a[j];
		a[j] = t;
		
		dot(X(i), Y(((Double)(a[j].elem)).doubleValue()), _old);
		dot(X(j), Y(((Double)(a[i].elem)).doubleValue()), _old);
		dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _new);
		dot(X(j), Y(((Double)(a[j].elem)).doubleValue()), _new);
		pause();
	}
	
	/*�����i��Ԫ�ش��ڵ�j��Ԫ�أ��򽻻�������Ԫ��*/
	void compExch(ITEM[] a, int i, int j) throws InterruptedException{
		if(a[i].compareTo(a[j]) == 1){
			exch(a, i, j);
		}
	}
	
	/*�������ݣ���iλ�õ�Ԫ������Ϊval������ǰ��ֵ��ɫΪ_old
	 * ���ú����ɫΪ_new*/
	void cpyVal(ITEM[] a, int i, ITEM val) throws InterruptedException{
		dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _old);
		a[i] = val;
		dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _new);
	}
	
	/**
	 * ��ʾ����a�γɵ�����
	 * @param a	����ʾ���ߵ���ֵ
	 */
	void showCurve(ITEM[] a){
		// ����ˢ��ǰ��
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		// ��ӡÿ����
		for(int i = 0; i < a.length; i++){
			dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _new);
		}
	}
	
	/**
	 * �ӿں�����������
	 * @param a	�����������
	 * @param l	������ķ�Χ������±�
	 * @param r ������ķ�Χ���Ҷ��±�
	 */
	abstract void sort(ITEM a[], int l, int r);
}
