/*
 * Created on 2010-4-15
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package Element;

/**
 * @author LuWei
 *
 * ElemItem类的测试示例代码，ExampleElemItem.java
 * 测试类Integer，Double，String和Student类型的
 * ElemItem
 */
public class ExampleElemItem {
	
	public static void main(String args[]){
		//字符串类型的元素项
		ElemItem<String> elemString = 
			new ElemItem<String>("String type elem item");
		//整数类型的元素项
		ElemItem<Integer> elemInt 
				= new ElemItem<Integer>(123);
		//Double型的元素项
		ElemItem<Double> elemDouble 
				= new ElemItem<Double>(12.345);
		//打印各类元素项
		System.out.println(elemString.getElem().toString());
		System.out.println(elemInt.getElem());
		System.out.println(elemDouble.getElem());
		
		// Student 类的测试示例
		//学生1，学号：1，年龄15，成绩97
		ElemItem S1 
			= new ElemItem<Student>(new Student(1, "Jack", 15, 97));
		//学生2,学号：2，年龄16，成绩95
		ElemItem S2 
			= new ElemItem<Student>(new Student(2, "Lucy", 16, 95));
		System.out.println(S1.getElem());
		System.out.println(S2.getElem());
		int cmp = S1.compareTo(S2);
		System.out.println("S1 比 S2成绩" + 
				(cmp>0?"高":(cmp<0?"低":"相同")));
		
	}
}
