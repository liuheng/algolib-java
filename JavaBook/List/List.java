/*
 * Created on 2010-4-14
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package List;

/**
 * @author Administrator
 *
 * ���Ա�ADT�Լ�����ʵ���㷨��
 * ˳�������������ַ�Ϊ������˫�����ѭ������
 */

import Element.ElemItem;;
/*
 * ��������ӿ����
 */
interface List {
	public void insert (ElemItem elem);	//�ڵ�ǰλ�ò���Ԫ��
	public ElemItem remove();			//ɾ�������ص�ǰԪ��
	public void append(ElemItem elem);	//�ڵ�ǰ���Ա�β�����Ԫ��
	public void clear();				//���������������
	public void goFirst();				//����ǰλ�ø�ֵΪ��һ��λ��
	public int next();					//���õ�ǰλ��Ϊ��һλ�ò�����λ��
	public int prev();					//���õ�ǰλ��Ϊǰһλ�ò�����λ��
	public void setCurrVal(ElemItem elem);	//���õ�ǰλ�õ�Ԫ����
	public ElemItem getCurrVal();		//��ȡ��ǰλ�õ�Ԫ����
	public int getSize();				//��ȡ��ǰ���Ա����ЧԪ�ظ���
	public int getTotalSize();			//��ȡ���Ա�����ߴ�
	public boolean inList(); 			//��ǰλ���Ƿ���������
	public void printList();			//��ӡ��ǰ���Ա��е���ЧԪ��
}
