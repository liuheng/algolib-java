/*
 * Created on 2010-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */

/**
 * @author Luwei
 *
 * ����Ԫ�����࣬�������Java���ͱ�̣����ҷ�������Ϊ
 * �̳���Comparable�������
 * ����T�������������ͣ���������Integer��Double��
 */
package Element;
public class ElemItem<T  extends Comparable<T>> {
	public T elem;			//����Ԫ��
	public ElemItem(){		//Ĭ�Ϲ��캯��
		elem = null;
	}
	public ElemItem(T t){	//�������Ĺ��캯��
		elem = t;
	}
	
	public T getElem(){		//��ȡԪ��
		return elem;
	}
	// Ԫ����֮����пɱȽ���
	public int compareTo(Object oo){
		ElemItem<T> other = (ElemItem<T>)oo;
		return elem.compareTo(other.elem);
	}
}
