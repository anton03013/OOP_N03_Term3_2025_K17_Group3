package User;

public class User {
    private String name;
    private int id;
    private int age;

    public User(String name, int id, int age){
        this.name = name ;
        this.id = id;
        this.age = age;
    }
    public String getName(){    
        return name;
    }
    public int getId(){
        return id;
    }
    public int getAge(){
        return age;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setId(int id){
        this.id = id;
    }
    public void setAge(int age){
        this.age = age;
    }
    public void printI4(String name, int id, int age){
        System.out.println("\nName: " + name + ", ID: " + id + ", Age: " + age);
    }
}