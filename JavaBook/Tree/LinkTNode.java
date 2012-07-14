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
 * ������������ṹ���ӽڵ��ʾ������
 * ����ĵ������Ǹ�Ч������
 */
public class LinkTNode{
	//�ڵ������Ԫ����
	private ElemItem elem;
	//�ڵ㸸�ڵ�
	private int parentNode;
	//ÿ�����ڵ��а���һ��������,
	//������ÿ���ڵ��е�Ԫ������LinkTNode����
	private SingleLink2 link_idx;
	//�ڵ��ڽڵ������е�λ��idx
	private int idx;
	
	//�޲����Ĺ��캯��
	public LinkTNode(){
		elem = null;
		parentNode = -1;
		link_idx = null;
		idx = -1;
	}
	
	//�������Ĺ��캯��
	public LinkTNode(ElemItem e, int pn,
				SingleLink2 sk, int _idx){
		elem = e;
		parentNode = pn;
		link_idx = sk;
		idx = _idx;
	}
	
	//���ýڵ�Ԫ����
	public void setElem(ElemItem _elem) {
		elem = _elem;	
	}
	//��ȡ�ڵ��Ԫ����
	public ElemItem getElem(){
		return elem;
	}
	
	//�ж��Ƿ���Ҷ�ڵ�
	public boolean isLeaf() {
		//���ڵ��е������ǿգ�����Ҷ�ڵ�
		return link_idx == null;
	}

	//���ظ��ڵ�
	public int parent() {
		return parentNode;
	}

	//�趨���ڵ�
	public void setParent(int pn) {
		parentNode = pn;
	}
	
	//���������ӽڵ��Ӧ������
	public SingleLink2 getChildLink(){
		return link_idx;
	}
	
	//�������ýڵ���ӽڵ�
	public void setChildLink(SingleLink2 slk){
		link_idx = slk;
	}
	
	//��ȡ��ǰ�ڵ��ڽڵ������е�λ��
	public int getIdx(){
		return idx;
	}
	//���õ�ǰ�ڵ��ڽڵ������е����
	public void setIdx(int _idx){
		idx = _idx;
	}
	//����Ԫ����String��ʽ
	public String toString(){
		return elem.getElem().toString();
	}
	
}
