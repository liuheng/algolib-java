/*
 * Created on 2010-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

/**
 * @author Lu Wei
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
import Element.ElemItem;
import List.List;
// ��˳���ʵ�����Ա�
class SequentialList implements List{
	private ElemItem[] SeqList; 
	private int listSize;	//˳���Ĵ�С
	private int currSize;	//��ǰ������ЧԪ�ظ���
	private int curr;		//��ǰλ��
	private void init(int mSize){//����˳����С��ʼ��
		curr = 0;			// ��ǰλ��Ϊ��һ��
		listSize = mSize;	//˳���Ĵ�С
		currSize = 0;		//��ǰ��ЧԪ�ظ���
		SeqList = new ElemItem[mSize];//�����ռ�
		
	}
	SequentialList(){
		init(0);	//�Դ�СΪ0��ʼ��
	}
	SequentialList(int mSize){
		init(mSize);	//�Դ�СΪmSize��ʼ��
	}
	
	public void insert(ElemItem elem) {
		if (currSize > listSize){
			return;
		}
		for(int i = currSize - 1; i >= curr; i--){
			SeqList[i + 1] = SeqList[i];
		}
		SeqList[curr] = elem;
		currSize++;
		
	}

	public ElemItem remove() {
		if(0 == currSize)
			return null;
		ElemItem for_ret = SeqList[curr];//������ȡ��ǰԪ��
		if(curr == currSize - 1){//��ǰλ���Ǳ��β��
			curr--;
			currSize--;
			return for_ret;
		}
		for(int c = curr; c < currSize - 1; c++){//����ǰλ���Ժ��Ԫ��������ǰ��
			SeqList[c] = SeqList[c + 1];
		}
		currSize--;	//��ǰ��ЧԪ�ظ�����1
		//curr--;		//��ǰλ��ǰ��1
		return for_ret;
	}
	
	public void goFirst(){
		if (0 == listSize){
			System.out.println("��ǰ˳����ǿյ�");
			return;
		}
		curr = 0;
	}

	public void append(ElemItem elem) {
		if (0 == listSize){
			System.out.println("��ǰ˳����ǿյ�");
			return;
		}
		else if(curr >= listSize){
			System.out.println("��ǰ˳����Ѿ�����");
			return;
		}
		else{
			SeqList[currSize++] = elem;	//��Ԫ�ز��뵽ĩβͬʱ����ǰ��������1
		}
	}

	public void clear() {
		if (0 == listSize){
			System.out.println("��ǰ˳����ǿյ�");
			return;
		}
		else{
			currSize = 0;//����ǰ��ЧԪ�ظ�����Ϊ0
			curr = 0;// ����ǰλ����Ϊ˳����ͷ��
		}
		
	}

	public int next() {
		if(curr < listSize - 1)
			curr++;
		return curr;
		
	}

	public int prev() {
		if(curr >= 1)
			curr--;
		return curr;
	}

	public void setCurrVal(ElemItem elem) {
		SeqList[curr].elem = (Comparable) elem;
	}

	public ElemItem getCurrVal() {
		return SeqList[curr];
	}

	public int getSize() {
		return currSize;
	}
	
	public int getTotalSize(){
		return listSize;
	}

	public boolean inList() {
		return (curr >= 0) && (curr < currSize);
	}
	
	public void printList(){
		if (0 == listSize){
			System.out.println("��ǰ˳����ǿյ�");
			return;
		}
		else{
			System.out.println("��ǰ˳����е�Ԫ�أ�");
			for(int c = 0; c < currSize - 1; c++){
				System.out.print(SeqList[c].getElem()+", ");
			}
			System.out.println(SeqList[currSize - 1].getElem()+".");
		}
	}
	
}
