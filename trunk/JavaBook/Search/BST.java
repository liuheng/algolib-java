/*
 * Created on 2010-8-1
 */
package Search;

import Element.ElemItem;
import Queue.LinkQueue;



/**
 * @author Wei LU
 *
 * �۰���������ű�����������롢ɾ����
 */
public class BST  {
	//˽�б���
	protected BstNode root;//����

	//�޲������캯��
	public BST(){
		root = null;
	}
	
	//���������캯��
	public BST(BstNode _root){
		root = _root;
	}
	
	// ��ȡ��ǰ���ĸ��ڵ�
	public BstNode getroot(){
		return root;
	}
	
	// ���õ�ǰ���ĸ��ڵ�
	public void setroot(BstNode r){
		root = r;
	}
	
	/**
	 * �ݹ��㷨ʵ�ֱ���x���뵽���ű��ӽڵ�r��ʼѰ�Һ��ʵĲ���ڵ�
	 * @param r	�ӽڵ�r��ʼѰ�Һ��ʵĲ���㣬���������ڵ�
	 * @param x	������ı���
	 * @return	�ڵ�r�����󣨻��ң��ڵ�Ϊx
	 */
	public BstNode insertR(BstNode r, StItem x){
		// ��rΪ�գ���ֱ�ӷ�����xΪ�������ݵĽڵ�
		if(r == null) {
			ElemItem e = new ElemItem<StItem>(x);
			return new BstNode(e, null, null, 1);
		}
		// r�б������ݵļ�ֵr_key
		ElemItem r_key = ((StItem)(r.getElem().elem)).GetKey();
		/* ��x�ļ�ֵС��r_key����x���뵽r�������������������ķ���ֵ��Ϊ
		 r�����ӽڵ� */
		if(x.GetKey().compareTo(r_key) < 0){
			r.setN(r.getN() + 1);
			r.setLeft(insertR(r.getLeft(), x));
		}
		/* ��x�ļ�ֵ����r_key����x���뵽r�������������������ķ���ֵ��Ϊ
		 r�����ӽڵ� */
		else {
			r.setN(r.getN() + 1);
			r.setRight(insertR(r.getRight(), x));
		}
		// ���ؽڵ�r
		return r;
	}
	
	/**
	 * ������x���뵽�۰�������У�����insertR����������������ʼ
	 * �������ʵ�λ�ò��룻
	 * @param x	������ı���
	 */
	public void insert(StItem x){
		root = insertR(root, x);
	}
	
	/**
	 * �����㷨ʵ�ֱ���x�Ĳ���
	 * @param x	������ı���
	 */
	public void itr_insert(StItem x){
		// x�ļ�ֵ
		ElemItem key = x.GetKey();
		// �����Ϊ�գ��򴴽�����Ϊx�Ľڵ㣬����ֵ������root
		if(root == null){
			root = new BstNode(
					new ElemItem<StItem>(x), 
					null, null, 1);
			return;
		}
		/* �������Ϊ�գ������Ѱ�Ҳ���λ�ã�pΪ���ٵĵ�ǰ�ڵ㣬qΪp���ӽڵ�
		 * ����ֱ��qΪ�սڵ㣬����p��Ϊ������ڵ�ĸ��ڵ� */
		BstNode p = root, q = p;
		// ����ֱ��qΪ��
		while(q != null){
			// ���x�ļ�ֵ��С�����ڼ���������������������λ��
			if(key.compareTo(((StItem)(q.getElem().elem)).GetKey()) < 0){
				p = q; q.setN(q.getN() + 1);
				q = q.getLeft();
			}
			// ���x�ļ�ֵ�ϴ����ڼ���������������������λ��
			else{
				p = q; q.setN(q.getN() + 1); 
				q = q.getRight();
			}
		}
		// ���p�ļ�ֵ���򴴽�����Ϊx�Ľڵ㣬����ֵ��p�����ӽڵ�
		if(key.compareTo(((StItem)(p.getElem().elem)).GetKey()) < 0)
			p.setLeft(new BstNode(
						new ElemItem<StItem>(x),
						null, null, 1) );
		// ���p�ļ�ֵС���򴴽�����Ϊx�Ľڵ㣬����ֵ��p�����ӽڵ�
		else
			p.setRight(new BstNode(
						new ElemItem<StItem>(x), 
						null, null, 1) );
	}
	
	/**
	 * BST������ת
	 * @param h	�ڵ�h�������ӽڵ㽻��λ��
	 * @return	����λ�ú�ԭhλ���ϵĽڵ�
	 */
	private BstNode rotR(BstNode h){
		BstNode x = h.getLeft();
		// ����h�����ӽڵ�Ϊԭ���ӽڵ�����ӽڵ�
		h.setLeft(x.getRight());
		// ����hԭ���ӽڵ�����ӽڵ�Ϊh
		x.setRight(h);
		// ����h���ӽڵ����N
		int l = h.getLeft()==null?0:h.getLeft().getN();
		int r = h.getRight()==null?0:h.getRight().getN();
		h.setN(l + r);
		l = x.getLeft()==null?0:x.getLeft().getN();
		r = x.getRight()==null?0:x.getRight().getN();
		x.setN(l + r);
		return x;
	}
	
	/**
	 * BST������ת
	 * @param h	�ڵ�h�������ӽڵ㽻��λ��
	 * @return	����λ�ú�ԭhλ���ϵĽڵ�
	 */
	private BstNode rotL(BstNode h){
		BstNode x = h.getRight();
		// ����h�����ӽڵ�Ϊԭ���ӽڵ�����ӽڵ�
		h.setRight(x.getLeft());
		// ����hԭ���ӽڵ�����ӽڵ�Ϊh
		x.setLeft(h);
		// ����h���ӽڵ����N
		int l = h.getLeft()==null?0:h.getLeft().getN();
		int r = h.getRight()==null?0:h.getRight().getN();
		h.setN(l + r);
		l = x.getLeft()==null?0:x.getLeft().getN();
		r = x.getRight()==null?0:x.getRight().getN();
		x.setN(l + r);
		return x;
	}
	
	/**
	 * ��BST���ϲ����±���x���ӽڵ�h��ʼ������
	 * @param h	�ӽڵ�h����ʼ���룬�������ڵ�
	 * @param x	������ı���
	 * @return	�������x��Ľڵ�h
	 */
	public BstNode insertT(BstNode h, StItem x){
		// ����ڵ�hΪ�գ����Ա���xΪ���ݴ����µĽڵ㣬����ֵ��h
		if(h == null) 
			return new BstNode(new ElemItem<StItem>(x),
					null, null, 1);
		// �������x�ļ�ֵ��h�б���ļ�ֵС
		if(x.GetKey().compareTo(((StItem)(h.getElem().elem)).GetKey()) < 0){
			// �ݹ���ã���x���뵽h��������������������h�����ӽڵ�
			h.setLeft(insertT(h.getLeft(), x));
			// ��hΪ������������ת����
			h = rotR(h);
		}
		else{
			// �ݹ���ã���x���뵽h��������������������h�����ӽڵ�
			h.setRight(insertT(h.getRight(), x));
			// ��hΪ������������ת����
			h = rotL(h);
		}
		// ���ؽڵ�h
		return h;
	}
	
	/**
	 * ��BST�ϸ��Ĳ��룬����insertT����
	 * @param x	������ı���
	 */
	public void root_insert(StItem x){
		root = insertT(root, x);
	}
	
	
	/**
	 * �ݹ��㷨ʵ�ֻ��ڼ�ֵkey�ı�����������ӽڵ�r��ʼ��������
	 * @param r		�ӽڵ�r��ʼ�������������������ڵ�
	 * @param key	�������ı���ļ�ֵ
	 * @return		���ؼ�ֵΪkey�ı���
	 */
	public StItem searchR(BstNode r, ElemItem key){
		// ���rΪ�գ��򷵻ؿ�
		if(r == null) return null;
		// �ڵ�r�еı���si
		StItem si = (StItem)(r.getElem().elem);
		// si�ļ�ֵr_key
		ElemItem r_key = si.GetKey();
		// ��key��r_key��ȣ��򷵻�si
		if(key.compareTo(r_key) == 0) return si;
		// ��keyС��r_key���򷵻��������м����ļ�ֵΪkey�ı���
		if(key.compareTo(r_key) < 0) return searchR(r.getLeft(), key);
		// ��key����r_key���򷵻��������м����ļ�ֵΪkey�ı���
		else return searchR(r.getRight(), key);
	}
	
	/**
	 * ���۰����в��Ҽ�ֵΪkey�ı����������searchR����������ʼ���в��ң�
	 * @param key	�����ҵļ�ֵΪkey�ı��
	 * @return	��ֵΪkey�ı���
	 */
	public StItem search(ElemItem key){
		return searchR(root, key);
	}
	
	/**
	 * �ݹ��㷨ͳ�ƣ��ӣ����нڵ�������ӽڵ�r��ʼ����ͳ�ƣ�
	 * �����ı����㷨Ϊ���������
	 * @param r	�ӽڵ�r��ʼͳ�ƣ����������ڵ�
	 * @return	�ڵ����
	 */
	public int countR(BstNode r){
		// ���rΪ�գ�����Ϊ0
		if(r == null) return 0;
		// r��Ϊ�գ��򷵻�1+�������ڵ����+�������ڵ����
		r.setN( 1 + countR(r.getLeft())
				 + countR(r.getRight()));
		return r.getN();
	}
	
	/**
	 * ͳ���۰����нڵ��ܸ��������õݹ麯��countR������Ϊ������
	 * @return	��ǰ�۰����нڵ������
	 */
	public int count(){
		return countR(root);
	}
	
	/**
	 * �ݹ��㷨��ѡ���k����С�ı���ӽڵ�h��ʼѡ��
	 * @param h	�ӽڵ�h����ʼѡ��
	 * @param k	ѡ���k����С�ı���
	 * @return	BST����hΪ���ڵ�������ĵ�k����С�ı���
	 */
	public StItem selectR(BstNode h, int k){
		// ���hΪ�գ��򷵻ؿ�
		if(h == null) return null;
		// ��ȡh�����ӽڵ���ӽڵ��ܸ���t
		int t = h.getLeft()==null?0:h.getLeft().getN();
		// ���t����k����ݹ����h����������ѡ���k����С���
		if(t > k) return selectR(h.getLeft(), k);
		// ���tС��k����ݹ����h����������ѡ���k-t-1����С�����
		if(t < k) return selectR(h.getRight(), k - t - 1);
		// t����k�����ص�ǰh�����ı���
		return (StItem)(h.getElem().elem);
	}
	
	/**
	 * ����BST�е�k����С�����BST��������ʼѡ��
	 * @param k	ѡ���k����С�ı���
	 * @return	BST�е�k����С�ı���
	 */
	public StItem select(int k){
		return selectR(root, k);
	}
	
	/**
	 * BST���ֺ�������BST����hΪ���ڵ�������е�k����С��
	 * �������������Ϊ�����ĸ��ڵ�
	 * @param h	�������ڵ�
	 * @param k	���ֵĽڵ�Ϊ��k����С�Ľڵ�
	 * @return	���ص�k���ڵ�
	 */
	public BstNode partR(BstNode h, int k){
		// ��ȡh�������ڵ����t
		int t = (h.getLeft()==null)?0:h.getLeft().getN();
		// ���t��������k���ݹ黮��h��������������h�����ӽڵ�Ϊ
		// ���ֺ���������ĸ��ڵ�
		if(t > k){
			h.setLeft(partR(h.getLeft(), k));
			// �Խڵ�h��������ת����
			h = rotR(h);
		}
		// ���t����С��k���ݹ黮��h��������������h�����ӽڵ�Ϊ
		// ���ֺ���������ĸ��ڵ�
		if(t < k){
			h.setRight(partR(h.getRight(), k - t - 1));
			// �Խڵ�h��������ת����
			h = rotL(h);
		}
		// ���ؽڵ�h
		return h;
	}
	
	/**
	 * ���ڵ�����ڵ�ϲ���������removeR����
	 * @param a	���ϲ��ĵ�һ���ڵ�
	 * @param b	���ϲ��ĵڶ����ڵ�
	 * @return	�ϲ���ĸ��ڵ�
	 */
	private BstNode joinLR(BstNode a, BstNode b){
		if(b == null) return a;
		// ������bΪ���ڵ��������ʹ����ڵ�Ϊ�����м�ֵ��С�Ľڵ�
		b = partR(b, 0);
		// ��a��Ϊb��������
		b.setLeft(a);
		// �����������ڵ�b
		return b;
	}
	
	/**
	 * ɾ����hΪ���ڵ�������нڵ㣬������remove����
	 * @param h		�������ڵ�
	 * @param key	��ɾ���ڵ�ļ�ֵ
	 * @return		��ֵΪk�Ľڵ㴦���½ڵ�
	 */
	public BstNode removeR(BstNode h, ElemItem key){
		if(h == null) return null;
		ElemItem w = ((StItem)(h.getElem().elem)).GetKey();
		// ���key��h�б���ļ�ֵС
		if(key.compareTo(w) < 0) {
			// ��h����������Ѱ�Ҳ�ɾ����ֵΪkey�Ľڵ㣬
			// �������������ӽڵ�
			h.setLeft(removeR(h.getLeft(), key));
			h.setN(h.getN() - 1);
		}
		// ���key��h�б���ļ�ֵ��
		if(key.compareTo(w) > 0){ 
			// ��h����������Ѱ�Ҳ�ɾ����ֵΪkey�Ľڵ㣬
			// �������������ӽڵ�
			h.setRight(removeR(h.getRight(), key));
			h.setN(h.getN() - 1);
		}
		// ���key����h�б���ļ�ֵ
		if(key.compareTo(w) == 0){ 
			// ��h�����ӽڵ�����ӽڵ�ϲ�����h���¸�ֵ
			h = joinLR(h.getLeft(), h.getRight());
		}
		// ���ؽڵ�h
		return h;
	}
	
	/**
	 * ɾ����ֵΪkey�Ľڵ㣬���õݹ麯��remove��
	 * @param key	��ɾ���ڵ��б���ļ�ֵ��
	 */
	public void remove(ElemItem key){
		removeR(root, key);
	}
	
	/**
	 * �ݹ麯�����ϲ�����BST��������join����
	 * @param a	��һ���ڵ�
	 * @param b	�ڶ����ڵ�
	 * @return	�ϲ���ĸ��ڵ�
	 */
	private BstNode joinR(BstNode a, BstNode b){
		if(b == null) return a;
		if(a == null) return b;
		// ��a�б�����뵽�ڵ�bΪ���ڵ������
		b = insertT(b, (StItem)(a.getElem().elem));
		// �ݹ���ã���a����������b���������ϲ�����ֵΪb��������
		b.setLeft(joinR(a.getLeft(), b.getLeft()));
		// �ݹ���ã���a����������b���������ϲ�����ֵΪb��������
		b.setRight(joinR(a.getRight(), b.getRight()));
		// �����������ڵ�b
		return b;
	}
	
	/**
	 * ����ǰBST����һBST�ϲ�
	 * @param b	��һBST
	 */
	public void join(BST b){
		setroot(joinR(root, b.getroot()));
	}
	
	/**
	 * ���ض��߶ȴ�ӡһ��Ԫ����
	 * @param n	����ӡ�Ľڵ�
	 * @param h	��ӡλ�õĸ߶�
	 */
	protected void printnode(BstNode n, int h){
		for(int i = 0; i < h; i++)
			System.out.print("\t");
		System.out.println("��-" + n.getN() + ", " + n);
	}
	
	/**
	 * ������������Խڵ�nΪ���ڵ�ģ��ӣ������������ȱ���n����������
	 * Ȼ����ʾ���ڵ㣬���ձ���n����������
	 * @param n	����ʾ���ӣ����ĸ��ڵ�
	 * @param h	n���ڵĸ߶�
	 */
	protected void recursive_show(BstNode n, int h){
		if(n == null) return;
		//�ȷ������ӽڵ�
		recursive_show(n.getRight(), h + 1);
		//���ʽڵ�����
		printnode(n, h);
		//�������ӽڵ�
		recursive_show(n.getLeft(), h + 1);
	}
	
	/**
	 * ��ӡBST����BST�ĸ��ڵ㿪ʼ����BST����߶�Ϊ0
	 * �������õݹ麯��recursive_show
	 */
	public void printBST() {
		System.out.println("��ת90�ȷֲ��ӡ��");
		//��BST������ʼ��ӡ�������ڵ�0��
		recursive_show(root, 0);
	}
	
	/**
	 * �ݹ��㷨ʵ���۰����Ĳ���������ӽڵ�rt��ʼ���б���
	 * @param rt	�ӽڵ�rt��ʼ���б���
	 * @return		��rt��ʼ�����õ��ĸ��ڵ������
	 */
	public String toStringR(BstNode rt){
		if(rt == null) return "";
		String str = "";
		//��������
		LinkQueue lq = new LinkQueue();
		lq.enqueue(new ElemItem<BstNode>(rt));
		while(lq.currSize() > 0){
			//����
			rt = (BstNode)(lq.dequeue().getElem());
			//���ʶ���ͷ���ڵ�
			str = str + rt + "��\n";
			if(rt.getLeft() != null) 
				//���ڵ�����ӽڵ㱣��������
				lq.enqueue(new 
				  ElemItem<BstNode>(rt.getLeft()));
			if(rt.getRight() != null)
				//���ڵ�����ӽڵ㱣��������
				lq.enqueue(new 
				  ElemItem<BstNode>(rt.getRight()));
		}
		return str;
	}
	
	/**
	 * ʵ���۰����Ĳ�����������ú���toStringR�������Ӹ��ڵ�root
	 * ��ʼ���б�����
	 */
	public String toString(){
		return toStringR(root);
	}
	
	// ����ʾ��
	public static void main(String args[]){
		BST bst = new BST();
		String data[] = {"A", "S", "E", "R", "C", "H", "I"};
		for(int i = 0; i < data.length; i++){
			ElemItem key = new ElemItem<String>(data[i]);
			ElemItem info = new ElemItem<String>("bst");
			bst.root_insert(new StItem(key, info));
		}
		//bst.countR(bst.root);
		System.out.println("BSTԪ�ظ�����\n" + bst.count());
		System.out.println("\n" + bst.search(new ElemItem<String>("C")));
		// System.out.println("�����ӡ��������\n" + bst);
		System.out.println("������������������������������������������������������������������");
		System.out.println("��ӡ��");
		bst.printBST();
		
		int c = 0;
		System.out.println("ѡ���" + c + "����С�ؼ����");
		System.out.println(bst.select(c));
		/*
		System.out.println("����" + c + "����С�ؼ�������ΪBST�ĸ���");
		BstNode r = bst.getroot();
		bst.setroot(bst.partR(r, c));
		System.out.println("������������������������������������������������������������������");
		bst.printBST();
		 */
		
		/*
		ElemItem k = new ElemItem<String>("R");
		System.out.println("����ֵΪR�ı���ɾ����" );
		bst.remove(k);
		System.out.println("������������������������������������������������������������������");
		bst.printBST();
		*/
		
		BST bst2 = new BST();
		String data2[] = {"G", "X", "W", "P", "D", "T", "K"};
		for(int i = 0; i < data.length; i++){
			ElemItem key = new ElemItem<String>(data2[i]);
			ElemItem info = new ElemItem<String>("bst2");
			bst2.root_insert(new StItem(key, info));
		}
		System.out.println("������������������������������������������������������������������");
		System.out.println("��ӡ��");
		bst2.printBST();
		bst2.join(bst);
		System.out.println("������������������������������������������������������������������");
		System.out.println("��ӡ��");
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
		System.out.println("������������������������������������������������������������������");
		System.out.println("��ӡ��");
		bst3.printBST();
	}
}
