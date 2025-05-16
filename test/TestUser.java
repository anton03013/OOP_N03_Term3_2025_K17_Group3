package test;

import review.User;

public class TestUser {
    public static void main(String[] args) {
        User user1 = new User("Dung", 101, 20);
        User user2 = new User("Lmao", 102, 30);

        user1.printI4(user1.getName(), user1.getId(), user1.getAge());
        user2.printI4(user2.getName(), user2.getId(), user2.getAge());

        user1.setName("Dung Dinh");
        user1.setAge(23);

        System.out.println("\nUpdated:");
        user1.printI4(user1.getName(), user1.getId(), user1.getAge());
    }
}

