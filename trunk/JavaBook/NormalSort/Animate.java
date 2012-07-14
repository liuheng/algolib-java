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
 * 继承Applet和Runnable的抽象类
 */
public abstract class Animate extends Applet implements Runnable{
	/*_old颜色表示的是被更换前的值，
	 * _new颜色表示的是被更换之后的值*/
	Color _old = Color.white;
	Color _new = Color.red;
	Graphics g;
	/*本线程*/
	Thread animatorThread;
	/*N表示的是本排序对应的随机数的个数*/
	int N;
	/*a数组长度为N，待排序的数组*/
	ITEM[] a;
	
	/*为了显示，用于暂停程序*/
	private void pause() throws InterruptedException{
		Thread.sleep(Parameter.sleeptime);
	}
	
	public void init(){
		this.resize(640, 480);
	}
	/*线程启动*/
	public void start(){
		init();
		g = getGraphics();
		new Thread(this).start();
	}
	/*线程结束*/
	public void stop(){animatorThread = null;}
	//线程运行函数
	public void  run(){
		/*读取配置参数*/
		N = Parameter.N;
		a = new ITEM[N];
		String strLine = null;// 从文件读取一行字符串
		/*产生随机数，并首先在图上画出每个值对应的点*/
		int i = 0;
		//for(int i = 0; i < N; i++){
		/*从文件中读取数据*/
		FileReader fr = null;
		BufferedReader br = null;
		try
		{
			// 建立FileReader对象，并实例化为fr
			fr = new FileReader(Parameter.filename);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// 建立BufferedReader对象，并实例化为br
		br = new BufferedReader(fr);
		
		while (i < N)
		{
			try {
				// 从文件中继续读取一行数据
				strLine = br.readLine();
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			if(strLine == null) break;
			/*
			// 除基数排序之外，都是用double类型的元素项
			double d = Double.parseDouble(strLine);
			a[i] = new ITEM<Double>(d);
			dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _new);
			*/
			
			/*
			// 折半快速排序
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
		
		//调用排序函数，这里的sort是抽象函数
		sort(a, 0, N - 1);
	}
	
	/*将数组下标i转为在图像显示的横坐标*/
	int X(int i){
		return (i * getSize().width) / N;
	}
	/*将数组的值转为在图像显示的纵坐标*/
	int Y(double v){
		return (int)((1 - v)*getSize().height);
	}
	/*在位置（x, y）处显示颜色为c的一个点*/
	void dot(int x, int y, Color c){
		g.setColor(c);
		g.fillOval(x, y, 5, 5);
	}
	/*交换第i和第j个元素，
	 * 原先第i个位置和第j个位置颜色为_old，
	 * 交换后第i位置和第j位置颜色为_new*/
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
	
	/*如果第i个元素大于第j个元素，则交换着两个元素*/
	void compExch(ITEM[] a, int i, int j) throws InterruptedException{
		if(a[i].compareTo(a[j]) == 1){
			exch(a, i, j);
		}
	}
	
	/*拷贝数据，将i位置的元素重置为val，重置前的值颜色为_old
	 * 重置后的颜色为_new*/
	void cpyVal(ITEM[] a, int i, ITEM val) throws InterruptedException{
		dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _old);
		a[i] = val;
		dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _new);
	}
	
	/**
	 * 显示数组a形成的曲线
	 * @param a	待显示曲线的数值
	 */
	void showCurve(ITEM[] a){
		// 首先刷新前景
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, 640, 480);
		// 打印每个点
		for(int i = 0; i < a.length; i++){
			dot(X(i), Y(((Double)(a[i].elem)).doubleValue()), _new);
		}
	}
	
	/**
	 * 接口函数，抽象函数
	 * @param a	待排序的数组
	 * @param l	待排序的范围的左端下标
	 * @param r 待排序的范围的右端下标
	 */
	abstract void sort(ITEM a[], int l, int r);
}
