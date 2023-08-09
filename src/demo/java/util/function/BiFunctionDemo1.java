package demo.java.util.function;

import java.util.function.BiFunction;

public class BiFunctionDemo1 implements BiFunction<BiFunctionDemo1.Person, String, Integer> {


    @Override
    public Integer apply(Person person, String s) {
        person.setName(s);
        return person.getAge();
    }


    public static void main(String[] args) {

        BiFunctionDemo1 biFunctionDemo = new BiFunctionDemo1();
        Integer zhangSan = biFunctionDemo.apply(new Person(), "zhangsan");
        System.out.println("zhangSan------>  " + zhangSan);
    }


    public static class Person {


        private String name;
        private Integer age;

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            if (name.equals("zhangsan")) {
                return 1;
            }
            return age;
        }
    }
}
