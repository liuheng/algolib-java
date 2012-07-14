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
 * 从文件读取图结构，并构造图数据结构；
 */
import java.io.*;  

public class BuildGraph {
	/**
	 * 从文件中读取图结构数据并构造图数据结构
	 * @param filename	文件名
	 * @return 图结构
	 */
	public static GraphLnk BulidGraphFromFile(String filename){
		// 顶点个数
		int N = -1;
		// 待返回的图结构
		GraphLnk G = null;
		try {    
			// 读取流文件
			FileInputStream fstream = new FileInputStream(filename);    
			// 将文件流转为数据输入流 
			DataInputStream in = new DataInputStream(fstream);    
			// 首先读取第一行，其值为图顶点个数
			if(in.available() != 0) 
			N = in.readInt();
			// 构造图结构
			G = new GraphLnk(N);
			// 逐行读取文件中数据，并根据每行数据向图中添加边
			while (in.available() != 0) {    
				// 读取一行字符
				String line = in.readLine();
				// 将字符分割，分割符为逗号
				String e[] = line.split(",");
				// 分割得到的字符数组为 {起点，终点，权值}
				int i = Integer.parseInt(e[0]);
				int j = Integer.parseInt(e[1]);
				int w = Integer.parseInt(e[2]);
				// 向图中添加边
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
