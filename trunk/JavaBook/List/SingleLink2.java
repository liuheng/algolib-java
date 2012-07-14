/*
 * Created on 2010-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

import Element.ElemItem;

/**
 * @author Lu Wei
 *
 * ������ṹ��currָ��ʵ�ʵĵ�ǰָ���ǰһ���ڵ㣬
 * ������һ����ͷ�ƽڵ㡣
 */
public class SingleLink2 implements List{
	protected SingleNode head;	//����ı�ͷ
	protected SingleNode tail;	//����ı�β
	protected SingleNode curr;	//����ĵ�ǰλ��
	protected int currSize;	// ��ǰ��������
	public SingleLink2(){	//���캯��
		head = new SingleNode(null, null);
		curr = head;// currָ����ǵ�ǰ�ڵ��ǰ���ڵ�
		tail = head;
		currSize = 0;
	}
	
	public SingleLink2(SingleNode _h, SingleNode _t, SingleNode _crr, int _cs){
		this.head = _h;
		this.tail = _t;
		this.curr = _crr;
		this.currSize = _cs;
	}

	public void insert(ElemItem elem) {
		//�õ�ǰ�ڵ���Ϊ�����ڵ㡢elem�����µĽڵ㣬����Ϊ�µĵ�ǰ�ڵ�
		curr.setNext(new SingleNode(elem, curr.getNext()));
		if(curr == tail){
			tail = tail.getNext();
		}
		currSize++;
		
	}

	public ElemItem remove() {
		if(curr == null || curr.getNext() == null) return null;	//��ɾ������
		ElemItem forReturn = curr.getNext().getElem();//��ǰλ�õ�Ԫ����
		if(curr.getNext() == tail)	//��Ҫɾ�����Ǳ�β��������Դ�
			tail = curr;
		curr.setNext(curr.getNext().getNext());
		currSize--;
		return forReturn;
	}

	public void append(ElemItem elem) {
		tail.setNext(new SingleNode(elem, null));//�ڱ�β���Ԫ����
		tail = tail.getNext();//����βλ�������
		currSize++;
		
	}

	public void clear() {
		head = new SingleNode(null, null);	//����ͷ��ֵΪ�սڵ�
		curr = head;
		tail = head;	//��ԭΪ��ʼ״̬
		
	}

	public void goFirst() {
		curr = head;	
	}

	public int next() {
		if(curr != null && curr.getNext() != null) curr = curr.getNext();
		return 0;
	}

	public int prev() {
		if(curr == null || curr == head){
			return -1;
		}
		else{
			SingleNode pre = head;
			while(pre.getNext() != curr)
				pre = pre.getNext();
			curr = pre;
		}
		return 0;
	}

	public void setCurrVal(ElemItem elem) {
		if(curr != null && curr.getNext() != null)
			curr.getNext().setElem(elem);
	}

	public ElemItem getCurrVal() {
		if(curr != null && curr.getNext() != null)
			return curr.getNext().getElem();
		return null;
	}
	
	public SingleNode getCurr(){
		if(curr != null && curr.getNext() != null)
			return curr;
		return null;
	}

	public int getSize() {
		return currSize;
	}

	public int getTotalSize() {
		return currSize;
	}

	public boolean inList() {
		return (curr != null) && (curr.getNext() != null);
	}

	public void printList() {
		SingleNode ptr = head;
		while(ptr.getNext() != tail){
			System.out.print(ptr.getNext().getElem().getElem() + ", ");
			ptr = ptr.getNext();
		}
		System.out.println(tail.getElem().getElem() + ".");
		
	}
	//���������β�ڵ��Ԫ����
	public ElemItem getTail(){
		return tail.getElem();
	}
}
