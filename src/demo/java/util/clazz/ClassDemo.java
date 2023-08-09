package demo.java.util.clazz;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClassDemo {

    public static void main(String[] args) {
        char i = 'a';
        Optional<Character> cc = Optional.of(i);

        Character character = cc.get();
        Class<? extends Character> aClass = character.getClass();
        List<ClassInfo> collect = new ClassDemo().getClassInfoList(aClass);
        collect.forEach(System.out::println);
    }


    private List<ClassInfo> getClassInfoList(Class<?> aClass) {
        Method[] methods = aClass.getMethods();
        return Arrays.stream(methods)
                .map(x -> {
                    String name = x.getName();
                    int modifiers = x.getModifiers();
                    Class<?> returnType = x.getReturnType();
                    String methodReturnName = returnType.getName();
                    List<String> parameterNameTypes = Arrays.stream(x.getParameterTypes())
                            .map(Class::getName)
                            .collect(Collectors.toList());
                    return new ClassInfo(modifiers, name, methodReturnName, parameterNameTypes);
                }).collect(Collectors.toList());
    }

    class ClassInfo {
        Integer modifiers;
        String name;
        String returnType;
        List<String> parameterTypes;

        public ClassInfo(Integer modifiers, String name, String returnType, List<String> parameterTypes) {
            this.modifiers = modifiers;
            this.name = name;
            this.returnType = returnType;
            this.parameterTypes = parameterTypes;
        }


        @Override
        public String toString() {
            return "ClassInfo{" +
                    "modifiers=" + modifiers +
                    ", name='" + name + '\'' +
                    ", returnType='" + returnType + '\'' +
                    ", parameterTypes=" + parameterTypes +
                    '}';
        }
    }

}
