package test;

import Student.Student;
import java.util.Set;
import java.util.TreeSet;
import java.util.Iterator;

public class TestStudent {
    public static void main(String[] args) {
        Student s1 = new Student("Fred", 3.0F);
        Student s2 = new Student("Sam", 3.5F);
        Student s3 = new Student("Steve", 2.1F);
        if (s3.compareTo((Object)s2) < 0)
            System.out.println("\n" + s3.getName() + " has a lower gpa than " + s2.getName());
        //Set is an interface in Java ; TreeSet is its implementation
        Set<Student> studentSet = new TreeSet<>();
        //Method of set add(element)
        studentSet.add(s1); studentSet.add(s2); studentSet.add(s3);
        //Iterator interface access elements of Map, List, Set
        Iterator<Student> i = studentSet.iterator();
        //method of Iterator hasNext()
        while(i.hasNext()) System.out.println(i.next().getName());
    }
}