/*
 * Created on 2010-5-8
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;

/**
 * @author Wei LU
 *
 * 霍夫曼树，主要成员变量为树的根节点，
 * 主要成员函数为建树和树的打印
 * HuffmanTree.java
 */
public class HuffmanTree {
	//私有成员变量
	//树根节点
	private LinkBinTreeNode root;
	
	//无参数构造函数
	public HuffmanTree(){
		root = null;
	}
	
	//有参数的构造函数
	public HuffmanTree(LinkBinTreeNode r){
		root = r;
	}
	
	//建立huffman树，入参为链表
	//链表元素项为HuffmanPair
	public void buildTree(LinkBinTreeNode hufflist[]){
		//首先对hufflist的元素项排序
		//数组长度
		int n = hufflist.length;
		//数组中第一个有效节点的位置
		int validpos = 0;
		//简单的选择排序
		for(int i = 0; i < n; i++){
			for(int j = i + 1; j < n; j++){
				//如果第i的频率大于第j的频率，则交换
				if(((HuffmanPair)(
						hufflist[i].getElem().getElem()
						)).compareTo(
					(HuffmanPair)(
						hufflist[j].getElem().getElem()
						)) == 1){
					LinkBinTreeNode t = hufflist[i];
					hufflist[i] = hufflist[j];
					hufflist[j] = t;
				}
			}
		}//排序完成
		
		//开始建树
		while(validpos != n -1){
			//新建节点用于表示两个子树的根节点
			LinkBinTreeNode r 
				= new LinkBinTreeNode();
			//设置根节点r的左右子树
			r.setLeft(hufflist[validpos]);
			r.setRight(hufflist[validpos + 1]);
			//将左右子节点的频率相加
			double _f = 
				((HuffmanPair)(
					hufflist[validpos].getElem().getElem()
					)).getFreq()
			  + ((HuffmanPair)(
			  		hufflist[validpos+1].getElem().getElem()
			  		)).getFreq();
			//设置节点的频率，同时节点的字符用"□"表示
			r.setElem(new ElemItem<HuffmanPair>(
					new HuffmanPair("□", _f)));
			//有效位置向后移
			validpos++;
			//重设validpos位置为新建的节点
			hufflist[validpos] = r;
			//调整validpos节点的位置，
			//因为其频率值的大小不一定是最小的
			int idx;
			//validpos位置的huffmanPair
			HuffmanPair tt1 =
				(HuffmanPair)(
					hufflist[validpos].getElem().getElem());
			//定位第一个比tt1的频率大的节点的位置idx
			for(idx = validpos + 1; idx < n; idx++){
				HuffmanPair tt2 = 
				(HuffmanPair)(
					hufflist[idx].getElem().getElem());
				//找到第一个大于idx
				if(tt1.compareTo(tt2) <= 0){
					break;
				}
			}
			//将validpos节点缓存，
			//并将validpos～idx - 1huffmanPair一一前移
			LinkBinTreeNode t = hufflist[validpos];
			for(int i = validpos; i < idx - 1; i++){
				hufflist[i] = hufflist[i + 1];
			}
			//将t放到合适的位置
			hufflist[idx - 1] = t;
		}
		//最终得到的根节点就是hufflist的最后一个huffmanPair
		root = hufflist[n - 1];
	}
	
	//在高度h处打印一个节点n
	protected void printnode(LinkBinTreeNode n, int h){
		//高度为h个制表位
		for(int i = 0; i < h; i++){
			System.out.print("\t");
		}
		//获取节点的频率值的单精度形式
		double _f = (
			(HuffmanPair)(
				n.getElem().getElem())).getFreq();
		float f = (float)(_f);
		//节点的字符串，区分叶节点和中间节点
		String c;
		//叶节点时，c为节点的字符串值
		if(n.isLeaf()){
			c = ((HuffmanPair)(
				n.getElem().getElem())).getChar();
		}
		//中间节点时，c为空
		else{
			c = "□";
		}
		//打印节点
		System.out.println("--" + c + " " + f);
	}
	
	//打印以节点r为根节点的huffman树，
	//r的高度为h，调用节点打印函数，
	//本函数为递归函数
	public void ShowHT(LinkBinTreeNode r, int h){
		//根为空，直接返回
		if(r == null){
			return;
		}
		//递归调用，显示以r的右节点为根节点的树
		//高度为h+1
		ShowHT(r.getRight(), h + 1);
		//打印r节点
		printnode(r, h);
		//递归调用，显示以r的左节点为根节点的树
		//高度为h+1
		ShowHT(r.getLeft(), h + 1);
	}
	
	//打印huffman树，即打印以root为根节点的树
	//起始高度为0
	public void printTree(){
		ShowHT(root, 0);
	}
	
	//测试前序、中序、后序和层序遍历
	public void order_visit(){
		LinkBinTree lbt = new LinkBinTree(root);
		//两种前序测试
		System.out.println("\n递归算法实现前序遍历：");
		lbt.rec_preorder(lbt.getRoot());
		System.out.println("\n迭代算法实现前序遍历：");
		lbt.itr_preorder(lbt.getRoot());
		//两种中序测试
		System.out.println("\n递归算法实现中序遍历：");
		lbt.rec_inorder(lbt.getRoot());
		System.out.println("\n迭代算法实现中序遍历：");
		lbt.itr_inorder(lbt.getRoot());
		//两种后序测试
		System.out.println("\n递归算法实现后序遍历：");
		lbt.rec_postorder(lbt.getRoot());
		System.out.println("\n迭代算法实现后序遍历：");
		lbt.itr_postoder(lbt.getRoot());
		//层序测试
		System.out.println("\n迭代算法实现层序遍历：");
		lbt.layer_order(lbt.getRoot());
	}
}
