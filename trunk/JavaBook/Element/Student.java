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
 * 学生类，用于测试ElemItem类的可比较性 Studnet.java
 */
public class Student implements Comparable{
	private int ID;		// 学号
	private String name;	// 名字
	private int age;	// 年龄
	private int ave_score;	// 平均分
	public Student(){// 默认构造函数
		ID = -1;
		age = -1;
		ave_score = -1;
	}
	//有参数构造函数
	public Student(int _id, String _n, int _age, int _ave_age){
		ID = _id;
		name = _n;
		age = _age;
		ave_score = _ave_age;
	}
	//实现Comparable接口函数
	//当前学生的成绩比带比较的学生的成绩高时返回1，
	//低时返回-1，相等时返回0
	public int compareTo(Object _student) {
		Student _s = (Student)(_student);
		return (ave_score > _s.ave_score)?1:
			((ave_score < _s.ave_score)? -1:0);
	}
	//获取学生的信息
	public int getID(){ return ID;}
	public String getName(){ return name;}
	public int getAge() {return age;}
	public int getAve_score() {return ave_score;}
	
	//打印的内容，打印学生的学号，年龄和成绩
	public String toString(){
		return "Student number: " + ID + 
				",\t name: " + name + 
				",\t age: " + age + 
				",\t aveage score: " + ave_score;
	}
}


