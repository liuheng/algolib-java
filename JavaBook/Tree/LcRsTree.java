/*
 * Created on 2010-4-27
 *
 * 树的“左子节点/右兄弟节点”描述方法的类实现
 */
package Tree;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * 树的左子节点-右兄弟节点表示法
 */
public class LcRsTree{
	//私有成员变量
	//节点最大个数
	private int len;
	//私有成员变量，树节点个数
	private LcRsTNode node_array[];
	//私有成员变量，树的根节点在数组中序号
	private int root;
	//树中当前节点的个数
	private int curr_num;
	//无参数构造函数
	public LcRsTree(){
		root = -1;
		curr_num = -1;
	}
	//有参数的构造函数
	public LcRsTree(int _len){
		len = _len;
		node_array = new LcRsTNode[_len];
		//构造节点
		for(int i = 0; i < _len; i++){
			node_array[i] = new LcRsTNode();
		}
	}
	
	//删除树
	public void clearTree() {
		root = -1;
	}
	
	//返回树的根节点
	public int root() {
		return root;
	}
	
	//重设根节点
	public void setRoot(int r) {
		root = r;
	}
	
	//在父节点p_idx处插入序号为self_idx元素项为
	//e的节点
	public boolean addChild(int p_idx, 
						int self_idx, 
						ElemItem e){
		if(p_idx < 0 || p_idx >= len || 
				self_idx < 0 || self_idx >= len ||
				curr_num > len){
				return false;
		}

		// 如果节点数组在父节点号处没有元素项，
		// 则直接将元素项e赋值到数组的p_idx处，self_idx无效用
		if(node_array[p_idx].getElem() == null){
			node_array[p_idx].setElem(e);
			node_array[p_idx].setParent(-1);
			node_array[p_idx].setIdx(p_idx);
			curr_num++;
			return false;
		}
		
		// p_idx处不为空，首先设置self_idx节点
		node_array[self_idx].setElem(e);
		node_array[self_idx].setIdx(self_idx);
		node_array[self_idx].setLeftMostChild(-1);
		node_array[self_idx].setNextSibling(-1);
		node_array[self_idx].setParent(p_idx);
		// 如果p_idx没有子节点
		if(node_array[p_idx].leftMostChild() == -1){
			// 直接将self_idx设置为p_idx的子节点
			node_array[p_idx].setLeftMostChild(self_idx);
		}
		else{
			// 寻找p_idx节点的最右子节点的位置idx
			int idx = node_array[p_idx].leftMostChild();
			while(node_array[idx].rightSibling() != -1){
				idx = node_array[idx].rightSibling();
			}
			// 将找到的idx的右子节点设置为self_idx
			node_array[idx].setNextSibling(self_idx);
		}
		curr_num++;
		
		return true;
	}

	//删除n处的节点，
	protected void removeSon(int n){
		//节点的父节点
		int p = node_array[n].parent();
		if(p < 0) return;
		//获取父节点的最左子节点
		int idx = node_array[p].leftMostChild();
		//如果n正好是其父节点p的最左子节点
		//重新设置p的最左子节点
		if(idx == n){
			node_array[p].setLeftMostChild(
					node_array[n].rightSibling());
			//node_array[n] = new LcRsTNode();
			node_array[n] = null;
			curr_num--;
			return;
		}
		//寻找idx，满足其右兄弟节点为n
		while(node_array[idx].rightSibling() != n){
			idx = node_array[idx].rightSibling();
		}
		//重新设置idx的右兄弟节点为n的右兄弟节点，
		//这样就删除了节点
		node_array[idx].setNextSibling(
				node_array[n].rightSibling());
		node_array[n] = null;
		curr_num--;
	}
	
	//删除nidx节点以及其所有子节点
	public boolean removeChild(int nidx){
		if(nidx < 0 ||
			//nidx >curr_num||
			node_array[nidx].getElem() == null){
				return false;
		}
		//子节点不为空，则将子节点一一清空
		if(node_array[nidx].leftMostChild() != -1){
			//获取nidx的最左子节点的序号idx
			int idx = 
				node_array[nidx].leftMostChild();
			while(idx != -1){
				//缓存idx的右兄弟节点的序号
				int tmp_idx = 
					node_array[idx].rightSibling();
				//递归调用，删除idx节点
				removeChild(idx);
				//继续删除节点
				idx = tmp_idx;
			}
		}
		//删除完节点的子节点后再删除节点自身
		removeSon(nidx);
		
		return true;
	}
	
	//在特定高度打印一个元素项
	protected void printnode(int pos, int h){
		for(int i = 0; i < h; i++)
			System.out.print("\t");
		System.out.println(
			"|—— " +node_array[pos] + "(" + pos+ ")");
	}
	
	//从pos位置高度h出开始，前序打印树
	protected void preOrder(int pos, int h){
		if(pos < 0 || 
			node_array[pos].getElem() == null) 
				return;
		//打印第一个节点
		printnode(pos, h);
		//获取最左节点
		int idx = node_array[pos].leftMostChild();
		//递归调用
		while(idx != -1 && 
			  node_array[idx].getElem() != null){
			preOrder(idx, h + 1);
			idx = node_array[idx].rightSibling();
		}
		
	}
	
	//前序打印调用
	public void ford_print_tree() {
		//打印树结构
		if(curr_num == 0) {
			System.out.println("当前树中没有节点。");
			return;
		}
		System.out.println(curr_num + " 节点,前序遍历打印：");
		preOrder(root,0);
		
	}

}
