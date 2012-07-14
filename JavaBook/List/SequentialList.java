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
// 用顺序表实现线性表
class SequentialList implements List{
	private ElemItem[] SeqList; 
	private int listSize;	//顺序表的大小
	private int currSize;	//当前表中有效元素个数
	private int curr;		//当前位置
	private void init(int mSize){//根据顺序表大小初始化
		curr = 0;			// 当前位置为第一个
		listSize = mSize;	//顺序表的大小
		currSize = 0;		//当前有效元素个数
		SeqList = new ElemItem[mSize];//创建空间
		
	}
	SequentialList(){
		init(0);	//以大小为0初始化
	}
	SequentialList(int mSize){
		init(mSize);	//以大小为mSize初始化
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
		ElemItem for_ret = SeqList[curr];//首先提取当前元素
		if(curr == currSize - 1){//当前位置是表的尾部
			curr--;
			currSize--;
			return for_ret;
		}
		for(int c = curr; c < currSize - 1; c++){//将当前位置以后的元素依次向前移
			SeqList[c] = SeqList[c + 1];
		}
		currSize--;	//当前有效元素个数减1
		//curr--;		//当前位置前移1
		return for_ret;
	}
	
	public void goFirst(){
		if (0 == listSize){
			System.out.println("当前顺序表是空的");
			return;
		}
		curr = 0;
	}

	public void append(ElemItem elem) {
		if (0 == listSize){
			System.out.println("当前顺序表是空的");
			return;
		}
		else if(curr >= listSize){
			System.out.println("当前顺序表已经满了");
			return;
		}
		else{
			SeqList[currSize++] = elem;	//将元素插入到末尾同时将当前个数增加1
		}
	}

	public void clear() {
		if (0 == listSize){
			System.out.println("当前顺序表是空的");
			return;
		}
		else{
			currSize = 0;//将当前有效元素个数设为0
			curr = 0;// 将当前位置设为顺序表的头部
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
			System.out.println("当前顺序表是空的");
			return;
		}
		else{
			System.out.println("当前顺序表中的元素：");
			for(int c = 0; c < currSize - 1; c++){
				System.out.print(SeqList[c].getElem()+", ");
			}
			System.out.println(SeqList[currSize - 1].getElem()+".");
		}
	}
	
}
