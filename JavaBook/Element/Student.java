/*
 * Created on 2010-4-24
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Element;

/**
 * @author Lu Wei
 *
 * ѧ���࣬���ڲ���ElemItem��ĿɱȽ��� Studnet.java
 */
public class Student implements Comparable{
	private int ID;		// ѧ��
	private String name;	// ����
	private int age;	// ����
	private int ave_score;	// ƽ����
	public Student(){// Ĭ�Ϲ��캯��
		ID = -1;
		age = -1;
		ave_score = -1;
	}
	//�в������캯��
	public Student(int _id, String _n, int _age, int _ave_age){
		ID = _id;
		name = _n;
		age = _age;
		ave_score = _ave_age;
	}
	//ʵ��Comparable�ӿں���
	//��ǰѧ���ĳɼ��ȴ��Ƚϵ�ѧ���ĳɼ���ʱ����1��
	//��ʱ����-1�����ʱ����0
	public int compareTo(Object _student) {
		Student _s = (Student)(_student);
		return (ave_score > _s.ave_score)?1:
			((ave_score < _s.ave_score)? -1:0);
	}
	//��ȡѧ������Ϣ
	public int getID(){ return ID;}
	public String getName(){ return name;}
	public int getAge() {return age;}
	public int getAve_score() {return ave_score;}
	
	//��ӡ�����ݣ���ӡѧ����ѧ�ţ�����ͳɼ�
	public String toString(){
		return "Student number: " + ID + 
				",\t name: " + name + 
				",\t age: " + age + 
				",\t aveage score: " + ave_score;
	}
}


