package Tree;

import Element.ElemItem;
import Queue.LinkQueue;
import Stack.LinkStack;

/**
 * @author Wei LU
 *
 * �������࣬LinkBinTree.java
 * ��Ҫ����������ǰ�򡢺�������Ͳ���
 * �����㷨�ĵݹ�ͷǵݹ�ʵ��
 */
public class LinkBinTree {
	//˽�б���
	protected LinkBinTreeNode root;//����

	//�޲������캯��
	public LinkBinTree(){
		root = null;

	}
	//���������캯��
	public LinkBinTree(LinkBinTreeNode _root){
		root = _root;

	}

	//ǰ�����
	public void rec_preorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//���ȷ��ʸ��ڵ�
		System.out.print(rt + "��");
		//��η������ӽڵ�
		rec_preorder(rt.getLeft());
		//���������ӽڵ�
		rec_preorder(rt.getRight());
	}
	
	//������ʵ��ǰ�����
	public void itr_preorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//������ʽջ���ݽṹ
		LinkStack ls = new LinkStack();
		//���Ƚ����ڵ�ѹջ
		ls.push(new ElemItem<LinkBinTreeNode>(rt));
		//���ϵ��������ʣ�ͬʱѹ�������ӽڵ�
		while(ls.getSize() != 0){
			rt = (LinkBinTreeNode)(ls.pop().getElem());
			//���ʸ��ڵ�
			System.out.print(rt + "��");
			if(rt.getRight() != null)
				//ѹ�����ӽڵ�
				ls.push(new 
				   ElemItem<LinkBinTreeNode>(rt.getRight())
				  );
			if(rt.getLeft() != null)
				//ѹ�����ӽڵ�
				ls.push(new 
				   ElemItem<LinkBinTreeNode>(rt.getLeft())
				  );
			
		}
	}
	
	//�ݹ鷨ʵ�ֺ������
	public void rec_postorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//���ȷ������ӽڵ�
		rec_postorder(rt.getLeft());
		//��η������ӽڵ�
		rec_postorder(rt.getRight());
		//�����ʸ��ڵ�
		System.out.print(rt + "��");
	}
	
	//������ʵ�ֺ������
	public void itr_postoder(LinkBinTreeNode rt){
		if(rt == null) return;
		//�����ڵ�������ڻ��游�ڵ�
		LinkBinTreeNode p = new LinkBinTreeNode();
		//������ʽջ����
		LinkStack ls = new LinkStack();
		//�����ڵ����ڻ���ǰһ�����ʵģ��ң��ڵ�
		LinkBinTreeNode t = new LinkBinTreeNode();
		//�����ؽ��ڵ��Լ��ڵ�����ӽڵ�ѹջ
		while(rt != null){
			ls.push(new ElemItem<LinkBinTreeNode>(rt));
			rt = rt.getLeft();
		}
		//��ջ����
		while(ls.getSize() > 0){
			//��ȡջ��Ԫ��
			rt = (LinkBinTreeNode)(ls.getTop().getElem());
			//���Ԫ�ص��ҽڵ㲻Ϊ�գ��ҽڵ�����ӽڵ�
			//û�б����ʹ�������ؽ����ӽڵ㼰�����ӽڵ�ѹջ
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
			//������ӽڵ�Ϊ�գ��򽫵����ڵ㲢��ӡ�����ʣ�
			else{
				rt = (LinkBinTreeNode)(ls.pop().getElem());
				System.out.print(rt + "��");
				t = rt;
			}
			
		}
	}
	
	//�������
	public void rec_inorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//���ȷ������ӽڵ�
		rec_inorder(rt.getLeft());
		//��η��ʸ��ڵ�
		System.out.print(rt + "��");
		//���շ����ӽڵ�
		rec_inorder(rt.getRight());
		
	}
	
	//�����㷨ʵ���������
	public void itr_inorder(LinkBinTreeNode rt){
		if(rt == null) return;
		//������ʽջ���ݽṹ
		LinkStack ls = new LinkStack();
		//�����ؽ�rt�����ӽڵ�ѹջ
		while(rt != null){
			ls.push(new ElemItem<LinkBinTreeNode>(rt));
			rt = rt.getLeft();
		}
		//����
		while(ls.getSize() > 0){
			//����ջ���ڵ�
			rt = (LinkBinTreeNode)(ls.pop().getElem());
			//���ʽڵ�
			System.out.print(rt + "��");
			//����ýڵ�����ӽڵ㲻Ϊ�գ�
			//���ҽڵ㼰���������ӽڵ�ѹջ
			if(rt.getRight() != null){
				ls.push(new 
					ElemItem<LinkBinTreeNode>(rt.getRight())
				   );
				//�����ؽ����ӽڵ�ѹջ
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
	
	//�����ӡ
	public void layer_order(LinkBinTreeNode rt){
		if(rt == null) return;
		// ��������
		LinkQueue lq = new LinkQueue();
		lq.enqueue(new ElemItem<LinkBinTreeNode>(rt));
		while(lq.currSize() > 0){
			// ����
			rt = (LinkBinTreeNode)(lq.dequeue().getElem());
			// ���ʶ���ͷ���ڵ�
			System.out.print(rt + "��");
			if(rt.getLeft() != null) 
				// ���ڵ�����ӽڵ㱣��������
				lq.enqueue(new 
				  ElemItem<LinkBinTreeNode>(rt.getLeft()));
						
			if(rt.getRight() != null)
				// ���ڵ�����ӽڵ㱣��������
				lq.enqueue(new 
				  ElemItem<LinkBinTreeNode>(rt.getRight()));
			
		}
	}
	
	//��ȡ���ڵ�
	public LinkBinTreeNode getRoot(){
		return root;
	}
}
