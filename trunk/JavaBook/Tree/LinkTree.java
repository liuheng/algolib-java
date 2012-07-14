/*
 * Created on 2010-4-27
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Tree;

import Element.ElemItem;
import List.SingleLink2;

/**
 * @author Lu Wei
 *
 * ����������������Ľڵ�洢��ʽ
 */
public class LinkTree {
	//˽�г�Ա������LinkNode���͵����飬�̶�����
	private int len;//�ڵ�������
	//˽�г�Ա���������ڵ����
	private LinkTNode node_array[];
	//˽�г�Ա���������ĸ��ڵ������������
	private int root;
	//���е�ǰ�ڵ�ĸ���
	private int curr_num;
	
	//�в����Ĺ��캯��
	public LinkTree(int _len){
		len = _len;
		node_array = new LinkTNode[_len];
		root = -1;		
		//λ��0��Ϊ����ǰ���ڵ�
		node_array[0] = new LinkTNode(
				new ElemItem<Integer>(0),
				-1,	null, 0);
		for(int i  = 1; i < _len; i++){
			node_array[i] = new LinkTNode();
		}
	}
	
	//�������ĸ��ڵ�
	public void setRoot(int r){
		root = r;
		node_array[r].setParent(0);
		SingleLink2 slk = new SingleLink2();
		slk.insert(new ElemItem<Integer>(r));
		node_array[0].setChildLink(slk);
	}
	
	//��������е�Ԫ����
	public void clear(){
		root = -1;
		for(int i  = 0; i < len; i++){
			node_array[i].setElem(null);
		}
		curr_num = 0;
	}
	
	//�����е�n_idx���ڵ�����ӽڵ㣨���͵�Ԫ�����
	//n_idxΪ���ڵ��
	//����Ľڵ��Ϊself_idx
	public boolean addChild(int p_idx, 
							int self_idx, 
							ElemItem e){
		if(p_idx < 0 || p_idx >= len || 
			self_idx < 0 || self_idx >= len ||
			curr_num > len){
			return false;
		}
		//����ڵ������ڸ��ڵ�Ŵ�û��Ԫ���
		//��ֱ�ӽ�Ԫ����e��ֵ�������p_idx����self_idx��Ч��
		if(node_array[p_idx].getElem() == null){
			node_array[p_idx].setElem(e);
			node_array[p_idx].setChildLink(null);
			node_array[p_idx].setParent(-1);
			node_array[p_idx].setIdx(p_idx);
			curr_num++;
			return true;
		}
		//����ڵ������ڸ��ڵ�Ŵ���Ԫ������
		//��e��ֵ������self_idx����ͬʱ������ز���
		node_array[self_idx].setElem(e);
		node_array[self_idx].setIdx(self_idx);
		node_array[self_idx].setParent(p_idx);
		node_array[self_idx].setChildLink(null);
		//������ڵ�����������ǿյģ����½���������self_ode���Ľڵ�
		if (node_array[p_idx].getChildLink() == null){
			node_array[p_idx].setChildLink(
					new SingleLink2());
		}
		//�ڽڵ���ӽڵ�
		node_array[p_idx].getChildLink().insert(
				new ElemItem<Integer>(self_idx));
		node_array[p_idx].getChildLink().next();
		curr_num++;
		return true;
	}
	
	//ɾ��pIdx���ڵ���ӽڵ����sIdx,���������
	protected void removeSon(int pIdx, int sIdx){
		node_array[pIdx].getChildLink().goFirst();
		//��λ���ڵ���ӽڵ�������Ҫɾ�����ӽڵ����ŵ�λ��
		while(((Integer)(node_array[pIdx].getChildLink(
			).getCurrVal().elem)).intValue()!= sIdx){
			node_array[pIdx].getChildLink().next();
		}
		//ɾ����λ���ϵ��ӽڵ����ţ�����ڵ㣩
		node_array[pIdx].getChildLink().remove();
		curr_num--;
	}
	
	//ɾ��λ��Ϊnidx�Ľڵ���ӽڵ�
	public boolean removeChild(int nidx){
		if(nidx < 0 ||
			node_array[nidx].getElem() == null){
			return false;
		}
		//�ӽڵ㲻Ϊ�գ����ӽڵ�һһ���
		if(node_array[nidx].getChildLink() != null){
			node_array[nidx].getChildLink().goFirst();
			while(node_array[nidx].getChildLink().getCurrVal()
					!= null){
				int idx = 
					((Integer)(node_array[nidx].getChildLink(
						).getCurrVal().getElem())).intValue();
				//�ݹ����
				removeChild(idx);
			}
			
		}
		
		//�����ǰ�ڵ�
		//�������
		removeSon(node_array[nidx].parent(), nidx);
		//�����Ԫ�ض����
		node_array[nidx] = new LinkTNode();		
		return true;
	}
	
	//���ض��߶ȴ�ӡһ��Ԫ����
	protected void printnode(int pos, int h){
		for(int i = 0; i < h; i++)
			System.out.print("\t");
		System.out.println(
			"|���� " +node_array[pos] + "(" + pos+ ")");
	}
	
	//�����������Ԫ����
	protected void posOrder(int pos, int h){
		if(pos < 0 || 
			//pos > curr_num ||
			node_array[pos].getElem() == null) 
			return;
		//�������ӽڵ�
		SingleLink2 lftMostChild = 
			node_array[pos].getChildLink();
		if(lftMostChild != null){
			lftMostChild.goFirst();
			while(lftMostChild.getCurrVal() != null){
				int idx = ((Integer)(
					lftMostChild.getCurrVal().getElem())
					).intValue();
				posOrder(idx, h + 1);
				lftMostChild.next();
				
			}
		}
		//���ʸ��ڵ�
		printnode(pos, h);
	}
	
	//ǰ���������Ԫ����
	protected void preOrder(int pos, int h){
		if(pos < 0 || 
			//pos > curr_num ||
			node_array[pos].getElem() == null) 
			return;
		//���ʸ��ڵ�
		printnode(pos, h);
		//�������ӽڵ�
		SingleLink2 lftMostChild 
			= node_array[pos].getChildLink();
		if(lftMostChild != null){
			lftMostChild.goFirst();
			while(lftMostChild.getCurrVal() != null){
				int idx = ((Integer)(
					lftMostChild.getCurrVal().getElem())
					).intValue();
				preOrder(idx, h + 1);
				lftMostChild.next();		
			}
		}
	}
	
	//�����ӡ��
	public void post_print_tree(){
		if(curr_num == 0) {
			System.out.println("��ǰ����û�нڵ㡣");
			return;
		}
		System.out.println(curr_num + " �ڵ�,���������ӡ��");
		posOrder(1, 0);
	}
	//ǰ���ӡ��
	public void ford_print_tree(){
		if(curr_num == 0) {
			System.out.println("��ǰ����û�нڵ㡣");
			return;
		}
		System.out.println(curr_num + " �ڵ�,ǰ�������ӡ��");
		preOrder(1,0);
	}
}
