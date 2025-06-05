package test;

import Time.Time;

public class TestTime {
    public static void main(String[] args) {
        Time t1 = new Time();                  
        Time t2 = new Time(13);             
        Time t3 = new Time(13, 45);            
        Time t4 = new Time(13, 45, 30);        

        System.out.println("t1 (24h): " + t1.toUniversalString() + " | t1 (12h): " + t1.toString());
        System.out.println("t2 (24h): " + t2.toUniversalString() + " | t2 (12h): " + t2.toString());
        System.out.println("t3 (24h): " + t3.toUniversalString() + " | t3 (12h): " + t3.toString());
        System.out.println("t4 (24h): " + t4.toUniversalString() + " | t4 (12h): " + t4.toString());

        t1.setTime(25, 61, 70);
        System.out.println("\nWrong value: " + t1.toUniversalString());
    }
}
