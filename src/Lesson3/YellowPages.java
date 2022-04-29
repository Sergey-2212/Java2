package Lesson3;

import java.util.ArrayList;

public class YellowPages {

    private static ArrayList<Person> list = new ArrayList<>();

    public static void add(String name, String phoneNumber) {
        if (name == null || phoneNumber == null) {
            System.out.println("Введено недействительное значение.");
            return;
        }
        list.add(new YellowPages().new Person(name, phoneNumber));
    }

    public static void get(String name) {
        int count = 0;
        if (name == null) {System.out.println("Уточните параметр поиска.");}
        for (Person person : list) {
            if (name.equals(person.getName())) {
                System.out.println(person);
                count++;
            }
        }
       if (count == 0) {
           System.out.println("Записей по " + name + " не найдено.");
       }
    }
    public class Person {

        private String name;
        private String phoneNumber;

        private Person(String name, String phoneNumber) {
            this.name = name;
            this.phoneNumber = phoneNumber;
        }

        public String getName() {
            return name;
        }


        @Override
        public String toString() {
            return "Person '" + name + '\'' +
                    ", has phone number '" + phoneNumber + '\'';
        }
    }
}
