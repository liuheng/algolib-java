/*
 * Created on 2010-4-27
 *
 * ���ġ����ӽڵ�/���ֵܽڵ㡱������������ʵ��
 */
package Tree;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * �������ӽڵ�-���ֵܽڵ��ʾ��
 */
public class LcRsTree{
	//˽�г�Ա����
	//�ڵ�������
	private int len;
	//˽�г�Ա���������ڵ����
	private LcRsTNode node_array[];
	//˽�г�Ա���������ĸ��ڵ������������
	private int root;
	//���е�ǰ�ڵ�ĸ���
	private int curr_num;
	//�޲������캯��
	public LcRsTree(){
		root = -1;
		curr_num = -1;
	}
	//�в����Ĺ��캯��
	public LcRsTree(int _len){
		len = _len;
		node_array = new LcRsTNode[_len];
		//����ڵ�
		for(int i = 0; i < _len; i++){
			node_array[i] = new LcRsTNode();
		}
	}
	
	//ɾ����
	public void clearTree() {
		root = -1;
	}
	
	//�������ĸ��ڵ�
	public int root() {
		return root;
	}
	
	//������ڵ�
	public void setRoot(int r) {
		root = r;
	}
	
	//�ڸ��ڵ�p_idx���������Ϊself_idxԪ����Ϊ
	//e�Ľڵ�
	public boolean addChild(int p_idx, 
						int self_idx, 
						ElemItem e){
		if(p_idx < 0 || p_idx >= len || 
				self_idx < 0 || self_idx >= len ||
				curr_num > len){
				return false;
		}

		// ����ڵ������ڸ��ڵ�Ŵ�û��Ԫ���
		// ��ֱ�ӽ�Ԫ����e��ֵ�������p_idx����self_idx��Ч��
		if(node_array[p_idx].getElem() == null){
			node_array[p_idx].setElem(e);
			node_array[p_idx].setParent(-1);
			node_array[p_idx].setIdx(p_idx);
			curr_num++;
			return false;
		}
		
		// p_idx����Ϊ�գ���������self_idx�ڵ�
		node_array[self_idx].setElem(e);
		node_array[self_idx].setIdx(self_idx);
		node_array[self_idx].setLeftMostChild(-1);
		node_array[self_idx].setNextSibling(-1);
		node_array[self_idx].setParent(p_idx);
		// ���p_idxû���ӽڵ�
		if(node_array[p_idx].leftMostChild() == -1){
			// ֱ�ӽ�self_idx����Ϊp_idx���ӽڵ�
			node_array[p_idx].setLeftMostChild(self_idx);
		}
		else{
			// Ѱ��p_idx�ڵ�������ӽڵ��λ��idx
			int idx = node_array[p_idx].leftMostChild();
			while(node_array[idx].rightSibling() != -1){
				idx = node_array[idx].rightSibling();
			}
			// ���ҵ���idx�����ӽڵ�����Ϊself_idx
			node_array[idx].setNextSibling(self_idx);
		}
		curr_num++;
		
		return true;
	}

	//ɾ��n���Ľڵ㣬
	protected void removeSon(int n){
		//�ڵ�ĸ��ڵ�
		int p = node_array[n].parent();
		if(p < 0) return;
		//��ȡ���ڵ�������ӽڵ�
		int idx = node_array[p].leftMostChild();
		//���n�������丸�ڵ�p�������ӽڵ�
		//��������p�������ӽڵ�
		if(idx == n){
			node_array[p].setLeftMostChild(
					node_array[n].rightSibling());
			//node_array[n] = new LcRsTNode();
			node_array[n] = null;
			curr_num--;
			return;
		}
		//Ѱ��idx�����������ֵܽڵ�Ϊn
		while(node_array[idx].rightSibling() != n){
			idx = node_array[idx].rightSibling();
		}
		//��������idx�����ֵܽڵ�Ϊn�����ֵܽڵ㣬
		//������ɾ���˽ڵ�
		node_array[idx].setNextSibling(
				node_array[n].rightSibling());
		node_array[n] = null;
		curr_num--;
	}
	
	//ɾ��nidx�ڵ��Լ��������ӽڵ�
	public boolean removeChild(int nidx){
		if(nidx < 0 ||
			//nidx >curr_num||
			node_array[nidx].getElem() == null){
				return false;
		}
		//�ӽڵ㲻Ϊ�գ����ӽڵ�һһ���
		if(node_array[nidx].leftMostChild() != -1){
			//��ȡnidx�������ӽڵ�����idx
			int idx = 
				node_array[nidx].leftMostChild();
			while(idx != -1){
				//����idx�����ֵܽڵ�����
				int tmp_idx = 
					node_array[idx].rightSibling();
				//�ݹ���ã�ɾ��idx�ڵ�
				removeChild(idx);
				//����ɾ���ڵ�
				idx = tmp_idx;
			}
		}
		//ɾ����ڵ���ӽڵ����ɾ���ڵ�����
		removeSon(nidx);
		
		return true;
	}
	
	//���ض��߶ȴ�ӡһ��Ԫ����
	protected void printnode(int pos, int h){
		for(int i = 0; i < h; i++)
			System.out.print("\t");
		System.out.println(
			"|���� " +node_array[pos] + "(" + pos+ ")");
	}
	
	//��posλ�ø߶�h����ʼ��ǰ���ӡ��
	protected void preOrder(int pos, int h){
		if(pos < 0 || 
			node_array[pos].getElem() == null) 
				return;
		//��ӡ��һ���ڵ�
		printnode(pos, h);
		//��ȡ����ڵ�
		int idx = node_array[pos].leftMostChild();
		//�ݹ����
		while(idx != -1 && 
			  node_array[idx].getElem() != null){
			preOrder(idx, h + 1);
			idx = node_array[idx].rightSibling();
		}
		
	}
	
	//ǰ���ӡ����
	public void ford_print_tree() {
		//��ӡ���ṹ
		if(curr_num == 0) {
			System.out.println("��ǰ����û�нڵ㡣");
			return;
		}
		System.out.println(curr_num + " �ڵ�,ǰ�������ӡ��");
		preOrder(root,0);
		
	}

}
