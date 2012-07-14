/*
 * Created on 2010-7-28
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Graph;

/**
 * @author Wei LU
 *
 * ���ļ���ȡͼ�ṹ��������ͼ���ݽṹ��
 */
import java.io.*;  

public class BuildGraph {
	/**
	 * ���ļ��ж�ȡͼ�ṹ���ݲ�����ͼ���ݽṹ
	 * @param filename	�ļ���
	 * @return ͼ�ṹ
	 */
	public static GraphLnk BulidGraphFromFile(String filename){
		// �������
		int N = -1;
		// �����ص�ͼ�ṹ
		GraphLnk G = null;
		try {    
			// ��ȡ���ļ�
			FileInputStream fstream = new FileInputStream(filename);    
			// ���ļ���תΪ���������� 
			DataInputStream in = new DataInputStream(fstream);    
			// ���ȶ�ȡ��һ�У���ֵΪͼ�������
			if(in.available() != 0) 
			N = in.readInt();
			// ����ͼ�ṹ
			G = new GraphLnk(N);
			// ���ж�ȡ�ļ������ݣ�������ÿ��������ͼ����ӱ�
			while (in.available() != 0) {    
				// ��ȡһ���ַ�
				String line = in.readLine();
				// ���ַ��ָ�ָ��Ϊ����
				String e[] = line.split(",");
				// �ָ�õ����ַ�����Ϊ {��㣬�յ㣬Ȩֵ}
				int i = Integer.parseInt(e[0]);
				int j = Integer.parseInt(e[1]);
				int w = Integer.parseInt(e[2]);
				// ��ͼ����ӱ�
				G.setEdgeWt(i, j, w);
			}    
			in.close();    
		} catch (Exception e) {    
			e.printStackTrace();
		}      
		return G;
	}


	public static void main(String args[]){
		BuildGraph.BulidGraphFromFile("Graph\\graph1.txt");
	}
}
