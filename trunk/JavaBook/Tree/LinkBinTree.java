package Tree;

import Element.ElemItem;
import Queue.LinkQueue;
import Stack.LinkStack;

/**
 * @author Wei LU
 *
 * 二叉树类，LinkBinTree.java
 * 主要包含二叉树前序、后序、中序和层序
 * 遍历算法的递归和非递归实现
 */
public class LinkBinTree {
	//私有变量
	protected LinkBinTreeNode root;//树根

	//无参数构造函数
	public LinkBinTree(){
		root = null;

	}
	//带参数构造函数
	public LinkBinTree(LinkBinTreeNode _root){
		root = _root;

	}

	//前序遍历
	public void rec_preorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//首先访问父节点
		System.out.print(rt + "→");
		//其次访问左子节点
		rec_preorder(rt.getLeft());
		//最后访问右子节点
		rec_preorder(rt.getRight());
	}
	
	//迭代法实现前序遍历
	public void itr_preorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//创建链式栈数据结构
		LinkStack ls = new LinkStack();
		//首先将根节点压栈
		ls.push(new ElemItem<LinkBinTreeNode>(rt));
		//不断弹出并访问，同时压入左右子节点
		while(ls.getSize() != 0){
			rt = (LinkBinTreeNode)(ls.pop().getElem());
			//访问根节点
			System.out.print(rt + "→");
			if(rt.getRight() != null)
				//压入右子节点
				ls.push(new 
				   ElemItem<LinkBinTreeNode>(rt.getRight())
				  );
			if(rt.getLeft() != null)
				//压入左子节点
				ls.push(new 
				   ElemItem<LinkBinTreeNode>(rt.getLeft())
				  );
			
		}
	}
	
	//递归法实现后序遍历
	public void rec_postorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//首先访问左子节点
		rec_postorder(rt.getLeft());
		//其次访问右子节点
		rec_postorder(rt.getRight());
		//最后访问父节点
		System.out.print(rt + "→");
	}
	
	//迭代法实现后序遍历
	public void itr_postoder(LinkBinTreeNode rt){
		if(rt == null) return;
		//创建节点对象，用于缓存父节点
		LinkBinTreeNode p = new LinkBinTreeNode();
		//创建链式栈对象
		LinkStack ls = new LinkStack();
		//创建节点用于缓存前一个访问的（右）节点
		LinkBinTreeNode t = new LinkBinTreeNode();
		//迭代地将节点以及节点的左子节点压栈
		while(rt != null){
			ls.push(new ElemItem<LinkBinTreeNode>(rt));
			rt = rt.getLeft();
		}
		//出栈处理
		while(ls.getSize() > 0){
			//获取栈顶元素
			rt = (LinkBinTreeNode)(ls.getTop().getElem());
			//如果元素的右节点不为空，且节点的右子节点
			//没有被访问过则迭代地将右子节点及其左子节点压栈
			if(rt.getRight() != null &&
				rt.getRight() != t){
				rt = rt.getRight();
				while(rt != null){
					ls.push(new 
					   ElemItem<LinkBinTreeNode>(rt)
					  );
					rt = rt.getLeft();
				}
			}
			//如果右子节点为空，则将弹出节点并打印（访问）
			else{
				rt = (LinkBinTreeNode)(ls.pop().getElem());
				System.out.print(rt + "→");
				t = rt;
			}
			
		}
	}
	
	//中序遍历
	public void rec_inorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//首先访问左子节点
		rec_inorder(rt.getLeft());
		//其次访问父节点
		System.out.print(rt + "→");
		//最终访右子节点
		rec_inorder(rt.getRight());
		
	}
	
	//迭代算法实现中序访问
	public void itr_inorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//创建链式栈数据结构
		LinkStack ls = new LinkStack();
		//迭代地将rt的左子节点压栈
		while(rt != null){
			ls.push(new ElemItem<LinkBinTreeNode>(rt));
			rt = rt.getLeft();
		}
		//遍历
		while(ls.getSize() > 0){
			//弹出栈顶节点
			rt = (LinkBinTreeNode)(ls.pop().getElem());
			//访问节点
			System.out.print(rt + "→");
			//如果该节点的右子节点不为空，
			//则将右节点及其所有左子节点压栈
			if(rt.getRight() != null){
				ls.push(new 
					ElemItem<LinkBinTreeNode>(rt.getRight())
				   );
				//迭代地将左子节点压栈
				rt = rt.getRight().getLeft();
				while(rt != null){
					ls.push(new 
					    ElemItem<LinkBinTreeNode>(rt)
					   );
					rt = rt.getLeft();
				}
			}
		}
		
	}
	
	//层序打印
	public void layer_order(LinkBinTreeNode rt){
		if(rt == null) return;
		// 创建队列
		LinkQueue lq = new LinkQueue();
		lq.enqueue(new ElemItem<LinkBinTreeNode>(rt));
		while(lq.currSize() > 0){
			// 出列
			rt = (LinkBinTreeNode)(lq.dequeue().getElem());
			// 访问队列头部节点
			System.out.print(rt + "→");
			if(rt.getLeft() != null) 
				// 将节点的左子节点保存至队列
				lq.enqueue(new 
				  ElemItem<LinkBinTreeNode>(rt.getLeft()));
						
			if(rt.getRight() != null)
				// 将节点的右子节点保存至队列
				lq.enqueue(new 
				  ElemItem<LinkBinTreeNode>(rt.getRight()));
			
		}
	}
	
	//获取根节点
	public LinkBinTreeNode getRoot(){
		return root;
	}
}
