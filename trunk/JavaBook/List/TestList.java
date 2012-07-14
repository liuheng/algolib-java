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
import List.SequentialList;
public class TestList {
	public static void main(String args[]){
		SequentialList sList = new SequentialList(10);
		ElemItem<Integer> e1;
		for(int i = 0; i < sList.getTotalSize();i++){
			e1 = new ElemItem<Integer>(i);
			sList.append(e1);
			sList.next();
		}
		sList.next();
		sList.next();
		sList.next();
		sList.printList();
		sList.remove();
		sList.remove();
		sList.printList();
		System.out.println(sList.getCurrVal().getElem());
		sList.insert(new ElemItem<Double>(1.11));
		sList.printList();
		sList.insert(new ElemItem<String>("java"));
		sList.printList();
		System.out.println(sList.getCurrVal().getElem());
		sList.printList();
		
		System.out.println("#############################################");
		SingleLink sglList = new SingleLink();
		for(int i = 0; i < 10; i++){
			e1 = new ElemItem<Integer>(i);
			sglList.append(e1);
			sglList.next();
		}
		sglList.next();
		sglList.next();
		sglList.next();
		sglList.printList();
		sglList.remove();
		sglList.remove();
		sglList.printList();
		System.out.println(sglList.getCurrVal().getElem());
		sglList.insert(new ElemItem<Double>(1.11));
		sglList.printList();
		sglList.insert(new ElemItem<String>("java"));
		sglList.printList();
		System.out.println(sglList.getCurrVal().getElem());
		sglList.printList();
		
		System.out.println("#############################################");
		//SingleLink2 sglList2 = new SingleLink2();
		//DoubleLink sglList2 = new DoubleLink();
		CycleLink sglList2 = new CycleLink();
		for(int i = 0; i < 10; i++){
			e1 = new ElemItem<Integer>(i);
			sglList2.append(e1);
			sglList2.next();
		}
		sglList2.prev();
		sglList2.prev();
		sglList2.prev();
		sglList2.printList();
		sglList2.remove();
		sglList2.remove();
		sglList2.printList();
		//if(sglList2.getCurrVal().getElem() != null) System.out.println(sglList2.getCurrVal().getElem());
		sglList2.insert(new ElemItem<Double>(1.11));
		sglList2.printList();
		sglList2.insert(new ElemItem<String>("java"));
		sglList2.printList();
		System.out.println(sglList2.getCurrVal().getElem());
		sglList2.printList();
	}

}
