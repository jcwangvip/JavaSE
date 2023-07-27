package use.function;

public class ConverterMain {

    public static void main(String[] args) {

        demo2();

//        demo1();
    }

    private static void demo2() {
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted.getClass());   //class java.lang.Integer
    }

    private static void demo1() {
        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted.getClass());
    }
}
