package Student;

public class Student implements Comparable {
    public Student(String name, float gpa) {
        this.name = name;
        this.gpa = gpa;
    }
    public Student() {}
    //Object is the ancestor of every class
    public int compareTo(Object o) {
    //cast the correct type (Student)o
        if ( ((Student)o).gpa < gpa )     return 1;
        else if ( ((Student)o).gpa > gpa )    return -1;
        else   return 0;
        }
    public boolean equals(Object o) {
    //cast the correct type (Student)o
        if (gpa == ((Student) o).gpa)  return true;
        else return false;
        }
    public String getName() { return name;}
    public float getGpa() { return gpa;}
    private String name;
    private float gpa;
}
