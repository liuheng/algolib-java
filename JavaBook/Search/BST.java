/*
 * Created on 2010-8-1
 */
package Search;

import Element.ElemItem;
import Queue.LinkQueue;



/**
 * @author Wei LU
 *
 * 折半查找树符号表，包括表项插入、删除等
 */
public class BST  {
	//私有变量
	protected BstNode root;//树根

	//无参数构造函数
	public BST(){
		root = null;
	}
	
	//带参数构造函数
	public BST(BstNode _root){
		root = _root;
	}
	
	// 获取当前树的根节点
	public BstNode getroot(){
		return root;
	}
	
	// 设置当前树的根节点
	public void setroot(BstNode r){
		root = r;
	}
	
	/**
	 * 递归算法实现表项x插入到符号表，从节点r开始寻找合适的插入节点
	 * @param r	从节点r开始寻找合适的插入点，即子树根节点
	 * @param x	待插入的表项
	 * @return	节点r，其左（或右）节点为x
	 */
	public BstNode insertR(BstNode r, StItem x){
		// 若r为空，则直接返回以x为表项内容的节点
		if(r == null) {
			ElemItem e = new ElemItem<StItem>(x);
			return new BstNode(e, null, null, 1);
		}
		// r中表项内容的键值r_key
		ElemItem r_key = ((StItem)(r.getElem().elem)).GetKey();
		/* 若x的键值小于r_key，则将x插入到r的左子树，并将插入后的返回值设为
		 r的左子节点 */
		if(x.GetKey().compareTo(r_key) < 0){
			r.setN(r.getN() + 1);
			r.setLeft(insertR(r.getLeft(), x));
		}
		/* 若x的键值大于r_key，则将x插入到r的右子树，并将插入后的返回值设为
		 r的右子节点 */
		else {
			r.setN(r.getN() + 1);
			r.setRight(insertR(r.getRight(), x));
		}
		// 返回节点r
		return r;
	}
	
	/**
	 * 将表项x插入到折半查找树中，调用insertR函数，从树根处开始
	 * 搜索合适的位置插入；
	 * @param x	待插入的表项
	 */
	public void insert(StItem x){
		root = insertR(root, x);
	}
	
	/**
	 * 迭代算法实现表项x的插入
	 * @param x	待插入的表项
	 */
	public void itr_insert(StItem x){
		// x的键值
		ElemItem key = x.GetKey();
		// 如果根为空，则创建内容为x的节点，并赋值给树根root
		if(root == null){
			root = new BstNode(
					new ElemItem<StItem>(x), 
					null, null, 1);
			return;
		}
		/* 如果根不为空，则迭代寻找插入位置，p为跟踪的当前节点，q为p的子节点
		 * 迭代直至q为空节点，最终p即为待插入节点的父节点 */
		BstNode p = root, q = p;
		// 迭代直至q为空
		while(q != null){
			// 如果x的键值较小，则在继续在左子树中搜索合适位置
			if(key.compareTo(((StItem)(q.getElem().elem)).GetKey()) < 0){
				p = q; q.setN(q.getN() + 1);
				q = q.getLeft();
			}
			// 如果x的键值较大，则在继续在右子树中搜索合适位置
			else{
				p = q; q.setN(q.getN() + 1); 
				q = q.getRight();
			}
		}
		// 如果p的键值大，则创建内容为x的节点，并赋值给p的左子节点
		if(key.compareTo(((StItem)(p.getElem().elem)).GetKey()) < 0)
			p.setLeft(new BstNode(
						new ElemItem<StItem>(x),
						null, null, 1) );
		// 如果p的键值小，则创建内容为x的节点，并赋值给p的右子节点
		else
			p.setRight(new BstNode(
						new ElemItem<StItem>(x), 
						null, null, 1) );
	}
	
	/**
	 * BST的右旋转
	 * @param h	节点h和它左子节点交换位置
	 * @return	交换位置后原h位置上的节点
	 */
	private BstNode rotR(BstNode h){
		BstNode x = h.getLeft();
		// 设置h的左子节点为原左子节点的右子节点
		h.setLeft(x.getRight());
		// 设置h原左子节点的右子节点为h
		x.setRight(h);
		// 更新h的子节点个数N
		int l = h.getLeft()==null?0:h.getLeft().getN();
		int r = h.getRight()==null?0:h.getRight().getN();
		h.setN(l + r);
		l = x.getLeft()==null?0:x.getLeft().getN();
		r = x.getRight()==null?0:x.getRight().getN();
		x.setN(l + r);
		return x;
	}
	
	/**
	 * BST的左旋转
	 * @param h	节点h和它右子节点交换位置
	 * @return	交换位置后原h位置上的节点
	 */
	private BstNode rotL(BstNode h){
		BstNode x = h.getRight();
		// 设置h的右子节点为原右子节点的左子节点
		h.setRight(x.getLeft());
		// 设置h原右子节点的左子节点为h
		x.setLeft(h);
		// 更新h的子节点个数N
		int l = h.getLeft()==null?0:h.getLeft().getN();
		int r = h.getRight()==null?0:h.getRight().getN();
		h.setN(l + r);
		l = x.getLeft()==null?0:x.getLeft().getN();
		r = x.getRight()==null?0:x.getRight().getN();
		x.setN(l + r);
		return x;
	}
	
	/**
	 * 向BST树上插入新表项x，从节点h开始插入表项；
	 * @param h	从节点h处开始插入，子树根节点
	 * @param x	待插入的表项
	 * @return	插入表项x后的节点h
	 */
	public BstNode insertT(BstNode h, StItem x){
		// 如果节点h为空，则以表项x为内容创建新的节点，并赋值给h
		if(h == null) 
			return new BstNode(new ElemItem<StItem>(x),
					null, null, 1);
		// 如果表项x的键值比h中表项的键值小
		if(x.GetKey().compareTo(((StItem)(h.getElem().elem)).GetKey()) < 0){
			// 递归调用，将x插入到h的左子树，并重新设置h的左子节点
			h.setLeft(insertT(h.getLeft(), x));
			// 以h为参数进行右旋转操作
			h = rotR(h);
		}
		else{
			// 递归调用，将x插入到h的右子树，并重新设置h的右子节点
			h.setRight(insertT(h.getRight(), x));
			// 以h为参数进行左旋转操作
			h = rotL(h);
		}
		// 返回节点h
		return h;
	}
	
	/**
	 * 在BST上根的插入，调用insertT函数
	 * @param x	待插入的表项
	 */
	public void root_insert(StItem x){
		root = insertT(root, x);
	}
	
	
	/**
	 * 递归算法实现基于键值key的表项的搜索，从节点r开始进行搜索
	 * @param r		从节点r开始进行搜索，即子树根节点
	 * @param key	待搜索的表项的键值
	 * @return		返回键值为key的表项
	 */
	public StItem searchR(BstNode r, ElemItem key){
		// 如果r为空，则返回空
		if(r == null) return null;
		// 节点r中的表项si
		StItem si = (StItem)(r.getElem().elem);
		// si的键值r_key
		ElemItem r_key = si.GetKey();
		// 若key与r_key相等，则返回si
		if(key.compareTo(r_key) == 0) return si;
		// 若key小于r_key，则返回左子树中检索的键值为key的表项
		if(key.compareTo(r_key) < 0) return searchR(r.getLeft(), key);
		// 若key大于r_key，则返回右子树中检索的键值为key的表项
		else return searchR(r.getRight(), key);
	}
	
	/**
	 * 在折半树中查找键值为key的表项，函数调用searchR，从树根开始进行查找；
	 * @param key	待查找的键值为key的表项；
	 * @return	键值为key的表项
	 */
	public StItem search(ElemItem key){
		return searchR(root, key);
	}
	
	/**
	 * 递归算法统计（子）树中节点个数，从节点r开始进行统计；
	 * 对树的遍历算法为后序遍历。
	 * @param r	从节点r开始统计，即子树根节点
	 * @return	节点个数
	 */
	public int countR(BstNode r){
		// 如果r为空，个数为0
		if(r == null) return 0;
		// r不为空，则返回1+左子树节点个数+右子树节点个数
		r.setN( 1 + countR(r.getLeft())
				 + countR(r.getRight()));
		return r.getN();
	}
	
	/**
	 * 统计折半树中节点总个数，调用递归函数countR，参数为树根；
	 * @return	当前折半树中节点个数。
	 */
	public int count(){
		return countR(root);
	}
	
	/**
	 * 递归算法，选择第k个最小的表项，从节点h开始选择；
	 * @param h	从节点h处开始选择
	 * @param k	选择第k个最小的表项
	 * @return	BST中以h为根节点的子树的第k个最小的表项
	 */
	public StItem selectR(BstNode h, int k){
		// 如果h为空，则返回空
		if(h == null) return null;
		// 获取h的左子节点的子节点总个数t
		int t = h.getLeft()==null?0:h.getLeft().getN();
		// 如果t大于k，则递归地在h的左子树中选择第k个最小表项；
		if(t > k) return selectR(h.getLeft(), k);
		// 如果t小于k，则递归地在h的右子树中选择第k-t-1个最小表项；；
		if(t < k) return selectR(h.getRight(), k - t - 1);
		// t等于k，返回当前h包含的表项
		return (StItem)(h.getElem().elem);
	}
	
	/**
	 * 返回BST中第k个最小表项，从BST的树根开始选择；
	 * @param k	选择第k个最小的表项
	 * @return	BST中第k个最小的表项
	 */
	public StItem select(int k){
		return selectR(root, k);
	}
	
	/**
	 * BST划分函数，将BST中以h为根节点的子树中第k个最小的
	 * 表项，并将其重设为子树的根节点
	 * @param h	子树根节点
	 * @param k	划分的节点为第k个最小的节点
	 * @return	返回第k个节点
	 */
	public BstNode partR(BstNode h, int k){
		// 获取h左子树节点个数t
		int t = (h.getLeft()==null)?0:h.getLeft().getN();
		// 如果t个数大于k，递归划分h左子树，并重设h的左子节点为
		// 划分后的左子树的根节点
		if(t > k){
			h.setLeft(partR(h.getLeft(), k));
			// 对节点h进行右旋转操作
			h = rotR(h);
		}
		// 如果t个数小于k，递归划分h右子树，并重设h的右子节点为
		// 划分后的右子树的根节点
		if(t < k){
			h.setRight(partR(h.getRight(), k - t - 1));
			// 对节点h尽心左旋转操作
			h = rotL(h);
		}
		// 返回节点h
		return h;
	}
	
	/**
	 * 将节点的两节点合并，被函数removeR调用
	 * @param a	待合并的第一个节点
	 * @param b	待合并的第二个节点
	 * @return	合并后的根节点
	 */
	private BstNode joinLR(BstNode a, BstNode b){
		if(b == null) return a;
		// 调整以b为根节点的子树，使其根节点为子树中键值最小的节点
		b = partR(b, 0);
		// 将a设为b的左子树
		b.setLeft(a);
		// 返回子树根节点b
		return b;
	}
	
	/**
	 * 删除以h为根节点的子树中节点，被函数remove调用
	 * @param h		子树根节点
	 * @param key	待删除节点的键值
	 * @return		键值为k的节点处的新节点
	 */
	public BstNode removeR(BstNode h, ElemItem key){
		if(h == null) return null;
		ElemItem w = ((StItem)(h.getElem().elem)).GetKey();
		// 如果key比h中表项的键值小
		if(key.compareTo(w) < 0) {
			// 在h的左子树中寻找并删除键值为key的节点，
			// 并重新设置左子节点
			h.setLeft(removeR(h.getLeft(), key));
			h.setN(h.getN() - 1);
		}
		// 如果key比h中表项的键值大
		if(key.compareTo(w) > 0){ 
			// 在h的右子树中寻找并删除键值为key的节点，
			// 并重新设置右子节点
			h.setRight(removeR(h.getRight(), key));
			h.setN(h.getN() - 1);
		}
		// 如果key等于h中表项的键值
		if(key.compareTo(w) == 0){ 
			// 将h的左子节点和右子节点合并并对h重新赋值
			h = joinLR(h.getLeft(), h.getRight());
		}
		// 返回节点h
		return h;
	}
	
	/**
	 * 删除键值为key的节点，调用递归函数remove；
	 * @param key	待删除节点中表项的键值。
	 */
	public void remove(ElemItem key){
		removeR(root, key);
	}
	
	/**
	 * 递归函数，合并两个BST。被函数join调用
	 * @param a	第一个节点
	 * @param b	第二个节点
	 * @return	合并后的根节点
	 */
	private BstNode joinR(BstNode a, BstNode b){
		if(b == null) return a;
		if(a == null) return b;
		// 将a中表项插入到节点b为根节点的子树
		b = insertT(b, (StItem)(a.getElem().elem));
		// 递归调用，将a的左子树和b的左子树合并并赋值为b的左子树
		b.setLeft(joinR(a.getLeft(), b.getLeft()));
		// 递归调用，将a的右子树和b的右子树合并并赋值为b的右子树
		b.setRight(joinR(a.getRight(), b.getRight()));
		// 返回子树根节点b
		return b;
	}
	
	/**
	 * 将当前BST与另一BST合并
	 * @param b	另一BST
	 */
	public void join(BST b){
		setroot(joinR(root, b.getroot()));
	}
	
	/**
	 * 在特定高度打印一个元素项
	 * @param n	待打印的节点
	 * @param h	打印位置的高度
	 */
	protected void printnode(BstNode n, int h){
		for(int i = 0; i < h; i++)
			System.out.print("\t");
		System.out.println("●-" + n.getN() + ", " + n);
	}
	
	/**
	 * 中序遍历树中以节点n为父节点的（子）树。函数首先遍历n的左子树，
	 * 然后显示本节点，最终遍历n的右子树。
	 * @param n	待显示（子）树的根节点
	 * @param h	n所在的高度
	 */
	protected void recursive_show(BstNode n, int h){
		if(n == null) return;
		//先访问右子节点
		recursive_show(n.getRight(), h + 1);
		//访问节点自身
		printnode(n, h);
		//访问左子节点
		recursive_show(n.getLeft(), h + 1);
	}
	
	/**
	 * 打印BST，从BST的根节点开始遍历BST，其高度为0
	 * 函数调用递归函数recursive_show
	 */
	public void printBST() {
		System.out.println("旋转90度分层打印：");
		//从BST树根开始打印，它处于第0层
		recursive_show(root, 0);
	}
	
	/**
	 * 递归算法实现折半树的层序遍历，从节点rt开始进行遍历
	 * @param rt	从节点rt开始进行遍历
	 * @return		从rt开始遍历得到的各节点的内容
	 */
	public String toStringR(BstNode rt){
		if(rt == null) return "";
		String str = "";
		//创建队列
		LinkQueue lq = new LinkQueue();
		lq.enqueue(new ElemItem<BstNode>(rt));
		while(lq.currSize() > 0){
			//出列
			rt = (BstNode)(lq.dequeue().getElem());
			//访问队列头部节点
			str = str + rt + "→\n";
			if(rt.getLeft() != null) 
				//将节点的左子节点保存至队列
				lq.enqueue(new 
				  ElemItem<BstNode>(rt.getLeft()));
			if(rt.getRight() != null)
				//将节点的右子节点保存至队列
				lq.enqueue(new 
				  ElemItem<BstNode>(rt.getRight()));
		}
		return str;
	}
	
	/**
	 * 实现折半树的层序遍历，调用函数toStringR函数，从根节点root
	 * 开始进行遍历；
	 */
	public String toString(){
		return toStringR(root);
	}
	
	// 测试示例
	public static void main(String args[]){
		BST bst = new BST();
		String data[] = {"A", "S", "E", "R", "C", "H", "I"};
		for(int i = 0; i < data.length; i++){
			ElemItem key = new ElemItem<String>(data[i]);
			ElemItem info = new ElemItem<String>("bst");
			bst.root_insert(new StItem(key, info));
		}
		//bst.countR(bst.root);
		System.out.println("BST元素个数：\n" + bst.count());
		System.out.println("\n" + bst.search(new ElemItem<String>("C")));
		// System.out.println("层序打印二叉树：\n" + bst);
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("打印树");
		bst.printBST();
		
		int c = 0;
		System.out.println("选择第" + c + "个最小关键字项：");
		System.out.println(bst.select(c));
		/*
		System.out.println("将第" + c + "个最小关键字项设为BST的根：");
		BstNode r = bst.getroot();
		bst.setroot(bst.partR(r, c));
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		bst.printBST();
		 */
		
		/*
		ElemItem k = new ElemItem<String>("R");
		System.out.println("将键值为R的表项删除后：" );
		bst.remove(k);
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		bst.printBST();
		*/
		
		BST bst2 = new BST();
		String data2[] = {"G", "X", "W", "P", "D", "T", "K"};
		for(int i = 0; i < data.length; i++){
			ElemItem key = new ElemItem<String>(data2[i]);
			ElemItem info = new ElemItem<String>("bst2");
			bst2.root_insert(new StItem(key, info));
		}
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("打印树");
		bst2.printBST();
		bst2.join(bst);
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("打印树");
		bst2.printBST();
		
		String s = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		BST bst3 = new BST();
		for(int i = 0; i < 26; i++){
			String data3 = s.substring(i, i + 1);
			ElemItem key = new ElemItem<String>(data3);
			ElemItem info = new ElemItem<String>("bst3");
			bst3.insert(new StItem(key, info));
		}
		bst3.count();
		System.out.println("□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□□");
		System.out.println("打印树");
		bst3.printBST();
	}
}
